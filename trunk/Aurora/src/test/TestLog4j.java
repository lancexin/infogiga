package test;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class TestLog4j {
	
	static Logger log = Logger.getLogger(TestLog4j.class.getName());
	public static void main(String[] args) {
		MDC.put("userId", "1");
		MDC.put("levels", "M");
		MDC.put("event", "Hello");
		log.error("abcd");
	}
}
