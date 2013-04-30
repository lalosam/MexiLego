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
import lejos.util.Delay;


public class Host {
	
	BTComm btComm;

	public static void main(String[] args) {
		Host h = new Host();
		h.NXTHost();
	}
	
	
	private void NXTHost(){
		Thread threadButtons = new Thread(new Buttons(this));
		threadButtons.start();
		
		btComm = new BTComm(this);
		
		Thread threadComm = new Thread(btComm);
		threadComm.start();
		
		Thread threadSensors = new Thread(new SensorsScanner(this));
		threadSensors.start();
		
		Actions.setHost(this);
		
		LCD.drawString("Running . . .", 0, 0);
		
		try{
			while(true){
				
				Thread.sleep(100);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void notifyButton(int b){
		switch (b) {
			case 1:
				LCD.drawString("ENTER NOTIFY", 0, 1);
				btComm.write("ENTER was pressed");
				break;
			case 2:
				LCD.drawString("LEFT  NOTIFY", 0, 1);
				break;
			case 3:
				LCD.drawString("RIGHT  NOTIFY", 0, 1);
				break;
			case 4:
				LCD.drawString("ESCAPE  NOTIFY", 0, 1);
				break;	
		}
		Delay.msDelay(1000);
		
	}
	
	
	public void notifyCommandReceived(String cmd){
		//LCD.drawString("                ", 0, 2);
		//LCD.drawString(cmd, 0, 2);
		String type = cmd.substring(0, 3);
		String params = null;
		String[] values = null;
		if(cmd.length() > 3){
			params = cmd.substring(4);
			values = CommandParser.parse(params);
			//CommandParser.print(values);
		}		
		if(type.equals("act")){
			Actions.execute(values);
		}else if(type.equals("cfg")){
			Config.execute(CommandParser.convertToInt(values));
		}else if(type.equals("pmt")){
			//LCD.drawString("Adding RULE", 0, 2);
			//Delay.msDelay(1000);
			Config.addRule(CommandParser.convertToInt(values));	
		}else if(type.equals("snd")){
			Actions.beep();
		}else if(type.equals("doi")){
			Actions.doIt(values);
		}else if(type.equals("sas")){
			Actions.setActionSteps(CommandParser.convertToInt(values));
		}
	}
	
	public void sendToPC(String s){
		btComm.write(s);
	}
	
	public void notifySensorReading(int[] values){
		if(Config.rules != null){
			for (Rule r : Config.rules) {
				int action = r.check(values[0], values[1], values[2], values[3]);
				if(action >= 0){
					Actions.executeSensorAction(action);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder("sns:");
		sb.append(values[0]);
		sb.append("@");
		sb.append(values[1]);
		sb.append("@");
		sb.append(values[2]);
		sb.append("@");
		sb.append(values[3]);
		btComm.write(sb.toString());
	}
	

}
