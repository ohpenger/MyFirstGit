package com.tarena.shoot;

import java.util.Random;

public class Airspecious extends FlyingObject implements Attack{
	private int speed = 1;//�л��߲��Ĳ���
	public Airspecious(){
		image = ShootGame.airspecious;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - this.width);
		y = -this.height;   //y:���ĵл��ĸ�
		
		
	}
		
	//��д getScore();
	public int getScore(){
		return 10;
	}
	
	public void step(){
		y += speed;
	}
	public boolean outOfBounds(){
		return this.y > ShootGame.HEIGHT;      //�л���y������ڴ��ڵĸ�
			
	}



public BulletEnemy[] shoot(){
	int xStep = this.width/4;
                        //����
		BulletEnemy[] bulletenemys = new BulletEnemy[1];
		bulletenemys[0] = new BulletEnemy(this.x + 2 * xStep,this.y + 70);
		return bulletenemys;
	}
}
