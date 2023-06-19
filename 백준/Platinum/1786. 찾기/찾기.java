import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String pattern = sc.nextLine();

        Queue<Integer> indices = findAllPatterns(text, pattern);
        if (!indices.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(indices.size() + "\n");
            while (!indices.isEmpty()) sb.append(indices.poll() + 1 + " ");
            System.out.println(sb.toString());
            return;
        }
        System.out.println(0);

    }

    public static int[] buildPatternTable(String pattern) {
        int[] table = new int[pattern.length()];
        int i = 0, j = 1;
        while (j < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[j] = i + 1;
                i++;
                j++;
            } else {
                if (i != 0) i = table[i - 1];
                else {
                    table[j] = 0;
                    j++;
                }
            }
        }
        return table;
    }//

    public static Queue<Integer> findAllPatterns(String text, String pattern) {
        Queue<Integer> indices = new LinkedList<>();
        int[] patternTable = buildPatternTable(pattern);
        int i = 0, j = 0;

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    indices.add(i - j);
                    j = patternTable[j - 1];
                }
            } else {
                if (j != 0) j = patternTable[j - 1];
                else i++;
            }
        }
        return indices;
    }//
}
