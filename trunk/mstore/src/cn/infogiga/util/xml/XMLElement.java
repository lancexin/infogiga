package cn.infogiga.util.xml;

import java.lang.annotation.ElementType; 
import java.lang.annotation.Retention; 
import java.lang.annotation.RetentionPolicy; 
import java.lang.annotation.Target; 

/**
 * @author Cindy Lee
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.TYPE)
public @interface XMLElement {
	public String value();
	
	public int type();
}
