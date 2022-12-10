package breakout;
import utilities.GDV5;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
public class Breakout extends GDV5{
	static int w = 400;
	static int h = 800;
	
	private Brick[][] one = Brick.makeBricks(4,3);
	private Brick[][] two = Brick.makeBricks(8, 4);
	private Brick[][] three = Brick.makeBricks(10, 6);
	public static Ball ball = new Ball(10);

	public static Paddle paddle = new Paddle((int)w/2,h-20,100,20);
	public static int lives = 3;
	public static int score = 0;
	Font big = new Font("SERIF",Font.BOLD,130);
	Font title = new Font("SERIF",Font.BOLD,30);
	Font text = new Font("SERIF",Font.ROMAN_BASELINE, 30);
	Font mess = new Font("SANS_SERIF",Font.BOLD, 60); 
	private int gamestate = 0; // 0 is intro, 1 - 3 is levels, 4 is score, 5 is controls
	private boolean firstTwo = true;
	private boolean firstThree = true;
	public Breakout() {
		super();
	}

	private void drawString(Graphics2D g, String text, int x, int y) {
		for (String line : text.split("\n"))
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
	
	
	
	public void drawCenteredString(Graphics2D g, String text, Font font, int updown) {
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = 0 + (w - metrics.stringWidth(text)) / 2;
	    int y = 0 + ((h - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font);
	    g.drawString(text, x, y+updown);
	}
	public void introUpdate() {
		if(super.KeysPressed[KeyEvent.VK_ENTER]) {
			gamestate = 1;			}
		if(super.KeysPressed[KeyEvent.VK_C]) {
			gamestate = 5;
			}
	}
	public void introDraw(Graphics2D win) {
		win.setColor(Color.white);
		win.setFont(title);
		win.drawString("BREAKOUT - the most",0,100);
		win.setFont(text);
		win.drawString("Press [C] to see controls", 10, 400);
		win.drawString("Press [ENTER] to start", 10, 150);
	}

	public void gameUpdate(int level) {
		Ball.dx = Math.min(Ball.dx, 10);
		Ball.dy = Math.min(Ball.dy, 10);
		
		if(level == 1) {
			ball.move(one);
			if(score == 240) {
				gamestate = 2;
			}
		}else if(level==2) {
			if(firstTwo) {
				try {
					ball.setLocation(w/2,h/2);
					Ball.dx = 6;
					Ball.dy = 6;
					Thread.sleep(100);
					firstTwo = false;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			ball.move(two);
			if(score == 880) {
				gamestate = 3;
			}
		}else if (level == 3){
			
			if(firstThree) {
				try {
					ball.setLocation(w/2,h/2);
					Ball.dx = 6;
					Ball.dy = 6;
					Thread.sleep(1000);
					firstThree = false;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			ball.move(three);
			if(score == 2080) {
				gamestate = 4;
			}
		}
		paddle.bot();
		if(ball.intersects(paddle) && GDV5.collisionDirection(paddle, ball, ball.dx, ball.dy)==1) {
			ball.dy *= -1.1;
			if(ball.dx > 0) {
				if(super.KeysPressed[KeyEvent.VK_RIGHT]) {
					ball.dx *= 1.2;
				}else if(super.KeysPressed[KeyEvent.VK_LEFT]){
					ball.dx*= 0.8;
				}
			}else {
				if(super.KeysPressed[KeyEvent.VK_RIGHT]) {
					ball.dx *= 0.8;
				}else if(super.KeysPressed[KeyEvent.VK_LEFT]){
					ball.dx*= 1.2;
				
			}
		}
		}
		if(lives == 0) {
			gamestate = 4;
		}
		
	}
	public void gameDraw(Graphics2D win, int level) {

		if(level == 1) {
			for(Brick[] b : one) {
				for(Brick x : b) {
					if(x.isOn()) {
						
						win.setColor(x.c);
						win.draw(x);
						win.fill(x);
					}
				}
			}
		}else if(level==2) {
			for(Brick[] b : two) {
				for(Brick x : b) {
					if(x.isOn()) {
						win.setColor(x.c);
						win.draw(x);
						win.fill(x);
					}
				}
			}
			
		}else if (level == 3){
			for(Brick[] b : three) {
				for(Brick x : b) {
					if(x.isOn()) {
						win.setColor(x.c);
						win.draw(x);
						win.fill(x);
					}
				}
			}
		
		}
		
		
		
		win.setColor(Color.white);
		win.draw(ball);
		win.draw(paddle);
		win.fill(paddle);
		win.fill(ball);
		
		drawCenteredString(win, String.valueOf(lives), big,0);
		drawCenteredString(win, String.valueOf(score), text,100);
	}	
	
	public void controlUpdate() {
		if(super.KeysPressed[KeyEvent.VK_ENTER]) {
			gamestate = 1;
		}
		if(super.KeysPressed[KeyEvent.VK_H]) {
			gamestate = 0;
		}		
	}
	
	public void controlDraw(Graphics2D win) {
		win.setColor(Color.white);
		win.setFont(text);
		drawString(win,"Press [H] to go back to start", 10, 10);
		drawString(win,"left and right arrows to move \npaddle", 10, 100);
		drawString(win,"Press [ENTER] to \nSTART THE FREAKING \nGAME", 10, 200);
		drawString(win,"white brick - teleport", 10, 500);
		drawString(win,"orange brick - slowmo", 10, 600);
		drawString(win,"green brick - speedup", 10, 700);
	}	
	public void scoreUpdate() {
		if(super.KeysPressed[KeyEvent.VK_ENTER]) {
			score = 0;
			Ball.dx = 6;
			Ball.dy=6;
			lives = 3;
			gamestate = 1;
		}
		if(super.KeysPressed[KeyEvent.VK_H]) {
			gamestate = 0;
		}		
	}
	public void scoreDraw(Graphics2D win, int score) {
		win.setColor(Color.white);
		win.setFont(text);
		if(score >= 2080 ) {
			drawString(win,"good", 10, 10);
			drawString(win,"your score:", 10, 100);
			drawString(win,String.valueOf(score), 10, 200);
		}else if(score > 1500 ) {
			drawString(win,"not bad", 10, 10);
			drawString(win,"your score:", 10, 100);
			drawString(win,String.valueOf(score), 10, 200);
		}else if(score <= 1500 ) {
			drawString(win,"please try harder \nnext time", 10, 10);
			drawString(win,"your score:", 10, 100);
			drawString(win,String.valueOf(score), 10, 200);
		}
		
		drawString(win,"Press [H] to go back to start", 10, 500);
		drawString(win,"Press [ENTER] to \nRUN IT BACK", 10, 600);
	
	}
	
	
	@Override
	public void update() {
		if(gamestate == 0) introUpdate();
		if(gamestate == 1) gameUpdate(1);
		if(gamestate == 2) gameUpdate(2);
		if(gamestate == 3) gameUpdate(3);
		if(gamestate == 4) scoreUpdate();
		if(gamestate == 5) controlUpdate();
	}
	
	@Override
	public void draw(Graphics2D win) {
		if(gamestate == 0) introDraw(win);
		if(gamestate == 1) gameDraw(win,1);
		if(gamestate==2) gameDraw(win,2);
		if(gamestate==3) gameDraw(win,3);
		if(gamestate==4) gameDraw(win,score);
		if(gamestate==5) controlDraw(win);
	}
	
	public static void main(String[] args) {
		Breakout b = new Breakout();
		b.start();
	}
}

