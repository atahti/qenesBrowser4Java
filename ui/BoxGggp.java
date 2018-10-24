package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

import data.ENTRYS;
import data.GeneData;
import data.SEX;

public class BoxGggp extends BoxBase {

	private static final long serialVersionUID = 1L;
	private JLabel uiText = new JLabel();
	
	void setId(Integer idIn) {
		id = idIn;	
		
		if ( gd.pd[id].sex == SEX.MALE ) this.setColor(gd.maleColor);
		if ( gd.pd[id].sex == SEX.FEMALE ) this.setColor(gd.femaleColor);
		if ( gd.pd[id].sex == SEX.UNKNOWN || id == 0 ) this.setColor(gd.unknownColor);

		if (id == 0) {
			uiName.setText("");
			uiText.setText("");
		} else {
			this.uiName.setText(gd.pd[id].nameFamily + " " + gd.pd[id].name1st);
			String output = "";
			if (gd.pd[id].iEventDateText(ENTRYS.BIRTH) != "") output = "*" + gd.pd[id].iEventDateText(ENTRYS.BIRTH);
			if (gd.pd[id].iEventDateText(ENTRYS.DEATH) != "") {
				if (output != "") output += " ";
				output += "+" + gd.pd[id].iEventDateText(ENTRYS.DEATH);
			}
			uiText.setText(output);	
		}
	}
	
	/**
	 * This is the default constructor
	 */
	public BoxGggp(GeneData g) {
		this.gd = g;

		setMinimumSize(new Dimension(160,10));//50
		
		uiName.setFont(gd.titleFont);
		uiText.setFont(gd.boxFont);
		
		add(uiName,	new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(uiText, new GridBagConstraints(0, 1, 1, 1, 10.0, 10.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		//add(Box.createVerticalGlue(),	new GridBagConstraints(0, 2, 1, 1, 10.0, 10.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		
		Mouse mouse = new Mouse();
		this.addMouseListener(mouse);
		this.uiName.addMouseListener(mouse);
		this.uiText.addMouseListener(mouse);
		this.uiText.setFont(g.boxFont);
	}

	public void setColor(Color c) {		
		setBackground(c);
		this.uiText.setBackground(c);
		this.uiName.setBackground(c);	
		
	}

}  //  @jve:decl-index=0:visual-constraint="9,6"
