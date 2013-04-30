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

public class CommandParser {
	
	public static String[] parse (String s){		
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
	
	public static void print(String[] values, String prefix){
		for(int i=0; i < values.length; i++){
			System.out.println(prefix + i + " [" + values[i] + "]");
		}
	}

}
