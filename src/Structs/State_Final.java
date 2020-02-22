package Structs;

import java.util.ArrayList;

/**
 *
 * @author JeffGeo
 */
public class State_Final {

    int Estado;
    String Siguiente;
    ArrayList<Object> Transition;

    public State_Final(int Estado, String Siguiente) {
        this.Estado = Estado;
        this.Siguiente = Siguiente;
        this.Transition = new ArrayList();
    }

    @Override
    public String toString() {
        return "Estado=" + Estado + ", Siguiente=" + Siguiente + " Transiciones: {" + Transitions() + '}';
    }

    public void addTransition(int Estado, String Lexema) {
        this.Transition.add(new Transition(Estado,Lexema));
    }

    public String Transitions() {
        String txt = "";
        for (Object temp : this.Transition) {
            txt += temp.toString();
        }
        return txt;
    }
}
