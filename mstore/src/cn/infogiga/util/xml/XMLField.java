package cn.infogiga.util.xml;

import java.lang.annotation.ElementType; 
import java.lang.annotation.Retention; 
import java.lang.annotation.RetentionPolicy; 
import java.lang.annotation.Target; 
/**
 * @author Cindy Lee
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.FIELD)
public @interface XMLField {
	public String value() default "";
	public int xmlType();
	public Class mClass() default Class.class;
}