package test;

public class TestSend {
	public static void main(String[] args) {
		/*String xml = "<?xml version='1.0' encoding='UTF-8'?>"+
					"<msg>"+
						"<type>16</type>"+	
						"<mail_to>cindy_lee_wh@qq.com</mail_to>"+	
						"<mail_templete_code>cgt</mail_templete_code>"+	
					"</msg>";*/
		String xml = "<?xml version='1.0' encoding='UTF-8'?>"+
			"<msg>"+
				"<type>14</type>"+	
				"<mms_sequenceid>47325EF46BB38339FDCB3C94D660EBB5</mms_sequenceid>"+	
				"<mms_send_result>1</mms_send_result>"+	
			"</msg>";
		HttpToolkit.postMessage(xml, "http://192.168.1.24:8888/exp/m");
	}
}
