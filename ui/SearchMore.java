package ui;

import java.awt.Choice;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data.ADOPTED_BY;
import data.FamilyData;
import data.GeneData;
import data.SEX;

public class SearchMore extends JDialog {

	class SearchPressed implements ActionListener {
		public void actionPerformed(ActionEvent ae) {							
			String address = uiAddress.getText();
			String place = uiPlace.getText();
			String value = uiValue.getText();
			String name = uiName.getText();
			String any = uiAny.getText();
			String note = uiNote.getText();
			
			if (!any.isEmpty()) {
				address = any;
				place = any;
				value = any;
				name = any;
				note = any;
			}
			
			if (!address.isEmpty()) uiText.append("Address : " + address + "\n");
			if (!place.isEmpty()) uiText.append("Place : " + place + "\n");
			if (!value.isEmpty()) uiText.append("Value : " + value + "\n");
			if (!name.isEmpty()) uiText.append("Name : " + name + "\n");
			if (!any.isEmpty()) uiText.append("Any field : " + any + "\n");
			if (!note.isEmpty()) uiText.append("Note : " + note + "\n");
			if (uiSingle.isSelected()) uiText.append("Singles\n");
			if (uiAdopted.isSelected()) uiText.append("Adopted\n");
			if (uiMarriedButNoChilds.isSelected()) uiText.append("Married, but no childs\n");
			if ( (uiGenre.getSelectedIndex() == 1) && !uiText.getText().contains("Females") ) uiText.append("Females\n");
			if ( (uiGenre.getSelectedIndex() == 2) && !uiText.getText().contains("Males") ) uiText.append("Males\n");
			if ( (uiGenre.getSelectedIndex() == 3) && !uiText.getText().contains("Genre Unknown") ) uiText.append("Genre Unknown\n");

			int i,e;
			boolean remove;

			for (i=0 ; i<gd.pdLastUsed ; i++) {
				
				if (results.contains(i)) {
					remove = true;
					
					if (uiSingle.isSelected()) {
						if (gd.pd[i].getSpouse(gd) == 0) remove = false; 
					}
					
					if (uiAdopted.isSelected()) {
						if (gd.pd[i].adoptedBy != ADOPTED_BY.NA) remove = false;
					}
					
					if (uiMarriedButNoChilds.isSelected()) {
						ArrayList<Integer> childs = new ArrayList<Integer>();
						gd.pd[i].childs(gd, childs, false);
						if ( ((childs.size() == 0) && (gd.pd[i].getSpouse(gd) != 0) ) ) remove = false;  
					}
					
					if (!note.isEmpty()) if (gd.pd[i].note.text.contains(note)) remove = false;
					//System.out.println(note + " " + i + " " + gd.pd[i].note.text + " " + gd.pd[i].note.text.contains(note) + remove);
										
					if (!name.equals("")) if ( gd.pd[i].name1st.contains(name) || gd.pd[i].name2nd.contains(name) || gd.pd[i].name3rd.contains(name) || gd.pd[i].nameFamily.contains(name)) remove = false;
						
					if ((uiGenre.getSelectedIndex() == 1) && (gd.pd[i].sex == SEX.FEMALE)) remove = false;
					if ((uiGenre.getSelectedIndex() == 2) && (gd.pd[i].sex == SEX.MALE)) remove = false;
					if ((uiGenre.getSelectedIndex() == 3) && (gd.pd[i].sex == SEX.UNKNOWN)) remove = false;
	
					//System.out.println(gd.pd[i].source.id);
					if (uiSource.getSelectedIndex() != 0) {
						if (uiSource.getSelectedIndex() == gd.pd[i].source.id) remove = false;
					}
					// jos entryjä ei ole, mutta joku entryyn liittyvä hakutermi on käytössä
					// silloin ei näistä entryistä ko hakutermiä löydy (koska entryjä ei siis ole)
					
					
					if ( !(gd.pd[i].entry.size() == 0 && ( !place.isEmpty() || !value.isEmpty() || !note.isEmpty() || !address.isEmpty())))
						for (e=0 ; e<gd.pd[i].entry.size() ; e++) {
							if (!address.isEmpty()) if ( gd.pd[i].entry.get(e).address.contains(address)) remove = false;
							if (!place.isEmpty()) if ( gd.pd[i].entry.get(e).place.contains(place)) remove = false;
							if (!value.isEmpty()) if ( gd.pd[i].entry.get(e).attrText.contains(value)) remove = false;
							if (!note.isEmpty()) if ( gd.pd[i].entry.get(e).note.text.contains(note)) remove = false;					
							if (uiSource.getSelectedIndex() != 0) {
								if (uiSource.getSelectedIndex() == gd.pd[i].entry.get(e).source.id) remove = false;
							}
						}
					
					// käydään läpi henkilön avioliittotiedostot
					for (int fi = 0 ; fi<gd.pd[i].famSList.size() ; fi++) {
						int f = gd.pd[i].famSList.get(fi);
						
						for (e=0 ; e<gd.fd[f].entry.size() ; e++) {
							if (!address.isEmpty()) if ( gd.fd[f].entry.get(e).address.contains(address)) remove = false;
							if (!place.isEmpty()) if ( gd.fd[f].entry.get(e).place.contains(place)) remove = false;
							if (!value.isEmpty()) if ( gd.fd[f].entry.get(e).attrText.contains(value)) remove = false;
							if (!note.isEmpty()) if ( gd.fd[f].entry.get(e).note.text.contains(note)) remove = false;
							if (uiSource.getSelectedIndex() != 0) {
								if (uiSource.getSelectedIndex() == gd.fd[f].entry.get(e).source.id) remove = false;
							}
						}
					}
					if (remove) removeResult(i);
				}
				

			}

			uiResults.update();
			uiClearAll.doClick();
		}
	}

