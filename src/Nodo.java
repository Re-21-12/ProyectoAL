
/**
 *
 * @author victo
 */
public class Nodo {

    private String actual;
    private Nodo nodoIzquierda;
    private Nodo nodoDerecha;

    public Nodo(String actual){
        this.actual = actual;
        this.nodoIzquierda = null;
        this.nodoDerecha = null;
    }
    //constructor vacio para poder instanciar un nodo vacio en base a lo del libro
    public Nodo(){

    }

    public String getActual() {
        return actual;
    }

    public Nodo getNodoIzquierda() {
        return nodoIzquierda;
    }

    public Nodo getNodoDerecha() {
        return nodoDerecha;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public void setNodoIzquierda(Nodo nodoIzquierda) {
        this.nodoIzquierda = nodoIzquierda;
    }

    public void setNodoDerecha(Nodo nodoDerecha) {
        this.nodoDerecha = nodoDerecha;
    }

}
