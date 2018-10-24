package data;

import java.util.ArrayList;
import java.util.List;

public class PersonData  { //implements Cloneable	
	public List<Entry>  	entry 		= new ArrayList<Entry>();
	public List<MultiMedia> multiMedia 	= new ArrayList<MultiMedia>();
	public List<String> 	famsTags 	= new ArrayList<String>();
	public List<String> 	submTags 	= new ArrayList<String>();
	public List<Integer>  	famSList 	= new ArrayList<Integer>();
	public List<Integer>	submitters	= new ArrayList<Integer>();
	public String 		nameFamily;
	public String 		name1st;
	public String 		name2nd;
	public String 		name3rd;	
	public String		desiTag;
	public String		ansiTag;
	public String		afn;
	public String		anciTag;
	public Famc			famc 			= new Famc();
	public Famc			famcSecondary 	= new Famc();
	public Note			note 			= new Note();
	public Note			nameNote 		= new Note();	
	public PEDI_TYPE 	pedi;
	public ADOPTED_BY	adoptedBy;
	public String 		nameGedCom;
	public String		refnType;
	public String		rfn;
	public String		rin;
	public SEX			sex;
	public String refn;	
	public Source		nameSource;//		= new Source();
	public Source		source;//			= new Source();	
	public int 			id, famSId;
	public Changed	changed;// = new Changed();
	public int 	privacyPolicy;
	public int		descentants;
	
	public PersonData() {
		this.note = new Note();
		nameSource = new Source();
		source = new Source();
		changed = new Changed();
		this.clear();
	}
		
	public void clear() {		
		id = 0;
		famSId = 0;
		nameFamily 	= "";
		name1st 	= "";
		name2nd 	= "";
		name3rd 	= "";		
		desiTag 	= "";
		ansiTag 	= "";
		afn 		= "";
		anciTag 	= "";
		pedi 		= PEDI_TYPE.NA;
		adoptedBy 	= ADOPTED_BY.NA;
		nameGedCom 	= "";
		refnType 	= "";
		refn		= "";
		rfn 		= "";
		rin 		= "";
		sex 		= SEX.UNKNOWN;		
		famSList.clear();
		famSId		= 0;
		famc.clear();
		famcSecondary.clear();
		multiMedia.clear();
		entry.clear();
		famsTags.clear();
		submTags.clear();
		famSList.clear();
		note.clear();
		nameNote.clear();
		source.clear();
		nameSource.clear();
		submitters.clear();
		changed.clear();
		privacyPolicy = 0;
		descentants = -1; // -1 tarkoittaa että tätä ei ole laskettu vielä, arvot lasketaan latauksen jälkeen
	}

	public String nameShort() {
		return this.nameFamily + " " + this.name1st;
	}
	
	public String iEventDateText(ENTRYS t)
	{
		int i;
		int type = ENTRYS.toInt(t);
		
		for (i=0 ; i<this.entry.size() ; i++) {
			if (this.entry.get(i).type == type) return this.entry.get(i).day.toString();
		}
		return "";
	}
	
	public PersonData(PersonData pd) {
		this.name1st = pd.name1st;
		this.name2nd = pd.name2nd;
		this.name3rd = pd.name3rd;		
		this.nameFamily = pd.nameFamily;
		this.famc = pd.famc.clone();
		this.famcSecondary = pd.famcSecondary.clone();
		this.id = pd.id;
		this.nameGedCom = pd.nameGedCom;
		this.pedi = pd.pedi;
		this.sex = pd.sex;
		this.adoptedBy = pd.adoptedBy;
		this.anciTag = pd.anciTag;
		this.ansiTag = pd.ansiTag;
		this.afn = pd.afn;
		this.pedi = pd.pedi;
		this.nameGedCom = pd.nameGedCom;
		this.refnType = pd.refnType;
		this.rfn = pd.rfn;
		this.rin = pd.rin;
		this.refn = pd.refn;
		this.nameSource = new Source(pd.nameSource);
		this.source = new Source(pd.source);
		this.changed = new Changed(pd.changed);
		this.privacyPolicy = pd.privacyPolicy;
		this.descentants = pd.descentants;
		
		for (int i=0 ; i<pd.entry.size() ; i++) this.entry.add(pd.entry.get(i));// this.entry.add(pd.entry.get(i).clone());
		for (int i=0 ; i<pd.famsTags.size() ; i++ ) this.famsTags.add(pd.famsTags.get(i));
		for (int i=0 ; i<pd.multiMedia.size() ; i++) this.multiMedia.add(pd.multiMedia.get(i));
		
		this.note = new Note(pd.note);
		this.nameNote = new Note(pd.nameNote);
				
		//this.entry = (List<Entry>) ((ArrayList)(this.entry)).clone();
	}

