package flappyBird;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.*;

public class FlappyBird extends JFrame implements ActionListener,MouseListener,KeyListener{
	
	int ticks=0,yMotion=0;
	public static final int W=1400;
	public static final int H=800;
	public static FlappyBird fb;
	public Renderer renderer;
	public Rectangle bird;
	public boolean draw=true;
	public boolean gameOver=false,started=false;
	public Random rand;
	public ArrayList<Rectangle> columns;
	
	public FlappyBird(){
		
		super("Flappy Bird");
		Timer timer=new Timer(20,this);
		renderer=new Renderer();
		
		rand=new Random();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(renderer);
		addMouseListener(this);
		addKeyListener(this);
		setSize(W,H);
		setVisible(true);
		setResizable(true);
		
		bird=new Rectangle(W/2-10,H/2-80,18,18);
		columns=new ArrayList<Rectangle>();
		
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
				
		timer.start();
		
	}
	
	public void repaint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.yellow);
		g.fillRect(0,H-150,W,150);
		
		g.setColor(Color.green);
		g.fillRect(0,H-170,W,20);
		
		if(draw){
			g.setColor(Color.red);
			g.fillRect(bird.x,bird.y,bird.width,bird.height);
		}
		
		
		for(Rectangle col:columns){
			paintColumns(g,col);
		}
		g.setColor(Color.white);
		g.setFont(new Font("Arial",1,100));
		if(gameOver){
			g.drawString("Game Over",W/2-250,H/2-100);
		}
	}
	
	public void addColumn(boolean start){
		int width=100;
		int space=150;
		int height=30+rand.nextInt(450);
		if(start){
			columns.add(new Rectangle(W+columns.size()*300,0,width,height));
			columns.add(new Rectangle(W+(columns.size()-1)*300,
					0+height+space,width,H-150-height-space));
		}
		else{
			columns.add(new Rectangle(columns.get(columns.size()-1).x+600,
					H-height-100,width,height-50));
			columns.add(new Rectangle(columns.get(columns.size()-1).x,0,
					width,H-height-space-100));
		}
	}
	
	public void paintColumns(Graphics g,Rectangle r){
		g.setColor(Color.green.darker().darker());
		g.fillRect(r.x,r.y,r.width,r.height);
	}
	
	public void jump(){ 
		if(!started){
			started=true;
		}
		else if(!gameOver){
			if(yMotion>0)
				yMotion=0;
			yMotion-=10;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int speed=15;
		ticks++;
		for(int i=0;i<columns.size();i++){
			Rectangle r=columns.get(i);
			r.x-=speed;
		}
		if(ticks%2==0){
			yMotion+=2;
		}
		for(int i=0;i<columns.size();i++){
			Rectangle column=columns.get(i);
			if(column.x+column.width<0){
				columns.remove(column);
				if(column.y==0)
					 addColumn(false);
			}
		}
		bird.y+=yMotion;
		for(Rectangle column:columns){
			if(column.intersects(bird)){
				gameOver=true;
				bird.x=column.x-bird.width;
			}
		}
		if(bird.y>H-150 || bird.y<0){
			gameOver=true;
		}
		if(bird.y+yMotion>=H-150){
			bird.y=H-170-bird.height;
		}
			
		renderer.repaint();
	}
	public static void main(String[] args){
		fb=new FlappyBird();
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE)
			jump();
		else if(arg0.getKeyCode()==KeyEvent.VK_R)
			gameOver=false;
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		jump();
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
