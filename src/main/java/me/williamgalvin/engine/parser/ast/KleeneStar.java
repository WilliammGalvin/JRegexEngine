package me.williamgalvin.engine.parser.ast;

public class KleeneStar extends ASTNode {

    private ASTNode child;

    public KleeneStar(ASTNode child) {
        this.child = child;
    }

    public ASTNode getChild() {
        return child;
    }

}
