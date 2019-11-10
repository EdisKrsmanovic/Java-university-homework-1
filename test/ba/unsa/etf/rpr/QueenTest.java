package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;
import ba.unsa.etf.rpr.Pieces.ChessPiece;
import ba.unsa.etf.rpr.Pieces.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void move1() {
        Queen queen = new Queen("E2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> queen.move("E4")
        );
    }

    @Test
    void moveDiagonal() {
        Queen queen = new Queen("E1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> queen.move("D2")
        );
    }

    @Test
    void moveTwo() {
        Queen queen = new Queen("C2", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> queen.move("E2")
        );
    }

    @Test
    void moveTwo2() {
        Queen queen = new Queen("C8", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> queen.move("C6")
        );
        assertDoesNotThrow(
                () -> queen.move("C4")
        );
        assertDoesNotThrow(
                () -> queen.move("C5")
        );
    }

    @Test
    void moveTwo3() {
        Queen queen = new Queen("C2", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> queen.move("C4")
        );
    }

    @Test
    void moveForward() {
        Queen queen = new Queen("C8", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> queen.move("C2")
        );
    }

    @Test
    void moveBackwards() {
        Queen queen = new Queen("C2", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> queen.move("C8")
        );
    }

    @Test
    void moveBack() {
        Queen queen = new Queen("E3", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> queen.move("E2")
        );
    }

    @Test
    void constructor1() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Queen("I2", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void constructor2() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Queen("B9", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void constructor3() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Queen("", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void moveIllegal1() {
        Queen queen = new Queen("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> queen.move("C0")
        );
    }

    @Test
    void moveIllegal2() {
        Queen queen = new Queen("H1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> queen.move("I1")
        );
    }

    @Test
    void moveIllegal3() {
        Queen queen = new Queen("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> queen.move("")
        );
    }
}