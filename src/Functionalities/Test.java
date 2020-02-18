package Functionalities;

/**
 *
 * @author JeffGeo
 */
public class Test {

    private String Identifier;
    private String Cadena;

    public Test(String Identifier, String Cadena) {
        this.Identifier = Identifier;   //Identifier for Test
        this.Cadena = Cadena;           //Test to comprobate with expression regular
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String Identifier) {
        this.Identifier = Identifier;
    }

    public String getCadena() {
        return Cadena;
    }

    public void setCadena(String Cadena) {
        this.Cadena = Cadena;
    }
}
