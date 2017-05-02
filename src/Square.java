
class Square {
	int hCost = 0; // h cost of the square
	int finalCost = 0; // g cost + h cost
	// coordinate of the Square
	int x, y;
	int distance;
	// parent of the cell (before cell to keep the track to go back)
	Square parent;

	// setting the square by it's coordinate
	Square(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	// for printing the square by formating
	public String toFormat() {
		return "[" + this.x + "," + this.y + "]";
	}

}