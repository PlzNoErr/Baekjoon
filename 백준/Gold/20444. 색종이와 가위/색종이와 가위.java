import java.util.*;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long k = sc.nextLong();
 
        long left = 0;
        long right = n / 2;
        while(left <= right) {
            long row = (left + right) / 2;
            long col = n - row;
            long total = (row + 1) * (col + 1);
            if(total == k) {
                System.out.println("YES");
                return;
            } 
            else if(total > k) right = row - 1;
            else left = row + 1;
        }
        System.out.println("NO");
    }//
}
