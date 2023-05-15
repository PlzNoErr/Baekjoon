import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] canlender = new int[366];
        StringTokenizer st;
        for (int j = 0; j < n; j++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            for (int i = s; i <= e; i++) canlender[i]++;
        }
        int row = 0;
        int col = 0;
        int ans = 0;
        for (int i = 0; i < 366; i++) {
            if (canlender[i] != 0) {
                row = Math.max(row, canlender[i]);
                col++;
            } else {
                ans += row * col;
                row = 0;
                col = 0;
            }
        }
        ans += row * col;
        System.out.println(ans);
    }
}