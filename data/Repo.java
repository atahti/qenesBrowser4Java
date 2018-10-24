package data;

public class Repo implements Cloneable {
	       
    public int      id;
    public String   name;
    public Address  address;
    public Note     note;
    public String	refn;
    public String	refnType;
    public String	rin;
    static int      count;
    public Changed changed = new Changed();
    	        
    public void		clear() {
    	id = 0;
    	name = "";
    	address.clear();
    	note.clear();
    	refn = "";
    	refnType = "";
    	rin = "";	        	
    }	   
    
    public Repo clone() {
    	try {
			return (Repo)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
