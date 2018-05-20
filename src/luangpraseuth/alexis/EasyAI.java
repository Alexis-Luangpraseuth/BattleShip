package luangpraseuth.alexis;

public class EasyAI extends AI {

	public EasyAI(Grid myGrid, Grid ennmyGrid) {
		super(myGrid, ennmyGrid);
		super.setName("Easy AI");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String shoot() {
		return randomCoordinate(Battleship.coordMax -1);
	}

}
