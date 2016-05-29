package net.exodiusmc.blasteroids;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import net.exodiusmc.blasteroids.enums.LogLevel;

public class Logger {
	private static String filename = "log.txt";
	private static Logger instance;
	private File logfile;
	private BufferedWriter writer;
	
	public static Logger getLogger() {
		if(instance == null) {
			instance = new Logger();
		}
		
		return instance;
	}
	
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
			this.writer = new BufferedWriter(new FileWriter(logfile, true));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setLogDestination(String name) {
		filename = name;
	}
	
	@SuppressWarnings("deprecation")
	public void log(String message, LogLevel... tags) {
		Date d = new Date();
		String msg = "[" + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + "] ";
		boolean error = false;
		
		for(LogLevel tag : tags) {
			msg += tag.asTag() + " ";
			
			if(tag.equals(LogLevel.ERROR)) error = true;
		}
		
		msg += message;
		
		if(error) {
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
	}
	
	public void warn(String message) {
		this.log(message, LogLevel.WARNING);
	}
	
	public void error(String message) {
		this.log(message, LogLevel.ERROR);
	}
	
	public void info(String message) {
		this.log(message, LogLevel.INFO);
	}
}
