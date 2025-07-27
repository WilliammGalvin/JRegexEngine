package me.williamgalvin.engine.parser.ast;

import java.util.ArrayList;

public class Alternation extends ASTNode {

    private ArrayList<ASTNode> children;

    public Alternation(ArrayList<ASTNode> children) {
        this.children = children;
    }

    public ArrayList<ASTNode> getChildren() {
        return children;
    }

}
