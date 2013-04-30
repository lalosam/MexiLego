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

import lejos.nxt.Button;
import lejos.util.Delay;

public class Buttons implements Runnable{

	static final byte ENTER 	= 1;
	static final byte LEFT 		= 2;
	static final byte RIGHT 	= 4;
	static final byte ESCAPE	= 8;
	Host h;
	
	public Buttons(Host h) {
		this.h = h;
	}
	
	@Override
	public void run() {
		try{
			while(true){
				byte values = (byte)Button.readButtons();				
				if((values & ENTER) != 0 ){
					h.notifyButton(1);					
				}else if((values & LEFT) != 0 ){
					h.notifyButton(2);
				}else if((values & RIGHT) != 0 ){
					h.notifyButton(3);
				}else if((values & ESCAPE) != 0 ){
					h.notifyButton(4);
					Delay.msDelay(1000);
					System.exit(0);
				}
				Thread.sleep(1000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	

}
