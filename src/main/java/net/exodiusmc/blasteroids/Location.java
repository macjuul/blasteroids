package net.exodiusmc.blasteroids;

/**
 * Represents a Location in the game in a 2D area
 */
public class Location {
	private double x;
	private double y;
	
	/**
	 * Construct a new location zero location
	 */
	public Location() {
		this(0, 0);
	}
	
	/**
	 * Construct a new location from the given x and y units
	 * 
	 * @param x The x unit
	 * @param y The y unit
	 */
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the X unit of this location
	 * 
	 * @return x unit
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Returns the Y unit of this location
	 * 
	 * @return y unit
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Change the X unit of this location
	 * 
	 * @param x X unit
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Change the Y unit of this location
	 * 
	 * @param y Y unit
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Add the given X and Y units to the location
	 * 
	 * @param x X unit
	 * @param y Y unit
	 * @return Location
	 */
	public Location add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	/**
	 * Subtract the given X and Y units from the location
	 * 
	 * @param x X unit
	 * @param y Y unit
	 * @return Location
	 */
	public Location subtract(double x, double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	
	/**
	 * Performs scalar multiplication, multiplying X and Y with the provided unit
	 * 
	 * @param amount The unit to multiply with
	 * @return Location
	 */
	public Location multiply(double a) {
		this.x *= a;
		this.y *= a;
		return this;
	}
	
	/**
	 * Performs multiple scalar multiplication, multiplying X and Y with the provided units
	 * 
	 * @param amount The unit to multiply X with
	 * @param amount The unit to multiply Y with
	 * @return Location
	 */
	public Location multiply(double x, double y) {
		this.x *= x;
		this.y *= y;
		return this;
	}
	
	/**
	 * Resets the location to a zero location
	 * 
	 * @return Location
	 */
	public Location zero() {
		this.x = 0;
		this.y = 0;
		return this;
	}
	
	/**
	 * Construct a new zero location
	 * 
	 * @return Location
	 */
	public static Location constructZero() {
		return new Location(0, 0);
	}
	
	/**
	 * Calculates the distance between this and another location
	 * 
	 * @param location The second location
	 * @return double The distance between the locations
	 */
	public double distance(Location otherLoc) {
		double x1 = this.x;
		double y1 = this.y;
		double x2 = otherLoc.x;
		double y2 = otherLoc.y;
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	/**
	 * Returns the angle in degrees between the current location and
	 * the target location in degrees
	 * 
	 * @param Location otherLoc
	 * @return double Degrees
	 */
	public float getAngle(Location otherLoc) {
		float angle = (float) Math.toDegrees(Math.atan2(otherLoc.getY() - this.y, otherLoc.getX() - this.x)) + 90;

	    if(angle < 0){
	        angle += 360;
	    }
	
	    return angle;
	}
	
	public Location clone() {
		return new Location(this.x, this.y);
	}
	
	@Override
	public String toString() {
		return "{X=" + this.x + ", Y=" + this.y + "}";
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Location) {
			Location loc = (Location) o;
			return this.x == loc.getX() && this.y == loc.getY();
		}
		return false;
	}
}
