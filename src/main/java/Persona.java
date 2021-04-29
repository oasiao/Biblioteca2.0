import static java.lang.Integer.parseInt;

public abstract class Persona {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;
    private String type;

    //constructor vacío
    public Persona(){}

    //constructor con parametros
    public Persona(String nombre, String apellido1, String apellido2, int edad,String type) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
        this.type=type;
    }

    //TODO CONSTRUCTOR COPIA
    //TODO TOSTRING PERSONA


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //MÉTODOS
    public void solicitarDatosPersona(){
        //INSTANCIAMOS LAS CLASES NECESARIAS
        Utilities utilities = new Utilities();
        Usuario usuario = new Usuario();
        Bibliotecario bibliotecario = new Bibliotecario();

        String persona = "";

        //SOLICITAMOS LOS DATOS QUE TENGAN EN COMÚN BIBLIOTECARIO Y USUARIO
        //NOMBRE
        String nombre = utilities.makeQuestion("Ingresa el nombre");

        //APELLIDOS
        String apellido1 = utilities.makeQuestion("Ingresa tu primer apellido");
        String apellido2 = utilities.makeQuestion("Ingresa tu segundo apellido");

        //EDAD
        int edad = parseInt(utilities.makeQuestion("Ingresa edad"));
        String type;

        //Los int's los pasamos a string para poder hacer el control
        String edadToString = Integer.toString(edad);

        if (!nombre.isEmpty() && !apellido1.isEmpty() && !apellido2.isEmpty() && !edadToString.isEmpty()) //CONTROL DE DATOS INTRODUCIDOS Y VACÍOS
        {
            do {
                persona = utilities.makeQuestion("Usuario (U) o Bibliotecario (B)").toUpperCase();
                if (persona.equals("U")) {
                    type="USUARIO";
                    usuario.solicitarDatosUsuario(nombre, apellido1, apellido2, edad, type);
                } else if (persona.equals("B")) {
                    type="BIBLIOTECARIO";
                    bibliotecario.solicitarDatosBibliotecario(nombre, apellido1, apellido2, edad, type);
                } else {
                    System.out.println("ERROR. Usuario (U) o (B)");
                }
            }
            while (!persona.equals("B") || !persona.equals("U")); //Si es diferente de bibliotecario (B) o usuario (U), volverá a preguntar: ¿usuario o bibliotecario?

        } else { //si no se cumple el control, entonces mostrará que ha habido un error
            System.out.println("¡ERROR! Vuelve a intentarlo");
            solicitarDatosPersona();
            //TODO MENU: en el menú, preguntar si quiere salir o volver a introducir los datos.
        }
    }
}
