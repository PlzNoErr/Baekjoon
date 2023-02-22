import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int clients = Integer.parseInt(st.nextToken());
        int mud = Integer.parseInt(st.nextToken());

        int[] arr = new int[1000001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < clients; i++) {
            arr[Integer.parseInt(st.nextToken())] = 1;
        }

        int togi = 0;
        int coffee = 0;
        int record = 0;

        here:
        for (int i = 0; i <= 1000000; i++) {
            if (arr[i] == 1) {
                if (coffee == 0) {
                    System.out.println("fail");
                    return;
                } else {
                    coffee--;
                    continue;
                }
            }

            if (togi == 0) {
                togi++;
                continue;
            }

            int temp = Math.max(i + 1, record);
            int temp2 = Math.min(1000000, i + mud);

            for (int j = temp; j <= temp2; j++) {
                if (arr[j] == 1) {
                    record = j + 1;
                    coffee++;
                    togi--;
                    continue here;
                }
            }

            togi++;
            record = temp2;
        }

        System.out.println("success");
    }
}