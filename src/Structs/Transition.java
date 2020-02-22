package Structs;

/**
 *
 * @author JeffGeo
 */
public class Transition {
    int State;
    String Lexema;
    
    public Transition(int state, String lexema){
        this.State = state;
        this.Lexema = lexema;
    }

    @Override
    public String toString() {
        return "Lexema=" + Lexema + ", State=" + State + '}';
    }
}
