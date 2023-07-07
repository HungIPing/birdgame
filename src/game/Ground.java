package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Ground {

	Image image;
	
	int x,y;//位置
	
	int width,height;
	
	//地面初始化
	public Ground() throws Exception
	{
		image =ImageIO.read(getClass().getResource("/ground.png"));
		width=image.getWidth(null);
		height=image.getHeight(null);
		x=0;
		y=500;
	}
}