package Functionalities;

import java.util.ArrayList;

/**
 *
 * @author JeffGeo
 */
public class Analyzer_Lexico {

    private ArrayList<Object> Tokens;       //Save Tokens of file
    private ArrayList<String> Mistakes;     //Save Stakes of file
    private String[] ReservedWords;         //Word's Reserved 

    private String Lexema;
    private int Indice, Estado;

    public Analyzer_Lexico() {
        Tokens = new ArrayList();
        Mistakes = new ArrayList();
        ReservedWords = new String[1];

        Lexema = "";
        Indice = 0;
        Estado = 0;

        Load_Words_Reserved();
    }

    // Load Words Reserved to the System----------------------------------------
    private void Load_Words_Reserved() {
        this.ReservedWords[0] = "CONJ";
    }

    // Check if it's Letter ----------------------------------------------------
    private Boolean IsLetter(int code) {
        if ((code >= 65 && code <= 90) | (code >= 97 && code <= 122)
                | (code >= 160 && code <= 165) || code == 130 || code == 181
                || code == 144 || code == 214 || code == 224 || code == 233) {
            return true;
        }
        return false;
    }

    // Check is it's Number ----------------------------------------------------
    private Boolean IsNumber(int code) {
        if (code >= 48 && code <= 57) {
            return true;
        }
        return false;
    }

    // Check is it's Symbol of Expression Regular ------------------------------
    private Boolean IsSymbolER(String lexema) {
        if (lexema.equals("+") | lexema.equals("|") | lexema.equals(".")
                | lexema.equals("?") | lexema.equals("*")) {
            return true;
        }
        return false;
    }

    // Check is it's Symbol Complementary --------------------------------------
    private Boolean IsSymbol(int code) {
        if ((code > 32 && code <= 47) | (code >= 58 && code <= 64) //Code's 32 -> 125
                | (code >= 91 && code <= 96) | (code >= 123 && code <= 126)) {
            return true;
        }
        return false;
    }

    // Check si it's Word Reserved ---------------------------------------------
    private Boolean IsWR(String token) {
        if (token.equalsIgnoreCase(this.ReservedWords[0])) {
            return true;
        }
        return false;
    }

    //Check is it's Asignation of CONJ ----------------------------------------- ; ~ ,
    private Boolean IsSC(int code) {
        if (code == 126 | code == 44 | code == 59) {
            return true;
        }
        return false;
    }

    public String Imprimir() {
        String txt = "";
        Type_File t;
        for (int i = 0; i < Tokens.size(); i++) {
            t = (Type_File) Tokens.get(i);
            txt += t.ToString() + "\n";
        }

        for (int i = 0; i < Mistakes.size(); i++) {
            txt += Mistakes.get(i) + "\n";
        }
        return txt;
    }
    
    private String DeleteChar(String chain){
        String ret="";
        for(int i = 0; i<chain.length(); i++){
            if(i!=0 && i+1!=chain.length()){
                ret += chain.charAt(i);
            }
        }
        return ret;
    }
    
    public void File_Separate(ArrayList Sets, ArrayList Expressions, ArrayList Tests) {
        Type_File t;
        String Id = "";
        String Set = "";
        Boolean WR = false;
        Boolean Two = false;
        Boolean Comma = false;
        Boolean Asignation = false;

        //Separate Sets---------------------------------------------------------
        for (int i = 0; i < Tokens.size(); i++) {
            t = (Type_File) Tokens.get(i);
            if (t.getLexema().equalsIgnoreCase(this.ReservedWords[0])) {
                WR = true;
            } else if (t.getLexema().equalsIgnoreCase(":")) {
                Two = true;
            } else if (WR && Two && Id.length() == 0) {
                Id = t.getLexema();

            } else if (t.getLexema().equalsIgnoreCase("->")) {
                Asignation = true;

            } else if (t.getLexema().equalsIgnoreCase(";")) {
                if (!Id.isEmpty() && !Set.isEmpty()) {
                    Sets.add(new Set(Id, Set));
                    Id = "";
                    Set = "";
                    WR = false;
                    Two = false;
                    Comma = false;
                    Asignation = false;
                }

            } else if (WR && Two && Asignation && Id.length() != 0) {
                Set += t.getLexema();
            }
        }

        //Separeta Expression Regular ------------------------------------------
        WR = false;
        Comma = false;
        Asignation = false;
        Boolean Identifier = false;
        Id = "";
        String Expression = "";

        for (int i = 0; i < Tokens.size(); i++) {
            t = (Type_File) Tokens.get(i);
            if (t.getLexema().equalsIgnoreCase(this.ReservedWords[0])) {
                WR = true;
            } else if (t.getLexema().equalsIgnoreCase("->")) {
                Asignation = true;
            } else if (t.getType() == Type_File.Id && WR == false) {
                if (Id.length() == 0) {
                    Id = t.getLexema();
                } else {
                    Expression += t.getLexema();
                }
            } else if (t.getLexema().equalsIgnoreCase(";")) {
                if (WR == true) {
                    WR = false;
                    Id = "";
                } else {
                    if (Id.length() != 0 && Expression.length() != 0) {
                        Expressions.add(new Expression(Id, Expression));
                        Id = "";
                        Expression = "";
                        Asignation = false;
                    }
                }
            } else if (WR == false && Id.length() != 0 && Asignation == true) {
                Expression += t.getLexema();
            } else if (t.getLexema().equalsIgnoreCase("%")) {
                break;
            }
        }
        //Separate Test cadenas -----------------------------------------------
        int pg = 0;
        Id = "";
        String test = "";
        Asignation = false;
        for (int i = 0; i < Tokens.size(); i++) {
            t = (Type_File) Tokens.get(i);
            if (t.getLexema().equalsIgnoreCase("%")) {
                pg++;
            } else if (pg >= 4 && t.getType() == Type_File.Id) {
                Id = t.getLexema();
            } else if (pg >= 4 && t.getLexema().equalsIgnoreCase("->")) {
                Asignation = true;

            } else if (pg >= 4 && t.getLexema().equalsIgnoreCase(";")) {
                if (Id.length() != 0 && test.length() != 0) {
                    Tests.add(new Test(Id, test));
                    Id = "";
                    test = "";
                    Asignation = false;
                }
            } else if (pg >= 4 && Asignation == true) {
                test += t.getLexema();
            }
        }
    }

