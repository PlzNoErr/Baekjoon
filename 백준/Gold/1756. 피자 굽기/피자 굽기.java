import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int D, N;
	static int arr[];
	static int dep, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[D];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < D; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < D; i++) {
			if (arr[i] > arr[i - 1]) {
				arr[i] = arr[i - 1];
			}
		}
		
		dep = D - 1;
		min = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int target = Integer.parseInt(st.nextToken());
			binary(target, 0, dep);
		}
		
		System.out.println(min+1);
	}

	static void binary(int target, int top, int low) {
		int res = -1;
		while (top <= low) {
			int mid = (top + low) / 2;
			if (arr[mid] >= target) {
				res = mid;
				top = mid + 1;
			} else {
				low = mid - 1;
			}
		}
		min = Math.min(min, res);
		dep = res - 1;
	}

}