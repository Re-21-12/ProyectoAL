import java.util.Arrays;

public class Respuesta {
    private boolean bandera;
    private int resultado_entero;
    private float resultado_decimal;
    private String tipo_dato;
    private String lexema;
    private String nombre_variable;
    private boolean esAritmeticoVariable;
    private String variables_enteras;
    private String variables_decimales;

    //Constructores
    public Respuesta() {

    }

    public Respuesta(boolean bandera) {
        this.bandera = bandera;
    }

    public Respuesta(boolean bandera, float resultado_decimal) {
        this.bandera = bandera;
        this.resultado_decimal = resultado_decimal;
    }

    public Respuesta(boolean bandera, int resultado_entero) {
        this.bandera = bandera;
        this.resultado_entero = resultado_entero;
    }

    public Respuesta(boolean bandera, String tipo_dato) {
        this.bandera = bandera;
        this.tipo_dato = tipo_dato;
    }

    public Respuesta(boolean bandera, String tipo_dato, int resultado_entero) {
        this.bandera = bandera;
        this.tipo_dato = tipo_dato;
        this.resultado_entero = resultado_entero;
    }

    public Respuesta(boolean bandera, String tipo_dato, float resultado_decimal) {
        this.bandera = bandera;
        this.tipo_dato = tipo_dato;
        this.resultado_decimal = resultado_decimal;
    }

    //%int variableuno = 1;";
    public Respuesta filtrarDatos(String lexema) {
        this.lexema = lexema;
        String[] lexemas;
        String[] variableyasignacion = lexema.split("=");
        String nombre_variable;
        int valor;
        float valor_decimal;
        variableyasignacion[1] = variableyasignacion[1].replace(";", "");
        variableyasignacion[1] = variableyasignacion[1].trim();
        lexemas = variableyasignacion[0].replace("%", "").split(" ");
        this.tipo_dato = lexemas[0];
        nombre_variable = lexemas[1];
        if(lexemas[1].matches("[a-z]+")){
            this.nombre_variable = nombre_variable;
        }
        if (variableyasignacion[1].matches("[0-9]+")) {
            valor = Integer.parseInt(variableyasignacion[1].trim());
            this.resultado_entero = valor;
            System.out.println(valor);
        }
        if (variableyasignacion[1].matches("[0-9]+.[0-9]+")) {
            valor_decimal = Integer.parseInt(variableyasignacion[1].trim());
            this.resultado_decimal = valor_decimal;
            System.out.println(valor_decimal);
        }

        if (variableyasignacion[1].matches("[a-z]+\\s*[+\\-*/]\\s*[a-z]+") && tipo_dato.equals("int")) {
            this.esAritmeticoVariable = true;
            this.variables_enteras = variableyasignacion[1];
            System.out.println(variableyasignacion[1]);
        }

        if (variableyasignacion[1].matches("[a-z]+\\s*[+\\-*/]\\s*[a-z]+") && tipo_dato.equals("float")) {
            this.esAritmeticoVariable = true;
            this.variables_decimales = variableyasignacion[1];
            System.out.println(variableyasignacion[1]);
        }


        //String no tiene
        return Respuesta.this;
    }

    public String getTipo_dato() {
        return tipo_dato;
    }

    public void setTipo_dato(String tipo_dato) {
        this.tipo_dato = tipo_dato;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

//gettters setters

    public float getResultado_decimal() {
        return resultado_decimal;
    }

    public void setResultado_decimal(float resultado_decimal) {
        this.resultado_decimal = resultado_decimal;
    }

    public int getResultado_entero() {
        return resultado_entero;
    }

    public void setResultado_entero(int resultado_entero) {
        this.resultado_entero = resultado_entero;
    }

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public String getNombre_variable() {
        return nombre_variable;
    }

    public void setNombre_variable(String nombre_variable) {
        this.nombre_variable = nombre_variable;
    }

    public boolean isEsAritmeticoVariable() {
        return esAritmeticoVariable;
    }

    public void setEsAritmeticoVariable(boolean esAritmeticoVariable) {
        this.esAritmeticoVariable = esAritmeticoVariable;
    }

    public String getVariables_enteras() {
        return variables_enteras;
    }

    public void setVariables_enteras(String variables_enteras) {
        this.variables_enteras = variables_enteras;
    }

    public String getVariables_decimales() {
        return variables_decimales;
    }

    public void setVariables_decimales(String variables_decimales) {
        this.variables_decimales = variables_decimales;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "bandera=" + bandera + ", resultado_entero=" + resultado_entero + ", resultado_decimal=" + resultado_decimal + ", tipo_dato='" + tipo_dato + '\'' + ", lexema='" + lexema + '\'' + ", nombre_variable='" + nombre_variable + '\'' + ", esAritmeticoVariable=" + esAritmeticoVariable + ", variables_enteras='" + variables_enteras + '\'' + ", variables_decimales='" + variables_decimales + '\'' + '}';
    }
}
