package Functionalities;

/**
 *
 * @author JeffGeo
 */
public class Type_ER {
    static final int Symbol_Expression_Regular = 1;
    static final int Cadena = 2;
    static final int Set = 3;
    
    private String lexema;
    private int type;
    
    public Type_ER(String lexema, int type){
       this.lexema = lexema;
       this.type = type;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
