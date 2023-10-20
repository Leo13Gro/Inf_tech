import java.util.Arrays;

public class Main {
    public static final double PI = 3.1415926535;
    public static int n = 100000;
    public static double d = 2 * PI / (n-1);
    public static double[] x = new double[n+2];
    public static double a(double x){
        return Math.sin(x);
//        return 2;
    }
    public static double b(double x){
        return Math.sin(x) + 3 * Math.sin(3 * x);
//        return Math.sin(x);
    }
    public static double f_i(double f_i_2, double f_i_1, double a_i_1, double b_i_1){
        return 2 * f_i_1 - a_i_1 * f_i_1 * d * d - f_i_2 + b_i_1 * d * d;
    }
    public static double f(double el, double[] res){
        int n = Arrays.binarySearch(x, el);
        if (n >= 0){
//            System.out.println(x[n]);
            return res[n];
        }
        else {
            int ind = - n - 1;
//            System.out.println(x[1-n]);
            return res[ind-1] + (res[ind] - res[ind-1]) / (x[ind] - x[ind-1]) * (el - x[ind-1]);
        }
    }
    public static void main(String[] args){
        double[] a = new double[n+2];
        double[] b = new double[n+2];
        for (int i = 0; i < n+2; i++){
            x[i] = d * (i-1);
            a[i] = a(x[i]);
            b[i] = b(x[i]);
        }
        double[] f_1 = new double[n+2];
        double[] f_2 = new double[n+2];
        double[] f_part = new double[n+2];
        f_1[0] = 0; f_1[1] = d;
        f_2[0] = d; f_2[1] = 0;
        f_part[0] = d; f_part[1] = d;
        for (int i = 2; i < n+2; i++){
            f_1[i] = f_i(f_1[i-2], f_1[i-1], a[i-1], 0);
            f_2[i] = f_i(f_2[i-2], f_2[i-1], a[i-1], 0);
            f_part[i] = f_i(f_part[i-2], f_part[i-1], a[i-1], b[i-1]);
        }
        double k = f_1[1] - f_1[n];
        double l = f_2[1] - f_2[n];
        double m = f_part[n] - f_part[1];
        double c = f_1[2] - f_1[n+1];
        double e = f_2[2] - f_2[n+1];
        double f = f_part[n+1] - f_part[2];
        double c2 = (f - c*m/k) / (e - c*l/k);
        double c1 = (m - l*c2) / k;
//        System.out.println("k = " + k);
//        System.out.println("l = " + l);
//        System.out.println("m = " + m);
//        System.out.println("c = " + c);
//        System.out.println("e = " + e);
//        System.out.println("f = " + f);
//        System.out.println("f_1[1] = " + f_1[1]);
//        System.out.println("f_1[n] = " + f_1[n]);
//        System.out.println("f_1[2] = " + f_1[2]);
//        System.out.println("f_1[n+1] = " + f_1[n+1]);
//        System.out.println("f_2[1] = " + f_2[1]);
//        System.out.println("f_2[n] = " + f_2[n]);
//        System.out.println("f_2[2] = " + f_2[2]);
//        System.out.println("f_2[n+1] = " + f_2[n+1]);
//        System.out.println(e - c*l/k);
        double[] f_res = new double[n+2];
        for (int i = 0; i < n + 2; i++) {
            f_res[i] = c1 * f_1[i] + c2 * f_2[i] + f_part[i];
        }
//        System.out.println(f(0, f_res));
//        System.out.println(f(PI/6, f_res) - f(0, f_res));
//        System.out.println(f(PI/3, f_res) - f(0, f_res));
//        System.out.println(f(PI/2, f_res) - f(0, f_res));
//        System.out.println(f(PI, f_res) - f(0, f_res));
//        System.out.println(f(3*PI/2, f_res) - f(0, f_res));
//        System.out.println(f_res[1] + " " + f_res[n]);
//        System.out.println(f_res[2] + " " + f_res[n+1]);
        System.out.println("f(1) = " + f(1, f_res));
        System.out.println("f(2) = " + f(2, f_res));
    }
}