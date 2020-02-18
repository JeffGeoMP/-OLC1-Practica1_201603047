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
    int Leaves;

    public Tree() {
        this.Root = null;
        Symbols = new ArrayList();
        this.Leaves = 1;
    }

    private boolean IsTwo(String lexema) {
        return lexema.equals(".") | lexema.equals("|");
    }

    private boolean IsUnity(String lexema) {
        return lexema.equalsIgnoreCase("?") | lexema.equalsIgnoreCase("*")
                | lexema.equalsIgnoreCase("+");
    }

    public void Insert(String name, Type_ER te) {
        Node n = new Node(name, te);

        if (n.Information.getType() != 1) {
            n.First = this.Leaves + "";
            n.Follow = this.Leaves + "";
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
                for (int i = 0; i < Symbols.size(); i++) {
                    Node temp = (Node) Symbols.get(i);
                    int current_nivel = temp.Nivel;
                    if (this.IsTwo(temp.Information.getLexema())) {
                        if (temp.Information.getLexema().equalsIgnoreCase("|")) {
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
            if (left == false && or == false) {
                for (int i = Symbols.size() - 1; i >= 0; i--) {
                    Node temp = (Node) Symbols.get(i);
                    int current_nivel = temp.Nivel;
                    if (this.IsTwo(temp.Information.getLexema())) {
                        if (temp.Right == null) {
                            n.Nivel = current_nivel + 1;
                            temp.Right = n;
                            right = true;
                            break;
                        }
                    }
                }
            }
            Symbols.add(n);
        }
    }

    public void printTree() {
        for (int i = 0; i < Symbols.size(); i++) {
            Node n = (Node) Symbols.get(i);
            if (n.Left == null && n.Right == null) {
                System.out.println("Nombre: " + n.Name + " Symbol: " + n.Information.getLexema() + " Izquierda: ----------          Derecha: ----------  Primeros: " + n.First + "Siguientes: " + n.Follow + "Anulable: " + n.Anulable + "Nivel: " + n.Nivel);
            } else if (n.Left != null && n.Right == null) {
                System.out.println("Nombre: " + n.Name + " Symbol: " + n.Information.getLexema() + " Izquierda: " + n.Left.Name + "  Derecha: ----------  Primeros: " + n.First + "Siguientes: " + n.Follow + "Anulable: " + n.Anulable + "Nivel: " + n.Nivel);
            } else {
                System.out.println("Nombre: " + n.Name + " Symbol: " + n.Information.getLexema() + " Izquierda: " + n.Left.Name + "  Derecha: " + n.Right.Name + "Primeros: " + n.First + "Siguientes: " + n.Follow + "Anulable: " + n.Anulable + "Nivel: " + n.Nivel);
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

    public void GenerateImage(int n) {
        final String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String rutaImagen = "Arbol.png";
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
//            pw.println("node [shape = record, style=filled, fillcolor=seashell2]");
            pw.println("node [shape = circle, style=filled, fillcolor=seashell2,width=0.8, fixedsize=true]");
            if (Root != null) {
                pw.print(Code(Root));
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

    private String Code(Node n) {
        String values = "";
        if (n.Left == null && n.Right == null) {
            values += n.Name + " [label=\"" + n.Information.getLexema() + "\"]";
        } else {
            values += n.Name + " [label=\"" + n.Information.getLexema() + "\"]\n";
        }
        if (n.Left != null) {
            values += Code(n.Left) + n.Name + "->" + n.Left.Name + "\n";
        }
        if (n.Right != null) {
            values += Code(n.Right) + n.Name + "->" + n.Right.Name + "\n";
        }
        return values;
    }

    public void Calculate() {
        int nivels = this.Nivels();
        for (int i = nivels; i > 0; i--) {
            for (int j = 0; j < Symbols.size(); j++) {
                Node n = (Node) Symbols.get(i);
                if (n.Nivel == nivels) {
                    System.out.println(j);
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
                        n.Follow = n.Left.Follow;
                    } else {
                        if (n.Left.Anulable == true) {
                            n.Anulable = true;
                        } else {
                            n.Anulable = false;
                        }
                        n.First = n.Left.First;
                        n.Follow = n.Left.Follow;
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
                            n.Follow = n.Left.Follow+ "," + n.Right.Follow;
                        } else {
                            n.Follow = n.Right.Follow;
                        }

                    } else {
                        if (n.Left.Anulable == true | n.Right.Anulable == true) {
                            n.Anulable = true;
                        } else {
                            n.Anulable = false;
                        }
                        n.First = n.Left.First + "," + n.Right.First;
                        n.Follow = n.Left.Follow + "," + n.Right.Follow;
                    }
                }
            }
        }
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

}
