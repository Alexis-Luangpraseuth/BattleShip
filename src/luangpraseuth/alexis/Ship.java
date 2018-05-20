package luangpraseuth.alexis;
import java.util.ArrayList;
import java.util.List;


public class Ship {
	List<Square> squares = new ArrayList<Square>();
	String name;
	
	public Ship(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	public List<Square> getSquares() {
		return squares;
	}

	public void setSquares(String coordS, String coordE, Grid grid,int idBateau) {
		char letterStartCoord = coordS.charAt(0);
		char letterEndCoord = coordE.charAt(0);
		int intStartCoord = Tools.getInt(coordS);
		int intEndCoord = Tools.getInt(coordE);

		if (letterStartCoord == letterEndCoord) {
			for (int k = intStartCoord; k <= intEndCoord; k++) {
			
				int lat = Tools.convertLetterToInt(letterStartCoord);
				grid.getGrid()[lat][k].setContainsShip(true);;
				grid.getGrid()[lat][k].setIdShip(idBateau);;
				squares.add(grid.getGrid()[lat][k]);
			}
		} else {
			for (char k = letterStartCoord; k <= letterEndCoord; k++) {
				
				int lat = Tools.convertLetterToInt(k);
				grid.getGrid()[lat][intStartCoord].setContainsShip(true);;
				grid.getGrid()[lat][intStartCoord].setIdShip(idBateau);;
				squares.add(grid.getGrid()[lat][intStartCoord]);
			}
		}
	}

	public boolean isHit(Square target) {
		boolean res = squares.contains(target)?  true : false;
		return res;
	}

	public boolean isDestroyed() {
		boolean res = true;
		List<Square> squares = this.getSquares();
		int i=0;
		
		while(i<getSquares().size() && res) {
			Square square = squares.get(i);
			if(!square.isHit())
				res = false;
			i++;
		}
		
		return res;
	}
	
	public int getSize() {
		int res = -1;
		switch (name) {
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
		}
		return res;
	}

}
