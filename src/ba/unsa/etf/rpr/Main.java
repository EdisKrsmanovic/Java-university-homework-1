package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.ChessBoard.Board;
import ba.unsa.etf.rpr.Exceptions.IllegalChessMoveException;
import ba.unsa.etf.rpr.Pieces.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board b = new Board();
        Scanner scanner = new Scanner(System.in);
        String input;
        String player = "White";
		while (true) {
			ChessPiece.Color color = ChessPiece.Color.valueOf(player.toUpperCase());
			if(b.isCheck(color)) System.out.println("CHECK!!!");
			System.out.print(player + " move: ");
            input = scanner.nextLine();
            if (input.equals("X")) {
                System.out.println(player + " gave up!");
                break;
            }
            if (input.length() != 2 && input.length() != 3) {
                System.out.println("Invalid input!");
                continue;
            } else if (input.length() == 2) {
                if (input.charAt(0) < 'a' || input.charAt(0) > 'h' || input.charAt(1) < '1' || input.charAt(1) > '8') {
                    System.out.println("Invalid input!");
                    continue;
                }
                try {
					b.move(Pawn.class, color, input);
				} catch (IllegalChessMoveException e) {
                    System.out.println("Illegal move");
					continue;
				}
			} else {
            	char figureLetter = input.charAt(0);
                if (input.charAt(1) < 'a' || input.charAt(1) > 'h' || input.charAt(2) < '1' || input.charAt(2) > '8' || (figureLetter != 'K' && figureLetter != 'Q' && figureLetter != 'R' && figureLetter != 'B' && figureLetter != 'N')) {
                    System.out.println("Invalid input!");
                    continue;
                }

                Class type;
                if(figureLetter == 'Q') type = Queen.class;
                else if(figureLetter == 'K') type = King.class;
                else if(figureLetter == 'N') type = Knight.class;
                else if(figureLetter == 'B') type = Bishop.class;
                else type = Rook.class;

				try {
					b.move(type, color, input.substring(1,3));
				} catch (IllegalChessMoveException e) {
                    System.out.println("Illegal move");
					continue;
				}
			}
            player = player.equals("White") ? "Black" : "White";
        }
    }
}
