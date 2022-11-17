import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int min = Integer.MAX_VALUE;
	static int[][] bbq;
	static int[][] house;
	static int[][] map;
	static int[][] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		check = new int[m][2];

		int house_num = 0;
		int bbq_num = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					house_num++;
				if (map[i][j] == 2) 
					bbq_num++;
			}
		}
		
		house = new int[house_num][2];
		bbq = new int[bbq_num][2];
		
		house_num = 0;
		bbq_num = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1) {
					house[house_num][0] = i;
					house[house_num][1] = j;
					house_num++;
				}
				if (map[i][j] == 2) {
					bbq[bbq_num][0] = i;
					bbq[bbq_num][1] = j;
					bbq_num++;
				}
			}
		}
		
		dfs(0, -1);
		System.out.println(min);

	}// main

	static void dfs(int depth, int start) {
		if (depth == m) {
			int sum = 0;
			for (int i = 0; i < house.length; i++) {
				int min_check = Integer.MAX_VALUE;
				for (int k = 0; k < m; k++) {
					min_check = Math.min(min_check,
							Math.abs(house[i][0] - check[k][0]) + Math.abs(house[i][1] - check[k][1]));
				}
				sum += min_check;
			}
			min = Math.min(min, sum);
			return;
		} // depth = m

		for (int i = start + 1; i < bbq.length; i++) {
			if (map[bbq[i][0]][bbq[i][1]] == 2) {
				map[bbq[i][0]][bbq[i][1]] = 3;
				check[depth][0] = bbq[i][0];
				check[depth][1] = bbq[i][1];
				dfs(depth+1, i);
				map[bbq[i][0]][bbq[i][1]] = 2;
			}
		}
	}// dfs

}