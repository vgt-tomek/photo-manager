package pl.vgtworld.photomanager.filters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import pl.vgtworld.photomanager.FileStub;
import pl.vgtworld.photomanager.model.PhotoContainer;

public class DateFilterTest {
	
	@Test
	public void shouldCreateTwoListsForPhotosFromDifferentDays() {
		List<PhotoContainer> photos = new ArrayList<>();
		photos.add(createPhotoContainer("IMG_001", 2013, 12, 13));
		photos.add(createPhotoContainer("IMG_002", 2013, 12, 14));
		DateFilter dateFilter = new DateFilter();
		
		Map<String, List<PhotoContainer>> groupedPhotos = dateFilter.groupPhotosByDay(photos);
		
		assertEquals(2, groupedPhotos.size());
	}
	
	@Test
	public void shouldProperlyParseMonthsWithLeadingZero() {
		List<PhotoContainer> photos = new ArrayList<>();
		photos.add(createPhotoContainer("IMG_001", 2013, 1, 19));
		DateFilter dateFilter = new DateFilter();
		
		Map<String, List<PhotoContainer>> groupedPhotos = dateFilter.groupPhotosByDay(photos);
		
		assertEquals(true, groupedPhotos.containsKey("2013-01-19"));
	}
	
	@Test
	public void shouldProperlyParseDaysWithLeadingZero() {
		List<PhotoContainer> photos = new ArrayList<>();
		photos.add(createPhotoContainer("IMG_001", 2013, 11, 9));
		DateFilter dateFilter = new DateFilter();
		
		Map<String, List<PhotoContainer>> groupedPhotos = dateFilter.groupPhotosByDay(photos);
		
		assertEquals(true, groupedPhotos.containsKey("2013-11-09"));
	}
	
	@Test
	public void shouldProperlyGroupPhotosFromTheSameDay() {
		List<PhotoContainer> photos = new ArrayList<>();
		photos.add(createPhotoContainer("IMG_001", 2013, 12, 15));
		photos.add(createPhotoContainer("IMG_002", 2013, 12, 15));
		photos.add(createPhotoContainer("IMG_003", 2013, 12, 15));
		photos.add(createPhotoContainer("IMG_004", 2013, 12, 15));
		DateFilter dateFilter = new DateFilter();
		
		Map<String, List<PhotoContainer>> groupedPhotos = dateFilter.groupPhotosByDay(photos);
		
		assertEquals(1, groupedPhotos.size());
	}
	
	private PhotoContainer createPhotoContainer(String namePrefix, int year, int month, int day) {
		PhotoContainer photo = new PhotoContainer();
		photo.setJpg(new FileStub(namePrefix + ".jpg", year, month, day));
		photo.setRaw(new FileStub(namePrefix + ".raw", year, month, day));
		return photo;
	}
	
}
