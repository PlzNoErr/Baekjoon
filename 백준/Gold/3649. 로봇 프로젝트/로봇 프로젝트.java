import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = null;

        here:
        while ((s = br.readLine()) != null) {
            int size = Integer.parseInt(s) * 10000000;
            int n = Integer.parseInt(br.readLine());
            int[] list = new int[n];

            for (int i = 0; i < n; i++) {
                list[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(list);

            int left = 0;
            int right = n - 1;

            while (left < right) {
                int sum = list[left] + list[right];

                if (sum == size) {
                    sb.append("yes " + list[left] + " " + list[right] + "\n");
                    s = null;
                    continue here;
                } else if (sum > size) {
                    right--;
                } else {
                    left++;
                }
            }

            sb.append("danger" + "\n");
            s = null;
        }
        System.out.println(sb.toString());
    }
}