    //Analyzer Aux -------------------------------------------------------------
    public void Analyze_Expressions(ArrayList Aux, String Expression) {
        Aux.clear();
        Indice = 0;
        Estado = 0;
        Lexema = "";
        Expression += "\n";
        for (Indice = 0; Indice < Expression.length(); Indice++) {
            char letter = Expression.charAt(Indice);
            int code = (int) letter;

            switch (Estado) {

                case 0:
                    if (this.IsSymbolER(letter + "")) {
                        Estado = 1;
                        Lexema += letter;
                    } else if (letter == '"') {
                        Estado = 2;
                        Lexema += letter;
                    } else if (letter == '{') {
                        Estado = 4;
                        Lexema += letter;
                        
                    }else if(letter == ' ' | letter == '\n'){
                        
                                
                    } else {
                        System.out.println("Fatal Error ->" + letter);
                    }
                    break;

                case 1:
                    Aux.add(new Type_ER(Lexema, Type_ER.Symbol_Expression_Regular));
                    Lexema = "";
                    Estado = 0;
                    Indice--;
                    break;

                case 2:
                    if (letter == '"') {
                        Estado = 3;
                        Lexema += letter;
                    } else {
                        Estado = 2;
                        Lexema += letter;
                    }
                    break;

                case 3:
                    Lexema = this.DeleteChar(Lexema);
                    Aux.add(new Type_ER(Lexema, Type_ER.Cadena));
                    Lexema = "";
                    Estado = 0;
                    Lexema = "";
                    Indice--;
                    break;

                case 4:
                    if (letter == '}') {
                        Estado = 5;
                        Lexema += letter;
                    } else {
                        Estado = 4;
                        Lexema += letter;
                    }
                    break;

                case 5:
                    Lexema = this.DeleteChar(Lexema);
                    Aux.add(new Type_ER(Lexema, Type_ER.Set));
                    Lexema = "";
                    Estado = 0;
                    Indice--;
                    break;
            }
        }
    }
    
