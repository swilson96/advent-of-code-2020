package uk.co.swilson.advent.day14;

import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day14 implements Solver {
    private static final Pattern MEM_ALLOC = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");

    public long solvePartOne(String input) {
        DecoderChip chip = new VersionOne();
        return chip.decode(input);
    }

    public long solvePartTwo(String input) {
        DecoderChip chip = new VersionTwo();
        return chip.decode(input);
    }

    private abstract class DecoderChip {
        protected String mask;
        protected Map<Long, Long> mem = new HashMap<>();

        abstract protected void applyMaskAndSave(long key, long input);

        public long decode(String input) {
            var lines = input.lines().collect(Collectors.toList());

            for (var line: lines) {
                if (line.startsWith("mask")) {
                    mask = line.substring(7);
                } else {
                    Matcher match = MEM_ALLOC.matcher(line);
                    match.find();
                    applyMaskAndSave(
                            Long.parseLong(match.group(1)),
                            Long.parseLong(match.group(2)));
                }
            }

            return mem.values().stream().mapToLong(i -> i).sum();
        }
    }

    private class VersionOne extends DecoderChip {
        @Override
        protected void applyMaskAndSave(long key, long input) {
            int radix = mask.length() - 1;
            for (String bit : mask.split("")) {
                if (bit.equals("0")) {
                    input &= ~(1L << radix);
                }
                if (bit.equals("1")) {
                    input |= (1L << radix);
                }
                --radix;
            }
            mem.put(key, input);
        }
    }

    private class VersionTwo extends DecoderChip {
        @Override
        protected void applyMaskAndSave(long inputKey, long value) {
            var keys = new long[] { inputKey };
            int radix = mask.length() - 1;
            for (String bit : mask.split("")) {
                if (bit.equals("1")) {
                    for (int i = 0; i < keys.length; ++i) {
                        keys[i] |= (1L << radix);
                    }
                }
                if (bit.equals("X")) {
                    final long finalRadix = radix;
                    keys = Arrays.stream(keys).flatMap(k -> LongStream.of(
                            k | (1L << finalRadix),
                            k & ~(1L << finalRadix)
                        )).toArray();
                }
                --radix;
            }
            for (long maskedKey : keys) {
                mem.put(maskedKey, value);
            }
        }
    }
}
