import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static long p, q;
    static HashMap<Long, Long> map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        p = sc.nextLong();
        q = sc.nextLong();
        map = new HashMap<>();
        System.out.println(func(n));
    }

    static long func(long k) {
        if (k == 0) return 1;
        if (map.containsKey(k)) return map.get(k);
        long ans = func(k / p) + func(k / q);
        map.put(k, ans);
        return ans;
    }
}