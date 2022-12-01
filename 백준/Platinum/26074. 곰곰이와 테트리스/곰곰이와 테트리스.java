import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] Arr = new int[8];
        for (int i = 0; i < 8; i++) {
            Arr[i] = sc.nextInt();
        }

        if ((n == 1 && m == 2) || (n == 2 && m == 1))
            System.out.println("ChongChong");
        else
            System.out.println("GomGom");
        
    }
}