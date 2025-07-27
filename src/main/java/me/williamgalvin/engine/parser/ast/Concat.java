package me.williamgalvin.engine.parser.ast;

import java.util.ArrayList;

public class Concat extends ASTNode {

    private ArrayList<ASTNode> children;

    public Concat(ArrayList<ASTNode> children) {
        this.children = children;
    }

    public ArrayList<ASTNode> getChildren() {
        return children;
    }
    
}