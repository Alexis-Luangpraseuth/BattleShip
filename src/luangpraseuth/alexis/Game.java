package luangpraseuth.alexis;


public class Game {
 private Player activePlayer;
 private Player oppositePlayer;
 private boolean isOver = false;
 private String gameType = "Human vs IA";
 
public String getGameType() {
	return gameType;
}

public void setGameType(String gameType) {
	this.gameType = gameType;
}

public Game(Player activePlayer,Player oppositePlayer){
	setActivePlayer(activePlayer);
	setOppositePlayer(oppositePlayer);
}
 
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
	activePlayer.riseScore();
}

public void switchActivePlayer() {
	Player toSwitch = this.activePlayer;
	setActivePlayer(this.oppositePlayer);
	setOppositePlayer(toSwitch);
}

}
