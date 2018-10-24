package data;

public class Address {
	public String line;
	public String line1;
	public String line2;
	public String city;
	public String state;
	public String post;
	public String country;
	public String email;
	
	public boolean contains(String string) {
		if (line.contains(string) || 
				line1.contains(string) || 
				line2.contains(string) || 
				city.contains(string) ||
				state.contains(string) || 
				post.contains(string) || 
				country.contains(string) || 
				email.contains(string)) return true;
		return false;
	}
	
	public void clear() {
		line = "";
		line1 = "";
		line2 = "";
		city = "";
		state = "";
		post = "";
		country = "";
		email = "";
	}
	
	public Address() {
		this.clear();
	}
	
	public Address(Address a) {
		this.line = a.line;
		this.line1 = a.line1;
		this.line2 = a.line2;
		this.city = a.city;
		this.state = a.state;
		this.post = a.post;
		this.country = a.country;
		this.email = a.email;
	}
	
	public String all() {
		String out = "";
		
		if ( !line.isEmpty()) out = line;
		if (!line1.isEmpty()) out += " " + line1;
		if (!line2.isEmpty()) out += " " + line2;
		if (!city.isEmpty())  out += " " + city;
		/*if (!state.equals("")) out += " " + state;
		if (!post.equals("")) out += " " + post;
		if (!country.equals("")) out += " " + country;
		*/
		
	//out = line + line1 + line2 + city + state + post + country;
//out = "";
		return out;
	}
}
