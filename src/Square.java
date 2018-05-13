

public class Square {
	
// red = ship   blue = no ship   
private Color color;
// 0 = no hit    1 = hit
private boolean hit;


public Color getColor() {
	return color;
}
public void setColor(Color color) {
	this.color = color;
}
public boolean isHit() {
	return hit;
}
public void setHit(boolean hit) {
	this.hit = hit;
}

public Square(Color color, boolean hit) {
	super();
	this.color = color;
	this.hit = hit;
}

public String toString(String usedFor) {
	String res ="";
	
	switch (usedFor) {
	case "ally":
		if(hit==false) {
			res = color == Color.red? "S":"W";
		}
		else if(hit==true) {
			res = color == Color.red? "H":"M";
		}
		break;
	case "opponent":
		if(hit==false) {
			res = "?";
		}
		else if(hit==true) {
			res = color == Color.red? "H":"M";
		}
		break;
	}
	return res;
}

}
