package cindy.util;

import java.util.Date;

public class Code {
	
	public static String getCode(int length){
		
		String md5Str = MD5.MD5Encode(new Date().getTime()+"");
		
		return md5Str.substring(0,length);
	}
	
	public static String getCode(){
		
		String md5Str = MD5.MD5Encode(new Date().getTime()+""+Math.random());
		
		return md5Str;
	}
}