	public int father(GeneData gd) { return gd.fd[famc.value].husb; }

	public int mother(GeneData gd) { return gd.fd[famc.value].wife; }

	public int wife(GeneData gd) { return gd.fd[famS()].wife; }

	public int husb(GeneData gd) { return gd.fd[famS()].husb; }
	
	public int famS() { 
		if (famSList.size() == 0) return 0;
		return famSList.get(famSId);		
	}
	
	public void setFamS(int fams) { famSList.add(fams); 
	}

	public String famsTag(int famsID) { return famsTags.get(famsID); }
	
	public int getSpouse(GeneData gd) {
		int spouse = gd.fd[famS()].husb;	    
	    if ( ( spouse==0 ) || ( spouse == id )) spouse = gd.fd[famS()].wife;	    
	    if (spouse != id) return spouse; else return 0;
	    
	}
	
	public boolean isChild(GeneData gd, int child)
	{
	    int i;
	    for (i=0 ; i< famSList.size() ; i++)
	        if ( ( famSList.get(i) == gd.pd[child].famc.value) && famSList.get(i) != 0 ) return true;
	    return false;
	}
	
	public void childs(GeneData gd, List<Integer> childs, boolean currentSpouseOnly)
	{
	    int i;
	    childs.clear();

	    for (i=1 ; i<=gd.pdLastUsed ; i++) {
	        if (isChild(gd, i) && ( !currentSpouseOnly || ( currentSpouseOnly && gd.pd[getSpouse(gd)].isChild(gd, i)) ) )
	        {
	            childs.add(i);
	        }
	    }
	}
	
	public void siblings(GeneData gd, List<Integer> siblings, boolean currentSpouseOnly)
	{
	    int i;
	    siblings.clear();

	    for (i=1 ; i<=gd.pdLastUsed ; i++) {
	        if (currentSpouseOnly) {
	            if ( (gd.pd[i].famc.value == famc.value) && famc.value != 0) siblings.add(i);
	        } else {
	            if (   ( ( gd.pd[i].father(gd) == father(gd)) && father(gd) != 0 )
	                || ( ( gd.pd[i].mother(gd) == mother(gd)) && mother(gd) != 0 ) )
	                siblings.add(i);
	        }
	    }
	}
	
	public void cousins(GeneData gd, List<Integer> cousins, boolean currentSpouseOnly)
	{
	    int i, ff, fm, mf, mm, mother, father;
	    ArrayList<Integer> myGrandParents = new ArrayList<Integer>();
	    cousins.clear();

	    mother = mother(gd);
	    father = father(gd);

	    myGrandParents.add(gd.pd[father(gd)].father(gd));
	    myGrandParents.add(gd.pd[father(gd)].mother(gd));
	    myGrandParents.add(gd.pd[mother(gd)].father(gd));
	    myGrandParents.add(gd.pd[mother(gd)].mother(gd));

	    for (i=1 ; i<= gd.pdLastUsed ; i++) {
	        ff = gd.pd[gd.pd[i].father(gd)].father(gd);
	        fm = gd.pd[gd.pd[i].father(gd)].mother(gd);
	        mf = gd.pd[gd.pd[i].mother(gd)].father(gd);
	        mm = gd.pd[gd.pd[i].mother(gd)].mother(gd);

	        if ((gd.pd[i].father(gd) != father && gd.pd[i].mother(gd) != mother)) {
	            if (   ((ff != 0) && myGrandParents.contains(ff) )
	                || ((fm != 0) && myGrandParents.contains(fm) )
	                || ((mf != 0) && myGrandParents.contains(mf) )
	                || ((mm != 0) && myGrandParents.contains(mm) ) ) {

	                if ( (currentSpouseOnly
	                    && ( (myGrandParents.contains(ff) && myGrandParents.contains(fm))
	                         || (myGrandParents.contains(mf) && myGrandParents.contains(mf)) ))
	                    || !currentSpouseOnly )
	                    cousins.add(i);
	            }
	        }
	    }
	}
	
	public Entry ievent(ENTRYS type)
	{
		
		return ievent(ENTRYS.toInt(type));
	}
	
	public Entry ievent(int type)
	{
	    for (int i=0 ; i<entry.size() ; i++) {
	        if ( entry.get(i).type == type ) {
	            return entry.get(i);	            
	        }
	    }
	    Entry foo = new Entry();
	    return foo;
	}
}
