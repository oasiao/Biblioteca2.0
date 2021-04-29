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
    @Test
    public void eliminarPersona(){
        Bibliotecario bibliotecario=new Bibliotecario();
        Biblioteca.getPersonas().add(new Bibliotecario("nombre", "apellido1","apellido2",
                21, "type", "puestoTrabajo","45697747J","123456789"));
        bibliotecario.getListaBibliotecarios().add(new Bibliotecario("nombre", "apellido1","apellido2",
                21, "type", "puestoTrabajo","45697747J","123456789"));

        System.out.println(Biblioteca.getPersonas().toString());
        System.out.println(bibliotecario.getListaBibliotecarios().toString());

        bibliotecario.eliminarPersonal();

        Assertions.assertFalse(Biblioteca.getPersonas().size()==2 && bibliotecario.getListaBibliotecarios().size()==2, "No tira!");

        System.out.println(Biblioteca.getPersonas().toString());
        System.out.println(bibliotecario.getListaBibliotecarios().toString());
    }

}
