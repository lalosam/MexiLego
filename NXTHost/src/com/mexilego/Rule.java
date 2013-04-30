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

//import lejos.nxt.LCD;

public class Rule {
	int sensor;
	int value;
	int action;
	
	boolean fired;
	
	
	public Rule(int sensor, int value, int action) {
		super();
		this.sensor = sensor;
		this.value = value;
		switch (sensor) {
		case 1:
		case 2:
			this.action = action;		
			break;
		case 3:
			this.action = action + 4;
			break;
		case 4:
			this.action = action + 7;
			break;		
		}		
	}
	
	public int check(int s1, int s2, int s3, int s4){
		int actionToExecute=-1;
		switch (sensor) {
			case 1:
				//LCD.clear(6);
				//LCD.drawString("CHK S1 -> " + s1, 0, 6);
				if(s1==1){
					//LCD.clear(7);
					//LCD.drawString("TRIGGER S1", 0, 7);
					actionToExecute = action;					
				}
				break;	
			case 2:
				if(s2==1){
					actionToExecute = action;
				}
				break;
			case 3:
				if(s3==value){
					actionToExecute = action;
				}
				break;
			case 4:
				if(s4 <= value){
					actionToExecute = action;
				}
				break;		
		}		
		if(actionToExecute >= 0){			
			if(fired && actionToExecute != 0 && actionToExecute !=7){
				actionToExecute = -1;
			}else{
				fired = true;
			}
		}else{
			fired = false;
		}
		return actionToExecute;
	}
	
	
}
