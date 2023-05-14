import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(getCountOfRightTriangle(n, points));
    }

    static int getCountOfRightTriangle(int n, int[][] points) {
        int countOfRightTriangle = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    countOfRightTriangle = addCountIfRightTriangle(points, countOfRightTriangle, i, j, k);
                }
            }
        }
        return countOfRightTriangle;
    }

    static int addCountIfRightTriangle(int[][] points, int countOfRightTriangle, int i, int j, int k) {
        if (isRightTriangleTest(getSquaredSides(points[i], points[j], points[k]))) {
            countOfRightTriangle++;
        }
        return countOfRightTriangle;
    }

    static boolean isRightTriangleTest(long[] squaredSides) {
        long max = 0;
        for (long squaredSide : squaredSides) {
            max = Math.max(max, squaredSide);
        }
        return max * 2 == squaredSides[2] + squaredSides[0] + squaredSides[1];
    }

    static long[] getSquaredSides(int[] dot1, int[] dot2, int[] dot3) {
        return new long[]{getSide(dot1, dot2), getSide(dot1, dot3), getSide(dot2, dot3)};
    }

    static long getSide(int[] dot1, int[] dot2) {
        long xLength = Math.abs(dot1[0] - dot2[0]);
        long yLength = Math.abs(dot1[1] - dot2[1]);
        return (xLength * xLength) + (yLength * yLength);
    }
}