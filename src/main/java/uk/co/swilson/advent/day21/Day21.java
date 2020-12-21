package uk.co.swilson.advent.day21;

import com.google.common.collect.Sets;
import uk.co.swilson.advent.Solver;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day21 extends Solver {
    public long solvePartOne(String input) {
        var cookbook = new Cookbook(input);
        return cookbook.foods.stream()
                .flatMap(f -> f.ingredients.stream())
                .filter(cookbook.safeIngredients::contains)
                .count();
    }

    private static class Cookbook {
        public final Set<Food> foods;
        public final Set<String> safeIngredients;
        public final Set<String> allAllergens;
        public final Collection<String> allAllergensInLocal;
        public final Map<String, String> englishToLocal;

        public Cookbook(String input) {
            foods = input.lines().map(Food::new).collect(Collectors.toSet());
            allAllergens = foods.stream().flatMap(f -> f.allergens.stream()).collect(Collectors.toSet());
            var allIngredients = foods.stream().flatMap(f -> f.ingredients.stream()).collect(Collectors.toSet());
            englishToLocal = new HashMap<>();
            while (englishToLocal.size() < allAllergens.size()) {
                for (var allergen : allAllergens) {
                    if (englishToLocal.containsKey(allergen)) {
                        continue;
                    }
                    var triggers = foods.stream()
                            .filter(f -> f.hasAllergen(allergen))
                            .collect(Collectors.toList());
                    var unknownIngredients = Sets.difference(allIngredients, new HashSet<>(englishToLocal.values()));
                    var candidates = triggers.stream()
                            .map(f -> f.ingredients)
                            .reduce(unknownIngredients, Sets::intersection);

                    if (candidates.size() == 1) {
                        englishToLocal.put(allergen, candidates.stream().findFirst().get());
                    }
                }
            }
            allAllergensInLocal = englishToLocal.values();
            safeIngredients = Sets.difference(allIngredients, new HashSet<>(allAllergensInLocal));
        }
    }

    private static class Food {
        private final static Pattern PATTERN = Pattern.compile("([a-z ]+) \\(contains ([a-z, ]+)\\)");
        public final Set<String> ingredients;
        public final Set<String> allergens;

        public Food(String input) {
            var matcher = PATTERN.matcher(input);
            matcher.find();
            ingredients = Sets.newConcurrentHashSet(Arrays.asList(matcher.group(1).split(" ")));
            allergens = Sets.newConcurrentHashSet(Arrays.asList(matcher.group(2).split(", ")));
        }

        public boolean hasAllergen(String allergen) {
            return allergens.contains(allergen);
        }
    }

    public long solvePartTwo(String input) {
        return 0;
    }

    @Override
    public String solvePartTwoToString(String input) {
        var cookbook = new Cookbook(input);
        return cookbook.allAllergens.stream().sorted().map(cookbook.englishToLocal::get).collect(Collectors.joining(","));
    }
}
