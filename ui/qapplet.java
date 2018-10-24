package ui;

import ui.BoxCenter;
import ui.Lines;
import ui.InfoPanel;
import java.applet.Applet;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.Box;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.javafx.tk.Toolkit;

import io.*;
import data.*;

public class qapplet extends Applet {
	final GeneData gd = new GeneData();
	private InfoPanel infoPanel = null;
	private static final long serialVersionUID = 1L;
	private BoxP boxFather 		= null;
	private BoxP boxMother 		= null;
	private BoxCenter boxCurrent 		= null;
	private PersonTable uiCousinList = null;
	private PersonTable uiSiblingList = null;
	private PersonTable uiChildList = null;
	private BoxP boxFF 		= null;
	private BoxP boxFM 		= null;
	private BoxP boxMF 		= null;
	private BoxP boxMM 		= null;
	
	private BoxGgp boxFFF 		= null;
	private BoxGgp boxFFM 		= null;
	private BoxGgp boxFMF 		= null;
	private BoxGgp boxFMM 		= null;
	private BoxGgp boxMFF 		= null;
	private BoxGgp boxMFM 		= null;
	private BoxGgp boxMMF 		= null;
	private BoxGgp boxMMM 		= null;
	
	private BoxGggp boxFFFF		= null;
	private BoxGggp boxFFFM		= null;
	private BoxGggp boxFFMF		= null;
	private BoxGggp boxFFMM		= null;
	private BoxGggp boxFMFF		= null;
	private BoxGggp boxFMFM		= null;
	private BoxGggp boxFMMF		= null;
	private BoxGggp boxFMMM		= null;
	private BoxGggp boxMFFF		= null;
	private BoxGggp boxMFFM		= null;
	private BoxGggp boxMFMF		= null;
	private BoxGggp boxMFMM		= null;
	private BoxGggp boxMMFF		= null;
	private BoxGggp boxMMFM		= null;
	private BoxGggp boxMMMF		= null;
	private BoxGggp boxMMMM		= null;
	
	private BoxGgggp boxFFFFF		= null;
	private BoxGgggp boxFFFFM		= null;
	private BoxGgggp boxFFFMF		= null;
	private BoxGgggp boxFFFMM		= null;
	private BoxGgggp boxFFMFF		= null;
	private BoxGgggp boxFFMFM		= null;
	private BoxGgggp boxFFMMF		= null;
	private BoxGgggp boxFFMMM		= null;
	private BoxGgggp boxFMFFF		= null;
	private BoxGgggp boxFMFFM		= null;
	private BoxGgggp boxFMFMF		= null;
	private BoxGgggp boxFMFMM		= null;
	private BoxGgggp boxFMMFF		= null;
	private BoxGgggp boxFMMFM		= null;
	private BoxGgggp boxFMMMF		= null;
	private BoxGgggp boxFMMMM		= null;
	private BoxGgggp boxMFFFF		= null;
	private BoxGgggp boxMFFFM		= null;
	private BoxGgggp boxMFFMF		= null;
	private BoxGgggp boxMFFMM		= null;
	private BoxGgggp boxMFMFF		= null;
	private BoxGgggp boxMFMFM		= null;
	private BoxGgggp boxMFMMF		= null;
	private BoxGgggp boxMFMMM		= null;
	private BoxGgggp boxMMFFF		= null;
	private BoxGgggp boxMMFFM		= null;
	private BoxGgggp boxMMFMF		= null;
	private BoxGgggp boxMMFMM		= null;
	private BoxGgggp boxMMMFF		= null;
	private BoxGgggp boxMMMFM		= null;
	private BoxGgggp boxMMMMF		= null;
	private BoxGgggp boxMMMMM		= null;
	
	private Lines 	lines1 	= null;
	
	private Lines 	lines2a 	= null;
	private Lines 	lines2b 	= null;
	
	private Lines 	lines3a 	= null;
	private Lines 	lines3b 	= null;
	private Lines 	lines3c 	= null;
	private Lines 	lines3d 	= null;
	
