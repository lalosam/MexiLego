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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Queue;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.util.Delay;

public class BTComm implements Runnable{
	DataInputStream dis = null;
	DataOutputStream dos = null;
		
	Queue<String> inQueue = new Queue<String>();
	Queue<String> outQueue = new Queue<String>();
	
	Host h;
	
	
	public BTComm(Host host) {
		super();
		this.h = host;
		connect();
	}


	public void connect(){
		LCD.drawString("Connecting . . .", 0, 0);
		BTConnection btc = Bluetooth.waitForConnection();
		LCD.drawString("Connected         ", 0, 0);
		Delay.msDelay(1000);
		dis = btc.openDataInputStream();
		dos = btc.openDataOutputStream();
	}


	@Override
	public void run() {
		try{
			while(true){
				boolean readFlag = true;
				String cmd = "";
				while(readFlag){
					char c = dis.readChar();							
					if(c == '\n'){
						readFlag = false;
					}else{
						cmd = cmd + c;
					}				
				}
				h.notifyCommandReceived(cmd);
				Thread.sleep(100);
			}
		}catch(Exception e){
			LCD.drawString("Lost connection", 0, 0);
			Delay.msDelay(2000);
			System.exit(1);
		}
		
	}
	
	public String read(){
		if(!inQueue.empty()){
			return (String) inQueue.pop();
		}else{
			return null;
		}
	}
	
	public void write(String s){		
		//outQueue.push(s);
		try {
			dos.writeChars(s);
			dos.writeChar('\n');
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
