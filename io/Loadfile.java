package io;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

import data.*;

public final class Loadfile {
	public Loadfile () {		
		String s 				= new String("JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC" ) ;		
		String strindiAttribute	= new String("OCCU EDUC NATI TITL PROP RELI RESI SSN CAST DSCR NCHI NMR INDO");
	    String strindiEvents 	= new String("BIRT CHR CONF DEAT EMIG IMMI EVEN CENS WILL GRAD BURI CREM ADOP RETI PROB NATU BLES");
	    String strfamilyEvents	= new String("MARR DIV ANUL CENS DIVF ENGA MARB MARC MARL MARS EVEN");
	    String straccuracy		= new String("NA ABT CAL EST BEF AFT EXA FRO");
	    
	    int i;
	    String a [  ] = s.split ( "\\s" ) ; for ( i=0; i<a.length ; i++ ) months.add(a[i]); 
	    a = strindiAttribute.split("\\s"); 	for ( i=0; i<a.length ; i++ ) indiAttribute.add(a[i]);
	    a = strindiEvents.split("\\s");		for ( i=0; i<a.length ; i++ ) indiEvents.add(a[i]);
	    a = strfamilyEvents.split("\\s");	for ( i=0; i<a.length ; i++ ) familyEvents.add(a[i]);
	    a = straccuracy.split("\\s");		for ( i=0; i<a.length ; i++ ) accuracy.add(a[i]);  
	}
	
	private String messageOut;
	private static List<String> months = new ArrayList<String>();
	private static List<String> familyEvents = new ArrayList<String>();
	private static List<String> indiEvents = new ArrayList<String>();
	private static List<String> indiAttribute = new ArrayList<String>();
	private static List<String> famList = new ArrayList<String>();
	private static List<String> repoList = new ArrayList<String>();
	private static List<String> indList = new ArrayList<String>();
	private static List<String> objeList = new ArrayList<String>();
	private static List<String> noteList = new ArrayList<String>();
	private static List<String> submList = new ArrayList<String>();
	private static List<String> sourList = new ArrayList<String>();
	private static List<String> accuracy = new ArrayList<String>();	
	private GeneData	gd;
	private PersonData	tmpPd = new PersonData();
	private FamilyData	tmpFd = new FamilyData();
	private Entry		tmpEvent = new Entry();
	private int		level;
	private int		idIndi;
	private int		idFam;
	private int		idSour;
	private int		idObje;
	private int		idNote;
	private int		idRepo;
	private int		idSubm; // tätä ei tarvita
	private String 	command[];
	private String 	commandOld[];
	private String 	jeje[];
	private String 	cmd;
	private String 	param;
	private String 	tag;
	private Date2  date = new Date2();
	private Time2	time = new Time2();
	private String	tmpParam; // tätä ei tarvita..
	private Famc	tmpFamc 			= new Famc();
	private Note	tmpNote 			= new Note();
	private Source	tmpSource 			= new Source();
	private MultiMedia tmpMultimedia 	= new MultiMedia();
    private Address tmpAddress 			= new Address();
    private Repo	tmpRepo 			= new Repo();
    private boolean used;

    private int countDescentants(int id) {
    	// laskee montako riviä kunkin henkilön boxi tarvii jälkeläistaulukossa
    	if (gd.pd[id].descentants != -1) return gd.pd[id].descentants;
    	
    	int result = 0;
    	List<Integer> childs = new ArrayList<Integer>();
    	gd.pd[id].childs(gd, childs, false);
    	if (childs.size() == 0) result = 1;
    	else for (int c=0 ; c<childs.size() ; c++) result += countDescentants(childs.get(c));
    	
    	gd.pd[id].descentants = result;
    	return result;
    }
    
	private MULTIMEDIA_FORMAT mmFormat(String str) {
        if (str.equals("jpg" )) return MULTIMEDIA_FORMAT.JPEG;
        if (str.equals("JPG" )) return MULTIMEDIA_FORMAT.JPEG;
        
        if (str.equals("gif" )) return MULTIMEDIA_FORMAT.GIF;
        if (str.equals("GIF" )) return MULTIMEDIA_FORMAT.GIF;
        
        if (str.equals("bmp" )) return MULTIMEDIA_FORMAT.BMP;
        if (str.equals("BMP" )) return MULTIMEDIA_FORMAT.BMP;

        if (str.equals("ole" )) return MULTIMEDIA_FORMAT.OLE;
        if (str.equals("OLE" )) return MULTIMEDIA_FORMAT.OLE;

        if (str.equals("pcx" )) return MULTIMEDIA_FORMAT.PCX;
        if (str.equals("PCX" )) return MULTIMEDIA_FORMAT.PCX;
        
        if (str.equals("TIF" )) return MULTIMEDIA_FORMAT.TIFF;
        if (str.equals("tif" )) return MULTIMEDIA_FORMAT.TIFF;
        return MULTIMEDIA_FORMAT.NA;	
	}
	
	/* Tämä on qenesissä, mutta ei käytetä täällä
    private String pedi(PEDI_TYPE pt)
    {
        if (pt == PEDI_TYPE.ADOPTED) return "adopted";
        if (pt == PEDI_TYPE.BIRTH)   return "birth";
        if (pt == PEDI_TYPE.FOSTER)  return "foster";
        if (pt == PEDI_TYPE.SEALING) return "sealing";
        return "";
    }
    */

    private PEDI_TYPE pedi(String str)
    {
        if (str.equals("adopted"))   return PEDI_TYPE.ADOPTED;
        if (str.equals("birth"))     return PEDI_TYPE.BIRTH;
        if (str.equals("foster"))    return PEDI_TYPE.FOSTER;
        if (str.equals("sealing"))   return PEDI_TYPE.SEALING;
        return PEDI_TYPE.NA;
    }
    
    QUALITY quality(String str)
    {
        if (str.equals("0")) return QUALITY.QUAY0;
        if (str.equals("1")) return QUALITY.QUAY1;
        if (str.equals("2")) return QUALITY.QUAY2;
        if (str.equals("3")) return QUALITY.QUAY3;
        return QUALITY.NO_QUAY;
    }

    String familyEventRole(ENTRYf_ROLE fer)
    {
        if (fer == ENTRYf_ROLE.CHILD) return "child";
        if (fer == ENTRYf_ROLE.HUSBAND) return "husband";
        if (fer == ENTRYf_ROLE.WIFE) return "wife";
        if (fer == ENTRYf_ROLE.MOTHER) return "mother";
        if (fer == ENTRYf_ROLE.FATHER) return "father";
        if (fer == ENTRYf_ROLE.SPOUSE) return "spouse";
        return "";
    }    

    ENTRYf_ROLE familyEventRole(String fer)
    {
        if (fer.equals("child")) { return ENTRYf_ROLE.CHILD; }
        if (fer.equals("husband")) { return ENTRYf_ROLE.HUSBAND; }
        if (fer.equals("wife")) { return ENTRYf_ROLE.WIFE; }
        if (fer.equals("mother")) { return ENTRYf_ROLE.MOTHER; }
        if (fer.equals("father")) { return ENTRYf_ROLE.FATHER; }
        if (fer.equals("spouse")) { return ENTRYf_ROLE.SPOUSE; }
        return ENTRYf_ROLE.NA;        
    }
    
    // headRecordEnded()
    
    public boolean findGedComTag(String str) {      
      // toimii v�h�n eri tavalla kuin Qenelogyss�, palauttaa true jos str on @jotakin@, muuten false
      if (str.endsWith("@") && str.startsWith("@")) return true;
      return false;
    }
	
    private void gedComTimeAnalyse(String param) {
      Pattern p = Pattern.compile(":");
      String tulos[];
      tulos = new String[3];
      
      tulos = p.split(param);
      
      time.hours = Integer.parseInt(tulos[0]);
      time.minutes = Integer.parseInt(tulos[1]);
      time.seconds = Integer.parseInt(tulos[2]);
    }

