import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Kim Asiao
 * @author Santiago Martinez
 * @version 1.0
 * Clase Bibliotecario extiende de Persona: almacena la logica de la aplicacion del usuario Bibliotecario.
 */


public class Bibliotecario extends Persona {
    private String puestoTrabajo;
    private String NIF;
    private String password;

    //Creamos una nueva lista, solo para bibliotecarios
    private static List<Bibliotecario> listaBibliotecarios = new ArrayList<>();

    //Creamos un boolean login
    private static boolean login;

    //CREAMOS una var para la contraseña
    private static boolean passwordValida = false;

    /**
     *
     * Constructor vacio.
     */

    public Bibliotecario() {
    }

    /**
     * Contructor con los parametros de la super clase Persona y los propios del Bibliotecario.
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param edad
     * @param type
     * @param puestoTrabajo
     * @param NIF
     * @param password
     */
    public Bibliotecario(String nombre, String apellido1, String apellido2, int edad, String type, String puestoTrabajo, String NIF, String password) {
        super(nombre, apellido1, apellido2, edad, type);
        this.puestoTrabajo = puestoTrabajo;
        this.NIF = NIF;
        this.password = password;
    }

    /**
     *
     * @return devuelve el bibliotecario con el siguiente formato.
     */
    @Override
    public String toString() {
        return "------------------------\n"
                + "\nNIF: " + NIF
                + "\nPuesto: " + puestoTrabajo
                + "\nContraseña: " + password
                + "\nTipo: " + super.getType()
                + "\n-------------------------\n";
    }

    //GETTERS AND SETTERS
    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    /**
     *
     * @return devuelve el nif del bibliotecario.
     */
    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    /**
     *
     * @return devuelve la contraseña de bibliotecario.
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password establece la contraseña de bibliotecario.
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return devuelve la lista de bibliotecarios.
     */

    public static List<Bibliotecario> getListaBibliotecarios() {
        return listaBibliotecarios;
    }

    public void setListaBibliotecarios(List<Bibliotecario> listaBibliotecarios) {
        this.listaBibliotecarios = listaBibliotecarios;
    }

    /**
     *
     * @return devuelve el estado del login.
     */
    public static boolean getLogin() {
        return login;
    }

    /**
     *
     * @param login establece el estado del login.
     */
    public static void setLogin(boolean login) {
        boolean loginDefecto = false;
        Bibliotecario.login = loginDefecto;
        if (!login) {
            Bibliotecario.login = loginDefecto;
        } else {
            Bibliotecario.login = login;
        }
    }

