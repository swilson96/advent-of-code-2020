package uk.co.swilson.advent.day18;

import uk.co.swilson.advent.Solver;

import java.util.Stack;

public class Day18 implements Solver {
    public long solvePartOne(String input) {
        return input.lines()
                .mapToLong(expression -> new Calculator().evaluate(expression))
                .sum();
    }

    private class Calculator extends BaseCalculator {
        public long evaluate(String expression) {
            for (var c : expression.split("")) {
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

    private abstract class BaseCalculator {
        protected final Stack<String> stack = new Stack<>();

        protected Long operate(long left, String op, long right) {
            var result = op.equals("+") ? left + right : left * right;
//            System.out.printf("%d %s %d = %d%n", left, op, right, result);
            return result;
        }
    }

    public long solvePartTwo(String input) {
        return input.lines()
                .mapToLong(expression -> new AdvancedCalculator().evaluate(expression))
                .sum();
    }

    private class AdvancedCalculator extends BaseCalculator {
        public long evaluate(String expression) {
            for (var c : expression.split("")) {
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
}
