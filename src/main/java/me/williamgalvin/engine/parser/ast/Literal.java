package me.williamgalvin.engine.parser.ast;

public class Literal extends ASTNode {

    private String value;

    public Literal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
