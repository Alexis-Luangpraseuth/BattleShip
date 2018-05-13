import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class BattleShip {
	public static int coordMax = 10;
	public static Scanner reader = new Scanner(System.in);
	public static String error = "";
	
	public static boolean isCorrectCoordinate(String coordinate) {
	boolean res =  coordinate.matches("^[A-Z][0-9]{1,2}$");
	if(res==false)
		error ="The coordinate is not correctly wrote, please try again and be careful, exemple of coordinate : 'A1'";
	return res;
	}
	
	public static char getLetter(String coordinate) {
		char res = coordinate.charAt(0);
		return res;
	}
	
	public static int getInt(String coordinate) {
		String intStartCoord = coordinate.substring(1);
		int res = Integer.parseInt(intStartCoord) - 1;
		return res;
	}

	public static boolean isInGridCoordinate(String coordinate) {
		boolean res = false;
		int longitude = convertLetterToInt(getLetter(coordinate));
		int latitude = getInt(coordinate);
		if (longitude >= 0 && longitude < coordMax && latitude >= 0 && latitude < coordMax)
			res = true;
		if(res == false)
			error = "The coordinate is out of the battlefield";
		return res;
	}

	public static int getShipSize(String shipName) {
		int res;
		switch (shipName) {
		case "Destroyer":
			res = 2;
			break;
		case "Submarine":
			res = 3;
			break;
		case "Cruiser":
			res = 3;
			break;
		case "Battleship":
			res = 4;
			break;
		case "Carrier":
			res = 5;
			break;
		default:
			res = -1;
		}
		return res;
	}

	public static boolean coordinatesAreAvaible(String startCoord, String endCoord, String shipName, Grid grid) {
		boolean res = true;
		char startLetter = getLetter(startCoord);
		char endLetter = getLetter(endCoord);
		int startInt = getInt(startCoord);
		int endInt = getInt(endCoord);
		int size = getShipSize(shipName);
		if (startLetter == endLetter) {
			if (startInt == endInt - size || startInt == endInt + size) {
				for (int k = startInt; k <= endInt; k++) {
					int lat = convertLetterToInt(startLetter);
					Square square = grid.getGrid()[lat][k];
					if (square.getColor() == Color.red) {
						res = false;
						error = "One of those positions is already taken by another ship";
					}
				}
			} else {
				error = "You did not put the correct size for this ship";
				res = false;
			}
		} else if (startInt == endInt) {
			if (startLetter == endLetter - size || startLetter == endLetter + size) {
				for (char k = startLetter; k <= endLetter; k++) {
					int lat = convertLetterToInt(k);
					Square square = grid.getGrid()[lat][startInt];
					if (square.getColor() == Color.red)
						res = false;
				}
			}
			else {
				error = "You did not put the correct size for this ship";
				res = false;
			}
		} else {
			res = false;
			error = "You did not put the correct size for this ship, or it is not placed vertically/horizontally";
		}
		if (res == false)
			System.out.println(error);
		return res;
	}

	private static int convertLetterToInt(char val) {
		int res = -1;
		switch (val) {
		case 'A':
			res = 0;
			break;
		case 'B':
			res = 1;
			break;
		case 'C':
			res = 2;
			break;
		case 'D':
			res = 3;
			break;
		case 'E':
			res = 4;
			break;
		case 'F':
			res = 5;
			break;
		case 'G':
			res = 6;
			break;
		case 'H':
			res = 7;
			break;
		case 'I':
			res = 8;
			break;
		case 'J':
			res = 9;
			break;
		default:
			res = -1;
			break;
		}
		return res;
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		String winner = "";
		Player player1 = new Player();
		Player player2 = new Player();
		game.setActivePlayer(player1);
		game.setOppositePlayer(player2);
		Grid gridPlayer1 = new Grid();
		Grid gridPlayer2 = new Grid();
		player1.setMyGrid(gridPlayer1);
		player1.setEnnemyGrid(gridPlayer2);
		player2.setMyGrid(gridPlayer2);
		player2.setEnnemyGrid(gridPlayer1);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int num=i+1;
			System.out.println("player " + num + "  choose your name : ");
			player.setName(reader.next());
			String playerName = player.getName();
			List<Ship> fleet = new ArrayList<Ship>();
			System.out.println(playerName + " you are going to place your fleet");
			List<String> battlecrew = new ArrayList<String>();
			battlecrew.add("Destroyer");
			/*battlecrew.add("Submarine");
			battlecrew.add("Cruiser");
			battlecrew.add("Battleship");
			battlecrew.add("Carrier");*/
			for (int j = 0; j < battlecrew.size(); j++) {
				String shipName = battlecrew.get(j);
				boolean check = false;
				while (!check) {
					System.out.println("give start coordinates for your " + shipName + "(" + getShipSize(shipName)
							+ " squares), exemple: 'A1'");
					String coordS = reader.next();
					if(isCorrectCoordinate(coordS)) {
					if (isInGridCoordinate(coordS)) {
						System.out.println("give end coordinates for your " + shipName + "( " + getShipSize(shipName)
								+ " squares), exemple: 'A3'");
						String coordE = reader.next();
						if(isCorrectCoordinate(coordE)){
						if (isInGridCoordinate(coordE)) {
							Grid grid = player.getMyGrid();
							if (coordinatesAreAvaible(coordS, coordE, shipName, grid)) {
								check = true;
								List<String> coordinates = new ArrayList<String>();
								List<Square> squares = new ArrayList<Square>();
								char letterStartCoord = coordS.charAt(0);
								char letterEndCoord = coordE.charAt(0);
								int intStartCoord = getInt(coordS);
								int intEndCoord = getInt(coordE);

								if (letterStartCoord == letterEndCoord) {
									for (int k = intStartCoord; k <= intEndCoord; k++) {
										coordinates.add(letterStartCoord + Integer.toString(k + 1));
										int lat = convertLetterToInt(letterStartCoord);
										player.getMyGrid().getGrid()[lat][k].setColor(Color.red);
										squares.add(player.getMyGrid().getGrid()[lat][k]);
									}
								} else {
									for (char k = letterStartCoord; k <= letterEndCoord; k++) {
										coordinates.add(k + Integer.toString(intStartCoord + 1));
										int lat = convertLetterToInt(k);
										player.getMyGrid().getGrid()[lat][intStartCoord].setColor(Color.red);
										squares.add(player.getMyGrid().getGrid()[lat][intStartCoord]);
									}
								}
								System.out.println("The ship has been placed on your battlefield: \n");
								System.out.println(player.getMyGrid().toString("ally"));
								Ship ship = new Ship(coordinates, squares);

								fleet.add(ship);
								
								if (j == battlecrew.size()-1)
									player.setFleet(fleet);
							}
						}
						else
							System.out.println(error);
						}
						else
							System.out.println(error);
					}
					else
						System.out.println(error);
					}
					else
						System.out.println(error);
				}
			}
		}
		while (!game.isOver()) {
			Player activePlayer = game.getActivePlayer();
			Player oppositePlayer = game.getOppositePlayer();
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
			String target = reader.next();
			//Checking if the player did put a correct coordinate
			boolean alreadyAttacked = true;
			while(!isCorrectCoordinate(target) || !(isInGridCoordinate(target)) || alreadyAttacked){
				if(isCorrectCoordinate(target) && isInGridCoordinate(target)) {
					int lat = convertLetterToInt(getLetter(target));
					int longitude = getInt(target);
					if(oppositePlayerGrid.getGrid()[lat][longitude].isHit()) {
						System.out.println("You have already attacked this position, choose another target!");
						System.out.println("Target :");
						target = reader.next();
					}
					else {
						alreadyAttacked = false;
					}
				}
				else {
					System.out.println(error);
					System.out.println("Target :");
					target = reader.next();
				}
			}
			int lat = convertLetterToInt(getLetter(target));
			int longitude = getInt(target);
			Square squareAttacked = oppositePlayerGrid.getGrid()[lat][longitude];
			squareAttacked.setHit(true);
			String res = "";
			List<Ship> fleet = oppositePlayer.getFleet();
			// checking if a ship as been touched
			if (squareAttacked.getColor() == Color.red) {
				// We check if the ship is destroyed or not
				int i = 0;
				boolean shipFound = false;
				while (i < fleet.size() && shipFound == false) {
					Ship ship = fleet.get(i);
					if (ship.isHit(squareAttacked)) {
						shipFound = true;
						if (!ship.isDestroyed())
							res = "Hit!";
					}
				}
			} else {
				res = "Missed!";
			}
			if (res.equals("")) {
				res = "Sunk!";
				int i = 0;
				boolean shipFound = false;
				while (i < fleet.size() && shipFound == false) {
					Ship ship = fleet.get(i);
					if (!ship.isDestroyed()) {
						shipFound = true;
					}
					i++;
				}
				if (shipFound == false) {
					game.setOver(true);
					winner = activePlayerName;
				}
				
			}
			System.out.println("result: " + res);
			game.switchActivePlayer();

		}
		System.out.println(
				"Congratulation " + winner + ", the game is over, you have destroyed the whole fleet of your opponent");
	}
}
