import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] use;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
       use = new boolean[n][n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (Math.abs(map[i][j] - map[i][j + 1])>=2){
                    break;
                }
                if (Math.abs(map[i][j] - map[i][j + 1])==1){
                    if(map[i][j] < map[i][j + 1] && (j+1-L<0 || !checkC(map,i,j,L)))
                        break;
                    if(map[i][j] > map[i][j + 1] && (j+L>n-1 || !checkC(map,i,j+L,L)))
                        break;
                }
                if(j==n-2)
                    count++;
            }
        }
        use = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (Math.abs(map[j][i] - map[j + 1][i])>=2){
                    break;
                }
                if (Math.abs(map[j][i] - map[j + 1][i])==1){
                    if(map[j][i] < map[j + 1][i] && (j+1-L<0 || !checkR(map,i,j,L)))
                        break;
                    if(map[j][i] > map[j + 1][i] && (j+L>n-1 || !checkR(map,i,j+L,L)))
                        break;
                }
                if(j==n-2)
                    count++;
            }
        }
        System.out.println(count);

    }
    static boolean checkC(int[][] map, int k, int idx, int L){
        for (int i = idx; i >idx-L+1 ; i--) {
            if(map[k][i]!=map[k][i-1]||use[k][i-1])
                return false;
        }
        for (int i = idx; i >idx-L+1 ; i--) {
            use[k][i] = true;
            use[k][i-1] = true;
        }

        if(L==1){
            if(use[k][idx])
                return false;
            use[k][idx] = true;
        }

        return true;
    }
    static boolean checkR(int[][] map, int k, int idx, int L){
        for (int i = idx; i >idx-L+1 ; i--) {
            if(map[i][k]!=map[i-1][k]||use[i-1][k])
                return false;
        }
        for (int i = idx; i >idx-L+1 ; i--) {
            use[i][k] = true;
            use[i-1][k] = true;
        }

        if(L==1){
            if(use[idx][k])
                return false;
            use[idx][k] = true;
        }
        return true;
    }
}