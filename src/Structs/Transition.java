package Structs;

/**
 *
 * @author JeffGeo
 */
public class Transition {
    int Estado;
    String Siguiente;

    public Transition(int Estado, String Siguiente) {
        this.Estado = Estado;
        this.Siguiente = Siguiente;
    }

    @Override
    public String toString() {
        return "Transition{" + "Estado=" + Estado + ", Siguiente=" + Siguiente + '}';
    }    
}