	class clearResultsPressed implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			results.clear();
			for (int i=0 ; i<gd.pdLastUsed ; i++) results.add(i);
			
			uiResults.update();
			uiClearAll.doClick();
			uiText.setText("");
		}		
	}

	class exitPressed implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			close();
		}		
	}

	class clearAllPressed implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			uiAny.setText("");
			uiName.setText("");
			uiPlace.setText("");
			uiValue.setText("");
			uiNote.setText("");
			uiAddress.setText("");
			uiSingle.setSelected(false);
			uiAdopted.setSelected(false);
			uiMarriedButNoChilds.setSelected(false);
		}		
	}
	
	private static final long serialVersionUID = 1L;

	private GeneData gd;
	private PersonTable uiResults = null;
	private JTextField uiAny = new JTextField();
	private JTextField uiName = new JTextField();
	private JTextField uiPlace = new JTextField();
	private JTextField uiValue = new JTextField();
	private JTextField uiNote = new JTextField();
	private JTextField uiAddress = new JTextField();
	private JTextArea	uiText = new JTextArea();
	private Choice uiSource = new Choice();
	private Choice uiGenre = new Choice();

	private JButton		uiClearAll = new JButton("Clear All");
	private JButton		uiStartSearch = new JButton("Start Search");
	private JButton		uiExit = new JButton("EXIT");
	private JButton		uiClearResults = new JButton("Clear Results");
	
	private JCheckBox	uiSingle = new JCheckBox("Single");
	private JCheckBox	uiAdopted = new JCheckBox("Adopted");
	private JCheckBox	uiMarriedButNoChilds = new JCheckBox("Married, but no childs");
	
	ArrayList<Integer> results = new ArrayList<Integer>();	

	private void removeResult(int i) {
		if (results.contains(i)) results.remove(results.indexOf(i));
	}
	
	public SearchMore(GeneData g) {
		gd = g;

		this.setSize(1024, 640);
		uiResults = new PersonTable("Results", gd, results);
		this.setLayout(new GridBagLayout());
		
		for (int i=0 ; i<gd.pdLastUsed ; i++) {
//			gd.pd[i].search = true;
			results.add(i);
		}

		uiGenre.addItem("All");
		uiGenre.addItem("Female");
		uiGenre.addItem("Male");
		uiGenre.addItem("Unknown");
		
		for (int s=0 ; s<gd.sourLastUsed ; s++) uiSource.addItem(gd.source[s].data.title);
		
		this.uiStartSearch.addActionListener(new SearchPressed());

		this.uiClearAll.addActionListener(new clearAllPressed());
		this.uiClearResults.addActionListener(new clearResultsPressed());
		this.uiExit.addActionListener(new exitPressed());
		
		this.add(new Label("Any Field"),	new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(new Label("Name"), 		new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(new Label("Note"), 		new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(new Label("Place"), 		new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(new Label("Value"), 		new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(new Label("Address"), 		new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(new Label("Source"), 		new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(new Label("Genre"), 		new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiSingle,					new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiAdopted, 				new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiMarriedButNoChilds,		new GridBagConstraints(0, 10, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
			
		this.add(uiAny, 		new GridBagConstraints(1, 0, 1, 1, 10.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiName, 		new GridBagConstraints(1, 1, 1, 1, 10.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiNote, 		new GridBagConstraints(1, 2, 1, 1, 10.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiPlace, 		new GridBagConstraints(1, 3, 1, 1, 10.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiValue, 		new GridBagConstraints(1, 4, 1, 1, 10.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiAddress, 	new GridBagConstraints(1, 5, 1, 1, 10.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiSource, 		new GridBagConstraints(1, 6, 1, 1, 10.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiGenre, 		new GridBagConstraints(1, 7, 1, 1, 10.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		
		this.add(uiClearAll, 	new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiStartSearch,	new GridBagConstraints(2, 1, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiExit, 		new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiClearResults,new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(uiText,		new GridBagConstraints(0, 11, 3, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		this.add(Box.createVerticalGlue(), new GridBagConstraints(2, 12, 1, 1, 0.0, 10.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));

		this.add(uiResults, new GridBagConstraints(3, 0, 1, 13, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2));
		
		this.setVisible(true);
		
		uiResults.update();
	}
	
	private void close() {
		this.setVisible(false);
	}
}
