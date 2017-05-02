
import java.util.*;

public class AStar {
	public static final int DCOST = 14;
	public static final int VHCOST = 10;

	Square[][] GRID;
	ArrayList<Square> path = new ArrayList<Square>();
	PriorityQueue<Square> openSquares;
	boolean closedSquares[][];
	int startX, startY;
	int endX, endY;

	// method for assign the blocked squares with null
	public void setBlockedSquare(int x, int y) {
		GRID[x][y] = null;
	}

	// method for assigning the start square
	public void setStartSquare(int x, int y) {
		startX = x;
		startY = y;
	}

	// method for assigning the end square
	public void setEndSquare(int x, int y) {
		endX = x;
		endY = y;
	}

	void checkAndUpdateCost(Square currentSquare, Square tempSquare, int cost, int distance) {
		if (tempSquare == null || closedSquares[tempSquare.x][tempSquare.y])
			return;
		int tempFinalCost = tempSquare.hCost + cost;

		boolean isOpen = openSquares.contains(tempSquare);
		if (!isOpen || tempFinalCost < tempSquare.finalCost) {
			tempSquare.finalCost = tempFinalCost;
			tempSquare.distance = distance;
			tempSquare.parent = currentSquare;
			if (!isOpen)
				openSquares.add(tempSquare);
		}
	}

