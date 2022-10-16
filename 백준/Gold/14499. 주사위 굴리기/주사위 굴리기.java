import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };
	static Dice dice = new Dice();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int now_r = Integer.parseInt(st.nextToken());
		int now_c = Integer.parseInt(st.nextToken());
		int c_length = Integer.parseInt(st.nextToken());
		int[][] map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		String command = br.readLine().replace(" ", "");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < c_length; i++) {
			int order = command.charAt(i) - '0';
			int R = now_r + dr[order];
			int C = now_c + dc[order];
			if (0 <= R && R < H && 0 <= C && C < W) {
				now_r = R;
				now_c = C;
				roll_dice(order);
				if (dice.side1 != 0 && map[R][C] == 0) {
					map[R][C] = dice.side1;
				} else if (map[R][C] != 0) {
					dice.side1 = map[R][C];
					map[R][C] = 0;
				}
				sb.append(dice.side6 + "\n");
			}
		}
		System.out.println(sb.toString());
	}// main

	static void roll_dice(int direction) {
		int temp1 = dice.side1;
		int temp2 = dice.side2;
		int temp3 = dice.side3;
		int temp4 = dice.side4;
		int temp5 = dice.side5;
		int temp6 = dice.side6;
		// 동
		if (direction == 1) {
			dice.side1 = temp3;
			dice.side3 = temp6;
			dice.side4 = temp1;
			dice.side6 = temp4;
		}
		// 서
		if (direction == 2) {
			dice.side1 = temp4;
			dice.side3 = temp1;
			dice.side4 = temp6;
			dice.side6 = temp3;
		}
		// 북
		if (direction == 3) {
			dice.side1 = temp2;
			dice.side2 = temp6;
			dice.side5 = temp1;
			dice.side6 = temp5;
		}
		// 남
		if (direction == 4) {
			dice.side1 = temp5;
			dice.side2 = temp1;
			dice.side5 = temp6;
			dice.side6 = temp2;
		}
	}//

	static class Dice {
		int side1, side2, side3, side4, side5, side6;
	}

}