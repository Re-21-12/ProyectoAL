
import java.util.ArrayList;
import java.util.Arrays;

//TODO:
// 1. Volver private las funciones en Lenguaje.java
// 2. Corregir cuando solo le mando un solo dato -> Crear una opcion para que pueda recibir un solo dato
// 3. Ver division
// 4. No se esta CONTEMPLANDO EL CASO DE ASIGNACION DE VARIABLE EN UNA DECLARACION YA EXISTENTE
public class Main {
    public static void main(String[] args) {
        ArrayList<String> declaraciones = new ArrayList<>();
        ArrayList<Respuesta> declaraciones_mapeadas = new ArrayList<>();
        //String Lenguaje_prueba1 = "%int variableuno = 1;";
        //String Lenguaje_prueba2 = "%int variabledos = 2;";
        //String respuesta = "%int respuesta = variableuno + variabledos;";



        String respuesta = "%int respuesta = 1 + 2;";

        //declaraciones.add(Lenguaje_prueba1);
        //declaraciones.add(Lenguaje_prueba2);
        declaraciones.add(respuesta);

        for (String declaracion : declaraciones) {

            declaraciones_mapeadas.add(new Respuesta().filtrarDatos(declaracion));
        }

       for(Respuesta declaracion:declaraciones_mapeadas) {
           System.out.println("Mapeada: "+ declaracion);
       }
        Lenguaje prueba = new Lenguaje();
        //Lenguaje Lenguaje1 = new Lenguaje(Lenguaje_prueba1);
        //Lenguaje Lenguaje2 = new Lenguaje(Lenguaje_prueba2);

        //Respuesta respuesta1 = prueba.esUnaDeclaracionValida(Lenguaje1.getTexto());
        //Respuesta respuesta2 = prueba.esUnaDeclaracionValida(Lenguaje2.getTexto());

        //declaraciones.add(Lenguaje_prueba1);
        //declaraciones.add(Lenguaje_prueba2);
        Lenguaje resultado = new Lenguaje(declaraciones);
   /*
    String res = resultado.extraerValoresDeclaraciones(declaraciones_mapeadas);
   System.out.println();
    resultado.operarAsignacion(res);
            System.out.println(res);
*/
            resultado.esUnLenguajeValido(declaraciones_mapeadas);
    }
}