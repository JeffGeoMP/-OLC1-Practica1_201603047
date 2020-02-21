package Structs;

import Functionalities.Type_ER;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author JeffGeo
 */
public class Follow {

    public Type_ER Information;
    public ArrayList<Integer> Follows;

    public Follow(Type_ER Information) {
        this.Information = Information;
        this.Follows = new ArrayList();
    }

    public void Add(Integer n) {
        this.Follows.add(n);
        Collections.sort(this.Follows);
    }

    public String Follows() {
        String follows="";
        for (int i = 0; i < this.Follows.size(); i++) {
            if(i==0){
                follows = ""+this.Follows.get(i);
            }else{
                follows = follows + ","+this.Follows.get(i);
            }
        }
        return follows;
    }
}
