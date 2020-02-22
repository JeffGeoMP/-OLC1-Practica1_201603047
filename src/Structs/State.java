package Structs;

import java.util.ArrayList;

/**
 *
 * @author JeffGeo
 */
public class State {

    String Follows;
    boolean Verificated;
    ArrayList<Object> Operations;

    public State(String Follows) {
        this.Follows = Follows;
        Verificated = false;
        this.Operations = new ArrayList();
    }

    public void AddOperation(String s, String f) {

        boolean find = false;
        for (Object t : this.Operations) {
            Operation op = (Operation) t;
            if (s.equalsIgnoreCase(op.Symbol) && f.equalsIgnoreCase(op.Follow)) {
                find = true;
            }
        }
        if (find == false) {
            Operation op = new Operation(s, f);
            this.Operations.add(op);
        }
    }

    public String Operation() {
        String txt = "";
        for (Object temp : this.Operations) {
            Operation op = (Operation) temp;
            txt = txt + op.toString();
        }
        return txt;
    }

    public String tostring() {
        return "Follows : " + this.Follows + " Verificado: " + this.Verificated + " Operaciones: " + Operation();
    }

}
