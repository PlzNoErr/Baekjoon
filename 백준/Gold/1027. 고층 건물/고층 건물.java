import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new long[N];
        int[] cache = new int[N];
        for(int i=0; i<N; i++) map[i] = Long.parseLong(st.nextToken());
        int answer = 0;
        boolean cont;
        for(int i=0; i<N-1; i++){
            for(int j=i+1; j<N; j++){
                cont = true;
                for(int k=i+1; k<j; k++){
                    if(check(i,j,k) <= 0){
                        cont = false;
                        break;
                    }
                }
                if(cont){
                    cache[i]++;
                    cache[j]++;
                }
            }
        }

        for (int a : cache) answer = Math.max(a, answer);      
        System.out.println(answer);
    }//

    static long check(int a, int b, int c){
        return  (long)a*map[c] + (long)b*map[a] + (long)c*map[b] - ((long)a*map[b] + (long)b*map[c] + (long)c*map[a]);
    }
}