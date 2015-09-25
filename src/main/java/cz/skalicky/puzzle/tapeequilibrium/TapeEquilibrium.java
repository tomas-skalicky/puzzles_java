package cz.skalicky.puzzle.tapeequilibrium;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.springframework.util.Assert;

/**
 * @see https://codility.com/programmers/task/tape_equilibrium
 * @author Tomas Skalicky
 */
class TapeEquilibrium {

    private static class CalculationState {

        private int leftPartSum;
        private int rightPartSum;

        private CalculationState(final int leftPartSum, final int rightPartSum) {
            this.leftPartSum = leftPartSum;
            this.rightPartSum = rightPartSum;
        }

        private int getDifference() {
            return Math.abs(leftPartSum - rightPartSum);
        }

        private void moveRight(final int number) {
            leftPartSum += number;
            rightPartSum -= number;
        }

        private int moveRightAndGetDifference(final int number) {
            moveRight(number);
            return getDifference();
        }
    }

    public static void main(String[] args) {

        runTest(new int[] { 1 }, -1);
        runTest(new int[] { 1, 2 }, 1);
        runTest(new int[] { 1, 2, 3 }, 0);
        runTest(new int[] { 1, 0, -1 }, 2);
        runTest(new int[] { 3, 1, 2, 4, 3 }, 1);
        System.out.println("SUCCESS");
    }

    private static void runTest(final int[] tape, final int expectedMinDifference) {

        Assert.isTrue(getMinDifference(tape) == expectedMinDifference, String.format(
                "Min difference in tape %s should be %d.", Arrays.toString(tape), expectedMinDifference));
    }

    private static int getMinDifference(final int[] tape) {

        if (tape.length < 2) {
            return -1;
        }

        final int tapeSum = Arrays.stream(tape).sum();
        final CalculationState state = new CalculationState(0, tapeSum);
        // @formatter:off
        final int minDifference =
                IntStream.range(0, tape.length - 1)
                .map(i -> state.moveRightAndGetDifference(tape[i]))
                .min().getAsInt();
        // @formatter:on
        return minDifference;
    }

}
