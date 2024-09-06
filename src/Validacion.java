/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author victo
 */
public class Validacion {

    //private char[] permitidos = {'(', ')', '√', '^', '*', '/', '+', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private String[] permitidos = {"(", ")", "#", "^", "*", "/", "+", "-", "-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "9", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",};

    //asigna valores a las variables ingresadas
    public String colocarVariables(String expresion, String caracter, String valor) {
        //instanciamos la expresion nueva que vamos a retornar
        String expresionNueva = "";
        //recorremos
        for (int i = 0; i < expresion.length(); i++) {
            //si el caracter coincide coon la variable
            if (expresion.charAt(i) == caracter.charAt(0)) {
                //el valor que le pasamos lo sustituimoos
                expresionNueva += valor;
            } else {
                //si no coincide seguimoos armando la expresion
                expresionNueva += expresion.charAt(i);
            }
        }
        System.out.println("expresion nueuva: " + expresionNueva);
        return expresionNueva;
    }
    //aqui revisa las variables ingresadas

    //se encarga de validar las vas variables
    public int verificarVariables(String expresion) {
        //verificamos que lo que le estamos pasando sean variables en la expresion en casoo las halla
        int contador = 0;
        //colocamos los numeros por que tambien vamos a podoer pasarle numeros ya en la expresion
        String[] noVariables = {"(", ")", "#", "^", "*", "/", "+", "-", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9"};
        //recorremoos
        for (int i = 0; i < expresion.length(); i++) {
            //guardamos el caracter en una variable de tipo char
            String caracter = String.valueOf(expresion.charAt(i));
            //creamos una flag que nos diggag si es variable oo noo
            boolean esVariable = true;
            //si el caracter esta en los no permitidos entonces vas a retornar falso y saldras del bucle por que ya encontraste algo
            for (String noVariable : noVariables) {
                if (caracter.equalsIgnoreCase(noVariable)) {
                    esVariable = false;
                    break;
                }
            }
            //si es variable osea no esta en no variables
            if (esVariable) {
                //anadie al contador
                contador++;
            }
        }
        //el contador noos va a servir para pedir tantas veces el numero de datos a ingresar
        //Por ejemplo: a+b -> seria 2 variables osea 2
        // ya luegoo en el while del menu va a iterar 2 vecces y nos pedira la letra de la variable y su valor para pasarlo a la otora funcion
        return contador;
    }
    //conversion de inorden a postoorden

    //realiza la conversion de la expresion pero tambien retorna ya una pila de string para convertir a nodo en arbol
    public Stack<String> conversionPostorden(String expresion) {
        //la primerma condicion es recorrer de izquierda a derecha la expresion
        Stack<String> expresionPostfija = new Stack<>();
        Stack<String> operadores = new Stack<>();
        String numero = "";
        String numeroNegativo = "";
//        int contadorPrueba = 0;
        for (int i = 0; i < expresion.length(); i++) {

//si es un numero
            if (Character.isDigit(expresion.charAt(i))) {
                //concatenar la cadena de numeros
                numero += expresion.charAt(i);
                //si no es un digito corta y mandalo a la pila
                //si la cadena numero empieza con negativo entonces va a ser un numeroo negativo
                if (i == expresion.length() - 1 || !Character.isDigit(expresion.charAt(i + 1))) {
//aniade al rsultado el numero
                    expresionPostfija.add(numero);
                    numero = "";
                }
                //si es un operador o abre un parentesis manda la expresion a la pila de operadores
            } else if (expresion.charAt(i) == '-' && (i == 0 || esOperador(String.valueOf(expresion.charAt(i - 1))))) {
                // entonces es un número negativo
                numero = expresion.charAt(i) + numero;
                // Verificar si el siguiente carácter es un dígito
                if (i == expresion.length() - 1 || !Character.isDigit(expresion.charAt(i + 1))) {
                    // Agregar el número negativo a la expresión postfija
                    System.out.println(numero + " b");
                    expresionPostfija.add(numero);
                    numero = ""; // Reiniciar la cadena para el siguiente número
                }
            } else if (expresion.charAt(i) == '(') {
                operadores.add(String.valueOf(expresion.charAt(i)));

            } else if (expresion.charAt(i) == ')') { //cuando encuentre un parentesis de cierre
                //si encuentras el cierre entonces
                //recorre la pila mientras no este vacia  y que la cima de la pila sea distinta al parentesis de apertura
                while (!operadores.isEmpty() && !operadores.peek().equals("(")) {
                    expresionPostfija.add(operadores.pop());
                }
                //cuando encuentres el parentesis de apertura sacalo
                operadores.pop();
            } else if (esOperador(String.valueOf(expresion.charAt(i))) || !esOperador(String.valueOf(expresion.charAt(i - 1)))) {
                // Si es un operador manejamos la jerarquía de operaciones
                while (!operadores.isEmpty() && !operadores.peek().equals("(") && jerarquiaOperaciones(operadores.peek()) >= jerarquiaOperaciones(String.valueOf(expresion.charAt(i)))) {
                    expresionPostfija.add(String.valueOf(operadores.pop()));
                }
//                System.out.println(String.valueOf(expresion.charAt(i)) + " ");

                operadores.add(String.valueOf(expresion.charAt(i)));
            }
        }
        //por ultimo supongamos el caso (a+b)-(c-d)
        //como al haber parentesis se va a truncar hasta el abierto quedara el - en la pila recorreremos lo ultimo que tengag la pila affuera del bucle para obtener la raiz
        while (!operadores.isEmpty()) {
            expresionPostfija.add(operadores.pop());
        }
        System.out.println("Expresion PostOrden [I-D-R]");
        for (String expresiones : expresionPostfija) {
            System.out.print(expresiones + " ");
        }
        System.out.println();
        return expresionPostfija;
    }

    private Double resolverNotacionPolaca(Nodo arbol) {
        double resultado = 0.0;
// Si el dato no es un operador, es un operando, lo agregamos a la pila de resultados
        if (!esOperador(arbol.getActual())) {
            return Double.valueOf(arbol.getActual());
            //enn caos de que sea negativo
        } else if (esOperador(arbol.getActual())) {
            resultado += realizarOperacion(arbol);
        }
        return resultado;
    }

    public String resultadoNotacionPolaca(Nodo arbol) {
        String resultado = "";
        resultado = String.valueOf(resolverNotacionPolaca(arbol));
        //System.out.println("Raíz del árbol de expresión: " + Arbol.getActual());
        return resultado;
    }

    // aqui evaluamos los opoeradores  y retornamos el resultadoo
    private double realizarOperacion(Nodo arbol) {
        double resultado = 0.0;
        switch (arbol.getActual()) {
            case "+":
                resultado = resolverNotacionPolaca(arbol.getNodoIzquierda()) + resolverNotacionPolaca(arbol.getNodoDerecha());
                break;
            case "-":
                double izquierda = resolverNotacionPolaca(arbol.getNodoIzquierda());
                double derecha = resolverNotacionPolaca(arbol.getNodoDerecha());
                if (izquierda < 0 && derecha < 0) {
                    resultado = izquierda - derecha; // Ambos son negativos, por lo que sumamos
                } else {
                    resultado = izquierda - derecha; // Restamos normalmente
                }
                break;
            case "*":
                resultado = resolverNotacionPolaca(arbol.getNodoIzquierda()) * resolverNotacionPolaca(arbol.getNodoDerecha());
                break;
            case "/":
                double divisor = resolverNotacionPolaca(arbol.getNodoDerecha());
                if (divisor != 0) {
                    resultado = resolverNotacionPolaca(arbol.getNodoIzquierda()) / divisor;
                } else {
                    System.out.println("Indeterminado");
                    return 0.0; // Manejar el caso de división por cero
                }
                break;
            case "^":
                double base = resolverNotacionPolaca(arbol.getNodoIzquierda());
                double exponente = resolverNotacionPolaca(arbol.getNodoDerecha());
                if (base < 0) {
                    resultado = Math.pow(-base, exponente) * -1; // Tratamos la base negativa
                } else {
                    resultado = Math.pow(base, exponente);
                }
                break;
            case "#":
                double indice = resolverNotacionPolaca(arbol.getNodoIzquierda());
                double radicando = resolverNotacionPolaca(arbol.getNodoDerecha());
                resultado = Math.pow(radicando, 1.0 / indice);
                break;
        }
        return resultado;
    }


    //evalua el caracter que te pase si coincide devuelve true
    public boolean esOperador(String caracter) {

        switch (caracter) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
            case "#":
                return true;
            default:
                return false;
        }
    }

    public int jerarquiaOperaciones(String caracter) {
        switch (caracter) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
            case "#":
                return 3;
            default:
                return 0;
        }
    }

    public boolean validarExpresion(String expresion) {
        //recorrer el arbol
        for (int i = 0; i < expresion.length(); i++) {
            //usamos una bandera iniciada en falso para saber si ya encotnramos alguna coincidencia
            boolean encontrado = false;
            //recorremos los caracteres que deseamos que se acepten
            //            for (char caracter : permitidos) {

            for (String caracter : permitidos) {
                //si coincide retorna  true y vuelve a empezar
                if (String.valueOf(expresion.charAt(i)).equalsIgnoreCase(caracter)) {
                    encontrado = true;
                    break;
                }
            }
            //si no lo encontraste no es una expresion valida
            if (!encontrado) {
                System.out.println("La expresión contiene caracteres no permitidos." + expresion.charAt(i));
                return false;
            }
        }
        //si todos se aceptaron devuelve true
        System.out.println("La expresión es válida.");
        return true;
    }
}
