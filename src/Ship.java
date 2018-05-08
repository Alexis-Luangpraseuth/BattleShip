import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Ship {
	private String startCoord;
	private String endCoord;
	private List<String> coordinates = new ArrayList<String>();

	public Ship(String startCoord, String endCoord) {

		this.startCoord = startCoord;
		this.endCoord = endCoord;
		String letterStartCoord = startCoord.substring(0,0);
		String letterEndCoord = endCoord.substring(0,0);
		String intStartCoord = startCoord.substring(1);
		String intEndCoord = endCoord.substring(1);
		
		if(letterStartCoord.equals(letterEndCoord)) {
			for(int i = Integer.parseInt(intStartCoord); i <= Integer.parseInt(intEndCoord); i++)
			{
			 coordinates.add(letterStartCoord+ Integer.toString(i));
			}
		}
		else
		{
			for(char i = letterStartCoord.charAt(0); i <= letterEndCoord.charAt(0); i++)
			{
			 coordinates.add(i+ intStartCoord);
			}	
		}
	}

	public List<String> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<String> coordinates) {
		this.coordinates = coordinates;
	}

	public boolean isHit(String missileCoord) {
		boolean res = false;

		return res;
	}

	public boolean isDestroyed() {
		boolean res = false;
		return res;
	}

	public String getName() {
		String res;
		switch (this.coordinates.size()) {
		case 2:
			res = "Destroyer";
			break;
		case 3:
			res = "Submarine";
			break;
		case 4:
			res = "Battleship";
			break;
		case 5:
			res = "Carrier";
			break;
		default:
			res = "Error";
		}
		return res;

	}

}
