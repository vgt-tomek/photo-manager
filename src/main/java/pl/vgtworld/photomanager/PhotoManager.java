package pl.vgtworld.photomanager;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import pl.vgtworld.photomanager.apps.Launcher;
import pl.vgtworld.photomanager.cli.Cli;

public class PhotoManager {
	
	private static final Logger LOGGER = Logger.getLogger(PhotoManager.class);
	
	public static void main(String[] args) {
		try {
			Cli cli = new Cli();
			cli.parseArgs(args);
			
			if (cli.getAction() == Cli.Action.NONE) {
				cli.printHelp();
				return;
			}
			
			if (cli.getAction() == Cli.Action.RENAME) {
				renameAction(cli);
			}
		} catch (ParseException e) {
			LOGGER.warn(String.format("Exception caught while parsing arguments (%s).", e.getMessage()));
		}
	}
	
	private static void renameAction(Cli cli) {
		if (!cli.hasOption("p")) {
			LOGGER.error("Path to target directory is required. Use -h for more information.");
			return;
		}
		if (!cli.hasOption("d")) {
			LOGGER.error("Number of digits for renaming files is required.");
			return;
		}
		
		String path = cli.getOptionValue("p");
		int digits = 0;
		try {
			digits = Integer.parseInt(cli.getOptionValue("d"));
			if (digits < 1) {
				throw new NumberFormatException("Value below one");
			}
		} catch (NumberFormatException e) {
			LOGGER.error("Invalid number provided for -d parameter.");
			return;
		}
		
		Launcher launcher = new Launcher();
		launcher.rename(path, digits);
	}
	
}
