package ui;

import java.awt.Color;
import javax.swing.Box;
import data.ENTRYS;
import data.GeneData;
import data.SEX;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

public class BoxP extends BoxBase {

	private static final long serialVersionUID = 1L;
    private JLabel uiBirth = new JLabel();
	private JLabel uiDeath = new JLabel();
	private JLabel uiOccupation = new JLabel();
	private JLabel uiEducation = new JLabel();

	public BoxP(GeneData g) {
		this.setMinimumSize(new Dimension(160,10));//141 tän tarkoitus on pakottaa leveys riittäväksi
		
		uiName.setFont(g.titleFont);
		
		uiBirth.setFont(g.boxFont);
		uiDeath.setFont(g.boxFont);
		
		uiOccupation.setText("Occupation");
		uiOccupation.setFont(g.boxFont);
		
		uiEducation.setText("Education");
		uiEducation.setFont(g.boxFont);
		
		add(uiName,			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(uiBirth, 		new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(uiDeath,		new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(uiOccupation,	new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(uiEducation,	new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(Box.createVerticalGlue(),	new GridBagConstraints(0, 5, 1, 1, 10.0, 10.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
	
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		uiName.addMouseListener(mouse);
		uiBirth.addMouseListener(mouse);
		uiDeath.addMouseListener(mouse);
		uiOccupation.addMouseListener(mouse);
		uiEducation.addMouseListener(mouse);
				
		this.gd = g;
	}

	public void setColor(Color c) {		
		setBackground(c);
		uiName.setBackground(c);
		uiBirth.setBackground(c);
		uiDeath.setBackground(c);
		uiOccupation.setBackground(c);
		uiEducation.setBackground(c);
	}
	
	void setId(Integer idIn) {
		id = idIn;	
		
		if ( gd.pd[id].sex == SEX.MALE ) this.setColor(gd.maleColor);
		if ( gd.pd[id].sex == SEX.FEMALE ) this.setColor(gd.femaleColor);
		if ( gd.pd[id].sex == SEX.UNKNOWN || id == 0 ) this.setColor(gd.unknownColor);
		
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
		
			output = "+";
			if (gd.pd[id].iEventDateText(ENTRYS.DEATH) != "") {			
				output += gd.pd[id].iEventDateText(ENTRYS.DEATH);
				if (gd.pd[id].ievent(ENTRYS.DEATH).place != null) output += " " + gd.pd[id].ievent(ENTRYS.DEATH).place;
			
			} else output += "(death ? or alive)";
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
	}
	
}
