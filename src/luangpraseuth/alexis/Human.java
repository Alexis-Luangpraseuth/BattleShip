package luangpraseuth.alexis;

import java.util.ArrayList;
import java.util.List;

public class Human extends Player {

	public Human(Grid myGrid, Grid ennmyGrid) {
		super(myGrid, ennmyGrid);
	}

	
	public String shoot() {
		String res="";
		String target = BattleShip.reader.next();
		//the human needs to put a correct coordinate
		while (!Tools.isCorrectCoordinate(target) || !(Tools.isInGridCoordinate(target))) {
			System.out.println(BattleShip.error);
			System.out.println("Target :");
			target = BattleShip.reader.next();
		}
		return res;
	}
	
	public void placeFleet(List<String> fleetString) {
		List<Ship> fleet = new ArrayList<Ship>();
		for (int j = 0; j < fleetString.size(); j++) {
			String shipName = fleetString.get(j);
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
									ship.setSquares(coordS,coordE,grid,j);
									System.out.println("The ship has been placed on your battlefield: \n");
									System.out.println(grid.toString("ally"));
									fleet.add(ship);

									if (j == fleet.size() - 1)
										setFleet(fleet);
								}
							}

						}
					}

				}
				if (!check)
					System.out.println(BattleShip.error);
			}
		}
	}
}
