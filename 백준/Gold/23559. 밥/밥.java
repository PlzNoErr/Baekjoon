import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());

        int mat = 0;
        PriorityQueue<Taste> PQ = new PriorityQueue<>((Taste a, Taste b) -> a.taste1 - a.taste2 > b.taste1 - b.taste2 ? -1 : 1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            mat += b;
            PQ.add(new Taste(a, b));
        }

        int money = 1000 * PQ.size();
        while (!PQ.isEmpty()) {
            Taste p = PQ.poll();

            if (p.taste1 > p.taste2) {
                money += 4000;
                if (money > limit) break;
                mat = mat - p.taste2 + p.taste1;
            }
        }
        System.out.println(mat);
    }

    static class Taste {
        int taste1, taste2;

        Taste(int taste1, int taste2) {
            this.taste1 = taste1;
            this.taste2 = taste2;
        }
    }
}
