package cz.skalicky.puzzle.tworectanglesintersection;

import org.springframework.util.Assert;

public class TwoRectanglesIntersection {

    private static class Point {
        private final int x;
        private final int y;

        private Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {

        Assert.isTrue(solve(0, 2, 5, 10, 3, 1, 20, 15) == 16);
        Assert.isTrue(solve(0, 2, 5, 10, 5, 1, 20, 15) == 0);
        Assert.isTrue(solve(0, 2, 5, 10, 5, 10, 20, 15) == 0);
        Assert.isTrue(solve(0, 2, 5, Integer.MAX_VALUE / 2, 2, 10, 20, Integer.MAX_VALUE) == -1);
        System.out.println("SUCCESS");
    }

    private static int solve(final int k, final int l, final int m, final int n, final int p, final int q,
            final int r, final int s) {
        final Point rectangle1LowerLeft = new Point(k, l);
        final Point rectangle1UpperRight = new Point(m, n);
        final Point rectangle2LowerLeft = new Point(p, q);
        final Point restangle2UpperRight = new Point(r, s);

        final int lowerLeftX = Math.max(rectangle1LowerLeft.x, rectangle2LowerLeft.x);
        final int lowerLeftY = Math.max(rectangle1LowerLeft.y, rectangle2LowerLeft.y);
        final Point intersectionLowerLeft = new Point(lowerLeftX, lowerLeftY);

        final int upperRightX = Math.min(rectangle1UpperRight.x, restangle2UpperRight.x);
        final int upperRightY = Math.min(rectangle1UpperRight.y, restangle2UpperRight.y);
        final Point intersectionUpperRight = new Point(upperRightX, upperRightY);

        if ((intersectionLowerLeft.x >= intersectionUpperRight.x)
                || (intersectionLowerLeft.y >= intersectionUpperRight.y)) {
            return 0;
        } else {
            final int length = intersectionUpperRight.x - intersectionLowerLeft.x;
            final int high = intersectionUpperRight.y - intersectionLowerLeft.y;
            final long product = length * (long) high;
            if (product > Integer.MAX_VALUE) {
                return -1;
            } else {
                return (int) product;
            }
        }
    }

}
