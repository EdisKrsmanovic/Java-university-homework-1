package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

import static ba.unsa.etf.rpr.Pieces.PiecesUtil.checkMove;

public class Bishop extends ChessPiece {
    public Bishop(String position, Color color) throws IllegalArgumentException {
        super(position, color);
    }

    @Override
    public void move(String position) throws IllegalArgumentException, IllegalChessMoveException {
        super.move(position);
        checkMove('B', getPosition(), position);
        super.setPosition(position);
    }
}
