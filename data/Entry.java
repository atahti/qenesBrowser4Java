package data;

public class Entry implements Cloneable {	
	public Date2 day = new Date2();
	public int type;
	public String place;
	public String cause;
	public String attrType;
	public String agency;
	public int age;
	public String attrText;
	public boolean entryLineYes;
	public Note note;
	public MultiMedia multiMedia;
	public Source source;
	public Address address;
	public int privacyPolicy;
	
	public Entry() {
		note = new Note();
		multiMedia = new MultiMedia();
		address = new Address();
		source = new Source();		
	}
	
	public Entry(Entry en) {
		en.day.copy(this.day);
		//this.day =  en.day;
		this.type = en.type;
		this.place = en.place;
		this.cause = en.cause;
		this.attrType = en.attrType;
		this.agency = en.agency;
		this.age = en.age;
		this.attrText = en.attrText;
		this.entryLineYes = en.entryLineYes;
		this.note = new Note(en.note);
		this.multiMedia = new MultiMedia(en.multiMedia);
		this.source = new Source(en.source);
		this.address = new Address(en.address);
		this.privacyPolicy = 0;
	}
	
	public void clear() {
		//day.clear();
		//day.set(1, 1, 1);
		type = 0;
		place = "";
		cause = "";
		attrType = "";
		agency = "";
		age = 0;
		attrText = "";
		entryLineYes = false;
		this.note.clear();
		this.multiMedia.clear();
		this.source.clear();
		this.address.clear();
	}
	
	
/*	
	public Entry clone() {		
		try {
			return (Entry)super.clone();
		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}
		return null;
	}
	*/
	public String typeToString() {
		return ENTRYS.toString(type);
	}
	
}
