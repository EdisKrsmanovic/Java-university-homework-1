package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.ChessBoard.Board;
import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;
import ba.unsa.etf.rpr.Pieces.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board b = new Board();
        Scanner scanner = new Scanner(System.in);
        String unos;
        String player = "White";
		while (true) {
			ChessPiece.Color color = ChessPiece.Color.valueOf(player.toUpperCase());
			if(b.isCheck(color)) System.out.println("CHECK!!!");
			System.out.print(player + " move: ");
            unos = scanner.nextLine();
            if (unos.equals("X")) {
                System.out.println(player + " gave up!");
                break;
            }
            if (unos.length() != 2 && unos.length() != 3) {
                System.out.println("Neispravna unos!");
                continue;
            } else if (unos.length() == 2) {
                if (unos.charAt(0) < 'a' || unos.charAt(0) > 'h' || unos.charAt(1) < '1' || unos.charAt(1) > '8') {
                    System.out.println("Neispravan unos!");
                    continue;
                }
                try {
					b.move(Pawn.class, color, unos);
				} catch (IllegalChessMoveException e) {
					System.out.println("Ilegalan potez");
					continue;
				}
			} else {
            	char figureLetter = unos.charAt(0);
                if (unos.charAt(1) < 'a' || unos.charAt(1) > 'h' || unos.charAt(2) < '1' || unos.charAt(2) > '8' || (figureLetter != 'K' && figureLetter != 'Q' && figureLetter != 'R' && figureLetter != 'B' && figureLetter != 'N')) {
                    System.out.println("Neispravan unos!");
                    continue;
                }

                Class type;
                if(figureLetter == 'Q') type = Queen.class;
                else if(figureLetter == 'K') type = King.class;
                else if(figureLetter == 'N') type = Knight.class;
                else if(figureLetter == 'B') type = Bishop.class;
                else type = Rook.class;

				try {
					b.move(type, color, unos.substring(1,3));
				} catch (IllegalChessMoveException e) {
					System.out.println("Ilegalan potez");
					continue;
				}
			}
            player = player.equals("White") ? "Black" : "White";
        }
    }
}
