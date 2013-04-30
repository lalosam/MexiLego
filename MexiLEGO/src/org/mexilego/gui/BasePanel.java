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
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.mexilego.gui.listeners.ActionPadListener;

public class BasePanel extends JPanel{

	private static final long serialVersionUID = 1L;	
	//public static final Color borderColor = new Color(255,255,255);
	public static final Color borderColor = new Color(255,205,0);
	public static final int borderThickness = 3;
	public static final boolean borderRounded = true;
	BufferedImage img = null;
	
	public BasePanel(){
		super(new GridBagLayout());	
		
		File f = new File("resources/curiosity1.jpg");		
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		
		ActionPadListener.initiate();
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(2, 2, 2, 2);	
		
		JPanel jpnConfig = new ConfigPanel();
		c.weightx = 0.1;
		c.weighty = 0.4;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 5;
		add(jpnConfig,c);
				
		JPanel jpnSensors = new SensorsPanel();
		c.weightx = 0.1;
		c.weighty = 0.2;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(jpnSensors,c);
		
		JPanel jpnAction = new ActionPanel();
		c.weightx = 0.9;
		c.weighty = 0.6;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 6;
		
		add(jpnAction,c);
		
		JPanel jpnRules = new RulesPanel();
		c.weightx = 1.0;
		c.weighty = 0.3;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		c.gridheight = 3;
		add(jpnRules,c);
		
	}	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(img != null){
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}
	
}
