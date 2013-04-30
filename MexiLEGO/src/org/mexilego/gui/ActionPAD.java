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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.mexilego.operation.Control;

public class ActionPAD extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int margin = 10;
	private final int innerCircle = 20;
	private final int zoomFactor = 10;
	private Control ctr = Control.getInstance();
	private BufferedImage img = null;
	
	public static int x = 0;
	public static int y = 0;	
	public static boolean exited = true;


	public ActionPAD(){
		super();	
		setOpaque(false);
		ctr.setaPAD(this);
		File f = new File("resources/mindstorms1.png");
		try {
			img = ImageIO.read(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		
		Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
		int xRadio = getWidth() / 2;
		int yRadio = getHeight() / 2;
		
		g2.setStroke(new BasicStroke(3));
		
		int radio = (xRadio<yRadio?xRadio:yRadio)-margin;
		
		int zoomMargin = (5-ctr.getZoom()) * zoomFactor; 
		
		g2.drawImage(ctr.getImg(), xRadio - radio + zoomMargin , yRadio - radio + zoomMargin, (radio *2) - (zoomMargin * 2), radio * 2 - (zoomMargin * 2), null);
		
		g2.setColor(new Color(0,255,0));
		g2.drawOval(xRadio - radio, yRadio - radio , radio *2, radio * 2);
		
		g2.drawLine(xRadio, yRadio-radio - margin, xRadio, yRadio - innerCircle);
		g2.drawLine(xRadio, yRadio + innerCircle, xRadio, yRadio + radio + margin);		
		
		g2.drawLine(xRadio-radio - margin, yRadio, xRadio - innerCircle, yRadio);
		g2.drawLine(xRadio + innerCircle, yRadio, xRadio + radio + margin, yRadio);
		
		g2.drawImage(img, xRadio - radio + zoomMargin , yRadio - radio + zoomMargin, (radio *2) - (zoomMargin * 2), radio * 2 - (zoomMargin * 2), null);
		
		if(!exited){
			g2.setStroke(new BasicStroke(2));
			g2.setColor(new Color(255,255,0));
			g2.drawLine(xRadio, yRadio, xRadio + x, yRadio - y);
			
			Font font = new Font(g.getFont().getName(),Font.BOLD,16);
			g.setColor(new Color(255, 255, 0));
	    	g.setFont(font);
	        FontMetrics metrics = g.getFontMetrics(font);     
	        int  fontHeight  = metrics.getHeight();
	        g.drawString("Dist.: " + ctr.getRealMagnitude() + " cm", getWidth() -100, getHeight()-(fontHeight*2));
	        String direction=null;
	        if(ctr.getAngle() >= 0){
	        	direction = "R";
	        }else{
	        	direction = "L";
	        }
	        g.drawString("Ang.: " + Math.abs(ctr.getAngle()) + "° " + direction, getWidth() -100, getHeight()-fontHeight);	
		}
		
		
	}
	
	
}
