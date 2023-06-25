import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cheeseNum = 0, time = 0;
    static int[][] map;
    static int[][] air;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1)
                    cheeseNum++;
            }
        }

        while(cheeseNum != 0)
            cheese();

        System.out.println(time);
    }

    static void cheese(){
        air = new int[N][M];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        air[0][0] = -1;

        while(!q.isEmpty()){
            Node a = q.poll();

            for(int i = 0; i<4; i++){
                int dr = a.row + dR[i];
                int dc = a.col + dC[i];

                if(dr<0||dc<0||dr>=N||dc>=M)
                    continue;

                if(map[dr][dc] == 1)
                    air[dr][dc]++;
                if(map[dr][dc] == 0 && air[dr][dc] == 0){
                    air[dr][dc] = -1;
                    q.offer(new Node(dr, dc));
                }
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(air[i][j] >= 2){
                    cheeseNum--;
                    map[i][j] = 0;
                }
            }
        }
        time++;
    }
}
class Node{
    int row, col;

    Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}