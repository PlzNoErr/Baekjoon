import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int min = Integer.MAX_VALUE;
        int[][] map = new int[n][n];
        boolean[][] check = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) map[i][j] = s.charAt(j) - '0';
        }
        LinkedList<Node> Q = new LinkedList<>();
        Q.add(new Node(0, 0, 0));
        check[0][0] = true;
        while (!Q.isEmpty()) {
            Node now = Q.poll();
            if (now.change >= min) continue;
            for (int i = 0; i < 4; i++) {
                int r = now.r + dr[i];
                int c = now.c + dc[i];
                if (r < 0 || r >= n || c < 0 || c >= n || check[r][c]) continue;
                if (r == n - 1 && c == n - 1) {
                    min = Math.min(min, now.change);
                    continue;
                }
                check[r][c] = true;
                if (map[r][c] == 0) Q.addLast(new Node(r, c, now.change + 1));
                else Q.addFirst(new Node(r, c, now.change));
            }
        }//
        System.out.println(min);
    }//

    static class Node {
        int r, c, change;

        public Node(int r, int c, int change) {
            this.r = r;
            this.c = c;
            this.change = change;
        }
    }//
}
