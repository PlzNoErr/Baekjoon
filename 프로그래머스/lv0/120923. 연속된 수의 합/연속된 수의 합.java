class Solution {
      public int[] solution(int num, int total) {
        int start = 0;
        int ans = 0;
        int[] answer = new int[num];
        while (true) {
            ans = 0;
            int tc = 0;
            for (int i = start; 0 <= tc && tc < num; tc++) {
                ans += i;
                i++;
            }
            if (ans == total) {
                for (int i = 0; i < num; i++) {
                    answer[i] = start;
                    start++;
                }
                break;
            }
            if (ans > total)
                start--;
            else
                start++;
        }
        return answer;
    }
}