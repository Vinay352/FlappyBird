package flappyBird;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.*;

public class Renderer extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.cyan);
		FlappyBird.fb.repaint(g);
	}

	
	
}
