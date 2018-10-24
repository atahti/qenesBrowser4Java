package data;

import java.util.ArrayList;
import java.util.List;

public class FamilyData implements Cloneable {
	public String gedComTag;
	public int nchi;
	public int husb;
	public int wife;
	public String refnType;
	public String rin;
	public List<Entry> entry = new ArrayList<Entry>();
	public String refn;	 
	public String gedComTagHusb;
	public String gedComTagWife;
	public List<String> gedComTagChilds = new ArrayList<String>();
	public List<String> gedComTagSubmitters = new ArrayList<String>();
	public Note note;
	public MultiMedia multiMedia;
	public List<Source> sourCitations = new ArrayList<Source>();
	public int id;
	public Changed changed = new Changed();
	public int privacyPolicy;
	
	
	public FamilyData() {
		clear();
	}
	
	
	public FamilyData(FamilyData fd) {
		this.gedComTag = fd.gedComTag;
		this.nchi = fd.nchi;
		this.husb = fd.husb;
		this.wife = fd.wife;
		this.refnType = fd.refnType;
		this.rin = fd.rin;
		this.refn = fd.refn;
		this.gedComTagHusb = fd.gedComTagHusb;
		this.gedComTagWife = fd.gedComTagWife;
		this.note = fd.note;
		//this.multiMedia = fd.multiMedia.clone();
		this.id = fd.id;
		
		int i;		
	
		for (i=0 ; i<fd.entry.size() ; i++) this.entry.add(fd.entry.get(i));// this.entry.add(fd.entry.get(i).clone());
		for (i=0 ; i<fd.gedComTagChilds.size() ; i++) this.gedComTagChilds.add(fd.gedComTagChilds.get(i));//clone??
		for (i=0 ; i<fd.gedComTagSubmitters.size() ; i++) this.gedComTagSubmitters.add(fd.gedComTagSubmitters.get(i));
		for (i=0 ; i<fd.sourCitations.size() ; i++) this.sourCitations.add(fd.sourCitations.get(i));		
	}
	
	public void clear() {
		gedComTag = "";
		nchi = 0;
		husb = 0;
		wife = 0;
		refnType = "";
		rin = "";		
		entry.clear();
		gedComTagHusb = "";
		gedComTagWife = "";
		gedComTagChilds.clear();
		gedComTagSubmitters.clear();
	}
	
	public FamilyData clone() {
		try {
			return (FamilyData)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	public Entry fentry(ENTRYS type)
	{
	    int i;
	    int itype = ENTRYS.toInt(type);

	    for (i = 0; i<this.entry.size() ; i++) {
	        if (entry.get(i).type == itype)	return entry.get(i);	        
	    }
	    
	    Entry foo = new Entry(); 
	    //foo.place = "";
	    return foo;	    
	}
	
	public String eventDateText(ENTRYS t)
	{
		int i;
		int type = ENTRYS.toInt(t);
		
		for (i=0 ; i<this.entry.size() ; i++) {
			if (this.entry.get(i).type == type) {
				String out;
				/*int month = this.entry.get(i).day.get(Calendar.MONTH);
				if (month == 0) month = 12;
				out = 	Integer.toString(this.entry.get(i).day.get(Calendar.DATE)) + "." +
						Integer.toString(month) + "." +
						Integer.toString(this.entry.get(i).day.get(Calendar.YEAR));
				*/
				int month = this.entry.get(i).day.month;
				if (month == 0) month = 12;
				
				out =   Integer.toString(this.entry.get(i).day.date) + "." +
						Integer.toString(month) + "." + 
						Integer.toString(this.entry.get(i).day.year);
				
				return out;				
			}
		}
		return "";
	}
}
