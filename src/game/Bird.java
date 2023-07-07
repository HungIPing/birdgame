package game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.imageio.ImageIO;

public class Bird {
	// 小鸟的属性
	Image image;
	int x, y;
	int width, height;
	int size;
	double g;
	double t;
	double v0;
	double speed;
	double s;

	public Bird() throws Exception {
		// 初始化小鸟的属性
		image = ImageIO.read(getClass().getResource("/bird.png"));
		width = image.getWidth(null);
		height = image.getHeight(null);
		x = 350;
		y = 280;
		size = 40;
		// 重力加速度
		g = 0.08;
		// 初始下降速度
		v0 = 0;
		t = 0.01;
		speed = v0;
		s = 0;

	}

	// 飞行动作
	public void fly() {
		speed = v0;
	}

	// 移动一步
	public void step() {
		speed += g;
		y += speed;
	}

	// 向上飞行
	public void clickFly() {
		speed = -1;
		int jumpDistance = -20;
		y += jumpDistance;
	}

	// 检测小鸟是否碰撞到地面
	public boolean hit(Ground ground) {
		boolean hit = y + size / 2 > ground.y;
		if (hit) {
			y = ground.y - size / 2;
		}
		return hit;
	}

	// 检测小鸟是否撞到柱子
	public boolean hit(Column column) {
		// 检测是否在柱子范围内
		if (x > column.x - column.width / 2 - size / 2 && x < column.x + column.width / 2 + size / 2) {
			if (y > column.y - column.gap / 2 + size / 2 && y < column.y + column.gap / 2 - size / 2) {
				return false;
			}
			return true;
		}
		return false;
	}
}
