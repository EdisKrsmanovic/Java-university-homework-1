package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

import static ba.unsa.etf.rpr.Pieces.PiecesUtil.checkMove;

public class Queen extends ChessPiece {
    public Queen(String position, Color color) throws IllegalArgumentException {
        super(position, color);
    }

    @Override
    public void move(String position) throws IllegalArgumentException, IllegalChessMoveException {
        super.move(position);
        checkMove('Q', getPosition(), position);
        super.setPosition(position);
    }
}
