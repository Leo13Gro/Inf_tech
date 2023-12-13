import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Generate {

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("C:\\Users\\User\\IdeaProjects\\Inf_tech\\src\\input.txt");
        /*Random rand = new Random();*/
        for (int i = 100; i < 200; i++){
            printWriter.print((1000 * i * i / (i + 20)) + " ");
        }
        printWriter.close();
    }
}