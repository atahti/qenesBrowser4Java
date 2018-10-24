package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Rectangle;
import javax.swing.border.Border;
import data.ENTRYS;
import data.GeneData;
import data.SEX;

public class BoxGp extends BoxBase {
// tätä ei tarvita???
	private static final long serialVersionUID = 1L;
	private Label uiBirth = null;
	private Label uiDeath = null;

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
		
			output = "";
			if (gd.pd[id].iEventDateText(ENTRYS.DEATH) != "") {			
				output += gd.pd[id].iEventDateText(ENTRYS.DEATH);
				if (gd.pd[id].ievent(ENTRYS.DEATH).place != null) output += " " + gd.pd[id].ievent(ENTRYS.DEATH).place;
			
			} else output += "(death ? or alive)";
			uiDeath.setText(output);
		}
	}

	public BoxGp(GeneData g) {
		super();

		this.setMinimumSize(new Dimension(160,10));
			
		uiName.setFont(g.titleFont);
		
		uiBirth = new Label();
		uiBirth.setBounds(new Rectangle(5, 28, 150, 17));
		uiBirth.setText("");
		uiBirth.setFont(g.boxFont);
		uiDeath = new Label();
		uiDeath.setBounds(new Rectangle(5, 48, 150, 17));
		uiDeath.setText("");
		uiDeath.setFont(g.boxFont);
		
		add(uiBirth, null);
		add(uiDeath, null);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		uiName.addMouseListener(mouse);
		uiBirth.addMouseListener(mouse);
		uiDeath.addMouseListener(mouse);
		
		this.gd = g;
	}

	public void setColor(Color c) {		
		setBackground(c);
		this.uiBirth.setBackground(c);
		this.uiDeath.setBackground(c);
		this.uiName.setBackground(c);
	}

}  //  @jve:decl-index=0:visual-constraint="9,6"
