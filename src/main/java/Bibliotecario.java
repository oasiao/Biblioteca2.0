import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Bibliotecario extends Persona {
    private String puestoTrabajo;
    private String NIF;
    private String contraseña;

    //Creamos una nueva lista, solo para bibliotecarios
    private static List<Bibliotecario> listaBibliotecarios = new ArrayList<>();

    //Creamos un boolean login
    private static boolean login;

    //constructor vacio
    public Bibliotecario() {
    }

    //constructor parametros
    public Bibliotecario(String nombre, String apellido1, String apellido2, int edad,String type, String puestoTrabajo, String NIF, String contraseña) {
        super(nombre, apellido1, apellido2, edad,type);
        this.puestoTrabajo = puestoTrabajo;
        this.NIF = NIF;
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "------------------------\n"
                + "\nNIF: " + NIF
                + "\nPuesto: " + puestoTrabajo
                + "\nContraseña: " + contraseña
                + "\nTipo: " + super.getType()
                + "\n-------------------------\n";
    }

    //TODO CONSTRUCTOR COPIA

    //GETTERS AND SETTERS
    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public static List<Bibliotecario> getListaBibliotecarios() {
        return listaBibliotecarios;
    }

    public void setListaBibliotecarios(List<Bibliotecario> listaBibliotecarios) {
        this.listaBibliotecarios = listaBibliotecarios;
    }

    public static boolean getLogin() {
        return login;
    }

    public static void setLogin(boolean login) {
        boolean loginDefecto = false;
        Bibliotecario.login = loginDefecto;
        if (!login) {
            Bibliotecario.login = loginDefecto;
        } else {
            Bibliotecario.login = login;
        }
    }


    //MÉTODOS
    public void solicitarDatosBibliotecario(String nombre, String apellido1, String apellido2, int edad, String type) {
        //INSTANCIAMOS
        Biblioteca biblioteca = new Biblioteca();
        Utilities utilities = new Utilities();

        //SEGUIMOS SOLICITANDO DATOS
        //Puesto de trabajo
        String puestoTrabajo = utilities.makeQuestion("Introduce tu puesto de trabajo");

        //Nif
        String NIF = utilities.makeQuestion("Introduce tu NIF");

        //Contraseña
        String contraseña = utilities.makeQuestion("Introduce tu contraseña");


        //CONTROL DE DATOS
        if (!puestoTrabajo.isEmpty() && !NIF.isEmpty() && !contraseña.isEmpty()) {
            //Añade el bibliotecario a la lista de Personas <usuario,bibliotecario>
            Biblioteca.getPersonas().add(new Bibliotecario(nombre, apellido1, apellido2, edad, type, puestoTrabajo, NIF, contraseña));

            //Añade el bibliotecario a la lista que hemos creado en esta clase
            getListaBibliotecarios().add(new Bibliotecario(nombre, apellido1, apellido2, edad, type, puestoTrabajo, NIF, contraseña));

            System.out.println("¡Bibliotecario registrado!");

            //el bibliotecario puede añadir nuevo personal, por tanto, si ya ha hecho log in, volverá al menu sino, tendrá que iniciar sesión
            if (getLogin()) {
                App.menuBibliotecario();
            } else {
                logInOrRegister();
            }

            //SI NO CUMPLE CON EL CONTROL DE DATOS, ENTONCES, MOSTRARÁ EL MENSAJE ¡ERROR!
        } else {
            System.out.println("ERROR. Vuelve a introducir los datos");
            solicitarDatosBibliotecario(nombre, apellido1, apellido2, edad, type);
        }
    }

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
                        App.menuUsuario();
                        break;
                    default:
                        System.out.println("ERROR");
                        break;
                }
                break;

                //SALIR
            case 3:
                System.exit(0);
                break;

            default:
                System.out.println("ERROR");
                logInOrRegister(); //VOLVEMOS AL MENU
                break;
        }
    }

    public void logInBibliotecario() {
        Utilities utilities = new Utilities();
        String NIF = utilities.makeQuestion("Introduce usuario (NIF)");
        String contraseña = utilities.makeQuestion("Introduce tu contraseña");

        for (int i = 0; i < getListaBibliotecarios().size(); i++) {
            if (getListaBibliotecarios().get(i).toString().contains(NIF) &&
                    getListaBibliotecarios().get(i).toString().contains(contraseña)) {
                setLogin(true);
                System.out.println("\n Bienvenido, " + getListaBibliotecarios().get(i).getNombre() + ".\n");
                App.menuBibliotecario();
            } else {
                System.out.println("ERROR. Usuario incorrecto.\n");
                logInOrRegister();
            }
        }
    }

    public void añadirPersonal() {
        super.solicitarDatosPersona();
    }

    public void eliminarPersonal() {
        Utilities utilities = new Utilities();
        String opcion = utilities.makeQuestion("Introduce el NIF");

        for (int i = 0; i < Biblioteca.getPersonas().size(); i++) {
            if (Biblioteca.getPersonas().get(i).toString().contains(opcion)) {
                Biblioteca.getPersonas().remove(i);

                //LO BORRAMOS EN LA LISTA CREADA SOLO PARA BIBLIOTECARIOS
                for (int j = 0; j < getListaBibliotecarios().size(); j++) {
                    if(getListaBibliotecarios().get(j).getNIF().equals(opcion)){
                        getListaBibliotecarios().remove(j);
                    }
                }
                System.out.println("-------------------ELIMINADO CON ÉXITO------------------");
                break;
            } else {
                if(i==Biblioteca.getPersonas().size()-1 &&!Biblioteca.getPersonas().get(i).toString().contains(opcion)) {
                    System.out.println("El elemento no existe");
                }
            }
        }
    }

    public void logOut() {
        setLogin(false);
    }


    //TODO si cambia en la lista de Bibliotecarios, tambien cambia en la lista de personas

    public void cambiarContraseñaBibliotecario(){
        //INSTANCIAMOS
        Utilities utilities = new Utilities();
        Usuario usuario = new Usuario();
        Bibliotecario bibliotecario = new Bibliotecario();

        //SOLICITAMOS LOS DATOS
        String NIF = utilities.makeQuestion("Introduce tu NIF");

        //Recorremos la lista
        for (int i = 0; i < getListaBibliotecarios().size(); i++) {
            if (getListaBibliotecarios().get(i).getNIF().equals(NIF)) {
                String contraseñaAntigua=utilities.makeQuestion("Introduce tu contraseña antigua");
                if (getListaBibliotecarios().get(i).getContraseña().equals(contraseñaAntigua)) {
                    String contraseñaNueva = utilities.makeQuestion("Introduce tu nueva contraseña");
                    getListaBibliotecarios().get(i).setContraseña(contraseñaNueva);
                    System.out.println("----------------CONTRASEÑA CAMBIADA----------------\n");
                }
            } else {
                System.out.println("ERROR. Usuario incorrecto.\n");
            }
        }
    }
}
