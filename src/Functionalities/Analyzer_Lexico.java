package Functionalities;

import java.util.ArrayList;

/**
 *
 * @author JeffGeo
 */
public class Analyzer_Lexico {

    private ArrayList<Object> Tokens;
    private ArrayList<String> Mistakes;
    private String[] ReservedWords;

    private String Lexema;
    private int Indice, Estado;

    public Analyzer_Lexico() {
        Tokens = new ArrayList();
        Mistakes = new ArrayList();
        ReservedWords = new String[1];

        Lexema = "";
        Indice = 0;
        Estado = 0;

        Load_Words();
    }

    // Load Words Reserved to the System----------------------------------------
    private void Load_Words() {
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
        if(lexema.equals("+")| lexema.equals("|") | lexema.equals(".") |
                lexema.equals("?") | lexema.equals("*")){
            return true;
        }
        return false;
    }

    // Check is it's Symbol Complementary --------------------------------------
    private Boolean IsSymbol(int code) {
        if ((code >= 32 && code <= 47) | (code >= 58 && code <= 64) //Code's 32 -> 125
                | (code >= 91 && code <= 96) | (code >= 123 && code <= 125)) {
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

    public void Analyzer(String Input) {
        Tokens.clear();
        Mistakes.clear();
        Indice = 0;
        Estado = 0;

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
                    } else if (letter == ' ') {
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
                        Tokens.add(new Type(Lexema, Type.Number));
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
                            Tokens.add(new Type(Lexema, Type.Reserved_Word));
                            Lexema = "";
                            Estado = 0;
                            Indice--;
                        } else {
                            Tokens.add(new Type(Lexema, Type.Id));
                            Lexema = "";
                            Estado = 0;
                            Indice--;
                        }
                    }
                    break;

                case 3:
                    if (this.IsSC(code)) {
                        Tokens.add(new Type(Lexema, Type.Symbol_C));
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
                    Tokens.add(new Type(Lexema, Type.Cadena));
                    Lexema = "";
                    Estado = 0;
                    Indice--;
                    break;

                case 5:
                    if (this.IsSC(code)) {
                        Tokens.add(new Type(Lexema, Type.Symbol_C));
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
                    Tokens.add(new Type(Lexema, Type.Comment_of_a_line));
                    Estado = 0;
                    Lexema = "";
                    Indice--;
                    break;

                case 8:
                    if (this.IsSC(code)) {
                        Tokens.add(new Type(Lexema, Type.Symbol_C));
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
                    Tokens.add(new Type(Lexema, Type.Comment_Multiline));
                    Estado = 0;
                    Lexema += letter;
                    Indice--;
                    break;

                case 12:
                    if (this.IsSC(code)) {
                        Tokens.add(new Type(Lexema, Type.Symbol_C));
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
                    Tokens.add(new Type(Lexema, Type.Asignation));
                    Estado = 0;
                    Lexema = "";
                    Indice--;
                    break;

                case 14:
                    if(this.IsSymbolER(Lexema)){
                        Tokens.add(new Type(Lexema, Type.Symbol_ER));
                        Estado = 0;
                        Lexema = "";
                        Indice--;
                    }else{
                        Tokens.add(new Type(Lexema, Type.Symbol_C));
                        Estado = 0;
                        Lexema = "";
                        Indice--;
                    }
                    break;
            }
        }
    }
    
    public  String Imprimir(){
        String txt = "";
        Type t;
        for(int i = 0; i<Tokens.size(); i++){
            t = (Type)Tokens.get(i);
            txt += t.ToString()+"\n";    
        }

        for(int i = 0; i<Mistakes.size(); i++){
            txt += Mistakes.get(i) + "\n";
        }
        return txt;
    }
}
