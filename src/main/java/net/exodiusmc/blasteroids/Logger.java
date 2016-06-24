package net.exodiusmc.blasteroids;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import net.exodiusmc.blasteroids.enums.LogLevel;

/**
 * The Logger class is used to log messages to the console and
 * log file output.
 */
public class Logger {
	private static String filename = "log.txt";
	private static Logger instance;
	private File logfile;
	private BufferedWriter writer;
	
	/**
	 * Returns the associated Logger object
	 * 
	 * @return Logger
	 */
	public static Logger getLogger() {
		if(instance == null) {
			instance = new Logger();
		}
		
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	private Logger() {
		this.logfile = new File(filename);
		
		if(!logfile.exists()) {
			try {
				logfile.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			this.writer = new BufferedWriter(new FileWriter(logfile, logfile.length() < 20480 /* 20 KB */));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			Date d = new Date();
			this.writer.newLine();
			this.writer.write("********** Loading Blasteroids " + Main.getVersion() + " (" + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ") **********");
			this.writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set the log destination of the logfile output
	 * 
	 * @param name String
	 */
	public static void setLogDestination(String name) {
		filename = name;
	}
	
	/**
	 * Perform a basic log message. Custom LogLevels can be included to alter
	 * the tags inserted before the message
	 * 
	 * @param message String
	 * @param tags LogLevels
	 */
	@SuppressWarnings("deprecation")
	public void log(String message, LogLevel... tags) {
		Date d = new Date();
		String msg = "[" + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + "] ";
		boolean error = false;
		boolean critical = false;
		
		for(LogLevel tag : tags) {
			msg += tag.asTag() + " ";
			
			if(tag.equals(LogLevel.ERROR)) error = true;
			if(tag.equals(LogLevel.CRITICAL_ERROR)) critical = true;
		}
		
		msg += message;
		
		if(error || critical) {
			System.err.println(msg);
		} else {
			System.out.println(msg);
		}
		
		try {
			writer.write(msg);
			writer.newLine();
			writer.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(critical) {
			System.exit(0);
		}
	}
	
	/**
	 * Perform a warn log message. Alias to {@link Logger#log(String, LogLevel...)}
	 * 
	 * @param message String
	 */
	public void warn(String message) {
		this.log(message, LogLevel.WARNING);
	}
	
	/**
	 * Perform an error log message. Alias to {@link Logger#log(String, LogLevel...)}
	 * 
	 * @param message String
	 */
	public void error(String message) {
		this.log(message, LogLevel.ERROR);
	}
	
	/**
	 * Perform an info log message. Alias to {@link Logger#log(String, LogLevel...)}
	 * 
	 * @param message String
	 */
	public void info(String message) {
		this.log(message, LogLevel.INFO);
	}
}
