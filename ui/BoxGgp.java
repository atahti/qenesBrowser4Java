package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.border.Border;
import data.ENTRYS;
import data.GeneData;
import data.SEX;

public class BoxGgp extends BoxBase {

	private static final long serialVersionUID = 1L;
	private JLabel uiBirth = new JLabel();
	private JLabel uiDeath = new JLabel();

	Border border;

	void setId(Integer idIn) {
		id = idIn;	
		
		if ( gd.pd[id].sex == SEX.MALE ) this.setColor(gd.maleColor);
		if ( gd.pd[id].sex == SEX.FEMALE ) this.setColor(gd.femaleColor);
		if ( gd.pd[id].sex == SEX.UNKNOWN || id == 0 ) this.setColor(gd.unknownColor);
		
		if (id == 0) {
			uiName.setText("");
			uiBirth.setText("");
			uiDeath.setText("");
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
		}
	}

	public BoxGgp(GeneData g) {
		this.gd = g;
		
		this.setMinimumSize(new Dimension(160,10));//69
		uiName.setFont(gd.titleFont);		
		uiBirth.setFont(gd.boxFont);
		uiDeath.setFont(gd.boxFont);

		add(uiName,			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(uiBirth, 		new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(uiDeath,		new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(Box.createVerticalGlue(),	new GridBagConstraints(0, 3, 1, 1, 10.0, 10.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		uiName.addMouseListener(mouse);
		uiBirth.addMouseListener(mouse);
		uiDeath.addMouseListener(mouse);
	
	}

	public void setColor(Color c) {		
		setBackground(c);
		this.uiBirth.setBackground(c);
		this.uiDeath.setBackground(c);
		this.uiName.setBackground(c);
	}

}  //  @jve:decl-index=0:visual-constraint="9,6"
