package cz.skalicky.puzzle.tapeequilibrium;

import java.util.Arrays;

class MarcoTapeEquilibriumSolution implements TapeEquilibriumSolution {

    @Override
    public int solution(int[] A) {

        if (A.length < 2) {
            return -1;
        }

        int sum = Arrays.stream(A).parallel().sum();
        Arrays.parallelPrefix(A, Integer::sum);
        return Arrays.stream(A).parallel().limit(A.length - 1).map(i -> Math.abs(sum - 2 * i)).min()
                .getAsInt();
    }

}
