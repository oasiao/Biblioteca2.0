import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilities {
    public static String makeQuestion(String enunciado){
        String valor = " ";
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader (isr);
            System.out.print(enunciado+"  : ");
            valor = br.readLine();
        } catch (IOException ex) {
            System.out.println("Error");
        }
        return valor;
    }
}
