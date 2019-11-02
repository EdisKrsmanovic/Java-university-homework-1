package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

public class King extends ChessPiece {

    public King(String position, Color color) throws IllegalArgumentException {
        super(position, color);
    }

    @Override
    public void move(String position) throws IllegalArgumentException, IllegalChessMoveException {
        throw new IllegalArgumentException();
    }
}
