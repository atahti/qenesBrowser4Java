package ui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.Box;

import data.GeneData;
import data.SEX;

public class BoxGgggp extends BoxBase {

	private static final long serialVersionUID = 1L;
	
	void setId(Integer idIn) {
		id = idIn;	
		
		if ( gd.pd[id].sex == SEX.MALE ) this.setColor(gd.maleColor);
		if ( gd.pd[id].sex == SEX.FEMALE ) this.setColor(gd.femaleColor);
		if ( gd.pd[id].sex == SEX.UNKNOWN || id == 0 ) this.setColor(gd.unknownColor);
		
		if (id == 0) {
			uiName.setText("");
		} else {
			uiName.setText(gd.pd[id].nameFamily + " " + gd.pd[id].name1st);
		}
	}
	
	public BoxGgggp(GeneData g) {
		this.gd = g;
		setMinimumSize(new Dimension(160,20)); // tässä mimimikorkeus on tärkeä, se säätää muidenkin elementtien korkeutta
		uiName.setFont(gd.titleFont);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		uiName.addMouseListener(mouse);
		add(uiName,	new GridBagConstraints(0, 0, 10, 10, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(Box.createVerticalGlue(),	new GridBagConstraints(0, 2, 1, 1, 10.0, 10.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		
	}

	public void setColor(Color c) {		
		this.setBackground(c);
		uiName.setBackground(c);
	}

}  //  @jve:decl-index=0:visual-constraint="9,6"

