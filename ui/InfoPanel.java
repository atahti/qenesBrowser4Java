package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import data.GeneData;

public class InfoPanel extends JPanel {
	private GeneData gd;	
	private static final long serialVersionUID = -2390935689351348455L;
	private Border borderUp;
	private Label titlePersons = new Label("Persons :");
	private Label titleFamilies = new Label("Families:");
	private Label titleSources = new Label("Sources :");
	public InfoPanel(GeneData g) {
		gd = g;
		setLayout(new GridBagLayout());
		borderUp = BorderFactory.createLoweredBevelBorder();
		this.setBorder(borderUp);
		
		this.add(titlePersons, 	new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		this.add(titleFamilies,	new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		this.add(titleSources, 	new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		
		this.add(new Label(String.valueOf(gd.pd.length)), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		this.add(new Label(String.valueOf(gd.fd.length)), new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		this.add(new Label(String.valueOf(gd.source.length)), new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));

	}

}
