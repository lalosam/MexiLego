//**********************************************************************************
//*                                   MexiLego                                     *
//*                                                                                *
//*     Author:			Eduardo Sámano                                             *
//*     Version:		0.1                                                        *
//*     Last Update:    05/30/2013	                         			           *
//*     License:		MPL 2.0    http://www.mozilla.org/MPL/2.0/                 *
//*                                                                                *
//**********************************************************************************

package org.mexilego.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.mexilego.operation.Control;

public class SensorComponent extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	BufferedImage img = null;
	int type;
	int index;	
	
	public SensorComponent(int type, int index){
		super();
		this.type = type;
		this.index = index;
		setOpaque(false);
		switch (type) {
			case 1: // Logic
				//Nothing to do
				break;
			case 2: // Color
				//Nothing to do
				break;
			case 3: // UltraSonic
				File f = new File("resources/speaker.png");
				try {
					img = ImageIO.read(f);
				} catch (IOException e) {					
					e.printStackTrace();
				}
				break;	
			}
	}

	@Override
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        String text = null;
        Color backgroundColor = null;
        Font font = new Font(g.getFont().getName(),Font.BOLD,16);
        
        int xCenter = getWidth() /2;
        int yCenter = getHeight()/2;
        
        int radio=0;
        if(xCenter < yCenter){
        	radio = xCenter-2;
        }else{
        	radio = yCenter-2;
        }
        
        int value = Control.getInstance().getSensorValue(index);
        
        
        Ellipse2D.Double circle;
        
        switch (type) {
			case 1: // Logic
				if(value > 0){
					backgroundColor = new Color(0, 255, 0);
					text = "ON";
				}else{
					backgroundColor = new Color(255, 0, 0);
					text = "OFF";
				}								 
				break;
			case 2: // Color				
				switch (value) {				
				case 0: //RED
					backgroundColor = new Color(255, 0, 0);
					break;
				case 1: //GREEN
					backgroundColor = new Color(0, 255, 0);
					break;
				case 2: //BLUE
					backgroundColor = new Color(0, 0, 255);
					break;
				case 3: //YELLOW
					backgroundColor = new Color(255, 255, 0);
					break;
				case 4: //MAGENTA
					backgroundColor = new Color(255, 0, 255);
					break;
				case 5: //ORANGE
					backgroundColor = new Color(255, 165, 0);
					break;
				case 6: //WHITE
					backgroundColor = new Color(255, 255, 255);
					break;	
				case 7: //BLACK
					backgroundColor = new Color(0, 0, 0);
					break;
				case 8: //PINK
					backgroundColor = new Color(228, 0, 124);
					break;
				case 9: //GRAY
					backgroundColor = new Color(128,128,128);
					break;
				case 10: //LIGHT GRAY 
					backgroundColor = new Color(211, 211, 211);
					break;
				case 11:  //DARK GRAY
					backgroundColor = new Color(169, 169, 169);
					break;
				case 12: //CYAN
					backgroundColor = new Color(0, 255, 255);
					break;
				}
				
				break;
			case 3: // UltraSonic
				//g.drawImage(img, xCenter-radio+3, yCenter -radio +3 , radio * 2 -6, radio * 2 -6, this);				
				text = String.valueOf(value);
				backgroundColor = new Color(144, 113, 84);
				break;	
		} 
        
        
        if(backgroundColor != null){
        	g.setColor(backgroundColor);
        	circle = new Ellipse2D.Double(xCenter-radio+3, yCenter -radio +3 , radio * 2 -6, radio * 2 -6);
			g2.fill(circle);
        }
        
        
        if(text != null){
        	g.setColor(new Color(255, 255, 255));
        	g.setFont(font);
	        FontMetrics metrics = g.getFontMetrics(font);     
	        int  fontHeight  = metrics.getHeight();     
	        int  stringWidth = metrics.stringWidth(text);
	        g.drawString(text, xCenter-stringWidth/2, yCenter+fontHeight/2-3);
        }
        
        
     
        
        g.setColor(Color.WHITE);
        g.drawOval(xCenter-radio, yCenter -radio , radio * 2, radio * 2);
	}
	
	
	
	
	

}
