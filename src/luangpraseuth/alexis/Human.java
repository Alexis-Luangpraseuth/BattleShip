package luangpraseuth.alexis;



public class Human extends Player {

	public Human(Grid myGrid, Grid ennmyGrid) {
		super(myGrid, ennmyGrid);
	}

	public String shoot() {
		System.out.println("This is your battlefield :");
		System.out.println(getMyGrid().toString("ally"));
		System.out.println("This is your ennemy battlefield :");
		System.out.println(getEnnemyGrid().toString("opponent"));
		System.out.println("Choose a target for your next attack. Exemple: 'A1'");
		System.out.println("Target :");
		String target = Battleship.reader.next();
		// the human needs to put a correct coordinate
		while (!Tools.isCorrectCoordinate(target) || !(Tools.isInGridCoordinate(target))) {
			System.out.println(Battleship.error);
			System.out.println("Target :");
			target = Battleship.reader.next();
		}
		return target;
	}

	public void placeShip(Ship ship, int idShip) {
		boolean check = false;
		while (!check) {
			System.out.println("give start coordinates for your " + ship.getName() + "(" + ship.getSize()
					+ " squares), exemple: 'A1'");
			String coordS = Battleship.reader.next();
			if (Tools.isCorrectCoordinate(coordS)) {
				if (Tools.isInGridCoordinate(coordS)) {
					System.out.println("give end coordinates for your " + ship.getName() + "( " + ship.getSize()
							+ " squares), exemple: 'A3'");
					String coordE = Battleship.reader.next();
					if (Tools.isCorrectCoordinate(coordE)) {
						if (Tools.isInGridCoordinate(coordE)) {
							Grid grid = getMyGrid();
							if (Tools.coordinatesAreAvaible(coordS, coordE, ship, grid)) {
								check = true;
								ship.setSquares(coordS, coordE, grid, idShip);
								System.out.println("The ship has been placed on your battlefield: \n");
								System.out.println(grid.toString("ally"));
							}
						}

					}
				}

			}
			if (!check)
				System.out.println(Battleship.error);
		}
	}
}
