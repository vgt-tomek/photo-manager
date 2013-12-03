package pl.vgtworld.photomanager.model;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import pl.vgtworld.photomanager.model.PhotoContainer;

public class PhotoContainerTest {
	
	class FileStub extends File {

		private static final long serialVersionUID = 1L;
		
		private long lastModified;
		
		FileStub(String filename, long lastModified) {
			super(filename);
			this.lastModified = lastModified;
		}
		
		@Override
		public long lastModified() {
			return lastModified;
		}
	}
	
	@Test
	public void shouldReturnZeroAsLastModifiedForEmptyContainer() {
		PhotoContainer container = new PhotoContainer();
		
		assertEquals(0, container.getLastModified());
	}
	
	@Test
	public void shouldReturnProperJpgFile() {
		File jpg = new FileStub("some-file.jpg", 12345L);
		PhotoContainer container = new PhotoContainer();
		
		container.setJpg(jpg);
		
		assertEquals(jpg, container.getJpg());
	}
	
	@Test
	public void shouldReturnProperRawFile() {
		File raw = new FileStub("some-file.cr2", 12345L);
		PhotoContainer container = new PhotoContainer();
		
		container.setRaw(raw);
		
		assertEquals(raw, container.getRaw());
	}
	
	@Test
	public void shouldReturnProperTimestamp() {
		long timestamp = 574355346L;
		File jpg = new FileStub("some-file.jpg", timestamp);
		File raw = new FileStub("some-file.raw", timestamp);
		PhotoContainer container = new PhotoContainer();
		
		container.setJpg(jpg);
		container.setRaw(raw);
		
		assertEquals(574355346L, container.getLastModified());
	}
	
	@Test
	public void shouldReturnTimestampOfEarlierJpgFile() {
		File jpg = new FileStub("some-file.jpg", 1234567L);
		File raw = new FileStub("some-file.raw", 2234567L);
		PhotoContainer container = new PhotoContainer();
		
		container.setRaw(raw);
		container.setJpg(jpg);
		
		assertEquals(1234567L, container.getLastModified());
	}
	
	@Test
	public void shouldReturnTimestampOfEarlierRawFile() {
		File jpg = new FileStub("some-file.jpg", 2234567L);
		File raw = new FileStub("some-file.raw", 1234567L);
		PhotoContainer container = new PhotoContainer();
		
		container.setJpg(jpg);
		container.setRaw(raw);
		
		assertEquals(1234567L, container.getLastModified());
	}
	
	@Test
	public void shouldReturnTimestampOfJpgFile() {
		long timestamp = 1234568L;
		File file = new FileStub("some-file.jpg", timestamp);
		PhotoContainer container = new PhotoContainer();
		
		container.setJpg(file);
		
		assertEquals(1234568L, container.getLastModified());
	}
	
	@Test
	public void shouldReturnTimestampOfRawFile() {
		long timestamp = 1234562L;
		File file = new FileStub("some-file.raw", timestamp);
		PhotoContainer container = new PhotoContainer();
		
		container.setRaw(file);
		
		assertEquals(1234562L, container.getLastModified());
	}
	
}
