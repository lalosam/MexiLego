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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.mexilego.gui.ActionPAD;
import org.mexilego.operation.Control;

public class ActionPadListener implements MouseListener, ChangeListener, MouseMotionListener{
	
	private static ActionPadListener instance=null;
	
	private Control ctr;
	
	private ActionPadListener(){
		super();
		ctr = Control.getInstance();
	}
	
	public static void initiate(){
		if(instance == null){
			instance = new ActionPadListener();
		}
	}
	
	public static ActionPadListener getInstance(){
		return instance;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			JComponent comp = (JComponent)e.getSource();			
			//System.out.println("W [" + comp.getWidth() + "]  H [" + comp.getHeight() + "]  Coord: (" + e.getX() + "," + e.getY() + ")");
			
			int xRadio = comp.getWidth() / 2;
			int yRadio = comp.getHeight() / 2;
			
			int radio = xRadio<yRadio?xRadio:yRadio;
			
			//angle(v1, v2) = 
			
			int v1x = 0;
			int v1y = 1;
			
			int v2x = e.getX() - xRadio;
			int v2y = yRadio - e.getY();
			
			double normV2 = Math.sqrt(Math.pow(v2x,2)+Math.pow(v2y,2));
			
			//double angle = Math.acos((v1x * v2x + v1y * v2y) / (Math.sqrt(Math.pow(v1x,2)+Math.pow(v1y,2)) * Math.sqrt(Math.pow(v2x,2)+Math.pow(v2y,2))));
			
			double angle = Math.acos((v1x * v2x + v1y * v2y) / normV2);
			
			int intAngle = (int)(angle * 180 / Math.PI);
			
			if(v2x < 0){
				intAngle *= -1;
			}
			
			double unitV2 = normV2 / radio;
			
			if(unitV2 > 1){
				unitV2 = 1;
			}	
			
			ctr.setAngle(intAngle);
			ctr.setMagnitude(unitV2);			
			ctr.executeAction();
		}
		
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		ActionPAD.exited = false;

	}

	@Override
	public void mouseExited(MouseEvent e) {	
		ActionPAD.exited=true;
		((JComponent)(e.getSource())).repaint();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int zoom = (int)source.getValue();
            ctr.setZoom(zoom);
            //System.out.println("Zoom [" + zoom + "]");
        } 
				
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		JComponent comp = (JComponent)e.getSource();			
		//System.out.println("W [" + comp.getWidth() + "]  H [" + comp.getHeight() + "]  Coord: (" + e.getX() + "," + e.getY() + ")");
		
		int xRadio = comp.getWidth() / 2;
		int yRadio = comp.getHeight() / 2;
		
		int radio = xRadio<yRadio?xRadio:yRadio;
		
		//angle(v1, v2) = 
		
		int v1x = 0;
		int v1y = 1;
		
		int v2x = e.getX() - xRadio;
		int v2y = yRadio - e.getY();
		
		double normV2 = Math.sqrt(Math.pow(v2x,2)+Math.pow(v2y,2));
		
		//double angle = Math.acos((v1x * v2x + v1y * v2y) / (Math.sqrt(Math.pow(v1x,2)+Math.pow(v1y,2)) * Math.sqrt(Math.pow(v2x,2)+Math.pow(v2y,2))));
		
		double angle = Math.acos((v1x * v2x + v1y * v2y) / normV2);
		
		int intAngle = (int)(angle * 180 / Math.PI);
		
		if(v2x < 0){
			intAngle *= -1;
		}
		
		double unitV2 = normV2 / radio;
		
		if(unitV2 > 1){
			unitV2 = 1;
		}
		
		ActionPAD.x = (int)(Math.cos(angle+Math.PI/2)*(unitV2*radio));
		ActionPAD.y = (int)(Math.sin(angle+Math.PI/2)*(unitV2*radio));
		
		if(v2x > 0){
			ActionPAD.x=-ActionPAD.x;
		}
		
		ctr.setAngle(intAngle);
		ctr.setMagnitude(unitV2);		
		
		comp.repaint();
		
	}

}
