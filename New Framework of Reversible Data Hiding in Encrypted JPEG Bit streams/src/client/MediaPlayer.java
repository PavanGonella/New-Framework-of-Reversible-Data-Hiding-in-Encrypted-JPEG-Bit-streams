package client;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author spiro13
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.net.URL;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MediaPlayer extends javax.swing.JPanel 
{
	
	public void playVideo(URL mediauUrl) 
	{
		int n=0;
	setLayout(new BorderLayout());
	try 
	{

	Player mediaPlayer=Manager.createRealizedPlayer(new MediaLocator(mediauUrl));
	Component video=mediaPlayer.getVisualComponent();
	Component control=mediaPlayer.getControlPanelComponent();
	Object[] options = {"Yes, please","No, thanks","Exit, no ham!"};
	n = JOptionPane.showOptionDialog(null,"Would you like to Play selected Video "+ " ","A Silly Question",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
	System.out.println("i value for vdeo is :::"+n);
	
	if (video!=null && n==0) 
	{	
		System.out.println("video palyingggggggggg");		
		add(video, BorderLayout.CENTER);  
		add(control, BorderLayout.SOUTH);          // place the control in  panel
		mediaPlayer.start();      
		
		JFrame mediaTest = new JFrame( "Movie Player" );
		mediaTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		mediaTest.getContentPane().add( this );
		mediaTest.setSize( 800, 700 ); // set the size of the player
		mediaTest.setLocationRelativeTo(null);
		mediaTest.setVisible( true );// place the video component in the panel
		
	}
	else
	{ 
		n=1;
		System.out.println("in elseeeee n value is  :::  "+n);
		setVisible(false);
		 
	}
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
 
	
}


public static void main(String[] args) 
{
	MediaPlayer mediaPanel = new MediaPlayer();
	 
	}
}