package Structs;

import Functionalities.Type_ER;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author JeffGeo
 */
public class Tree {

    Node Root;
    ArrayList<Object> Symbols;
    ArrayList<Object> Table_Follow;
    ArrayList<Object> Table_Transitions;
    int Leaves;
    int State;

    public Tree() {
        this.Root = null;
        Symbols = new ArrayList();
        Table_Follow = new ArrayList();
        Table_Transitions = new ArrayList();
        this.Leaves = 1;
        this.State = 0;
    }

    //Insert in Table for Tree -------------------------------------------------
    public void Insert(String name, Type_ER te) {
        Node n = new Node(name, te);

        if (n.Information.getType() != 1) {
            n.First = this.Leaves + "";
            n.Last = this.Leaves + "";
            n.Anulable = false;
            this.Leaves++;
        }

        if (this.Root == null) {
            this.Root = n;
            n.Nivel = 1;
            Symbols.add(n);
        } else {
            boolean left = false;
            boolean or = false;
            boolean right = false;
            for (int i = 0; i < Symbols.size(); i++) {
                Node temp = (Node) Symbols.get(i);
                int current_nivel = temp.Nivel;
                if (temp.Left == null) {
                    if (temp.Information.getType() == 1) {
                        temp.Left = n;
                        n.Nivel = current_nivel + 1;
                        left = true;
                        break;
                    }
                }
            }
            if (left == false) {
                for (int i = Symbols.size() - 1; i >= 0; i--) {
                    Node temp = (Node) Symbols.get(i);
                    int current_nivel = temp.Nivel;
                    if (temp.Information.getType() == 1) {
                        if (this.IsTwo(temp.Information.getLexema())) {
                            if (temp.Right == null) {
                                temp.Right = n;
                                n.Nivel = current_nivel + 1;
                                or = true;
                                break;
                            }
                        }
                    }
                }
            }
            Symbols.add(n);
        }
    }

    //--------------------------------------------------------------------------
    //Calculate Anulable, First y Last -----------------------------------------
    public void Calculate() {
        int nivels = this.Nivels();
//        System.out.println(nivels);
        for (int i = nivels; i > 0; i--) {
            for (int j = 0; j < Symbols.size(); j++) {
                Node n = (Node) Symbols.get(j);
                if (n.Nivel == i) {
                    Update(n);
                }
            }
        }
    }

    private void Update(Node n) {
        if (n.Information.getType() == 1) {
            if (this.IsUnity(n.Information.getLexema())) {
                if (n.Left != null) {
                    if (n.Information.getLexema().equalsIgnoreCase("*") | n.Information.getLexema().equalsIgnoreCase("?")) {
                        n.Anulable = true;
                        n.First = n.Left.First;
                        n.Last = n.Left.Last;
                    } else {
                        if (n.Left.Anulable == true) {
                            n.Anulable = true;
                        } else {
                            n.Anulable = false;
                        }
                        n.First = n.Left.First;
                        n.Last = n.Left.Last;
                    }
                }
            } else {
                if (n.Left != null && n.Right != null) {
                    if (n.Information.getLexema().equalsIgnoreCase(".")) {
                        if (n.Left.Anulable == true && n.Right.Anulable == true) {
                            n.Anulable = true;
                        } else {
                            n.Anulable = false;
                        }
                        if (n.Left.Anulable == true) {
                            n.First = n.Left.First + "," + n.Right.First;
                        } else {
                            n.First = n.Left.First;
                        }

                        if (n.Right.Anulable == true) {
                            n.Last = n.Left.Last + "," + n.Right.Last;
                        } else {
                            n.Last = n.Right.Last;
                        }

                    } else {
                        if (n.Left.Anulable == true | n.Right.Anulable == true) {
                            n.Anulable = true;
                        } else {
                            n.Anulable = false;
                        }
                        n.First = n.Left.First + "," + n.Right.First;
                        n.Last = n.Left.Last + "," + n.Right.Last;
                    }
                }
            }
        }
    }
    //--------------------------------------------------------------------------

