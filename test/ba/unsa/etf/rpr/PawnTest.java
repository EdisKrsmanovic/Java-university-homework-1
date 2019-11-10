package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;
import ba.unsa.etf.rpr.Pieces.ChessPiece;
import ba.unsa.etf.rpr.Pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void move1() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> p.move("E4")
        );
    }

    @Test
    void moveDiagonal() {
        Pawn pawn = new Pawn("E1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> pawn.move("D2")
        );
    }

    @Test
    void moveTwo() {
        Pawn pawn = new Pawn("C2", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> pawn.move("E2")
        );
    }

    @Test
    void moveTwo2() {
        Pawn pawn = new Pawn("C8", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> pawn.move("C6")
        );
        assertThrows(IllegalChessMoveException.class,
                () -> pawn.move("C4")
        );
        assertDoesNotThrow(
                () -> pawn.move("C5")
        );
    }

    @Test
    //Cannot move backwards
    void moveTwo3() {
        Pawn pawn = new Pawn("C2", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> pawn.move("C4")
        );
    }

    @Test
    void moveBack() {
        Pawn pawn = new Pawn("E3", ChessPiece.Color.WHITE);
        assertThrows(IllegalChessMoveException.class,
                () -> pawn.move("E2")
        );
    }

    @Test
    void constructor1() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Pawn("I2", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void constructor2() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Pawn("B9", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void constructor3() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Pawn("", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void moveIllegal1() {
        Pawn pawn = new Pawn("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> pawn.move("C0")
        );
    }

    @Test
    void moveIllegal2() {
        Pawn pawn = new Pawn("H1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> pawn.move("I1")
        );
    }

    @Test
    void moveIllegal3() {
        Pawn pawn = new Pawn("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> pawn.move("")
        );
    }
}