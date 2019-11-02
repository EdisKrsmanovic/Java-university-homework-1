package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

public class King extends ChessPiece {

    public King(String position, Color color) throws IllegalArgumentException {
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

        int letterCount = Math.abs(oldPositionLetter - newPositionLetter);
        int numberCount = Math.abs(oldPositionNumber - newPositionNumber);

        if(letterCount > 1 || numberCount > 1) throw new IllegalChessMoveException();
        super.setPosition(position);
    }
}