	private Lines 	lines4a 	= null;
	private Lines 	lines4b 	= null;
	private Lines 	lines4c 	= null;
	private Lines 	lines4d 	= null;
	private Lines 	lines4e 	= null;
	private Lines 	lines4f 	= null;
	private Lines 	lines4g 	= null;
	private Lines 	lines4h 	= null;
	private SearchAndHistory uiSah = null; // Search and hisotry
	private JTextArea jTextArea = null;
	private JScrollPane jScrollPane = null;
	public Label title = new Label();


	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();			
		}
		return jTextArea;
	}
	
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTextArea());			
		}
		return jScrollPane;
	}
	

  //  @jve:decl-index=0:
	ArrayList<Integer> childs = new ArrayList<Integer>();  //  @jve:decl-index=0:
	ArrayList<Integer> siblings = new ArrayList<Integer>();  //  @jve:decl-index=0:
	ArrayList<Integer> cousins = new ArrayList<Integer>();  //  @jve:decl-index=0:
	
	public void init() {
		final Loadfile kk = new Loadfile();
		gd.root = this.getCodeBase().toString();
		gd.parent = this;
		String msg = kk.lataa(gd);
		
		GridBagLayout grid = new GridBagLayout();

		Dimension dim = this.getMaximumSize();
		this.setSize(dim);
		
		//xxx
		//frame.setSize(dim.width, dim.height);
		
		//this.setSize(1440, 1024);
		this.setLayout(grid);
		this.setBackground(Color.lightGray);
	
		infoPanel	= new InfoPanel(gd);
		uiSah 		= new SearchAndHistory(gd);
		uiChildList = new PersonTable("Childs", gd, childs);
		boxCurrent 	= new BoxCenter(gd);
		boxFather 	= new BoxP(gd);
		boxMother 	= new BoxP(gd);		
		boxFF 		= new BoxP(gd);
		boxFM 		= new BoxP(gd);
		boxMF 		= new BoxP(gd);
		boxMM 		= new BoxP(gd);
		// Boxgp käyttämättä
		
	
		boxFFF 		= new BoxGgp(gd);
		boxFFM 		= new BoxGgp(gd);
		boxFMF 		= new BoxGgp(gd);
		boxFMM 		= new BoxGgp(gd);
		boxMFF 		= new BoxGgp(gd);
		boxMFM 		= new BoxGgp(gd);
		boxMMF 		= new BoxGgp(gd);
		boxMMM 		= new BoxGgp(gd);		
		
		boxFFFF		= new BoxGggp(gd);
		boxFFFM		= new BoxGggp(gd);
		boxFFMF		= new BoxGggp(gd);
		boxFFMM		= new BoxGggp(gd);
		boxFMFF		= new BoxGggp(gd);
		boxFMFM		= new BoxGggp(gd);
		boxFMMF		= new BoxGggp(gd);
		boxFMMM		= new BoxGggp(gd);
		boxMFFF		= new BoxGggp(gd);
		boxMFFM		= new BoxGggp(gd);
		boxMFMF		= new BoxGggp(gd);
		boxMFMM		= new BoxGggp(gd);
		boxMMFF		= new BoxGggp(gd);
		boxMMFM		= new BoxGggp(gd);
		boxMMMF		= new BoxGggp(gd);
		boxMMMM		= new BoxGggp(gd);
		
		boxFFFFF		= new BoxGgggp(gd);
		boxFFFFM		= new BoxGgggp(gd);
		boxFFFMF		= new BoxGgggp(gd);
		boxFFFMM		= new BoxGgggp(gd);
		boxFFMFF		= new BoxGgggp(gd);
		boxFFMFM		= new BoxGgggp(gd);
		boxFFMMF		= new BoxGgggp(gd);
		boxFFMMM		= new BoxGgggp(gd);
		boxFMFFF		= new BoxGgggp(gd);
		boxFMFFM		= new BoxGgggp(gd);
		boxFMFMF		= new BoxGgggp(gd);
		boxFMFMM		= new BoxGgggp(gd);
		boxFMMFF		= new BoxGgggp(gd);
		boxFMMFM		= new BoxGgggp(gd);
		boxFMMMF		= new BoxGgggp(gd);
		boxFMMMM		= new BoxGgggp(gd);
		boxMFFFF		= new BoxGgggp(gd);
		boxMFFFM		= new BoxGgggp(gd);
		boxMFFMF		= new BoxGgggp(gd);
		boxMFFMM		= new BoxGgggp(gd);
		boxMFMFF		= new BoxGgggp(gd);
		boxMFMFM		= new BoxGgggp(gd);
		boxMFMMF		= new BoxGgggp(gd);
		boxMFMMM		= new BoxGgggp(gd);
		boxMMFFF		= new BoxGgggp(gd);
		boxMMFFM		= new BoxGgggp(gd);
		boxMMFMF		= new BoxGgggp(gd);
		boxMMFMM		= new BoxGgggp(gd);
		boxMMMFF		= new BoxGgggp(gd);
		boxMMMFM		= new BoxGgggp(gd);
		boxMMMMF		= new BoxGgggp(gd);
		boxMMMMM		= new BoxGgggp(gd);
	
		lines1		= new Lines(230,70,410);
		lines2a 	= new Lines(70,30,130);
		lines2b 	= new Lines(70,30,130);
		
		lines3a 	= new Lines(30,30,80);
		lines3b 	= new Lines(15,30,85);
		lines3c 	= new Lines(110,30,100);
		lines3d 	= new Lines(90,30,90);
		
		lines4a 	= new Lines(25,15,40);
		lines4b 	= new Lines(25,15,40);
		lines4c 	= new Lines(25,15,40);
		lines4d 	= new Lines(25,15,40);
		lines4e 	= new Lines(25,15,40);
		lines4f 	= new Lines(25,15,40);
		lines4g 	= new Lines(25,15,40);
		lines4h 	= new Lines(25,15,40);
		
		uiCousinList = new PersonTable("Cousins", gd, cousins);
		uiSiblingList = new PersonTable("Siblings", gd, siblings);
								
		int tp = 2;
		int bm = 2;
		int lt = 2;
		int rt = 2;

		title.setFont(new Font("sansserif", Font.PLAIN, 16));
		title.setBackground(Color.green);
		
		this.add(infoPanel, 	new GridBagConstraints(0, 1, 1, 11, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(uiChildList, 	new GridBagConstraints(0, 12, 1, 8, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(uiSiblingList,	new GridBagConstraints(3, 12, 1, 8, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(uiCousinList,	new GridBagConstraints(5, 12, 1, 8, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(uiSah, 		new GridBagConstraints(0, 26, 4, 6, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(getJScrollPane(),new GridBagConstraints(1, 1, 3, 5, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		
		this.add(title,			new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		
		
		this.add(boxCurrent, 	new GridBagConstraints(1, 6, 1, 20, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		
		this.add(boxFather, 	new GridBagConstraints(3, 6, 1, 6, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMother, 	new GridBagConstraints(3, 20, 1, 6, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		
		
		this.add(boxFF,	 		new GridBagConstraints(5, 0, 1, 6, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFM,	 		new GridBagConstraints(5, 6, 1, 6, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMF,	 		new GridBagConstraints(5, 20, 1, 6, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMM,	 		new GridBagConstraints(5, 26, 1, 6, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		
		this.add(boxFFF, 		new GridBagConstraints(7, 0, 1, 4, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt),0 ,1));
		this.add(boxFFM, 		new GridBagConstraints(7, 4, 1, 4, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMF, 		new GridBagConstraints(7, 8, 1, 4, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMM, 		new GridBagConstraints(7, 12, 1, 4, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFF, 		new GridBagConstraints(7, 16, 1, 4, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFM, 		new GridBagConstraints(7, 20, 1, 4, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMF, 		new GridBagConstraints(7, 24, 1, 4, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMM, 		new GridBagConstraints(7, 28, 1, 4, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		
		this.add(boxFFFF, 		new GridBagConstraints(9, 0, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFFFM, 		new GridBagConstraints(9, 2, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFFMF, 		new GridBagConstraints(9, 4, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFFMM, 		new GridBagConstraints(9, 6, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMFF, 		new GridBagConstraints(9, 8, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMFM, 		new GridBagConstraints(9, 10, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMMF, 		new GridBagConstraints(9, 12, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMMM, 		new GridBagConstraints(9, 14, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFFF, 		new GridBagConstraints(9, 16, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFFM, 		new GridBagConstraints(9, 18, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFMF, 		new GridBagConstraints(9, 20, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFMM, 		new GridBagConstraints(9, 22, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMFF, 		new GridBagConstraints(9, 24, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMFM, 		new GridBagConstraints(9, 26, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMMF, 		new GridBagConstraints(9, 28, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMMM, 		new GridBagConstraints(9, 30, 1, 2, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
			
		this.add(boxFFFFF, 		new GridBagConstraints(11, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFFFFM, 		new GridBagConstraints(11, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFFFMF, 		new GridBagConstraints(11, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFFFMM, 		new GridBagConstraints(11, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFFMFF, 		new GridBagConstraints(11, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFFMFM, 		new GridBagConstraints(11, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFFMMF, 		new GridBagConstraints(11, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFFMMM, 		new GridBagConstraints(11, 7, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMFFF, 		new GridBagConstraints(11, 8, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMFFM, 		new GridBagConstraints(11, 9, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMFMF, 		new GridBagConstraints(11, 10, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMFMM, 		new GridBagConstraints(11, 11, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMMFF, 		new GridBagConstraints(11, 12, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMMFM, 		new GridBagConstraints(11, 13, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMMMF, 		new GridBagConstraints(11, 14, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxFMMMM, 		new GridBagConstraints(11, 15, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFFFF, 		new GridBagConstraints(11, 16, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFFFM, 		new GridBagConstraints(11, 17, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFFMF, 		new GridBagConstraints(11, 18, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFFMM, 		new GridBagConstraints(11, 19, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFMFF, 		new GridBagConstraints(11, 20, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFMFM, 		new GridBagConstraints(11, 21, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFMMF, 		new GridBagConstraints(11, 22, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMFMMM, 		new GridBagConstraints(11, 23, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMFFF, 		new GridBagConstraints(11, 24, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMFFM, 		new GridBagConstraints(11, 25, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMFMF, 		new GridBagConstraints(11, 26, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMFMM, 		new GridBagConstraints(11, 27, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMMFF, 		new GridBagConstraints(11, 28, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMMFM, 		new GridBagConstraints(11, 29, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMMMF, 		new GridBagConstraints(11, 30, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));
		this.add(boxMMMMM, 		new GridBagConstraints(11, 31, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 1));

		this.add(Box.createVerticalGlue(), new GridBagConstraints(0, 32, 1, 1, 0.0, 10.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		this.add(Box.createHorizontalGlue(), new GridBagConstraints(12, 0, 1, 1, 10.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		/*
		this.add(lines1,		new GridBagConstraints(2, 1, 1, 16, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines2a,		new GridBagConstraints(4, 1, 1, 6, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines2b,		new GridBagConstraints(4, 12, 1, 6, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines3a,		new GridBagConstraints(6, 1, 1, 4, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines3b,		new GridBagConstraints(6, 5, 1, 4, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines3c,		new GridBagConstraints(6, 9, 1, 4, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines3d,		new GridBagConstraints(6, 13, 1, 4, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines4a,		new GridBagConstraints(8, 1, 1, 2, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines4b,		new GridBagConstraints(8, 5, 1, 2, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines4c,		new GridBagConstraints(8, 9, 1, 2, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines4d,		new GridBagConstraints(8, 13, 1, 2, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines4e,		new GridBagConstraints(8, 17, 1, 2, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines4f,		new GridBagConstraints(8, 21, 1, 2, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines4g,		new GridBagConstraints(8, 25, 1, 2, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		this.add(lines4h,		new GridBagConstraints(8, 29, 1, 2, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
	*/
	
		//this.add(uiInfoPanel,	new GridBagConstraints(0, 17, 5, 2, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(tp, lt, bm, rt), 0, 0));
		/*
		GridBagLayout addInfoGrid = new GridBagLayout();
		
		
		uiInfoPanel.setLayout(addInfoGrid);		
		
		uiInfoPanel.add(uiSubmitterNameLabel, 				new GridBagConstraints(0, 18, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		uiInfoPanel.add(uiSubmitterAddressLineLabel, 		new GridBagConstraints(0, 19, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		uiInfoPanel.add(uiSubmitterAddressCityZipLabel,		new GridBagConstraints(0, 20, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		uiInfoPanel.add(uiSubmitterAddressCountryStateLabel,new GridBagConstraints(0, 21, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		uiInfoPanel.add(uiSubmitterAddressPhoneLabel, 		new GridBagConstraints(0, 22, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		
		uiInfoPanel.add(uiSubmitterName, 				new GridBagConstraints(2, 18, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		uiInfoPanel.add(uiSubmitterAddressLine, 		new GridBagConstraints(2, 19, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		uiInfoPanel.add(uiSubmitterAddressCityZip,		new GridBagConstraints(2, 20, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		uiInfoPanel.add(uiSubmitterAddressCountryState,	new GridBagConstraints(2, 21, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		uiInfoPanel.add(uiSubmitterAddressPhone, 		new GridBagConstraints(2, 22, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(tp, lt, bm, rt), 0, 0));
		
		uiSubmitterNameLabel.setFont(new Font("arial", Font.BOLD, 14));
		uiSubmitterAddressLineLabel.setFont(new Font("arial", Font.BOLD, 14));
		uiSubmitterAddressCityZipLabel.setFont(new Font("arial", Font.BOLD, 14));
		uiSubmitterAddressCountryStateLabel.setFont(new Font("arial", Font.BOLD, 14));
		uiSubmitterAddressPhoneLabel.setFont(new Font("arial", Font.BOLD, 14));
		
		uiSubmitterNameLabel.setText("Submitter name");		
		uiSubmitterAddressLineLabel.setText("Address");		
		uiSubmitterAddressCityZipLabel.setText("City/Zip");
		uiSubmitterAddressCountryStateLabel.setText("Country / State");
		uiSubmitterAddressPhoneLabel.setText("Address Phone");

		this.uiSubmitterName.setText(gd.sub.name);		
		this.uiSubmitterAddressLine.setText(gd.sub.address.line + " " + gd.sub.address.line1 + " " + gd.sub.address.line2);		
		this.uiSubmitterAddressCityZip.setText(gd.sub.address.post + " " + gd.sub.address.city);
		this.uiSubmitterAddressCountryState.setText(gd.sub.address.country + " " + gd.sub.address.state);
		this.uiSubmitterAddressPhone.setText("Address Phone");
		*/
		boxCurrent.setBackground(Color.yellow);
		
		System.out.println(msg);
		message(msg);
	}
	              
	public String personName(int i) {
		String out = new String(gd.pd[i].nameFamily + " " + gd.pd[i].name1st + " " + gd.pd[i].name2nd + " " + gd.pd[i].name3rd);
		return out;
	}
	
	private void message(String msg) {
		System.out.println("houhou " + msg);
		jTextArea.append(msg + "\n");
		JScrollBar vertical = jScrollPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );		
	}
	
	public void update() {		
		
		int c = gd.current;
		message(gd.pd[c].nameFamily + " " + gd.pd[c].name1st + " (" + String.valueOf(c) + ")");



		
		jTextArea.setAlignmentY(5);
		

	
		int m = gd.pd[gd.current].mother(gd);
		int f = gd.pd[gd.current].father(gd);
		
		int mm = gd.pd[m].mother(gd);
		int mf = gd.pd[m].father(gd);
		int fm = gd.pd[f].mother(gd);
		int ff = gd.pd[f].father(gd);
		
		int mmm = gd.pd[mm].mother(gd);
		int mmf = gd.pd[mm].father(gd);
		int mfm = gd.pd[mf].mother(gd);
		int mff = gd.pd[mf].father(gd);
		int fmm = gd.pd[fm].mother(gd);
		int fmf = gd.pd[fm].father(gd);
		int ffm = gd.pd[ff].mother(gd);
		int fff = gd.pd[ff].father(gd);
		
		int mmmm = gd.pd[mmm].mother(gd);
		int mmmf = gd.pd[mmm].father(gd);
		int mmfm = gd.pd[mmf].mother(gd);
		int mmff = gd.pd[mmf].father(gd);
		int mfmm = gd.pd[mfm].mother(gd);
		int mfmf = gd.pd[mfm].father(gd);
		int mffm = gd.pd[mff].mother(gd);
		int mfff = gd.pd[mff].father(gd);
		int fmmm = gd.pd[fmm].mother(gd);
		int fmmf = gd.pd[fmm].father(gd);
		int fmfm = gd.pd[fmf].mother(gd);
		int fmff = gd.pd[fmf].father(gd);
		int ffmm = gd.pd[ffm].mother(gd);
		int ffmf = gd.pd[ffm].father(gd);
		int fffm = gd.pd[fff].mother(gd);
		int ffff = gd.pd[fff].father(gd);
		
		int mmmmm = gd.pd[mmmm].mother(gd);
		int mmmmf = gd.pd[mmmm].father(gd);
		int mmmfm = gd.pd[mmmf].mother(gd);
		int mmmff = gd.pd[mmmf].father(gd);
		int mmfmm = gd.pd[mmfm].mother(gd);
		int mmfmf = gd.pd[mmfm].father(gd);
		int mmffm = gd.pd[mmff].mother(gd);
		int mmfff = gd.pd[mmff].father(gd);
		int mfmmm = gd.pd[mfmm].mother(gd);
		int mfmmf = gd.pd[mfmm].father(gd);
		int mfmfm = gd.pd[mfmf].mother(gd);
		int mfmff = gd.pd[mfmf].father(gd);
		int mffmm = gd.pd[mffm].mother(gd);
		int mffmf = gd.pd[mffm].father(gd);
		int mfffm = gd.pd[mfff].mother(gd);
		int mffff = gd.pd[mfff].father(gd);
		int fmmmm = gd.pd[fmmm].mother(gd);
		int fmmmf = gd.pd[fmmm].father(gd);
		int fmmfm = gd.pd[fmmf].mother(gd);
		int fmmff = gd.pd[fmmf].father(gd);
		int fmfmm = gd.pd[fmfm].mother(gd);
		int fmfmf = gd.pd[fmfm].father(gd);
		int fmffm = gd.pd[fmff].mother(gd);
		int fmfff = gd.pd[fmff].father(gd);
		int ffmmm = gd.pd[ffmm].mother(gd);
		int ffmmf = gd.pd[ffmm].father(gd);
		int ffmfm = gd.pd[ffmf].mother(gd);
		int ffmff = gd.pd[ffmf].father(gd);
		int fffmm = gd.pd[fffm].mother(gd);
		int fffmf = gd.pd[fffm].father(gd);
		int ffffm = gd.pd[ffff].mother(gd);
		int fffff = gd.pd[ffff].father(gd);
		
		
		//System.out.println("qapplet::update. Current = " + c + " mother = " + mother + " father = " + father + " famc = " + gd.pd[gd.current].famc.value );
		
		boxCurrent.setId(c);
		boxFather.setId(f);
		boxMother.setId(m);
		
		boxFF.setId(ff);
		boxFM.setId(fm);
		boxMF.setId(mf);
		boxMM.setId(mm);
		
		boxFFF.setId(fff);
		boxFFM.setId(ffm);
		boxFMF.setId(fmf);
		boxFMM.setId(fmm);
		boxMFF.setId(mff);
		boxMFM.setId(mfm);
		boxMMF.setId(mmf);
		boxMMM.setId(mmm);
		
		boxFFFF.setId(ffff);
		boxFFFM.setId(fffm);
		boxFFMF.setId(ffmf);
		boxFFMM.setId(ffmm);
		boxFMFF.setId(fmff);
		boxFMFM.setId(fmfm);
		boxFMMF.setId(fmmf);
		boxFMMM.setId(fmmm);
		boxMFFF.setId(mfff);
		boxMFFM.setId(mffm);
		boxMFMF.setId(mfmf);
		boxMFMM.setId(mfmm);
		boxMMFF.setId(mmff);
		boxMMFM.setId(mmfm);
		boxMMMF.setId(mmmf);
		boxMMMM.setId(mmmm);

		boxFFFFF.setId(fffff);
		boxFFFFM.setId(ffffm);
		boxFFFMF.setId(fffmf);
		boxFFFMM.setId(fffmm);
		boxFFMFF.setId(ffmff);
		boxFFMFM.setId(ffmfm);
		boxFFMMF.setId(ffmmf);
		boxFFMMM.setId(ffmmm);
		boxFMFFF.setId(fmfff);
		boxFMFFM.setId(fmffm);
		boxFMFMF.setId(fmfmf);
		boxFMFMM.setId(fmfmm);
		boxFMMFF.setId(fmmff);
		boxFMMFM.setId(fmmfm);
		boxFMMMF.setId(fmmmf);
		boxFMMMM.setId(fmmmm);
		boxMFFFF.setId(mffff);
		boxMFFFM.setId(mfffm);
		boxMFFMF.setId(mffmf);
		boxMFFMM.setId(mffmm);
		boxMFMFF.setId(mfmff);
		boxMFMFM.setId(mfmfm);
		boxMFMMF.setId(mfmmf);
		boxMFMMM.setId(mfmmm);
		boxMMFFF.setId(mmfff);
		boxMMFFM.setId(mmffm);
		boxMMFMF.setId(mmfmf);
		boxMMFMM.setId(mmfmm);
		boxMMMFF.setId(mmmff);
		boxMMMFM.setId(mmmfm);
		boxMMMMF.setId(mmmmf);
		boxMMMMM.setId(mmmmm);
		lines1.update(c, f, m);
		
		lines2a.update(f, ff, fm);
		lines2b.update(m, mf, mm);
		
		lines3a.update(ff, fff, ffm);
		lines3b.update(fm, fmf, fmm);
		lines3c.update(mf, mff, mfm);
		lines3d.update(mm, mmf, mmm);
		
		lines4a.update(fff, ffff, fffm);
		lines4b.update(ffm, ffmf, ffmm);
		lines4c.update(fmf, fmff, fmfm);
		lines4d.update(fmm, fmmf, fmmm);
		lines4e.update(mff, mfff, mffm);
		lines4f.update(mfm, mfmf, mfmm);
		lines4g.update(mmf, mmff, mmfm);
		lines4h.update(mmm, mmmf, mmmm);
		
		gd.pd[c].childs(gd, childs, false);
		gd.pd[c].siblings(gd, siblings, false);
		gd.pd[c].cousins(gd, cousins, false);
		
		uiChildList.update();
		uiSiblingList.update();
		uiCousinList.update();
		
		if (gd.pd[c].sex == SEX.FEMALE) this.boxCurrent.setColor(gd.femaleColor);
		if (gd.pd[c].sex == SEX.MALE) this.boxCurrent.setColor(gd.maleColor);
		if (gd.pd[c].sex == SEX.UNKNOWN) this.boxCurrent.setColor(Color.LIGHT_GRAY);

		uiSah.addPerson(c);
		
		title.setText(gd.header.note);
		
	}
	
	public void goToSpouse() {		
		int c = gd.pd[gd.current].getSpouse(gd);
		System.out.println("qapplet::goToSpouse. Spouse = " + c);
		if ( c != 0 ) {
			gd.current = c;
			update();
		}
	}
	
	public void start() {		
		gd.current = 1;
		this.update();				
	}
	
	public void showEvent(int id, int event)
	{
		//System.out.println("qapplet::showevent " + id + " " + event);
		//eventView.setEvent(id, event);
		
	}

}  //  @jve:decl-index=0:visual-constraint="22,54"
