import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\User\\IdeaProjects\\Inf_tech\\src\\input.txt"));
        String[] input = sc.nextLine().split(" ");
        int[] intInput = new int[input.length];
        int inputLength = 0;
        long A = 0;
        long B = 0;
        for (int i = 0; i < input.length; i++) {
            inputLength += input[i].length();
            intInput[i] =  Integer.parseInt(input[i]);
        }
        for (int i = 0; i < intInput.length-1; i++) {
            A += (long) intInput[i] * intInput[i+1];
            B += (long) intInput[i] * intInput[i];
        }
        float c1 = (float) A / B;
        StringBuilder res = new StringBuilder();
        int outputLength = 0;
        String t = String.format("%.2f",c1);
        outputLength += t.length();
        res.append(t).append(" ");
        res.append(intInput[0]).append(" ");
        outputLength += input[0].length();
        for (int i = 1; i < intInput.length; i++) {
            t = String.format("%.0f", intInput[i] - c1 * intInput[i - 1]);
            res.append(t).append(" ");
            outputLength += t.length();
        }
        System.out.println(res);
        System.out.println(inputLength);
        System.out.println(outputLength);
        System.out.println("k = " + (float) inputLength / outputLength);

    }
}