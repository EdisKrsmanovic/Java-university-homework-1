package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

class PiecesUtil {
    static void checkCorrectPosition(String position) {
        if (position.length() != 2) throw new IllegalArgumentException();
        char positionLetter = position.toLowerCase().charAt(0);
        char positionNumber = position.toLowerCase().charAt(1);
        if (positionLetter > 'h' || positionNumber > '8' || positionLetter < 'a' || positionNumber <= '0')
            throw new IllegalArgumentException();
    }

    static void checkMove(char figureLetter, String oldPosition, String newPosition) throws IllegalChessMoveException {
        char oldPositionLetter = oldPosition.toLowerCase().charAt(0);
        char oldPositionNumber = oldPosition.toLowerCase().charAt(1);

        char newPositionLetter = newPosition.toLowerCase().charAt(0);
        char newPositionNumber = newPosition.toLowerCase().charAt(1);

        int letterCount = Math.abs(oldPositionLetter - newPositionLetter);
        int numberCount = Math.abs(oldPositionNumber - newPositionNumber);

        boolean diagonalMove = Math.abs(oldPositionLetter - newPositionLetter) != Math.abs(oldPositionNumber - newPositionNumber);

        if (figureLetter == 'B') {
            if (diagonalMove) throw new IllegalChessMoveException();
        } else if (figureLetter == 'R') {
            if (newPositionLetter != oldPositionLetter && newPositionNumber != oldPositionNumber)
                throw new IllegalChessMoveException();
        } else if (figureLetter == 'Q') {
            if (diagonalMove && newPositionLetter != oldPositionLetter && newPositionNumber != oldPositionNumber)
                throw new IllegalChessMoveException();
        } else if (figureLetter == 'N') {
            if (!(letterCount == 2 && numberCount == 1) && !(numberCount == 2 && letterCount == 1))
                throw new IllegalChessMoveException();
        } else if (figureLetter == 'K') {
            if (letterCount > 1 || numberCount > 1) throw new IllegalChessMoveException();
        }
    }

}
