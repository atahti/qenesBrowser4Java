package data;

public class SourceRecord {
	// SourceRecord is a class for Gedcom's source_record, and Source is a class for holding source_records data
	// AND for individual source citations. SourceRecord has a counter for counting source_records, so its not
	// possible to combine SourceRecord and Source because use of citations would mess the record counter.
	public int id;
	//static int count;
	//static int lastUsed;
	public Source data;
	
	public SourceRecord() { 
		data = new Source();
		id = 0;
	}
}
