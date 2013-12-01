package pl.vgtworld.photomanager.tools;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExtensionRemoverTest {
	
	@Test
	public void shouldReturnTheSameNameForFilenameWithoutExtension() {
		ExtensionRemover remover = new ExtensionRemover();
		
		String result = remover.removeExtension("filename");
		
		assertEquals("filename", result);
	}
	
	@Test
	public void shouldRemoveLowercaseJpgExtension() {
		ExtensionRemover remover = new ExtensionRemover();
		
		String result = remover.removeExtension("IMG_0666.jpg");
		
		assertEquals("IMG_0666", result);
	}
	
	@Test
	public void shouldRemoveUppercaseJpgExtension() {
		ExtensionRemover remover = new ExtensionRemover();
		
		String result = remover.removeExtension("IMG_0667.JPG");
		
		assertEquals("IMG_0667", result);
	}
	
	@Test
	public void shouldRemoveUnusualExtension() {
		ExtensionRemover remover = new ExtensionRemover();
		
		String result = remover.removeExtension("IMG_0668.exteNsion");
		
		assertEquals("IMG_0668", result);
	}
}
