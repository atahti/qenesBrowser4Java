package data;

public class Source {
	public int id;
	public String quay;
	public MultiMedia multiMedia 	= new MultiMedia(); 
	public Note note 				= new Note();
	public Address address 			= new Address();
	public SourceEvent event 		= new SourceEvent();
	public String tag;	        
	public String page;
	public String author;
	public String title;
	public String name;
	public String copyright;
	public String text;
	public String version;
	public String approvedSystemID;
	public String corporation;
	public String productName;
	public String phone;
	public String abbreviation;
	public String publication;
	public String gedComRepoID;
	public String refn;
	public String refnType;
	public String rin;
	public Changed changed = new Changed();
    //QDateTime amended;
        
	public void clear() {
		id = 0;
		// QUALITY quay;
		// MultiMedia multiMedia;
		note.clear();
		address.clear();
		//event
		tag = "";	        
		page = "";
		author = "";
		title = "";
		name = "";
		copyright = "";
		text = "";
		version = "";
		approvedSystemID = "";
		corporation = "";
		productName = "";
		phone = "";
		abbreviation = "";
		publication = "";
		gedComRepoID = "";
		refn = "";
		refnType = ""; 
		rin = "";
		multiMedia.clear();		
	}
	
	public Source() {
		this.clear();
	}
	
	public Source(Source s) {
		id = s.id;
		// QUALITY quay;
		note = new Note(s.note);
		address = new Address(s.address);
		//event
		tag = s.tag;
		page = s.page;
		author = s.author;
		title = s.title;
		name = s.name;
		copyright = s.copyright;
		text = s.text;
		version = s.version;
		approvedSystemID = s.approvedSystemID;
		corporation = s.corporation;
		productName = s.productName;
		phone = s.phone;
		abbreviation = s.abbreviation;
		publication = s.publication;
		gedComRepoID = s.gedComRepoID;
		refn = s.refn;
		refnType = s.refnType; 
		rin = s.rin;
		multiMedia = new MultiMedia(s.multiMedia);
	}
};
