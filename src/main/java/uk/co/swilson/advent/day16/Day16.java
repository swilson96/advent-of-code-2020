package uk.co.swilson.advent.day16;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day16 extends Solver {
    private static final Pattern RULE_MATCHER = Pattern.compile("(\\n|^)([\\S ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");
    private static final Pattern MY_TICKET_MATCHER = Pattern.compile("your ticket:\\r?\\n([\\d,]+)\\r?\\n");

    public long solvePartOne(String input) {
        var rules = getRules(input);
        var tickets = otherTickets(input);

        return tickets.stream()
                .flatMap(t -> invalidFields(t, rules))
                .mapToInt(i -> i)
                .sum();
    }

    private Stream<Integer> invalidFields(int[] ticket, List<Rule> rules) {
        return Arrays.stream(ticket)
                .filter(v -> !rules.stream().anyMatch(r -> r.matches(v)))
                .boxed();
    }

    private boolean ticketIsValid(int[] ticket, List<Rule> rules) {
        return invalidFields(ticket, rules).findAny().isEmpty();
    }

    private List<Rule> getRules(String input) {
        var matcher = RULE_MATCHER.matcher(input);
        List<Rule> ret = Lists.newArrayList();
        while (matcher.find()) {
            ret.add(new Rule(
                    matcher.group(2),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5)),
                    Integer.parseInt(matcher.group(6))
            ));
        }
        return ret;
    }

    private List<int[]> otherTickets(String input) {
        return input.split("nearby tickets:\\r?\\n")[1].lines()
                .map(l -> Arrays.stream(l.split(",")).mapToInt(Integer::parseInt).toArray())
                .collect(Collectors.toList());
    }

    public long solvePartTwo(String input) {
        List<Rule> rules = getRules(input);
        List<int[]> tickets = otherTickets(input).stream().filter(t -> ticketIsValid(t, rules)).collect(Collectors.toList());
        int[] myTicket = getMyTicket(input);

        Map<String, Integer> ruleToField = new HashMap<>();
        while (ruleToField.size() < rules.size()) {
            for (var rule : rules) {
                if (ruleToField.containsKey(rule.name)) {
                    continue;
                }
                var possibleFields = IntStream.range(0, rules.size())
                        .filter(i -> !ruleToField.containsValue(i))
                        .filter(i -> tickets.stream().mapToInt(t -> t[i]).allMatch(rule::matches))
                        .toArray();
                if (possibleFields.length == 1) {
                    ruleToField.put(rule.name, possibleFields[0]);
                }
            }
        }

        return ruleToField.entrySet().stream()
                .filter(e -> e.getKey().startsWith("departure"))
                .mapToLong(e -> myTicket[e.getValue()])
                .reduce(1, (a, b) -> a * b);
    }

    private int[] getMyTicket(String input) {
        var myTicketMatcher = MY_TICKET_MATCHER.matcher(input);
        myTicketMatcher.find();
        return Arrays.stream(myTicketMatcher.group(1).split(",")).mapToInt(Integer::parseInt).toArray();
    }

    private static class Rule {
        public final String name;
        private final int a1;
        private final int a2;
        private final int b1;
        private final int b2;

        public Rule(String name, int a1, int a2, int b1, int b2) {
            this.name = name;
            this.a1 = a1;
            this.a2 = a2;
            this.b1 = b1;
            this.b2 = b2;
        }

        public boolean matches(int value) {
            return (a1 <= value && value <= a2) || (b1 <= value && value <= b2);
        }
    }
}
