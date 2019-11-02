package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

public class Pawn extends ChessPiece {
    private boolean alreadyMoved;

    public Pawn(String position, Color color) throws IllegalArgumentException {
        super(position, color);
        alreadyMoved = false;
    }

    @Override
    public void move(String position) throws IllegalArgumentException {
        int directionMultiplier = getColor() == Color.WHITE ? 1 : -1;
        if (getPosition().charAt(0) != position.charAt(0)) {
            if (getPosition().charAt(1) + directionMultiplier != position.charAt(1) || Math.abs(getPosition().charAt(0) - position.charAt(0)) != 1)
                throw new IllegalArgumentException();
        } else if (getPosition().charAt(1) + 2 * directionMultiplier == position.charAt(1) && alreadyMoved) {
            throw new IllegalArgumentException();
        }
        else if (position.charAt(1) - getPosition().charAt(1) != directionMultiplier && alreadyMoved) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void setPosition(String position) {
        move(position);
        if (!alreadyMoved) alreadyMoved = true;
        super.setPosition(position);
    }
}
