package game;

import javax.imageio.ImageIO;
import java.util.*;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.awt.*;

public class BirdGame extends JPanel {
	
	
	Image background;//背景图片
	Image startImage;//开始图片
	Image overImage;//游戏结束图片
	Ground ground;//地面
	Column column1,column2;//两根柱子
	Bird bird;//小鸟
	JFrame frame;
	int state;//游戏状态
	int score;
	//状态常量
	public static final int START=0;//开始
	public static final int RUNNING=1;//运行
	public static final int GAME_OVER=2;//结束
	
	
	
	
	public BirdGame() throws Exception
	{
		
		background = ImageIO.read(getClass().getResource("/background2.png"));
		startImage = ImageIO.read(getClass().getResource("/start1.png"));
		overImage=ImageIO.read(getClass().getResource("/game over1.png"));
		//初始化地面、柱子、小鸟、分号、状态
		ground=new Ground();
		column1=new Column(1);
		column2=new Column(2);
		
		bird=new Bird();
		state=0;
		score=0;
		
		frame = new JFrame();
		
		
			
		}
		
		
	
	
	public void paintComponent(Graphics g) {
		
		
	    // 绘制背景
	    g.drawImage(background, 0, 0, null);

	    // 绘制地面
	    g.drawImage(ground.image, ground.x, ground.y, null);

	    // 绘制柱子
	    
	    
	    g.drawImage(column1.image, column1.x, column1.y - column1.height / 2, null);
	    g.drawImage(column2.image, column2.x, column2.y - column2.height / 2, null);
	    

	    g.drawImage(bird.image, bird.x - bird.width / 2, bird.y - bird.height / 2, null);
	    
	    g.setFont(new Font("Courier New", Font.BOLD, 25));
	    g.setColor(Color.WHITE);
	    g.drawString("Score: " + score, 20, 30);

	    // 绘制开始和结束界面
	    switch (state) {
	        case START:
	            // 绘制开始界面前绘制背景
	            g.drawImage(startImage, 0, 0, null);
	            break;
	        case GAME_OVER:
	            g.drawImage(overImage, 0, 0, null);
	            break;
	    }
	}
	
	public void action() throws Exception {
	    // 不断重复和绘制
	    MouseAdapter mouseAdapter = new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            try {
	                switch(state) {
	                    case START:
	                        // 在开始前按下鼠标转为运行状态
	                        state = RUNNING;
	                        score= 0;
	                        break;
	                    case RUNNING:
	                        // 在运行状态，按下鼠标小鸟向上飞行
	                    	
	                        bird.clickFly();
	                        break;
	                    case GAME_OVER:
	                        // 在结束状态，按下鼠标重置数据变为开始
	                        column1 = new Column(1);
	                        column2 = new Column(2);
	                        bird = new Bird();
	                        state = START;
	                        break;
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }

	
	            
	        
	    };
	    addMouseListener(mouseAdapter);

	    while (true) {
	        switch (state) {
	            case START:
	                bird.fly();
	                break;
	            case RUNNING:
	                column1.step();
	                column2.step();
	                bird.step();
	                score++;
	                // 检测是否碰撞
	                if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
	                	JOptionPane.showMessageDialog(frame, "Game Over~ Final Score: " + score);
	                	state = GAME_OVER;
	                	break;
	                }
	                
	                break;
	        }
	        
	        

                
			//休眠1000/60毫秒
			Thread.sleep(1000/60);
			repaint();
		}
	    
	}
	
	//启动方法
	
	public static void main(String[] args) throws Exception
	{
		
		JFrame frame=new JFrame();
		BirdGame game=new BirdGame();
		frame.add(game);
		frame.setTitle("Game");
		frame.setSize(800,570);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		
	
		game.action();
		

		
	}
	
}