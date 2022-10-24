import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        // n이 홀수인 경우
        if (n % 2 == 1) {
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    append(sb, 'R', m - 1);
                    if (i != n - 1) {
                        append(sb, 'D', 1);
                    }
                } else {
                    append(sb, 'L', m - 1);
                    append(sb, 'D', 1);
                }
            }
        }
        // m이 홀수인 경우
        else if (m % 2 == 1) {
            for (int j = 0; j < m; j++) {
                if (j % 2 == 0) {
                    append(sb, 'D', n - 1);
                    if (j != m - 1) {
                        append(sb, 'R', 1);
                    }
                } else {
                    append(sb, 'U', n - 1);
                    append(sb, 'R', 1);
                }
            }
        }
        // 둘다 짝수이다.
        else {
            int r, c;
            r = 0;
            c = 1;

            // 맨 처음칸을 흰칸으로 가정하고
            // 맨 오른쪽 아래칸을 검은 칸으로 가정한다
            // 체스판의 검정칸 중 제일 작은 수를 찾는다
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if ((i + j) % 2 == 1) {
                        if (arr[r][c] > arr[i][j]) {
                            r = i;
                            c = j;
                        }
                    }
                }
            }

            int r1 = 0;
            int c1 = 0;
            int r2 = n - 1;
            int c2 = m - 1;

            // 이 친구는 도착점(n-1, m-1)에서 역주행으로 위로 올라와서 2x2칸으로 좁혀주기 위해서 무빙한다.
            StringBuilder sb2 = new StringBuilder();

            // 시작점과 도착점에서 인접한 2줄에 최소크기의 검정칸이 없다면 압축
            while (r2 - r1 > 1) {
                if (r1 / 2 < r / 2) {
                    append(sb, 'R', m - 1);
                    append(sb, 'D', 1);
                    append(sb, 'L', m - 1);
                    append(sb, 'D', 1);
                    r1 += 2;
                }
                if (r / 2 < r2 / 2) {
                    append(sb2, 'R', m - 1);
                    append(sb2, 'D', 1);
                    append(sb2, 'L', m - 1);
                    append(sb2, 'D', 1);
                    r2 -= 2;
                }
            }

            //2줄 남았을 경우 인접한 2칸에서 최소 검정칸이 없다면 압축
            while (c2 - c1 > 1) {
                if (c1 / 2 < c / 2) {
                    append(sb, 'D', 1);
                    append(sb, 'R', 1);
                    append(sb, 'U', 1);
                    append(sb, 'R', 1);
                    c1 += 2;
                }
                if (c / 2 < c2 / 2) {
                    append(sb2, 'D', 1);
                    append(sb2, 'R', 1);
                    append(sb2, 'U', 1);
                    append(sb2, 'R', 1);
                    c2 -= 2;
                }
            }

            // 2X2칸 남았을 경우
            // 가장 작은칸을 피해서 이동한다.
            if (c == c1) {
                append(sb, 'R', 1);
                append(sb, 'D', 1);
            } else {
                append(sb, 'D', 1);
                append(sb, 'R', 1);
            }

            //도착점에서 출발한 경로 뒤집기
            sb2.reverse();
            sb.append(sb2);
        }

        System.out.println(sb.toString());
    }

    // sb에 c를 num만큼 반복해서 채워준다.
    public static void append(StringBuilder sb, char c, int num) {
        for (int i = 0; i < num; i++) {
            sb.append(c);
        }//
    }
}