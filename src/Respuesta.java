public  class Respuesta {
    private boolean bandera;
    private int resultado_entero;
    private float resultado_decimal;
    private String tipo_dato;
    private String lexema;
    private String nombre_variable;
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
    public Respuesta(boolean bandera, int resultado_entero ) {
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

    @Override
    public String toString() {
        return "Respuesta{" +
                "bandera=" + bandera +
                ", resultado_entero=" + resultado_entero +
                ", resultado_decimal=" + resultado_decimal +
                ", tipo_dato='" + tipo_dato + '\'' +
                ", lexema='" + lexema + '\'' +
                ", nombre_variable='" + nombre_variable + '\'' +
                '}';
    }
}
