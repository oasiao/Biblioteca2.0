import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BibliotecaTests {
    Biblioteca biblioteca = new Biblioteca();
    Libro libro = new Libro();

    @BeforeAll
    public static void crearLibros(){
        Biblioteca.getLibros().add(new Libro("1","El Quijote","Anonimo","DeBolsillo",4,4));
        Biblioteca.getLibros().add(new Libro("2","El Quijote 2.0","Anonimo","DeBolsillo",4,0));
    }

    @Test
    public void mostrarListaLibros(){
        Assertions.assertEquals("-------------------------------------------\nISBN: " + "1" + "\nTítulo: " + "El Quijote" + "\nAutor: " + "Anonimo" + "\nEditorial: " + "DeBolsillo"
                + "\nNúmero de copias: " + "4" + "\nNúmero de copias disponibles: " + "4" + "\n-------------------------------------------\n",biblioteca.mostrarLibros());
    }

    @Test
    public void mostrarListaLibrosDisponibles(){
        Assertions.assertEquals("-------------------------------------------\nISBN: " + "1" + "\nTítulo: " + "El Quijote" + "\nAutor: " + "Anonimo" + "\nEditorial: " + "DeBolsillo"
                + "\nNúmero de copias: " + "4" + "\nNúmero de copias disponibles: " + "4" + "\n-------------------------------------------\n",biblioteca.mostrarLibrosDisponibles());
    }
}
