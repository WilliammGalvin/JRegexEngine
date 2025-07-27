package me.williamgalvin.engine.parser.ast;

public class KleenePlus extends ASTNode {

    private ASTNode child;

    public KleenePlus(ASTNode child) {
        this.child = child;
    }

    public ASTNode getChild() {
        return child;
    }

}
