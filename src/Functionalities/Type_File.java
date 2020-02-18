package Functionalities;

/**
 *
 * @author JeffGeo
 */
public class Type_File {

    public static final int Reserved_Word = 1;
    public static final int Id = 2;
    public static final int Number = 3;
    public static final int Cadena = 4;
    public static final int Comment_of_a_line = 5;
    public static final int Comment_Multiline = 6;
    public static final int Asignation = 7;
    public static final int Symbol_ER = 8;
    public static final int Symbol_C = 9;

    private String Lexema;
    private int Type;

    public String getLexema() {
        return Lexema;
    }

    public void setLexema(String Lexema) {
        this.Lexema = Lexema;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public Type_File(String Lexema, int Type) {
        this.Lexema = Lexema;
        this.Type = Type;
    }

    public String Parser(int type) {
        switch (type) {
            case 1:
                return "Reserved Word";

            case 2:
                return "Id";

            case 3:
                return "Number";

            case 4:
                return "Cadena";

            case 5:
                return "Comment of a Line";

            case 6:
                return "Comment Multiline";

            case 7:
                return "Asignation";

            case 8:
                if (this.getLexema().equals("+")) {
                    return "Symbol of Expression Regular [+]";
                } else if (this.getLexema().equals("*")) {
                    return "Symbol of Expression Regular [*]";
                } else if (this.getLexema().equals(".")) {
                    return "Symbol of Expression Regular [.]";
                } else if (this.getLexema().equals("?")) {
                    return "Symbol of Expression Regular [?]";
                } else if (this.getLexema().equals("|")) {
                    return "Symbol of Expression Regular [|]";
                }

            case 9:
                if (this.getLexema().equals("{")) {
                    return "Open Key";
                } else if (this.getLexema().equals("}")) {
                    return "Closed Key";
                } else if (this.getLexema().equals(":")) {
                    return "Two Points";
                } else if (this.getLexema().equals(";")) {
                    return "SemiColon";
                } else if (this.getLexema().equals("~")) {
                    return "Equivalence Sign";
                } else if (this.getLexema().equals("%")) {
                    return "Porcentage";
                } else if (this.getLexema().equals(",")) {
                    return "Comma";
                } else {
                    return "Sign Complementary [" + this.getLexema() + "]";
                }

            default:
                return "xxx";
        }
    }

    public String ToString() {
        return this.getLexema() + "   " + Parser(this.getType());
    }
}
