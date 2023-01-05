import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        Robot[][] map = new Robot[a][b];
        HashMap<Integer, int[]> hashMap = new HashMap<Integer, int[]>();
        st = new StringTokenizer(br.readLine());
        int robot_mounts = Integer.parseInt(st.nextToken());
        int orders = Integer.parseInt(st.nextToken());
        for (int i = 0; i < robot_mounts; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            if (direction.equals("N")) map[a - y][x - 1] = new Robot(a - y, x - 1, 0, i + 1);
            if (direction.equals("E")) map[a - y][x - 1] = new Robot(a - y, x - 1, 1, i + 1);
            if (direction.equals("S")) map[a - y][x - 1] = new Robot(a - y, x - 1, 2, i + 1);
            if (direction.equals("W")) map[a - y][x - 1] = new Robot(a - y, x - 1, 3, i + 1);
            hashMap.put(i + 1, new int[]{a - y, x - 1});
        }
        for (int i = 0; i < orders; i++) {
            st = new StringTokenizer(br.readLine());
            int rn = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            int times = Integer.parseInt(st.nextToken());
            Robot robot = map[hashMap.get(rn)[0]][hashMap.get(rn)[1]];
            map[hashMap.get(rn)[0]][hashMap.get(rn)[1]] = null;
            // 로봇이 움직일 방향을 정해준다.
            int dir = robot.dir;

            for (int k = 1; k <= times; k++) {
                if (direction.equals("L")) {
                    dir = robot.dir - 1;
                    if (dir < 0) dir += 4;
                    else dir %= 4;
                    robot.dir = dir;
                    continue;
                } else if (direction.equals("R")) {
                    dir = robot.dir + 1;
                    if (dir < 0) dir += 4;
                    else dir %= 4;
                    robot.dir = dir;
                    continue;
                }

                int R = robot.r + dr[dir];
                int C = robot.c + dc[dir];
                if (R < 0 || R >= a || C < 0 || C >= b) {
                    System.out.println("Robot " + robot.num + " crashes into the wall");
                    return;
                }
                if (map[R][C] != null) {
                    System.out.println("Robot " + robot.num + " crashes into robot " + map[R][C].num);
                    return;
                }

                robot.r = R;
                robot.c = C;
            }//

            hashMap.put(robot.num, new int[]{robot.r, robot.c});
            map[robot.r][robot.c] = new Robot(robot.r, robot.c, dir, robot.num);
        }
        System.out.println("OK");
    }//

    static class Robot {
        int r, c, dir, num;

        public Robot(int r, int c, int dir, int num) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.num = num;
        }
    }
}
