package luangpraseuth.alexis;
import java.util.Scanner;

public class Battleship {
	public static final int coordMax = 10;
	public static final int nbShips = 5;
	public static Scanner reader = new Scanner(System.in);
	public static String error = "";
	public static String gameType = "Human vs IA";
	public static boolean switchStartingPlayer = false;
	
	
	public static void main(String args[]) {
	
		boolean matchContinue = true;
		boolean newMatch = true;
		//the programme is continuing until players don't want a new match.
		while (newMatch) {
		//creating players grid
		Grid gridPlayer1 = new Grid();
		Grid gridPlayer2 = new Grid();
		boolean check = false;
		String response ="";
		while(!check) {
		System.out.println("choose your game type: type 1 for 'Human vs Human' , type 2 for = 'Human vs AI'");
		response = reader.next();
		check = Tools.isCorrectResponseGameType(response);
		if(!check)
			System.out.println(error);
		}
		gameType = response.equals("1") ? "Human vs Human" : "Human vs IA";
		Player player1 = new Human(gridPlayer1, gridPlayer2);
		Player player2;
		if (gameType.equals("Human vs Human"))
			player2 = new Human(gridPlayer2, gridPlayer1);
		else {
			check = false;
			while(!check) {
				System.out.println("choose the difficulty of the IA: 1=easy  2=medium 3=hard");
				response = reader.next();
				check = Tools.isCorrectResponseDifficulty(response);
				if(!check)
					System.out.println(error);
				}
			
			if(response.equals("1"))
				player2 = new EasyAI(gridPlayer2, gridPlayer1);
			else if(response.equals("2"))
				player2 = new MediumAI(gridPlayer2, gridPlayer1);
			else
				player2 = new HardAI(gridPlayer2, gridPlayer1);
		}
			//a match means that the players don't change, and every game played counts for the score of the players.
			while (matchContinue) {
				Game game = new Game(player1, player2);
				//the setup is the placement of the fleet for each player.
				game.setup();
				//the method play is running until we have a winner.
				game.play();
				System.out.println("Congratulation " + game.getActivePlayer().getName()
						+ " the game is over, you have destroyed the whole fleet of your opponent");
				System.out.println("Match score : "+ player1.getName() +": " + player1.getScore() + " win(s), "
						+ player2.getName() +": " + player2.getScore() + " win(s).");
				System.out.println("Do you want to play a new game with the same players?  Press 1 for Yes, 2 for No ");
				matchContinue = reader.next().equals("1");
				if(matchContinue)
					switchStartingPlayer = !(switchStartingPlayer);
					gridPlayer1.reset();
					gridPlayer2.reset();
			}
			
			System.out.println("Do you want to play a new match with different players?  Press 1 for Yes, 2 for No ");
			newMatch = reader.next().equals("1");
			matchContinue = true;
		}
	}
}
