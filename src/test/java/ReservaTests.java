import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReservaTests {

    Reserva reserva = new Reserva();




    @Test
    public void reserva(){
        Libro libro = new Libro("isbn", "titulo", "autor", "editorial", 1,1);
        Usuario usuario = new Usuario("nombre", "apellido1", "apellido2", 21,"type",603200924,
                "direccion", 07015, "correoElectronico");

        String show="------------------------------------------------\nReserva:\n" +
                "Reservado por\n: " + usuario + "\n" +
                "Fecha y hora:" + reserva.getFechaYHora() + "\n------------------------------------------------\n";
        String expectedShow=reserva.mostrarReservas();
        Assertions.assertEquals(expectedShow,show);


    }

}
