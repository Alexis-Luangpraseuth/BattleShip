package luangpraseuth.alexis;

import java.util.ArrayList;
import java.util.List;

public class Human extends Player {

	public Human(Grid myGrid, Grid ennmyGrid) {
		super(myGrid, ennmyGrid);
	}

	
	public String shoot() {
		String res="";
		return res;
	}
	
	public void placeFleet(List<String> fleet) {
		for (int j = 0; j < fleet.size(); j++) {
			String shipName = fleet.get(j);
			boolean check = false;
			Ship ship = new Ship(shipName);
			while (!check) {
				System.out.println("give start coordinates for your " + shipName + "(" + ship.getSize()
						+ " squares), exemple: 'A1'");
				String coordS = BattleShip.reader.next();
				if (Tools.isCorrectCoordinate(coordS)) {
					if (Tools.isInGridCoordinate(coordS)) {
						System.out.println("give end coordinates for your " + shipName + "( "
								+ ship.getSize() + " squares), exemple: 'A3'");
						String coordE = BattleShip.reader.next();
						if (Tools.isCorrectCoordinate(coordE)) {
							if (Tools.isInGridCoordinate(coordE)) {
								Grid grid = getMyGrid();
								if (Tools.coordinatesAreAvaible(coordS, coordE, ship, grid)) {
									check = true;
									ship.setSquares(coordS);
									
									char letterStartCoord = coordS.charAt(0);
									char letterEndCoord = coordE.charAt(0);
									int intStartCoord = Tools.getInt(coordS);
									int intEndCoord = Tools.getInt(coordE);

									if (letterStartCoord == letterEndCoord) {
										for (int k = intStartCoord; k <= intEndCoord; k++) {
										
											int lat = Tools.convertLetterToInt(letterStartCoord);
											player.getMyGrid().getGrid()[lat][k].setColor(Color.red);
											squares.add(player.getMyGrid().getGrid()[lat][k]);
										}
									} else {
										for (char k = letterStartCoord; k <= letterEndCoord; k++) {
											
											int lat = Tools.convertLetterToInt(k);
											player.getMyGrid().getGrid()[lat][intStartCoord]
													.setColor(Color.red);
											squares.add(player.getMyGrid().getGrid()[lat][intStartCoord]);
										}
									}
									System.out.println("The ship has been placed on your battlefield: \n");
									System.out.println(player.getMyGrid().toString("ally"));
									ship.setSquares(squares);;

									fleet.add(ship);

									if (j == fleet.size() - 1)
										player.setFleet(fleet);
								}
							}

						}
					}

				}
				if (!check)
					System.out.println(error);
			}
		}
	}
}
