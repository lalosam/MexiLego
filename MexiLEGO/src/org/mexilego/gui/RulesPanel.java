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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.mexilego.operation.Control;

public class RulesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Color textColor = new Color(255, 205, 0);
	BufferedImage img = null;
	GridBagConstraints c = null;
	
	
	private final boolean[] ruleDefaultState = {true, false, false, false, false, true, true};
	private final String[] colorSenseValues = {"RED", "GREEN", "BLUE", "YELLOW", "MAGENTA", "ORANGE", "WHITE", "BLACK", "PINK", "GRAY", "LIGHT_GRAY", "DARK_GRAY", "CYAN"};
	private final String[] ultraSonicActions = {"EMERGENCY STOP", "Do IT!", "BEEP", "RUN AWAY !!"};
	private final String[] colorSenseActions = {"STOP", "BEEP", "Do IT!"};
	private final String[] touchActions = {"EMERGENCY STOP", "Do IT!", "BEEP", "RUN AWAY !!"};
	
	private final int[] sensorTypes = {4, 4, 3, 3, 3, 1, 2};
	private Object[] sensorStates  = new Object[7];
	private Object[] sensorValues  = new Object[7];
	private Object[] sensorActions = new Object[7];
	
		
	public RulesPanel(){
		super(new GridBagLayout());		
		TitledBorder b = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(BasePanel.borderColor,
						BasePanel.borderThickness,BasePanel.borderRounded),"Mission Parameters");
		b.setTitleColor(Color.WHITE);
		setBorder(b);
		setOpaque(false);
		
		c = new GridBagConstraints();		
		c.insets = new Insets(2, 10, 2, 10);
		
		addSensorRuleState();
		
		addSensorLabel(0, "ULTRA SONIC");
		addSensorLabel(1, "ULTRA SONIC");
		addSensorLabel(2, "COLOR");
		addSensorLabel(3, "COLOR");
		addSensorLabel(4, "COLOR");
		addSensorLabel(5, "TOUCH 1");
		addSensorLabel(6, "TOUCH 2");
		
		addSensorCondition(0, "OBJECT AT DISTANCE <");
		addSensorCondition(1, "OBJECT AT DISTANCE <");
		addSensorCondition(2, "DETECT COLOR");
		addSensorCondition(3, "DETECT COLOR");
		addSensorCondition(4, "DETECT COLOR");
		addSensorCondition(5, "STATE");
		addSensorCondition(6, "STATE");
		
		addSensorValues(0, 3, null, "30");
		addSensorValues(1, 3, null, "0");
		addSensorValues(2, 2, colorSenseValues, "0");
		addSensorValues(3, 2, colorSenseValues, "0");
		addSensorValues(4, 2, colorSenseValues, "0");
		addSensorValues(5, 1, null, "ON");
		addSensorValues(6, 1, null, "ON");
		
		addSensorActions(0, ultraSonicActions);
		addSensorActions(1, ultraSonicActions);
		addSensorActions(2, colorSenseActions);
		addSensorActions(3, colorSenseActions);
		addSensorActions(4, colorSenseActions);
		addSensorActions(5, touchActions);
		addSensorActions(6, touchActions);
		
		JButton btnSendParameters = new JButton("SEND PARAMETERS");
		btnSendParameters.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Control.getInstance().sendParameters(sensorStates, sensorTypes, sensorValues, sensorActions);
			}
		});
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.8;
		c.weighty = 0.1;
		c.gridx = 5;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 2;	
		add(btnSendParameters,c);
		Control.getInstance().sendParameters(sensorStates, sensorTypes, sensorValues, sensorActions);
		
	}	
	
	private void addSensorRuleState(){
		for(int i=0; i < ruleDefaultState.length; i++){
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.1;
			c.weighty = 0.1;
			c.gridx = 0;
			c.gridy = i;
			c.gridwidth = 1;
			c.gridheight = 1;		
			JCheckBox jchStateSensor = new JCheckBox();
			jchStateSensor.setSelected(ruleDefaultState[i]);
			jchStateSensor.setName("state" + i);
			sensorStates[i] = jchStateSensor;
			add(jchStateSensor,c);
		}
	}
	
	private void addSensorLabel(int index, String label){
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridx = 1;
		c.gridy = index;
		c.gridwidth = 1;
		c.gridheight = 1;		
		JLabel lblSensor = new JLabel(label);
		lblSensor.setForeground(textColor);
		lblSensor.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSensor,c);
	}	
	
	private void addSensorCondition(int index, String noOptionsLabel){
		c.fill = GridBagConstraints.HORIZONTAL;		
			c.weightx = 0.1;
			c.weighty = 0.1;
			c.gridx = 2;
			c.gridy = index;
			c.gridwidth = 1;
			c.gridheight = 1;		
			JLabel lblSensor = new JLabel(noOptionsLabel);
			lblSensor.setForeground(textColor);
			lblSensor.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblSensor,c);				
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensorValues(int index, int type, String[] options, String value){
		final int gridx = 3;
		switch (type) {
			case 1:
				c.fill = GridBagConstraints.HORIZONTAL;		
				c.weightx = 0.1;
				c.weighty = 0.1;
				c.gridx = gridx;
				c.gridy = index;
				c.gridwidth = 1;
				c.gridheight = 1;		
				JLabel lblValue = new JLabel(value);
				lblValue.setHorizontalAlignment(SwingConstants.CENTER);
				lblValue.setForeground(textColor);
				sensorValues[index] = lblValue;
				add(lblValue,c);
				break;
			case 2:
				c.weightx = 0.1;
				c.weighty = 0.1;
				c.gridx = gridx;
				c.gridy = index;
				c.gridwidth = 1;
				c.gridheight = 1;
				JComboBox cmbValue = new JComboBox(options);
				cmbValue.setSelectedIndex(0);
				//cmbCondition.addActionListener(listener);
				cmbValue.setName("cmbValue" + index);
				sensorValues[index] = cmbValue;
				add(cmbValue,c);
				break;
			case 3:
				c.fill = GridBagConstraints.NONE;	
				c.weightx = 0.1;
				c.weighty = 0.1;
				c.gridx = gridx;
				c.gridy = index;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.anchor = GridBagConstraints.CENTER;
				JTextField txtValue = new JTextField(value, 5);	
				txtValue.setHorizontalAlignment(JTextField.CENTER);
				//cmbSpeed.addActionListener(listener);
				txtValue.setName("txtValue" + index);
				sensorValues[index] = txtValue;
				add(txtValue,c);
				break;		
		}					
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensorActions(int index, String[] actions){
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridx = 4;
		c.gridy = index;
		c.gridwidth = 1;
		c.gridheight = 1;
		JComboBox cmbActions = new JComboBox(actions);
		cmbActions.setSelectedIndex(0);
		//cmbCondition.addActionListener(listener);
		cmbActions.setName("cmbActions" + index);
		sensorActions[index] = cmbActions;
		add(cmbActions,c);
	}
	
	
}


