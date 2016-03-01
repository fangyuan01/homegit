package fangyuan;

public class TestProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = 0;
		int j = 1;
		for (int i = 1; i <= 100; i++) {// 计算 1!+2!+3!.....+100!
			j = j * i;
			sum = sum + j;
		}
		System.out.println("1!+2!+3!.....+100!=" + sum);

		for (int i = 1; i < 100; i += 2) {// 计算 1+3+5+.....+99
			sum = sum + i;
		}
		System.out.println("1+3+5+7....+99=" + sum);

		int i = 0; // do while和while的区别
		while (i < 10) {
			System.out.print("i=" + i);
			i++;
		}
		System.out.print("\n");
		i = 0;
		do {
			i++;
			System.out.print("i=" + i);
		} while (i < 10);

		int stop = 4;// break
		for (int h = 0; h < 10; h++) {
			if (h == stop)
				break;
			System.out.println("h=" + h);

		}

		int skip = 5;// continue
		for (int m = 0; m < 10; m++) {
			if (m == skip)
				continue;
			System.out.println("m=" + m);

		}
		// 1-100前10个可以被三整除的数
		int sum1 = 0;
		for (int i1 = 1; i1 <= 100; i1++) {
			if (i1 % 3 == 0) {
				System.out.print(i1 + " ");
				sum1++;
			}
			if (sum1 == 10)
				break;
		}

		// 输出101-200内的质数
		for (int i2 = 101; i2 < 200; i2 += 2) {
			for (int j2 = 2; j2 <= (i2 / 2); j2++) {
				if (i2 % j2 == 0) {

				}
			}
		}
	}
}
