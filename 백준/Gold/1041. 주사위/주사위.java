import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] minSection = new long[3];
        int[] section = new int[6];
        long answer = 0;
        for (int i = 0; i < 6; i++) section[i] = Integer.parseInt(st.nextToken());
        Arrays.fill(minSection, Integer.MAX_VALUE);

        minSection[0] = Math.min(section[0], section[5]);
        minSection[1] = Math.min(section[1], section[4]);
        minSection[2] = Math.min(section[2], section[3]);
        Arrays.sort(minSection);

        minSection[1] += minSection[0];
        minSection[2] += minSection[1];

        if (N == 1) answer = Arrays.stream(section).sum() - Arrays.stream(section).max().getAsInt();
        else
            answer = 4 * minSection[2] + ((N - 2) * 8 + 4) * minSection[1] + ((N - 2) * (N - 2) * 5 + 4 * (N - 2)) * minSection[0];

        System.out.println(answer);
    }
}