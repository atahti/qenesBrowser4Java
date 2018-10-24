package data;

import java.awt.Color;
import java.awt.Font;

import ui.qapplet;

public class GeneData {
	
	public PersonData[] pd = new PersonData[10];
	public FamilyData[] fd = new FamilyData[3];
	public NoteRecord[] note = new NoteRecord[5];
	public SourceRecord[] source =  new SourceRecord[3];
	public MultiMediaRecord[] multiMedia =  new MultiMediaRecord[2];
	public Repo[] repo =  new Repo[2];
	public Header header = new Header();
	public Submitter sub = new Submitter();
	public Subn subn = new Subn();
	public Integer current = 1;
	public int	pdLastUsed, fdLastUsed, sourLastUsed;
	public Integer rootId = 0;
	public String root;
	public int rootFams;
	public qapplet parent;
	
	public Color maleColor = new Color(199, 199, 255);
	public Color femaleColor = new Color(255, 199, 199);
	public Color unknownColor = new Color(190, 190, 190);
	
	public Font boxFont = new Font("DejaVu Sans", Font.PLAIN, 10);
	public Font titleFont = new Font("serif", Font.BOLD, 12);
		
	public GeneData() {
		int i;
		System.out.println("gnedata creat");
		for (i=0 ; i<pd.length ; i++) pd[i] = new PersonData();
		for (i=0 ; i<fd.length ; i++) fd[i] = new FamilyData();
		for (i=0 ; i<note.length ; i++) note[i] = new NoteRecord();
		for (i=0 ; i<source.length ; i++) source[i] = new SourceRecord();
		for (i=0 ; i<repo.length ; i++) repo[i] = new Repo();		
	}
	
	public void spacePd(int space) {
		if (space >= pd.length) {
			int newSize = space + 50;
			PersonData[] tmp = new PersonData[newSize];
			System.arraycopy(pd, 0, tmp, 0, pd.length);
			pd = tmp;			
		}
    }
	
	public void spaceFd(int space) {		
		if (space >= fd.length) {			
			int newSize = space + 20;
			FamilyData[] tmp = new FamilyData[newSize];
			System.arraycopy(fd, 0, tmp, 0, fd.length);
			fd = tmp;
		}		
	}
	
	public void spaceNote(int space) {
		if (space >= note.length) {
			int newSize = space + 20;
			NoteRecord[] tmp = new NoteRecord[newSize];
			System.arraycopy(note, 0, tmp, 0, note.length);
			note = tmp;
		}		
	}
	
	public void spaceRepo(int space) {
		if (space >= repo.length) {
			int newSize = space + 20;
			Repo[] tmp = new Repo[newSize];
			System.arraycopy(repo, 0, tmp, 0, repo.length);
			repo = tmp;
		}		
	}
	
	public void spaceSource(int space) {
		if (space >= source.length) {
			int newSize = space + 20;
			SourceRecord[] tmp = new SourceRecord[newSize];
			for (int x = 0 ; x<newSize ; x++) tmp[x] = new SourceRecord(); // t�m� tarvitaan t�ss� mutta ei muualla == ??
			System.arraycopy(source, 0, tmp, 0, source.length);			
			source = tmp;
		}		
	}
	
	public void spaceMultiMedia(int space) {
		if (space >= multiMedia.length) {
			int newSize = space + 200;
			MultiMediaRecord[] tmp = new MultiMediaRecord[newSize];
			System.arraycopy(multiMedia, 0, tmp, 0, multiMedia.length);
			multiMedia = tmp;
		}		
	}
	
    public String right(String text, int length)
    {
    	return text.substring(text.length() - length, text.length());    	
    }    
}
