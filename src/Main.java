
import java.util.ArrayList;
import java.util.Arrays;

//TODO:
// 1. Volver private las funciones en Lenguaje.java

public class Main {
    public static void main(String[] args) {
        //ArrayList<String> declaraciones = new ArrayList<>();
        String Lenguaje_prueba1 = "%int variableuno = 1;";
        String Lenguaje_prueba2 = "%int variabledos = 2;";
        String Lenguaje_prueba3 = "%int variabledos = 2; %int variableuno = 1; %int respuesta = variableuno + variabledos;";
        String respuesta = "%int respuesta = variableuno + variabledos;";

        Lenguaje prueba = new Lenguaje();
        Lenguaje Lenguaje1 = new Lenguaje(Lenguaje_prueba1);
        Lenguaje Lenguaje2 = new Lenguaje(Lenguaje_prueba2);

        Respuesta respuesta1 = prueba.esUnaDeclaracionValida(Lenguaje1.getTexto());
        Respuesta respuesta2 = prueba.esUnaDeclaracionValida(Lenguaje2.getTexto());
        //declaraciones.add(Lenguaje_prueba1);
        //declaraciones.add(Lenguaje_prueba2);
        //Lenguaje resultado = new Lenguaje(declaraciones);
        String res =  prueba.elLenguajeEsValido(Lenguaje_prueba3);
            System.out.println(res);

    }
}