    private void gedComDayAnalyze(String param) {
      Pattern p = Pattern.compile("\\s");
      String tulos[]; tulos = new String[3];
      
      tulos = p.split(param);
      
      int day = 1;
      int month = 1;
      int year = 1;

      if (tulos.length == 1 ) year = Integer.parseInt(tulos[0]);

      if (tulos.length == 2 ) {
	month	= months.indexOf(tulos[0])+1;
	year	= Integer.parseInt(tulos[1]);
      }
      
      if (tulos.length == 3 ) {			
	      day		= Integer.parseInt(tulos[0]);
	      month	= months.indexOf(tulos[1])+1;
	      year	= Integer.parseInt(tulos[2]);
      }

      if (tulos.length == 4 ) {
	      // accuracy here
	      day		= Integer.parseInt(tulos[1]);
	      month	= months.indexOf(tulos[2])+1;
	      year	= Integer.parseInt(tulos[3]);
      }

      date.year = year;
      date.month = month;
      date.date = day;
      
      //dateTime.set(year, month, day);		
    }

    public void gedComLineAnalyze() {
      if (level == 0) {
	  if (jeje.length == 2) {
		  cmd = jeje[1];
		  param = "";
	  }
	  if (jeje.length == 3) {
		  cmd = jeje[2];
		  param = jeje[1];
	  }

	  tag = param;
      } else {
	  if (jeje.length > 1) cmd = jeje[1];
	  if (jeje.length > 2) param = jeje[2];
      }	
    }
    
    
    // commandLeve()

    private boolean isCommand(String cmd[], String cmd0, String cmd1, String cmd2)
    {
	int i = 0;

	if (!cmd0.equals("**")) {
	    if ( ( (cmd[0].equals(cmd0)) || (cmd0.equals("*")) ) &&
		  ( (cmd[1].equals(cmd1)) || (cmd1.equals("*")) ) && //) return true;
		  ( (cmd[2].equals(cmd2)) || (cmd2.equals("*")) ) ) return true;
	} else {
	    while ( ( !cmd[i].equals(cmd1)) && (i<8) ) i++;
	    if ( (cmd[i].equals(cmd1) ) && ( ( cmd2.equals(cmd[i+1] )) || ( cmd2.equals("*") ) ) ) return true;
	}

	return false;
    }
	
    // isCommand()

    private boolean hasRecordEnded(String cmd)
    {
	if ( isCommand(commandOld, "**", cmd, "*") && (isCommand(command, "**", cmd, "") ) ) return true;

	for (int i=8 ; i>=0 ; i--) {
	    if (commandOld[i].equals(cmd) ) {

		if ( !command[i].equals(cmd) ) return true;
		else return false;
	    }
	}
	return false;

    }

	private void noteRecordEnded()
	{
	    gd.spacePd(idIndi);
	    gd.spaceFd(idFam);
	    
	    
	    if ( isCommand(commandOld, "INDI", "NOTE", "*") ) {
	        if (tmpNote.text.equals("QENES ROOT")) { 
	        	gd.rootId = idIndi;
	        	tmpNote.text = "";
	        	used = true;
	        	System.out.println("Rootid set at " + gd.rootId + " at loadfile");
        	}
	            else {
	                tmpPd.note = new Note(tmpNote);	                
	                tmpNote.clear();	                
	                used = true;
	            }
	        }
	    
	    if ( isCommand(commandOld, "HEAD", "NOTE", "*") ) { gd.header.note = tmpNote.text; tmpNote.clear(); used = true; } // t�m� lis��ttiin vain siksi ett� tmpParam:lla tehd��n jotain
	    // head / note luetaan analyseHeadRecordissa
	    if ( isCommand(commandOld, "**", "SOUR", "NOTE") ) { tmpSource.note = new Note(tmpNote); tmpNote.clear(); used = true; }
	    if ( isCommand(commandOld, "FAM", "NOTE", "*") ) { 
	      if (tmpNote.text.equals("QENES ROOT")) { 
	    	  gd.rootFams = idFam;
	    	  used = true;
	    	  }
	      else {
		tmpFd.note = new Note(tmpNote); 
		tmpNote.clear();
		used = true;
	      }
	    }
	    if ( isCommand(commandOld, "INDI", "NAME", "NOTE") ) { tmpPd.nameNote = new Note(tmpNote); tmpNote.clear(); used = true; }
	    if ( isCommand(commandOld, "**", "REPO", "NOTE") )   { tmpRepo.note = new Note(tmpNote); tmpNote.clear(); used = true; }
	    if ( isCommand(commandOld, "**", "OBJE", "NOTE") )   { tmpMultimedia.note = new Note(tmpNote); tmpNote.clear(); used = true; }
	    if ( isCommand(commandOld, "**", "SOUR", "NOTE") ) { tmpSource.note = new Note(tmpNote); tmpNote.clear(); used = true; }
	    // t�t� ei tueta, voidaan tallentaa SOUR/NOTE if ( isCommand(commandOld, "SOUR", "DATA", "NOTE") ) { sour[idSour].data.note = tmpNote; tmpNote.clear();}
	    	    
	    if ( isFamilyEvent(commandOld) != 0 ) { tmpEvent.note = new Note(tmpNote); tmpNote.clear(); used = true; }
	    if ( isIndiEvent(commandOld) != 0) { tmpEvent.note = new Note(tmpNote); tmpNote.clear(); used = true; }
	}

	private void addrRecordEnded()
	{
	    if (isCommand(commandOld, "HEAD", "SOUR", "CORP") ) { tmpSource.address = new Address(tmpAddress); tmpAddress.clear(); used = true; }
	    if (isCommand(commandOld, "SUBM", "ADDR", "*") ) { gd.sub.address = new Address(tmpAddress); tmpAddress.clear(); used = true; }
	    if (isCommand(commandOld, "REPO", "ADDR", "*") ) { tmpRepo.address = new Address(tmpAddress);  tmpAddress.clear(); used = true; }
	    
	    if (isFamilyEvent(commandOld) != 0|| isIndiEvent(commandOld) != 0) { tmpEvent.address = new Address(tmpAddress); tmpAddress.clear(); used = true; }
	}

	private void objeRecordEnded()
	{
	    // tarkastetaan viel� onko FORM tod n�k oikein
	    if (tmpMultimedia.format == MULTIMEDIA_FORMAT.NA) {
	        if (!tmpMultimedia.file.equals("") ) {
	            // { NO_MM, BMP_MM, GIF_MM, JPEG_MM, OLE_MM, PCX_MM, TIFF_MM, WAV_MM };
	        	tmpMultimedia.format = mmFormat(gd.right(tmpMultimedia.file, 3));	            
	        }
	    }
	    if ( isCommand(commandOld, "FAM", "OBJE", "*") )   { tmpFd.multiMedia = new MultiMedia(tmpMultimedia); tmpMultimedia.clear(); used = true; }
	    if ( isCommand(commandOld, "**", "SOUR", "OBJE") ) { tmpSource.multiMedia = new MultiMedia(tmpMultimedia); tmpMultimedia.clear(); used = true; }
	    if ( isCommand(commandOld, "SUBM", "OBJE", "*") )  { gd.sub.multimedia = new MultiMedia(tmpMultimedia);  tmpMultimedia.clear(); used = true; }
	    if ( isCommand(commandOld, "INDI", "OBJE", "*") )  { tmpPd.multiMedia.add(new MultiMedia(tmpMultimedia)); tmpMultimedia.clear();	 used = true; }
	    if ( isFamilyEvent(commandOld) != 0 || isIndiEvent(commandOld ) != 0) { tmpEvent.multiMedia = new MultiMedia(tmpMultimedia); tmpMultimedia.clear();}
	}

