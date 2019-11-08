package ba.unsa.etf.rpr.ChessBoard;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;
import ba.unsa.etf.rpr.Pieces.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static ba.unsa.etf.rpr.ChessBoard.BoardUtil.isValidMove;

public class Board {
    private Map<String, ChessPiece> figures = new HashMap<>();

    public Board() {
        BoardUtil.newGameState(figures);
    }

    public void move(Class type, ChessPiece.Color color, String position) throws IllegalChessMoveException {
        String newPosition = position.toLowerCase();
        Map.Entry<String, ChessPiece> suitableFigure = figures.entrySet()
                .stream()
                .filter(e -> e.getValue() != null)
                .filter(e -> e.getValue().getColor() == color)
                .filter(e -> e.getValue().getClass() == type)
                .filter(e -> checkValidMove(e.getKey(), newPosition, e.getValue()))
                .findFirst()
                .orElse(null);
        if (suitableFigure == null) throw new IllegalChessMoveException();
        ChessPiece chessPiece = suitableFigure.getValue();
        String oldPosition = suitableFigure.getKey();
        moveFigure(chessPiece, oldPosition, newPosition);
    }

    public void move(String oldPosition, String newPosition) throws IllegalChessMoveException {
        oldPosition = oldPosition.toLowerCase();
        newPosition = newPosition.toLowerCase();

        ChessPiece chessPiece = figures.get(oldPosition);
        if (chessPiece == null) {
            throw new IllegalArgumentException();
        }
        if (!isValidMove(figures, chessPiece, newPosition)) {
            throw new IllegalChessMoveException();
        }
        moveFigure(chessPiece, oldPosition, newPosition);
    }

    public boolean isCheck(ChessPiece.Color color) {
        String kingsPosition = Objects.requireNonNull(figures.entrySet()
                .stream()
                .filter(e -> isKingOfGivenColor(color, e))
                .findFirst()
                .orElse(null))
                .getKey();

        return figures.entrySet()
                .stream()
                .filter(e -> isOfOpositeColor(color, e))
                .anyMatch(e -> checkValidMove(e.getKey(), kingsPosition, e.getValue()));
    }

    private void moveFigure(ChessPiece chessPiece, String oldPosition, String newPosition) throws IllegalChessMoveException {
        boolean isInCheck = isCheck(chessPiece.getColor());
        ChessPiece otherChessPiece = figures.get(newPosition);
        if (otherChessPiece != null) {
            if (otherChessPiece.getColor() == chessPiece.getColor()) {
                throw new IllegalChessMoveException();
            }
        }
        if(!checkValidMove(oldPosition, newPosition, chessPiece)) throw new IllegalChessMoveException();
        ChessPiece temporaryChessPiece = figures.get(newPosition);
        figures.remove(oldPosition);
        figures.put(newPosition, chessPiece);
        chessPiece.move(newPosition);
        if(isInCheck && isCheck(chessPiece.getColor())) {
            figures.put(newPosition, temporaryChessPiece);
            figures.put(oldPosition, chessPiece);
            throw new IllegalChessMoveException();
        }
    }

    private boolean checkValidMove(String oldPosition, String newPosition, ChessPiece chessPiece) {
        try {
            oldPosition = oldPosition.toLowerCase();
            newPosition = newPosition.toLowerCase();

            chessPiece.move(newPosition);

            if(chessPiece.getClass() == Pawn.class) {
                chessPiece = new Pawn(oldPosition, chessPiece.getColor());
                if(oldPosition.charAt(0) != newPosition.charAt(0) && oldPosition.charAt(1) != newPosition.charAt(1)) {
                    if(figures.get(newPosition) == null) throw new IllegalChessMoveException();
                    if(figures.get(newPosition).getColor() == chessPiece.getColor()) throw new IllegalChessMoveException();
                }
                else if(figures.get(newPosition) != null) {
                    throw new IllegalChessMoveException();

                }
            }
            else chessPiece.move(oldPosition);

            return isValidMove(figures, chessPiece, newPosition);

        } catch (Exception e) {
            return false;
        }
    }
    private boolean isOfOpositeColor(ChessPiece.Color color, Map.Entry<String, ChessPiece> e) {
        if(e.getValue() == null) return false;
        else return !e.getValue().getColor().equals(color);
    }
    private boolean isKingOfGivenColor(ChessPiece.Color color, Map.Entry<String, ChessPiece> e) {
        if(e.getValue() == null) return false;
        return e.getValue().getClass() == King.class && e.getValue().getColor() == color;
    }
}