    //Analyzer Principal -------------------------------------------------------
    public void Analyzer(String Input) {
        Tokens.clear();
        Mistakes.clear();
        Indice = 0;
        Estado = 0;
        Lexema = "";

        String txt = Input + "\n";
        String Temp = "";

        for (int i = 0; i < txt.length(); i++) {
            char letter = txt.charAt(i);
            switch (letter) {
                case '\r':
                case '\t':
                // case '\n':
                case '\b':
                case '\f':
                    break;

                default:
                    Temp = Temp + letter;
            }
        }

        for (Indice = 0; Indice < Temp.length(); Indice++) {
            char letter = Temp.charAt(Indice);
            int code = letter;

            switch (Estado) {
                case 0:
                    if (this.IsNumber(code)) {
                        Estado = 1;
                        Lexema += letter;
                    } else if (this.IsLetter(code)) {
                        Estado = 2;
                        Lexema += letter;
                    } else if (letter == '"') {
                        Estado = 3;
                        Lexema += letter;
                    } else if (letter == '/') {
                        Estado = 5;
                        Lexema += letter;
                    } else if (letter == '<') {
                        Estado = 8;
                        Lexema += letter;
                    } else if (letter == '-') {
                        Estado = 12;
                        Lexema += letter;
                    } else if (this.IsSymbol(code)) {
                        Estado = 14;
                        Lexema += letter;
                    } else if (letter == ' ' | letter == '\n') {
                        Estado = 0;
                        Lexema = "";
                    } else {
                        Mistakes.add("Error Unsupported Character [" + letter + "]");
                        Estado = 0;
                        Lexema = "";
                    }
                    break;

                case 1:
                    if (this.IsNumber(code)) {
                        Estado = 1;
                        Lexema += letter;
                    } else {
                        Tokens.add(new Type_File(Lexema, Type_File.Number));
                        Lexema = "";
                        Estado = 0;
                        Indice--;
                    }
                    break;

                case 2:
                    if (this.IsLetter(code) | this.IsNumber(code)) {
                        Estado = 2;
                        Lexema += letter;
                    } else {
                        if (this.IsWR(Lexema)) {
                            Tokens.add(new Type_File(Lexema, Type_File.Reserved_Word));
                            Lexema = "";
                            Estado = 0;
                            Indice--;
                        } else {
                            Tokens.add(new Type_File(Lexema, Type_File.Id));
                            Lexema = "";
                            Estado = 0;
                            Indice--;
                        }
                    }
                    break;

                case 3:
                    if (this.IsSC(code)) {
                        Tokens.add(new Type_File(Lexema, Type_File.Symbol_C));
                        Lexema = "";
                        Estado = 0;
                        Indice--;
                    } else if (letter == '"') {
                        Lexema += letter;
                        Estado = 4;
                    } else {
                        Lexema += letter;
                        Estado = 3;
                    }
                    break;

                case 4:
                    Tokens.add(new Type_File(Lexema, Type_File.Cadena));
                    Lexema = "";
                    Estado = 0;
                    Indice--;
                    break;

                case 5:
                    if (this.IsSC(code)) {
                        Tokens.add(new Type_File(Lexema, Type_File.Symbol_C));
                        Lexema = "";
                        Estado = 0;
                        Indice--;
                    } else if (letter == '/') {
                        Lexema += letter;
                        Estado = 6;
                    } else {
                        Lexema += letter;
                        Mistakes.add(Lexema + "Not supported");
                        Estado = 0;
                        Lexema = "";
                    }
                    break;

                case 6:
                    if (letter == '\n') {
                        Estado = 7;
                    } else {
                        Estado = 6;
                        Lexema += letter;
                    }
                    break;

                case 7:
                    Tokens.add(new Type_File(Lexema, Type_File.Comment_of_a_line));
                    Estado = 0;
                    Lexema = "";
                    Indice--;
                    break;

                case 8:
                    if (this.IsSC(code)) {
                        Tokens.add(new Type_File(Lexema, Type_File.Symbol_C));
                        Lexema = "";
                        Estado = 0;
                        Indice--;
                    } else if (letter == '!') {
                        Lexema += letter;
                        Estado = 9;
                    } else {
                        Lexema += letter;
                        Mistakes.add(Lexema + "Not supported");
                        Estado = 0;
                        Lexema = "";
                    }
                    break;

                case 9:
                    if (letter == '!') {
                        Estado = 10;
                        Lexema += letter;
                    } else {
                        Estado = 9;
                        Lexema += letter;
                    }
                    break;

                case 10:
                    if (letter == '>') {
                        Estado = 11;
                        Lexema += letter;
                    } else {
                        Estado = 9;
                        Lexema += letter;
                    }
                    break;

                case 11:
                    Tokens.add(new Type_File(Lexema, Type_File.Comment_Multiline));
                    Estado = 0;
                    Lexema += letter;
                    Indice--;
                    break;

                case 12:
                    if (this.IsSC(code)) {
                        Tokens.add(new Type_File(Lexema, Type_File.Symbol_C));
                        Lexema = "";
                        Estado = 0;
                        Indice--;
                    } else if (letter == '>') {
                        Lexema += letter;
                        Estado = 13;
                    } else {
                        Lexema += letter;
                        Mistakes.add(Lexema + "Not supported");
                        Estado = 0;
                        Lexema = "";
                    }
                    break;

                case 13:
                    Tokens.add(new Type_File(Lexema, Type_File.Asignation));
                    Estado = 0;
                    Lexema = "";
                    Indice--;
                    break;

                case 14:
                    if (this.IsSymbolER(Lexema)) {
                        Tokens.add(new Type_File(Lexema, Type_File.Symbol_ER));
                        Estado = 0;
                        Lexema = "";
                        Indice--;
                    } else {
                        Tokens.add(new Type_File(Lexema, Type_File.Symbol_C));
                        Estado = 0;
                        Lexema = "";
                        Indice--;
                    }
                    break;
            }
        }
    }
}
