package data;

public class Date2 {
	public int year;
	public int month;
	public int date;
	
	public Date2() {
		clear();
	}
	
	public void clear() {
		year = 0;
		month = 0;
		date = 0;
	}
	
	public void copy(Date2 d) {
		d.year = this.year;
		d.month = this.month;
		d.date = this.date;
	}
	
	public Date2(Date2 d) {
		this.year = d.year;
		this.month = d.month;
		this.date = d.date;
	}
	
	public String toString() {
		String out;
		int month2 = 0;

		if (month == 0) month2 = 12; else month2 = month;
		
		out = Integer.toString(date) + "." +
				Integer.toString(month2) + "." + 
				Integer.toString(year);
		
		return out;	
	}
}

