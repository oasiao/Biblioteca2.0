import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author Kim Asiao
 * @author Santiago Martinez
 * @version 1.0
 * Clase Utilities: metodo de entrada de datos.
 */

public class Utilities {
    /**
     *
     * @param enunciado introducimos el enunciado, pregunta un valor y lo guarda.
     * @return devuelve el valor introducido
     */
    public static String makeQuestion(String enunciado){
        String valor = " ";
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader (isr);
            System.out.print(enunciado+": ");
            valor = br.readLine();
        } catch (IOException ex) {
            System.out.println("Error");
        }
        return valor;
    }
}