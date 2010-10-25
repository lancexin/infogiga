package cn.infogiga.cmpp2.util;

public class Seq {
	private static int count = -1;
	
	public static int getSeq(){
		count++;
		return count;
	}
	
	
}
