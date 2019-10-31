package ba.unsa.etf.rpr.ChessBoard;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;
import ba.unsa.etf.rpr.Pieces.ChessPiece;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<String, ChessPiece> figures = new HashMap<>();

    public Board() {
        BoardUtil.newGameState(figures);
    }

    public void move(Class type, ChessPiece.Color color, String position) throws IllegalChessMoveException {
        if(figures.get(position) != null) {
            throw new IllegalChessMoveException();
        }

        ChessPiece chessPiece = (ChessPiece) figures.entrySet()
                .stream()
                .filter(e -> hasSameColorSameTypeAndIsValidMove(type, color, position, e.getValue()))
                .findFirst()
                .orElse(null);

        if(chessPiece == null) {
            throw new IllegalChessMoveException();
        }

        chessPiece.move(position);
    }

    public void move(String oldPosition, String newPosition) throws IllegalChessMoveException {
        oldPosition = oldPosition.toLowerCase();
        newPosition = newPosition.toLowerCase();

        ChessPiece chessPiece = figures.get(oldPosition);

        if(chessPiece == null) {
            throw new IllegalArgumentException();
        }
        else if(!isValidMove(chessPiece, newPosition)) {
            throw new IllegalChessMoveException();
        }

        chessPiece.move(newPosition);
    }

    public boolean isCheck(ChessPiece.Color color) {
        return false;
    }

    private boolean hasSameColorSameTypeAndIsValidMove(Class type, ChessPiece.Color color, String position, ChessPiece chessPiece) {
        return isValidMove(chessPiece, position.toLowerCase()) && chessPiece.getColor().equals(color) && chessPiece.getClass() == type;
    }

    private boolean isValidMove(ChessPiece chessPiece, String newPosition) {
        return false;
    }
}
