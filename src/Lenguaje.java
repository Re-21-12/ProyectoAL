

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

    public String elLenguajeEsValido(String texto) {
        ArrayList<Respuesta> declaraciones = new ArrayList<>();
        ArrayList<String> Lenguaje = new ArrayList<>(Arrays.asList(texto.split("(?<=;)")));
        for (String lenguaje : Lenguaje) {
            declaraciones.add(esUnaDeclaracionValida(lenguaje));
        }
        String nuevadeclaracion = operandoVariablesEnDeclaraciones(declaraciones, texto);
        return nuevadeclaracion;
    }
    // Le deberia mandar un ArrayList<Respuesta> declaraciones con un contenido similar a "%int variableuno = 1;"
    public String operandoVariablesEnDeclaraciones(ArrayList<Respuesta> declaraciones, String texto) {
        ArrayList<Integer> valores_enteros = sonVariablesEnterasValidas(declaraciones);
        //me devolvera: %int suma = 1 + 2;
        return asignarValoresaResultado(valores_enteros, texto);
    }
    private String asignarValoresaResultado(ArrayList<Integer> valores_enteros, String variable_operacion) {
        // Eliminar el punto y coma al final de la cadena
        variable_operacion = variable_operacion.replace(";", "");
        String[] lexemas = variable_operacion.split(" ");

        // Inicializar un StringBuilder para construir el resultado
        StringBuilder resultado = new StringBuilder();

        // Agregar la parte inicial de la declaración (antes del signo '=')
        for (int i = 0; i < 3; i++) {
            resultado.append(lexemas[i]).append(" ");
        }

        // Inicializar un contador para los valores enteros
        int valorIndex = 0;

        // Procesar la parte después del signo '='
        for (int i = 3; i < lexemas.length; i++) {
            if (lexemas[i].matches(letras)) {
                // Reemplazar los nombres de variables con valores de valores_enteros
                resultado.append(valores_enteros.get(valorIndex++)).append(" ");
            } else {
                // Agregar operadores tal como están
                resultado.append(lexemas[i]).append(" ");
            }
        }

        // Convertir el StringBuilder a String y devolver
        return resultado.toString().trim();
    }
    public ArrayList<Integer> sonVariablesEnterasValidas(ArrayList<Respuesta> variables) {
        ArrayList<Integer> valores_enteros = new ArrayList<>();
        ArrayList<String> lexemas = new ArrayList<>();
        //mapeamos los lexemas
        for(Respuesta respuesta : variables){
            lexemas.add(respuesta.getLexema());
        }
        //verificamos que sea un entero
        for (String Lenguaje : lexemas) {
            //Si es un valor valido y es un valor entero entonces
            if (esUnaDeclaracionValida(texto).isBandera() && String.valueOf(esUnaDeclaracionValida(texto).getResultado_entero()).matches(numeros)) {

                valores_enteros.add(esUnaDeclaracionValida(texto).getResultado_entero());
            }
        }
        return valores_enteros;
    }



    //
    public Respuesta esUnaDeclaracionValida(String Lenguaje) {
        Respuesta respuesta = new Respuesta();
        respuesta.setLexema(Lenguaje);
        //en el caso de que venga un operador [* + -]
        Lenguaje = Lenguaje.trim();
        String[] lexemas = Lenguaje.split(" ");
        String tipo_dato;
        String nombre_variable = lexemas[1];
        //[2] es operador =
        String valor = lexemas[3];
        //Validar el primer token
        if (!Lenguaje.startsWith("%")) {
            System.out.println("Error: Lenguaje debe empezar con %");
            respuesta.setBandera(false);
            return respuesta;
        }

        //Eliminamos espacios vacios
        if (Lenguaje.isBlank() || Lenguaje.isEmpty()) {
            System.out.println("Error: Lenguaje está vacío o en blanco");
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
        if (esUnValorValido(tipo_dato, valor).isBandera() && valor.matches(numeros)) {
            respuesta.setBandera(true);
            respuesta.setResultado_entero(Integer.parseInt(valor));
            respuesta.setTipo_dato(tipo_dato);
            // System.out.println("La respuesta es: "+ respuesta);
            return respuesta;
        }
        //En el caso de que no sea un entero necesariamente
        respuesta.setBandera(true);
        return respuesta;
    }

    private Respuesta esUnValorValido(String tipo_dato, String valor) {
        Respuesta respuesta = new Respuesta(false);
        //Si el tipo de dato es int
        if (tipo_dato.equalsIgnoreCase(tipos_datos[0])) {
            //Si el valor no es un número despues de leer su declaracion
            if (!valor.matches(numeros)) {
                System.out.println("Error: El valor no es un número válido");
                return respuesta;
            }
            //Si lo es ->
            respuesta.setBandera(true);
            respuesta.setTipo_dato(tipo_dato);
            respuesta.setResultado_entero(Integer.parseInt(valor));
            return respuesta;
        }
        //si el tipo de dato es bool
        if (tipo_dato.equalsIgnoreCase(tipos_datos[1])) {
            if (!valor.equals("true") && !valor.equals("false")) {
                System.out.println("Error: El valor no es un booleano válido");
                return respuesta;
            }
        }
        //si el tipo de dato es string valida que sea alfanumerico
        if (tipo_dato.equalsIgnoreCase(tipos_datos[2])) {
            if (!valor.matches(alfanumericos)) {
                System.out.println("Error: El valor no es alfanumérico válido");
                return respuesta;
            }

        }
        respuesta.setBandera(true);
        respuesta.setTipo_dato(tipo_dato);
        //En el caso de que no sea int pero si cualaquier tipo de dato
        System.out.println("El valor es válido");
        return respuesta;
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




