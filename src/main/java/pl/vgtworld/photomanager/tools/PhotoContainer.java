package pl.vgtworld.photomanager.tools;

import java.io.File;

import org.apache.log4j.Logger;

class PhotoContainer {
	
	private static final Logger LOGGER = Logger.getLogger(PhotoContainer.class);
	
	private File jpg;
	
	private File raw;
	
	public File getJpg() {
		return jpg;
	}
	
	void setJpg(File jpg) {
		this.jpg = jpg;
		if (raw != null) {
			verifyTimestampIntegrity();
		}
	}
	
	File getRaw() {
		return raw;
	}
	
	void setRaw(File raw) {
		this.raw = raw;
		if (jpg != null) {
			verifyTimestampIntegrity();
		}
	}
	
	long getLastModified() {
		if (raw == null && jpg == null) {
			return 0;
		}
		if (raw == null && jpg != null) {
			return jpg.lastModified();
		}
		if (raw != null && jpg == null) {
			return raw.lastModified();
		}
		if (raw.lastModified() < jpg.lastModified()) {
			return raw.lastModified();
		}
		return jpg.lastModified();
	}
	
	private void verifyTimestampIntegrity() {
		if (jpg.lastModified() != raw.lastModified()) {
			String messageFormat =
					"Two files should have the same timestamp but it's different. "
					+ "Using older timestamp for all calculations. %1$s - %3$d, %2$s - %4$d"
					;
			String message = String.format(
					messageFormat,
					jpg.getName(),
					raw.getName(),
					jpg.lastModified(),
					raw.lastModified()
					);
			LOGGER.warn(message);
		}
	}
}
