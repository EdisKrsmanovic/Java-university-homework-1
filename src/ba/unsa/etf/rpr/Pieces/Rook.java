package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

import static ba.unsa.etf.rpr.Pieces.PiecesUtil.checkMove;

public class Rook extends ChessPiece {
    public Rook(String position, Color color) throws IllegalArgumentException {
        super(position, color);
    }

    @Override
    public void move(String position) throws IllegalArgumentException, IllegalChessMoveException {
        super.move(position);
        checkMove('R', getPosition(), position);
        super.setPosition(position);
    }
}
