package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

import static ba.unsa.etf.rpr.Pieces.PiecesUtil.checkCorrectPosition;

public class Pawn extends ChessPiece {
    private boolean alreadyMoved;

    public Pawn(String position, Color color) throws IllegalArgumentException {
        super(position, color);
        checkCorrectPosition(position);
        alreadyMoved = false;
    }

    @Override
    public void move(String position) throws IllegalArgumentException, IllegalChessMoveException {
        checkCorrectPosition(position);
        int directionMultiplier = getColor() == Color.WHITE ? 1 : -1;

        char oldPositionLetter = getPosition().toLowerCase().charAt(0);
        char oldPositionNumber = getPosition().toLowerCase().charAt(1);

        char newPositionLetter = position.toLowerCase().charAt(0);
        char newPositionNumber = position.toLowerCase().charAt(1);

        if (oldPositionLetter != newPositionLetter) {
            if (oldPositionNumber + directionMultiplier != newPositionNumber || Math.abs(oldPositionLetter - newPositionLetter) != 1) throw new IllegalArgumentException();

        } else if (oldPositionNumber + 2 * directionMultiplier == newPositionNumber) {
            if (alreadyMoved) throw new IllegalArgumentException();
            alreadyMoved = true;
        } else {
            int numberOfSteps = newPositionNumber - oldPositionNumber;
            if (numberOfSteps != directionMultiplier && numberOfSteps != 0) throw new IllegalArgumentException();
        }
        super.setPosition(position);
    }
}
