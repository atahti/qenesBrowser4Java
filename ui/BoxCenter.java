package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import data.ENTRYS;
import data.GeneData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.awt.Button;
import javax.swing.border.Border;

import ui.BoxBase.Mouse;

import java.awt.Choice;
import java.awt.TextArea;

public class BoxCenter extends BoxBase {
	
	private ImagePanel uiPicture = null;
	private static final long serialVersionUID = 1L;
	private JLabel uiBirth = new JLabel();
	private JLabel uiDeath = new JLabel();
	private JLabel uiName = new JLabel();
	private JLabel uiOccupation = new JLabel();
	private JLabel uiEducation = new JLabel();
	private Button button = null;
	private Choice uiSpouseCombo = null;

	Vector<Object> columnNames = new Vector<Object>();  //  @jve:decl-index=0:
	
	Border border;
	private TextArea uiNotes = null;
	private Button showDescentants = null;
	
	class ActionShowDescentants implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ShowDescentants(gd, gd.current);
		}
	}
	
	class ShowSpousePressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {							
			if (id != 0) {
				gd.current = gd.pd[id].getSpouse(gd);
				gd.parent.update();
			}
		}
	}
	
	/**
	 * This method initializes button	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button uiSpouse() {
		if (button == null) {
			button = new Button();
			button.addActionListener(new ShowSpousePressed());
			button.setLabel("Go to spouse family");
			button.setFont(gd.boxFont);
		}
		return button;
	}

	/**
	 * This method initializes uiSpouseCombo	
	 * 	
	 * @return java.awt.Choice	
	 */

	class SpouseSelected implements ItemListener {
		public void actionPerformed(ActionEvent e) {			
			
		}

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			setFamsId();
		}
	}
	
	private Choice getUiSpouseCombo() {
		if (uiSpouseCombo == null) {
			uiSpouseCombo = new Choice();
			uiSpouseCombo.addItemListener(new SpouseSelected());
			uiSpouseCombo.setFont(gd.boxFont);
		}
		return uiSpouseCombo;
	}
	
	private ImagePanel getPanel() {
		if (uiPicture == null) {
			uiPicture = new ImagePanel(gd, 196);
			this.setSize(196, 196);
			this.setMinimumSize(new Dimension(196, 196));
			this.setMaximumSize(new Dimension(196, 196));
		}
		return uiPicture;
	}
	
	
	private TextArea getUiNotes() {
		if (uiNotes == null) {
			uiNotes = new TextArea("test", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);			
			uiNotes.setFont(gd.boxFont);
		}
		return uiNotes;
	}
	

	/**
	 * This method initializes showDescentants	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button getshowDescentants() {
		if (showDescentants == null) {
			showDescentants = new Button();
			showDescentants.setLabel("Show Descentants");
			showDescentants.addActionListener(new ActionShowDescentants());
			showDescentants.setFont(gd.boxFont);
		}
		return showDescentants;
	}

	void setId(Integer idIn) {
		id = idIn;	
		
		if (id == 0) {
			uiName.setText("");
			uiBirth.setText("");
			uiDeath.setText("");
			uiEducation.setText("");
			uiOccupation.setText("");
			
		} else {
			this.uiName.setText(gd.pd[id].nameFamily + " " + gd.pd[id].name1st);

			String output;
			output = "*";
			if (gd.pd[id].iEventDateText(ENTRYS.BIRTH) != "") {			
				output += gd.pd[id].iEventDateText(ENTRYS.BIRTH);
				if (gd.pd[id].ievent(ENTRYS.BIRTH).place != null) output += " " + gd.pd[id].ievent(ENTRYS.BIRTH).place;			
			} else output += "(birth ?)";
			uiBirth.setText(output);
		
			output = "\u271d";
			if (gd.pd[id].iEventDateText(ENTRYS.DEATH) != "") {			
				output += gd.pd[id].iEventDateText(ENTRYS.DEATH);
				if (gd.pd[id].ievent(ENTRYS.DEATH).place != null) output += " " + gd.pd[id].ievent(ENTRYS.DEATH).place;
			
			} else output += "(person alive / unknown death)";
			uiDeath.setText(output);
				
			output = "";
			output = gd.pd[id].ievent(ENTRYS.OCCUPATION).attrText + " ";
			if (gd.pd[id].ievent(ENTRYS.OCCUPATION).place != null) output += gd.pd[id].ievent(ENTRYS.OCCUPATION).place;
			else output = "(occupation ?)";
			uiOccupation.setText(output);
		
			output = "";
			output = gd.pd[id].ievent(ENTRYS.EDUCATION).attrText + " ";
			if (gd.pd[id].ievent(ENTRYS.EDUCATION).place != null) output += gd.pd[id].ievent(ENTRYS.EDUCATION).place;
			else output = "(education ?)";
			uiEducation.setText(output);
		}

		String output = "";
		// &#x2764;
		// &hearts
		output = "\u2665" + gd.fd[gd.pd[id].famS()].eventDateText(ENTRYS.MARRIAGE) + " " + gd.fd[gd.pd[id].famS()].fentry(ENTRYS.MARRIAGE).place;
		//if (!output.equals("")) uiMarriage.setText(output); else uiMarriage.setText("");
		
		if (!output.equals("")) uiSpouse().setLabel(output); else uiSpouse().setLabel("");
		
		int tmp = gd.pd[id].famSId;
		this.uiSpouseCombo.removeAll();

		for (int i = 0 ; i<gd.pd[id].famSList.size() ; i++ ) {
			gd.pd[id].famSId = i;
			int spouse = gd.pd[id].getSpouse(gd);
			String spouseName = gd.pd[spouse].name1st + " " + gd.pd[spouse].nameFamily;

			if (spouseName != null) this.uiSpouseCombo.addItem(spouseName);
		}	
		
		gd.pd[id].famSId = tmp;

		uiNotes.setText(gd.pd[id].note.text);

		if (gd.pd[id].multiMedia.size() > 0 ) this.uiPicture.loadImage(gd.pd[id].multiMedia.get(0).file);
		
		else this.uiPicture.loadImage("");

	}
	
	void setFamsId() {
		int tmp = this.uiSpouseCombo.getSelectedIndex();
		gd.pd[this.id].famSId = tmp;
		
	}

	public BoxCenter(GeneData gene) {		
		gd = gene;
		
		border = BorderFactory.createRaisedBevelBorder();		
		setBorder(border);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		uiName.addMouseListener(mouse);
	   
		setMinimumSize(new Dimension(196, 500)); // tarvitaan asettamaan boxin leveys
		
		uiName.setFont(gd.titleFont);
		uiBirth.setFont(gd.boxFont);
		uiDeath.setFont(gd.boxFont);
		uiOccupation.setFont(gd.boxFont);
		uiEducation.setFont(gd.boxFont);
	
		setLayout(new GridBagLayout());
		
		add(uiName, 			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(uiBirth,			new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		add(uiDeath,			new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		add(uiOccupation,		new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		add(uiEducation,		new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		add(uiSpouse(),			new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		add(getUiSpouseCombo(),	new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		add(getPanel(),			new GridBagConstraints(0, 7, 1, 1, 2.0, 2.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(getUiNotes(),		new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(getshowDescentants(),	new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
	}
	
	public void setColor(Color c) {	
		setBackground(c);
		uiBirth.setBackground(c);
		uiDeath.setBackground(c);
	}
}
