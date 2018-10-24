package data;

public class Note {
	public String tag = "";
	public String text = "";
	public String refn = "";
	public String refnType = "";
	public String rin = "";
	public int id;
	public Changed changed;// = new Changed();
	        
	public void clear() {
		tag = "";
		text = "";
		refn = "";
		refnType = "";
		rin = "";
		changed = new Changed();
	}
	
	public Note() {
		this.clear();
	}
	
	public Note(Note n) {
		this.tag = n.tag;
		this.text = n.text;
		this.refn = n.refn;
		this.refnType = n.refnType;
		this.rin = n.rin;
		this.id = n.id;
		this.changed = new Changed(n.changed);
	}
}
