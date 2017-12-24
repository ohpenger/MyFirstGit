package com.tarena.shoot;
import java.util.Random;
//Be---飞行物，也能获取奖励
public class Bee extends FlyingObject implements Award{
	private int xSpeed = 1;     //x坐标走步步数
	private int ySpeed = 2;     //y坐标走步步数
	   
	private int i;

	
	public Bee(){
		Random rand = new Random();
		 this.i=rand.nextInt(3);
		switch (i){
		case 0:image = ShootGame.buffer0;break;
		case 1:image = ShootGame.buffer1;break;
		case 2:image = ShootGame.buffer2;break;
		}

		
		width = image.getWidth();
		height = image.getHeight();
		
		x = rand.nextInt(ShootGame.WIDTH - this.width);
	    y = -this.height; 
		
	
	}
	
	public int getType(){
		return this.i;
	}
    public void step(){   	
    	if(x >= ShootGame.WIDTH - this.width){
    		xSpeed = -1;
    	}
    	if(x <= 0){
    		xSpeed = 1;
    	}
    	x += xSpeed;
    	y += ySpeed;		
	}
    public boolean outOfBounds(){
    	return this.y > ShootGame.HEIGHT;  
	}
}
