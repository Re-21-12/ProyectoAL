
import java.util.ArrayList;
import java.util.Arrays;

//TODO:
// 1.
public class Main {
    public static void main(String[] args) {
        //ArrayList<String> declaraciones = new ArrayList<>();
        String Lenguaje_prueba1 = "%int variableuno = 1;";
        String Lenguaje_prueba2 = "%int variabledos = 2;";
        String Lenguaje_prueba3 = "%int variabledos = 2; %int variableuno = 1;";
        String respuesta = "%int respuesta = variableuno + variabledos;";

        Lenguaje prueba = new Lenguaje();
        Lenguaje Lenguaje1 = new Lenguaje(Lenguaje_prueba1);
        Lenguaje Lenguaje2 = new Lenguaje(Lenguaje_prueba2);

        Respuesta respuesta1 = prueba.esUnaDeclaracionValida(Lenguaje1.getTexto());
        Respuesta respuesta2 = prueba.esUnaDeclaracionValida(Lenguaje2.getTexto());
        prueba.elLenguajeEsValido(Lenguaje_prueba3);
        //declaraciones.add(Lenguaje_prueba1);
        //declaraciones.add(Lenguaje_prueba2);
        //Lenguaje resultado = new Lenguaje(declaraciones);

        if (respuesta1.isBandera() && respuesta2.isBandera()) {
            System.out.println("Se puede realizar la operacion");
        } else {
            System.out.println("No se puede realizar la operacion");
        }

    }
}