    /**
     *
     * @param password le pasamos por parametro un password y la valida
     * @return devuelve la validacion del password.
     */
    public static boolean isPasswordValida(String password) {
        boolean mayus = false;
        boolean num = false;
        boolean minus = false;
        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                if (Character.isUpperCase(password.charAt(i))) {
                    mayus = true;
                } else if (Character.isLowerCase(password.charAt(i))) {
                    minus = true;
                } else if (Character.isDigit(password.charAt(i))) {
                    num = true;
                }
            }
        }
        passwordValida = mayus && num && minus;
        return passwordValida;
    }

    /**
     *
     * @param passwordValida establece el estado del password.
     */
    public static void setPasswordValida(boolean passwordValida) {
        Bibliotecario.passwordValida = passwordValida;
    }


    //MÉTODOS

    /**
     * solicitarDatosBibliotecario: pasa los datos de la super clase y pregunta los datos propios del bibliotecario.
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param edad
     * @param type
     */
    public void solicitarDatosBibliotecario(String nombre, String apellido1, String apellido2, int edad, String type) {
        //INSTANCIAMOS
        Biblioteca biblioteca = new Biblioteca();
        Utilities utilities = new Utilities();

        //SEGUIMOS SOLICITANDO DATOS
        //Puesto de trabajo
        String puestoTrabajo = utilities.makeQuestion("Introduce tu puesto de trabajo");

        //Nif
        String NIF = utilities.makeQuestion("Introduce tu NIF").toUpperCase();
        //CONTROL NIF
        while (NIF.length() != 9) {
            System.out.println("---------------- El NIF introducido es incorrecto. Inténtelo de nuevo! ----------------");
            NIF = utilities.makeQuestion("Introduce tu NIF").toUpperCase();
        }


        //Contraseña
        setPasswordValida(false);
        String password = utilities.makeQuestion("Introduce tu password");

        while (!isPasswordValida(password)) {
            System.out.println("Introduce una password más segura que tenga mínimo 8 carácteres y contenga números, mayúsculas y minúsculas");
            password = utilities.makeQuestion("Introduce una password");
        }

        //CONTROL DE DATOS
        if (!puestoTrabajo.isEmpty() && !NIF.isEmpty() && !password.isEmpty()) {
            //Añade el bibliotecario a la lista de Personas <usuario,bibliotecario>
            Biblioteca.getPersonas().add(new Bibliotecario(nombre, apellido1, apellido2, edad, type, puestoTrabajo, NIF, password));

            //Añade el bibliotecario a la lista que hemos creado en esta clase
            getListaBibliotecarios().add(new Bibliotecario(nombre, apellido1, apellido2, edad, type, puestoTrabajo, NIF, password));

            System.out.println("\n-----------------------¡Bibliotecario registrado!---------------------\n");

            //el bibliotecario puede añadir nuevo personal, por tanto, si ya ha hecho log in, volverá al menu sino, tendrá que iniciar sesión
            if (getLogin()) {
                App.menuBibliotecario();
            } else {
                logInOrRegister();
            }

            //SI NO CUMPLE CON EL CONTROL DE DATOS, ENTONCES, MOSTRARA EL MENSAJE ¡ERROR!
        }else{
            System.out.println("----------------- ERROR! VUELVE A INTRODUCIR LOS DATOS -----------------");
            solicitarDatosBibliotecario(nombre, apellido1, apellido2, edad, type);
        }
    }

    /**
     * logInOrRegister: es un menu en el que se permite registrar una persona, que este inicie sesion o bien salir
     * del programa.
     *
     */
    public void logInOrRegister() {
        //INSTANCIAMOS
        Utilities utilities = new Utilities();
        Biblioteca biblioteca = new Biblioteca();
        Usuario usuario = new Usuario();

        //PRIMER MENÚ
        System.out.println("----------------------------------");
        System.out.println("1. Registrar nuevo bibliotecario/usuario");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Salir");
        System.out.println("----------------------------------\n");

        //ESCOGE OPCIÓN
        int opcion = parseInt(utilities.makeQuestion("Escoge una opción"));

        //MENÚ
        switch (opcion) {

            //REGISTRAR NUEVO USUARIO O BIBLIOTECARIO
            case 1:
                super.solicitarDatosPersona(); //Solicitamos los datos de la Persona (y en el mismo método hace elegir entre usuario o bibliotecario)
                logInOrRegister(); //A continuación, damos a elegir al usuario si quiere iniciar sesión o salir
                break;

            //INICIAR SESIÓN
            case 2:
                String user = utilities.makeQuestion("¿Bibliotecario(B), o Usuario(U)?").toUpperCase();

                switch (user) {
                    case "B":
                        logInBibliotecario();
                        break;
                    case "U":
                        usuario.logInUsuario();
                        break;
                    default:
                        System.out.println("----------------- ¡ERROR! -----------------");
                        break;
                }
                break;

            //SALIR
            case 3:
                System.exit(0);
                break;

            default:
                System.out.println("----------------- ¡ERROR! -----------------");
                logInOrRegister(); //VOLVEMOS AL MENU
                break;
        }
    }

    /**
     * logInBibliotecario: control de inicio sesion para el bibliotecario, introduciendo el nif y el password.
     */
    public void logInBibliotecario() {
        Utilities utilities = new Utilities();
        String NIF = utilities.makeQuestion("Introduce usuario (NIF)").toUpperCase();
        String password = utilities.makeQuestion("Introduce tu password");

        //CONTADOR
        int contador=0;
        for (int i = 0; i < getListaBibliotecarios().size(); i++) {
            if (getListaBibliotecarios().get(i).getNIF().equals(NIF))
            {
                while(!getListaBibliotecarios().get(i).getPassword().equals(password) && contador!=5)
                {
                    contador++;
                    System.out.println("----------------- PASSWORD INCORRECTA -----------------");
                    password = utilities.makeQuestion("Introduce tu password");
                }

                if(contador==5)
                {
                    System.out.println("--------------- NO TE QUEDAN MAS INTENTOS ---------------");
                    break;
                }

                setLogin(true);
                System.out.println("\n Bienvenido, " + getListaBibliotecarios().get(i).getNombre() + ".\n");
                App.menuBibliotecario();
            }
        }
        System.out.println("----------------- ¡ERROR! -----------------");
        logInOrRegister();
    }

    /**
     * logOut: establece el cierre de sesion.
     */
    public void logOut() {
        setLogin(false);
        logInOrRegister();
    }

    /**
     * addPersonal: invoca al metodo solicitarDatosPersona() y crea nuevas personas.
     */
    public void addPersonal() {
        super.solicitarDatosPersona();
    }

    /**
     * eliminarPersonal: permite, mediante la introduccion del nif, borrar a la persona con los datos coincidentes.
     */
    public void eliminarPersonal() {
        Utilities utilities = new Utilities();
        String opcion = utilities.makeQuestion("Introduce el NIF").toUpperCase();

        for (int i = 0; i < Biblioteca.getPersonas().size(); i++) {
            for (int j = 0; j < getListaBibliotecarios().size(); j++) {
                if (Biblioteca.getPersonas().get(i).toString().contains(opcion) &&
                        Biblioteca.getPersonas().get(i).toString().equals(getListaBibliotecarios().get(j).toString())) {

                    Biblioteca.getPersonas().remove(i);
                    getListaBibliotecarios().remove(j);

                    System.out.println("------------------- ELIMINADO CON EXITO ------------------");
                    break;
                } else {
                    System.out.println("----------------- NO EXISTE -----------------");
                }
            }
        }
    }

    /**
     * cambiarPasswordBibliotecario: permite cambiar el password del bibliotecario, pero jamas del
     * usuario root (administrador).
     */

    public void cambiarPasswordBibliotecario() {
        //INSTANCIAMOS
        Utilities utilities = new Utilities();

        //SOLICITAMOS LOS DATOS
        String NIF = utilities.makeQuestion("Introduce tu NIF");

        //NUMERO DE INTENTOS
        int contador=0;

        //Recorremos la lista
        for (int i = 0; i < getListaBibliotecarios().size(); i++) {
            if(NIF.equals("00000000A"))
            {
                System.out.println("\n-------------- NO PUEDES CAMBIAR EL PASSWORD DEL ADMINISTRADOR --------------\n");
                break;
            }
            if (getListaBibliotecarios().get(i).getNIF().equals(NIF)) {
                //SOLICITAMOS LA CONTRASEÑA ANTIGUA
                String passwordAntigua = utilities.makeQuestion("Introduce tu contraseña antigua");

                //SI NO COINCIDE CON LA CONTRASEÑA
                while(!getListaBibliotecarios().get(i).getPassword().equals(passwordAntigua) && contador<5)
                {
                    System.out.println("La contraseña que has introducido no coincide.");
                    passwordAntigua = utilities.makeQuestion("Introduce tu contraseña antigua");
                    contador++;
                }

                if (contador==5)
                {
                    System.out.println("\n-----------NO TE QUEDAN MAS INTENTOS. VUELVE A INTENTARLO MAS TARDE---------------\n");
                    break;
                }

                if (getListaBibliotecarios().get(i).getPassword().equals(passwordAntigua)) {

                    String passwordNueva = utilities.makeQuestion("Introduce tu nueva contraseña");

                    setPasswordValida(false);
                    while (!isPasswordValida(passwordNueva)) {
                        System.out.println("Introduce una contraseña más segura que tenga mínimo 8 carácteres y contenga números, mayúsculas y minúsculas");
                        passwordNueva = utilities.makeQuestion("Introduce una contraseña");
                    }
                    getListaBibliotecarios().get(i).setPassword(passwordNueva);
                    System.out.println("\n----------------CONTRASEÑA CAMBIADA----------------\n");
                }
                else
                {
                    System.out.println("----------------- ERROR. USUARIO INCORRECTO -----------------.\n");
                }
            }
            else
            {
                System.out.println("----------------- ERROR. USUARIO INCORRECTO. -----------------\n");
            }


        }
    }

    /**
     *
     * @return muestra la lista de bibliotecarios con el formato toString().
     */
    public String mostrarBibliotecarios() {
        String personal = "";
        for (int i = 0; i < getListaBibliotecarios().size(); i++) {
            personal += getListaBibliotecarios().get(i).toString() + "\n";
        }
        return personal;
    }

}
