import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Kim Asiao
 * @author Santiago Martinez
 * @version 1.0
 * Clase Usuario que extiende de la clase abstracta Persona: crea usuarios con atributos especificos y los adhiere a la lista Personas.
 */
public class Usuario extends Persona {
    /**
     * Atributos telefono, direccion, codigo postal, correo electronico; y las listas de reservas y usuarios.
     */
    private int telefono;
    private String direccion;
    private int codigoPostal;
    private String correoElectronico;
    private static List<Reserva> listaReservas = new ArrayList<>();

    //Necesitamos una lista de usuarios
    private static List<Usuario> listaUsuarios = new ArrayList<>();

    /**
     * Constructor vacio.
     */
    public Usuario() {
    }

    /**
     * Constructor en los que se pasan los siguientes parametros (incluyendo aquellos que se encuentran en la super clase).
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param edad
     * @param type
     * @param telefono
     * @param direccion
     * @param codigoPostal
     * @param correoElectronico
     */
    public Usuario(String nombre, String apellido1, String apellido2, int edad, String type, int telefono, String direccion, int codigoPostal, String correoElectronico) {
        super(nombre, apellido1, apellido2, edad, type);
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.correoElectronico = correoElectronico;
    }

    /**
     *
     * @return devuelve la lista de usuarios con el siguiente formato.
     */

    public String toString() {
        return "------------------------\n"
                + "Nombre: " + super.getNombre()
                + "\nPrimer apellido: " + super.getApellido1()
                + "\nSegundo apellido: " + super.getApellido2()
                + "\nEdad: " + super.getEdad()
                + "\nDirección: " + direccion
                + "\nCódigo Postal: " + codigoPostal
                + "\nTeléfono: " + getTelefono()
                + "\nCorreo Electrónico: " + getCorreoElectronico()
                + "\nTipo: " + super.getType()
                + "\n-------------------------\n";
    }

    //GETTERS AND SETTERS

    /**
     *
     * @return obtiene el telefono del usuario.
     */
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

    /**
     *
     * @return obtiene el email del usuario.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     *
     * @param correoElectronico establece el email al usuario.
     */

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     *
     * @return  obtiene la lista Reservas.
     */


    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    /**
     *
     * @return obtiene lista de usuarios.
     */

    public static List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    //MÉTODOS

    /**
     * Pasamos los atributos comunes de la super clase Persona; ademas, se añaden atributos propios del usuario.
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param edad
     * @param type
     */
    public void solicitarDatosUsuario(String nombre, String apellido1, String apellido2, int edad, String type) {
        //INSTANCIAMOS
        Bibliotecario bibliotecario = new Bibliotecario();
        Biblioteca biblioteca = new Biblioteca();
        Utilities utilities = new Utilities();

        //SEGUIMOS SOLICITANDO MÁS DATOS
        //Teléfono
        int telefono = parseInt(utilities.makeQuestion("Introduce tu teléfono"));

        //CONTROL NÚMERO DE TELÉFONO
        while(control(telefono).length()!=9){
            System.out.println("Este número no existe. Vuelve a introducir tu número de teléfono!");
            telefono=parseInt(utilities.makeQuestion("Introduce tu número de teléfono"));
        }

        //Dirección y código postal
        String direccion = utilities.makeQuestion("Introduce dirección");
        int codigoPostal = parseInt(utilities.makeQuestion("Introduce código postal"));

        //CONTROL CÓDIGO POSTAL
        while(control(codigoPostal).length()>5){
            System.out.println("El código postal introducido no existe. Vuelve a introducir su código postal!");
            codigoPostal=parseInt(utilities.makeQuestion("Introduce código postal"));
        }

        //Correo electrónico
        String correoElectronico = utilities.makeQuestion("Introduce tu correo electrónico");

        //CONTROL CORREO ELECTRÓNICO
        while(!correoElectronico.contains("@") && !correoElectronico.contains("."))
        {
            System.out.println("Este correo electrónico no existe. Vuelve a introducir tu correo electrónico!");
            correoElectronico=utilities.makeQuestion("Introduce tu correo electrónico");
        }


        //Los int's los pasamos a string para poder hacer el control
        String telefonoToString = Integer.toString(telefono);
        String codigoPostalToString = Integer.toString(codigoPostal);

        //CONTROL DE DATOS
        if (!telefonoToString.isEmpty() && !direccion.isEmpty() && !codigoPostalToString.isEmpty() && !correoElectronico.isEmpty()) {

            //añadimos el usuario en la lista: personas<usuario,bibliotecario> que se encuentra en Biblioteca
            Biblioteca.getPersonas().add(new Usuario(nombre, apellido1, apellido2, edad, type, telefono, direccion, codigoPostal, correoElectronico));

            //añadimos el mismo usuario en la lista que hemos creado en esta clase (en esta lista solo hay usuarios)
            getListaUsuarios().add(new Usuario(nombre, apellido1, apellido2, edad, type, telefono, direccion, codigoPostal, correoElectronico));

            System.out.println("\n-----------------¡Usuario registrado!--------------------\n");

            //si el bibliotecario inicia sesión, entonces, podrá realizar operaciones en la biblioteca
            if (Bibliotecario.getLogin() == true) {
                App.menuBibliotecario();
            } else {
                bibliotecario.logInOrRegister();
            }

            //si no cumple el CONTROL DE DATOS, entonces mostrará el mensaje: ERROR!
        } else {
            System.out.println("ERROR. Vuelve a introducir los datos");
            solicitarDatosUsuario(nombre, apellido1, apellido2, edad, type);
        }
    }

