package com.tarena.shoot;
import java.awt.image.BufferedImage;
//Ӣ�ۻ��Ƿ�����
public class Hero extends FlyingObject{
	private int life;                 //��
	private int doubleFire;           //����ֵ
	private BufferedImage[] images;   //ͼƬ����
	private int index;                //Э��ͼƬ�л�
	private int tripleFire;

	
	public Hero(){
		image = ShootGame.hero0;
		width = image.getWidth();
		height = image.getHeight();
		x = 150;
		y = 400;                
		life =10;              //3����
		doubleFire = 0;        //��������
		images = new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
		index = 0;
		tripleFire=0;

	}
    	
	public void step(){
		//ÿ100������һ��
		image = images[index++/10%images.length];		
		/*
		index++;
		int a = index / 10;
		int b = a % 2;
		image = images[b];
		*/
	}
	
	public Bullet[] shoot(){
		int xStep = this.width/4;
		if(doubleFire > 0){           //˫��
			Bullet[] bullets = new Bullet[2];
			bullets[0] = new Bullet(this.x + 1 * xStep,this.y - 20);
			bullets[1] = new Bullet(this.x + 3 * xStep,this.y - 20);
			doubleFire -= 2;      //����˫��������ÿ�μ�2��ʵ�ʾ���2�������ĳ���ʱ��
			return bullets;
		}else if(tripleFire>0){
			Bullet[] bullets = new Bullet[3];
			bullets[0] = new Bullet(this.x + 1 * xStep,this.y - 20);
			bullets[1] = new Bullet(this.x + 2 * xStep,this.y - 20);
			bullets[2]=new Bullet(this.x+3*xStep,this.y-20);
			tripleFire -= 2;      //����˫��������ÿ�μ�2��ʵ�ʾ���2�������ĳ���ʱ��
			return bullets;
		}                       //����
			else {
			Bullet[] bullets = new Bullet[1];
			bullets[0] = new Bullet(this.x + 2 * xStep,this.y-20);
			return bullets;
		}

	}
    
	public int getx(){
        return x;
	}
	public int gety(){
        return y;
	}
	public void setx(int x){
        this.x=x;
	}
	public void sety(int y){
        this.y=y;
	}
	public void moveTo(int x,int y){
		this.x = x - this.width/2;
		this.y = y - this.height/2;
	}

	public boolean outOfBounds(){
		return false;              //Ӣ�ۻ�����Խ��
	}
    //����
    public void addLife(){
    	life++;
    }
    //��ȡ��
    public int getLife(){
    	return life;
    }
    
    public void addDoubleFire(){
    	doubleFire += 40;
    }
    public void addTripleFire(){
    	tripleFire += 40;
    }

    //����ֵ����
    public void setDoubleFire(int doubleFire){
    	this.doubleFire = doubleFire;
    }
    public void setTripleFire(int tripleFire){
    	this.tripleFire = tripleFire;
    }

    
    //Ӣ�ۻ�ײ����
    public boolean hit(FlyingObject other){
    	int x1 = other.x - this.width/2;
    	int x2 = other.x + other.width + this.width/2;
    	int y1 = other.y - this.height/2;
    	int y2 = other.y + other.height + this.height/2;
    	int hx = this.x + this.width/2;
    	int hy = this.y + this.height/2;
    	return hx > x1 && hx < x2
    			&&
    		   hy > y1 && hy < y2;
    }
    //����
    public void subtractLife(){
    	life--;
    }
    public void setLife(){
    	life=0;
    }
}
