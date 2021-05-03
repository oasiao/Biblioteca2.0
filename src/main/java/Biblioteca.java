import java.util.ArrayList;
import java.util.List;

/**
 * @author Kim Asiao
 * @author Santiago Martinez
 * @version 1.0
 * Clase Biblioteca: se almacenan las listas libros, libros reservados y personas.
 */

public class Biblioteca {

    /**
     * Atributos que definen el nombre de biblioteca y las listas de libros, reservas de estos y personas.
     */
    private String nombreBiblioteca;
    private static List<Libro> libros = new ArrayList<>();
    private static List<Persona> personas =new ArrayList<>();

    //creamos una lista de libros reservados
    private static List<Libro> librosReservados=new ArrayList<>();

    /**
     * Constructor vacio.
     */
    public Biblioteca(){}

    /**
     * Constructor con el parametro del nombre de la biblioteca.
     * @param nombreBiblioteca
     */
    public Biblioteca(String nombreBiblioteca) {
        this.nombreBiblioteca = nombreBiblioteca;
    }



    //GETTERS AND SETTERS

    /**
     *
     * @return devuelve el nombre de la biblioteca.
     */
    public String getNombreBiblioteca() {
        return nombreBiblioteca;
    }

    public void setNombreBiblioteca(String nombreBiblioteca) {
        String primeraLetra=nombreBiblioteca.substring(0,0);
        String resto=nombreBiblioteca.substring(1);
        nombreBiblioteca=primeraLetra.toUpperCase()+resto;
        this.nombreBiblioteca = nombreBiblioteca;
    }

    /**
     *
     * @return devuelve la lista de libros.
     */
    public static List<Libro> getLibros() {
        return libros;
    }

    public static void setLibros(List<Libro> libros) {
        Biblioteca.libros = libros;
    }

    /**
     *
     * @return devuelve la lista de personas.
     */
    public static List<Persona> getPersonas() {
        return personas;
    }

    public static void setPersonas(List<Persona> personal) {
        Biblioteca.personas = personal;
    }

    /**
     *
     * @return devuelve lista de libros reservados.
     */
    public static List<Libro> getLibrosReservados() {
        return librosReservados;
    }

    public static void setLibrosReservados(List<Libro> librosReservados) {
        Biblioteca.librosReservados = librosReservados;
    }


    //MÉTODOS
    /**
     *
     * Mostrar libros: Imprimira por pantalla toda la lista de libros.
     */

    public String mostrarLibros(){
        Libro libro = new Libro();
        String libros="";
        for (int i = 0; i < getLibros().size(); i++) {
            libros+=getLibros().get(i).toString()+"\n";//este toString es de Libro, porque hacemos get(i) por tanto equivale a LIBRO
        }
        return libros;
    }

    /**
     *
     * @return devuelve la lista de libros disponibles para reservar, con el formato especificado en el metodo toString().
     */

    public String mostrarLibrosDisponibles(){
        String librosDisponibles="";
        for (int i = 0; i < getLibros().size(); i++) {
            if(getLibros().get(i).getNumCopiasDisponibles()!=0)
            {
                librosDisponibles+=getLibros().get(i).toString()+"\n";
            }
            else
            {
                System.out.println("No hay ningún libro disponible.");
            }
        }
        return librosDisponibles;
    }

    /**
     *
     * @return devuelve la lista de personas con el formato del toString().
     */
    public String mostrarPersonas(){
        String personas="";
        for (int i = 0; i < getPersonas().size(); i++) {
            personas+=getPersonas().get(i).toString()+"\n";
        }
        return personas;
    }


    /**
     *
     * @return devuelve el formato de visualizacion de una hipotetica lista de biblioteca.
     */
    @Override
    public String toString() {
        return "Biblioteca: "+getNombreBiblioteca()+
                "\nLista de libros: "+getLibros()+
                "\nLista del personal:"+ getPersonas();
    }

}
