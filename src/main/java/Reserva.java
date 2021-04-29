import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class Reserva extends Libro{
    //se caracteriza por tener un libro, por eso hago un extends pero no sé si ist gut
    private String fechaYHora;

    //añadimos el usuario para poder pasarlo por parámetro al constructor
    private Usuario usuario;

    //constructor vacío
    public  Reserva(){}

    //constructor con parámetros
    public Reserva(String isbn, String titulo, String autor,String editorial,int numCopias,int numCopiasDisponibles,Usuario usuario,String fechaYHora) {
        super(isbn,titulo,autor,editorial,numCopias,numCopiasDisponibles); //extiende de Libro
        this.usuario=usuario;
        this.fechaYHora = fechaYHora;
    }

    @Override
    public String toString() {
        return "------------------------------------------------\nReserva:" +
                "Reservado por: " + usuario + "\n" +
                "Fecha y hora:" + fechaYHora + "\n------------------------------------------------\n";
    }

    //TODO : ES UN ADD
    //CREAMOS UN MÉTODO QUE MUESTRE TODAS LAS RESERVAS QUE HAY
    public String mostrarReservas(){
        //La lista de reservas se encuentra en usuario porque así especifica el enunciado
        Usuario usuario = new Usuario();
        String reservas="";
        for (int i = 0; i < usuario.getListaReservas().size(); i++) {
            reservas+=usuario.getListaReservas().toString();
        }
        return reservas;
    }

    //TODO CONSTRUCTOR COPIA

    //GETTERS AND SETTERS
    public String getFechaYHora() {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public void setFechaYHora(String fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    //MÉTODOS
    public void reservarLibro() {
        //INSTANCIAMOS
        Utilities utilities = new Utilities();
        Usuario usuario = new Usuario();
        Libro libro = new Libro();
        Biblioteca biblioteca = new Biblioteca();

        //SOLICITAMOS DATOS
        //Número de teléfono del usuario
        int numTel = parseInt(utilities.makeQuestion("Introduce su número de teléfono"));

        //Correo electrónico del usuario
        String correoElectronico = utilities.makeQuestion("Introduce tu correo electrónico");

        //RECORREMOS LA LISTA DONDE SOLO HAY USUARIOS
        for (int i = 0; i < usuario.getListaUsuarios().size(); i++) {

            //COMPROBAMOS QUE EL USUARIO ESTA REGISTRADO EN NUESTRA BIBLIOTECA
            if (usuario.getListaUsuarios().get(i).getTelefono()==(numTel) &&
                    usuario.getListaUsuarios().get(i).getCorreoElectronico().equals(correoElectronico))
            {

                //SI EL USUARIO EXISTE, INTRODUCIMOS EL ISBN DEL LIBRO QUE QUIERE RESERVAR
                String isbn=utilities.makeQuestion("Introduce el isbn del libro");

                //RECORREMOS LA LISTA DE LIBROS
                for (int j = 0; j < Biblioteca.getLibros().size(); j++)
                {
                    //COMPROBAMOS QUE EL LIBRO SE ENCUENTRA EN LA BIBLIOTECA Y ADEMÁS, QUE HAYA COPIAS DISPONIBLES
                    if(Biblioteca.getLibros().get(j).getIsbn().equals(isbn) && Biblioteca.getLibros().get(j).getNumCopiasDisponibles() > 0)
                    {
                        //LIBRO RESERVADO
                        //Restamos libro disponible
                        Biblioteca.getLibros().get(j).setNumCopiasDisponibles(Biblioteca.getLibros().get(j).getNumCopiasDisponibles() - 1);

                        //añadimos el libro reservado en la lista de libros reservados para poder hacer el control de eliminarLibro
                        Biblioteca.getLibrosReservados().add(Biblioteca.getLibros().get(j));

                        //creamos una reserva
                        usuario.getListaReservas().add(new Reserva(Biblioteca.getLibros().get(j).getIsbn(),
                                Biblioteca.getLibros().get(j).getTitulo(), Biblioteca.getLibros().get(j).getAutor(),
                                Biblioteca.getLibros().get(j).getEditorial(),
                                Biblioteca.getLibros().get(j).getNumCopias(),
                                Biblioteca.getLibros().get(j).getNumCopiasDisponibles(),
                                usuario.getListaUsuarios().get(i),
                                getFechaYHora()));

                        System.out.println("Libro reservado!");
                    }
                    else{
                        //NO HAY LIBROS DISPONIBLES
                        System.out.println("No tenemos ningún libro disponible.");
                    }

                    //EL LIBRO NO ESTÁ REGISTRADO EN NUESTRA BIBLIOTECA
                    //Si hemos llegado al último libro y el isbn != todos, entonces mostraremos el siguiente mensaje
                    if(j==Biblioteca.getLibros().size()-1 && !Biblioteca.getLibros().get(j).getIsbn().equals(isbn))
                    {
                        System.out.println("El libro no se encuentra registrado en nuestra biblioteca.");
                    }
                }
            }

            //CORREO ELECTRÓNICO Y TELÉFONO NO REGISTRADOS
            else {
                System.out.println("El usuario introducido no existe.");
                //TODO MENU: SI NO EXISTE EL USUARIO, PREGUNTAR QUE QUIERE HACER
            }
        }
    }

    public void devolverLibro(){
        //INSTANCIAMOS
        Utilities utilities = new Utilities();
        Usuario usuario = new Usuario();
        Libro libro = new Libro();
        Biblioteca biblioteca = new Biblioteca();

        //SOLICITAMOS DATOS
        //Número de teléfono del usuario
        int numTel = parseInt(utilities.makeQuestion("Introduce su número de teléfono"));

        //Correo electrónico del usuario
        String correoElectronico = utilities.makeQuestion("Introduce tu correo electrónico");

        //RECORREMOS LA LISTA DONDE SOLO HAY USUARIOS
        for (int i = 0; i < usuario.getListaUsuarios().size(); i++) {

            //COMPROBAMOS QUE EL USUARIO ESTA REGISTRADO EN NUESTRA BIBLIOTECA
            if (usuario.getListaUsuarios().get(i).getTelefono()==(numTel) &&
                    usuario.getListaUsuarios().get(i).getCorreoElectronico().equals(correoElectronico))
            {
                //SI EL USUARIO EXISTE, INTRODUCIMOS EL ISBN DEL LIBRO QUE QUIERE DEVOLVER
                String isbn=utilities.makeQuestion("Introduce el isbn del libro");

                //RECORREMOS LA LISTA DE LIBROS
                for (int j = 0; j < Biblioteca.getLibros().size(); j++)
                {
                    //COMPROBAMOS QUE EL LIBRO SE ENCUENTRA EN LA BIBLIOTECA
                    if(Biblioteca.getLibros().get(j).getIsbn().equals(isbn))
                    {
                        //LIBRO DEVUELTO
                        Biblioteca.getLibros().get(j).setNumCopiasDisponibles(Biblioteca.getLibros().get(j).getNumCopiasDisponibles() + 1);
                    }

                    //EL LIBRO NO ESTÁ REGISTRADO EN NUESTRA BIBLIOTECA
                    //Si hemos llegado al último libro y el isbn != todos, entonces mostraremos el siguiente mensaje
                    if(j==Biblioteca.getLibros().size()-1 && !Biblioteca.getLibros().get(j).getIsbn().equals(isbn))
                    {
                        System.out.println("El libro no se encuentra registrado en nuestra biblioteca.");
                    }
                }
            }
            //CORREO ELECTRÓNICO Y TELÉFONO NO REGISTRADOS
            else {
                System.out.println("El usuario introducido no existe.");
                //TODO MENU: SI NO EXISTE EL USUARIO, PREGUNTAR QUE QUIERE HACER
            }
        }
    }

}
