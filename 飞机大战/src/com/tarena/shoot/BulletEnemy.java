package com.tarena.shoot;

public class BulletEnemy extends FlyingObject{
	private int speed = 3;  //�ӵ��߲�������ֻ��y�����ڱ�
	public BulletEnemy(int x,int y){//�ӵ����������ŵл����ı仯���仯		
		image = ShootGame.bulletenemy;
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	}
    public void step(){
		y +=2;
		//x += speed*((hero.x-x)/(hero.y-y));
	}
    public void rstep(){
    	y +=1;
    	x-=2;
    	
    }
    public void lstep(){
    	y +=1;
    	x+=2;
    	
    }
    /*public void xstep(Hero hero){
    	y += speed;
    	x += speed*((hero.x-x)/(hero.y-y));
    	
    }*/
    public void step1() {
    	y+=1;
    	x-=1;
    }
    public void step2() {
    	y+=1;
    	x-=2;
    	
    }
    public void step3() {
    	y+=1;
    	x-=(4/3);
    	
    }
    public void step4() {
    	y+=1;
    	x-=(2/3);
    	
    }
    public void step5() {
    	y+=1;
    	x+=(2/3);
    	
    }
    public void step6() {
    	y+=1;
    	x+=(4/3);
    	
    }
    public void step7() {
    	y+=1;
    	x+=2;
    	
    }
    public void step8() {
    	y+=1;
    	x+=1;
    	
    }
    
    public boolean outOfBounds(){
		return this.y < -this.height;
	}
}
