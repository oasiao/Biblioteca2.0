import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BibliotecaTests {
    Biblioteca biblioteca = new Biblioteca();
    Libro libro = new Libro();
    @Test
    public void mostrarLibrosDisp(){
        Biblioteca.getLibros().add(new Libro("123456789","EL QUIJOTE","ANONIMO","DEBOLSILLO",1));
        biblioteca.mostrarLibrosDisponibles();
        Assertions.assertEquals(1,Biblioteca.getLibros().size());
    }

}
