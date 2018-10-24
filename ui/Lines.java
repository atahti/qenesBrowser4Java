package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Lines extends JPanel {

	private int yChild;
	private int yFather;
	private int yMother;
	private static final long serialVersionUID = 1L;
	private int child, father, mother;

    final static BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, new float[] {2.0f}, 0.0f);
    final static BasicStroke solid = new BasicStroke(1);

    public void update(int c, int f, int m) {
    	
    	child = c;
    	mother = m;
    	father = f;
    	this.repaint();
    }
    
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g.create();
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		int xMiddle = this.getWidth()/2;
		int xParent = this.getWidth();
		g2.setColor(Color.DARK_GRAY);
		
		if ( mother !=0 || father !=0 ) g2.setStroke(solid); else g2.setStroke(dashed);		
		g2.drawLine(0, yChild, xMiddle, yChild);
		
		if ( father !=0 ) g2.setStroke(solid); else g2.setStroke(dashed);
		g2.drawLine(xMiddle, yChild, xMiddle, yFather);
		g2.drawLine(xMiddle, yFather, xParent, yFather);
		
		if ( mother !=0 ) g2.setStroke(solid); else g2.setStroke(dashed);
		g2.drawLine(xMiddle, yChild, xMiddle, yMother);
		g2.drawLine(xMiddle, yMother, xParent, yMother);
		
		g2.dispose();
		
	}

	public Lines(int yC, int yF, int yM) {
		super();
		yChild = yC;
		yFather = yF;
		yMother = yM;
		
		child = 0;
		mother = 0;
		father = 0;
		
	}

}  //  @jve:decl-index=0:visual-constraint="9,6"

