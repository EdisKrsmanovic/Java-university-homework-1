package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

import static ba.unsa.etf.rpr.Pieces.PiecesUtil.checkCorrectPosition;
import static ba.unsa.etf.rpr.Pieces.PiecesUtil.checkMove;

public class Bishop extends ChessPiece {
    public Bishop(String position, Color color) throws IllegalArgumentException {
        super(position, color);
        checkCorrectPosition(position);
    }

    @Override
    public void move(String position) throws IllegalArgumentException, IllegalChessMoveException {
        checkCorrectPosition(position);
        checkMove('B', getPosition(), position);
        super.setPosition(position);
    }
}
