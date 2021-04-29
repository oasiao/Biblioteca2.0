import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Usuario extends Persona{
    private int telefono;
    private String direccion;
    private int codigoPostal;
    private String correoElectronico;
    private List<Reserva> listaReservas=new ArrayList<>();

    //Necesitamos una lista de usuarios
    private static List<Usuario> listaUsuarios=new ArrayList<>();

    //constructor vacío
    public Usuario(){}

    //constructor con todos los parámetros
    public Usuario(String nombre, String apellido1, String apellido2, int edad,String type,int telefono, String direccion, int codigoPostal, String correoElectronico) {
        super(nombre,apellido1,apellido2,edad,type);
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.correoElectronico = correoElectronico;
    }

    //TODO constructor copia

    public String toString() {
        return "------------------------\n"
                + "Nombre: " + super.getNombre()
                + "\nPrimer apellido: " + super.getApellido1()
                + "\nSegundo apellido: " + super.getApellido2()
                + "\nEdad: " + super.getEdad()
                + "\nDirección: " + direccion
                + "\nCódigo Postal: " + codigoPostal
                + "\nTeléfono: " + getTelefono()
                + "\nCorreo Electrónico: "+ getCorreoElectronico()
                +"\nTipo: "+super.getType()
                + "\n-------------------------\n";
    }

    //GETTERS AND SETTERS
    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public static List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    //MÉTODOS
    public void solicitarDatosUsuario(String nombre, String apellido1, String apellido2, int edad, String type){
        //INSTANCIAMOS
        Bibliotecario bibliotecario=new Bibliotecario();
        Biblioteca biblioteca = new Biblioteca();
        Utilities utilities = new Utilities();

        //SEGUIMOS SOLICITANDO MÁS DATOS
        //Teléfono
        int telefono = parseInt(utilities.makeQuestion("Introduce tu teléfono"));

        //Dirección y código postal
        String direccion = utilities.makeQuestion("Introduce dirección");
        int codigoPostal = parseInt(utilities.makeQuestion("Introduce código postal"));

        //Correo electrónico
        String correoElectronico = utilities.makeQuestion("Introduce tu correo electrónico");


        //Los int's los pasamos a string para poder hacer el control
        String telefonoToString = Integer.toString(telefono);
        String codigoPostalToString = Integer.toString(codigoPostal);

        //CONTROL DE DATOS
        if (!telefonoToString.isEmpty() && !direccion.isEmpty() && !codigoPostalToString.isEmpty() && !correoElectronico.isEmpty()) {

            //añadimos el usuario en la lista: personas<usuario,bibliotecario> que se encuentra en Biblioteca
            Biblioteca.getPersonas().add(new Usuario(nombre, apellido1, apellido2, edad, type, telefono, direccion, codigoPostal, correoElectronico));

            //añadimos el mismo usuario en la lista que hemos creado en esta clase (en esta lista solo hay usuarios)
            getListaUsuarios().add(new Usuario(nombre, apellido1, apellido2, edad, type, telefono, direccion, codigoPostal, correoElectronico));

            //TODO MENU: esto lo añadiría en el MENU
            //si el bibliotecario inicia sesión, entonces, podrá realizar operaciones en la biblioteca
            if(Bibliotecario.getLogin()==true){
                App.menuBibliotecario();
            } else {
                bibliotecario.logInOrRegister();
            }

            //si no cumple el CONTROL DE DATOS, entonces mostrará el mensaje: ERROR!
        } else {
            System.out.println("ERROR. Vuelve a introducir los datos");

            //TODO menu: preguntar si quiee volver a intentarlo o no.
            solicitarDatosUsuario(nombre, apellido1, apellido2, edad, type);
        }
    }

    public void logInUsuario() {
        //INSTANCIAMOS
        Utilities utilities = new Utilities();
        Usuario usuario = new Usuario();
        Bibliotecario bibliotecario = new Bibliotecario();

        //SOLICITAMOS LOS DATOS
        //numTEL
        int numTel = parseInt(utilities.makeQuestion("Introduce tu número de teléfono"));

        //correo electronico
        String correoElectronico = utilities.makeQuestion("Introduce tu correo electrónico");

        for (int i = 0; i < getListaUsuarios().size(); i++) {
            if (getListaUsuarios().get(i).getTelefono()==numTel && getListaUsuarios().get(i).getCorreoElectronico().equals(correoElectronico)) {
                Bibliotecario.setLogin(true);
                System.out.println("\n Bienvenido, " + getListaUsuarios().get(i).getNombre() + ".\n");
            } else {
                System.out.println("ERROR. Usuario incorrecto.\n");
                //TODO MENU logIn
                bibliotecario.logInOrRegister();
            }
        }
    }

    public void cambiarContraseñaUsuario(){
        //INSTANCIAMOS
        Utilities utilities = new Utilities();
        Usuario usuario = new Usuario();
        Bibliotecario bibliotecario = new Bibliotecario();

        //SOLICITAMOS LOS DATOS
        int numTel = parseInt(utilities.makeQuestion("Introduce tu número de teléfono"));

        for (int i = 0; i < getListaUsuarios().size(); i++) {
            if (getListaUsuarios().get(i).getTelefono()==numTel) {
                String correoElectronicoAntiguo=utilities.makeQuestion("Introduce tu correo electrónico antiguo");
                if (getListaUsuarios().get(i).getCorreoElectronico().equals(correoElectronicoAntiguo)) {
                    String correoElectronicoNuevo = utilities.makeQuestion("Introduce tu nuevo correo electrónico");
                    getListaUsuarios().get(i).setCorreoElectronico(correoElectronicoNuevo);
                    System.out.println("----------------CORREO ELECTRÓNICO CAMBIADO----------------");
                }
            } else {
                System.out.println("ERROR. Usuario incorrecto.\n");
            }
        }
    }
}
