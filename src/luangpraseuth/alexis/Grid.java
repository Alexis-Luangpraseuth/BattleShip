package luangpraseuth.alexis;

public class Grid {

private Square[][] grid; 
 
 
 public Square[][] getGrid() {
	return grid;
}



public void setGrid(Square[][] grid) {
	this.grid = grid;
}

public void reset() {
	Square[][] grid = getGrid();
	for(int i=0; i<Battleship.coordMax ; i++) {
		for(int j=0; j<Battleship.coordMax ; j++) {
			grid[i][j].setHit(false);
			grid[i][j].setContainsShip(false);
		}
	}
}



public Grid() {
		
		grid = new Square[Battleship.coordMax][Battleship.coordMax];
		for(int i=0; i<Battleship.coordMax ; i++) {
			for(int j=0; j<Battleship.coordMax ; j++) {
				grid[i][j] = new Square(false,false);
			}
		}
 }


public String toString(String usedFor) {
	String res ="";
	res += "S=ship,W=water,?=unknown,H=shot hit,M= shot miss \n \n";
	res += "   ";
	char start = 'A';
	for(int i=0;i<Battleship.coordMax;i++) {
		res+= " " +start;
		start += 1;
	}
	res += "\n";
	for(int i=0;i<Battleship.coordMax;i++) {
		if(i<9)
			res += i+1 +"  ";
		else
			res += i+1 +" ";
		for(int j=0;j<Battleship.coordMax;j++) {
			
			res += " " +grid[i][j].toString(usedFor);
		}
		res += "\n";
	}
	return res;
}
}
