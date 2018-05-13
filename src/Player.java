import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
public class Player {
	private List<Ship> fleet = new ArrayList<Ship>();
	private Grid myGrid;
	private Grid ennemyGrid;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Ship> getFleet() {
		return fleet;
	}
	public void setFleet(List<Ship> fleet) {
		this.fleet = fleet;
	}

	public Grid getMyGrid() {
		return myGrid;
	}
	public void setMyGrid(Grid myGrid) {
		this.myGrid = myGrid;
	}
	public Grid getEnnemyGrid() {
		return ennemyGrid;
	}
	public void setEnnemyGrid(Grid ennemyGrid) {
		this.ennemyGrid = ennemyGrid;
	}
	
	public Player() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
