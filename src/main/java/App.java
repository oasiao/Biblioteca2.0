import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class App {

    public static void main(String[] args) {
        Bibliotecario bibliotecario = new Bibliotecario();
        Utilities utilities = new Utilities();
        /*Crea un menú (con switch), que permita gestionar la biblioteca dando soporte a todos los métodos
        que se han solicitado anteriormente. Puede ser interesante que haya varios menús, uno para gestionar
        el personal de la biblioteca y otro para gestionar las reservas.*/

        //añadimos un usuario por defecto que será el administrador del sistema
        Biblioteca.getPersonas().add(new Bibliotecario("root", "root", "root", 0, "BIBLIOTECARIO",
                "ADMINISTRADOR", "00000000A", "rootroot"));

        Bibliotecario.getListaBibliotecarios().add(new Bibliotecario("root", "root", "root", 0, "BIBLIOTECARIO",
                "ADMINISTRADOR", "00000000A", "rootroot"));

        //ENTRAMOS AL PRIMER MENÚ
        bibliotecario.logInOrRegister();

    }

    public static void menuBibliotecario() {
        /**
         * Instanciamos las clases necesarias para importar funciones
         */
        Libro libro = new Libro();
        Biblioteca biblioteca = new Biblioteca();
        Utilities utilities = new Utilities();
        Usuario usuario = new Usuario();
        Reserva reserva = new Reserva();
        Bibliotecario bibliotecario = new Bibliotecario();

        //OPCIONES MENU BIBLIOTECARIO
        System.out.println("----------------MENÚ BIBLIOTECARIO----------------");
        System.out.println("1. Gestionar libros");
        System.out.println("2. Visualizar biblioteca");
        System.out.println("3. Gestionar personal");
        System.out.println("4. Cambiar contraseña");
        System.out.println("5. Cerrar Sesión");
        System.out.println("6. Salir");
        System.out.println("------------------------------------------------\n");

        int menu = parseInt(utilities.makeQuestion("Escoge una opción"));
        int confirm;

        /**
         * Elegimos añadir, eliminar,  buscar libros o reservar
         */
        switch (menu) {
            //OPCIONES GESTIONAR LIBRO
            case 1:
                System.out.println("---------------GESTIONAR LIBROS----------------\n");
                System.out.println("1. Añadir libro");
                System.out.println("2. Eliminar libro");
                System.out.println("3. Buscar libro(ISBN)");
                System.out.println("4. Buscar libro(título)");
                System.out.println("5. Reservar libro");
                System.out.println("6. Devolver libro");
                System.out.println("7. Salir");
                System.out.println("------------------------------------------------\n");

                try {
                    int opcionMenu1 = parseInt(utilities.makeQuestion("Escoge una opción"));

                    //MENU
                    switch (opcionMenu1) {
                        case 1:
                            do {
                                libro.añadirLibro();
                                confirm = parseInt(utilities.makeQuestion("Quieres añadir otro libro? Si(1) / No (0)"));
                            }
                            while (confirm == 1);
                            break;
                        case 2:
                            do {
                                libro.eliminarLibro();
                                confirm = parseInt(utilities.makeQuestion("Quieres eliminar otro libro? Si(1) / No (0)"));
                            }
                            while (confirm == 1);
                            break;
                        case 3:
                            do {
                                libro.buscarIsbn();
                                confirm = parseInt(utilities.makeQuestion("Quieres buscar otro libro? Si(1) / No (0)"));
                            }
                            while (confirm == 1);
                            break;
                        case 4:
                            do {
                                libro.buscarTitulo();
                                confirm = parseInt(utilities.makeQuestion("Quieres buscar otro libro? Si(1) / No (0)"));
                            }
                            while (confirm == 1);
                            break;
                        case 5:
                            reserva.reservarLibro();
                            break;
                        case 6:
                            reserva.devolverLibro();
                            break;
                        case 7:
                            App.menuBibliotecario();
                            break;
                    }
                    App.menuBibliotecario();
                } catch (InputMismatchException e) {
                    System.out.println("El caracter introducido no es válido");
                }
                break;

            /**
             * VISUALIZAR BIBLIOTECA
             */
            case 2:
                System.out.println("---------------VISUALIZAR BIBLIOTECA----------------\n");
                System.out.println("Escoge una opción: ");
                System.out.println("1. Mostrar lista de libros");
                System.out.println("2. Mostrar libros disponibles");
                System.out.println("3. Mostrar lista de reservas");
                System.out.println("4. Salir");
                System.out.println("----------------------------------------------------\n");

                try {
                    int opcionMenu2 = parseInt(utilities.makeQuestion("Escoge una opción"));

                    switch (opcionMenu2) {

                        //MOSTRAR LISTA DE LIBROS
                        case 1:
                            System.out.println(biblioteca.mostrarLibros());
                            break;

                            //MOSTRAR LISTA DE LIBROS DISPONIBLES
                        case 2:
                            System.out.println(biblioteca.mostrarLibrosDisponibles());;
                            break;

                            //MOSTRAR LISTA DE RESERVAS
                        case 3:
                            System.out.println(reserva.mostrarReservas());
                            break;

                            //SALIR
                        case 4:
                            App.menuBibliotecario();
                            break;
                    }
                    App.menuBibliotecario();
                } catch (InputMismatchException e) {
                    System.out.println("El valor introducido no es correcto");
                }
                break;

            /**
             * Escogemos entre añadir, eliminar, modificar, listar el personal de la bilioteca o salir.
             */

            //GESTIONAR PERSONAL
            case 3:
                System.out.println("---------------GESTIONAR PERSONAL----------------\n");
                System.out.println("Escoge una opción: ");
                System.out.println("1. Añadir personal");
                System.out.println("2. Eliminar personal");
                System.out.println("3. Listar personal de la biblioteca");
                System.out.println("4. Listar usuarios registrados");
                System.out.println("5. Listar personal absoluto");
                System.out.println("6. Salir");
                System.out.println("----------------------------------------------------\n");

                try {
                    int opcionMenu3 = parseInt(utilities.makeQuestion("Escoge una opción"));

                    switch (opcionMenu3) {
                        case 1:
                            do {
                                bibliotecario.añadirPersonal();
                                confirm = parseInt(utilities.makeQuestion("Quieres añadir más personal? Si(1) / No (0)"));
                            }
                            while (confirm == 1);
                            break;
                        case 2:
                            do {
                                bibliotecario.eliminarPersonal();
                                confirm = parseInt(utilities.makeQuestion("Quieres eliminar más personal? Si(1) / No (0)"));
                            }
                            while (confirm == 1);
                            break;
                        case 3:
                            System.out.println(bibliotecario.mostrarBibliotecarios());
                            break;
                        case 4:
                            System.out.println(usuario.mostrarUsuarios());
                            break;
                        case 5:
                            System.out.println(biblioteca.mostrarPersonas());
                            break;
                        case 6:
                            App.menuBibliotecario();
                            break;
                    }
                    App.menuBibliotecario();
                } catch (InputMismatchException e) {
                    System.out.println("El carácter introducido no es válido");
                }
                break;

            //CAMBIAR CONTRASEÑA
            case 4:
                bibliotecario.cambiarContraseñaBibliotecario();
                App.menuBibliotecario();
                break;

            //CERRAR SESÓN CON LA OPCIÓN DE VOLVER A INICIAR SESIÓN
            case 5:
                bibliotecario.logOut();
                System.out.println("--------------------SESIÓN CERRADA-----------------------\n");
                bibliotecario.logInOrRegister();
                break;
            //SALIR
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("No ha introducido una opción válida.");
                App.menuBibliotecario();
                break;
        }
    }

    public static void menuUsuario() {

        //INSTANCIAMOS
        Libro libro = new Libro();
        Biblioteca biblioteca = new Biblioteca();
        Utilities utilities = new Utilities();
        Usuario usuario = new Usuario();
        Bibliotecario bibliotecario = new Bibliotecario();

        //OPCIONES A MENU USUARIO
        System.out.println("----------------MENÚ USUARIO----------------");
        System.out.println("1. Buscar libro por titulo");
        System.out.println("2. Cambiar correo electrónico");
        System.out.println("3. Cerrar Sesión");
        System.out.println("4. Salir");
        System.out.println("--------------------------------------------\n");

        //ESCOGE UNA OPCIÓN
        int menu = parseInt(utilities.makeQuestion("Escoge una opción"));
        int confirm;

        //MENU
        switch (menu) {

            //BUSCAR LIBRO
            case 1:
                do {
                    libro.buscarTitulo();
                    confirm = parseInt(utilities.makeQuestion("Quieres buscar otro libro? Si(1) / No (0)"));
                }
                while (confirm == 1);
                App.menuUsuario();
                break;


            case 2:
                usuario.cambiarContraseñaUsuario();
                App.menuUsuario();
                break;

             //CERRAR SESIÓN
            case 3:
                bibliotecario.logOut();
                bibliotecario.logInOrRegister();
                break;

            //SALIR
            case 4:
                System.exit(0);
                break;
        }

    }

}

