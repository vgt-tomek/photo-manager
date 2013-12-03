package pl.vgtworld.photomanager.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.vgtworld.photomanager.model.PhotoContainer;

public class DateFilter {
	
	public Map<String, List<PhotoContainer>> groupPhotosByDay(List<PhotoContainer> photos) {
		Map<String, List<PhotoContainer>> photosGroupedByDay = new HashMap<>();
		for (PhotoContainer photo : photos) {
			long timestamp = photo.getLastModified();
			String date = String.format("%1$tY-%1$tm-%1$td", timestamp);
			if (!photosGroupedByDay.containsKey(date)) {
				photosGroupedByDay.put(date, new ArrayList<PhotoContainer>());
			}
			photosGroupedByDay.get(date).add(photo);
		}
		return photosGroupedByDay;
	}
}