    //Calculate Table Siguientes ----------------------------------------------
    public void Table_Follow() {
        for (Object current : Symbols) {
            Node n = (Node) current;
            if (n.Information.getType() != 1) {
                Table_Follow.add(new Follow(n.Information));
            }
        }
        for (Object current : Symbols) {
            Node n = (Node) current;
            if (n.Information.getType() == 1) {
                if (this.IsUnity(n.Information.getLexema()) && n.Left != null) {
                    if (n.Information.getLexema().equalsIgnoreCase("*") | n.Information.getLexema().equalsIgnoreCase("+")) {
                        String positions = n.Left.Last;
                        String follow = n.Left.First;
                        Insert_Table(positions, follow);
                    }
                } else {
                    if (n.Information.getLexema().equalsIgnoreCase(".") && n.Left != null && n.Right != null) {
                        String positions = n.Left.Last;
                        String follow = n.Right.First;
                        Insert_Table(positions, follow);
                    }
                }
            }
        }
    }

    private void Insert_Table(String pos, String sig) {
        String[] position = pos.split(",");
        String[] next = sig.split(",");
        for (int i = 0; i < position.length; i++) {
            int index = Integer.parseInt(position[i]) - 1;
            if (index < Table_Follow.size()) {
                Follow tf = (Follow) Table_Follow.get(index);
                if (tf.Follows.isEmpty()) {
                    for (int j = 0; j < next.length; j++) {
//                        tf.Follows.Add(Integer.parseInt(next[j]));
                        tf.Add(Integer.parseInt(next[j]));
                    }
                } else {
                    boolean ver = false;
                    for (int j = 0; j < next.length; j++) {
                        ver = false;
                        for (int k = 0; k < tf.Follows.size(); k++) {
                            if (tf.Follows.get(k) != Integer.parseInt(next[j])) {
                                ver = true;
                            } else {
                                ver = false;
                                break;
                            }
                        }
                        if (ver) {
//                           tf.Follows.Add(Integer.parseInt(next[j]));
                            tf.Add(Integer.parseInt(next[j]));
                        }
                    }
                }
            }
        }
    }
    //--------------------------------------------------------------------------

    // Table Transitions for Automata ------------------------------------------
    public void Table_Transitions() {
        ArrayList<Object> States = new ArrayList();

        String Current_Positions = this.Root.First;
        if (!Current_Positions.isEmpty()) {
            States.add(new State(Current_Positions));
        }

        boolean New = true;
        while (New == true) {
            State st = null;
            Current_Positions = "";
            for (int i = 0; i < States.size(); i++) {
                State aux = (State) States.get(i);
                if (aux.Verificated == false) {
                    aux.Verificated = true;
                    st = aux;
                    Current_Positions = st.Follows;
                    break;
                }
            }

            if (st != null && !Current_Positions.isEmpty()) {
                String[] Positions = Current_Positions.split(",");
                for (int i = 0; i < Positions.length; i++) {
                    if (IsDigit(Positions[i])) {
                        int index = Integer.parseInt(Positions[i]) - 1;
                        if (index >= 0 && index < (this.Table_Follow.size() - 1)) {
                            Follow f = (Follow) this.Table_Follow.get(index);
                            String Current_Lex = f.Information.getLexema();
                            String Current_Follow = ReturnFollows(Current_Lex);
                            if (!Current_Follow.isEmpty()) {
                                st.AddOperation(f.Information.getLexema(), Current_Follow);
                                boolean find_States = false;
                                for (int j = 0; j < States.size(); j++) {
                                    State st2 = (State) States.get(j);
                                    if (st2.Follows.equalsIgnoreCase(Current_Follow)) {
                                        find_States = true;
                                        break;
                                    }
                                }
                                if (find_States == false) {
                                    States.add(new State(Current_Follow));
                                }
                            }
                        }
                    }
                }
            }

            New = false;
            for (int i = 0; i < States.size(); i++) {
                State ver = (State) States.get(i);
                if (ver.Verificated == false) {
                    New = true;
                    break;
                }
            }
        }

        for (Object x : States) {
            State state = (State) x;
            System.out.println(state.tostring());
        }

        for (int i = 0; i < States.size(); i++) {
            State st = (State) States.get(i);
            this.Table_Transitions.add(new State_Final(i, st.Follows));
        }

        for (int i = 0; i < States.size(); i++) {
            State st = (State) States.get(i);
            for (Object x : st.Operations) {
                Operation op = (Operation) x;
                int estado = this.ReturnState(op.Follow);
                String symbol = op.Symbol;

                if (estado != -1 && !symbol.isEmpty()) {
                    State_Final sf = (State_Final) this.Table_Transitions.get(i);
                    sf.addTransition(estado, symbol);
                }
            }
        }

//        for (Object x : this.Table_Transitions) {
//            State_Final state = (State_Final) x;
//            System.out.println(state.toString());
//        }
        //----------------------------------------------------------------------
    }