	public void AStarAlgorithm(String method) {

		// add the start location to open list.
		openSquares.add(GRID[startX][startY]);

		Square currentSquare;

		while (true) {
			currentSquare = openSquares.poll();
			if (currentSquare == null)
				break;
			closedSquares[currentSquare.x][currentSquare.y] = true;

			if (currentSquare.equals(GRID[endX][endY])) {
				return;
			}

			Square tempSuqare;

			if (method.equals("manhattan")) {
				//System.out.println("Test 1");
				// Left Square
				if (currentSquare.x - 1 >= 0) {
					tempSuqare = GRID[currentSquare.x - 1][currentSquare.y];
					checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + VHCOST, VHCOST);
				}
				// Up Square
				if (currentSquare.y - 1 >= 0) {
					tempSuqare = GRID[currentSquare.x][currentSquare.y - 1];
					checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + VHCOST, VHCOST);
				}
				// Down Square
				if (currentSquare.y + 1 < GRID[0].length) {
					tempSuqare = GRID[currentSquare.x][currentSquare.y + 1];
					checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + VHCOST, VHCOST);
				}
				// Right Square
				if (currentSquare.x + 1 < GRID.length) {
					tempSuqare = GRID[currentSquare.x + 1][currentSquare.y];
					checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + VHCOST, VHCOST);
				}

			}
			if (method.equals("chebyshev")) {
				//System.out.println("Test 2");
				// Left Square
				if (currentSquare.x - 1 >= 0) {
					tempSuqare = GRID[currentSquare.x - 1][currentSquare.y];
					checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + VHCOST, VHCOST);
					// Left Up Square
					if (currentSquare.y - 1 >= 0) {
						tempSuqare = GRID[currentSquare.x - 1][currentSquare.y - 1];
						checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + DCOST, DCOST);
					}
					// Left Down Square
					if (currentSquare.y + 1 < GRID[0].length) {
						tempSuqare = GRID[currentSquare.x - 1][currentSquare.y + 1];
						checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + DCOST, DCOST);
					}
				}
				// Up Square
				if (currentSquare.y - 1 >= 0) {
					tempSuqare = GRID[currentSquare.x][currentSquare.y - 1];
					checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + VHCOST, VHCOST);
				}
				// Down Square
				if (currentSquare.y + 1 < GRID[0].length) {
					tempSuqare = GRID[currentSquare.x][currentSquare.y + 1];
					checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + VHCOST, VHCOST);
				}
				// Right Square
				if (currentSquare.x + 1 < GRID.length) {
					tempSuqare = GRID[currentSquare.x + 1][currentSquare.y];
					checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + VHCOST, VHCOST);
					// Right Up Square
					if (currentSquare.y - 1 >= 0) {
						tempSuqare = GRID[currentSquare.x + 1][currentSquare.y - 1];
						checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + DCOST, DCOST);
					}
					// Right Down Square
					if (currentSquare.y + 1 < GRID[0].length) {
						tempSuqare = GRID[currentSquare.x + 1][currentSquare.y + 1];
						checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + DCOST, DCOST);
					}
				}

			}

			if (method.equals("euclidean")) {
				//System.out.println("Test 3");
				// Left Square but we don't care 
				if (currentSquare.x - 1 >= 0) {
					tempSuqare = GRID[currentSquare.x - 1][currentSquare.y];
					// Left Up Square
					if (currentSquare.y - 1 >= 0) {
						tempSuqare = GRID[currentSquare.x - 1][currentSquare.y - 1];
						checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + DCOST, DCOST);
					}
					// Left Down Square
					if (currentSquare.y + 1 < GRID[0].length) {
						tempSuqare = GRID[currentSquare.x - 1][currentSquare.y + 1];
						checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + DCOST, DCOST);
					}
				}
				// Right Square  but we don't care 
				if (currentSquare.x + 1 < GRID.length) {
					tempSuqare = GRID[currentSquare.x + 1][currentSquare.y];
					// Right Up Square
					if (currentSquare.y - 1 >= 0) {
						tempSuqare = GRID[currentSquare.x + 1][currentSquare.y - 1];
						checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + DCOST, DCOST);
					}
					// Right Down Square
					if (currentSquare.y + 1 < GRID[0].length) {
						tempSuqare = GRID[currentSquare.x + 1][currentSquare.y + 1];
						checkAndUpdateCost(currentSquare, tempSuqare, currentSquare.finalCost + DCOST, DCOST);
					}
				}
			}
		}
	}

	public ArrayList<Square> myProcess(String method, int x, int y, int si, int sj, int ei, int ej, int[][] blocked) {

		// modify the GRID size & blocked array
		GRID = new Square[x][y];
		closedSquares = new boolean[x][y];
		openSquares = new PriorityQueue<>((Object o1, Object o2) -> {
			Square c1 = (Square) o1;
			Square c2 = (Square) o2;

			return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
		});
		// assigning the source Square
		setStartSquare(si, sj);
		// assigning the destination Square
		setEndSquare(ei, ej);
		// calculating and assigning the hCost for each Square
		for (int i = 0; i < x; ++i) {
			for (int j = 0; j < y; ++j) {
				GRID[i][j] = new Square(i, j);

				if (method.equals("manhattan")) {
					GRID[i][j].hCost = Math.abs(i - endX) + Math.abs(j - endY); // manhattan
				} else if (method.equals("chebyshev")) {
					GRID[i][j].hCost = Math.max(Math.abs(i - endX), Math.abs(j - endY)); // chebyshev
				} else if (method.equals("euclidean")) {
					GRID[i][j].hCost = (int) Math.sqrt(((i - endX) ^ 2) + ((j - endY) ^ 2)); // euclidean
				}
			}
		}

		// making the source final cost as zero
		GRID[si][sj].finalCost = 0;

		// for setting the blocked array and blocked Square is going to be null
		for (int i = 0; i < blocked.length; ++i) {
			setBlockedSquare(blocked[i][0], blocked[i][1]);
		}

		System.out.println();
		
		AStarAlgorithm(method);
		System.out.println("\nScores for cells: ");
		for (int i = 0; i < x; ++i) {
			for (int j = 0; j < x; ++j) {
				if (GRID[i][j] != null)
					System.out.printf("%-3d ", GRID[i][j].finalCost);
				else
					System.out.print("BL  ");
			}
			System.out.println();
		}
		System.out.println();
		int cost = 0;
		int distance = 0;
		if (closedSquares[endX][endY]) {

			Square current = GRID[endX][endY];
			path.add(current);
			
			
			while (current.parent != null) {
				cost += current.finalCost;
				System.out.print(current.toFormat() + " -> ");
				
				System.out.println(current.distance + "    " + current.finalCost);
				
				distance += current.distance;
				current = current.parent;
				path.add(current);
			}
			System.out.println();
			System.out.println(method + " -> " + cost);
			System.out.println("Crossed Cell : " + (path.size() - 1));
			System.out.println("Distance : " + distance);

			System.out.println();
		} else {
			System.out.println("No possible path");
		}
		return path;
	}

}