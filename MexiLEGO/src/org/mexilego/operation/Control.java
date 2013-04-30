//**********************************************************************************
//*                                   MexiLego                                     *
//*                                                                                *
//*     Author:			Eduardo Sámano                                             *
//*     Version:		0.1                                                        *
//*     Last Update:    05/30/2013	                         			           *
//*     License:		MPL 2.0    http://www.mozilla.org/MPL/2.0/                 *
//*                                                                                *
//**********************************************************************************

package org.mexilego.operation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.mexilego.comm.BTComm;
import org.mexilego.comm.CommandParser;
import org.mexilego.gui.ActionPAD;
import org.mexilego.gui.SensorComponent;

public class Control {
	
	private static Control  instance = null;
	
	private int mission=1;
	private int speed=2;
	private int turnSpeed=2;
	private int actionSteps=360;
	
	private int zoom = 5;
	private int angle;
	private double magnitude;
	private int realMagnitude;
	
	private BufferedImage img;
	
	private ActionPAD aPAD = null;
	
	private BTComm comm = null;
	
	private int[] sensors = {0, 0, -1, 255};	
	
	List<SensorComponent> listSensors = new ArrayList<SensorComponent>() ;
	
	private Control(){
		super();		
		loadImage();
	}
	
	public static Control getInstance(){
		if(instance == null){
			instance = new Control();
		}
		return instance;
	}

	public int getMission() {
		return mission;
	}

	public void setMission(int mission) {
		this.mission = mission;
		loadImage();
		if(aPAD != null){
			aPAD.repaint();
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getTurnSpeed() {
		return turnSpeed;
	}

	public void setTurnSpeed(int turnSpeed) {
		this.turnSpeed = turnSpeed;
	}

	public int getActionSteps() {
		return actionSteps;
	}

	public void setActionSteps(int actionSteps) {
		this.actionSteps = actionSteps;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
		calculateRealMagnitude();
		if(aPAD != null){
			aPAD.repaint();
		}
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;		
	}

	public double getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;		
		calculateRealMagnitude();
	}

	public int getRealMagnitude() {
		return realMagnitude;
	}
	
	
	
	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}
	
	

	public ActionPAD getaPAD() {
		return aPAD;
	}

	public void setaPAD(ActionPAD aPAD) {
		this.aPAD = aPAD;
	}
	
	

	public BTComm getComm() {
		return comm;
	}

	public void setComm(BTComm comm) {
		this.comm = comm;
		Thread btCommThreat = new Thread(comm);		
		btCommThreat.start();
	}		

	private void calculateRealMagnitude(){
		realMagnitude = (int)((6-zoom) * 10 * magnitude);
		//System.out.println("Magnitude: [" + realMagnitude + "]");		
	}
	
	private void loadImage(){
		File f=null; 
		switch (mission) {
		case 0:
			f = new File("resources/city.png");
			break;
		case 1:
			f = new File("resources/earth2.png");
			break;
		case 2:
			f = new File("resources/moon2.png");
			break;			
		case 3:
			f = new File("resources/mars1.png");
			break;		
		}		
		
		try {
			img = ImageIO.read(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executeAction() {
		StringBuilder sb = new StringBuilder("act:");
		sb.append(turnSpeed+1);
		sb.append("@");
		sb.append(angle);
		sb.append("@");
		sb.append(speed+1);
		sb.append("@");
		sb.append(realMagnitude);
		comm.write(sb.toString());		
	}

	public void executeBeep() {	
		comm.write("snd");	
	}

	public void notifyCommandReceived(String cmd) {
		//String prefix="P";
		//System.out.println("Received [" + cmd + "]");
		String type = cmd.substring(0, 3);
		String params = cmd.substring(4);
		String[] values = CommandParser.parse(params);		
		if(type.equals("sns")){
			//values[2] = colorNames[Integer.parseInt(values[2]) + 1];
			//prefix = "Sensor";
			for(int i = 0; i < 4; i++){
				sensors[i] = Integer.parseInt(values[i]);
			}
			notifySensorComponents();
		}else if(type.equals("cfg")){
			
		}
		//CommandParser.print(values, prefix);
	}

	public void executeDoIt() {		
		comm.write("doi:" + actionSteps);		
	}
	
	public int getSensorValue(int index){
		return sensors[index];
	}
	
	public void registerSensorComponent(SensorComponent sc){
		listSensors.add(sc);
	}
	
	private void notifySensorComponents(){
		for (SensorComponent sc : listSensors) {
			sc.repaint();
		}
	}

	@SuppressWarnings("rawtypes")
	public void sendParameters(Object[] sensorStates, int[] sensorTypes,
			Object[] sensorValues, Object[] sensorActions) {
		comm.write("sas:" + actionSteps);
		comm.write("cfg:1");
		
		for(int i = 0; i < sensorStates.length; i++){
			JCheckBox jchState = (JCheckBox)sensorStates[i];
			if(jchState.isSelected()){
				StringBuilder sb = new StringBuilder("pmt:");
				sb.append(sensorTypes[i]);
				sb.append('@');
				switch (sensorTypes[i]) {
					case 1:
					case 2:
						sb.append(1);
						sb.append('@');
						break;
					case 3:						
						JComboBox jcbValue = (JComboBox)sensorValues[i];
						sb.append(jcbValue.getSelectedIndex());						
						sb.append('@');
						break;
					case 4:
						JTextField jtfValue = (JTextField)sensorValues[i];
						sb.append(jtfValue.getText());
						sb.append('@');
						break;			
				}
				JComboBox jcbAction = (JComboBox)sensorActions[i];
				sb.append(jcbAction.getSelectedIndex());
				comm.write(sb.toString());
				System.out.println(sb.toString());
			}
			
		}
		comm.write("cfg:2");
		
	}

	

}
