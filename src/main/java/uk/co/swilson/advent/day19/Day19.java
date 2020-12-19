package uk.co.swilson.advent.day19;

import uk.co.swilson.advent.Solver;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day19 implements Solver {

    public long solvePartOne(String input) {
        var bits = input.split("\r?\n\r?\n");
        var ruleMap = bits[0].lines()
                .map(r -> r.split(": "))
                .collect(Collectors.toMap(
                        ruleBits -> ruleBits[0],
                        ruleBits ->ruleBits[1]
                ));

//        var regex = "^" + buildRegex(ruleMap.get("0"), ruleMap) + "$";
//        var matcher = Pattern.compile(regex).matcher(bits[1]);
//        var count = 0;
//        while (matcher.find()) {
//            ++count;
//        }
//        return count;

        Map<String, Set<String>> cache = new HashMap<>();

        var matchingStrings = buildMatches(ruleMap.get("0"), ruleMap, cache);
        return bits[1].lines()
                .filter(m -> matchingStrings.contains(m))
                .count();
    }

    private Set<String> buildMatches(String rule, Map<String, String> ruleMap, Map<String, Set<String>> cache) {
        if (cache.containsKey(rule)) {
            return cache.get(rule);
        }
        var matches = Arrays.stream(rule.split(" \\| "))
                .flatMap(s -> {
                    if (s.startsWith("\"")) {
                        return Stream.of(s.replaceAll("\"", ""));
                    }
                    Stream<String> matchStubs = Stream.of("");
                    var subMatches = Arrays.stream(s.split(" "))
                            .map(ruleMap::get)
                            .map(r -> buildMatches(r, ruleMap, cache))
                            .collect(Collectors.toList());
                    for (var matchList : subMatches) {
                        matchStubs = matchStubs.flatMap(stub -> matchList.stream().map(m -> stub + m));
                    }
                    return matchStubs;
                })
                .collect(Collectors.toSet());
        cache.put(rule, matches);
        return matches;
    }

    // Ought to work, but the direct method was easier to debug
    private String buildRegex(String rule, Map<String, String> ruleMap) {
        var orSplit = rule.split(" \\| ");
        var ret = Arrays.stream(orSplit)
                .map(s -> {
                    if (s.startsWith("\"")) {
                        return s.replaceAll("\"", "");
                    }
                    var subRules = s.split(" ");
                    return Arrays.stream(subRules)
                            .map(ruleMap::get)
                            .map(r -> buildRegex(r, ruleMap))
                            .map(r -> r.contains("|") ? "(" + r + ")" : r)
                            .collect(Collectors.joining(""));
                })
                .map(s -> s.length() == 1 ? s : "(" + s + ")")
                .collect(Collectors.joining("|"));
        return ret;
    }

    public long solvePartTwo(String input) {
        var bits = input.split("\r?\n\r?\n");
        var ruleMap = bits[0].lines()
                .map(r -> r.split(": "))
                .collect(Collectors.toMap(
                        ruleBits -> ruleBits[0],
                        ruleBits ->ruleBits[1]
                ));

//        ruleMap.put("8", "42 | 42 8");
//        ruleMap.put("11", "42 31 | 42 11 31");

        Map<String, Set<String>> cache = new HashMap<>();

        // Build everything that has no loops
        buildMatches("31", ruleMap, cache);
        buildMatches("42", ruleMap, cache);

        return bits[1].lines()
                .filter(m -> messageMatchesNewRuleZero(m, cache))
                .count();
    }

    private boolean messageMatchesNewRuleEleven(String message, Map<String, Set<String>> cache) {
        // 42 31 | 42 11 31
        for (var head : cache.get("42")) {
            if (message.startsWith(head)) {
                for (var tail : cache.get("31")) {
                    var withoutHead = message.substring(head.length());
                    if (withoutHead.endsWith(tail)) {
                        var remainder = withoutHead.substring(0, withoutHead.length() - tail.length());
                        if (remainder.isEmpty()) {
                            return true;
                        }
                        if (messageMatchesNewRuleEleven(remainder, cache)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean messageMatchesNewRuleZero(String message, Map<String, Set<String>> cache) {
        // 0: 8 11
        // 8: 42 | 42 8
        // first chop off at least one 8
        for (var candidate : cache.get("42")) {
            if (message.startsWith(candidate)) {
                var chopped = message.substring(candidate.length());
                if (messageMatchesNewRuleEleven(chopped, cache)) {
                    return true;
                }
                if (messageMatchesNewRuleZero(chopped, cache)) {
                    return true;
                }
            }
        }
        return false;
    }
}
