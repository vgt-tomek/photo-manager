package pl.vgtworld.photomanager;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import pl.vgtworld.photomanager.apps.Launcher;
import pl.vgtworld.photomanager.cli.Cli;

public class PhotoManager {
	
	private static final String ARGUMENT_VALUE_DIGITS = "d";

	private static final String ARGUMENT_VALUE_PATH = "p";

	private static final String ARGUMENT_VALUE_HELP = "h";
	
	private static final String ARGUMENT_VALUE_VERBOSE = "v";

	private static final String INVALID_NUMBER_FOR_D_PARAMETER_MESSAGE = "Invalid number provided for -d parameter.";

	private static final String MISSING_NUMBER_OF_DIGITS_PARAMETER_MESSAGE = "Number of digits for renaming files is required.";

	private static final String MISSING_DIRECTORY_MESSAGE = "Path to target directory is required.";
	
	private static final String SEE_HELP_MESSAGE = " Use -h for more information.";
	
	private static final Logger LOGGER = Logger.getLogger(PhotoManager.class);
	
	public static void main(String[] args) {
		try {
			Cli cli = new Cli();
			cli.parseArgs(args);
			
			if (cli.hasOption(ARGUMENT_VALUE_VERBOSE)) {
				Logger.getRootLogger().setLevel(Level.INFO);
			}
			
			if (cli.hasOption(ARGUMENT_VALUE_HELP) || cli.getAction() == Cli.Action.NONE) {
				cli.printHelp();
				return;
			}
			
			if (cli.getAction() == Cli.Action.RENAME) {
				renameAction(cli);
			}
			
			if (cli.getAction() == Cli.Action.DELETE) {
				deleteAction(cli);
			}
		} catch (ParseException e) {
			LOGGER.warn(String.format("Exception caught while parsing arguments (%s).", e.getMessage()));
		}
	}
	
	private static void deleteAction(Cli cli) {
		if (!cli.hasOption(ARGUMENT_VALUE_PATH)) {
			LOGGER.error(MISSING_DIRECTORY_MESSAGE + SEE_HELP_MESSAGE);
			return;
		}
		
		String path = cli.getOptionValue(ARGUMENT_VALUE_PATH);
		Launcher launcher = new Launcher();
		launcher.delete(path);
	}
	
	private static void renameAction(Cli cli) {
		if (!cli.hasOption(ARGUMENT_VALUE_PATH)) {
			LOGGER.error(MISSING_DIRECTORY_MESSAGE + SEE_HELP_MESSAGE);
			return;
		}
		if (!cli.hasOption(ARGUMENT_VALUE_DIGITS)) {
			LOGGER.error(MISSING_NUMBER_OF_DIGITS_PARAMETER_MESSAGE + SEE_HELP_MESSAGE);
			return;
		}
		
		String path = cli.getOptionValue(ARGUMENT_VALUE_PATH);
		int digits = 0;
		try {
			digits = Integer.parseInt(cli.getOptionValue(ARGUMENT_VALUE_DIGITS));
			if (digits < 1) {
				throw new NumberFormatException("Value below one");
			}
		} catch (NumberFormatException e) {
			LOGGER.error(INVALID_NUMBER_FOR_D_PARAMETER_MESSAGE + SEE_HELP_MESSAGE);
			return;
		}
		
		Launcher launcher = new Launcher();
		launcher.rename(path, digits);
	}
	
}
