package luangpraseuth.alexis;

import java.util.ArrayList;
import java.util.List;

public class IA extends Player {
private int difficulty;

	public IA(Grid myGrid, Grid ennmyGrid,int difficulty) {
		super(myGrid, ennmyGrid);
		super.setName("IA LEVEL :" + difficulty);
		this.difficulty = difficulty;
		// TODO Auto-generated constructor stub
	}
	
	public String shoot() {
		String res="";
		return res;
	}
	
	public void placeFleet(List<String> fleet) {
		
	}
}
