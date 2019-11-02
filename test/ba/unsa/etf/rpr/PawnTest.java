package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Pieces.ChessPiece;
import ba.unsa.etf.rpr.Pieces.Pawn;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @org.junit.jupiter.api.Test
    void move1() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> p.move("E4")
        );
    }

}