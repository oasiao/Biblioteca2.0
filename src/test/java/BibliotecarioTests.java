import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BibliotecarioTests {
    @Test
    public void sizeLista(){
        Bibliotecario bibliotecario = new Bibliotecario();
        bibliotecario.getListaBibliotecarios().add(new Bibliotecario("nombre", "apellido1","apellido2",
                21, "type", "puestoTrabajo","43462662E","123456789"));
        Assertions.assertEquals(1,Bibliotecario.getListaBibliotecarios().size());
    }


}
