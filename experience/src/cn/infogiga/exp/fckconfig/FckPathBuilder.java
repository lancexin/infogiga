package cn.infogiga.exp.fckconfig;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.UserPathBuilder;

public class FckPathBuilder implements UserPathBuilder{

	public String getUserFilesAbsolutePath(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserFilesPath(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		return "http://localhost:8888/exp";
	}

}
