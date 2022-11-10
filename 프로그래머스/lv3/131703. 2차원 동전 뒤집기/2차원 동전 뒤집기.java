class Solution {
    
    static int min = Integer.MAX_VALUE;
    static int[][] tar;


    public static int solution(int[][] beginning, int[][] target) {
        int answer = -1;
        tar = target;

        dfs(beginning, 0, 0);

        if (min != Integer.MAX_VALUE)
            answer = min;
        return answer;
    }

    static void dfs(int[][] map, int depth, int turn) {
        if (depth == map.length) {
            int[][] clone = new int[map.length][map[0].length];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    clone[i][j] = map[i][j];
                }
            }

            for (int k = 0; k < map[0].length; k++) {
                boolean same = true;
                for (int i = 0; i < map.length; i++) {
                    if (map[i][k] != tar[i][k]) {
                        same = false;
                        break;
                    }
                }
                if (!same) {
                    for (int i = 0; i < map.length; i++) {
                        if (clone[i][k] == 1) clone[i][k] = 0;
                        else clone[i][k] = 1;
                    }
                    turn++;
                }
            }
            if (check(clone)) {
                min = Math.min(min, turn);
            }
            return;
        }

        // 행 뒤집기
        for (int i = 0; i < map[0].length; i++) {
            if (map[depth][i] == 1) map[depth][i] = 0;
            else map[depth][i] = 1;
        }
        dfs(map, depth + 1, turn + 1);

        for (int i = 0; i < map[0].length; i++) {
            if (map[depth][i] == 1) map[depth][i] = 0;
            else map[depth][i] = 1;
        }

        // 안뒤집기
        dfs(map, depth + 1, turn);
    }//

    static boolean check(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != tar[i][j]) return false;
            }
        }
        return true;
    }//

}

