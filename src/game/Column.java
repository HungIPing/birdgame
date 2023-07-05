package game;

import java.util.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Column {

	Image image;
	
	int x,y;
	int width,height;
	//柱子之间缝隙
	int gap;
	//柱子之间的距离
	int distance;
	Random random =new Random();
	
	//初始第N根柱子
	
	public Column(int n) throws Exception
	{
		image=new ImageIcon("picture/pillar.png").getImage();
		width=image.getWidth(null);
		height=image.getHeight(null);
		gap=190;
		distance=350;
		x=700+(n-1)*distance;
		y=random.nextInt(218)+132;
	}
	
	public void step()
	{
		x-=2;
		if(x<= -width/2)
		{
			x=650;
			y=random.nextInt(450);
		}
	}
}
