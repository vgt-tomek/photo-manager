package pl.vgtworld.photomanager.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class FilelistConverter {
	
	private static final Logger LOGGER = Logger.getLogger(FilelistConverter.class);
	
	private static ExtensionRemover extensionRemover = new ExtensionRemover();
	
	public List<PhotoContainer> convertToPhotoContainerList(List<File> files) {
		Map<String, PhotoContainer> photos = new HashMap<>();
		for (File file : files) {
			insertFileIntoMap(photos, file);
		}
		
		return convertIntoList(photos);
	}
	
	private void insertFileIntoMap(Map<String, PhotoContainer> photos, File file) {
		PhotoContainer container = null;
		String name = extensionRemover.removeExtension(file.getName());
		if (photos.containsKey(name)) {
			container = photos.get(name);
		} else {
			container = new PhotoContainer();
		}
		
		if (file.getName().toLowerCase().endsWith("jpg")) {
			container.setJpg(file);
		} else if (file.getName().toLowerCase().endsWith("cr2")) {
			container.setRaw(file);
		} else {
			LOGGER.warn(String.format("Unexpected extension %1$s. Ignoring file.", file.getName()));
			return;
		}
		
		if (photos.containsKey(name) == false) {
			photos.put(name, container);
		}
	}
	
	private List<PhotoContainer> convertIntoList(Map<String, PhotoContainer> map) {
		List<PhotoContainer> list = new ArrayList<>();
		for (PhotoContainer photo : map.values()) {
			list.add(photo);
		}
		return list;
	}
}
