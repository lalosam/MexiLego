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
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.mexilego.operation.Control;

public class SensorsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public SensorsPanel(){
		super();
		TitledBorder b = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(BasePanel.borderColor,BasePanel.borderThickness,BasePanel.borderRounded),"Sensors");
		b.setTitleColor(Color.WHITE);
		setBorder(b);		
		setBackground(Color.BLACK);
		setOpaque(false);
		
		Control ctr = Control.getInstance();	
		
		GridLayout lyt = new GridLayout(1,4);
		setLayout(lyt);
		
		SensorComponent jpnSensor1 = new SensorComponent(1,0);		
		add(jpnSensor1);
		ctr.registerSensorComponent(jpnSensor1);
		
		SensorComponent jpnSensor2 = new SensorComponent(1,1);		
		add(jpnSensor2);
		ctr.registerSensorComponent(jpnSensor2);
		
		SensorComponent jpnSensor3 = new SensorComponent(2,2);		
		add(jpnSensor3);
		ctr.registerSensorComponent(jpnSensor3);
		
		SensorComponent jpnSensor4 = new SensorComponent(3,3);		
		add(jpnSensor4);
		ctr.registerSensorComponent(jpnSensor4);
	}
	
}
