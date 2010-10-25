package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest1 extends Test{
	public static void main(String[] args) {
		try {
			FileInputStream fin = new FileInputStream( "readandshow.txt" );
			FileChannel fc = fin.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(12);
			//buffer.
			int i = fc.read(buffer);
			System.out.println(i);
			byte[] b = buffer.array();
			System.out.println(bytesToHexStr(b));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
