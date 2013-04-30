//**********************************************************************************
//*                                   MexiLego                                     *
//*                                                                                *
//*     Author:			Eduardo Sámano                                             *
//*     Version:		0.1                                                        *
//*     Last Update:    05/30/2013	                         			           *
//*     License:		MPL 2.0    http://www.mozilla.org/MPL/2.0/                 *
//*                                                                                *
//**********************************************************************************

package org.mexilego.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.mexilego.operation.Control;

import lejos.nxt.LCD;
import lejos.pc.comm.NXTConnector;
import lejos.util.Delay;

public class BTComm implements Runnable{
	
	private boolean communicationEnabled = true; 
	
	DataOutputStream dos;
    DataInputStream dis;
    
    Control ctr = Control.getInstance();
    
    public BTComm(){
    	super();
    	if(communicationEnabled){
    		connect();
    	}
    }
    
	
	private void connect(){
		NXTConnector conn = new NXTConnector();
		boolean connected = conn.connectTo("btspp://");
		   
	       
        if (!connected) {
            System.err.println("Failed to connect to any NXT");
            System.exit(1);
        }
        
        dos = new DataOutputStream(conn.getOutputStream());
        dis = new DataInputStream(conn.getInputStream());
        
	}

	@Override
	public void run() {
		try{
			if(communicationEnabled){
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
					ctr.notifyCommandReceived(cmd);
					Thread.sleep(100);
				}
			}
		}catch(Exception e){
			LCD.drawString("Lost connection", 0, 0);
			Delay.msDelay(2000);
			System.exit(1);
		}
	}
	
	public void write(String s){		
		try {
			if(communicationEnabled){
				int delay =0;
				switch (ctr.getMission()) {
					case 0:
						delay = 0;
						break;
					case 1:
						delay = 500;
						break;
					case 2:
						delay = 1300;
						break;
					case 3:
						delay = 180000;
						break;
				}
				Thread.sleep(delay);
				dos.writeChars(s);
				dos.writeChar('\n');
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
