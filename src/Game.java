import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Game {
 private Player activePlayer;
 private Player oppositePlayer;
 private boolean isOver = false;
 
 
public Player getActivePlayer() {
	return activePlayer;
}
public void setActivePlayer(Player activePlayer) {
	this.activePlayer = activePlayer;
}
public Player getOppositePlayer() {
	return oppositePlayer;
}
public void setOppositePlayer(Player oppositePlayer) {
	this.oppositePlayer = oppositePlayer;
}
public boolean isOver() {
	return isOver;
}
public void setOver(boolean isOver) {
	this.isOver = isOver;
}

public void switchActivePlayer() {
	Player toSwitch = this.activePlayer;
	setActivePlayer(this.oppositePlayer);
	setOppositePlayer(toSwitch);
}

}
