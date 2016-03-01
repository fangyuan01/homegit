package fangyuan;

public class Test {

	public void test() {
		String a = "10010001";// 给出一个二进制字符串
		double i = 0;// 转换的十进制
		int b[] = new int[8];// 存放字符串 的每个字符
		for (int m = 0; m < 8; m++) {
			b[m] = a.charAt(m);// 取字符
			if (b[m] == 48)// ascll码中1为49，0为48
				b[m] = 0;
			if (b[m] == 49)
				b[m] = 1;
			i = i + b[m] * Math.pow(2, (7 - m));
		}
		System.out.println(i);
		int j = 145; // 十进制转换为二进制
		int c[] = new int[8]; // 存放二进制的数组，从最低位开始记录
		String h = ""; // 字符串为输出的二进制
		for (int m = 0; m < 8; m++) {
			c[m] = j % 2;// 余数为二进制
			j = j / 2;
			h = c[m] + h;// 将二进制顺序正确输出
		}
		System.out.println(h);
		h = "";
		j = 1780;// 10进制转换为16进制

		for (int m = 0; m < 8; m++) {
			c[m] = j % 16;// 取余数
			j = j / 16;
			switch (c[m]) {
			case 10:
				h = "A" + h;
				break;
			case 11:
				h = "B" + h;
				break;
			case 12:
				h = "C" + h;
				break;
			case 13:
				h = "D" + h;
				break;
			case 14:
				h = "E" + h;
				break;
			case 15:
				h = "F" + h;
				break;
			default:
				h = c[m] + h;
				break;
			}

		}
		System.out.println(h);
	}
}
