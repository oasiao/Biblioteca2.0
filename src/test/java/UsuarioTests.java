import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UsuarioTests {

    Usuario usuario = new Usuario();
    @BeforeAll
    public static void crearUsuario()
    {
        Biblioteca.getPersonas().add(new Usuario("root", "root", "root", 0, "USUARIO",123456789,
                "direccion",22222,"rootroot@correo.com"));

        Usuario.getListaUsuarios().add(new Usuario("root", "root", "root", 0, "USUARIO",123456789,
                "direccion",22222,"rootroot@correo.com"));
    }

    @Test
    public void mostarListaPersonas(){

        Assertions.assertEquals("------------------------\n"
                + "Nombre: " + "root"
                + "\nPrimer apellido: " + "root"
                + "\nSegundo apellido: " + "root"
                + "\nEdad: " + "0"
                + "\nDirección: " + "direccion"
                + "\nCódigo Postal: " + "22222"
                + "\nTeléfono: " + "123456789"
                + "\nCorreo Electrónico: " + "rootroot@correo.com"
                + "\nTipo: " + "USUARIO"
                + "\n-------------------------\n\n",usuario.mostrarUsuarios());
    }
}