	private void sourRecordEnded()
	{
	    if ( isCommand(commandOld, "FAM", "SOUR", "*") ) { tmpFd.sourCitations.add(tmpSource); used = true; used = true; }
	    if ( isCommand(commandOld, "HEAD", "SOUR", "*")) { gd.header.source = new Source(tmpSource); tmpSource.clear(); used = true;  }
	    if ( isCommand(commandOld, "INDI", "NAME", "SOUR")) { tmpPd.nameSource = new Source(tmpSource); tmpSource.clear(); used = true; }
	    if ( isCommand(commandOld, "INDI", "SOUR", "*")) { tmpPd.source = new Source(tmpSource); tmpSource.clear(); used = true; }
	    if ( isFamilyEvent(commandOld) != 0 || isIndiEvent(commandOld) != 0) { tmpEvent.source = new Source(tmpSource); tmpSource.clear(); used = true; }
	}

	private void famEventEnded()
	{
	    ENTRYS eventType = ENTRYS.NA;
	    gd.spaceFd(idFam);	    
	    if ( isCommand(commandOld, "FAM", "MARR", "*" ) ) eventType = ENTRYS.MARRIAGE;
	    if ( isCommand(commandOld, "FAM", "ANUL", "*" ) ) eventType = ENTRYS.ANNULMENT;
	    if ( isCommand(commandOld, "FAM", "CENS", "*" ) ) eventType = ENTRYS.CENSUS;
	    if ( isCommand(commandOld, "FAM", "DIV" , "*" ) ) eventType = ENTRYS.DIVORCE;
	    if ( isCommand(commandOld, "FAM", "DIVF", "*" ) ) eventType = ENTRYS.DIVORCE_FILED;
	    if ( isCommand(commandOld, "FAM", "ENGA", "*" ) ) eventType = ENTRYS.ENGAGEMENT;
	    if ( isCommand(commandOld, "FAM", "MARB", "*" ) ) eventType = ENTRYS.MARRIAGE_BANN;
	    if ( isCommand(commandOld, "FAM", "MARC", "*" ) ) eventType = ENTRYS.MARRIAGE_CONTRACT;
	    if ( isCommand(commandOld, "FAM", "MARL", "*" ) ) eventType = ENTRYS.MARRIAGE_LICENSE;
	    if ( isCommand(commandOld, "FAM", "MARS", "*" ) ) eventType = ENTRYS.MARRIAGE_SETTLEMENT;
	    if ( isCommand(commandOld, "FAM", "EVEN", "*" ) ) eventType = ENTRYS.EVEN;
	    if (eventType != ENTRYS.NA) {	    	
	    	tmpEvent.type = ENTRYS.toInt(eventType);
	        tmpFd.entry.add(new Entry(tmpEvent));
	        used = true;
	    }
	    tmpEvent.clear();
	}

	private void indiEventEnded()
	{		
	    gd.spacePd(idIndi);	    
	    ENTRYS eventType = ENTRYS.NA;
	    if ( isCommand(commandOld, "INDI", "BIRT", "*") ) eventType = ENTRYS.BIRTH;
	    if ( isCommand(commandOld, "INDI", "DEAT", "*") ) eventType = ENTRYS.DEATH;
	    if ( isCommand(commandOld, "INDI", "BURI", "*") ) eventType = ENTRYS.BURIED;
	    if ( isCommand(commandOld, "INDI", "CHR", "*") )  eventType = ENTRYS.CHRISTENING;
	    if ( isCommand(commandOld, "INDI", "IMMI", "*") ) eventType = ENTRYS.IMMIGRATION;
	    if ( isCommand(commandOld, "INDI", "CENS", "*") ) eventType = ENTRYS.ICENSUS;
	    if ( isCommand(commandOld, "INDI", "WILL", "*") ) eventType = ENTRYS.WILL;
	    if ( isCommand(commandOld, "INDI", "GRAD", "*") ) eventType = ENTRYS.GRADUATION;
	    if ( isCommand(commandOld, "INDI", "EMIG", "*") ) eventType = ENTRYS.EMIGRATION;
	    if ( isCommand(commandOld, "INDI", "EVEN", "*") ) eventType = ENTRYS.USEREVENT;
	    if ( isCommand(commandOld, "INDI", "CREM", "*") ) eventType = ENTRYS.CREM;
	    if ( isCommand(commandOld, "INDI", "ADOP", "*") ) eventType = ENTRYS.ADOPTATION;
	    if ( isCommand(commandOld, "INDI", "CONF", "*") ) eventType = ENTRYS.CONFIRMATION;
	    if ( isCommand(commandOld, "INDI", "RETI", "*") ) eventType = ENTRYS.RETI;
	    if ( isCommand(commandOld, "INDI", "PROB", "*") ) eventType = ENTRYS.PROB;
	    if ( isCommand(commandOld, "INDI", "NATU", "*") ) eventType = ENTRYS.NATU;
	    if ( isCommand(commandOld, "INDI", "BLES", "*") ) eventType = ENTRYS.BLESSING;
	    
	    if ( isCommand(commandOld, "INDI", "OCCU", "*") ) eventType = ENTRYS.OCCUPATION;
	    if ( isCommand(commandOld, "INDI", "EDUC", "*") ) eventType = ENTRYS.EDUCATION;
	    if ( isCommand(commandOld, "INDI", "RESI", "*") ) eventType = ENTRYS.RESIDENCE;
	    if ( isCommand(commandOld, "INDI", "TITL", "*") ) eventType = ENTRYS.TITLE;
	    if ( isCommand(commandOld, "INDI", "NATI", "*") ) eventType = ENTRYS.NATIONALITY;
	    if ( isCommand(commandOld, "INDI", "PROP", "*") ) eventType = ENTRYS.PROPERTY;
	    if ( isCommand(commandOld, "INDI", "RELI", "*") ) eventType = ENTRYS.RELIGION;
	    if ( isCommand(commandOld, "INDI", "SSN", "*") )  eventType = ENTRYS.SOCIAL_SECURITY_NRO;
	    if ( isCommand(commandOld, "INDI", "CAST", "*") ) eventType = ENTRYS.CAST;
	    if ( isCommand(commandOld, "INDI", "DSCR", "*") ) eventType = ENTRYS.DSCR;
	    if ( isCommand(commandOld, "INDI", "IDNO", "*") ) eventType = ENTRYS.IDNO;
	    if ( isCommand(commandOld, "INDI", "NCHI", "*") ) eventType = ENTRYS.NCHI;
	    if ( isCommand(commandOld, "INDI", "NMR", "*") )  eventType = ENTRYS.NMR;
	    
	    if (eventType != ENTRYS.NA) {	    	
	        tmpEvent.type = ENTRYS.toInt(eventType);
	        tmpPd.entry.add(new Entry(tmpEvent));
	        used = true; 
	    }

	    tmpEvent.clear();
	}
	
	private void famcRecordEnded()
	{
	    // jos ennest��n ei ole famc.t�, tai jos ennest��n on joku sikas�k�-adoptio, kirjoitetaan yli
	    
	    if (tmpPd.pedi == PEDI_TYPE.ADOPTED) { tmpPd.famcSecondary = tmpFamc.clone(); } //*pedi
	    else tmpPd.famc = tmpFamc.clone();
	    tmpFamc.clear();
	}
	
	private void analyseNoteRecord()
	{
	    if ( isCommand(command, "**", "NOTE", "")) {
	        if (param.indexOf("@") != param.lastIndexOf("@")) tmpNote.tag = param;
	        else tmpNote.text = param;	
	        used = true; 
	    }
	    if ( isCommand(command, "**", "NOTE", "CONC")) { conc(tmpNote.text, param); used = true; }
	    if ( isCommand(command, "**", "NOTE", "CONT")) { cont(tmpNote.text, param); used = true; }
	    if ( isCommand(command, "**", "REFN", ""))     { tmpNote.refn = param; used = true; }
	    if ( isCommand(command, "**", "REFN", "TYPE")) { tmpNote.refnType = param; used = true; }
	    if ( isCommand(command, "**", "REFN", "RIN"))  { tmpNote.rin = param; used = true; }
	    if ( isCommand(command, "**", "CHAN", "DATE")) { date.copy(tmpNote.changed.date); used = true; }
	    if ( isCommand(command, "**", "DATE", "TIME")) { time.copy(tmpNote.changed.time); used = true; } // *** pitää varmaan yhdistää date ja time
	}
	
