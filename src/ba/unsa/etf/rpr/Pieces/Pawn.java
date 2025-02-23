package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;


public class Pawn extends ChessPiece {
    private boolean alreadyMoved;

    public Pawn(String position, Color color) throws IllegalArgumentException {
        super(position, color);
        alreadyMoved = false;
    }

    @Override
    public void move(String position) throws IllegalChessMoveException {
        super.move(position);
        int directionMultiplier = getColor() == Color.WHITE ? 1 : -1;

        char oldPositionLetter = getPosition().toLowerCase().charAt(0);
        char oldPositionNumber = getPosition().toLowerCase().charAt(1);

        char newPositionLetter = position.toLowerCase().charAt(0);
        char newPositionNumber = position.toLowerCase().charAt(1);

        if (oldPositionLetter != newPositionLetter) {
            if (oldPositionNumber + directionMultiplier != newPositionNumber || Math.abs(oldPositionLetter - newPositionLetter) != 1) throw new IllegalChessMoveException("You can't move that way!");

        } else if (oldPositionNumber + 2 * directionMultiplier == newPositionNumber) {
            if (alreadyMoved) throw new IllegalChessMoveException("You can't move two steps again!");
            alreadyMoved = true;
        } else {
            int numberOfSteps = newPositionNumber - oldPositionNumber;
            if (numberOfSteps != directionMultiplier && numberOfSteps != 0) throw new IllegalChessMoveException("You can't move that way!");
        }
        super.setPosition(position);
    }
}
