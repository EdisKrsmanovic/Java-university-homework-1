package ba.unsa.etf.rpr.ChessBoard;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;
import ba.unsa.etf.rpr.Pieces.*;

import java.util.Map;

class BoardUtil {
    static void newGameState(Map<String, ChessPiece> figures) {
        figures.put("a1", new Rook("a1", ChessPiece.Color.WHITE));
        figures.put("b1", new Knight("b1", ChessPiece.Color.WHITE));
        figures.put("c1", new Bishop("c1", ChessPiece.Color.WHITE));
        figures.put("d1", new Queen("d1", ChessPiece.Color.WHITE));
        figures.put("e1", new King("e1", ChessPiece.Color.WHITE));
        figures.put("f1", new Bishop("f1", ChessPiece.Color.WHITE));
        figures.put("g1", new Knight("g1", ChessPiece.Color.WHITE));
        figures.put("h1", new Rook("h1", ChessPiece.Color.WHITE));

        figures.put("a2", new Pawn("a2", ChessPiece.Color.WHITE));
        figures.put("b2", new Pawn("b2", ChessPiece.Color.WHITE));
        figures.put("c2", new Pawn("c2", ChessPiece.Color.WHITE));
        figures.put("d2", new Pawn("d2", ChessPiece.Color.WHITE));
        figures.put("e2", new Pawn("e2", ChessPiece.Color.WHITE));
        figures.put("f2", new Pawn("f2", ChessPiece.Color.WHITE));
        figures.put("g2", new Pawn("g2", ChessPiece.Color.WHITE));
        figures.put("h2", new Pawn("h2", ChessPiece.Color.WHITE));


        figures.put("a8", new Rook("a8", ChessPiece.Color.BLACK));
        figures.put("b8", new Knight("b8", ChessPiece.Color.BLACK));
        figures.put("c8", new Bishop("c8", ChessPiece.Color.BLACK));
        figures.put("d8", new Queen("d8", ChessPiece.Color.BLACK));
        figures.put("e8", new King("e8", ChessPiece.Color.BLACK));
        figures.put("f8", new Bishop("f8", ChessPiece.Color.BLACK));
        figures.put("g8", new Knight("g8", ChessPiece.Color.BLACK));
        figures.put("h8", new Rook("h8", ChessPiece.Color.BLACK));

        figures.put("a7", new Pawn("a7", ChessPiece.Color.BLACK));
        figures.put("b7", new Pawn("b7", ChessPiece.Color.BLACK));
        figures.put("c7", new Pawn("c7", ChessPiece.Color.BLACK));
        figures.put("d7", new Pawn("d7", ChessPiece.Color.BLACK));
        figures.put("e7", new Pawn("e7", ChessPiece.Color.BLACK));
        figures.put("f7", new Pawn("f7", ChessPiece.Color.BLACK));
        figures.put("g7", new Pawn("g7", ChessPiece.Color.BLACK));
        figures.put("h7", new Pawn("h7", ChessPiece.Color.BLACK));
    }

    static boolean isValidMove(Map<String, ChessPiece> figures, ChessPiece chessPiece, String newPosition) throws IllegalChessMoveException {
        String oldPosition = chessPiece.getPosition();

        if (chessPiece.getClass() == Queen.class || chessPiece.getClass() == Bishop.class || chessPiece.getClass() == Rook.class || chessPiece.getClass() == Pawn.class) {
            moveInLine(figures, oldPosition.charAt(0), oldPosition.charAt(1), newPosition.charAt(0), newPosition.charAt(1));
        }
        return true;
    }

    private static void moveInLine(Map<String, ChessPiece> figures, char fromLetter, char fromNumber, char toLetter, char toNumber) throws IllegalChessMoveException {
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
            if(fromLetter > 'h' || fromNumber > '8' || fromLetter < 'a' || fromNumber <= '0') break;
        }
    }
}
