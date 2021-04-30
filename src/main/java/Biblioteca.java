import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private String nombreBiblioteca;
    private static List<Libro> libros = new ArrayList<>();
    private static List<Persona> personas =new ArrayList<>();

    //creamos una lista de libros reservados
    private static List<Libro> librosReservados=new ArrayList<>();

    //constructor vacio
    public Biblioteca(){}

    //constructor con parametros
    public Biblioteca(String nombreBiblioteca) {
        this.nombreBiblioteca = nombreBiblioteca;
    }

    //TODO CONSTRUCTOR COPIA

    //GETTERS AND SETTERS
    public String getNombreBiblioteca() {
        return nombreBiblioteca;
    }

    public void setNombreBiblioteca(String nombreBiblioteca) {
        String primeraLetra=nombreBiblioteca.substring(0,0);
        String resto=nombreBiblioteca.substring(1);
        nombreBiblioteca=primeraLetra.toUpperCase()+resto;
        this.nombreBiblioteca = nombreBiblioteca;
    }

    public static List<Libro> getLibros() {
        return libros;
    }

    public static void setLibros(List<Libro> libros) {
        Biblioteca.libros = libros;
    }

    public static List<Persona> getPersonas() {
        return personas;
    }

    public static void setPersonas(List<Persona> personal) {
        Biblioteca.personas = personal;
    }

    public static List<Libro> getLibrosReservados() {
        return librosReservados;
    }

    public static void setLibrosReservados(List<Libro> librosReservados) {
        Biblioteca.librosReservados = librosReservados;
    }


    //MÉTODOS
    /*Mostrar libros: Imprimirá por pantalla toda la lista de libros. (método instancia)*/
    public String mostrarLibros(){
        Libro libro = new Libro();
        String libros="";
        for (int i = 0; i < getLibros().size(); i++) {
            libros+=getLibros().get(i).toString()+"\n";//este toString es de Libro, porque hacemos get(i) por tanto equivale a LIBRO
        }
        return libros;
    }

    public String mostrarLibrosDisponibles(){
        String librosDisponibles="";
        for (int i = 0; i < getLibros().size(); i++) {
            if(getLibros().get(i).getNumCopiasDisponibles()!=0)
            {
                librosDisponibles+=getLibros().get(i).toString()+"\n";
            }
        }
        return librosDisponibles;
    }

    public String mostrarPersonas(){
        String personas="";
        for (int i = 0; i < getPersonas().size(); i++) {
            personas+=getPersonas().get(i).toString()+"\n";
        }
        return personas;
    }


    //TO STRING BIBLIOTECA
    @Override
    public String toString() {
        return "Biblioteca: "+getNombreBiblioteca()+
                "\nLista de libros: "+getLibros()+
                "\nLista del personal:"+ getPersonas();
    }

}