    //Reports ------------------------------------------------------------------
    public void GenerateImageTree(String name) {
        final String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String rutaImagen = name + ".png";
        String rutatxt = "Arbol.txt";
        String parametroT = "-Tpng";
        String parametroO = "-o";

        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter(rutatxt);
            pw = new PrintWriter(archivo);
            pw.println("digraph G {");
            pw.println("rankdir=TB");
            pw.println("node [shape = circle, style=filled, fillcolor=seashell2,width=1.5, fixedsize=true]");
            if (Root != null) {
                pw.print(CodeGraphivzTree(Root));
            } else {
                pw.print("node0[label=\"Not Found Files\"];");
            }
            pw.println("}");
            archivo.close();

            // Creates rutaImagen of Structure
            try {
                String[] cmd = new String[5];
                cmd[0] = rutaDot;
                cmd[1] = parametroT;
                cmd[2] = rutatxt;
                cmd[3] = parametroO;
                cmd[4] = rutaImagen;

                Runtime rt = Runtime.getRuntime();
                rt.exec(cmd);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("ERROR en archivo: " + e.toString());
        }
    }

    //--------------------------------------------------------------------------
    public void GenerateImageTableF(String name) {
        final String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String rutaImagen = name + ".png";
        String rutatxt = "Table_Follow.txt";
        String parametroT = "-Tpng";
        String parametroO = "-o";

        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter(rutatxt);
            pw = new PrintWriter(archivo);
            pw.println("digraph G {");
            pw.println("rankdir = LR");
            pw.println("node[shape=plaintext]");
            pw.println("a[label=<<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"1\">");

            if (!this.Table_Follow.isEmpty()) {
                pw.println("<TR>");
                pw.println("<TD BGCOLOR=\"#F6DDCC\"> Index </TD>");
                pw.println("<TD BGCOLOR=\"#F6DDCC\"> Simbol </TD>");
                pw.println("<TD BGCOLOR=\"#F6DDCC\"> Follow </TD>");
                pw.println("</TR>");
            }
            int index = 1;
            for (Object x : this.Table_Follow) {
                Follow f = (Follow) x;
                pw.println("<TR>");
                pw.println("<TD BGCOLOR=\"#F6DDCC\">" + index + "</TD>");
                pw.println("<TD BGCOLOR=\"#F6DDCC\">" + f.Information.getLexema() + "</TD>");
                pw.println("<TD BGCOLOR=\"#F6DDCC\">" + f.Follows() + "</TD>");
                pw.println("</TR>");
                index++;
            }

            pw.println("</TABLE>>]");
            pw.println("b [style=filled, fillcolor=seashell2, label=\"Table of Follows: " + name + "\"]");
            pw.println("}");
            archivo.close();

            // Creates rutaImagen of Structure
            try {
                String[] cmd = new String[5];
                cmd[0] = rutaDot;
                cmd[1] = parametroT;
                cmd[2] = rutatxt;
                cmd[3] = parametroO;
                cmd[4] = rutaImagen;

                Runtime rt = Runtime.getRuntime();
                rt.exec(cmd);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("ERROR en archivo: " + e.toString());
        }
    }

