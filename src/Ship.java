import java.util.ArrayList;
import java.util.List;


public class Ship {
	private List<String> coordinates = new ArrayList<String>();
	List<Square> squares = new ArrayList<Square>();


	public Ship(List<String> coordinates, List<Square> squares) {
		this.coordinates = coordinates;
		this.squares = squares;
	}

	
	public List<String> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<String> coordinates) {
		this.coordinates = coordinates;
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

}
