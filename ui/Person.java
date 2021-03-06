package ui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import data.GeneData;
import data.SEX;
import ui.PersonTab;

public class Person extends JDialog {
	private static final long serialVersionUID = 1L;
	private Label uiNameFamily = new Label();  //  @jve:decl-index=0:visual-constraint="24,16"
	private Label uiNames = new Label();  //  @jve:decl-index=0:visual-constraint="127,16"
	private Label uiGenre = new Label();  //  @jve:decl-index=0:visual-constraint="24,47"
	private Label uiNotes = new Label();
	private Button uiClose = null;  //  @jve:decl-index=0:visual-constraint="282,187"
	private JScrollPane jScrollPane = null;
	private JScrollPane jImageScroll = null;
	private JPanel imagePanel = new JPanel();
	private JTabbedPane uiTab = null;
	private GeneData gd;
	public List<Boolean> rowColor = new ArrayList<Boolean>();
	private Font fontti = new Font("Arial", Font.PLAIN, 30);
	
	class CloseButtonPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			close();
		}
	}
	
	private Button getUiClose() {
		if (uiClose == null) {
			uiClose = new Button();
			uiClose.setLabel("Close");
			uiClose.addActionListener(new CloseButtonPressed());
		}
		return uiClose;
	}	
	
	public Person(GeneData g, int id) {
		this.gd = g;

		this.setSize(1024, 640);
		this.setLayout(new GridBagLayout());
		this.setTitle("Person");

		uiNameFamily.setFont(fontti);
		uiNames.setFont(fontti);
		System.out.println("<" + id);
		uiNameFamily.setText(gd.pd[id].nameFamily);
		uiNames.setText(gd.pd[id].name1st + " " + gd.pd[id].name2nd + " " + gd.pd[id].name3rd);
		uiNotes.setFont(gd.titleFont);
		uiNotes.setText(gd.pd[id].note.text);
		
		if (gd.pd[id].sex == SEX.FEMALE) this.uiGenre.setText("Female");
		if (gd.pd[id].sex == SEX.MALE) this.uiGenre.setText("Male");
		if (gd.pd[id].sex == SEX.UNKNOWN) this.uiGenre.setText("Unknown genre");
	
		uiTab = new JTabbedPane();		
		uiTab.addTab("Person", new PersonTab(gd, true, id));
		for (int f = 0 ; f<gd.pd[id].famSList.size() ; f++) {
			gd.pd[id].famSId = f;
			int spouse = gd.pd[id].getSpouse(gd);
			int fami = gd.pd[id].famSList.get(f);
			uiTab.addTab("Family with " + gd.pd[spouse].nameShort(), new PersonTab(gd, false, fami));
		}

		jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(uiTab);

		imagePanel.setLayout(new GridBagLayout());
		imagePanel.setMaximumSize(new Dimension(1200,200));
		imagePanel.setPreferredSize(new Dimension(this.getWidth(), 200));
		
		imagePanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		add(uiGenre,		new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		add(uiNameFamily, 	new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		add(uiNames, 		new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		add(uiNotes,		new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		add(jScrollPane, 	new GridBagConstraints(0, 2, 4, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(Box.createVerticalGlue(), new GridBagConstraints(0, 3, 4, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		add(imagePanel, 	new GridBagConstraints(0, 4, 4, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 2, 2)); 

		ImagePanel aip = new ImagePanel(gd, 200);
		if (gd.pd[id].multiMedia.size() > 0) aip.loadImage(gd.pd[id].multiMedia.get(0).file);
		else aip.noPicture();
 
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 2;
		c.ipady = 2;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.weighty = 1;
		
		imagePanel.add(aip, c);
		
		int eMM = 1;
		for (int e=0 ; e<gd.pd[id].entry.size() ; e++) {
			String mmFile = gd.pd[id].entry.get(e).multiMedia.file;
			if (!mmFile.isEmpty()) {
				ImagePanel eI = new ImagePanel(gd, 200);
				eI.loadImage(mmFile);
				c.gridx = eMM;
				imagePanel.add(eI, c);
				eMM++;
			}
		}
		
		
		c.gridx = eMM+1;
		c.weightx = 1.5;
		imagePanel.add(Box.createVerticalGlue(), c);

		add(getUiClose(), 	new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
		
		this.setVisible(true);
	}
	
	private void close() {
		this.setVisible(false);
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
