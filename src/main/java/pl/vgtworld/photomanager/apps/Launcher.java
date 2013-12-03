package pl.vgtworld.photomanager.apps;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import pl.vgtworld.photomanager.PhotoManager;
import pl.vgtworld.photomanager.filters.DateFilter;
import pl.vgtworld.photomanager.model.PhotoContainer;
import pl.vgtworld.photomanager.tools.FilelistConverter;

public class Launcher {
	
	private static final Logger LOGGER = Logger.getLogger(PhotoManager.class);
	
	public void rename(String path, int minimumDigits) {
		File directory = new File(path);
		if (!directory.isDirectory()) {
			LOGGER.fatal("Provided path is not a valid directory.");
			return;
		}
		
		List<File> files = Arrays.asList(directory.listFiles());
		LOGGER.info(String.format("Found %d files.", files.size()));
		
		FilelistConverter converter = new FilelistConverter();
		List<PhotoContainer> photos = converter.convertToPhotoContainerList(files);
		LOGGER.info(String.format("Found %d photos.", photos.size()));
		
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
}
