public  class Respuesta {
    private boolean bandera;
    private int resultado_entero;
    private float resultado_decimal;

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

    @Override
    public String toString() {
        return "Modelos.Datos.Respuesta{" +
                "bandera=" + bandera +
                ", resultado_entero=" + resultado_entero +
                ", resulado_decimal=" + resultado_decimal +
                '}';
    }
}
