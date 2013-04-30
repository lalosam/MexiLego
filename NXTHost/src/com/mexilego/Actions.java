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

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.Sound;

public class Actions {
	
	static boolean RETURN_INMEDIATE = true; 
	static Host h;
	static int actionsteps=0;
	
	public static void setHost(Host h){
		Actions.h = h;
	}
	
	public static void execute(String[] v){
		execute(Integer.parseInt(v[0]), Integer.parseInt(v[1]), Integer.parseInt(v[2]), Integer.parseInt(v[3]),true);
	}
	
	public static void execute(int aspeedP, int astepsP, int speedP, int stepsP, boolean wait){
		int aspeed  = aspeedP*180;
		int asteps	= (int)(astepsP*4.35);
		int speed 	= speedP*180;
		int steps	= stepsP*34;
		
		if(wait){
			Motor.B.waitComplete();
			Motor.C.waitComplete();
		}
		
		if(asteps != 0){
			boolean clockwise = true;
			if(asteps < 0){
				clockwise = false;
				asteps = -asteps;
			}
			turn(aspeed, -asteps,clockwise);			
		}
		
		Motor.B.waitComplete();
		Motor.C.waitComplete();
		
		if(steps !=0){
			lineal(speed, steps);
		}
	}
	
	private static void lineal(int speed, int steps){
		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);
		
		Motor.B.rotate(steps,true);
		Motor.C.rotate(steps,true);
	}
	
	
	
	private static void turn(int aspeed, int asteps, boolean clockwise){
				
		Motor.B.setSpeed(aspeed);
		Motor.C.setSpeed(aspeed);
		if(clockwise){
			Motor.B.rotate(asteps,RETURN_INMEDIATE);
			Motor.C.rotate(-asteps,RETURN_INMEDIATE);
		}else{
			Motor.B.rotate(-asteps,RETURN_INMEDIATE);
			Motor.C.rotate(asteps,RETURN_INMEDIATE);
		}		
		
	}
	
	private static void stop(){
		Motor.B.stop(true);
		Motor.C.stop(false);
	}
	
	public static void beep(){
		LCD.drawString("SOUND", 0, 7);
		Sound.beepSequenceUp();
	}
	
	public static void doIt(String[] values){		
		doIt(Integer.parseInt(values[0]));
	}
	
	public static void doIt(int steps){
		Motor.A.setSpeed(1440);
		Motor.A.rotate(steps, RETURN_INMEDIATE);
	}

	public static void executeSensorAction(int action) {
		//LCD.drawString("           ", 0, 1);
		//LCD.drawString("Fired [" + action + "]", 0, 1);
		switch (action) {
			case 0:
				stop();
				break;
			case 1:
				doIt(actionsteps);		
				break;
			case 2:
				beep();
				break;
			case 3:
				stop();
				execute(3,0,3,-10,false);
				Motor.B.waitComplete();
				Motor.C.waitComplete();
				execute(3,180,3,10,false);				
				break;
			case 4:
				stop();
				break;
			case 5:
				beep();
				break;
			case 6:
				doIt(actionsteps);
				break;
			case 7:
				stop();
				break;
			case 8:
				doIt(actionsteps);
				break;
			case 9:
				beep();		
				break;
			case 10:
				stop();
				execute(5,180,5,20,false);
				break;		
		}
		
	}

	public static void setActionSteps(int[] values) {
		actionsteps = values[0];		
	}

}
