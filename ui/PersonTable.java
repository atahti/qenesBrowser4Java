package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import data.ENTRYS;
import data.GeneData;
import data.SEX;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

public class PersonTable extends Panel {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private GeneData gd;
	private String title;
	private ArrayList<Integer> persons = new ArrayList<Integer>();  //  @jve:decl-index=0:
	
	Vector<Object> columnNames = new Vector<Object>();  //  @jve:decl-index=0:
	Vector<Vector> tableData = new Vector<Vector>();

	//Vector<Object> columnNames = new Vector<Object>();  //  @jve:decl-index=0:

	public class CustomTableCellRenderer extends DefaultTableCellRenderer{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent (JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
	      
			Component cell = super.getTableCellRendererComponent( table, obj, isSelected, hasFocus, row, column);
			if (isSelected) { cell.setBackground(Color.green); }
			else {
				int c = persons.get(row);
				if (gd.pd[c].sex == SEX.FEMALE) cell.setBackground(gd.femaleColor);
				if (gd.pd[c].sex == SEX.MALE) cell.setBackground(gd.maleColor);
				if (gd.pd[c].sex == SEX.UNKNOWN) cell.setBackground(Color.LIGHT_GRAY);
			}
		return cell;
		}
	}
	
	public class test implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//System.out.println("1");
			int c = persons.get(jTable.getSelectedRow());
			
			if (c != 0) {
				gd.current = c;
				jTable.clearSelection();
				gd.parent.update();
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//System.out.println("2");
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//System.out.println("3");
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//System.out.println("4");
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	
	
	
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}
	
	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			columnNames.add(this.title);
			columnNames.add("Birth");
			jTable = new JTable(tableData, columnNames);
	        jTable.addMouseListener(new test());
	        TableColumn tcol;
	        tcol = jTable.getColumnModel().getColumn(0);
	        tcol.setCellRenderer(new CustomTableCellRenderer());
	        tcol = jTable.getColumnModel().getColumn(1);
	        tcol.setCellRenderer(new CustomTableCellRenderer());
		}
		return jTable;
	}

	/**
	 * This is the default constructor
	 */
	public PersonTable(String t, GeneData g, ArrayList<Integer> per) {
		this.gd = g;
		this.persons = per;
		this.title = t;
	
		setMinimumSize(new Dimension(160,10));
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridx = 0;
		this.setLayout(new GridBagLayout());
		this.add(getJScrollPane(), gridBagConstraints);
	
	}

	public void update() {
		tableData.removeAllElements();
		int i;
		for (i = 0 ; i<persons.size() ; i++ ) {
			Vector<String> row = new Vector<String>();
			String name;
			name = gd.pd[persons.get(i)].name1st;
			if (gd.pd[persons.get(i)].name2nd != null ) name += " " + gd.pd[persons.get(i)].name2nd; 
			row.addElement(name);
			row.addElement(gd.pd[persons.get(i)].iEventDateText(ENTRYS.BIRTH));
			
			tableData.addElement(row);
		}
		jTable.updateUI();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */

}
