package luangpraseuth.alexis;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private Player activePlayer;
	private Player oppositePlayer;
	private boolean isOver = false;
	private String gameType = "Human vs IA";

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public Game(Player activePlayer, Player oppositePlayer) {
		setActivePlayer(activePlayer);
		setOppositePlayer(oppositePlayer);
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public Player getOppositePlayer() {
		return oppositePlayer;
	}

	public void setOppositePlayer(Player oppositePlayer) {
		this.oppositePlayer = oppositePlayer;
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	public void setup() {
		if (Battleship.switchStartingPlayer)
			switchActivePlayer();
		Player activePlayer = getActivePlayer();
		Player oppositePlayer = getOppositePlayer();
		List<Player> players = new ArrayList<Player>();
		players.add(activePlayer);
		players.add(oppositePlayer);

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if (player instanceof Human) {
				int num = i + 1;
				System.out.println("player " + num + "  choose your name : ");
				player.setName(Battleship.reader.next());
			}
			String playerName = player.getName();
			//System.out.println(playerName + " you are going to place your fleet");
			List<String> fleetString = new ArrayList<String>();
			fleetString.add("Carrier");
			fleetString.add("Battleship");
			fleetString.add("Cruiser");
			fleetString.add("Submarine");
			fleetString.add("Destroyer");

			List<Ship> fleet = new ArrayList<Ship>();
			for (int j = 0; j < fleetString.size(); j++) {
				String shipName = fleetString.get(j);
				Ship ship = new Ship(shipName);
				player.placeShip(ship, j);
				fleet.add(ship);
				if (j == fleetString.size() - 1)
					player.setFleet(fleet);

			}

		}
	}

	public void play() {
		while (!isOver()) {
			activePlayer = getActivePlayer();
			Grid oppositePlayerGrid = activePlayer.getEnnemyGrid();
			String activePlayerName = activePlayer.getName();
			System.out.println(activePlayerName + " it is your turn to play");
			String target = activePlayer.shoot();
			int longitude = Tools.convertLetterToInt(Tools.getLetter(target));
			int latitude = Tools.getInt(target);
			Square squareAttacked = oppositePlayerGrid.getGrid()[longitude][latitude];
			boolean alreadyAttacked = false;
			if (squareAttacked.isHit())
				alreadyAttacked = true;
			else
				squareAttacked.setHit(true);
			String res = "";
			List<Ship> fleet = oppositePlayer.getFleet();
			// checking if a ship as been touched
			if (squareAttacked.containsShip() && !alreadyAttacked) {
				// We check if the ship is destroyed or not
				Ship ship = fleet.get(squareAttacked.getIdShip());
				res = ship.isDestroyed() ? "Sunk!" : "Hit!";

			} else {
				res = alreadyAttacked ? "already attacked" : "Missed!";
			}
			if (activePlayer instanceof HardAI) {
				((HardAI) activePlayer).updateShoot(squareAttacked.getIdShip(), target, res);
			}

			if (res.equals("Sunk!")) {
				if (oppositePlayer.hasLost()) {
					setOver(true);
					activePlayer.riseScore();
				}
			}
			System.out.println(activePlayerName + " shoots the target : " + target);
			System.out.println("result: " + res);
			if (!isOver())
				switchActivePlayer();
		}
	}

	public void switchActivePlayer() {
		Player toSwitch = this.activePlayer;
		setActivePlayer(this.oppositePlayer);
		setOppositePlayer(toSwitch);
	}

}
