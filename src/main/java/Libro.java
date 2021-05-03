/*Concretamente debemos almacenar:
- ISBN, título, autor, editorial, nº de copias y nº copias disponibles. (DONE)

Para esta clase debes crear:
- Constructor vacío. (DONE)
- Constructor con todos los parámetros. (DONE)
- Constructor copia. (WTH???????????????????????)
- toString. (DONE)
- getters/setters (DTO) (Getters and setters DONE, but DTO?)
- contador de libros (que llevará el control de los diferentes libros que hay en la aplicación,
por ejemplo, si tenemos el libro Javañol y de este libro tenemos 4 copias,
nuestro contador de libro marcará un libro). (DONE)*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private int numCopias;
    private int numCopiasDisponibles;

    public static int getNumLibros() {
        return numLibros;
    }

    public static void setNumLibros(int numLibros) {
        Libro.numLibros = numLibros;
    }

    private static int numLibros;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumCopias() {
        return numCopias;
    }

    public void setNumCopias(int numCopias) {
        /*añadir un control*/
        this.numCopias = numCopias;
    }

    public int getNumCopiasDisponibles() {
        return numCopiasDisponibles;
    }

    public void setNumCopiasDisponibles(int numCopiasDisponibles) {
        this.numCopiasDisponibles = numCopiasDisponibles;
    }

    /**
     * Constructor vacio
     */
    public Libro() {
    }

    /**
     * @param isbn
     * @param titulo
     * @param autor
     * @param editorial
     * @param numCopias
     * @param numCopiasDisponibles Constructor con todos los parametros
     */
    public Libro(String isbn, String titulo, String autor, String editorial, int numCopias, int numCopiasDisponibles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.numCopias = numCopias;
        this.numCopiasDisponibles = numCopiasDisponibles;
    }

    @Override
    public String toString() {
        return "-------------------------------------------\nISBN: " + getIsbn() + "\nTítulo: " + getTitulo() + "\nAutor: " + getAutor() + "\nEditorial: " + getEditorial()
                + "\nNúmero de copias: " + getNumCopias() + "\nNúmero de copias disponibles: " + getNumCopiasDisponibles() + "\n-------------------------------------------";
    }

    //MÉTODOS
    /*- Añadir libro: Solicitará los datos para crear un libro (DONE) y añadirá dicho libro a una lista de libros*/
    public void añadirLibro() {
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
            //número de copias disponibles
            int numCopiasDispo = parseInt(utilities.makeQuestion("Introduce el número de copias disponibles del libro: "));

            //AÑADIMOS EL LIBRO
            Libro libro = new Libro(isbn, title, author, editorial, numCopias, numCopiasDispo); //libro formato objeto
            biblioteca.getLibros().add(libro); //añadimos el libro en la lista
            setNumLibros(getNumLibros() + 1); //sumamos uno a la cantidad de libros que hay

            //MENSAJE DE SUCCESS
            System.out.println("¡El libro se ha introducido con éxito!");
        }
    }

    /*- Eliminar libro: Solicitará al usuario un ISBN, lo buscará y lo eliminará de la lista
    No se puede eliminar un libro que tiene reservas.*/

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

    /*- Buscar libro por ISBN: Pedirá al usuario un ISBN, lo buscará en la lista.
    En caso de encontrarlo devolverá la posición en la que se encuentra, en caso contrario devolverá -1.*/
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



    /* Buscar libro por título: Pedirá al usuario un título, lo buscará en la lista.
    Mostrará cualquier libro que contenga la cadena buscada. */
    public void buscarTitulo() {
        //INSTANCIAMOS
        Biblioteca biblioteca = new Biblioteca();
        Utilities utilities = new Utilities();

        //PEDIMOS EL TÍTULO
        String title = utilities.makeQuestion("Introduce el titulo del libro que estas buscando ");
        for (int i = 0; i < Biblioteca.getLibros().size(); i++) { //recorremos los libros

            //COMPROBAMOS QUE HAYA ALGÚN LIBRO COINCIDENTE CON EL TÍTULO INTRODUCIDO
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
