import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][2];
        for(int i=0; i<n; ++i){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (e1, e2) -> {
            if(e1[1] == e2[1]) return e2[0] - e1[0];
            else return e2[1] - e1[1];
        });

        long max = 0;
        for(int i=0; i<k; ++i){
            max += arr[i][0];
        }

        int[][] remained = new int[n-k][2];
        for(int i=0; i<n-k; ++i){
            remained[i][0] = arr[i+k][0];
            remained[i][1] = arr[i+k][1];
        }

        Arrays.sort(remained, (e1, e2) ->{
            if(e1[0] == e2[0]) return e2[1] - e1[1];
            else return e2[0] - e1[0];
        });

        for(int i=0; i<m; ++i){
            max += remained[i][0];
        }
        System.out.print(max);

    }
}