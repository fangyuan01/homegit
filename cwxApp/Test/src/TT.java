import java.util.LinkedList;
import java.util.Queue;

public class TT {

	private static Queue<Integer> A;
	private static Queue<Integer> B;
	private static Queue<Integer> C;
	private static Queue<Integer> D;

	private static int[] J1 = { 2, 2, 4, 4, 0 };
	private static int[] J2 = { 1, 2, 2, 3, 0 };
	private static int[] J3 = { 4, 2, 3, 1, 0 };
	private static int[] J4 = { 5, 8, 8, 3, 0 };
	private static int[] J5 = { 5, 2, 3, 2, 0 };

	private static int[][] J = { J1, J2, J3, J4, J5 };

	public static void main(String[] args) {
		A = new LinkedList<Integer>();
		B = new LinkedList<Integer>();
		C = new LinkedList<Integer>();
		D = new LinkedList<Integer>();
	}

}
