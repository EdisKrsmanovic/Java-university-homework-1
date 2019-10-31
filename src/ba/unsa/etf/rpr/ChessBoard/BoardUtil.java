package ba.unsa.etf.rpr.ChessBoard;

import ba.unsa.etf.rpr.Pieces.*;

import java.util.Map;

public class BoardUtil {
    static void newGameState(Map<String, ChessPiece> figures) {
        figures.put("a1", new Rook("a1", ChessPiece.Color.WHITE));
        figures.put("b1", new Knight("b1", ChessPiece.Color.WHITE));
        figures.put("c1", new Bishop("c1", ChessPiece.Color.WHITE));
        figures.put("d1", new King("d1", ChessPiece.Color.WHITE));
        figures.put("e1", new Queen("e1", ChessPiece.Color.WHITE));
        figures.put("f1", new Bishop("f1", ChessPiece.Color.WHITE));
        figures.put("g1", new Knight("g1", ChessPiece.Color.WHITE));
        figures.put("h1", new Rook("h1", ChessPiece.Color.WHITE));

        figures.put("a2", new Pawn("a2", ChessPiece.Color.WHITE));
        figures.put("b2", new Pawn("b2", ChessPiece.Color.WHITE));
        figures.put("c2", new Pawn("c2", ChessPiece.Color.WHITE));
        figures.put("d2", new Pawn("d2", ChessPiece.Color.WHITE));
        figures.put("e2", new Pawn("e2", ChessPiece.Color.WHITE));
        figures.put("f2", new Pawn("f2", ChessPiece.Color.WHITE));
        figures.put("g2", new Pawn("g2", ChessPiece.Color.WHITE));
        figures.put("h2", new Pawn("h2", ChessPiece.Color.WHITE));


        figures.put("a8", new Rook("a8", ChessPiece.Color.WHITE));
        figures.put("b8", new Knight("b8", ChessPiece.Color.WHITE));
        figures.put("c8", new Bishop("c8", ChessPiece.Color.WHITE));
        figures.put("d8", new King("d8", ChessPiece.Color.WHITE));
        figures.put("e8", new Queen("e8", ChessPiece.Color.WHITE));
        figures.put("f8", new Bishop("f8", ChessPiece.Color.WHITE));
        figures.put("g8", new Knight("g8", ChessPiece.Color.WHITE));
        figures.put("h8", new Rook("h8", ChessPiece.Color.WHITE));

        figures.put("a7", new Pawn("a7", ChessPiece.Color.WHITE));
        figures.put("b7", new Pawn("b7", ChessPiece.Color.WHITE));
        figures.put("c7", new Pawn("c7", ChessPiece.Color.WHITE));
        figures.put("d7", new Pawn("d7", ChessPiece.Color.WHITE));
        figures.put("e7", new Pawn("e7", ChessPiece.Color.WHITE));
        figures.put("f7", new Pawn("f7", ChessPiece.Color.WHITE));
        figures.put("g7", new Pawn("g7", ChessPiece.Color.WHITE));
        figures.put("h7", new Pawn("h7", ChessPiece.Color.WHITE));
    }
}