    //--------------------------------------------------------------------------
    public void GenerateImageTableT(String name) {
        final String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String rutaImagen = name + ".png";
        String rutatxt = "Table_Transition.txt";
        String parametroT = "-Tpng";
        String parametroO = "-o";

        FileWriter archivo = null;
        PrintWriter pw = null;

        ArrayList<String> Aux = new ArrayList();

        //Symbols for Table
        for (Object x : this.Table_Follow) {
            Follow f = (Follow) x;
            if (Aux.isEmpty()) {
                Aux.add(f.Information.getLexema());
            } else {
                boolean find = false;
                for (int i = 0; i < Aux.size(); i++) {
                    if (Aux.get(i).equals(f.Information.getLexema())) {
                        find = true;
                        break;
                    }
                }
                if (find == false) {
                    Aux.add(f.Information.getLexema());
                }
            }
        }
        Aux.remove(Aux.size()-1);

        try {
            archivo = new FileWriter(rutatxt);
            pw = new PrintWriter(archivo);
            pw.println("digraph G {");
            pw.println("rankdir = LR");
            pw.println("node[shape=plaintext]");
            pw.println("a[label=<<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"1\">");

            //Imprime Los Simbolos en El Encabezado
            if (!this.Table_Follow.isEmpty()) {
                pw.println("<TR>");
                pw.println("<TD BGCOLOR=\"#F6DDCC\"> Estado </TD>");
            }
            for (String x : Aux) {
                pw.println("<TD BGCOLOR=\"#F6DDCC\"> " + x + " </TD>");
            }
            pw.println("</TR>");

            for (Object x : this.Table_Transitions) {
                State_Final t = (State_Final) x;
                pw.println("<TR>");
                pw.println("<TD BGCOLOR=\"#F6DDCC\">S" + t.Estado + "</TD>");

                for (String temp : Aux) {
                    boolean find = false;
                    for (Object y : t.Transition) {
                        Transition tran = (Transition) y;
                        if (tran.Lexema.equals(temp)) {
                            find = true;
                            pw.println("<TD BGCOLOR=\"#F6DDCC\">S" + tran.State + "</TD>");
                            break;
                        }
                    }
                    if (find == false) {
                        pw.println("<TD BGCOLOR=\"#F6DDCC\">Error</TD>");
                    }
                }
                pw.println("</TR>");
            }

            pw.println("</TABLE>>]");
            pw.println("b [style=filled, fillcolor=seashell2, label=\"Table of Transitions: " + name + "\"]");
            pw.println("}");
            archivo.close();

            // Creates rutaImagen of Structure
            try {
                String[] cmd = new String[5];
                cmd[0] = rutaDot;
                cmd[1] = parametroT;
                cmd[2] = rutatxt;
                cmd[3] = parametroO;
                cmd[4] = rutaImagen;

                Runtime rt = Runtime.getRuntime();
                rt.exec(cmd);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("ERROR en archivo: " + e.toString());
        }
    }
//
//    //AFD-----------------------------------------------------------------------

    public void GenerateImageAFD(String name) {
        final String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String rutaImagen = name + ".png";
        String rutatxt = "AFD.txt";
        String parametroT = "-Tpng";
        String parametroO = "-o";

        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter(rutatxt);
            pw = new PrintWriter(archivo);
            pw.println("digraph G {");
            pw.println("rankdir=LR");
            String accept = "" + this.Table_Follow.size();
            for (Object x : this.Table_Transitions) {
                State_Final t = (State_Final) x;
                System.out.println(t.Siguiente);
                if (t.Siguiente.contains(accept)) {
                    pw.println("S" + t.Estado + "[shape = doublecircle, style=filled, fillcolor=seashell2,width=.8, fixedsize=true]");
                } else {
                    pw.println("S" + t.Estado + "[shape = circle, style=filled, fillcolor=seashell2,width=.8, fixedsize=true]");
                }
            }

            for (Object x : this.Table_Transitions) {
                State_Final t = (State_Final) x;
                for (Object y : t.Transition) {
                    Transition t2 = (Transition) y;
                    pw.println("S" + t.Estado + "->S" + t2.State + " [label=\"" + t2.Lexema + "\"]");
                }
            }

            pw.println("}");
            archivo.close();

            // Creates rutaImagen of Structure
            try {
                String[] cmd = new String[5];
                cmd[0] = rutaDot;
                cmd[1] = parametroT;
                cmd[2] = rutatxt;
                cmd[3] = parametroO;
                cmd[4] = rutaImagen;

                Runtime rt = Runtime.getRuntime();
                rt.exec(cmd);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("ERROR en archivo: " + e.toString());
        }
    }
    //--------------------------------------------------------------------------

    public void PrintT() {
        for (int i = 0; i < Table_Follow.size(); i++) {
            Follow tf = (Follow) Table_Follow.get(i);
//            System.out.println("Index: " + i + "  Name: " + tf.Information.getLexema() + "  Siguientes: " + tf.Follows());
        }
    }

    private boolean IsDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int ReturnState(String Follow) {
        int state = -1;
        for (Object temp : this.Table_Transitions) {
            State_Final t = (State_Final) temp;
            if (t.Siguiente.equalsIgnoreCase(Follow)) {
                state = t.Estado;
                break;
            }
        }
        return state;
    }

    private String ReturnFollows(String Current_Lex) {
        String Follows = "";
        for (Object temp : this.Table_Follow) {
            Follow f = (Follow) temp;
            if (f.Information.getLexema().equalsIgnoreCase(Current_Lex)) {
                if (Follows.isEmpty()) {
                    Follows = f.Follows();
                } else {
                    if (!f.Follows().isEmpty()) {
                        if (!Follows.contains(f.Follows())) {
                            Follows = Follows + "," + f.Follows();
                        }
                    }
                }
            }
        }
        return Follows;
    }

    private String CodeGraphivzTree(Node n) {
        String values = "";
        if (n.Left == null && n.Right == null) {
            values += n.Name + " [label=\"" + n.Information.getLexema() + "\\n Anulabilidad: " + n.Anulable + "\\n Primeros: " + n.First + " \\n Ultimos: " + n.Last + "\"]\n";
        } else {
            values += n.Name + " [label=\"" + n.Information.getLexema() + "\\n Anulabilidad: " + n.Anulable + "\\n Primeros: " + n.First + " \\n Ultimos: " + n.Last + "\"]\n";
        }
        if (n.Left != null) {
            values += CodeGraphivzTree(n.Left) + n.Name + "->" + n.Left.Name + "\n";
        }
        if (n.Right != null) {
            values += CodeGraphivzTree(n.Right) + n.Name + "->" + n.Right.Name + "\n";
        }
        return values;
    }

    private int Nivels() {
        int nivels = 0;
        for (int i = 0; i < this.Symbols.size(); i++) {
            Node n = (Node) Symbols.get(i);
            if (n.Nivel > nivels) {
                nivels = n.Nivel;
            }
        }
        return nivels;
    }

    private boolean IsTwo(String lexema) {
        return lexema.equals(".") | lexema.equals("|");
    }

    private boolean IsUnity(String lexema) {
        return lexema.equalsIgnoreCase("?") | lexema.equalsIgnoreCase("*")
                | lexema.equalsIgnoreCase("+");
    }

    private void printTree() {
        for (int i = 0; i < Symbols.size(); i++) {
            Node n = (Node) Symbols.get(i);
            if (n.Left == null && n.Right == null) {
                System.out.println("Nombre: " + n.Name + " Symbol: " + n.Information.getLexema() + " Izquierda: ----------          Derecha: ----------  Primeros: " + n.First + "Siguientes: " + n.Last + "Anulable: " + n.Anulable + "Nivel: " + n.Nivel);
            } else if (n.Left != null && n.Right == null) {
                System.out.println("Nombre: " + n.Name + " Symbol: " + n.Information.getLexema() + " Izquierda: " + n.Left.Name + "  Derecha: ----------  Primeros: " + n.First + "Siguientes: " + n.Last + "Anulable: " + n.Anulable + "Nivel: " + n.Nivel);
            } else if (n.Left != null && n.Right != null) {
                System.out.println("Nombre: " + n.Name + " Symbol: " + n.Information.getLexema() + " Izquierda: " + n.Left.Name + "  Derecha: " + n.Right.Name + "Primeros: " + n.First + "Siguientes: " + n.Last + "Anulable: " + n.Anulable + "Nivel: " + n.Nivel);
            }
        }
    }

    public void PreO() {
        this.PreOrden(this.Root);
    }

    private void PreOrden(Node n) {
        if (n != null) {
            System.out.println(n.Information.getLexema());
            PreOrden(n.Left);
            PreOrden(n.Right);
        }
    }
}
