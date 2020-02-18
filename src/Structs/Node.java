
package Structs;

import Functionalities.Type_ER;

/**
 *
 * @author JeffGeo
 */
public class Node {
    Node Left;
    Node Right;
    Type_ER Information;
    boolean Anulable;
    String First;
    String Follow;
    int Nivel;
    String Name;
    
    public Node(String name,Type_ER Information){
        this.Left = null;
        this.Right = null;
        this.Anulable = false; 
        this.First = "";
        this.Follow = "";
        this.Name = name;
        this.Information = Information;
        this.Nivel = 0;
    }    
}
