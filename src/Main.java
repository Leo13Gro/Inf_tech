import java.util.*;

public class Main {
    public static int diff(char c1, char c2){
        if (c1 == c2)
            return 0;
        return 1;
    }
    public static int min(int a, int b, int c){
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] str_1 = sc.nextLine().toCharArray();
        char[] str_2 = sc.nextLine().toCharArray();
        int n = str_1.length;
        int m = str_2.length;
        int [][] D = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            D[0][j] = j;
        }
        int t;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                t = diff(str_1[i-1], str_2[j-1]);
                D[i][j] = min(D[i-1][j] + 1, D[i][j-1] + 1, D[i-1][j-1] + t);
            }
        }
        System.out.println(D[n][m]);
    }
}