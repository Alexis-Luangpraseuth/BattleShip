package luangpraseuth.alexis;

public class Grid {

private Square[][] grid; 
 
 
 public Square[][] getGrid() {
	return grid;
}



public void setGrid(Square[][] grid) {
	this.grid = grid;
}



public Grid() {
		
		grid = new Square[BattleShip.coordMax][BattleShip.coordMax];
		for(int i=0; i<BattleShip.coordMax ; i++) {
			for(int j=0; j<BattleShip.coordMax ; j++) {
				grid[i][j] = new Square(Color.blue,false);
			}
		}
 }


public String toString(String usedFor) {
	String res ="";
	res += "S=ship,W=water,?=unknown,H=shot hit,M= shot miss \n \n";
	res += "   ";
	char start = 'A';
	for(int i=0;i<BattleShip.coordMax;i++) {
		res+= start;
		start += 1;
	}
	res += "\n";
	for(int i=0;i<BattleShip.coordMax;i++) {
		if(i<9)
			res += i+1 +"  ";
		else
			res += i+1 +" ";
		for(int j=0;j<BattleShip.coordMax;j++) {
			
			res += grid[i][j].toString(usedFor);
		}
		res += "\n";
	}
	return res;
}
}
