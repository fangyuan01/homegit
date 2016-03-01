package fangyuan;

public class tttt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MaxArray b = new MaxArray();
		int a[] = { 1, 2, 1, 8, 2, 3 };
		int f[] = { 78, 100, 20, 456, 30, 78, 25, 35, 78, 91 };
		System.out.println("数组a的最大值为" + b.findMax(a, 6));
		System.out.println("数组f的最大值为" + b.findMax(f, 10));
	}

}
