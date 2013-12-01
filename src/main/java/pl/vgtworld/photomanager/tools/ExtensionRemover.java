package pl.vgtworld.photomanager.tools;


class ExtensionRemover {
	
	String removeExtension(String filename) {
		int indexOfLastDot = filename.lastIndexOf('.');
		if (indexOfLastDot == -1) {
			return filename;
		}
		return filename.substring(0, indexOfLastDot);
	}
}
