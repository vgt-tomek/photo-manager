package pl.vgtworld.photomanager.cli;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Cli {
	
	public enum Action {NONE, RENAME, DELETE};
	
	private Options options;
	
	private CommandLineParser parser;
	
	private CommandLine cmd;
	
	public Cli() {
		initializeParser();
	}
	
	public void parseArgs(String[] args) throws ParseException {
		cmd = parser.parse(options, args);
	}
	
	public void printHelp() {
		HelpFormatter help = new HelpFormatter();
		help.printHelp("java -jar photo-manager.jar [rename|delete] <params>", options);
	}
	
	public boolean hasOption(String option) {
		return cmd.hasOption(option);
	}
	
	public String getOptionValue(String option) {
		return cmd.getOptionValue(option);
	}
	
	public Action getAction() {
		String[] args = cmd.getArgs();
		if (args.length == 0) {
			return Action.NONE;
		}
		if (args[0].equals("rename")) {
			return Action.RENAME;
		}
		if (args[0].equals("delete")) {
			return Action.DELETE;
		}
		return Action.NONE;
	}
	
	private void initializeParser() {
		Option help = new Option("h", "help", false, "Help page");
		Option verbose = new Option("v", "verbose", false, "Verbose logging");
		Option digits = new Option("d", true, "Minimum number of digits, while renaming files");
		Option directory = new Option("p", "path", true, "Target directory");
		
		options = new Options();
		options.addOption(help);
		options.addOption(verbose);
		options.addOption(directory);
		options.addOption(digits);
		
		parser = new BasicParser();
	}
}
