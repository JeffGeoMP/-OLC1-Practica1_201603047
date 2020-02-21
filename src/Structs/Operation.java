package Structs;

import java.util.ArrayList;

/**
 *
 * @author JeffGeo
 */
public class Operation {

    String Symbol;
    String Follow;

    public Operation(String symbol, String Follows) {
        this.Symbol = symbol;
        this.Follow = Follows;
    }

    @Override
    public String toString() {
        return "Operation{" + "Symbol=" + Symbol + ", Follow=" + Follow + '}';
    }
}
