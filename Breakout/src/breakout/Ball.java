package breakout;

import java.awt.Rectangle;
import breakout.Breakout;
import utilities.GDV5;
public class Ball extends Rectangle{
	private int h = 800;
	private int w = 400;
	public static int dx;
	public static int dy;
	public Ball(int size) {
		super(200,300,size,size);
		this.dx = 5;
		this.dy = 5;
	}
	public void move(Brick[][] bricks) {
		this.translate(dx,dy);
		if(this.getMaxX()>w || this.getMinX() < 0) {
			dx*=-1;
		}
		if(this.getMinY()<0) {
			dy*=-1;
		}
		for(int i = 0 ; i < bricks.length;i++) {
			for(int j =0; j < bricks[0].length; j++) {
				if(this.intersects(bricks[i][j]) && bricks[i][j].isOn()) {
					int collision = GDV5.collisionDirection(bricks[i][j],this, dx, dy);
					bricks[i][j].setOn(false);
					Breakout.score+= 20;
					if(collision == 1 || collision==3) {
						dy*=-1;
					}else{
						dx*=-1;
					}
				}
			}
		}
		if(this.getY() > 1000) {
			this.setLocation(w/2, h/2);
			Breakout.lives -= 1;

		}
	}

	
}
