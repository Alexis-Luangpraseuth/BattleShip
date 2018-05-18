package luangpraseuth.alexis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleShip {
	public static int coordMax = 10;
	public static Scanner reader = new Scanner(System.in);
	public static String error = "";
	public static String gameType = "Human vs IA";

	public static void main(String args[]) {

		String winner = "";
		boolean switchStartingPlayer = false;
		boolean run = true;

		Grid gridPlayer1 = new Grid();
		Grid gridPlayer2 = new Grid();

		System.out.println("choose your game type: 1= 'Human vs Human' , 2= 'Human vs IA'");
		gameType = reader.next().equals(1) ? "Human vs Human" : "Human vs IA";
		Player player1 = new Human(gridPlayer1, gridPlayer2);
		;
		Player player2;
		if (gameType.equals("Human vs Human"))
			player2 = new Human(gridPlayer2, gridPlayer1);
		else
			System.out.println("choose the difficulty of the IA: 1=easy  2=medium 3=hard");
			player2 = new IA(gridPlayer2, gridPlayer1,Integer.parseInt(reader.next()));

		while (run) {
			Game game = new Game(player1, player2);
			if (switchStartingPlayer)
				game.switchActivePlayer();
			Player activePlayer = game.getActivePlayer();
			Player oppositePlayer = game.getOppositePlayer();
			List<Player> players = new ArrayList<Player>();
			players.add(activePlayer);
			players.add(oppositePlayer);

			for (int i = 0; i < players.size(); i++) {
				Player player = players.get(i);
				if (player instanceof Human) {
				int num = i + 1;
				System.out.println("player " + num + "  choose your name : ");
				player.setName(reader.next());
				}
				String playerName = player.getName();
				System.out.println(playerName + " you are going to place your fleet");
				List<String> fleetString = new ArrayList<String>();
				fleetString.add("Destroyer");
				/*
				 * battlecrew.add("Submarine"); battlecrew.add("Cruiser");
				 * battlecrew.add("Battleship"); battlecrew.add("Carrier");
				 */
				player.placeFleet(fleetString);
			
			}
			while (!game.isOver()) {
				Grid activePlayerGrid = activePlayer.getMyGrid();
				Grid oppositePlayerGrid = activePlayer.getEnnemyGrid();
				String activePlayerName = activePlayer.getName();
				System.out.println(activePlayerName + " it is your turn to play");
				// showing our battlefield and opponent battlefield
				System.out.println("This is your battlefield :");
				System.out.println(activePlayerGrid.toString("ally"));
				System.out.println("This is your ennemy battlefield :");
				System.out.println(oppositePlayerGrid.toString("opponent"));
				System.out.println("Choose a target for your next attack. Exemple: 'A1'");
				System.out.println("Target :");
				String target = activePlayer.shoot();
				int lat = Tools.convertLetterToInt(Tools.getLetter(target));
				int longitude = Tools.getInt(target);
				Square squareAttacked = oppositePlayerGrid.getGrid()[lat][longitude];
				boolean alreadyAttacked = false;
				if(squareAttacked.isHit())
					alreadyAttacked = true;
				squareAttacked.setHit(true);
				String res = "";
				List<Ship> fleet = oppositePlayer.getFleet();
				// checking if a ship as been touched
				if (squareAttacked.getColor() == Color.red && !alreadyAttacked) {
					// We check if the ship is destroyed or not
						Ship ship = fleet.get(squareAttacked.getIdBateau());
						res = ship.isDestroyed() ? "Sunk!" : "Hit!";
							
						}
				else {
					res = alreadyAttacked ? "already attacked" :"Missed!";
				}
				if (res.equals("Sunk!")) {
					if(oppositePlayer.hasLost()) {
						game.setOver(true);
						winner = activePlayerName;
					}

				}
				System.out.println("result: " + res);
				game.switchActivePlayer();

			}
			System.out.println("Congratulation " + winner
					+ ", the game is over, you have destroyed the whole fleet of your opponent");
			System.out.println("Do you want to play a new game?  Press 1 for Yes ");
			run = reader.next().equals(1);
			switchStartingPlayer = !(switchStartingPlayer);
		}
	}
}
