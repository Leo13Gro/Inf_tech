import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    static PrintWriter printWriter;
    public static final double dt = 1e-2;
    public static final int n = 2000;
    public static double f1(double x, double y){
        return (1 - y) * x;
    }
    public static double f2(double x, double y){
        return (-1 + 2 * x) * y;
    }
    public static void writeGraph(int k) throws FileNotFoundException {
        double[] x = new double[n];
        double[] y = new double[n];
        double t = 0;
        x[0] = (4 + k) / 10.;
        y[0] = 1;
        for (int i = 1; i < n; i++) {
            x[i] = x[i-1] + f1(x[i-1], y[i-1]) * dt;
            y[i] = y[i-1] + f2(x[i-1], y[i-1]) * dt;
            t = t + dt;
        }
        printWriter.print("x" + k + " = [");
        for (int i = 0; i < n - 1; i++) {
            printWriter.print(x[i] + ",");
        }
        printWriter.print(x[n-1] + "]");
        printWriter.print("\ny" + k + " = [");
        for (int i = 0; i < n - 1; i++) {
            printWriter.print(y[i] + ",");
        }
        printWriter.print(y[n-1] + "]");
        printWriter.println("\n");
    }
    public static void main(String[] args) throws FileNotFoundException {
        printWriter = new PrintWriter("C:\\Users\\User\\IdeaProjects\\Inf_tech\\Graph.txt");
        for (int i = 1; i < 11; i++) {
            writeGraph(i);
        }
        printWriter.close();
    }
}