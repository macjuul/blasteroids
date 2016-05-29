package net.exodiusmc.blasteroids.enums;

public enum LogLevel {
	WARNING("[WARNING]"),
	ERROR("[ERROR]"),
	INFO("[INFO]"),
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
