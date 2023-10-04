public class Main {
    static final double eps = 1e-3;
    static final double par = 3;
    static double func(double x){
        return Math.sin(x) * Math.exp(-par *x) / Math.pow(x,3./2);
    }
    static double taylorFunc(double x){
        return (1 - x * x / 6 + Math.pow(x, 4) / 120) * (1 - par * x * (1 - par * x / 2)) / Math.sqrt(x);
    }
    static double intTaylorFunc(double a){
        return Math.sqrt(a) * (26 * Math.pow(a, 4) / 105 - 11 * a * a / 15 + 2);
    }
    static double intSimpson(double a, double b, double step){
        int n = (int) ((b - a) / step / 2);
        double[] f = new double[n];
        double t = a;
        for (int i = 0; i < n; i++) {
            f[i] = func(t);
            t += step;
        }
        double s = 0;
        for (int i = 0; i < n-2; i++) {
            s += f[i] + 4 * f[i+1] + f[i+2];
        }
        return  s * step / 6;
    }
    public static void main(String[] args){
        double a = 0.2;
        double b = 1.4;
        double c = 1000;
        double step = 1e-6;
        double rStep = 1e-2;
        double t = intTaylorFunc(a);
        while (true){
           if (Math.abs(t - intTaylorFunc(a/2)) < eps)
               break;
           a /= 2;
           t = intTaylorFunc(a);
//           System.out.println(t + ", " + a);
        }
        t = intSimpson(a, b, step);
        while (true){
            if (Math.abs(t - intSimpson(a, b * 2, step)) < eps)
                break;
            b *= 2;
            t = intSimpson(a, b, step);
//            System.out.println(t + ", " + b);
        }
        t = intSimpson(b, c, rStep);
        while (true){
            if (Math.abs(t - intSimpson(b, c * 2, rStep)) < eps)
                break;
            c *= 2;
            t = intSimpson(b, c, rStep);
//            System.out.println(t + ", " + b);
        }
        double result = intTaylorFunc(a) + intSimpson(a, b, step) + intSimpson(b, c, rStep);
        System.out.println("my result   = " + result);
        System.out.println("true result = 1.009762650");
    }
}