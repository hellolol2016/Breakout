package breakout;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.sound.sampled.*;
import java.io.*;
public class Paddle extends Rectangle{
private int up;
private int down;
	public Paddle(int x, int y, int w,int h) {
		super(x,y,w,h);
		this.up=up;
		this.down=down;
		}
	public void move() {
		if(Breakout.KeysPressed[KeyEvent.VK_RIGHT] && this.getX()<400-this.getWidth()) {
			this.setLocation((int)this.getX()+8,(int)this.getY());
		}
		if(Breakout.KeysPressed[KeyEvent.VK_LEFT] && this.getX()>0) {
			this.setLocation((int)this.getX()-8,(int)this.getY());
		}	
	}
	public void bot() {
		if(this.getCenterX()<Breakout.ball.getCenterX()) {
			this.setLocation((int)Breakout.ball.getX(),(int)this.getY());
		}
		if(this.getCenterX()>Breakout.ball.getCenterX() ) {
			this.setLocation((int)Breakout.ball.getX(),(int)this.getY());
		}
		
	}

	
	
}
