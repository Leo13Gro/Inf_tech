import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static double step = 0.5;
    private static double toInput(String s){
        switch (s){
            case "темные", "да": return 1.;
            case "светлые", "нет": return 0.;
        }
        return 0;
    }
    public static void print(double[][] W){
        for (double[] row : W) {
            for (double el : row) {
                System.out.print(el + ", ");
            }
            System.out.println();
        }
    }
    public static void random_fill(double[][] W){
        Random rand = new Random();
        for (int i = 0; i < W.length; i++) {
            for (int j = 0; j < W[i].length; j++) {
                W[i][j] = rand.nextDouble(1.);
            }
        }
    }
    public static double f(double x){
        return 1./(1 + Math.exp(-x));
    }
    private static void calc_out(double[] input, double[][] W, double[] output) {
        for (int j = 0; j < W[0].length; j++){
            for (int i = 0; i < W.length; i++) {
                output[j] += W[i][j] * input[i];
            }
            output[j] = f(output[j]);
        }
    }
    public static double[] step(double[] input, double[][] W){
        double[] output = new double[W[0].length + 1];
        calc_out(input, W, output);
        output[W[0].length] = 1;
        return output;
    }
    public static double[] f_step(double[] input, double[][] W){
        double[] output = new double[W[0].length];
        calc_out(input, W, output);
        return output;
    }
    public static double back_propagation(double[][] W, double[][] V, double[][] input, double[] answers){
        double[] outputL = new double[100];
        double err = 0;
        double[][] outputJ = new double[100][10];
        for (int i = 0; i < 100; i++){
            outputJ[i] = step(input[i], W);
            outputL[i] = f_step(outputJ[i], V)[0];
            err += 0.5 * (outputL[i] - answers[i]) * (outputL[i] - answers[i]);
        }
        /*System.out.println(outputL[0]);*/
        double[] deltaLast = new double[10];
        double[][] deltaIJ = new double[4][10];
        for (int n = 0; n < 100; n++) {
            for (int j = 0; j < 10; j++) {
                deltaLast[j] += (outputL[n] - answers[n]) * (1 - outputL[n]) * outputL[n] * outputJ[n][j];
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 9; j++) {
                    deltaIJ[i][j] += (outputL[n] - answers[n]) * (1 - outputL[n]) * outputL[n] * V[j][0] * outputJ[n][j] * (1 - outputJ[n][j]) * input[n][i];
                }
            }
        }
        for (int j = 0; j < 9; j++) {
            V[j][0] -= step * deltaLast[j];
            for (int i = 0; i < 4; i++) {
                W[i][j] -= step * deltaIJ[i][j];
            }
        }
        V[9][0] -= step * deltaLast[9];
        return err;
    }
    public static void net_test(double[][] W, double[][] V) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\User\\IdeaProjects\\Inf_tech\\Test.txt"));
        double[][] input = new double[10][4];
        for (int i = 0; i < 10; i++){
            input[i][0] = toInput(sc.next());
            input[i][1] = (sc.nextDouble() - 19) / 31;
            input[i][2] = (sc.nextDouble() - 2) / 3;
            input[i][3] = (sc.nextDouble() - 21) / 277;
            sc.nextLine();
            System.out.println(f_step(step(input[i], W), V)[0]);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\User\\IdeaProjects\\Inf_tech\\Learn.txt"));
        double[][] W = new double[4][9];
        random_fill(W);
        double[][] V = new double[10][1];
        random_fill(V);
        print(W);
        print(V);
        /*int ageMax = 50; int ageMin = 19;
        double markMax = 4.9; double markMin = 2.;
        int salaryMax = 298; int salaryMin = 21;*/
        double[][] input = new double[100][4];
        double[] answers = new double[100];
        for (int i = 0; i < 100; i++){
            input[i][0] = toInput(sc.next());
            input[i][1] = (sc.nextDouble() - 19) / 31;
            input[i][2] = (sc.nextDouble() - 2) / 3;
            input[i][3] = (sc.nextDouble() - 21) / 277;
            answers[i] = toInput(sc.next());
            sc.nextLine();
        }
        while(true){
            if (back_propagation(W, V, input, answers) < 1)
                break;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(f_step(step(input[i], W), V)));
        }
        net_test(W, V);
    }
}