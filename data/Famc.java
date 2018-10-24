package data;

public class Famc implements Cloneable { 
        public String     tag;
        public int         value;        
        
        public void        clear() {
        	tag = "";
        	value = 0;
        }
        
    	public Famc clone() {
    		try {
    			return (Famc)super.clone();
    		} catch (CloneNotSupportedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}		
    		return null;
    	}
}
