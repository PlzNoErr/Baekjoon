import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] points = new int[n][2];

        for(int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            points[i][0] = Integer.parseInt(input[0]);
            points[i][1] = Integer.parseInt(input[1]);
        }

        HashMap<Integer, Integer> xCount = new HashMap<>();
        HashMap<Integer, Integer> yCount = new HashMap<>();

        for(int i = 0; i < n; i++) {
            Integer countX = xCount.get(points[i][0]);
            Integer countY = yCount.get(points[i][1]);

            if(countX == null)
                xCount.put(points[i][0], 1);
            else
                xCount.put(points[i][0], countX + 1);

            if(countY == null)
                yCount.put(points[i][1], 1);
            else
                yCount.put(points[i][1], countY + 1);
        }


        long result = 0;
        for(int i = 0; i < n; i++) {
            long xValue = xCount.get(points[i][0]) - 1;
            long yValue = yCount.get(points[i][1]) - 1;

            result += xValue * yValue;
        }

        System.out.println(result);
    }
}