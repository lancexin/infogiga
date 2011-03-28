package cn.infogiga.szqb.pdf;

import java.io.File;

public class NameChangeBean {
	private File fromFile;
	private File toFile;
	
	public NameChangeBean(){
		
	}
	
	public NameChangeBean(File fromFile,File toFile){
		this.fromFile = fromFile;
		this.toFile = toFile;
	}

	public File getFromFile() {
		return fromFile;
	}

	public void setFromFile(File fromFile) {
		this.fromFile = fromFile;
	}

	public File getToFile() {
		return toFile;
	}

	public void setToFile(File toFile) {
		this.toFile = toFile;
	}
	
	
}
