//**********************************************************************************
//*                                   MexiLego                                     *
//*                                                                                *
//*     Author:			Eduardo Sámano                                             *
//*     Version:		0.1                                                        *
//*     Last Update:    05/30/2013	                         			           *
//*     License:		MPL 2.0    http://www.mozilla.org/MPL/2.0/                 *
//*                                                                                *
//**********************************************************************************

package org.mexilego;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.mexilego.comm.BTComm;
import org.mexilego.gui.BasePanel;
import org.mexilego.operation.Control;

public class MexiLEGO {
	
	private static void createAndShowGUI() {
        //Create and set up the window.
		
		BTComm comm = new BTComm();
		
		Control.getInstance().setComm(comm);
		
		File fImg = new File("resources/brick128.png");
		BufferedImage img=null;
		try {
			img = ImageIO.read(fImg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        JFrame f = new JFrame("EXPLORING THE UNIVERSE . . .");
        f.setMinimumSize(new Dimension(800,600));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
        f.setLocationRelativeTo(null);
        JPanel jpnBase = new BasePanel();
        f.getContentPane().add(jpnBase);
        f.setIconImage(img);
        f.setVisible(true);
        f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
       
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
