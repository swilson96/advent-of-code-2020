package uk.co.swilson.advent.day07;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagIterator {
    private final Map<String, List<Rule.RuleChild>> childrenOf;

    public BagIterator(String input) {
        childrenOf = new HashMap<>();
        input.lines().map(Rule::new)
                .forEach(rule -> {
                    childrenOf.putIfAbsent(rule.parent, Lists.newArrayList());
                    for (var child : rule.children) {
                        childrenOf.get((rule.parent)).add(child);
                    }
                });
    }

    public int countBagsInSingleBag(String bag) {
        var children = childrenOf.get(bag);
        return children == null ? 0 : children.stream()
                .mapToInt(child -> child.quantity * (1 + this.countBagsInSingleBag(child.name)))
                .sum();
    }
}
