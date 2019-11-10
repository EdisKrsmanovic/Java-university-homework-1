package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;
import ba.unsa.etf.rpr.Pieces.ChessPiece;
import ba.unsa.etf.rpr.Pieces.Bishop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void illegalMove1() {
        Bishop bishop = new Bishop("E2", ChessPiece.Color.WHITE);
        assertThrows(IllegalChessMoveException.class,
                () -> bishop.move("E4")
        );
    }

    @Test
    void moveDiagonal() {
        Bishop bishop = new Bishop("E1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> bishop.move("D2")
        );
    }

    @Test
    void illegalMove2() {
        Bishop bishop = new Bishop("C2", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> bishop.move("E2")
        );
    }

    @Test
    void illegalMove3() {
        Bishop bishop = new Bishop("C8", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> bishop.move("C4")
        );
    }

    @Test
    void illegalMove4() {
        Bishop bishop = new Bishop("C2", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> bishop.move("C4")
        );
    }

    @Test
    void illegalMove5() {
        Bishop bishop = new Bishop("C8", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> bishop.move("C2")
        );
    }

    @Test
    void illegalMove6() {
        Bishop bishop = new Bishop("C2", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> bishop.move("C8")
        );
    }

    @Test
    void legalMove1() {
        Bishop bishop = new Bishop("C2", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> bishop.move("G6")
        );
    }

    @Test
    void legalMove2() {
        Bishop bishop = new Bishop("G6", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> bishop.move("C2")
        );
    }

    @Test
    void legalMove3() {
        Bishop bishop = new Bishop("E4", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> bishop.move("B7")
        );
    }

    @Test
    void constructor1() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bishop("I2", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void constructor2() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bishop("B9", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void constructor3() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bishop("", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void moveIllegal1() {
        Bishop bishop = new Bishop("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> bishop.move("C0")
        );
    }

    @Test
    void moveIllegal2() {
        Bishop bishop = new Bishop("H1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> bishop.move("I1")
        );
    }

    @Test
    void moveIllegal3() {
        Bishop bishop = new Bishop("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> bishop.move("")
        );
    }
}