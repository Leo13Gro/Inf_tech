import java.util.Scanner;

public class Main {
    public static final int e = 17;
    public static final int n = 77;
    public static final int d = 53;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int c = 1;
        for (int i = 0; i < e; i++) {
            c = (c * m) % n;
        }
        System.out.println("c = " + c);
        int res = 1;
        for (int i = 0; i < d; i++) {
            res = (res * c) % n;
        }
        System.out.println("m = " + res);
    }
}