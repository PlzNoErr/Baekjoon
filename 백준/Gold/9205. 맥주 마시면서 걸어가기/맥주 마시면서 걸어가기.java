import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (tc-- > 0) {
            int convin_num = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            Queue<Location> Q = new LinkedList<>();
            Q.add(new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

            Location[] list = new Location[convin_num];
            boolean[] check = new boolean[convin_num];

            for (int i = 0; i < convin_num; i++) {
                st = new StringTokenizer(br.readLine());
                list[i] = (new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            st = new StringTokenizer(br.readLine());
            Location end = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            boolean find = false;
            while (!Q.isEmpty()) {
                Location loc = Q.poll();
                if (cal(loc, end)) {
                    sb.append("happy" + "\n");
                    find = true;
                    break;
                }
                for (int i = 0; i < list.length; i++) {
                    if (!check[i] && cal(loc, list[i])) {
                        check[i] = true;
                        Q.add(list[i]);
                    }
                }
            }
            if (!find) sb.append("sad" + "\n");
        }
        System.out.println(sb.toString());
    }//

    static boolean cal(Location a, Location b) {
        if (Math.abs(a.x - b.x) + Math.abs(a.y - b.y) <= 1000) return true;
        return false;
    }

    static class Location {
        int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}