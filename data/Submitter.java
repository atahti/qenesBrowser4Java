package data;

public class Submitter {	
    public int  id;     // will be used for giving id number if there's many submitters
    public String     tag;
    public String     name;
    public String     language;
    public MultiMedia  multimedia;
    public Address     address;
    public String     rfn;
    public String 		rin;
    static int  count;
    public Changed changed = new Changed();
    
    public void clear() {
    	id = 0;
    	tag = "";
    	name = "";
    	language = "";
    	multimedia.clear();
    	address.clear();
    	rfn = "";
    	rin = "";
    }
    
    public void Sumbitter() {
    	count++;
    }
}