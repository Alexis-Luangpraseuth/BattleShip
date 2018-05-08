import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class BattleShip {
	public static int coordMax = 9;
	
	public static Scanner reader = new Scanner(System.in);

	public static char getLetter(String coordinate) {
		char res = coordinate.charAt(0);
		return res;
	}
	
	public static int getInt(String coordinate) {
		String intStartCoord = coordinate.substring(1);
		int res = Integer.parseInt(intStartCoord) -1;
		return res;
	}
	
	public static boolean isInGridCoordinate(String coordinate) {
		boolean res = false;
		int longitude = convertLetterToInt(getLetter(coordinate));
		int latitude = getInt(coordinate);
		return res;
	}
	
	public static boolean coordinatesAreAvaible(String startCoord,String endCoord,String shipName,Player player) {
		boolean res = true;
		List<String> coordinates = new ArrayList<String>();
		char startLetter = getLetter(startCoord);
		char endLetter = getLetter(endCoord);
		int startInt = getInt(startCoord);
		int endInt = getInt(endCoord);
		switch (shipName) {
		case "Destroyer":
			
			break;
		case "Submarine":
			
			break;
		case "Battleship":
			
			break;
		case "Carrier":
			
			break;
		default:
			res = false;
		}
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
		Scanner in = new Scanner(System.in);

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
		
		for(int i = 1; i <= 2; i++) {
			System.out.println("Player " + i + " you are going to place your ship");
			List<String> battlecrew = new ArrayList<String>();
			battlecrew.add("Destroyer");
			battlecrew.add("Submarine");
			battlecrew.add("Battleship");
			battlecrew.add("Carrier");
			for(int j=0;j<battlecrew.size();i++) {
			String shipName = battlecrew.get(i);
			boolean check = false;
			while(!check) {
			System.out.println("give start coordinates for your " + shipName +"("+ j+2 +" squares), 'A1' to 'J10' exemple: 'A1'");
			String coordS = reader.next();
			if(isInGridCoordinate(coordS)) {
				System.out.println("give end coordinates for your " + shipName + "( "+ j+2 + " squares), exemple: 'A3'");
				String coordE = reader.next();
				if(isInGridCoordinate(coordE)) {
					
				}
			}
			}
			}
		}
		
		while (!game.isOver()) {
			// demande des coordonnées du tir
			System.out.println("Voici la carte de vos tirs");
			player1.showGrilleTir();
			System.out.println(player1.getPlayername() + " ,choisissez une position à attaquer(exemple:"+Config.limittop + Config.limitright+","+Config.limitbottom+Config.limitright);
			System.out.println("Coordonnée du tir :");
			shoot = reader.next();
			// si le joueur tir sur une case déjé essayée on lui redemande des coordonnées
			while (newgame.ActivePlayer.hasAlreadyShot(shoot) || (!newgame.Grille.contains(shoot))) {
				System.out.println(player1.getPlayername()
						+ " ,choisissez une nouvelle position, vous avez déjé attaqué ici ou la position n'est pas valide ! ");
				System.out.println("Coordonnée du tir :");
				shoot = reader.next();
			}
			newgame.ActivePlayer.myShoots.add(shoot);
			newgame.ActivePlayer.updatemap(shoot, 0);
			System.out.println(newgame.ActivePlayer.myShoots);
			// on instancie le crew du joueur adverse pour savoir si on touche
			battlecrew = newgame.OppositePlayer.getBattlecrew();
			int i = 0;
			String res = "A l'eau";
			Ship ship;
			// on boucle tant qu'il y a des bateaux dans la liste et que l'on a pas tout
			// parcouru
			// et qu'on ne touche pas
			while ((i < (newgame.OppositePlayer.length())) && (res.equals("A l'eau"))) {
				ship = battlecrew.get(i);
				// System.out.println(ship);
				// si c'est touché
				if (ship.isHit(shoot)) {
					ship.removepos(shoot);
					newgame.ActivePlayer.updatemap(shoot, 1);
					// on regarde si c'est coulé
					if (ship.isDestroyed()) {
						res = "Touché Coulé";
						newgame.OppositePlayer.removeShip(ship);
					} else {
						res = "Touché";
					}
				}
				i = i + 1;
			}
			System.out.println("C'est : " + res);
			// newgame.changePlayer();
			if (!newgame.IsOver()) {
				System.out.println("C'est à l'ordinateur de jouer : ");
				shoot = player2.shoot(newgame);
				newgame.OppositePlayer.myShoots.add(shoot);
				System.out.println("l'ordinateur a frappé en " + shoot);
				battlecrew = newgame.ActivePlayer.getBattlecrew();
				i = 0;
				res = "A l'eau";
				while ((i < (newgame.ActivePlayer.length())) && (res.equals("A l'eau"))) {
					ship = battlecrew.get(i);
					// System.out.println(ship);
					// si c'est touché
					if (ship.isHit(shoot)) {
						player2.setState("tir");
						ship.removepos(shoot);
						// on regarde si c'est coulé
						if (ship.isDestroyed()) {
							res = "Touché Coulé";
							player2.setCurrentboat(new ArrayList<String>());
							newgame.ActivePlayer.removeShip(ship);
							player2.setState("chasse");
							player2.setDirstate("haut");
						} else {
							res = "Touché";
							player2.getCurrentboat().add(shoot);
						}
					}
					i = i + 1;
				}
				System.out.println("C'est : " + res);
				System.out.println("Fin du tour de l'ordinateur");
			}
		}
		// on regarde qui a gagné et qui a perdu
		if (newgame.ActivePlayer.length() == 0) {
			System.out.println(player2.getPlayername() + " a gagné");
		} else {
			System.out.println(player1.getPlayername() + " a gagné");
		}
		System.out.println("Voulez faire une nouvelle partie ? oui/non");
		avis = reader.next();
	}
	System.out.println("Session terminée");
}

}

}
