import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static int[][] map = new int[9][9];
	static ArrayList<int[]> list;
	static boolean find = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = s.charAt(j) - '0';
				if (map[i][j] == 0) {
					list.add(new int[] { i, j });
				}
			}
		}
		
		dfs(0, 0);

	}// main

	static void dfs(int depth, int num) {
		// 이미 정답에 도달한 가지가 있다면 남은 연산 모두 종료
		if (find) {
			return;
		}

		// 드디어 정답 하나를 발견
		if (depth == list.size()) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			find = true;
			return;
		}

		for (int i = num; i < num + 1; i++) {
			for (int k = 1; k <= 9; k++) {
				map[list.get(i)[0]][list.get(i)[1]] = k;
				if (check(list.get(i)[0], list.get(i)[1])) {
					dfs(depth + 1, num + 1);
				}
				map[list.get(i)[0]][list.get(i)[1]] = 0;
			}
		}

	}// dfs

	static boolean check(int r, int c) {
		// 가로 세로 검사
		for (int i = 0; i < 9; i++) {
			if (i != r && map[i][c] == map[r][c]) {
				return false;
			}
			if (i != c && map[r][i] == map[r][c]) {
				return false;
			}
		}
		// 3X3 안의 검사
		int Sr = (r / 3) * 3;
		int Sc = (c / 3) * 3;
		int Br = Sr + 3;
		int Bc = Sc + 3;

		for (int i = Sr; i < Br; i++) {
			for (int j = Sc; j < Bc; j++) {
				if ((i != r && j != c) && map[i][j] == map[r][c]) {
					return false;
				}
			}
		}
		return true;
	}// check
}