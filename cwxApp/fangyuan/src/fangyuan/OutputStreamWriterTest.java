package fangyuan;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamWriterTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream("F:/fangyuan/fangyuan.txt"));
			osw.write("helloworld");
			System.out.println(osw.getEncoding());
			osw.close();
			osw = new OutputStreamWriter(new FileOutputStream(
					"F:/fangyuan/fangyuan.txt", true));// ������ԭ�����ļ�����true֮������ԭ�ļ�����������
			osw.write("i love you dear");

			osw.close();

		} catch (FileNotFoundException ae) {
			System.out.println("�ļ������ҵ�");
			System.exit(-1);
		} catch (IOException ae) {
			System.out.println("�ļ�д��ʧ��");
			System.exit(-1);
		}

	}
}
