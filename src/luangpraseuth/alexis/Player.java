package luangpraseuth.alexis;
import java.util.ArrayList;
import java.util.List;
public abstract class Player implements IPlay {
	private List<Ship> fleet = new ArrayList<Ship>();
	private Grid myGrid;
	private Grid ennemyGrid;
	private String name;
	private int score = 0;
	
	public Player(Grid myGrid, Grid ennmyGrid) {
		setMyGrid(myGrid);
        setEnnemyGrid(ennemyGrid);
		
	}
	
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
	public int getScore() {
		return score;
	}
	public void riseScore() {
		this.score += 1;
	}
	public Grid getEnnemyGrid() {
		return ennemyGrid;
	}
	public void setEnnemyGrid(Grid ennemyGrid) {
		this.ennemyGrid = ennemyGrid;
	}
	
	public boolean hasLost() {
		boolean res = true;
		int i = 0;
		while(i<getFleet().size() && res) {
			if(!getFleet().get(i).isDestroyed())
				res = false;
		}
		return res;
	}

	
	
	
	
	

}
