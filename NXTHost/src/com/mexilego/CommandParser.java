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

public class CommandParser {
	
	public static String[] parse (String s){
		//LCD.drawString("LENGTH " + s.length(), 0, 7);
		int count = 0;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(c == '@'){
				count++;
			}
		}		
		String[] result = new String[count+1];
		int lastIndex = 0;
		for(int i = 0; i <= count; i++){
			int pos = s.indexOf("@", lastIndex);
			if(pos == -1){
				pos = s.length();
			}
			//System.out.println("POS: " + pos);
			result[i] = s.substring(lastIndex,pos);
			lastIndex = pos+1;
		}		
		return result;
	}
	
	public static int[] convertToInt (String[] values){
		int[] result = new int[values.length];
		for(int i = 0; i < values.length; i++){
			result[i] = Integer.parseInt(values[i]);
		}
		return result;
	}
	
	public static void print(String[] values){
		int offset = 4;		
		for(int i=0; i < 4; i++){
			LCD.drawString("                ", 0, offset+i);
		}
		for(int i=0; i < values.length; i++){			
			LCD.drawString("P" + i + " [" + values[i] + "]", 0, offset+i);
		}
	}

}
