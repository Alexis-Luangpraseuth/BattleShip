package luangpraseuth.alexis;


import java.util.Random;

public abstract class AI extends Player {
	public AI(Grid myGrid, Grid ennmyGrid) {
		super(myGrid, ennmyGrid);

	}

	public void placeShip(Ship ship, int idShip) {
		boolean check = false;
		while (!check) {
			int coordinatesRange = (Battleship.coordMax - ship.getSize() -1);
			char randomX = (char) ((int) (Math.random() * coordinatesRange) + 65);
			int randomY = (int) (Math.random() * coordinatesRange) + 1;
			String coordS = randomX + Integer.toString(randomY);
			String coordE = "";
			String coordELong = "";
			char coordELat;
			String[] direction = { "horizontal", "vertical" };
			Random random = new Random();
			// randomly selects an index from the array
			int select = random.nextInt(direction.length);
			if (direction[select].equals("horizontal")) {
				coordELat = (char) ((int) randomX + ship.getSize()-1);
				coordELong = Integer.toString(randomY);
			} else {
				coordELat = randomX;
				coordELong = Integer.toString(randomY + ship.getSize()-1);
			}
			coordE = coordELat + coordELong;
			Grid grid = getMyGrid();
			if (Tools.coordinatesAreAvaible(coordS, coordE, ship, grid)) {
				check = true;
				ship.setSquares(coordS, coordE, grid, idShip);
			}
		}

	}
	
	public String randomCoordinate(int maxRange) {
		char randomX = (char) ((int) (Math.random() * maxRange) + 65);
		int randomY = (int) (Math.random() * maxRange) + 1;
		String coord = randomX + Integer.toString(randomY);
		return coord;
	}

}
