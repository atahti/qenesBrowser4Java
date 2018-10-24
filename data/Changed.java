package data;

public class Changed {
	public Time2 time = new Time2();
	public Date2 date = new Date2();

	public Changed() {
		clear();
	}
	
	public void clear() {
		time.clear();
		date.clear();
	}
	
	public Changed (Changed c) {
		this.time = new Time2(c.time);
		this.date = new Date2(c.date);
	}
	
	public void copy(Changed c) {
		time.copy(c.time);
		date.copy(c.date);
	}
}
