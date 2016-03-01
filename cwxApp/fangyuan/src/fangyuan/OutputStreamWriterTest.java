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
					"F:/fangyuan/fangyuan.txt", true));// 不覆盖原来的文件，加true之后会跟着原文件后面继续添加
			osw.write("i love you dear");

			osw.close();

		} catch (FileNotFoundException ae) {
			System.out.println("文件不能找到");
			System.exit(-1);
		} catch (IOException ae) {
			System.out.println("文件写入失败");
			System.exit(-1);
		}

	}
}