    /**
     * logInUsuario: control de inicio de sesion del usuario, en el que se piden el telefono y el email.
     */
    public void logInUsuario() {
        //INSTANCIAMOS
        Utilities utilities = new Utilities();
        Usuario usuario = new Usuario();
        Bibliotecario bibliotecario = new Bibliotecario();

        //CONTROL CONTRASEÑA
        int contador=0;

        //SOLICITAMOS LOS DATOS
        //numTEL
        int numTel = parseInt(utilities.makeQuestion("Introduce tu número de teléfono"));

        //correo electronico
        String correoElectronico = utilities.makeQuestion("Introduce tu correo electrónico");



        for (int i = 0; i < getListaUsuarios().size(); i++) {
            if (getListaUsuarios().get(i).getTelefono() == numTel){
                    while(!getListaUsuarios().get(i).getCorreoElectronico().equals(correoElectronico) && contador<5){
                        System.out.println("Correo electrónico incorrecto.");
                        correoElectronico=utilities.makeQuestion("Introduce tu correo electrónico");
                        contador++;
                    }

                    if(contador==5)
                    {
                        System.out.println("No te quedan intentos.");
                        break;
                    }

                Bibliotecario.setLogin(true);
                System.out.println("\n ---------------Bienvenido, " + getListaUsuarios().get(i).getNombre() + ".------------------------\n");
                App.menuUsuario();
                }
        }
        System.out.println("ERROR. ¡USUARIO INCORRECTO!\n");
        bibliotecario.logInOrRegister();
    }

    /**
     * cambiarContraseñaUsuario: permite que el usuario cambie de contraseña.
     */
    public void cambiarContraseñaUsuario() {
        //INSTANCIAMOS
        Utilities utilities = new Utilities();
        Usuario usuario = new Usuario();
        Bibliotecario bibliotecario = new Bibliotecario();

        //SOLICITAMOS LOS DATOS
        int numTel = parseInt(utilities.makeQuestion("Introduce tu número de teléfono"));

        for (int i = 0; i < getListaUsuarios().size(); i++) {
            if (getListaUsuarios().get(i).getTelefono() == numTel) {
                String correoElectronicoAntiguo = utilities.makeQuestion("Introduce tu correo electrónico antiguo");

                //CONTROL DEL CORREO ELECTRÓNICO ANTIGUO
                while(!getListaUsuarios().get(i).getCorreoElectronico().equals(correoElectronicoAntiguo))
                {
                    System.out.println("Correo electronico incorrecto. Vuelve a introducir su correo electronico antiguo!");
                    correoElectronicoAntiguo=utilities.makeQuestion("Introduce tu correo electrónico antiguo");
                }

                if (getListaUsuarios().get(i).getCorreoElectronico().equals(correoElectronicoAntiguo)) {
                    String correoElectronicoNuevo = utilities.makeQuestion("Introduce tu nuevo correo electrónico");

                    //CONTROL CORREO ELECTRÓNICO
                    while(!correoElectronicoNuevo.contains("@")&&!correoElectronicoNuevo.contains("."))
                    {
                        System.out.println("Este correo electrónico no existe. Vuelve a introducir tu correo electrónico!");
                        correoElectronicoNuevo=utilities.makeQuestion("Introduce tu nuevo correo electrónico");
                    }

                    getListaUsuarios().get(i).setCorreoElectronico(correoElectronicoNuevo);
                    System.out.println("\n----------------CORREO ELECTRÓNICO CAMBIADO----------------\n");
                }
                else
                {
                    System.out.println("ERROR. Usuario incorrecto.\n");
                }
            } else {
                System.out.println("ERROR. Usuario incorrecto.\n");
            }
        }
    }

    /**
     *
     * @return muestra la lista de Usuarios con el formato especificado en el metodo toString().
     */
    public String mostrarUsuarios() {
        String usuarios = "";
        for (int i = 0; i < getListaUsuarios().size(); i++) {
            usuarios += getListaUsuarios().get(i).toString() + "\n";
        }
        return usuarios;
    }

    /**
     * Creamos este metodo para poder hacer un control de los datos que introducimos
     * @param var ha de ser un numero.
     * @return convertira el numero en string y lo devolvera.
     */
    public String control(int var){
        String control=Integer.toString(var);
        return control;
    }
}
