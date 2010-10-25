package test;

import tool.XmlReader;
import tool.XmlToolkit;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s ="a";
		XmlReader xmlReader = new XmlReader();
		boolean ok = xmlReader.read(XmlToolkit.String2Stream(s), "GBK");
		try {
			String temp = xmlReader.getAttributeForPC("smswebservers_request");
		} catch (RuntimeException e) {
			System.out.println("error");
			e.printStackTrace();
			return;
		}
		System.out.println("other");

	}

}
