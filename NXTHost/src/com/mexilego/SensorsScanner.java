//**********************************************************************************
//*                                   MexiLego                                     *
//*                                                                                *
//*     Author:			Eduardo Sámano                                             *
//*     Version:		0.1                                                        *
//*     Last Update:    05/30/2013	                         			           *
//*     License:		MPL 2.0    http://www.mozilla.org/MPL/2.0/                 *
//*                                                                                *
//**********************************************************************************

package com.mexilego;

import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.Color;

public class SensorsScanner implements Runnable{
	TouchSensor			t1;
	TouchSensor			t2;
	ColorSensor			cs;
	UltrasonicSensor 	us;
	Host 				h;
	
	static int FRECUENCY = 250;
	
	public SensorsScanner(Host h){
		super();
		this.h = h;
		t1 = new TouchSensor(SensorPort.S1);
		t2 = new TouchSensor(SensorPort.S2);
		cs = new ColorSensor(SensorPort.S3);
		cs.setFloodlight(Color.WHITE);
		us = new UltrasonicSensor(SensorPort.S4);
		
	}

	@Override
	public void run() {
		int[] result = new int[4];
		while(true){
			try{
				result[0] = t1.isPressed()?1:0;
				result[1] = t2.isPressed()?1:0;
				result[2] = cs.getColor().getColor();				
				result[3] = us.getDistance();
				h.notifySensorReading(result);
				Thread.sleep(FRECUENCY);					
			}catch(Exception e){
				h.notifySensorReading(null);
			}
		}
	}

}
