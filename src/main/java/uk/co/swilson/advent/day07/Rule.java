package uk.co.swilson.advent.day07;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Rule {;
    public final String parent;
    public final List<RuleChild> children;

    public Rule(String input) {
        var bits = input.split(" bags contain ");
        parent = bits[0];
        children = Arrays.stream(bits[1].split(", "))
                .map(RuleChild::fromString)
                .filter(b -> b != null)
                .collect(Collectors.toList());
    }

    public static class RuleChild {
        private static final Pattern RULE_CHILD = Pattern.compile("(\\d+) (\\S+ \\S+) bags?\\.?");

        public final int quantity;
        public final String name;

        private RuleChild(int quantity, String name) {
            this.quantity = quantity;
            this.name = name;
        }

        public static RuleChild fromString(String input) {
            var matcher = RULE_CHILD.matcher(input);
            if (matcher.find()) {
                return new RuleChild(Integer.parseInt(matcher.group(1)), matcher.group(2));
            }
            return null;
        }
    }
}

