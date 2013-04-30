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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.mexilego.gui.listeners.ConfigListener;
import org.mexilego.operation.Control;

public class ConfigPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Control ctr = Control.getInstance();	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ConfigPanel(){	
		super(new GridBagLayout());		
		setBorder(BorderFactory.createLineBorder(BasePanel.borderColor,BasePanel.borderThickness,BasePanel.borderRounded));
		setOpaque(false);
		setForeground(Color.WHITE);
		
		String[] missionOptions = { "CITY - 0 Delay", "EARTH - 500ms Delay", "MOON - 1.3s Delay", "MARS - 3m Delay"};
		String[] speedOptions    = {"VERY SLOW", "SLOW", "MEDIUM", "FAST", "VERY FAST"};
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2, 10, 2, 10);
		
		ConfigListener listener = new ConfigListener(); 
			
		c.weightx = 0.3;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;		
		JLabel lblMission = new JLabel("Mission:");
		lblMission.setForeground(Color.WHITE);
		add(lblMission,c);
		
		
		JComboBox cmbMissions = new JComboBox(missionOptions);
		cmbMissions.setSelectedIndex(ctr.getMission());
		cmbMissions.setMaximumSize(new Dimension(100, 20));
		cmbMissions.addActionListener(listener);
		cmbMissions.setName("cmbMissions");
		c.weightx = 0.7;
		c.weighty = 0.1;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(cmbMissions,c);
		
		c.weightx = 0.3;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		JLabel lblSpeed = new JLabel("Speed:");
		lblSpeed.setForeground(Color.WHITE);
		add(lblSpeed,c);
		
		JComboBox cmbSpeed = new JComboBox(speedOptions);
		cmbSpeed.setSelectedIndex(ctr.getSpeed());
		cmbSpeed.addActionListener(listener);
		cmbSpeed.setName("cmbSpeed");
		c.weightx = 0.7;
		c.weighty = 0.1;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(cmbSpeed,c);
		
		c.weightx = 0.3;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		JLabel lblTurnSpeed = new JLabel("Turn Speed:");
		lblTurnSpeed.setForeground(Color.WHITE);
		add(lblTurnSpeed,c);
		
		JComboBox cmbTSpeed = new JComboBox(speedOptions);
		cmbTSpeed.setSelectedIndex(ctr.getTurnSpeed());
		cmbTSpeed.addActionListener(listener);
		cmbTSpeed.setName("cmbTSpeed");
		c.weightx = 0.7;
		c.weighty = 0.1;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(cmbTSpeed,c);
		
		c.weightx = 0.3;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		JLabel lblActionSteps = new JLabel("Action Steps:");
		lblActionSteps.setForeground(Color.WHITE);
		add(lblActionSteps,c);
		
		JTextField txtActionSteps = new JTextField(((Integer)ctr.getActionSteps()).toString(),5);
		txtActionSteps.addActionListener(listener);
		txtActionSteps.addFocusListener(listener);
		txtActionSteps.setHorizontalAlignment(JTextField.CENTER);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.7;
		c.weighty = 0.1;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.WEST;		
		add(txtActionSteps,c);
		
		JButton btnAction = new JButton("Do It!");
		btnAction.setName("doIt");
		btnAction.addActionListener(listener);
		c.weightx = 0.3;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		add(btnAction,c);
		
		JButton btnBeep = new JButton("Beep");
		btnBeep.setName("beep");
		btnBeep.addActionListener(listener);
		c.weightx = 0.7;
		c.weighty = 0.1;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		add(btnBeep,c);	
		
		JPanel jpnRemainder = new JPanel();		
		c.weightx = 0.3;
		c.weighty = 0.8;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		jpnRemainder.setOpaque(false);
		add(jpnRemainder,c);
		
		
	}
	
}
