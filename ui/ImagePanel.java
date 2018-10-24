package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import data.GeneData;

public class ImagePanel  extends JPanel {
	private static final long serialVersionUID = 1L;
	BufferedImage image;
	private Border border;
	private GeneData gd;
	private int xyI;
	private float xyF;
	
	public ImagePanel(GeneData ge, int xyz) {
		xyI = xyz/2;
		xyF = xyz;
		gd = ge;	
        image = new BufferedImage ( xyz, xyz, BufferedImage.TYPE_INT_ARGB );;
    	border = BorderFactory.createEtchedBorder();
    	this.setBorder(border);
    }

	public void noPicture() {
		this.image.getGraphics().setColor(Color.green);
		this.image.getGraphics().fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
    protected void paintComponent(Graphics g) {
    	
    	float xZoom;
    	xZoom = image.getWidth() / xyF;
    	float yZoom;
    	yZoom = image.getHeight() / xyF;
    	
    	float zoom;
    	int dx, dy;

    	if (xZoom > yZoom) {
    		zoom = xZoom;
    		dx = 0;
    		dy = xyI - (int)(image.getHeight()/zoom/2);
    	}
    	else {
    		zoom = yZoom;
    		dy = 0;
    		dx = xyI - (int)(image.getWidth()/zoom/2);	    		
    	}
    	
        g.drawImage(image, dx, dy, (int)(image.getWidth() / zoom), (int)(image.getHeight() / zoom), this);
        
    }
    
	public BufferedImage loadImage(String path) {		
		String file = "";
		Pattern p2 = Pattern.compile("[\\w\\�\\�\\�\\+\\-\\&\\!\\%\\(\\)\\d]+\\.\\w+");
		Matcher matcher = p2.matcher(path);
		if (matcher.find()) {
			file = gd.root+matcher.group();
			System.out.println(file);
    
	    	if (file == "") this.setVisible(false);
	    	else try {
	        	this.setVisible(true);
				URL url = new URL(file);
		        image = ImageIO.read(url);
	        } catch(IOException e) {
	        	setVisible(false);
	            System.out.println("read error:" + e.getMessage());
	        }
	        this.repaint();	        
	        
		}
	return image;
	}
	
}
