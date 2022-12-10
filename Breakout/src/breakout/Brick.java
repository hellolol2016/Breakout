package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import utilities.GDV5;

public class Brick extends Rectangle{
	public Color c;
	private boolean on = true;
	public boolean powerup = false;
	static Color[] colors = new Color[]{Color.red,Color.blue};
	public Brick(int x,int y, Color c, int row) {
		super(x,y,((GDV5.getMaxWindowX()-70-(10*(row-1)))/row),15);
		this.c =c; 
		this.on = true;
		
	}
	public boolean isOn() {
		return this.on;
	}
	public void setOn(boolean b) {
		this.on = b;
	}
	public static Brick[][] makeBricks(int l, int r) {
		Brick[][] bricks = new Brick[r][l];
		int x = 35;
		int y = 30;
		int padding = 10;
		for(int j = 0; j < r; j++) {
			for(int i = 0; i < l;i++) {
				if(i % 2 == 0) {
					bricks[j][i] = new Brick(x,y,colors[0],l);	
				}else {
					bricks[j][i] = new Brick(x,y,colors[1],l);
				}
				
				int pu = (int)(Math.random()*20);
				
				if(pu == 7) {
					bricks[j][i].c=Color.green;
				}
				if(pu == 8) {
					bricks[j][i].c=Color.orange;
				}
				if(pu == 9 && j == r-1) {
					bricks[j][i].c=Color.white;
				}
				
				
				x+= bricks[0][0].width+padding;
			}
			y+=bricks[0][0].height+padding;
			x = 35;
		}
		return bricks;
	}
	
	public void draw(Graphics2D pb) {
		pb.setColor(c);
		pb.fill(this);
		pb.setColor(Color.white);
		pb.draw(this);
	}
	
	public void delete() {
		this.setSize(0, 0);
	}
	
}