	private void analyseObjeRecord()
	{
	// -------------------- multimedia field --------------------------------
		
	    if ( isCommand(command, "**", "OBJE", ""))  { tmpMultimedia.tag = param; used = true; }
	    if ( isCommand(command, "**", "FORM", "*")) { tmpMultimedia.format = mmFormat(param); used = true; }
	    if ( isCommand(command, "**", "TITL", "*")) { tmpMultimedia.title = param; used = true; }
	    //if ( isCommand(command, "**", "OBJE", "*")) { tmpMultimedia.obje = param; }
	    if ( isCommand(command, "**", "FILE", "*")) { tmpMultimedia.file = param; used = true; }
	    if ( isCommand(command, "**", "REFN", "*")) { tmpMultimedia.refn = param; used = true; }
	    if ( isCommand(command, "**", "REFN", "TYPE")) { tmpMultimedia.refnType = param; used = true; }
	    if ( isCommand(command, "**", "RIN", "*")) { tmpMultimedia.rin = param; used = true; }
	    if ( isCommand(command, "**", "CHAN", "DATE") ) { time.copy(tmpMultimedia.changed.time); used = true; }
	    if ( isCommand(command, "**", "DATE", "TIME")) { date.copy(tmpMultimedia.changed.date); used = true; }
	}

	private void analyseAddrRecord()
	{
	// -------------------- address field --------------------------------
	    if ( isCommand(command, "**", "ADDR", "") ) { tmpAddress.line = param; used = true; }
	    if ( isCommand(command, "**", "ADDR", "CONT") ) { cont(tmpAddress.line, param); used = true; }
	    if ( isCommand(command, "**", "ADDR", "CONC") ) { conc(tmpAddress.line, param); used = true; }
	    if ( isCommand(command, "**", "ADR1", "*") ) { tmpAddress.line1 = param; used = true; }
	    if ( isCommand(command, "**", "ADR2", "*") ) { tmpAddress.line2 = param; used = true; }
	    if ( isCommand(command, "**", "CITY", "*") ) { tmpAddress.city = param; used = true; }
	    if ( isCommand(command, "**", "STAE", "*") ) { tmpAddress.state = param; used = true; }
	    if ( isCommand(command, "**", "POST", "*") ) { tmpAddress.post = param; used = true; }
	    if ( isCommand(command, "**", "CTRY", "*") ) { tmpAddress.country = param; used = true; }
	    if ( isCommand(command, "**", "EMAI", "*") ) { tmpAddress.email = param; used = true; }
	}
	
	private void analyseSourRecord()
	{
	// -------------------- source citation ------------------------------ s.34
	    // luetaan source muuttujaan ilman harmainta aavistusta, mihin se tulee k�ytt��n

	    if ( isCommand(command, "**", "SOUR", "" ) ) {
	        if (!tag.equals("")) tmpSource.tag = tag;
	        else tmpSource.text = param;
	        used = true;
	    }
	    //qDebug() << "tmpSourceKirjoius" << command[0] << command[1] << command[2] << param;
	    //if ( isCommand(command, "**", "SOUR", "CONT") ) cont(&tmpSource.dataText, param);
	    //if ( isCommand(command, "**", "SOUR", "CONC") ) conc(&tmpSource.dataText, param);
	    if ( isCommand(command, "**", "QUAY", "" ) ) { tmpSource.quay = param; used = true; }	    
	    if ( isCommand(command, "**", "PAGE", "CONC" ) ) { conc( tmpSource.page, param); used = true; }
	    if ( isCommand(command, "**", "PAGE", "CONT" ) ) { cont( tmpSource.page, param); used = true; }	    
	    if ( isCommand(command, "**", "EVEN", "") ) { tmpSource.event.type = param; used = true; }
	    if ( isCommand(command, "**", "EVEN", "ROLE") ) { tmpSource.event.role = familyEventRole(param); used = true; }
	    if ( isCommand(command, "**", "EVEN", "DATE") ) { date.copy(tmpSource.event.date); used = true; }
	    if ( isCommand(command, "**", "EVEN", "PLAC") ) { tmpSource.event.place = param; used = true; }
	    // multimedia luetaan omassa rakenteessaan
	    if ( isCommand(command, "**", "AUTH", "") ) { tmpSource.author = param; used = true; }
	    if ( isCommand(command, "**", "AUTH", "CONT") ) { cont(tmpSource.author, param); used = true; }
	    if ( isCommand(command, "**", "AUTH", "CONC") ) { conc(tmpSource.author, param); used = true; }
	    if ( isCommand(command, "**", "TITL", "") ) { tmpSource.title = param; used = true; }
	    if ( isCommand(command, "**", "TITL", "CONT") ) { cont(tmpSource.title, param); used = true; }
	    if ( isCommand(command, "**", "TITL", "CONC") ) { conc(tmpSource.title, param); used = true; }
	    // -- used by HEAD SOUR
	    if ( isCommand(command, "HEAD", "SOUR", "") ) { tmpSource.approvedSystemID = param; used = true; }
	    if ( isCommand(command, "**", "SOUR", "VERS") ) { tmpSource.version = param; used = true; }
	    if ( isCommand(command, "**", "SOUR", "NAME") ) { tmpSource.productName = param; used = true; }
	    if ( isCommand(command, "**", "CORP", "") ) { tmpSource.corporation = param; used = true; }
	    // address luetaan omassa funktiossa lukuunottamatta phone
	    if ( isCommand(command, "**", "PHON", "*") ) { tmpSource.phone = param; used = true; }
	    // multimedia sama juttu
	    if ( isCommand(command, "**", "DATA", "") ) { tmpSource.name = param; used = true; }
	    if ( isCommand(command, "**", "DATA", "AGNC") ) { tmpSource.event.agency = param; used = true; }
	    //if ( isCommand(command, "**", "DATA", "DATE") ) tmpSource.date.setDates(date);
	    if ( isCommand(command, "**", "TEXT", "") ) { tmpSource.text = param; used = true; }
	    if ( isCommand(command, "**", "TEXT", "CONT") ) { cont(tmpSource.text, param); used = true; }
	    if ( isCommand(command, "**", "TEXT", "CONC") ) { conc(tmpSource.text, param); used = true; }
	    if ( isCommand(command, "**", "DATA", "COPR") ) { tmpSource.copyright = param; used = true; }
	    //if ( isCommand(command, "**", "PHON", "") ) tmpSource.phone = param;
	    if ( isCommand(command, "**", "ABBR", "") ) { tmpSource.abbreviation = param; used = true; }
	    if ( isCommand(command, "**", "PUBL", "") ) { tmpSource.publication = param; used = true; }
	    if ( isCommand(command, "**", "PUBL", "CONT") ) { cont(tmpSource.publication, param); used = true; }
	    if ( isCommand(command, "**", "PUBL", "CONC") ) { conc(tmpSource.publication, param); used = true; }
	    if ( isCommand(command, "**", "REFN", "") ) { tmpSource.refn = param; used = true; }
	    if ( isCommand(command, "**", "REFN", "TYPE") ) { tmpSource.refnType = param; used = true; }
	    if ( isCommand(command, "**", "RIN", "") ) { tmpSource.rin = param; used = true; }
	    if ( isCommand(command, "**", "CHAN", "DATE") ) { date.copy(tmpSource.changed.date); used = true; }
	    if ( isCommand(command, "**", "DATE", "TIME")) { time.copy(tmpSource.changed.time); used = true; }
	    if ( isCommand(command, "**", "MEDI", "")) { used = true; } // ei käytetty
	}
	
