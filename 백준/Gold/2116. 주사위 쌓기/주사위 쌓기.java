import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] dices;
	static boolean[] isTop = new boolean[6];
	static int maxNum = 0;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		dices = new int[N][6];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				dices[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[] visited = new boolean[7];
		for (int i = 0; i < 6; i++) {
			int pair = findPair(i);
			visited[pair] = true;
			if (!visited[i]) {
				doStack(dices[0][i], dices[0][pair], 0, 0);
				doStack(dices[0][pair], dices[0][i], 0, 0);
			}
		}
		System.out.println(maxNum);

	}

	static void doStack(int top, int bottom, int cnt, int total) {
		int sideMax = 0;
		if (cnt == N - 1) {
			for (int i = 0; i < 6; i++) {
				if (dices[cnt][i] != top && dices[cnt][i] != bottom) {
					sideMax = Math.max(sideMax, dices[cnt][i]);
				}
			}
			total += sideMax;
			maxNum = Math.max(total, maxNum);
			return;
		}

		int topIdx = -1;
		for (int i = 0; i < 6; i++) {
			if (dices[cnt][i] != top && dices[cnt][i] != bottom) 
				sideMax = Math.max(sideMax, dices[cnt][i]);

			if (dices[cnt+1][i] == top) topIdx = i;
		}
		doStack(dices[cnt + 1][findPair(topIdx)], top, cnt + 1, total + sideMax);
	}
    
    static int findPair(int i) {
		int pair = -1;
		switch (i) {
			case 0: pair = 5; break;
			case 5: pair = 0; break;
			case 1: pair = 3; break;
			case 3: pair = 1; break;		
			case 2: pair = 4; break;
			case 4:	pair = 2; break;
		}
		return pair;
	}
}