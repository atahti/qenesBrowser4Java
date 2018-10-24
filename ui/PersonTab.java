package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.ScrollPane;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import data.Entry;
import data.GeneData;

public class PersonTab extends ScrollPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GeneData gd = null;;
	private JTable jTable = null;
	
	Vector<Object> columnNames = new Vector<Object>();
	Vector<Vector> tableData = new Vector<Vector>();

	private Vector<String> addEntry(Entry e) {
		Vector<String> row = new Vector<String>();
		Entry entry = e;
		row.addElement(entry.typeToString());
		row.addElement(entry.day.toString()); //	int type = gd.pd[indi].entry.get(i).type; row.addElement(gd.pd[id].iEventDateText(ENTRYS.fromInt(type)));			
		row.addElement(entry.place);
		row.addElement(entry.attrText);
		row.addElement(entry.note.text);
		row.addElement(entry.address.all());	
		return row;
	}

	private void addIndiEntrys(int indi) {
		for (int i = 0 ; i<gd.pd[indi].entry.size() ; i++ ) {
			tableData.addElement(addEntry(gd.pd[indi].entry.get(i)));
		}	
	}
	
	private void addFamEntrys(int fam) {
		for (int i = 0 ; i<gd.fd[fam].entry.size() ; i++ ) {
			tableData.addElement(addEntry(gd.fd[fam].entry.get(i)));
		}	
	}
	
	public class CustomTableCellRenderer extends DefaultTableCellRenderer{
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent (JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
	      
			Component cell = super.getTableCellRendererComponent( table, obj, isSelected, hasFocus, row, column);
			setBackground(Color.lightGray);
			return cell;
		}
	}
	
	private JTable getJTable() {
		if (jTable == null) {
			columnNames.add("c1");
			columnNames.add("c2");
			columnNames.add("c3");
			columnNames.add("c4");
			columnNames.add("c5");
			columnNames.add("c6");
			jTable = new JTable(tableData, columnNames);
			
	        TableColumn tcol;
	        tcol = jTable.getColumnModel().getColumn(0);
	        tcol.setCellRenderer(new CustomTableCellRenderer());
	        tcol = jTable.getColumnModel().getColumn(1);
	        tcol.setCellRenderer(new CustomTableCellRenderer());
	        tcol = jTable.getColumnModel().getColumn(2);
	        tcol.setCellRenderer(new CustomTableCellRenderer());
	        tcol = jTable.getColumnModel().getColumn(3);
	        tcol.setCellRenderer(new CustomTableCellRenderer());
	        tcol = jTable.getColumnModel().getColumn(4);
	        tcol.setCellRenderer(new CustomTableCellRenderer());
	        tcol = jTable.getColumnModel().getColumn(5);
	        tcol.setCellRenderer(new CustomTableCellRenderer());
		}
		return jTable;
	}
		
	public PersonTab(GeneData g, boolean indi, int x) {
		gd = g;
		this.add(this.getJTable());
		
		Vector<String> titleRow = new Vector<String>();
		titleRow.addElement("Event");
		titleRow.addElement("Date");
		titleRow.addElement("Place");
		titleRow.addElement("Value");
		titleRow.addElement("Note");
		titleRow.addElement("Address");
		tableData.addElement(titleRow);
		
		if (indi) this.addIndiEntrys(x); else this.addFamEntrys(x);	
	}
}
