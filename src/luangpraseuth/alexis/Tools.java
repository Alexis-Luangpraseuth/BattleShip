package luangpraseuth.alexis;

public class Tools {
	public static boolean isCorrectCoordinate(String coordinate) {
		boolean res = coordinate.matches("^[A-Z][0-9]{1,2}$");
		if (res == false)
			BattleShip.error = "The coordinate is not correctly wrote, please try again and be careful, exemple of coordinate : 'A1'";
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
		if (longitude >= 0 && longitude < BattleShip.coordMax && latitude >= 0 && latitude < BattleShip.coordMax)
			res = true;
		if (res == false)
			BattleShip.error = "The coordinate is out of the battlefield";
		return res;
	}

	public static boolean coordinatesAreAvaible(String startCoord, String endCoord, Ship ship, Grid grid) {
		boolean res = true;
		char startLetter = getLetter(startCoord);
		char endLetter = getLetter(endCoord);
		int startInt = getInt(startCoord);
		int endInt = getInt(endCoord);
		int size = ship.getSize();
		if (startLetter == endLetter) {
			if (startInt == endInt - size || startInt == endInt + size) {
				for (int k = startInt; k <= endInt; k++) {
					int lat = convertLetterToInt(startLetter);
					Square square = grid.getGrid()[lat][k];
					if (square.getColor() == Color.red) {
						res = false;
						BattleShip.error = "One of those positions is already taken by another ship";
					}
				}
			} else {
				BattleShip.error = "You did not put the correct size for this ship";
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
			} else {
				BattleShip.error = "You did not put the correct size for this ship";
				res = false;
			}
		} else {
			res = false;
			BattleShip.error = "You did not put the correct size for this ship, or it is not placed vertically/horizontally";
		}
		return res;
	}

	public static int convertLetterToInt(char val) {
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
}
