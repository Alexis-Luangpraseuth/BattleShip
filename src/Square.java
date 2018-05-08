import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Square {
// red = ship   blue = no ship   white = unknown
private Color color;
// 0 = no hit    1 = hit
private int status;


public Color getColor() {
	return color;
}
public void setColor(Color color) {
	this.color = color;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}

public Square(Color color, int status) {
	super();
	this.color = color;
	this.status = status;
}

public String toStringEnnemy() {
	String res="~";
	if(status==0) {
		res="m";
	}
	if(status==1) {
		res="x";
	}
	return res;
}


public String toStringFriend() {
	String res="~";
	if(partOfShip) {
		res="o";
	}
	if(status==1) {
		res="x";
	}
	return res;
}

}
