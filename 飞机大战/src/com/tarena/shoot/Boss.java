package com.tarena.shoot;

import java.util.Random;

public class Boss extends FlyingObject implements Attack{
	private int life;//Boss血量
	private int xspeed=1;
	
	public Boss(){
		image = ShootGame.boss;
		width = image.getWidth();
		height = image.getHeight();
		life=100;
		x = 256-(this.width/2);
		y = -this.height;   
		
		
	}
	public Boss(int i) {

		switch (i){
		case 2:image = ShootGame.boss2;break;
		case 3:image = ShootGame.boss3;break;
		}
		width = image.getWidth();
		height = image.getHeight();
		switch (i){
		case 2:life=20;break;
		case 3:life=30;break;
		}
        xspeed=2;
		x = 256-(this.width/2);
		y = -this.height;
		
	}
		
	 public void hurt(){
	    	life--;
	    }    
	 public boolean isdead() {
		 return life<=0;
	 }

	 public int getLife() {
		 return life;
	 }

	public boolean outOfBounds(){
		return this.y > ShootGame.HEIGHT;      //敌机的y坐标大于窗口的高
			
	}



public BulletEnemy[] shoot(){
	int xStep = this.width/4;
                        
		BulletEnemy[] bulletenemys = new BulletEnemy[9];
		bulletenemys[0] = new BulletEnemy(this.x ,this.y +50);
		bulletenemys[1] = new BulletEnemy(this.x ,this.y +50);
		bulletenemys[2] = new BulletEnemy(this.x ,this.y +50);
		bulletenemys[3] = new BulletEnemy(this.x + 2 * xStep,this.y +70);
		bulletenemys[4] = new BulletEnemy(this.x + 2 * xStep,this.y +70);
		bulletenemys[5] = new BulletEnemy(this.x + 2 * xStep,this.y +70);
		bulletenemys[6] = new BulletEnemy(this.x + this.width,this.y +50);
		bulletenemys[7] = new BulletEnemy(this.x + this.width,this.y +50);
		bulletenemys[8] = new BulletEnemy(this.x + this.width,this.y +50);
		
		return bulletenemys;
	}

public BulletEnemy[] shootcircle() {
	int xStep = this.width/3;
	int yStep = this.height/3;
	
	BulletEnemy[] bulletenemys = new BulletEnemy[8];
	bulletenemys[0] = new BulletEnemy(this.x ,this.y+yStep);
	bulletenemys[1] = new BulletEnemy(this.x ,this.y +2*yStep);
	bulletenemys[2] = new BulletEnemy(this.x ,this.y +3*yStep);
	bulletenemys[3] = new BulletEnemy(this.x+xStep ,this.y +3*yStep);
	bulletenemys[4] = new BulletEnemy(this.x+2*xStep ,this.y +3*yStep);
	bulletenemys[5] = new BulletEnemy(this.x+3*xStep ,this.y +3*yStep);
	bulletenemys[6] = new BulletEnemy(this.x+3*xStep ,this.y +2*yStep);
	bulletenemys[7] = new BulletEnemy(this.x+3*xStep ,this.y +yStep);
	
	return bulletenemys;
	
}





@Override
public int getScore() {
	// TODO Auto-generated method stub
	return 100;
}





@Override
public void step() {
	// TODO Auto-generated method stub
	if(this.y<100)
	y+=1;
	if(this.y==100&&x < ShootGame.WIDTH - this.width&&x > 0)
	x+=xspeed;
	if(this.y==100&&x >= ShootGame.WIDTH - this.width)
		{xspeed= -1*xspeed;
		x+=xspeed;
		}
	if(this.y==100&&x <= 0)
		{xspeed=-1*xspeed;
		x+=xspeed;
		}
}
public boolean bosshit(FlyingObject other){
	int x1 = this.x - other.width/2;
	int x2 = this.x + this.width + other.width/2;
	int y1 = this.y - other.height/2;
	int y2 = this.y + this.height + other.height/2;
	int hx = other.x + other.width/2;
	int hy = other.y + other.height/2;
	return hx > x1 && hx < x2
			&&
		   hy > y1 && hy < y2;
}
}
