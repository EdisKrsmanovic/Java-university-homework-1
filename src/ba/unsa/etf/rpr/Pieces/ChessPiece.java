package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

import static ba.unsa.etf.rpr.Pieces.PiecesUtil.checkCorrectPosition;

public abstract class ChessPiece {
    private String position;
    private Color color;

    public static enum Color {WHITE, BLACK}

    public ChessPiece(String position, Color color) throws IllegalArgumentException {
        checkCorrectPosition(position);
        this.position = position;
        this.color = color;
    }

    public void move(String position) throws IllegalArgumentException, IllegalChessMoveException {
        checkCorrectPosition(position);
    }

    public String getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    void setPosition(String position) {
        this.position = position;
    }
}
