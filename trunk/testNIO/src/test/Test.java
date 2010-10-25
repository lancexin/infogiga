package test;

public class Test {
	public static String bytesToHexStr(byte[] b) {
		if (b == null)
			return "";
		StringBuffer strBuffer = new StringBuffer(b.length * 3);
		for (int i = 0; i < b.length; i++) {
			strBuffer.append(Integer.toHexString(b[i] & 0xff));
			strBuffer.append(" ");
		}
		return strBuffer.toString();
	}
}
