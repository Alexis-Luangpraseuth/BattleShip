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
		switch (difficulty) {
		case 1:
			res="";
			break;
		case 2:
			res ="";
			break;
		case 3:
			res ="";
			break;
	
		default:
			res = "";
		}
		return res;
		
	}
	
	public void placeFleet(List<String> fleet) {
		
	}
}
