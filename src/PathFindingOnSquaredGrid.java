import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*************************************************************************
 * Author: Dr E Kapetanios Last update: 22-02-2017
 *
 *************************************************************************/

public class PathFindingOnSquaredGrid {

	public static void show(boolean[][] a, boolean which) {
		int N = a.length;
		StdDraw.setXscale(-1, N);
		;
		StdDraw.setYscale(-1, N);
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (a[i][j] == which) {
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledSquare(j, N - i - 1, .5);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.square(j, N - i - 1, .5);
				} else {
					StdDraw.filledSquare(j, N - i - 1, .5);
				}
			}
			StdDraw.setPenColor(StdDraw.BLACK);
		}
	}

	public static void show(boolean[][] a, boolean which, int x1, int y1, int x2, int y2) {
		int N = a.length;
		StdDraw.setXscale(-1, N);
		;
		StdDraw.setYscale(-1, N);
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (a[i][j] == which)
					if ((i == x1 && j == y1) || (i == x2 && j == y2)) {
						StdDraw.circle(j, N - i - 1, .5);
					} else
						StdDraw.square(j, N - i - 1, .5);
				else
					StdDraw.filledSquare(j, N - i - 1, .5);
	}

	public static boolean[][] random(int N, double p) {
		boolean[][] a = new boolean[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				a[i][j] = StdRandom.bernoulli(p);
		return a;
	}

	public static void main(String[] args) {

		Random random = new Random();
		int gridSize = 10;
		double perculation = 0.6;
		boolean[][] randomlyGenMatrix = random(gridSize, perculation);

		StdArrayIO.print(randomlyGenMatrix);
		show(randomlyGenMatrix, true);

		int blockedArrayLength = 0;
		for (int i = 0; i < randomlyGenMatrix.length; i++) {
			for (int j = 0; j < randomlyGenMatrix.length; j++) {
				if (randomlyGenMatrix[i][j] == false) {
					blockedArrayLength++;
				}
			}
		}
		int noOfBlocks = 0;
		int[][] blocked = new int[blockedArrayLength][2];
		for (int i = 0; i < randomlyGenMatrix.length; i++) {
			for (int j = 0; j < randomlyGenMatrix.length; j++) {
				if (randomlyGenMatrix[i][j] == false) {
					blocked[noOfBlocks][0] = i;
					blocked[noOfBlocks][1] = j;
					noOfBlocks++;
				}
			}

		}

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter i for A > ");
		int Ai = scanner.nextInt();

		System.out.print("Enter j for A > ");
		int Aj = scanner.nextInt();

		System.out.print("Enter i for B > ");
		int Bi = scanner.nextInt();

		System.out.print("Enter j for B > ");
		int Bj = scanner.nextInt();

		show(randomlyGenMatrix, true, Ai, Aj, Bi, Bj);

		AStar aStar = new AStar();

		while (true) {
			show(randomlyGenMatrix, true);
			System.out.print("Enter Method (manhattan / euclidean / chebyshev) : ");
			String method = scanner.next().toLowerCase();
			Stopwatch timerFlow = new Stopwatch();
			ArrayList<Square> finalArray = aStar.myProcess(method, gridSize, gridSize, Ai, Aj, Bi, Bj, blocked);
			StdOut.println("Elapsed time = " + timerFlow.elapsedTime());
			for (int i = (finalArray.size() - 1); i >= 0; i--) {
				Square current = finalArray.get(i);
				show(method, randomlyGenMatrix, current.x, current.y);
			}
			for (Square current : finalArray) {

			}
			show(randomlyGenMatrix, true, Ai, Aj, Bi, Bj);
			finalArray.clear();

			System.out.print("Do You want to Do it Agian (yes / no) : ");
			String again = scanner.next().toLowerCase();
			if (again.equals("no")) {
				System.out.println("Thank you !");
				System.exit(1);
			} else if (again.equals("yes")) {
				System.out.println("Welcome !");
			} else {
				System.out.println("Invalid Input !");
			}
		}

	}

	public static void show(String method, boolean[][] a, int x1, int y1) {
		int N = a.length;
		StdDraw.setXscale(-1, N);
		;
		StdDraw.setYscale(-1, N);
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (a[i][j] == true) {
					if ((i == x1 && j == y1)) {
						if (method.equals("manhattan")) {
							StdDraw.setPenColor(StdDraw.YELLOW);
							StdDraw.filledSquare(j, N - i - 1, .5);
						} else if (method.equals("chebyshev")) {
							StdDraw.setPenColor(StdDraw.GREEN);
							StdDraw.filledSquare(j, N - i - 1, .5);
						} else if (method.equals("euclidean")) {
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledSquare(j, N - i - 1, .5);
						}

					} else {
						StdDraw.square(j, N - i - 1, .5);
					}
				} else {
					StdDraw.filledSquare(j, N - i - 1, .5);
				}

				StdDraw.setPenColor(StdDraw.BLACK);
			}
		}
	}
}
