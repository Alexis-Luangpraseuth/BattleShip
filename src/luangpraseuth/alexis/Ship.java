package luangpraseuth.alexis;
import java.util.ArrayList;
import java.util.List;


public class Ship {
	List<Square> squares = new ArrayList<Square>();
	String name;

	public Ship(String name) {
		this.name = name;
	}

	public List<Square> getSquares() {
		return squares;
	}

	public void setSquares(List<Square> squares) {
		this.squares = squares;
	}

	public boolean isHit(Square target) {
		boolean res = squares.contains(target)?  true : false;
		return res;
	}

	public boolean isDestroyed() {
		boolean res = true;
		List<Square> squares = this.getSquares();
		for(int j=0;j<squares.size();j++) {
			Square square = squares.get(j);
			if(!square.isHit())
				res = false;
		}
		return res;
	}
	
	public int getSize() {
		int res;
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
		default:
			res = -1;
		}
		return res;
	}

}
