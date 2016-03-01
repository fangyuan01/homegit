package fangyuan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(isr);
		String s = null;
		try {
			s = bf.readLine();
			while (s != null) {
				if (s.equalsIgnoreCase("exit"))
					break;
				System.out.println(s.toUpperCase());
				s = bf.readLine();
			}
			bf.close();
		} catch (IOException ae) {
			System.out.println("wrong");
			System.exit(-1);
		}

	}
}
