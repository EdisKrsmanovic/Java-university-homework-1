package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

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

//        if (oldPositionLetter != newPositionLetter) {
//            if (oldPositionNumber + directionMultiplier != newPositionNumber || Math.abs(oldPositionLetter - newPositionLetter) != 1){
//                System.out.println(newPositionLetter);
//                throw new IllegalArgumentException();}
//        } else if (oldPositionNumber + 2 * directionMultiplier == newPositionNumber) {
//            if (alreadyMoved) throw new IllegalArgumentException();
//            alreadyMoved = true;
//        } else {
//            int numberOfSteps = newPositionNumber - oldPositionNumber;
//            if (numberOfSteps != directionMultiplier && numberOfSteps != 0) throw new IllegalArgumentException();
//        }
        int letterCount = Math.abs(oldPositionLetter - newPositionLetter);
        int numberCount = Math.abs(newPositionNumber - oldPositionNumber);

        if (letterCount > 1 || (letterCount == 1 && numberCount != 1)) throw new IllegalChessMoveException();

        if (!alreadyMoved) {
            if(numberCount > 2) throw new IllegalChessMoveException();
            alreadyMoved = true;
        } else {
            if(numberCount > 1) throw new IllegalChessMoveException();
        }
        super.setPosition(position);
    }
}
