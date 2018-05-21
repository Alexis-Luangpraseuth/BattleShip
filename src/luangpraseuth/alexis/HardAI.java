package luangpraseuth.alexis;

import java.util.ArrayList;
import java.util.List;


public class HardAI extends AI {
	private boolean hasTarget = false;
	private String initialCoordinateTarget = new String();
	private List<String> directionsToTestTarget = new ArrayList<String>();

	public String getInitialCoordinateTarget() {
		return initialCoordinateTarget;
	}

	public void setInitialCoordinateTarget(String initialCoordinateTarget) {
		this.initialCoordinateTarget = initialCoordinateTarget;
	}


	public List<String> getdirectionsToTestTarget() {
		return directionsToTestTarget;
	}

	public void setdirectionsToTestTarget(List<String> directionsToTestTarget) {
		this.directionsToTestTarget = directionsToTestTarget;
	}

	public void updateShoot(int idShip, String target, String res) {
		int longitude = Tools.convertLetterToInt(Tools.getLetter(target));
		int latitude = Tools.getInt(target);
		List<String> directions = new ArrayList<String>();
		
		if(res.equals("Hit!")){
			directions.add("left");
			directions.add("top");
			directions.add("bottom");
			directions.add("right");
			if (longitude <2 )
			{
				directions.remove("left");
			}
			if (longitude > Battleship.coordMax - 2 )
			{
				directions.remove("right");
			}
			if (latitude < 2)
			{
				directions.remove("top");
			
			}
			if (latitude > Battleship.coordMax - 2)
			{
				directions.remove("bottom");
			
			}
			if(directions.size() ==0)
				setTarget(false);
			if(!hasTarget()) {
				setTarget(true);
				this.setInitialCoordinateTarget(target);
			}
			this.setdirectionsToTestTarget(directions);
		}
		if(res.equals("Sunk!")){
			setTarget(false);
		}
		
	}

	public HardAI(Grid myGrid, Grid ennmyGrid) {
		super(myGrid, ennmyGrid);
		super.setName("Hard AI");
		// TODO Auto-generated constructor stub
	}

	public boolean hasTarget() {
		return hasTarget;
	}

	public void setTarget(boolean hasTarget) {
		this.hasTarget = hasTarget;
	}

	public String getInitialCoordinateTargets() {
		return initialCoordinateTarget;
	}

	public void setInitialCoordinateTargets(String initialCoordinateTarget) {
		this.initialCoordinateTarget = initialCoordinateTarget;
	}

	
	public String shoot() {
		String coordinate = "";
		List<String> directions = getdirectionsToTestTarget();
			boolean check = false;
			if (hasTarget()) {
			while (!check && directions.size()>0) {
			
				Grid ennemyGrid = super.getEnnemyGrid();
				String shipCoordinate = getInitialCoordinateTarget();
				int longitude = Tools.convertLetterToInt(Tools.getLetter(shipCoordinate));
				int latitude = Tools.getInt(shipCoordinate) +1;	
				String direction = directions.get(0);

				switch (direction) {
				case "top":
					coordinate = (char)(int)(longitude+65) + Integer.toString(latitude -1 );
					break;
				case "bottom":
					coordinate = (char)(int)(longitude+65) + Integer.toString(latitude + 1);
					break;
				case "right":
					coordinate = (char)(int)(longitude + 1 +65) + Integer.toString(latitude);
					break;
				case "left":
					coordinate = (char)(int)(longitude -1 +65) + Integer.toString(latitude);
					break;
				}
				
				check = !(Tools.alreadyHitCoordinate(ennemyGrid, coordinate));
				if (!check) {
					directions.remove(0);
				}	
			}
				}
			if (!check) {
				setTarget(false);
				while (!check) {
					coordinate = Tools.randomCoordinate(Battleship.coordMax - 1);
					Grid ennemyGrid = super.getEnnemyGrid();
					check = !(Tools.alreadyHitCoordinate(ennemyGrid, coordinate));

				}
			}
			
		return coordinate;
}
}
