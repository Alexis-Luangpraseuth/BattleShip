package luangpraseuth.alexis;

public class MediumAI extends AI {

	public MediumAI(Grid myGrid, Grid ennmyGrid) {
		super(myGrid, ennmyGrid);
		super.setName("Medium AI");
	}

	@Override
	public String shoot() {
		String coordinate ="";
		boolean check = false;
		while(!check) {
		 coordinate =super.randomCoordinate(Battleship.coordMax -1);
		 Grid ennemyGrid = super.getEnnemyGrid();
		 check = !(Tools.alreadyHitCoordinate(ennemyGrid, coordinate));
			
		}
		return coordinate;
	}

}
