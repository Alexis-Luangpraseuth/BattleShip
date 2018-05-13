

public class Grid {

private Square[][] grid; 
static final  int coordMax = 10; 
 
 
 public Square[][] getGrid() {
	return grid;
}



public void setGrid(Square[][] grid) {
	this.grid = grid;
}



public Grid() {
		
		grid = new Square[coordMax][coordMax];
		for(int i=0; i<coordMax ; i++) {
			for(int j=0; j<coordMax ; j++) {
				grid[i][j] = new Square(Color.blue,false);
			}
		}
 }


public String toString(String usedFor) {
	String res ="";
	res += " ";
	char start = 'A';
	for(int i=0;i<coordMax;i++) {
		res+= start;
		start += 1;
		
	}
	res += "\n";
	for(int i=0;i<coordMax;i++) {
		res += i+1;
		for(int j=0;j<coordMax;j++) {
			
			res += grid[i][j].toString(usedFor);
		}
		res += "\n";
	}
	return res;
}
}
