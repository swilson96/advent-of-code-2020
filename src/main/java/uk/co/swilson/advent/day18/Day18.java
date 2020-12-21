package uk.co.swilson.advent.day18;

import uk.co.swilson.advent.Solver;

import java.util.Stack;
import java.util.regex.Pattern;

public class Day18 implements Solver {
    public long solvePartOne(String input) {
        return input.lines()
                .mapToLong(expression -> new Calculator().evaluate(expression))
                .sum();
    }

    private static class Calculator extends BaseCalculator {
        public long evaluateTokenised(String[] tokenisedExpression) {
            for (var c : tokenisedExpression) {
                if (c.equals(" ")) {
                    // NOP
                } else if (Character.isDigit(c.charAt(0))) {
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        var prev = stack.peek();
                        if (prev.equals("+") || prev.equals("*")) {
                            var op = stack.pop();
                            var leftOp = Long.parseLong(stack.pop());
                            var rightOp = Long.parseLong(c);
                            var result = operate(leftOp, op, rightOp);
                            stack.push(String.valueOf(result));
                        }
                        if (prev.equals("(")) {
                            stack.pop();
                            stack.push(c);
                        }
                    }
                } else if (c.equals(")")) {
                    // Fold up the previous operation
                    var rightOp = Long.parseLong(stack.pop());
                    if (stack.isEmpty()) {
                        stack.push(String.valueOf(rightOp));
                    } else {
                        var op = stack.pop();
                        if (op.equals("(")) {
                            stack.push(String.valueOf(rightOp));
                        } else {
                            var leftOp = Long.parseLong(stack.pop());
                            var result = operate(leftOp, op, rightOp);
                            stack.push(String.valueOf(result));
                        }
                    }
                } else {
                    stack.push(c);
                }
            }
            return Long.parseLong(stack.pop());
        }
    }

    private abstract static class BaseCalculator {
        protected final Stack<String> stack = new Stack<>();

        public long evaluate(String expression) {
            // Assume every number is one digit
            return evaluateTokenised(expression.split(""));
        }

        public abstract long evaluateTokenised(String[] tokenisedExpression);

        protected Long operate(long left, String op, long right) {
            var result = op.equals("+") ? left + right : left * right;
            System.out.printf("%d %s %d = %d%n", left, op, right, result);
            return result;
        }
    }

    public long solvePartTwo(String input) {
        return input.lines()
                .mapToLong(this::evaluateAdvancedMath)
                .sum();
    }

    private long evaluateAdvancedMath(String expression) {
        var plusMatcher = Pattern.compile("(\\d+) \\+ (\\d+)").matcher(expression);
        if (plusMatcher.find()) {
            var leftOp = Long.parseLong(plusMatcher.group(1));
            var rightOp = Long.parseLong(plusMatcher.group(2));
            var combined = String.valueOf(leftOp + rightOp);
            expression = expression.replace(plusMatcher.group(0), combined);
            return evaluateAdvancedMath(expression);
        }
        var bracketMatcher = Pattern.compile("\\(([\\d *]*)\\)").matcher(expression);
        if (bracketMatcher.find()) {
            var innerVal = evaluateBasicMath(bracketMatcher.group(1));
            expression = expression.replace(bracketMatcher.group(0), String.valueOf(innerVal));
            return evaluateAdvancedMath(expression);
        }
        return evaluateBasicMath(expression);
    }

    private long evaluateBasicMath(String expression) {
        System.out.println(expression);
        return new Calculator().evaluateTokenised(expression
                .replaceAll("\\(", "( ")
                .replaceAll("\\)", " )")
                .split(" "));
    }
}
