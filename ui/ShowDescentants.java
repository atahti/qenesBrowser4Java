package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import data.GeneData;

public class ShowDescentants extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GeneData gd;
	private JScrollPane jScrollPane;
	private JPanel jPanel;
	//private BoxGgggp box;
	
	private void addToGrid(int i, int row, int column) {

		if (gd.pd[i].descentants == 1) {
			BoxGgggp box = new BoxGgggp(gd);
			box.setId(i);
			box.setMinimumSize(new Dimension(150, 22));
			jPanel.add(box, new GridBagConstraints(column, row, 1, gd.pd[i].descentants, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 1));
		} else 	if ((gd.pd[i].descentants == 2) || (gd.pd[i].descentants == 3)) {
			BoxGggp box = new BoxGggp(gd);
			box.setId(i);
			jPanel.add(box, new GridBagConstraints(column, row, 1, gd.pd[i].descentants, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 1));
		} else if (gd.pd[i].descentants > 3) {
			BoxGgp box = new BoxGgp(gd);
			box.setId(i);
			jPanel.add(box, new GridBagConstraints(column, row, 1, gd.pd[i].descentants, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 1));
		} else 
		{
			JButton button  = new JButton(gd.pd[i].name1st);
			jPanel.add(button, new GridBagConstraints(column, row, 1, gd.pd[i].descentants, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 1));			
		}

		
		List<Integer> childs = new ArrayList<Integer>();
		gd.pd[i].childs(gd, childs, false);

		int alkuY = 0;
		for (int c=0 ; c<childs.size() ; c++) {
			addToGrid(childs.get(c), row+alkuY, column+1);
			alkuY += gd.pd[childs.get(c)].descentants;
		}
	}
	
    public void paintComponents(Graphics g)
    {
        //super.paintComponents(g);
        
    }
	
	public ShowDescentants(GeneData g, int id) {
		this.gd = g;
		this.setSize(1024, 640);
		this.setLayout(new GridBagLayout());
		
		jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		jPanel.setSize(1024, 640);
		jPanel.setMinimumSize(new Dimension(1024, 640));
		this.add(jPanel, new GridBagConstraints(0, 0, 1, 1, 10.0, 10.0, GridBagConstraints.BOTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 0, 0));
				
		jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jPanel);
		jScrollPane.setVerticalScrollBar(new JScrollBar());
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setSize(1024, 640);
		jScrollPane.setMinimumSize(new Dimension(1024, 640));
		jScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		this.add(jScrollPane);
		
		addToGrid(id, 0, 0);
		
		this.setVisible(true);
		
	}
}
