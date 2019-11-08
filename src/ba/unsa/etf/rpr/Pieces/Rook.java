package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

import static ba.unsa.etf.rpr.Pieces.PiecesUtil.checkCorrectPosition;
import static ba.unsa.etf.rpr.Pieces.PiecesUtil.checkMove;

public class Rook extends ChessPiece {
    public Rook(String position, Color color) throws IllegalArgumentException {
        super(position, color);
        checkCorrectPosition(position);
    }

    @Override
    public void move(String position) throws IllegalArgumentException, IllegalChessMoveException {
        checkCorrectPosition(position);
        checkMove('R', getPosition(), position);
        super.setPosition(position);
    }
}
