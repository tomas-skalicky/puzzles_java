package cz.skalicky.puzzle.tapeequilibrium;

import java.util.Arrays;
import java.util.List;

public class TapeEquilibriumMain {

    public static void main(String[] args) {

        testSolution(new TomasTapeEquilibriumSolution());
        testSolution(new MarcoTapeEquilibriumSolution());
    }

    private static class TestCase {
        private final int[] tape;
        private final int expectedMinDifference;

        private TestCase(final int[] tape, final int expectedMinDifference) {
            this.tape = tape;
            this.expectedMinDifference = expectedMinDifference;
        }

        private boolean run(final TapeEquilibriumSolution solution) {

            final int actualSolution = solution.solution(tape);
            final boolean success = actualSolution == expectedMinDifference;
            if (!success) {
                System.out.format("%s: Min difference in tape %s should equal to %d, not %d.", solution
                        .getClass().getSimpleName(), Arrays.toString(tape), expectedMinDifference,
                        actualSolution);
            }
            return success;
        }
    }

    private static void testSolution(final TapeEquilibriumSolution solution) {

        // @formatter:off
        List<TestCase> testCases = Arrays.asList(
                new TestCase(new int[] { 1 }, -1),
                new TestCase(new int[] { 1, 2 }, 1),
                new TestCase(new int[] { 1, 2, 3 }, 0),
                new TestCase(new int[] { 1, 0, -1 }, 2),
                new TestCase(new int[] { 3, 1, 2, 4, 3 }, 1));
        final boolean failure = testCases.stream()
                .map(test -> test.run(solution))
                .anyMatch(s -> s == false);
        // @formatter:on
        if (!failure) {
            System.out.format("%s: SUCCESS\n", solution.getClass().getSimpleName());
        }
    }
}