	private void analyseEvent()
	{
	// ------------------- family or indi event -----------------------------------
	    if (level == 1) tmpEvent.attrText = param;
	    if ( isCommand(command, "INDI", "*", "")) { used = true; }
	    if ( isCommand(command, "FAM", "*", "")) { used = true; }
	    if ( isCommand(command, "*", "*", "DATE" ) ) { date.copy(tmpEvent.day); used = true; }
	    if ( isCommand(command, "*", "*", "PLAC" ) ) { tmpEvent.place = param; used = true; }	    
	    if ( isCommand(command, "*", "*", "AGE"  ) ) { tmpEvent.age = Integer.parseInt(param); used = true; }
	    if ( isCommand(command, "*", "*", "AGNC" ) ) { tmpEvent.agency = param; used = true; }
	    if ( isCommand(command, "*", "*", "CAUS" ) ) { tmpEvent.cause = param; used = true; }
	    if ( isCommand(command, "*", "*", "TYPE" ) ) { tmpEvent.attrType = param; }
	    if ( isCommand(command, "*", "*", "RESN" ) ) { 
	      if (param == "confidential") { tmpEvent.privacyPolicy = 1; used = true; }
	      if (param == "locked")  { tmpEvent.privacyPolicy = 2; used = true; }
	      if (param == "privacy") { tmpEvent.privacyPolicy = 3; used = true; }
	      
	    }
	    /*
	     * käyttäjän eventit, mitenkään tää pitäisi tehdä ***
	     
	    if ( isCommand(command, "*", "*", "TYPE" ) ) {
	        if (command[0].equals("INDI") && command[1].equals("EVEN") && !usrEvents.contains(param) ) usrEvents.append(param);
	        if (command[0].equals("FAM") && command[1].equals("EVEN") && !famEvents.contains(param) ) famEvents.append(param);
	        tmpEvent.attrType = param;
	        used = true;
	    }
	    */ 		
	    if ( isCommand(command, "**", "ADOP", "*") ) {
	        if (param.equals("Y") )      tmpPd.adoptedBy = ADOPTED_BY.YES;
	        if (param.equals("HUSB"))    tmpPd.adoptedBy = ADOPTED_BY.HUSB;
	        if (param.equals("WIFE"))    tmpPd.adoptedBy = ADOPTED_BY.WIFE;
	        if (param.equals("BOTH"))    tmpPd.adoptedBy = ADOPTED_BY.BOTH; 
	        used = true;
	    }
	    if ( param.equals("Y") || param.equals("y") ) tmpEvent.entryLineYes = true;
    
	}
	
	private void analyseRepoRecord()
	{
	    if ( isCommand(command, "*", "*", "NAME") ) { tmpRepo.name = param; used = true; }
	    if ( isCommand(command, "*", "*", "REFN") ) { tmpRepo.refn = param; used = true; }
	    if ( isCommand(command, "**", "REFN", "TYPE") ) { tmpRepo.refnType = param; used = true; }
	    if ( isCommand(command, "*", "*", "RIN") ) { tmpRepo.rin = param; used = true; }
	    if ( isCommand(command, "**", "CHAN", "DATE") ) { date.copy(tmpRepo.changed.date); used = true; }
	    if ( isCommand(command, "**", "DATE", "TIME")) { time.copy(tmpRepo.changed.time); used = true; }
	}

	private void analyseSubnRecord()
	{
	    if ( isCommand(command, "*", "*", "FAMF") ) { gd.subn.famf = param; used = true; }
	    if ( isCommand(command, "*", "*", "ANCE") ) { gd.subn.ance = param; used = true; }
	    if ( isCommand(command, "*", "*", "DESC") ) { gd.subn.desc = param; used = true; }
	    if ( isCommand(command, "*", "*", "ORDI") ) { gd.subn.ordi = param; used = true; }
	    if ( isCommand(command, "*", "*", "RIN") )  { gd.subn.rin = param; used = true; }
	}

	private void analyseHeadRecord()
	{		  
	    if ( isCommand(command, "HEAD", "FILE", "*") ) { gd.header.file = param; used = true; }
	    if ( isCommand(command, "HEAD", "CHAR", "") ) { gd.header.charsetName = param; used = true; }
	    if ( isCommand(command, "HEAD", "CHAR", "VERS") ) { gd.header.charsetVersion = param;  used = true; }
	    if ( isCommand(command, "HEAD", "DEST", "*") ) { gd.header.receivingSystemName = param; used = true; }
	    if ( isCommand(command, "HEAD", "DATE", "") ) { date.copy(gd.header.dateTransmission); used = true;  }
	    if ( isCommand(command, "HEAD", "DATE", "TIME") ) { time.copy(gd.header.time); used = true;  }
	    if ( isCommand(command, "HEAD", "SUBM", "*") ) { gd.header.submTag = param; used = true; }
	    if ( isCommand(command, "HEAD", "SUMN", "*") ) { gd.header.subnTag = param; used = true; }
	    if ( isCommand(command, "HEAD", "COPR", "*") ) { gd.header.copyright = param; used = true; }
	    if ( isCommand(command, "HEAD", "LANG", "*") ) { gd.header.language = param; used = true; }
	    if ( isCommand(command, "HEAD", "PLAC", "FORM") ) { gd.header.placForm = param;  used = true; }
	    if ( isCommand(command, "HEAD", "GEDC", "") ) { used = true; }
	    if ( isCommand(command, "HEAD", "GEDC", "VERS") ) { gd.header.gedComVersion = param; used = true; }
	    if ( isCommand(command, "HEAD", "GEDC", "FORM") ) { gd.header.gedComForm = param; used = true; }
	    // Note luetaan analyseNoteRecordissa, ja tallennetaan HEAD:n noteRecordEded:ss�
	    //if ( isCommand(command, "HEAD", "NOTE", "*") ) { gd.header.note = param; }
	    if ( isCommand(command, "HEAD", "NOTE", "CONT") ) { cont(gd.header.note, param); }
	    if ( isCommand(command, "HEAD", "NOTE", "CONC") ) { conc(gd.header.note, param); }
	}

	private void analyseSubmRecord()
	{
	    if ( isCommand(command, "SUBM", "NAME", "*")) { gd.sub.name = param;  used = true; }
	    if ( isCommand(command, "SUBM", "LANG", "*")) { gd.sub.language = param; used = true; }
	    if ( isCommand(command, "SUBM", "RFN", "*")) { gd.sub.rfn = param; used = true; }
	    if ( isCommand(command, "SUBM", "RIN", "*")) { gd.sub.rin = param; used = true; }
	    if ( isCommand(command, "SUBM", "CHAN", "DATE")) { date.copy(gd.sub.changed.date); used = true; }
	    if ( isCommand(command, "**", "DATE", "TIME")) { time.copy(gd.sub.changed.time); used = true; }

	    // multimedia & address in their own funcion
	}
	
