package fr.battleship;

import java.io.FileWriter;
import java.io.IOException;

import luangpraseuth.alexis.*;

public class TestIA {

	public static void main(String[] args) throws IOException {
		String res = "AI Name; score; AI Name2;score2\n";
		boolean matchContinue = true;
		//the programme stops when the 300 games between AI have been played, each 100 games, we switch players.
		int i = 300;
		while (i > 0) {
			Grid gridPlayer1 = new Grid();
			Grid gridPlayer2 = new Grid();
			Player player1;
			Player player2;
			if (i > 200) {
				player1 = new EasyAI(gridPlayer1, gridPlayer2);
				player2 = new MediumAI(gridPlayer2, gridPlayer1);
			} else if (i > 100) {
				player1 = new EasyAI(gridPlayer1, gridPlayer2);
				player2 = new HardAI(gridPlayer2, gridPlayer1);
			} else {
				player1 = new MediumAI(gridPlayer1, gridPlayer2);
				player2 = new HardAI(gridPlayer2, gridPlayer1);
			}

			while (matchContinue) {
				Game game = new Game(player1, player2);
				game.setup();
				game.play();
				if (matchContinue)
					Battleship.switchStartingPlayer = !(Battleship.switchStartingPlayer);
				gridPlayer1.reset();
				gridPlayer2.reset();
				i = i - 1;
				if (i == 200 || i == 100 || i == 0) {
					matchContinue = false;
					if (i == 200) {
						res += "AI Level Beginner;" + player1.getScore() + "; Level Medium;" + player2.getScore() +"\n";
						System.out.println(res);
					}
					if (i == 100) {
						res += "AI Level Beginner;" + player1.getScore() + "; Level Hard;" + player2.getScore()+"\n";
						System.out.println(res);
					}

					if (i == 0) {
						res += "AI Level Medium;" + player1.getScore() + "; Level Hard;" + player2.getScore();
						System.out.println(res);
					}

				}
			}

			matchContinue = true;
		}

	      FileWriter writer = null;
	 //creating the file ai_proof.csv in our current directory, the file contains the result of the matches between the AI.
	 try {

	     writer = new FileWriter("ai_proof.csv");
	     writer.append(res);
	

	     System.out.println("CSV file is created...");

	  } catch (IOException e) {
	     e.printStackTrace();
	  } finally {
	        try {
	      writer.flush();
	      writer.close();
	        } catch (IOException e) {
	      e.printStackTrace();
	}
	}

	}
}
