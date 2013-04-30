//**********************************************************************************
//*                                   MexiLego                                     *
//*                                                                                *
//*     Author:			Eduardo Sámano                                             *
//*     Version:		0.1                                                        *
//*     Last Update:    05/30/2013	                         			           *
//*     License:		MPL 2.0    http://www.mozilla.org/MPL/2.0/                 *
//*                                                                                *
//**********************************************************************************

package org.mexilego.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.mexilego.operation.Control;

public class ConfigListener implements ActionListener, FocusListener{
	
	private Control ctr = Control.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JComboBox){
			@SuppressWarnings("rawtypes")
			JComboBox cb = (JComboBox)e.getSource();
			String name = cb.getName();
	        int index = cb.getSelectedIndex();
	        System.out.println(name + " [" + index + "]");
	        if(name.equals("cmbMissions")){
	        	ctr.setMission(index);
	        }else if(name.equals("cmbSpeed")){
	        	ctr.setSpeed(index);
	        }else if(name.equals("cmbTSpeed")){
	        	ctr.setTurnSpeed(index);
	        }
		}else if(e.getSource() instanceof JButton){
			JButton btn = (JButton)e.getSource();
			String name = btn.getName();
	        if(name.equals("doIt")){
	        	System.out.println(" Do It!");
	        	ctr.executeDoIt();
	        }else if(name.equals("beep")){
	        	System.out.println("Beep");
	        	ctr.executeBeep();
	        }
		}else if(e.getSource() instanceof JTextField){
			JTextField txt = (JTextField)e.getSource();			
			try{
				ctr.setActionSteps(Integer.parseInt(txt.getText()));
	        } catch (Exception e2) {
				System.out.println("Invalid value ActionSteps");
	        }
			System.out.println("ActionSteps [" + txt.getText() + "]");
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {		
		if (!e.isTemporary()) {
	          String txt = ((JTextField) e.getSource()).getText();
	          try{
	        	  ctr.setActionSteps(Integer.parseInt(txt));
	          } catch (Exception e2) {
				System.out.println("Invalid value ActionSteps");
	          }
	          System.out.println("ActionSteps [" + txt + "]");
	     }	
	}     

}
