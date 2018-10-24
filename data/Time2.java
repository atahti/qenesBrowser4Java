package data;

public class Time2 {
	public int hours;
	public int minutes;
	public int seconds;
	
	public Time2() {
		clear();
	}
	
	public void clear() {
		hours = 0;
		seconds = 0;
		minutes = 0;
	}
	
	public void copy(Time2 d) {
		d.hours = this.hours;
		d.seconds = this.minutes;
		d.minutes = this.seconds;
	}
	
	public Time2(Time2 t) {
		this.hours = t.hours;
		this.minutes = t.minutes;
		this.seconds = t.seconds;
	}
}
