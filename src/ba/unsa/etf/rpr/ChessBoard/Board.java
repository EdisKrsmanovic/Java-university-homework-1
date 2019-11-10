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
        if (suitableFigure == null) throw new IllegalChessMoveException("No figure found to move");
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
            throw new IllegalChessMoveException("Invalid move, you are trying to jump over figures with a non-Knight figure");
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

    //This method should check if someone is trying to eat a King which is forbidden, but we aren't supposed to implement all of the rules apparently.
    private void moveFigure(ChessPiece chessPiece, String oldPosition, String newPosition) throws IllegalChessMoveException {
        boolean isInCheck = isCheck(chessPiece.getColor());
        ChessPiece otherChessPiece = figures.get(newPosition);
        if (otherChessPiece != null) {
            if (otherChessPiece.getColor() == chessPiece.getColor()) {
                throw new IllegalChessMoveException("Illegal move, you are trying to move a figure onto a figure of same color");
            }
        }
        if(!checkValidMove(oldPosition, newPosition, chessPiece)) throw new IllegalChessMoveException("Illegal move, you are trying to move a figure in a way its not supposed to move");
        ChessPiece temporaryChessPiece = figures.get(newPosition);
        figures.remove(oldPosition);
        figures.put(newPosition, chessPiece);
        chessPiece.move(newPosition);
                                                            //Checking if a move is preventing check even tho it was not defined in a text given to us.
        if(isInCheck && isCheck(chessPiece.getColor())) {   //Currently not checking if a move is going to cause a self-check because "we are not implementing all of the rules"
            figures.put(newPosition, temporaryChessPiece);
            figures.put(oldPosition, chessPiece);
            throw new IllegalChessMoveException("Illegal move, you are trying to move a figure that is not preventing a check");
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
                    if(figures.get(newPosition) == null) throw new IllegalChessMoveException("Illegal move, you are trying to eat nothing with a Pawn");
                    if(figures.get(newPosition).getColor() == chessPiece.getColor()) throw new IllegalChessMoveException("Illegal move, you are trying to move a Pawn onto a figure of same color");
                }
                else if(figures.get(newPosition) != null)  throw new IllegalChessMoveException("Illegal move, you are trying to eat a figure with a Pawn in a way its not supposed to be done");
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
