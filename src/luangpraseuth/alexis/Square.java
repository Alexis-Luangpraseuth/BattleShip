package luangpraseuth.alexis;

public class Square {

	// 1=yes 0 = no
	private boolean containsShip;
	// 0 = no hit 1 = hit
	private boolean hit;
	private int idShip;

	public int getIdShip() {
		return idShip;
	}

	public void setIdShip(int idShip) {
		this.idShip = idShip;
	}

	public boolean containsShip() {
		return containsShip;
	}

	public void setContainsShip(boolean containsShip) {
		this.containsShip = containsShip;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public Square(boolean containsShip, boolean hit) {
		super();
		this.containsShip = containsShip;
		this.hit = hit;
	}

	public String toString(String usedFor) {
		String res = "";

		switch (usedFor) {
		case "ally":
			if (hit == false) {
				res = containsShip() ? "S" : "W";
			} else if (hit == true) {
				res = containsShip() ? "H" : "M";
			}
			break;
		case "opponent":
			if (hit == false) {
				res = "?";
			} else if (hit == true) {
				res = containsShip() ? "H" : "M";
			}
			break;
		}
		return res;
	}

}
