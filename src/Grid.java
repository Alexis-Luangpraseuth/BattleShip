import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Grid {
 private Square[][] grid; 
 
 
 
 public Square[][] getGrid() {
	return grid;
}



public void setGrid(Square[][] grid) {
	this.grid = grid;
}



public Grid() {
		
		grid = new Square[10][10];
		for(int i=0; i<10 ; i++) {
			for(int j=0; j<10 ; j++) {
				grid[i][j] = new Square(Color.white,0);
			}
		}
 }


public String EnnemyGridToString() {
	String res ="";
	for(int i=0;i<10;i++) {
		for(int j=0;j<10;j++) {
			
			res += grid[i][j].toStringEnnemy();
		}
		res += "\n";
	}
	return res;
}

public String MyGridToString() {
	String res ="";
	for(int i=0;i<10;i++) {
		for(int j=0;j<10;j++) {
			
			res += grid[i][j].toStringFriend();
		}
		res += "\n";
	}
	return res;
}
}
