package uk.co.swilson.advent.day16;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day16 implements Solver {
    private static Pattern RULE_MATCHER = Pattern.compile(": (\\d+)-(\\d+) or (\\d+)-(\\d+)\\r?\\n");

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
                .mapToObj(i -> i);
    }

    private boolean ticketIsValid(int[] ticket, List<Rule> rules) {
        return Arrays.stream(ticket)
                .allMatch(v -> rules.stream().anyMatch(r -> r.matches(v)));
    }

    private List<Rule> getRules(String input) {
        var matcher = RULE_MATCHER.matcher(input);
        List<Rule> ret = Lists.newArrayList();
        while (matcher.find()) {
            ret.add(new Rule(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4))
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
        return 0;
    }

    private class Rule {
        private final int a1;
        private final int a2;
        private final int b1;
        private final int b2;

        public Rule(int a1, int a2, int b1, int b2) {
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
