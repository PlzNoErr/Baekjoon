import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> crane = new ArrayList<>();
        for (int i = 0; i < n; i++) crane.add(Integer.parseInt(st.nextToken()));
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> box = new ArrayList<>();
        for (int i = 0; i < m; i++) box.add(Integer.parseInt(st.nextToken()));

        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());

        if (crane.get(0) < box.get(0)) {
            System.out.println("-1");
            return;
        }

        int time = 0;
        while (!box.isEmpty()) {
            int idx = 0;
            for (int i = 0; i < crane.size(); ) {
                if (idx == box.size()) break;
                else if (crane.get(i) >= box.get(idx)) {
                    box.remove(idx);
                    i++;
                } else idx++;
            }
            time++;
        }
        System.out.println(time);

    }
}
