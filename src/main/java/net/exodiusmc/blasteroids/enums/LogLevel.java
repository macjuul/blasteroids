package net.exodiusmc.blasteroids.enums;

/**
 * LogLevels can be added to log messages to prefix the with handy meta information.
 * The Logger class comes with some default log methods that will automatically
 * insert one of these LogLevels.
 * 
 * To use custom LogLevels in your log message, use Logger#log(message, LogLevel...);
 * 
 * @see net.exodiusmc.blasteroids.Logger
 */
public enum LogLevel {
	WARNING("[WARNING]"),
	CRITICAL_ERROR("[CRITICAL-ERROR]"),
	ERROR("[ERROR]"),
	INFO("[INFO]"),
	LAYER("[LAYER]"),
	INPUT("[INPUT]"),
	MEDIA("[MEDIA]"),
	RUNTIME("[RUNTIME]"),
	RENDER("[RENDER]");
	
	private String tag;
	
	private LogLevel(String tag) {
		this.tag = tag;
	}
	
	public String asTag() {
		return tag;
	}
}
