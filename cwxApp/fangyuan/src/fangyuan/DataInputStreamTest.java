package fangyuan;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataInputStreamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeDouble(Math.random());
			dos.writeBoolean(true);
			ByteArrayInputStream bis = new ByteArrayInputStream(
					bos.toByteArray());
			System.out.println(bis.available());
			DataInputStream dis = new DataInputStream(bis);
			System.out.println(dis.readDouble());
			System.out.println(dis.readBoolean());
			dis.close();
			bis.close();
		} catch (IOException ae) {
			System.out.println("wrong output");
			System.exit(-1);
		}
	}
}
