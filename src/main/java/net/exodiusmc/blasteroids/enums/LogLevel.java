package net.exodiusmc.blasteroids.enums;

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
