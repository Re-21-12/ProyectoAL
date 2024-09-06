

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Lenguaje {
    protected String[] tipos_datos = {"int", "bool", "string", "float"};
    protected String alfanumericos = "[a-z0-9]+";
    protected String[] operadores = {"=", "+", "-", "*", "/"};
    protected String letras = "[a-z]+";
    protected String numeros = "[0-9]+";
    protected String letrasyoperandos = "[a-z]+\\s*[+\\-*/]\\s*[a-z]+";
    protected String variableregex = "^%int\\s+[a-z]+\\s*=\\s*[a-z]+\\s*([+\\-*/]\\s*[a-z]+)*\\s*;$";
    protected String texto;
    protected ArrayList<String> declaraciones = new ArrayList<>();
    protected Stack<String> expresionPostfija = new Stack<String>();
    protected Nodo ArbolExpresion = new Nodo();
    protected Arbol arbol = new Arbol();
    protected Validacion validar = new Validacion();
    private boolean esValido;

    public void esUnLenguajeValido(ArrayList<Respuesta> declaraciones) {

        String resultado = operarAsignacion(extraerValoresDeclaraciones(declaraciones));
        System.out.println("El resultado de operar declaraciones con variables es: " + resultado);
    }

    public String operarAsignacion(String declaracion_resultado) {
        String[] lexemas = declaracion_resultado.split("=");
        String operacion = lexemas[1].trim();
        operacion = operacion.replace(";", "");
        operacion = operacion.replace(" ", "");
        System.out.println("Operación: " + operacion);
        String resultado = "";
        expresionPostfija = validar.conversionPostorden(operacion);
        ArbolExpresion = arbol.ArbolExpresion(expresionPostfija);
        resultado = validar.resultadoNotacionPolaca(ArbolExpresion);
        System.out.println("El resultado de evaluar la Notación Polaca es: " + resultado);
        declaracion_resultado = lexemas[0].trim() + " = " + resultado;
        return declaracion_resultado;
    }

    public String extraerValoresDeclaraciones(ArrayList<Respuesta> declaraciones) {
        StringBuilder variableRespuesta = new StringBuilder();
        String[] lexemas;

        // Construir la expresión con variables y operadores
        for (Respuesta declaracion : declaraciones) {
            if (declaracion.getLexema().matches(variableregex)) {
                System.out.println("Variable a reemplazar: " + declaracion.getLexema());
                variableRespuesta.append(declaracion.getLexema()).append(" ");
            }
        }

        String variableRespuestaStr = variableRespuesta.toString().trim();
        String asignacion;

        // Separar la expresión en partes antes y después del signo '='
        lexemas = variableRespuestaStr.split("=");
        if (lexemas.length < 2) {
            throw new IllegalArgumentException("La declaración no contiene un signo '=' para la asignación.");
        }

        asignacion = lexemas[1].trim();
        System.out.println("Asignacion: " + asignacion);

        // Reemplazar variables por sus valores en la expresión
        for (Respuesta declaracion : declaraciones) {
            if (asignacion.contains(declaracion.getNombre_variable())) {
                System.out.println("Variable a reemplazar: " + declaracion.getNombre_variable());
                asignacion = asignacion.replace(declaracion.getNombre_variable(), String.valueOf(declaracion.getResultado_entero()));
            }
        }

        variableRespuestaStr = lexemas[0].trim() + " = " + asignacion;

        // Retornar la expresión con las variables reemplazadas
        return variableRespuestaStr;
    }


    public Respuesta esUnaDeclaracionValida(String Lenguaje) {
        Respuesta respuesta = new Respuesta();
        respuesta.setLexema(Lenguaje);
        //en el caso de que venga un operador [* + -]
        System.out.println("Lenguaje: " + Lenguaje);
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
            System.out.println("Error: El valor no termina con ; " + Lenguaje);

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




