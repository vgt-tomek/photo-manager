package pl.vgtworld.photomanager;

import java.io.File;
import java.util.Calendar;

public class FileStub extends File {

	private static final long serialVersionUID = 1L;
	
	private long lastModified;
	
	public FileStub(String filename, long lastModified) {
		super(filename);
		this.lastModified = lastModified;
	}
	
	public FileStub(String filename, int lastModifiedYear, int lastModifiedMonth, int lastModifiedDay) {
		super(filename);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, lastModifiedYear);
		cal.set(Calendar.MONTH, lastModifiedMonth - 1);
		cal.set(Calendar.DAY_OF_MONTH, lastModifiedDay);
		lastModified = cal.getTime().getTime();
	}
	
	@Override
	public long lastModified() {
		return lastModified;
	}
}

