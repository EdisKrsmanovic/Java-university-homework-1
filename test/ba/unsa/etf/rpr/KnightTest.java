package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Pieces.ChessPiece;
import ba.unsa.etf.rpr.Pieces.Knight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void move() {
        Knight k = new Knight("B1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> k.move("C3")
        );

    }
}