package ui;

import java.awt.Color;
import java.awt.Panel;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import data.GeneData;
import java.awt.Rectangle;
import java.awt.Label;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class EventsMore extends Panel {
	
	private static final long serialVersionUID = 1L;
	private GeneData gd;
	public qapplet parent;
	private int id;

	JPanel comp = null;
	Border border;
	private Label label = null;
	private Label label1 = null;
	private Label label2 = null;
	private Label label3 = null;
	private Label label4 = null;
	private AnImagePanel picture;
	
	class AnImagePanel  extends JPanel {
		private static final long serialVersionUID = 1L;
		BufferedImage image;

	    public AnImagePanel() {
	        image = null; //loadImage(path);	        
	    }

	    protected void paintComponent(Graphics g) {
	        g.drawImage(image, 0, 0, 150, 185, this);
	        
	    }

	    public BufferedImage loadImage(String path) {	        

	    	if (path == "") this.setVisible(false);
	    	else
	        try {
	            image = ImageIO.read(new File(path));
	            this.setVisible(true);
	        } catch(IOException e) {
	        	setVisible(false);
	            System.out.println("read error:" + e.getMessage() + " " + path) ;
	        }
	        
	        this.repaint();
	        	        
	        return image;
	    }
	}	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void setId(Integer idIn) {
		this.id = idIn;
		System.out.println("update " + id);
		if (id != 0) 
			if (gd.pd[id].multiMedia.size() > 0) {
				//System.out.println(gd.pd[id].multiMedia.get(0).file);
				this.picture.loadImage(gd.pd[id].multiMedia.get(0).file);
			}
	}

	/**
	 * This is the default constructor
	 */
	public EventsMore(GeneData g, qapplet qa) {
		super();
		initialize();
		this.gd = g;
		this.parent = qa;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		label4 = new Label();
		label4.setBounds(new Rectangle(180, 105, 75, 18));
		label4.setText("Label");
		label3 = new Label();
		label3.setBounds(new Rectangle(180, 83, 75, 18));
		label3.setText("Label");
		label2 = new Label();
		label2.setBounds(new Rectangle(180, 61, 75, 18));
		label2.setText("Label");
		label1 = new Label();
		label1.setBounds(new Rectangle(180, 38, 75, 18));
		label1.setText("Label");
		label = new Label();
		label.setBounds(new Rectangle(180, 14, 75, 18));
		label.setText("Address:");
		
		picture = new AnImagePanel();
		picture.setBounds(new Rectangle(5, 5, 150, 185));//315));
		picture.loadImage("C:\\minaite.jpg");
		this.setLayout(null);
		this.setSize(1100, 200);
		comp = new JPanel();
		this.add(comp, null);
		
		border = BorderFactory.createRaisedBevelBorder();		
		comp.setBorder(border);
		comp.setLayout(null);		
		comp.setLocation(0, 0);
		comp.setSize(1080, 200);		
		comp.add(label, null);
		comp.add(label1, null);
		comp.add(label2, null);
		comp.add(label3, null);
		comp.add(label4, null);
		comp.add(picture, null);
	}
	
	public void setColor(Color c) {		
		comp.setBackground(c);
	}

}  //  @jve:decl-index=0:visual-constraint="9,6"
