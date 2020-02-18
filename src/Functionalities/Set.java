package Functionalities;

/**
 *
 * @author JeffGeo
 */
public class Set {
    private String Identifier;
    private String Set;

    public Set(String Identifier, String Set) {
        this.Identifier = Identifier;   //Identifier of Set
        this.Set = Set;                 //Set defined for the file 
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String Identifier) {
        this.Identifier = Identifier;
    }

    public String getSet() {
        return Set;
    }

    public void setSet(String Set) {
        this.Set = Set;
    }
}
