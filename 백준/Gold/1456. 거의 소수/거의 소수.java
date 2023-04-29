import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       long a = Long.parseLong(st.nextToken());
       long b = Long.parseLong(st.nextToken());
       System.out.println(solution(a, b));
    }
    
    public static int solution(long a, long b) {
        long[] arr = new long[10000001];
        for (int i = 2; i < arr.length; i++) {
            arr[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(arr.length); i++) { 
            if (arr[i] == 0) {
                continue;
            }
            for (int j = i + i; j < arr.length; j = j + i) { 
                arr[j] = 0;
            }
        }


        int count = 0;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] != 0) {
                long temp = arr[i];
                while ((double) arr[i] <= (double)b/(double)temp) {
                    if ((double) arr[i] >= (double)a/(double)temp ) {
                        count++;
                    }
                    temp = temp * arr[i];
                }
            }
        }
        return count;
    }
}