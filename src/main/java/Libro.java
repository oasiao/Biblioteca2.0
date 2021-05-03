import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * @author Kim Asiao
 * @author Santiago Martinez
 * @version 1.0
 * Clase Libro contiene los atributos principales del libro y metodos de control de estos.
 */


public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private int numCopias;
    private int numCopiasDisponibles;
    private static int numLibros;

    /**
     *
     * @return devuelve el numero de libros que hay registrados.
     */
    public static int getNumLibros() {
        return numLibros;
    }

    /**
     *
     * @param numLibros introduces el numero de libros.
     */
    public static void setNumLibros(int numLibros) {
        Libro.numLibros = numLibros;
    }

    /**
     *
     * @return devuelve el isbn.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     *
     * @param isbn establece isbn.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    /**
     *
     * @return devuelve el titulo del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * @param titulo establece titulo del libro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     *
     * @return devuelve el autor del libro.
     */

    public String getAutor() {
        return autor;
    }

    /**
     *
     * @param autor establece el autor del libro.
     */

    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     *
     * @return devuelve la editorial.
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     *
     * @param editorial establece editorial.
     */

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     *
     * @return devuelve el numero de copias.
     */

    public int getNumCopias() {
        return numCopias;
    }

    /**
     *
     * @param numCopias establece el numero de copias.
     *
     */

    public void setNumCopias(int numCopias) {
        /*añadir un control*/
        this.numCopias = numCopias;
    }

    /**
     *
     * @return devuelve el numero de copias disponibles.
     */

    public int getNumCopiasDisponibles() {
        return numCopiasDisponibles;
    }

    /**
     *
     * @param numCopias establece numero de copias disponibles.
     */

    public void setNumCopiasDisponibles(int numCopias) {
        this.numCopiasDisponibles = numCopias;
    }

    /**
     * Constructor vacio
     */
    public Libro() {
    }

    /**
     * Pasas por parametros los atributos mencionados anteriormente.
     * @param isbn
     * @param titulo
     * @param autor
     * @param editorial
     * @param numCopias
     */
    public Libro(String isbn, String titulo, String autor, String editorial, int numCopias, int numCopiasDisponibles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.numCopias = numCopias;
        this.numCopiasDisponibles=numCopiasDisponibles;
    }

    /**
     *
     * @return devuelve el formato del objeto Libro.
     */
    @Override
    public String toString() {
        return "-------------------------------------------\nISBN: " + getIsbn() + "\nTítulo: " + getTitulo() + "\nAutor: " + getAutor() + "\nEditorial: " + getEditorial()
                + "\nNúmero de copias: " + getNumCopias() + "\nNúmero de copias disponibles: " + getNumCopiasDisponibles() + "\n-------------------------------------------";
    }

    /**
     * Add libro: Solicitara los datos para crear un libro y anyadira dicho libro a una lista de libros
     */
    public void addLibro() {
        //INSTANCIAMOS

        Biblioteca biblioteca = new Biblioteca();
        Utilities utilities = new Utilities();
        boolean libroRepetido = false;

        //SOLICITAMOS LOS DATOS DEL LIBRO
        //isbn
        String isbn = utilities.makeQuestion("Introduce el isbn del libro");
        for (int i = 0; i < Biblioteca.getLibros().size(); i++) {
            if (Biblioteca.getLibros().get(i).getIsbn().equals(isbn)) {
                System.out.println("Este libro ya existe.");
                libroRepetido = true;
                String answer = utilities.makeQuestion("¿Quieres añadir libros disponibles a este libro? Sí (1), No (0)");
                if (answer.equals("1")) {
                    int numCopiasDisponibles = parseInt(utilities.makeQuestion("Cuántas copias quieres añadir?"));
                    Biblioteca.getLibros().get(i).setNumCopiasDisponibles(Biblioteca.getLibros().get(i).getNumCopiasDisponibles() + numCopiasDisponibles);
                    Biblioteca.getLibros().get(i).setNumCopias(Biblioteca.getLibros().get(i).getNumCopias() + numCopiasDisponibles);
                    System.out.println("\n -------------------------Copias añadidas-------------------------\n");
                } else {
                    break;
                }
            }
        }
        if (libroRepetido == false) {
            //titulo
            String title = utilities.makeQuestion("Introduce el titulo del libro: ");

            //Autor
            String author = utilities.makeQuestion("Introduce el autor del libro: ");

            //editorial
            String editorial = utilities.makeQuestion("Introduce la editorial del libro: ");

            //Número de copias del libro
            /*Debe haber un control en el setter de manera que el número de copias inicial nunca debe ser menos de 1.*/
            int numCopias = parseInt(utilities.makeQuestion("Introduce el número de copias del libro: "));
            if (numCopias == 0) {
                numCopias = 1;
            }
            int numCopiasDisponibles=numCopias;
            //AÑADIMOS EL LIBRO
            Libro libro = new Libro(isbn, title, author, editorial, numCopias,numCopiasDisponibles); //libro formato objeto
            biblioteca.getLibros().add(libro); //añadimos el libro en la lista
            setNumLibros(getNumLibros() + 1); //sumamos uno a la cantidad de libros que hay

            //MENSAJE DE CONFIRMACIÓN
            System.out.println("¡El libro se ha introducido con éxito!");
        }
    }

    /**
     * Eliminar libro: Solicitara al usuario un ISBN, lo buscara y lo eliminara de la lista.
     *     No se puede eliminar un libro que tiene reservas.
     */
    public void eliminarLibro() {
        //INSTANCIAMOS
        Biblioteca biblioteca = new Biblioteca();
        Utilities utilities = new Utilities();

        //NECESITO UN BOOLEANO PARA RECORRER LAS 2 LISTAS
        boolean reservado = false;

        //PEDIMOS ISBN
        String isbn = utilities.makeQuestion("Introduce el isbn del libro que quieres eliminar: ");

        //RECORREMOS LA LISTA DE LIBROS
        for (int i = 0; i < biblioteca.getLibrosReservados().size(); i++) {

            //Si el libro coincide con alguna reserva, entonces no lo eliminamos
            if (isbn.equals(biblioteca.getLibrosReservados().get(i).getIsbn())) {
                reservado = true;
            }
        }

        for (int j = 0; j < Biblioteca.getLibros().size(); j++) {
            if (isbn.equals(biblioteca.getLibros().get(j).getIsbn())) {
                if (reservado == true) {
                    System.out.println("No podemos eliminar el libro porque un usuario lo tiene reservado.");
                } else {
                    biblioteca.getLibros().remove(j);
                    System.out.println("-----------------Libro eliminado!-----------------");
                }
            } else {
                if (j == Biblioteca.getLibros().size() - 1 && !isbn.equals(biblioteca.getLibros().get(j).getIsbn()))
                    System.out.println("El libro no está registrado en la biblioteca.");
            }
        }
    }

    /**
     * Buscar libro por ISBN: Pedira al usuario un ISBN, lo buscara en la lista.
     * En caso de encontrarlo devolvera la posición en la que se encuentra, en caso contrario devolvera un mensaje de error.*/
    public void buscarIsbn() {
        //INSTANCIAMOS
        Biblioteca biblioteca = new Biblioteca();
        Utilities utilities = new Utilities();

        //PEDIMOS EL ISBN
        String isbn = utilities.makeQuestion("Introduce el isbn del libro que estas buscando: ");
        String answer;

        //RECORREMOS LA LISTA DE LIBROS
        for (int i = 0; i < Biblioteca.getLibros().size(); i++) { //recorremos los libros

            //Buscamos un libro que coincida con el isbn introducido
            if (isbn.equals(biblioteca.getLibros().get(i).getIsbn())) {

                //si el isbn coincide con algun libro, pide una confirmación
                answer = utilities.makeQuestion("El libro que estás buscando es " + biblioteca.getLibros().get(i).getTitulo() + "? Sí (1) o No (0)");

                if (answer.equals("1")) {
                    System.out.println("TU BUSQUEDA: \n" + biblioteca.getLibros().get(i) + "\ny se encuentra en la posición: " + i + " de la biblioteca");
                    break;
                } else {
                    System.out.println("Probablemente se haya equivocado escribiendo el ISBN. \n Vuelva a intentarlo.");
                    break;
                }
            }

            if (!isbn.equals(Biblioteca.getLibros().get(i).getIsbn()) && i == Biblioteca.getLibros().size()-1) //si el libro es diferente al isbn y ya hemos llegado al último libro...
            {
                System.out.println("El libro que buscas no está registrado en nuestra biblioteca.");
            }
        }

        if(Biblioteca.getLibros().size()==0)
        {
            System.out.println("No hay ningún libro registrado.");
        }
    }



    /**
     * Buscar libro por titulo: Pedira al usuario un titulo, lo buscara en la lista.
     * Mostrara cualquier libro que contenga la cadena buscada.
     */
    public void buscarTitulo() {
        //INSTANCIAMOS
        Biblioteca biblioteca = new Biblioteca();
        Utilities utilities = new Utilities();

        //PEDIMOS EL TITULO
        String title = utilities.makeQuestion("Introduce el titulo del libro que estas buscando ");
        for (int i = 0; i < Biblioteca.getLibros().size(); i++) { //recorremos los libros

            //COMPROBAMOS QUE HAYA ALGUN LIBRO COINCIDENTE CON EL TITULO INTRODUCIDO
            if (title.equals(biblioteca.getLibros().get(i).getTitulo())) {
                System.out.println(biblioteca.getLibros().get(i));
                break;
            } else {
                if (!title.equals(biblioteca.getLibros().get(i).getTitulo()) && i == Biblioteca.getLibros().size() - 1) //si el libro es diferente al titulo y ya hemos llegado al último libro...
                {
                    System.out.println("No hay ningún libro registrado con este título: " + title);
                }
            }
        }

        if(Biblioteca.getLibros().size()==0)
        {
            System.out.println("No hay ningún libro registrado.");
        }
    }

}
