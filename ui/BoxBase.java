package ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import ui.ButtonClickHelper;

import data.GeneData;

public class BoxBase extends JPanel {
	
	public Border borderUp;
	public Border borderPressed;
	public int id = 0;
	public GeneData gd;
	public Button uiCenter = null;	
	public Label uiName = null;
	
	private ButtonClickHelper bch;

	private static final long serialVersionUID = 1L;

	public BoxBase() {
		borderUp = BorderFactory.createRaisedBevelBorder();
		borderPressed = BorderFactory.createLoweredBevelBorder();

		setLayout(new GridBagLayout());
		this.setBorder(borderUp);

		if (this.id != 0) if (gd.pd[this.id].privacyPolicy != 0) this.setBackground(Color.RED);
		//uiName.setFont(gd.titleFont);
		uiName = new Label();
		uiName.setText("");
		//add(uiName,	new GridBagConstraints(0, 0, 10, 10, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1,1,1,1), 1, 1));
		//uiName.setMaximumSize(new Dimension(160,20));
		//uiName.setBackground(Color.BLACK);
	}
	
	public class Mouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			setBorder(borderPressed);
			bch = new ButtonClickHelper(gd, id);
			System.out.println("mouse pressed");
			bch.start();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
			if (bch.isAlive()) {
				System.out.println("is alive" + id);
				bch.interrupt();

				new Person(gd, id);

			} else {
				System.out.println("is not alive");
			}

			setBorder(borderUp);
		}
	}

	class ButtonPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {								
				gd.current = id;
				gd.parent.update();			
		}
	}	
}