	private void analyseIndiRecord()
	{		
	    gd.spacePd(idIndi);	 
	    if (isCommand(command, "INDI", "NAME", "") ) { tmpPd.nameGedCom = param; used = true; }	
	    if (isCommand(command, "INDI", "NAME", "SURN") ) { used = true; } // ei käytetä vielä
	    if (isCommand(command, "INDI", "NAME", "GIVN") ) { used = true; } // ei käytetä vielä
	    if (isCommand(command, "INDI", "NAME", "NICK") ) { used = true; } // ei käytetä vielä
	    if (isCommand(command, "INDI", "NAME", "SPFX") ) { used = true; } // ei käytetä vielä
	    if (isCommand(command, "INDI", "NAME", "CONT") ) { cont(tmpPd.nameGedCom, param) ; used = true; }
	    if (isCommand(command, "INDI", "NAME", "CONC") ) { conc(tmpPd.nameGedCom, param) ; used = true; }
	    if (isCommand(command, "INDI", "FAMS", "*")) { tmpParam = param; used = true; } // foo1

	    if (isCommand(command, "INDI", "FAMS", "NOTE")) { } // *!* mix t� on tyhj�
	    else if (isCommand(command, "INDI", "FAMS", "*") ) { tmpPd.famsTags.add(param); used = true; }
	    
	    if (isCommand(command, "INDI", "SUBM", "*")) { tmpPd.submTags.add(param); used = true; }
	    if (isCommand(command, "INDI", "SEX", "*")) {
	        if (param.equals("M") ) { tmpPd.sex = SEX.MALE; used = true; }
	        if (param.equals("F") ) { tmpPd.sex = SEX.FEMALE; used = true; }
	    }
	    if (isCommand(command, "INDI", "RFN", "")) { tmpPd.rfn = param; used = true; }
	    if (isCommand(command, "INDI", "AFN", "")) { tmpPd.afn = param; used = true; }
	    if (isCommand(command, "INDI", "REFN", "")) { tmpPd.refn = param; used = true; }
	    if (isCommand(command, "INDI", "REFN", "TYPE")) { tmpPd.refnType = param; used = true;}
	    if (isCommand(command, "INDI", "RIN", "")) { tmpPd.rin = param; used = true; }
	    if (isCommand(command, "INDI", "CHAN", "")) { used = true; }
	    if (isCommand(command, "INDI", "CHAN", "DATE")) { 
	      if ( command[3].equals("")) { date.copy(tmpPd.changed.date); used = true; }
	      if ( command[3].equals("TIME")) { time.copy(tmpPd.changed.time); used = true; }
	    }
	    if ( isCommand(command, "INDI", "RESN", "" ) ) {
	      if (param.equals("confidential")) { tmpPd.privacyPolicy = 1; used = true; }
	      if (param.equals("locked")) { tmpPd.privacyPolicy = 2; used = true; }
	      if (param.equals("privacy"))  { tmpPd.privacyPolicy = 3; used = true; }
	    }
	    if (isCommand(command, "INDI", "ANCI", "*") ) { tmpPd.anciTag = param; used = true; }
	    if (isCommand(command, "INDI", "DECI", "*") ) { tmpPd.desiTag = param; used = true; }

	    if (isIndiEvent(command) != 0 || command[1].equals("NAME" ) )
	    	if ( command[2].equals("") ) tmpParam = param;	    // *** tätä ei ole cpp versiossa mitä tämä siis tekee
	}
	
	private void analyseFamRecord()
	{
	    gd.spaceFd(idFam);		
	    if ( isCommand(command, "FAM", "HUSB", "*" ) ) { if (tmpFd.gedComTagHusb.equals("")) { tmpFd.gedComTagHusb = param; used = true; } }
	    if ( isCommand(command, "FAM", "WIFE", "*" ) ) { if (tmpFd.gedComTagWife.equals("")) { tmpFd.gedComTagWife = param; used = true; } }
//	    if ( isCommand(command, "FAM", "HUSB", "*" ) ) { tmpFd.gedComTagHusb = param; used = true; }
//	    if ( isCommand(command, "FAM", "WIFE", "*" ) ) { tmpFd.gedComTagWife = param; used = true; }
	    if ( isCommand(command, "FAM", "CHIL", "*" ) ) { tmpFd.gedComTagChilds.add(param); used = true; }
	    if ( isCommand(command, "FAM", "NCHI", "*" ) ) { tmpFd.nchi = Integer.parseInt(param); used = true; }
	    
	    if ( isCommand(command, "FAM", "CHAN", "" ) ) used = true;
	    if ( isCommand(command, "FAM", "CHAN", "DATE" ) ) {
	      if ( command[3].equals("") ) { date.copy(tmpFd.changed.date); used = true; }
	      if ( command[3].equals("TIME") ) { time.copy(tmpFd.changed.time); used = true; }
	    }
	    
	    if ( isCommand(command, "FAM", "SUBM", "*" ) ) { tmpFd.gedComTagSubmitters.add(param); used = true; }
	    if ( isCommand(command, "FAM", "REFN", "" ) ) { tmpFd.refn = param; used = true; }
	    if ( isCommand(command, "FAM", "REFN", "TYPE" ) ) { tmpFd.refnType = param; used = true; }
	    if ( isCommand(command, "FAM", "RIN", "*" ) ) { tmpFd.rin = param; used = true; }
	    
	    if ( isCommand(command, "FAM", "RESN", "" ) ) {
	      if (param.equals("confidential")) { tmpFd.privacyPolicy = 1; used = true; }
	      if (param.equals("locked")) { tmpFd.privacyPolicy = 2; used = true; }
	      if (param.equals("privacy")) { tmpFd.privacyPolicy = 3; used = true; }
    }
	}
	
	private void analyseFamcRecord()
	{
	    if (level == 1) { tmpFamc.tag = tag; used = true; }
	    if ( isCommand(command, "**", "FAMC", "PEDI") ) { tmpPd.pedi = pedi(param); used = true; }
	}
	
	/*
	private int isIndiAttribute(String cmd[]) 	// qenerReader only
	{
	    if (!cmd[0].equals("INDI")) return 0;
	    int i = indiAttribute.indexOf(cmd[1])+1;
	    if (i<0) i=0;
	    return i;	    
	}
	*/
	
	private int isIndiEvent(String cmd[]) 	// qenerReader only, mutta tämä voi olla hyvin tässä
	{	    
	    if (!cmd[0].equals("INDI")) return 0;
	    
	    int i = indiEvents.indexOf(cmd[1])+1;
	    if (i<0) i=0;
	    
	    if (i==0) i = indiAttribute.indexOf(cmd[1])+1;
	    if (i<0) i=0;
	    
	    return i;
	}
	
	private int isFamilyEvent(String cmd[]) 	// qenerReader only, mutta tämä voi olla hyvin tässä
	{
	    if (!cmd[0].equals("FAM")) return 0;
	    int i = familyEvents.indexOf(cmd[1])+1;
	    if (i<0) i=0;
	    return i;
	}

	/*
	private void analyseIndiAttribute() 	// qenerReader only
	{
	// ------------------- indi attribute event -----------------------------------
	if (level == 1) tmpAttribute.attrText = param;
	if ( isCommand(command, "*", "*", "DATE" ) ) { tmpAttribute.day = (Calendar) date.clone(); }
	if ( isCommand(command, "*", "*", "PLAC" ) ) { tmpAttribute.place = param; }
	if ( isCommand(command, "*", "*", "AGE"  ) ) { tmpAttribute.age = Integer.parseInt(param); }
	if ( isCommand(command, "*", "*", "AGNC" ) ) { tmpAttribute.agency = param; }
	if ( isCommand(command, "*", "*", "CAUS" ) ) { tmpAttribute.cause = param; }
	if ( isCommand(command, "*", "*", "TYPE" ) ) { tmpAttribute.attrType = param; }
	}
	*/

	public String trimFamilyName(String test) {
		String[] output;
		output = test.split("/");
		return output[1];
	}
	
	public boolean isFamilyName(String name) {
		if (name.startsWith("/") && name.endsWith("/")) return true;
		return false;
	}
	
	public boolean isFirstName(String name) {
		if (name.startsWith("/") && name.endsWith("/")) return false;
		return true;
	}
	
