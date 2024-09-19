package main.java;

public class Token {
    ClaseLexica clase;
    String lexema;

    public Token(ClaseLexica clase, String lexema) {
        this.clase = clase;
        this.lexema = lexema;
    }

    @Override
    public String toString() {
        return "<" + this.clase + "," + this.lexema + ">";
    }
}