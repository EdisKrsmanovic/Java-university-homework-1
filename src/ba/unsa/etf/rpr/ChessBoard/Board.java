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
        Map.Entry<String, ChessPiece> suitableFigure = figures.entrySet()
                .stream()
                .filter(e -> e.getValue().getColor() == color)
                .filter(e -> e.getValue().getClass() == type)
                .filter(e -> checkValidMove(position, e.getValue()))
                .findFirst()
                .orElse(null);
        if (suitableFigure == null) throw new IllegalChessMoveException();

        ChessPiece chessPiece = suitableFigure.getValue();
        String oldPosition = suitableFigure.getKey();
        String newPosition = position.toLowerCase();
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
                .filter(e -> !e.getValue().getColor().equals(color))
                .anyMatch(e ->checkValidMove(kingsPosition, e.getValue()));
    }

    private void moveFigure(ChessPiece chessPiece, String oldPosition, String newPosition) throws IllegalChessMoveException {
        ChessPiece otherChessPiece = figures.get(newPosition);
        if (otherChessPiece != null) {
            if (otherChessPiece.getColor() == chessPiece.getColor()) {
                throw new IllegalChessMoveException();
            }
        }
//        System.out.println(oldPosition + " " + newPosition);
        figures.remove(oldPosition);
        figures.put(newPosition, chessPiece);
        chessPiece.move(newPosition);
    }

    private boolean checkValidMove(String position, ChessPiece chessPiece) {
        try {
            String oldPosition = chessPiece.getPosition();
            String newPosition = position.toLowerCase();

            chessPiece.move(newPosition);

            if(chessPiece.getClass() == Pawn.class) chessPiece = new Pawn(oldPosition, chessPiece.getColor());
            else chessPiece.move(oldPosition);

            return isValidMove(figures, chessPiece, newPosition);

        } catch (Exception e) {
            return false;
        }
    }

    private boolean isKingOfGivenColor(ChessPiece.Color color, Map.Entry<String, ChessPiece> e) {
        return e.getValue().getClass() == King.class && e.getValue().getColor() == color;
    }
}
