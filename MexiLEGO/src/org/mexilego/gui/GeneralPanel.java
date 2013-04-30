//**********************************************************************************
//*                                   MexiLego                                     *
//*                                                                                *
//*     Author:			Eduardo Sámano                                             *
//*     Version:		0.1                                                        *
//*     Last Update:    05/30/2013	                         			           *
//*     License:		MPL 2.0    http://www.mozilla.org/MPL/2.0/                 *
//*                                                                                *
//**********************************************************************************

package org.mexilego.gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GeneralPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public GeneralPanel(){
		super();
		setBorder(BorderFactory.createTitledBorder("Mission"));
		setSize(300, 100);
		
	}
	
}
