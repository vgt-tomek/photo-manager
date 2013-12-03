package pl.vgtworld.photomanager.apps;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.vgtworld.photomanager.model.PhotoContainer;

public class Renamer {
	
	class PhotoComparator implements Comparator<PhotoContainer> {

		public int compare(PhotoContainer o1, PhotoContainer o2) {
			return (int)(o1.getLastModified() - o2.getLastModified());
		}
		
	}
	
	private int minimumDigits;
	
	public void setMinimumDigits(int digits) {
		minimumDigits = digits;
	}
	
	public void rename(List<PhotoContainer> photos) {
		int counterDigits = (new String("" + photos.size())).length();
		if (counterDigits < minimumDigits) {
			counterDigits = minimumDigits;
		}
		
		Collections.sort(photos, new PhotoComparator());
		
		int counter = 0;
		for (PhotoContainer photo : photos) {
			String newNamePrefix = String.format("%1$tY-%1$tm-%1$td - %2$0" + counterDigits + "d", photo.getLastModified(), ++counter);
			renameFile(photo, newNamePrefix);
		}
	}
	
	private void renameFile(PhotoContainer photo, String newName) {
		File jpg = photo.getJpg();
		File raw = photo.getRaw();
		if (jpg != null) {
			jpg.renameTo(new File(jpg.getParentFile(), newName + ".jpg"));
		}
		if (raw != null) {
			raw.renameTo(new File(raw.getParentFile(), newName + ".cr2"));
		}
	}
}
