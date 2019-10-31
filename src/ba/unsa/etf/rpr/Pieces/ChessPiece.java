package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

public abstract class ChessPiece {
    private String position;
    private Color color;

    public static enum Color {WHITE, BLACK};

    public ChessPiece(String position, Color color) throws IllegalArgumentException {
        this.position = position;
        this.color = color;
    }

    public abstract void move(String position) throws IllegalArgumentException, IllegalChessMoveException;

    public String getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
}