	public String lataa(GeneData gdParam) {
		gd = gdParam;
		
		Pattern p = Pattern.compile("\\s");
		
		int i;
		
		int linenro=0;
				
		command = new String[9];
		commandOld = new String[9];
		jeje = new String[9];
		tag = "";
		idIndi = 0;
		BufferedReader breader = null;
		String oldRow = "";
				
		try {
			URL url = new URL(gd.root + "qenes.ged");
	
			messageOut = "Loading : " + gd.root + "qenes.ged\n";

	        URLConnection yc = url.openConnection();
	        breader = new BufferedReader( new InputStreamReader( yc.getInputStream(), Charset.forName("ISO-8859-15") ));
	   
			String rivi = "";			
			for (i=0 ; i<9 ; i++) command[i] = "";
			
			while ( (rivi = breader.readLine()) != null ) { // kun ladataan levylt� ja webist�
				linenro++;
				if (!used) {
					messageOut += "Load : unprocessed line " + String.valueOf(linenro) + ":" + oldRow +"\n";

				}
				oldRow = rivi;
				cmd = "";
				param = "";
				jeje = p.split(rivi, 3);			
				// this is for ensuring that read wont crash, if there are empty lines in the end of the file. There should not be...
				if ( rivi.charAt(0) == ' ' ) break;  

				level = Integer.parseInt(jeje[0]);				
				if (level == 0) tag = "";
				
				for (i=0 ; i<9 ; i++) commandOld[i] = command[i];
				
				gedComLineAnalyze();
		        for (i=level ; i<9 ; i++) command[i] = "" ;      	        
	        
		        command[level] = cmd;
		        // t�ss� on qenelogyss� findGedcomTag, joka l�yt�� tagin. T�ss� toimii v�h�n eri tavalla, tulos sama
		        if (findGedComTag(jeje[0])) tag = jeje[0];
		        if (jeje.length>1) if (findGedComTag(jeje[1])) tag = jeje[1];
		        if (jeje.length>2) if (findGedComTag(jeje[2])) tag = jeje[2];
		        // ---
	
		        time.clear();
		        date.clear();
//		        Calendar time = Calendar.getInstance(); time.set(1, 1, 1);
//		        dateTime = Calendar.getInstance(); dateTime.set(1, 1, 1);		        
        
		        if (cmd.equals("DATE")) gedComDayAnalyze(param);
		        if (cmd.equals("TIME")) gedComTimeAnalyse(param);
		        
		        /* 
		         * Step 1. Check if a record has ended.
		         */
		        
		        if ( hasRecordEnded("NOTE") ) noteRecordEnded();
		        if ( hasRecordEnded("ADDR") ) addrRecordEnded();
		        if ( hasRecordEnded("OBJE") ) objeRecordEnded();
		        if ( hasRecordEnded("SOUR") ) sourRecordEnded();
		        if ( isFamilyEvent(commandOld) != 0 && level <= 1 ) famEventEnded();
		        if ( isIndiEvent(commandOld) != 0 && level <= 1 ) indiEventEnded();
		        if ( isCommand(commandOld, "INDI", "FAMC", "*") && ( level <= 1 ) ) famcRecordEnded();
		        
		        
		        /*  If the level is zero.
		        - check what was the previous record, if necessary, create a new table index and save a tmp into that index
		        - check what is the new record. Update counter, and save the tag info for later processing.
		        */
		        
		        //if (level==0 && !commandOld[0].equals(null) ) {
		        		        
		        if (level==0 ) {
	            	
	                // allaolevat tapaukset luetaan samalla tavalla "citation":ksi ja omaksi recordikseen.
	            	
		        	if (commandOld[0].equals("INDI")) {		        		
		        		gd.spacePd(idIndi);		        		
		        		gd.pd[idIndi] = new PersonData(tmpPd);		        		
		        		tmpPd.clear();		        		
		        	}
		        	
	            	if (commandOld[0].equals("FAM"))  { 
	            		gd.spaceFd(idFam);	            		
	            		gd.fd[idFam] = new FamilyData(tmpFd); 
	            		tmpFd.clear(); 
            		}
	            	
	            	if (commandOld[0].equals("SOUR")) { 	            		
	            		gd.spaceSource(idSour);	            			            		
	            		gd.source[idSour].data = new Source(tmpSource);	            			            		
	            		tmpSource.clear(); 
            		}
	                
	            	if (commandOld[0].equals("OBJE")) { 
	            		gd.spaceMultiMedia(idObje);
	            		gd.multiMedia[idObje].data = new MultiMedia(tmpMultimedia);
	            		tmpMultimedia.clear(); 
            		}
	                
	            	if (commandOld[0].equals("NOTE")) { 
	            		gd.spaceNote(idNote);
	            		gd.note[idNote].data = new Note(tmpNote);
	            		tmpNote.clear(); 
            		}
	                
	            	if (commandOld[0].equals("REPO")) { 
	            		gd.spaceRepo(idRepo);
	            		gd.repo[idRepo] = tmpRepo.clone();
	            		tmpRepo.clear(); 
            		}
          		
	                // subn record will be saved directly into database in "analysing methods"
	                // header, the same
	                // subm, the same
	                
	                if (cmd.equals("FAM" )) if ( !tag.equals("") ) { idFam++; famList.add(tag);     tmpFd.id = idFam; } 
	                if (cmd.equals("INDI")) if ( !tag.equals("") ) { idIndi++; indList.add(tag);	tmpPd.id = idIndi; }	                	
	                if (cmd.equals("OBJE")) if ( !tag.equals("") ) { idObje++; objeList.add(tag);   tmpMultimedia.id = idObje; }
	                if (cmd.equals("NOTE")) if ( !tag.equals("") ) { idNote++; noteList.add(tag);   tmpNote.id = idNote;}
	                if (cmd.equals("REPO")) if ( !tag.equals("") ) { idRepo++; repoList.add(tag);   tmpRepo.id = idRepo;}
	                if (cmd.equals("SUBM")) if ( !tag.equals("") ) { idSubm++; submList.add(tag); }
	                if (cmd.equals("SOUR")) if ( !tag.equals("") ) { idSour++; sourList.add(tag);   tmpSource.id = idSour; }
               
	                // since there are only one subn, header, subm, they are not here... why subm is *!*
	            } else {

/* Level is not zero.
   In this phase we know what record we're reading (that's in command[0...]) The methods below are responsible for
   scanning the command[] variables and saving the param variable in the right place.
   For example, if commands are 0 = INDI, 1= RFN, and param holds the RFN value, then save tmpPd.rfn = param.
   Saving will happen always in tmp variables, which will be saved in the table in later phases (the code above).

   The order makes a difference, its important eg to make sure that indi and fam are in the end so that all records that
   are inside those, has been saved. That's why we use else's.

   Subn, header and subm are not saved in a tmp record but directly into database. There's only 1 of each.
   */	            	
           	
    	            if ( isCommand(command, "**", "NOTE", "*") ) analyseNoteRecord(); else
    	            if ( isCommand(command, "**", "OBJE", "*") ) analyseObjeRecord(); else
    	            if ( isCommand(command, "**", "ADDR", "*") ) analyseAddrRecord(); else
    	            if ( isCommand(command, "**", "SOUR", "*" ) ) analyseSourRecord(); else
    	            //if ( isIndiAttribute(command) != 0 ) analyseIndiAttribute(); else
    	            	
    	            if ( isFamilyEvent(command) != 0 || isIndiEvent(command) != 0 ) analyseEvent(); else
    	            if ( isCommand(command, "**", "FAMC", "*") ) analyseFamcRecord(); else
    	            if ( isCommand(command, "**", "REPO", "*") ) analyseRepoRecord(); else
    	            if ( isCommand(command, "**", "SUBN", "*") ) analyseSubnRecord(); else
    	            if ( isCommand(command, "HEAD", "*", "*")) analyseHeadRecord(); else
    	            if ( isCommand(command, "SUBM", "*", "*")) analyseSubmRecord(); else
    	            if ( isCommand(command, "INDI", "*", "*")) analyseIndiRecord(); else
    	            if ( isCommand(command, "FAM", "*", "*")) analyseFamRecord();    	          
    	        }
    	    }
			
		    int y;

		    // *!* Not a masterpiece QRegexp... how to make regexp that last name is cap(4).
		    // Now the problem is I dont know how to catch e.g. name from /name/ ...

		    //QRegExp nameExp("([^/]\\S*[^/ ]|\\?)? ?([^ /]\\S*[^/ ])? ?([^ /]\\S*[^/ ])? ?(\\/(.*)?\\/)?");

		    // For all indi's, lets change gedcom ref-tag eg. FAMC @family@ to FAMC 12
		    // tag value @family@ is saved on gedComFamc, and famList has mapping 12 <=> @family@.
		    // For famS values, each family record can have more than one famS, the number will be acquired
		    // from lastUsedFamsN. Gedcom famS tags are saved in each fd in a separate map.

		    int x = 0;
		    
		    for (i=1 ; i<=idIndi ; i++) {
		        if (famList.indexOf(gd.pd[i].famc.tag) != -1) {		        	
		            gd.pd[i].famc.value = 1 + famList.indexOf(gd.pd[i].famc.tag);		            
		        }
		        		        
		        gd.pd[i].nameNote.id = 1 + noteList.indexOf(gd.pd[i].nameNote.tag);
		        
		        gd.pd[i].note.id = 1 + noteList.indexOf(gd.pd[i].note.tag);
		        
		        if (famList.indexOf(gd.pd[i].famcSecondary.tag) != -1) gd.pd[i].famcSecondary.value = 1 + famList.indexOf(gd.pd[i].famcSecondary.tag);
		        
		        for (y=0 ; y<gd.pd[i].famsTags.size() ; y++) {
		            if (famList.indexOf(gd.pd[i].famsTag(y)) != -1) 
		            	gd.pd[i].setFamS(1 + famList.indexOf(gd.pd[i].famsTag(y)));          
		        }
		        
		        // Muunnetaan henkil�n i eventtien source tiedot t�g arvosta => int, sourList:n avulla.
		        // esim pd[i] - syntym� - source = @source1234@.
		        // sourList kertoo, ett� @source1234@ on tallennettu paikkaan 12.
		        // Arvo 12 haetaan ja tallennetaan pd[i] - syntym� - sourceID, jota arvoa k�ytet��n my�hemmin.
		        
		        for (y=0 ; y<gd.pd[i].entry.size() ; y++) {
		            Entry entry = gd.pd[i].entry.get(y);	
		            System.out.println(gd.pd[i].entry.get(y).source.tag);
		            entry.source.id = 1 + sourList.indexOf(gd.pd[i].entry.get(y).source.tag);
		            entry.multiMedia.id = 1 + objeList.indexOf(gd.pd[i].entry.get(y).multiMedia.tag);
		            entry.multiMedia.note.id = 1 + noteList.indexOf(gd.pd[i].entry.get(y).multiMedia.tag);
		            entry.note.id = 1 + noteList.indexOf(gd.pd[i].entry.get(y).note.tag);
		            gd.pd[i].entry.set(y, entry);		            
		        }
		        
		        String[] names;// = new String[9];
		        String deli = " ";
		        
		        names = gd.pd[i].nameGedCom.split(deli);
		        
		        String nullStr = "";
		    	if (isFirstName(names[0])) gd.pd[i].name1st   = new String(names[0]);
		    	if (names.length>1) if (isFirstName(names[1])) gd.pd[i].name2nd   = new String(names[1]);
		    	if (names.length>2) if (isFirstName(names[2])) gd.pd[i].name3rd   = new String(names[2]);
		    	
		    	if (gd.pd[i].name2nd == null) gd.pd[i].name2nd = nullStr;
		    	if (gd.pd[i].name3rd == null) gd.pd[i].name3rd = nullStr;
		   
		    	if (isFamilyName(names[0])) gd.pd[i].nameFamily = new String(trimFamilyName(names[0]));
		    	if (names.length>1) if (isFamilyName(names[1])) gd.pd[i].nameFamily = new String(trimFamilyName(names[1]));
		    	if (names.length>2) if (isFamilyName(names[2])) gd.pd[i].nameFamily = new String(trimFamilyName(names[2]));
		    	if (names.length>3) if (isFamilyName(names[3])) gd.pd[i].nameFamily = new String(trimFamilyName(names[3]));
		    	
		        for (y=0 ; y< gd.pd[i].multiMedia.size() ; y++) { // !*! t�ss� muutettu <= muotoon <
		        	x = objeList.indexOf(gd.pd[i].multiMedia.get(y).tag);
		            if ( x!= -1) {
		                MultiMedia mm = gd.pd[i].multiMedia.get(y);
		                mm.id = 1 + x;
		                mm.note.id = 1 + noteList.indexOf(gd.pd[i].multiMedia.get(y).note.tag);
		                gd.pd[i].multiMedia.set(y, mm);
		            }
		            x = 0;
		        }

		        x = noteList.indexOf(gd.pd[i].note.tag); if ( x != -1 ) gd.fd[i].note.id = 1 + x;

		        x = sourList.indexOf(gd.pd[i].source.tag); gd.pd[i].source.id = 1 + x;

		        for (y=0 ; y< gd.pd[i].submTags.size() ; y++) { // muutettu <= muotoon <
		            x = submList.indexOf(gd.pd[i].submTags.get(y));
		            if ( x != -1 ) gd.pd[i].submitters.add(1 + x);
		        }		        
		        
		        gd.pd[i].source.multiMedia.id = 1 + objeList.indexOf(gd.pd[i].source.multiMedia.tag);
		        gd.pd[i].source.note.id = 1 + noteList.indexOf(gd.pd[i].source.note.tag);
		    }
		    
		    // as wife and husb values are saved as HUSB @individual1@ and WIFE @indivudual2@, shall each
		    // family record be converted so that mapping @individual@ <=> 13 will be used. The gedcom tags
		    // are saved on fd[].gedcomwife and the actual mapping on fd[].wife.
		    for (i=0 ; i<=idFam ; i++) {        
		    //    emit(loadDialog(idHead, idIndi, idFam, idObje, idNote, idRepo, idSour, idSubm, idIndi+i));
		        gd.fd[i].wife = 1 + indList.indexOf(gd.fd[i].gedComTagWife);
		        gd.fd[i].husb = 1 + indList.indexOf(gd.fd[i].gedComTagHusb);

		        int count = gd.fd[i].sourCitations.size();
		        for (y=0 ; y<count ; y++) {
		            Source s = gd.fd[i].sourCitations.get(y);
		            s.id = 1 + sourList.indexOf(s.tag);
		            gd.fd[i].sourCitations.set(y, s);
		        }
/*
		        count = gd.fd[i].gedComTag.submitters.count();
		        for (y=0 ; y<count ; y++) {
		            String submTag = gd.fd[i].gedComTag.submitters.value(y);
		            int sourceId = 1 + submList.indexOf(submTag);
		            gd.fd[i].subM.append(sourceId);		     
		        }
		        */
		               
		    // lis�� t�h�n
		    }
		    
		    gd.pdLastUsed = idIndi;
		    gd.fdLastUsed = idFam;
		    gd.sourLastUsed = idSour;

		    for (i=0 ; i<gd.pdLastUsed ; i++) countDescentants(i);
		    messageOut += "Complete\n";
		    return messageOut;

		} catch ( IOException ioe ) {
			System.out.println("expection");
			ioe.printStackTrace();
			messageOut += "Load error";
			return messageOut;		    
		} finally {			
			try {

				/*
				System.out.print(idIndi + "loaded. 2 first:");
				for (i = 1 ; i<10 ; i++)					
					System.out.println("person " + i + " " + gd.pd[i].nameFamily + " " + gd.pd[i].name1st + " " + gd.pd[i].name2nd + " " + gd.pd[i].name3rd + " " + gd.pd[i].entry.size());
					*/
				if (breader != null) breader.close();

			} catch(IOException ioe) {
				ioe.printStackTrace();
			    String ret = new String("Ladattu1"); return ret;
			}
		}

	}
	
	private void cont(String data1, String add) { data1 += "\n"; data1 += add; }

	private void conc(String data1, String add) { data1 += add; }
}
