
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LibroTests {

    Libro libro = new Libro();
    Biblioteca biblioteca = new Biblioteca();

    @BeforeAll
    public static void crearLibro(){
        Biblioteca.getLibros().add(new Libro("1","El Quijote","Anonimo","DeBolsillo",4,4));
        Biblioteca.getLibros().add(new Libro("2","El Quijote 2.0","Anonimo","DeBolsillo",4,0));
    }

    @Test
    public void isbnLibro(){
        Assertions.assertEquals("1",Biblioteca.getLibros().get(0).getIsbn());
    }

    @Test
    public void tituloLibro(){
        Assertions.assertEquals("El Quijote",Biblioteca.getLibros().get(0).getTitulo());
    }

    @Test
    public void autorLibro(){
        Assertions.assertEquals("Anonimo",Biblioteca.getLibros().get(0).getAutor());
    }

    @Test
    public void editorial(){
        Assertions.assertEquals("DeBolsillo",Biblioteca.getLibros().get(0).getEditorial());
    }

    @Test
    public void numCopias(){
        Assertions.assertEquals(4,Biblioteca.getLibros().get(0).getNumCopias());
    }

    @Test
    public void numCopiasDisponibles(){
        Assertions.assertEquals(4,Biblioteca.getLibros().get(0).getNumCopiasDisponibles());
    }

    @Test
    public void numCopiasDisponiblesConReserva(){
        Assertions.assertEquals(0,Biblioteca.getLibros().get(1).getNumCopiasDisponibles());
    }
}
