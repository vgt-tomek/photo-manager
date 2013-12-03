package pl.vgtworld.photomanager.tools;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pl.vgtworld.photomanager.model.PhotoContainer;

public class FilelistCoverterTest {
	
	@Test
	public void shouldCreateTwoPhotoContainers() {
		File file1 = new File("IMG_001.JPG");
		File file2 = new File("IMG_002.CR2");
		List<File> files = new ArrayList<>();
		FilelistConverter converter = new FilelistConverter();
		
		files.add(file1);
		files.add(file2);
		List<PhotoContainer> photos = converter.convertToPhotoContainerList(files);
		
		assertEquals(2, photos.size());
	}
	
	@Test
	public void shouldCreateOnePhotoContainer() {
		File file1 = new File("IMG_001.JPG");
		File file2 = new File("IMG_001.CR2");
		List<File> files = new ArrayList<>();
		FilelistConverter converter = new FilelistConverter();
		
		files.add(file1);
		files.add(file2);
		List<PhotoContainer> photos = converter.convertToPhotoContainerList(files);
		
		assertEquals(1, photos.size());
	}
	
	@Test
	public void shouldIgnoreFilesWithWrongExtensionAndNotCreateAnyPhotoContainer() {
		File file1 = new File("IMG_001.BMP");
		File file2 = new File("IMG_001.MP3");
		List<File> files = new ArrayList<>();
		FilelistConverter converter = new FilelistConverter();
		
		files.add(file1);
		files.add(file2);
		List<PhotoContainer> photos = converter.convertToPhotoContainerList(files);
		
		assertEquals(0, photos.size());
	}
}
