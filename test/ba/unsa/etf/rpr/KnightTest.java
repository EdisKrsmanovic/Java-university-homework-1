package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;
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
    
    @Test
    void moveDiagonal() {
        Knight k = new Knight("E1", ChessPiece.Color.WHITE);
        assertThrows(IllegalChessMoveException.class,
                () -> k.move("D2")
        );
    }

    @Test
    void moveTwo() {
        Knight k = new Knight("C2", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> k.move("E2")
        );
    }

    @Test
    void moveBack() {
        Knight k = new Knight("E3", ChessPiece.Color.WHITE);
        assertThrows(IllegalChessMoveException.class,
                () -> k.move("E2")
        );
    }

    @Test
    void constructor1() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Knight("I2", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void constructor2() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Knight("B9", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void constructor3() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Knight("", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void moveIllegal1() {
        Knight k = new Knight("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> k.move("C0")
        );
    }

    @Test
    void moveIllegal2() {
        Knight k = new Knight("H1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> k.move("I1")
        );
    }

    @Test
    void moveIllegal3() {
        Knight k = new Knight("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> k.move("")
        );
    }
}