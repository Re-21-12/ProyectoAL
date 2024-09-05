

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Lenguaje {
    protected String[] tipos_datos = {"int", "bool", "string", "float"};
    protected String alfanumericos = "[a-z0-9]+";
    protected String[] operadores = {"=", "+", "-", "*", "/"};
    protected String letras = "[a-z]+";
    protected String numeros = "[0-9]+";

    protected String texto;
    protected ArrayList<String> declaraciones = new ArrayList<>();
    protected Stack<String> expresionPostfija = new Stack<String>();
   /*
    protected Nodo ArbolExpresion = new Nodo();
    protected Arbol arbol = new Arbol();
*/
    private boolean esValido;



    public ArrayList<Integer> sonVariablesEnterasValidas(ArrayList<String> variables) {
        ArrayList<Integer> valores_enteros = new ArrayList<>();
        for (String Lenguaje : variables) {
            //Si es un valor valido y es un valor entero entonces
            if (esUnaDeclaracionValida(texto).isBandera() && String.valueOf(esUnaDeclaracionValida(texto).getResultado_entero()).matches(numeros)) {

                valores_enteros.add(esUnaDeclaracionValida(texto).getResultado_entero());
            }
        }
        return valores_enteros;
    }

    public Respuesta esUnaDeclaracionValida(String Lenguaje) {
        Respuesta respuesta = new Respuesta();
        //en el caso de que venga un operador [* + -]
        Lenguaje = Lenguaje.trim();
        String[] lexemas = Lenguaje.split(" ");
        String tipo_dato;
        String nombre_variable = lexemas[1];
        //[2] es operador =
        String valor = lexemas[3];
        //Validar el primer token
        if (!Lenguaje.startsWith("%")) {
            System.out.println("Error: El Modelos.Datos.Lenguaje debe empezar con %");
            respuesta.setBandera(false);
            return respuesta;
        }

        //Eliminamos espacios vacios
        if (Lenguaje.isBlank() || Lenguaje.isEmpty()) {
            System.out.println("Error: El Modelos.Datos.Lenguaje está vacío o en blanco");
            respuesta.setBandera(false);
            return respuesta;
        }

        //Validar que el primer lexema sea un tipo de dato
        tipo_dato = lexemas[0].replace("%", "");
        if (!Arrays.asList(tipos_datos).contains(tipo_dato)) {
            System.out.println("Error: El primer lexema no es un tipo de dato válido");
            respuesta.setBandera(false);
            return respuesta;
        }

        if (!nombre_variable.matches(letras)) {
            System.out.println("Error: El nombre de la variable no contiene solo letras minúsculas");
            respuesta.setBandera(false);
            return respuesta;
        }

        //validar que es un operador de declaracion
        if (!lexemas[2].equalsIgnoreCase(operadores[0])) {
            System.out.println("Error: No existe una declaracion de asignacion con = ");
            respuesta.setBandera(false);
            return respuesta;
        }

        //Validar que termine con ;
        if (!valor.endsWith(";")) {
            System.out.println("Error: El valor no termina con ;");
            respuesta.setBandera(false);
            return respuesta;
        }

        //Eliminamos el ; para validar que el dato asignado sea correcto
        valor = valor.substring(0, valor.length() - 1);

        //validar que el valor sea valido segun el tipo de dato entero
        if (esUnValorValido(tipo_dato, valor) && valor.matches(numeros)) {
            respuesta.setBandera(true);
            respuesta.setResultado_entero(Integer.parseInt(valor));
            System.out.println(respuesta.getResultado_entero());
            return respuesta;
        }
        //En el caso de que no sea un entero necesariamente
        respuesta.setBandera(esUnValorValido(tipo_dato, valor));
        return respuesta;
    }

    private boolean esUnValorValido(String tipo_dato, String valor) {
        //Si el tipo de dato es int
        if (tipo_dato.equalsIgnoreCase(tipos_datos[0])) {
            if (!valor.matches(numeros)) {
                System.out.println("Error: El valor no es un número válido");
                return false;
            }
        }
        //si el tipo de dato es bool
        if (tipo_dato.equalsIgnoreCase(tipos_datos[1])) {
            if (!valor.equals("true") && !valor.equals("false")) {
                System.out.println("Error: El valor no es un booleano válido");
                return false;
            }
        }
        //si el tipo de dato es string valida que sea alfanumerico
        if (tipo_dato.equalsIgnoreCase(tipos_datos[2])) {
            if (!valor.matches(alfanumericos)) {
                System.out.println("Error: El valor no es alfanumérico válido");
                return false;
            }
        }

        return true;
    }
    //Constructores
    public Lenguaje() {
    }

    public Lenguaje(String texto) {
        this.texto = texto;
    }

    public Lenguaje(ArrayList<String> declaraciones) {
        this.declaraciones = declaraciones;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}




