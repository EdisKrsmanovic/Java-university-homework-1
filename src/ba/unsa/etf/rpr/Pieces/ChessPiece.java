package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

public abstract class ChessPiece {
    private String position;
    private Color color;

    public static enum Color {WHITE, BLACK}

    ;

    public ChessPiece(String position, Color color) throws IllegalArgumentException {
        this.position = position;
        this.color = color;
    }

    //treba napraviti da move provjerava da li je ta vrsta poteza dozvoljena, npr da pijun ne moze 3 polja preci il da kraljica skace ko konj
    public abstract void move(String position) throws IllegalArgumentException, IllegalChessMoveException;

    public String getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    void setPosition(String position) {
        this.position = position;
    }

    void checkCorrectPosition(String position) {
        if (position.length() != 2) throw new IllegalArgumentException();
        char positionLetter = position.toLowerCase().charAt(0);
        char positionNumber = position.toLowerCase().charAt(1);
        if (positionLetter > 'h' || positionNumber > '8' || positionLetter < 'a' || positionNumber <= '0')
            throw new IllegalArgumentException();
    }
}
