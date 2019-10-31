package ba.unsa.etf.rpr.ChessBoard;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;
import ba.unsa.etf.rpr.Pieces.*;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<String, ChessPiece> figures = new HashMap<>();

    public Board() {
        BoardUtil.newGameState(figures);
    }

    public void move(Class type, ChessPiece.Color color, String position) throws IllegalChessMoveException {
        if (figures.get(position) != null) {
            throw new IllegalChessMoveException();
        }

        ChessPiece chessPiece = (ChessPiece) figures.entrySet()
                .stream()
                .filter(e -> hasSameColorSameTypeAndIsValidMove(type, color, position, e.getValue()))
                .findFirst()
                .orElse(null);

        if (chessPiece == null) {
            throw new IllegalChessMoveException();
        }

        chessPiece.move(position);
    }

    public void move(String oldPosition, String newPosition) throws IllegalChessMoveException {
        oldPosition = oldPosition.toLowerCase();
        newPosition = newPosition.toLowerCase();

        ChessPiece chessPiece = figures.get(oldPosition);

        if (chessPiece == null) {
            throw new IllegalArgumentException();
        } else if (!isValidMove(chessPiece, newPosition)) {
            throw new IllegalChessMoveException();
        }

        chessPiece.move(newPosition);
    }

    public boolean isCheck(ChessPiece.Color color) {
        return false;
    }

    private boolean hasSameColorSameTypeAndIsValidMove(Class type, ChessPiece.Color color, String position, ChessPiece chessPiece) {
        try {
            return isValidMove(chessPiece, position.toLowerCase()) && chessPiece.getColor().equals(color) && chessPiece.getClass() == type;
        } catch (IllegalChessMoveException e) {
            return false;
        }
    }

    public boolean isValidMove(ChessPiece chessPiece, String newPosition) throws IllegalChessMoveException {
        chessPiece.move(newPosition);

        String oldPosition = chessPiece.getPosition();
        char posNumber1 = oldPosition.charAt(1);
        char posLetter1 = oldPosition.charAt(0);
        char posNumber2 = newPosition.charAt(1);
        char posLetter2 = newPosition.charAt(0);

        if (chessPiece.getClass() == Queen.class || chessPiece.getClass() == Bishop.class || chessPiece.getClass() == Rook.class || chessPiece.getClass() == Pawn.class) {
            moveInLine(posLetter1, posNumber1, posLetter2, posNumber2);
        }

        return true;
    }

    private void moveInLine(char fromLetter, char fromNumber, char toLetter, char toNumber) throws IllegalChessMoveException {
        int horizontalAddition = 0, verticalAddition = 0;

        if (toLetter > fromLetter) verticalAddition = 1;
        else if (toLetter < fromLetter) verticalAddition = -1;

        if (toNumber > fromNumber) horizontalAddition = 1;
        else if (toNumber < fromNumber) horizontalAddition = -1;
        fromLetter += verticalAddition;
        fromNumber += horizontalAddition;
        while (fromLetter != toLetter || fromNumber != toNumber) {
            String checkPlace = String.format("%c%c", fromLetter, fromNumber);
            if (figures.get(checkPlace) != null) throw new IllegalChessMoveException();
            fromLetter += verticalAddition;
            fromNumber += horizontalAddition;
        }
    }
}
