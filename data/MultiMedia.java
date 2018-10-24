package data;

//import java.util.Calendar;

public class MultiMedia {
		public MULTIMEDIA_FORMAT format;
        public String title;
        public String file;
        public String refn;
        public String refnType;
        public String rin;
        public String tag;
        public int id;
        public Note note = new Note();
        public Changed changed = new Changed();
        
        public void clear() {
        	title = "";
        	file = "";
        	refn = "";
        	refnType = "";
        	rin = "";
        	tag = "";
        	id = 0;
        	note.clear();
        }
        
    	public MultiMedia() {
    		clear();
    	}
    	
    	public MultiMedia(MultiMedia mm) {    		
    		this.format = mm.format;
    		this.title = mm.title;
    		this.file = mm.file;
    		this.refn = mm.refn;
    		this.refnType = mm.refnType;
    		this.rin = mm.rin;    		
    		this.tag = mm.tag;
    		this.id = mm.id;
    		this.note = new Note(mm.note);
    		// note!!
    	}   	
    	
    		
//    		for (int i=0 ; i<pd.entry.size() ; i++) this.entry.add(pd.entry.get(i).clone());
//    		for (int i=0 ; i<pd.famsTags.size() ; i++ ) this.famsTags.add(pd.famsTags.get(i));

    				

}
