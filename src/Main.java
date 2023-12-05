import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] AInput = sc.nextLine().split("; ");
        String[] BInput = sc.nextLine().split("; ");
        int k = sc.nextInt();
        int l = sc.nextInt();

        int[] tA;
        int[] tB;
        int res = 0;
        for (String sA : AInput) {
            for (String sB : BInput) {
                tA = Arrays.stream(sA.split(" ")).mapToInt(Integer::parseInt).toArray();
                tB = Arrays.stream(sB.split(" ")).mapToInt(Integer::parseInt).toArray();
                if (k == tA[0] && l == tB[1] && tA[1] == tB[0])
                    res += tA[2] * tB[2];
            }
        }
        System.out.println(res);
        /*int[][] A = new int[1000][1000];
        int[][] B = new int[1000][1000];
        String[] t;
        for (String s : AInput) {
            t = s.split(" ");
            A[Integer.parseInt(t[0])][Integer.parseInt(t[1])] = Integer.parseInt(t[2]);
        }
        for (String s : BInput) {
            t = s.split(" ");
            B[Integer.parseInt(t[0])][Integer.parseInt(t[1])] = Integer.parseInt(t[2]);
        }
        int res = 0;
        for (int i = 0; i < 1000; i++) {
            res += A[k][i] * B[i][l];
        }
        System.out.println(res);*/
    }
}