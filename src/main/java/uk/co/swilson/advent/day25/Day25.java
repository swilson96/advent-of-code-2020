package uk.co.swilson.advent.day25;

import uk.co.swilson.advent.Solver;

public class Day25 extends Solver {
    public long solvePartOne(String input) {
        var bits = input.split("\\r?\\n");
        var cardPublicKey = Long.valueOf(bits[0]);
        var doorPublicKey = Long.valueOf(bits[1]);
        var doorLoopsize = decryptKey(doorPublicKey);

        // transform card public with door private
        var encryptionKey = transform(cardPublicKey, doorLoopsize);
        return encryptionKey;
    }

    public long decryptKey(long publicKey) {
        long step = 0;
        long val = 1;
        while (val != publicKey) {
            ++step;
            val = singleStep(val, 7);
        }
        return step;
    }

    public long transform(long subjectValue, long loopSize) {
        long step = 0;
        long val = 1;
        while (step < loopSize) {
            ++step;
            val = singleStep(val, subjectValue);
        }
        return val;
    }

    public long singleStep(long val, long subject) {
        return (val * subject) % 20201227;
    }

    public long solvePartTwo(String input) {
        return 0;
    }
}
