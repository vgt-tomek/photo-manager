package pl.vgtworld.photomanager.apps;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import pl.vgtworld.photomanager.filters.DateFilter;
import pl.vgtworld.photomanager.model.PhotoContainer;
import pl.vgtworld.photomanager.tools.FilelistConverter;

public class Launcher {
	
	private static final Logger LOGGER = Logger.getLogger(Launcher.class);
	
	public void rename(String path, int minimumDigits) {
		List<PhotoContainer> photos = loadPhotos(path);
		if (photos == null) {
			return;
		}
		
		DateFilter filter = new DateFilter();
		Map<String, List<PhotoContainer>> groupedPhotos = filter.groupPhotosByDay(photos);
		Set<String> dates = groupedPhotos.keySet();
		LOGGER.info(String.format("Found %d different dates: %s", groupedPhotos.size(), dates.toString()));
		
		for (String date : dates) {
			List<PhotoContainer> photosFromSingleDay = groupedPhotos.get(date);
			LOGGER.info(String.format("Renaming %d photos from date: %s", photosFromSingleDay.size(), date));
			Renamer renamer = new Renamer();
			renamer.setMinimumDigits(minimumDigits);
			renamer.rename(photosFromSingleDay);
		}
	}

	public void delete(String path) {
		List<PhotoContainer> photos = loadPhotos(path);
		if (photos == null) {
			return;
		}
		
		List<File> rawToRemove = new ArrayList<File>();
		for (PhotoContainer photo : photos) {
			if (photo.getRaw() != null && photo.getJpg() == null) {
				rawToRemove.add(photo.getRaw());
			}
		}
		
		LOGGER.info(String.format("Found %d orphaned raw files. Removing.", rawToRemove.size()));
		for (File file : rawToRemove) {
			file.delete();
		}
	}
	
	private List<PhotoContainer> loadPhotos(String path) {
		File directory = new File(path);
		if (!directory.isDirectory()) {
			LOGGER.fatal("Provided path is not a valid directory.");
			return null;
		}
		
		List<File> files = Arrays.asList(directory.listFiles());
		LOGGER.info(String.format("Found %d files.", files.size()));
		
		FilelistConverter converter = new FilelistConverter();
		List<PhotoContainer> photos = converter.convertToPhotoContainerList(files);
		LOGGER.info(String.format("Found %d photos.", photos.size()));
		return photos;
	}
}
