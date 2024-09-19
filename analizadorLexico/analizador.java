import java.util.ArrayList;
import java.util.List;
import main.java.ClaseLexica;
import main.java.Token;

public class analizador {

    private String input;
    private int posicion;

    public analizador(String input) {
        this.input = input;
        this.posicion = 0;
    }

    // Función para iniciar el análisis
    public List<Token> analizar() {
        List<Token> tokens = new ArrayList<>();
        while (posicion < input.length()) {
            char actual = input.charAt(posicion);

            if (Character.isLetter(actual)) {
                tokens.add(analizarIdentificador());
            } else if (Character.isDigit(actual)) {
                tokens.add(analizarNumero());
            } else if (actual == '+') {
                tokens.add(new Token(ClaseLexica.SUMA, "+"));
                posicion++;
            } else if (actual == '=') {
                tokens.add(new Token(ClaseLexica.ASIGNACION, "::="));
                posicion++;
            } else if (Character.isWhitespace(actual)) {
                tokens.add(new Token(ClaseLexica.ESPACIO_EN_BLANCO, " "));
                posicion++;
            } else {
                posicion++;
            }
        }
        return tokens;
    }

    // Analizador de identificadores (solo letras)
    private Token analizarIdentificador() {
        StringBuilder sb = new StringBuilder();
        while (posicion < input.length() && Character.isLetter(input.charAt(posicion))) {
            sb.append(input.charAt(posicion));
            posicion++;
        }
        return new Token(ClaseLexica.IDENTIFICADOR, sb.toString());
    }

    // Analizador de números (enteros o decimales)
    private Token analizarNumero() {
        StringBuilder sb = new StringBuilder();
        boolean esDecimal = false;
        while (posicion < input.length() && (Character.isDigit(input.charAt(posicion)) || input.charAt(posicion) == '.')) {
            if (input.charAt(posicion) == '.') {
                if (esDecimal) {
                    break; 
                }
                esDecimal = true;
            }
            sb.append(input.charAt(posicion));
            posicion++;
        }
        return esDecimal ? new Token(ClaseLexica.DECIMAL, sb.toString()) : new Token(ClaseLexica.ENTERO, sb.toString());
    }

    // Main para probar el analizador léxico
    public static void main(String[] args) {
        String input = "holax ::= 10 + = 3.14 y";
        analizador lexer = new analizador(input);
        List<Token> tokens = lexer.analizar();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
