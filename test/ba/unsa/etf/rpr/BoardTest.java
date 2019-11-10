package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.ChessBoard.Board;
import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;
import ba.unsa.etf.rpr.Pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    // Is the board usable after isCheck
    void someLegalMoves() {
        Board b = new Board();
        boolean no = b.isCheck(ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
                    b.move(Bishop.class, ChessPiece.Color.WHITE, "A6");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
                    b.move(King.class, ChessPiece.Color.WHITE, "E2");
                    b.move(King.class, ChessPiece.Color.WHITE, "E3");
                }
        );
    }

    @Test
    // Pawn eats diagonally
    void pawnDiagonal() {
        Board b = new Board();
        assertDoesNotThrow(
            () -> {
                b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
                b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
                b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
                b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
                b.move(Pawn.class, ChessPiece.Color.WHITE, "C8");
            }
        );
    }

    @Test
    // Check by pawn
    void isCheck() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
        } catch(Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // Will queen be moved by isCheck
    void isCheckUsable() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
            b.move(Queen.class, ChessPiece.Color.WHITE, "E2");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F8");
        } catch(Exception e) {
            // Do nothing
        }
        b.isCheck(ChessPiece.Color.BLACK);
        assertDoesNotThrow(
            () -> {
                b.move(Queen.class, ChessPiece.Color.WHITE, "D3");
            }
        );
    }

    @Test
    // No check
    void isCheck2() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "C8");
            b.move(Queen.class, ChessPiece.Color.WHITE, "E2");
        } catch(Exception e) {
            // Do nothing
        }
        assertFalse(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // Check by queen
    void isCheck3() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "C8");
            b.move(Queen.class, ChessPiece.Color.WHITE, "E2");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F8");
        } catch(Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // Queen, bishop and rook can't jump pieces
    void jumpPiece() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Rook.class, ChessPiece.Color.BLACK, "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Bishop.class, ChessPiece.Color.BLACK, "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Queen.class, ChessPiece.Color.BLACK, "A5")
                )
        );
    }


    // Same test with other move method

    @Test
    // Is the board usable after isCheck
    void someLegalMoves1() {
        Board b = new Board();
        boolean no = b.isCheck(ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    b.move("E2", "E4");
                    b.move("F1", "A6");
                    b.move("B1", "C3");
                    b.move("E1", "E2");
                    b.move("E2", "E3");
                }
        );
    }

    @Test
    // Pawn eats diagonally, check by queen
    void pawnDiagonal1() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("E2", "E4");
                    b.move("E4", "E5");
                    b.move("E5", "E6");
                    b.move("E6", "D7");
                    b.move("D7", "C8");
                }
        );
    }

    @Test
    // Check by pawn
    void isCheck1() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
        } catch(Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // No check
    void isCheck12() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
            b.move("D7", "C8");
            b.move("D1", "E2");
        } catch(Exception e) {
            // Do nothing
        }
        assertFalse(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // Check by queen
    void isCheck13() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
            b.move("D7", "C8");
            b.move("D1", "E2");
            b.move("F2", "F4");
            b.move("F4", "F5");
            b.move("F5", "F6");
            b.move("F6", "E7");
            b.move("E7", "F8");
        } catch(Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
    // Queen, bishop and rook can't jump pieces
    void jumpPiece1() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("H8", "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("F8", "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("D8", "A5")
                )
        );
    }

    @Test
    // Check by queen
    void isCheckUsable1() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
            b.move("D7", "C8");
            b.move("D1", "E2");
        } catch(Exception e) {
            // Do nothing
        }
        b.isCheck(ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> {
                    b.move("E2", "D3");
                }
        );
    }

    @Test
    void moveThrowsIfIsCheckNotPrevented() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("C2", "C3");
                    b.move("D7", "D6");
                    b.move("D1", "A4");
                }
        );
        assertThrows(IllegalChessMoveException.class, () -> b.move("E7","E6"));
    }

    @Test
    void moveDoesNotThrowIfIsCheckIsPrevented() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("C2", "C3");
                    b.move("D7", "D6");
                    b.move("D1", "A4");
                }
        );
        assertDoesNotThrow(() -> b.move("B8","C6"));
    }

    @Test
    void combinationOfIllegalAndLegalMoves() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("C2", "C3");
                    b.move("D7", "D6");
                    b.move("D1", "A4");
                }
        );
        assertThrows(IllegalChessMoveException.class, () -> b.move("E7","E6"));
        assertDoesNotThrow(() -> b.move("B8","C6"));
    }

    @Test
    void whitePawnCannotJumpPieces() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("A7", "A5");
                    b.move("A5", "A4");
                    b.move("A4", "A3");
                }
        );
        assertThrows(IllegalChessMoveException.class, () -> b.move("A2","A4"));
    }

    @Test
    void blackPawnCannotJumpPieces() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("H2", "H4");
                    b.move("H4", "H5");
                    b.move("H5", "H6");
                }
        );
        assertThrows(IllegalChessMoveException.class, () -> b.move("H7","H5"));
    }

    @Test
    void whitePawnCannotEatVertically() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("A7", "A5");
                    b.move("A5", "A4");
                }
        );
        assertThrows(IllegalChessMoveException.class, () -> b.move("A2","A4"));
        assertDoesNotThrow(
                () -> {
                    b.move("A4", "A3");
                }
        );
        assertThrows(IllegalChessMoveException.class, () -> b.move("A2","A3"));
    }

    @Test
    void blackPawnCannotEatVertically() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("H2", "H4");
                    b.move("H4", "H5");
                }
        );
        assertThrows(IllegalChessMoveException.class, () -> b.move("H7","H5"));
        assertDoesNotThrow(
                () -> {
                    b.move("H5", "H6");
                }
        );
        assertThrows(IllegalChessMoveException.class, () -> b.move("H7","H6"));
    }

    @Test
    void moveShouldWorkWithLowercaseLetters1() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("e2", "E4");
                    b.move("e4", "E5");
                    b.move("e5", "e6");
                    b.move("e6", "D7");
                    b.move("d7", "c8");
                }
        );
    }

    @Test
    void moveShouldWorkWithLowercaseLetters2() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
                    b.move(Pawn.class, ChessPiece.Color.WHITE,  "E5");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "e6");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "c8");
                }
        );
    }
}