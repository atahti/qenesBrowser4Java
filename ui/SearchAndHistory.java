package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import data.ENTRYS;
import data.GeneData;
import data.SEX;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Dimension;

public class SearchAndHistory extends JPanel {

	private ArrayList<Integer> persons = new ArrayList<Integer>();  //  @jve:decl-index=0:
	
	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private TextField uiSearchText = null;
	private Button uiSearchButton = null;
	private Button uiClearHistoryButton = null;
	private Button uiSearchMoreButton = null;
	private GeneData gd;
	
	Vector<Object> columnNames = new Vector<Object>();  //  @jve:decl-index=0:
	Vector<Vector> tableData = new Vector<Vector>();
	
	class SearchPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {							
			System.out.println("search");
			search();			
		}
	}
	
	class ClearPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			persons.clear();
			update();			
		}
	}
	
	class SearchMorePressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new SearchMore(gd);
		}
	}
	
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	
	private class CustomTableCellRenderer extends DefaultTableCellRenderer{
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
	
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
//			jScrollPane.setBounds(new Rectangle(8, 8, 195, 163));
			jScrollPane.setBounds(new Rectangle(8, 8, 195, 70));
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
			//jTable = new JTable();
			this.jScrollPane.setMinimumSize(new Dimension(320, 20));

			columnNames.add("Browsed");
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
	
	
	
	private TextField getUiSearchText() {
		if (uiSearchText == null) {
			uiSearchText = new TextField();
			uiSearchText.setBounds(new Rectangle(63, 238, 135, 20));
			uiSearchText.setMinimumSize(new Dimension(160,20));
		}
		return uiSearchText;
	}

	/**
	 * This method initializes uiSearchButton	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button getUiSearchButton() {
		if (uiSearchButton == null) {
			uiSearchButton = new Button();
			uiSearchButton.setLabel("Search");
			uiSearchButton.addActionListener(new SearchPressed());
		}
		return uiSearchButton;
	}

	/**
	 * This method initializes uiClearHistoryButton	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button getUiClearHistoryButton() {
		if (uiClearHistoryButton == null) {
			uiClearHistoryButton = new Button();
			uiClearHistoryButton.setLabel("Clear List");
			//uiClearHistoryButton.setBounds(new Rectangle(110, 267, 88, 23));
			uiClearHistoryButton.addActionListener(new ClearPressed());
		}
		return uiClearHistoryButton;
	}

	private Button getUiSearchMoreButton() {
		if (uiSearchMoreButton == null) {
			uiSearchMoreButton = new Button();
			uiSearchMoreButton.setLabel("Search More");
			//uiClearHistoryButton.setBounds(new Rectangle(110, 267, 88, 23));
			uiSearchMoreButton.addActionListener(new SearchMorePressed());
		}
		return uiSearchMoreButton;
	}
	
	

	public SearchAndHistory(GeneData g) {
//		super();

		this.setLayout(new GridBagLayout());

		this.add(getJScrollPane(), 				new GridBagConstraints(0, 0, 2, 4, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		this.add(getUiSearchText(), 			new GridBagConstraints(3, 0, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		this.add(getUiSearchButton(), 			new GridBagConstraints(3, 1, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		this.add(getUiClearHistoryButton(), 	new GridBagConstraints(3, 2, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		this.add(getUiSearchMoreButton(), 		new GridBagConstraints(3, 3, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 0));
		
		this.gd = g;
	}
	
	public void addPerson(int person) {
		for (int i = 0 ; i<persons.size() ; i++) {
			if (persons.get(i) == person) {
				persons.remove(i);
				if (i>0) i--;
			}
		}
		persons.add(0, person);
		System.out.println("add " + person);
		update();
	}
	
	public void update() {
	
		tableData.removeAllElements();
		int i;
		for (i = 0 ; i<persons.size() ; i++ ) {
			Vector<String> row = new Vector<String>();
			row.addElement(gd.pd[persons.get(i)].nameFamily + " " + gd.pd[persons.get(i)].name1st);
			row.addElement(gd.pd[persons.get(i)].iEventDateText(ENTRYS.BIRTH));
			
			tableData.addElement(row);
		}
		jTable.updateUI();
	}

	private void search() {
		String string = this.uiSearchText.getText();
		boolean family = true;//this.uiNameFamily.isSelected();
		boolean name = true;//this.uiNameFirst.isSelected();
		boolean notes = true;//this.uiNotes.isSelected();
		
		for (int i = 1 ; i<gd.pdLastUsed ; i++ ) {
			if (family && gd.pd[i].nameFamily.contains(string)) addPerson(i);
			if (name && (
					   gd.pd[i].name1st.contains(string)
					|| gd.pd[i].name2nd.contains(string)
					|| gd.pd[i].name3rd.contains(string) ) ) addPerson(i);
			if (notes) {
				if (gd.pd[i].note.text.contains(string)) addPerson(i);
				for (int j=0 ; j<gd.pd[i].entry.size() ; j++ ) if (gd.pd[i].entry.get(j).note.text.contains(string)) addPerson(i);
				
			}			
		}		
	}
} 