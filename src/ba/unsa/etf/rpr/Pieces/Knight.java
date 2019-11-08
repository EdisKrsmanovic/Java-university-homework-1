package ba.unsa.etf.rpr.Pieces;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;

import static ba.unsa.etf.rpr.Pieces.PiecesUtil.checkCorrectPosition;
import static ba.unsa.etf.rpr.Pieces.PiecesUtil.checkMove;

public class Knight extends ChessPiece {
    public Knight(String position, Color color) throws IllegalArgumentException {
        super(position, color);
        checkCorrectPosition(position);
    }

    @Override
    public void move(String position) throws IllegalArgumentException, IllegalChessMoveException {
        checkCorrectPosition(position);
        checkMove('N', getPosition(), position);
        super.setPosition(position);
    }
}
