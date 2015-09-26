package cz.skalicky.puzzle.tapeequilibrium;

import java.util.Arrays;

/**
 * @see https://codility.com/programmers/task/tape_equilibrium
 * @author Tomas Skalicky
 */
class TomasTapeEquilibriumSolution implements TapeEquilibriumSolution {

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

    @Override
    public int solution(int[] tape) {

        if (tape.length < 2) {
            return -1;
        }

        final int tapeSum = Arrays.stream(tape).parallel().sum();
        final CalculationState state = new CalculationState(0, tapeSum);
        // @formatter:off
        final int minDifference = Arrays.stream(tape)
                .limit(tape.length - 1)
                .map(n -> state.moveRightAndGetDifference(n))
                .min().getAsInt();
        // @formatter:on
        return minDifference;
    }

}
