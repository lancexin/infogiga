package cn.infogiga.szqb.pdf;

import cn.infogiga.szqb.pojo.Periodical;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pdf2ImageConverter converter = new Pdf2ImageConverter();
		converter.setThreadNumber(10);
		for(int i=0;i<10000;i++){
			converter.addConversion(i);
		}
		//converter.startConversion();

	}

}
