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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import org.mexilego.gui.listeners.ActionPadListener;
import org.mexilego.operation.Control;

public class ActionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Control ctr = Control.getInstance();	
	
	public ActionPanel(){
		super(new GridBagLayout());		
		setBorder(BorderFactory.createLineBorder(BasePanel.borderColor,BasePanel.borderThickness,BasePanel.borderRounded));
		//setBackground(Color.BLACK);
		setOpaque(false);
		GridBagConstraints c = new GridBagConstraints();		
		c.insets = new Insets(2, 2, 2, 2);
		
		JLabel lblZoom = new JLabel("ZOOM");
		lblZoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblZoom.setVerticalAlignment(SwingConstants.CENTER);
		lblZoom.setForeground(Color.WHITE);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.05;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(lblZoom,c);		
		
		JSlider sldZoom = new JSlider(SwingConstants.VERTICAL, 1, 5, ctr.getZoom());
		sldZoom.setMajorTickSpacing(1);
		sldZoom.setMinorTickSpacing(1);
		sldZoom.setPaintTicks(true);
		sldZoom.addChangeListener(ActionPadListener.getInstance());
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.05;
		c.weighty = 0.9;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(sldZoom,c);
		
		JPanel jpnActionPAD = new ActionPAD();
		jpnActionPAD.setBackground(Color.BLACK);		
		jpnActionPAD.addMouseListener(ActionPadListener.getInstance());
		jpnActionPAD.addMouseMotionListener(ActionPadListener.getInstance());
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.9;
		c.weighty = 1.0;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight =2;
		add(jpnActionPAD,c);
		
		
	}	

}
