package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

public class Pawn extends ChessPiece {
    private boolean alreadyMoved;

    public Pawn(String position, Color color) throws IllegalArgumentException {
        super(position, color);
        alreadyMoved = false;
    }

    @Override
    public void move(String position) throws IllegalArgumentException, IllegalChessMoveException {

    }
}
