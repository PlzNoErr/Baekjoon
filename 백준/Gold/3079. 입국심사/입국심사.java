import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long maxTime = 0;
        int [] times = new int[N];

        for(int i = 0 ; i < N ; i++) {
            times[i] = Integer.parseInt(br.readLine());
            maxTime = Math.max(maxTime, times[i]);
        }

        long start = 0;
        long end = maxTime * M;

        long ans = Long.MAX_VALUE;

        while (start <= end) {
            long mid = (start + end) / 2;
            long cnt = 0;

            for (int i = 0 ; i < N ; i++) {
                if (cnt > M) break; 
                cnt += mid / times[i];
            }

            if (cnt < M) start = mid + 1;
            else {
                ans = Math.min(ans, mid);
                end = mid - 1;
            }
        }

        System.out.println(ans);
    }
}