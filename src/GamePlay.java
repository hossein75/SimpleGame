 
 import javax.swing.JPanel;
 
 import java.awt.event.KeyListener;
 import javax.swing.Timer;
 import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.KeyEvent;
 import java.util.Random;
	public class GamePlay extends JPanel implements KeyListener, ActionListener {
		
		private boolean play = false ;
		private int score = 0 ;
		
		private int totalBricks = 21;
		
		private Timer timer;
		private int delay= 8;
		
		private int playerX = 310 ;
		Random R = new Random();
		private int ballPosX = 350 ;
		private int ballPosY = 350 ;
		private int ballXdir = -1;//R.nextInt(2);
		private int ballYdir = -2;
		
		private MapGenerator map;
		
		public GamePlay() {
			map = new MapGenerator(3, 7);
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			timer = new Timer(delay, this);
			timer.start();
			
		}
		public void paint(Graphics g) {
			// backGround
			g.setColor(Color.black);
			g.fillRect(1, 1, 692, 592);
			
			//draw map
			map.draw((Graphics2D)g);
			
			// borders
			g.setColor(Color.blue);
			g.fillRect(0, 0, 3, 592);
			g.fillRect(0, 0, 592, 3);
			g.fillRect(692, 0, 3, 592);
			
			//score
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString(""+score, 530, 30);
			
			//paddle
			g.setColor(Color.white);
			g.fillRect(playerX, 560, 100, 8);
			
			//Ball
			g.setColor(Color.white);
			g.fillOval(ballPosX, ballPosY, 20, 20);
			
			//game over 
			if (ballPosY > 570) {
				play = false;
				ballXdir = ballYdir = 0;
				g.setColor(Color.green);
				g.setFont(new Font("serif", Font.BOLD, 35));
				g.drawString("your score is :"+score, 230, 270);
				g.setColor(Color.red);
				g.setFont(new Font("serif", Font.BOLD, 30));
				g.drawString("Game over", 250, 300);
				g.setFont(new Font("serif", Font.BOLD, 20));
				g.drawString("please press Enter to play again", 230, 320);
				
				
			}
			//win
			if(totalBricks == 0) {
				play = false;
				ballXdir = ballYdir = 0 ;
				g.setColor(Color.green);
				g.setFont(new Font("serif", Font.BOLD, 35));
				g.drawString("you won!"+score, 230,250 );
				g.setFont(new Font("serif", Font.BOLD, 20));
				g.drawString("please press Enter to play again", 230, 320);

			}
			g.dispose();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			timer.start();
			if(play) {
				
				ballPosX +=  ballXdir;
				ballPosY +=  ballYdir;
				
				if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(playerX, 560, 100, 8)) {
					ballYdir = -ballYdir;
				}
				 
				 for(int i = 0; i < map.map.length; i++) {
					for(int j = 0; j < map.map[i].length; j++) {
						if(map.map[i][j] > 0) {
							int brickX = j * map.brickWidth + 80;
							int brickY = i * map.brickHeight + 50;
							int brickWidth = map.brickWidth;
							int brickHeight = map.brickHeight ;
							
							Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
							Rectangle ball = new Rectangle(ballPosX, ballPosY, 20, 20);
							Rectangle bricks = rect;
							if(bricks.intersects(ball)) {
								map.setBtickValue(0, i, j);
								totalBricks--;
								score += 5;
								if(ballPosX <= bricks.x || ballPosX >= bricks.x + brickWidth ) {
									ballXdir = -ballXdir;
									
								}else {
									ballYdir = -ballYdir;
								}
							}
						}
					} 
				}
				
				if(ballPosX == 0) {
					ballXdir = -ballXdir;
				}
				if(ballPosX == 670) {
					ballXdir = -ballXdir;
				}
				if(ballPosY <= 0 ) {
					ballYdir = -ballYdir;
				}
				
				
			}
			
			repaint();
		}


		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if(playerX == 590) {
					playerX = 590 ;
				}else if (playerX < 590) {
					moveRight();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				if(playerX == 10) {
					playerX = 10;
				}else if(playerX > 10) {
					moveLeft();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				play = true ;
				ballPosX = ballPosY = R.nextInt(100)+250 ;
				ballXdir = -1 ;
				ballYdir = -2 ;
				playerX = 350 ;
				score = 0;
				totalBricks = 21 ;
				map = new MapGenerator(3, 7);
				
				repaint();
			}
		}
		
		public void moveRight() {
			play = true ;
			playerX+=32;
		}
		public void moveLeft() {
			play = true ;
			playerX-=32;
		}
		

		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}

	
	
	
}
