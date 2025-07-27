package me.williamgalvin.engine.parser.ast;

public class Optional extends ASTNode {

    private ASTNode child;

    public Optional(ASTNode child) {
        this.child = child;
    }

    public ASTNode getChild() {
        return child;
    }

}
