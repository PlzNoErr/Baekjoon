import java.util.Scanner;

public class Main {

    static int ff, fs, sf, ss;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ff = sc.nextInt();
        fs = sc.nextInt();
        sf = sc.nextInt();
        ss = sc.nextInt();
        System.out.println(cal());

    }//

    static int cal() {
        if (ff == 0 && fs == 0) return ss + Math.min(1, sf);
        if (fs == 0) return ff;
        int temp;
        if (fs > sf) temp = 2 * sf + 1;
        else temp = 2 * fs;
        return ff + ss + temp;
    }
}