package fangyuan;

public class Test {

	public void test() {
		String a = "10010001";// ����һ���������ַ���
		double i = 0;// ת����ʮ����
		int b[] = new int[8];// ����ַ��� ��ÿ���ַ�
		for (int m = 0; m < 8; m++) {
			b[m] = a.charAt(m);// ȡ�ַ�
			if (b[m] == 48)// ascll����1Ϊ49��0Ϊ48
				b[m] = 0;
			if (b[m] == 49)
				b[m] = 1;
			i = i + b[m] * Math.pow(2, (7 - m));
		}
		System.out.println(i);
		int j = 145; // ʮ����ת��Ϊ������
		int c[] = new int[8]; // ��Ŷ����Ƶ����飬�����λ��ʼ��¼
		String h = ""; // �ַ���Ϊ����Ķ�����
		for (int m = 0; m < 8; m++) {
			c[m] = j % 2;// ����Ϊ������
			j = j / 2;
			h = c[m] + h;// ��������˳����ȷ���
		}
		System.out.println(h);
		h = "";
		j = 1780;// 10����ת��Ϊ16����

		for (int m = 0; m < 8; m++) {
			c[m] = j % 16;// ȡ����
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
