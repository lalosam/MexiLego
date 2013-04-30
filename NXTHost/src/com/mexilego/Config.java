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

import java.util.ArrayList;
import java.util.List;

public class Config {
		
	public static boolean receivingRules = false;
	public static List<Rule> rules=null;
	private static List<Rule> tmpRules=null;
	
	public static void execute(int[] values) {
		if(values[0]==1){
			receivingRules = true;
			tmpRules = new ArrayList<Rule>();
		}else if(values[0]==2){
			receivingRules = false;
			applyRules();
		} 		
	}	

	public static void addRule(int[] values) {
		Rule r = new Rule(values[0], values[1], values[2]);
		tmpRules.add(r);
	}
	
	
	
	private static void applyRules(){
		rules = tmpRules;
		tmpRules = null;
	}

}
