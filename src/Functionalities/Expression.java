package Functionalities;

import Structs.Tree;

/**
 *
 * @author JeffGeo
 */
public class Expression {
    private String Identifier;
    private String Expression;
    private Tree Tree_Syntactic;

    
    public Expression(String Identifier, String Expression) {
        this.Identifier = Identifier;       //Indetifier of the expression
        this.Expression = Expression;       //Expression Regular
        this.Tree_Syntactic = new Tree();   //Tree Syntactic of the Expression Regular
    }

    public Tree getTreeEX() {
        return Tree_Syntactic;
    }

    public void setTreeEX(Tree TreeEX) {
        this.Tree_Syntactic = TreeEX;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String Identifier) {
        this.Identifier = Identifier;
    }

    public String getExpression() {
        return Expression;
    }

    public void setExpression(String Expression) {
        this.Expression = Expression;
    }
    
}
