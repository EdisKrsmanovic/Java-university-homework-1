package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

public class Bishop extends ChessPiece {
    public Bishop(String position, Color color) throws IllegalArgumentException {
        super(position, color);
        checkCorrectPosition(position);
    }

    @Override
    public void move(String position) throws IllegalArgumentException, IllegalChessMoveException {
        checkCorrectPosition(position);
        char oldPositionLetter = getPosition().toLowerCase().charAt(0);
        char oldPositionNumber = getPosition().toLowerCase().charAt(1);

        char newPositionLetter = position.toLowerCase().charAt(0);
        char newPositionNumber = position.toLowerCase().charAt(1);

        if(Math.abs(oldPositionLetter - newPositionLetter) != Math.abs(oldPositionNumber - newPositionNumber)) throw new IllegalChessMoveException();
        super.setPosition(position);
    }
}
