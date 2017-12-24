package com.tarena.shoot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javafx.scene.input.KeyCode;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
//�����������
public class ShootGame extends JPanel implements KeyListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600;  //���ڵĿ�
	public static final int HEIGHT = 900; //���ڵĸ�
	//��̬��Դ
	public static BufferedImage background; //����ͼ
	public static BufferedImage start;     //��ʼͼ
	public static BufferedImage pause;      //��ͣͼ
	public static BufferedImage gameover;   //��Ϸ����ͼ
	public static BufferedImage airplane;   //�л�ͼ
	public static BufferedImage bee;        //�۷�ͼ
	public static BufferedImage bullet;     //�ӵ�ͼ
	public static BufferedImage hero0;      //Ӣ�ۻ�0ͼ
	public static BufferedImage hero1;      //Ӣ�ۻ�1ͼ
	public static BufferedImage airspecious; //����л�ͼ
	public static BufferedImage bulletenemy; //�����ӵ�ͼ
	public static BufferedImage boss; //Bossͼ
	public static BufferedImage next; //��һ��ͼ
	public static BufferedImage buffer0; //BUFF����
	public static BufferedImage buffer1;
	public static BufferedImage buffer2;
	public static BufferedImage boss2;  //�ڶ���Boss
	public static BufferedImage boss3;  //������boss

	
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	public static final int NEXT = 4;
	public static int flag=0;
	public static int UP=0;
	public static int DOWN=0;
	public static int LEFT=0;
	public static int RIGHT=0;
	private int state = 0;   //��ǰ״̬
	
	private Hero hero = new Hero();
	//private Boss boss1 = new Boss();//Ӣ�ۻ�
	private Bullet[] bullets = {};          //�ӵ�����
	private FlyingObject[] flyings = {};     //��������
	private BulletEnemy[] bulletenemys = {};          //�����ӵ�����
	private Boss[] bosses = {};
	
	
	private Timer timer;
	private int intervel = 10;              //���ʱ�䣺��λ--����
	
	
	//��̬��
	static{
		try{
			background = ImageIO.read(new File("background.png"));
			start = ImageIO.read(new File("start.png"));
			pause = ImageIO.read(new File("pause.png"));
			gameover = ImageIO.read(new File("gameover.png"));
			airplane = ImageIO.read(new File("airplane.png"));
			bee = ImageIO.read(new File("bee.png"));
			bullet = ImageIO.read(new File("bullet1.png"));
			hero0 = ImageIO.read(new File("hero5.png"));
			hero1 = ImageIO.read(new File("hero6.png"));
			airspecious = ImageIO.read(new File("airspecious.png"));
			bulletenemy = ImageIO.read(new File("bulletenemy.png"));
			boss= ImageIO.read(new File("boss.png"));
			next= ImageIO.read(new File("next.png"));
			boss2= ImageIO.read(new File("bee.png"));
			buffer0=ImageIO.read(new File("doublefire.png"));
			buffer1=ImageIO.read(new File("life.png"));
			buffer2=ImageIO.read(new File("triplefire.png"));
            
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public static FlyingObject nextOne(){
		Random rand = new Random();
		int type = rand.nextInt(20);  //����0��19�������
		if(type == 0){                //�����Ϊ0������bee;���򷵻صл�
			Bee bee1=    new Bee();
			FlyingObject fo1=(FlyingObject) bee1 ;
			return  fo1;
		}
		if(type>0&&type<=15){	
			Airplane airplane= new Airplane();
			FlyingObject fo2= (FlyingObject) airplane;
		return fo2;
		}
		else {
			Airspecious airspecious = new Airspecious();
			FlyingObject fo3=(FlyingObject) airspecious;
			return fo3;
		}
		
	}
		
	int flyEnteredIndex = 0;
	
	//���˵ǳ�
	public void enterAction(){//10������һ��
		flyEnteredIndex++;  //ÿ10������1
		if(flyEnteredIndex % 40 == 0){
			FlyingObject obj = nextOne();
			flyings = Arrays.copyOf(flyings,flyings.length + 1);
			flyings[flyings.length - 1] = obj;//�����˸�ֵ��flyings��������һ��Ԫ��
		}
	}
	
	public void stepAction(){        //10������һ��
		hero.step();                 //Ӣ�ۻ���һ��
		for(int i = 0;i < flyings.length;i++){
			flyings[i].step();       //������һ��
		}
		for(int i = 0;i < bullets.length;i++){
			bullets[i].step();       //�ӵ���һ��
		}
		for(int i = 0;i < bulletenemys.length;i++){
			bulletenemys[i].step();       //�����ӵ���һ��
		}
	}
	int allowable =0;
	public void BossbulletstepAction() {
		if(allowable==0) {
			bulletenemys = new BulletEnemy[0];
		}
		hero.step(); 
		for(int i = 0;i < bullets.length;i++){
			bullets[i].step();       //�ӵ���һ��
		}
		for(int i = 0;i < flyings.length;i++){
			flyings[i].step();       //������һ��
		}
		for(int i = 0;i < bosses.length;i++){
			bosses[i].step();       //boss��һ��
		}
		if(score<200)
		{for(int i = 0;i < bulletenemys.length;i++){
			if(i%3==0)
			    bulletenemys[i].lstep();
			if(i%3==1)
			    bulletenemys[i].rstep();
			if(i%3==2)
				bulletenemys[i].step();}
		}
		if(score>=300&&score<400) 
		{for(int i = 0;i < bulletenemys.length;i++){
				if(i%8==0)
				    bulletenemys[i].step1();
				if(i%8==1)
				    bulletenemys[i].step2();
				if(i%8==2)
					bulletenemys[i].step3();
				if(i%8==3)
				    bulletenemys[i].step4();
				if(i%8==4)
				    bulletenemys[i].step5();
				if(i%8==5)
				    bulletenemys[i].step6();
				if(i%8==6)
					bulletenemys[i].step7();
				if(i%8==7)
				    bulletenemys[i].step8();
				}	
		}
	}
	
	int shootIndex = 0;
	public void shootAction(){         //10������һ��
		shootIndex++;                 //ÿ10������1
		if(shootIndex % 30 == 0){     //300���뷢��һ���ӵ�			
			Bullet[] bs = hero.shoot();//��ȡ�ӵ�����
			bullets = Arrays.copyOf(bullets,bullets.length + bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length,bs.length);
			
		}
		
		for(int i = 0;i < flyings.length;i++)  //����л������ӵ�
		{if(flyings[i] instanceof Attack&&shootIndex % 100 == 0) {  //ÿ1�뷢��һ���ӵ�
			BulletEnemy[] ba = ((Airspecious) flyings[i]).shoot();
			bulletenemys = Arrays.copyOf(bulletenemys,bulletenemys.length + ba.length);
			System.arraycopy(ba, 0, bulletenemys, bulletenemys.length - ba.length,ba.length);
		}
			
			
		}
		for(int i = 0;i < bosses.length;i++)            //Boss�����ӵ�
		{
			if(bosses[i] instanceof Attack&&shootIndex % 200 == 0&&score<200) {        //ÿ2�뷢��һ���ӵ�
			BulletEnemy[] ba = ((Boss) bosses[i]).shoot();
			bulletenemys = Arrays.copyOf(bulletenemys,bulletenemys.length + ba.length);
			System.arraycopy(ba, 0, bulletenemys, bulletenemys.length - ba.length,ba.length);
		}
			if(bosses[i] instanceof Attack&&shootIndex % 200 == 0&&score>=300&&score<400) {
				BulletEnemy[] ba = ((Boss) bosses[i]).shootcircle();
				bulletenemys = Arrays.copyOf(bulletenemys,bulletenemys.length + ba.length);
				System.arraycopy(ba, 0, bulletenemys, bulletenemys.length - ba.length,ba.length);	
			}
			
			
		}
		
	}
	
	//ɾ��Խ�������
	public void outOfBoundsAction(){
		int index = 0;
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for(int i = 0;i < flyings.length;i++){
			FlyingObject f = flyings[i];
			if(!f.outOfBounds()){
				flyingLives[index] = f;//��Խ�磬����װ��flyingLives[]������
				index++;
			}			
		}		
		flyings = Arrays.copyOf(flyingLives,index);
		
		index = 0;
		Bullet[] bulletsLives = new Bullet[bullets.length];
		for(int i = 0;i < bullets.length;i++){
			Bullet bs = bullets[i];
			if(!bs.outOfBounds()){
				bulletsLives[index] = bs;//��Խ�磬����װ��flyingLives[]������
				index++;
			}			
		}
		bullets = Arrays.copyOf(bulletsLives,index);
		
		index = 0;
		BulletEnemy[] bulletenemysLives = new BulletEnemy[bulletenemys.length];
		for(int i = 0;i < bulletenemys.length;i++){
			BulletEnemy ba = bulletenemys[i];
			if(!ba.outOfBounds()){
				bulletenemysLives[index] = ba;//��Խ�磬����װ��flyingLives[]������
				index++;
			}			
		}
		bulletenemys = Arrays.copyOf(bulletenemysLives,index);
	
	}
	
	int score = 0;        //�÷�
	//�����ӵ������е���ײ
	public void bangAction(){
		for(int i = 0;i < bullets.length;i++){
			bang(bullets[i]);
		}
	}
	
	//һ���ӵ������е���ײ
	public void bang(Bullet b){
		int index = -1;//��ײ���˵�����
		for(int i = 0;i < flyings.length;i++){//�������еĵ���
			if(flyings[i].shootBy(b)){//�ж��Ƿ�ײ��
				index = i;                    //��¼��ײ���˵�����
				break;
			}
		}
		if(index != -1){//ײ����
			FlyingObject one = flyings[index]; 
			if(one instanceof Enemy){
				Enemy e = (Enemy)one;
				score += e.getScore();
			}
			if(one instanceof Award){
				Award a = (Award)one;
				int type = a.getType();
				switch(type){
				case Award.DOUBLE_FIRE:      //��������ֵ
					hero.addDoubleFire();    //Ӣ�ۻ����ӻ���
					break;
				case Award.LIFE:             //������
					hero.addLife();          //Ӣ�ۻ�����
					break;	
				case Award.TRIPLEFIRE:
					hero.addTripleFire();
					break;
				}
			}
			//��ײ������flyings�����е����һ��Ԫ�ؽ���
			FlyingObject t = flyings[index];
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = t;
			//���ݣ�ɾ�����һ��Ԫ��---����ײ�Ķ���
			flyings = Arrays.copyOf(flyings, flyings.length  - 1);
			
			int c=0;
			for(int i=0;i<bullets.length;i++) {
				if(bullets[i]==b)
					c=i;
			}
			bullets[c]=bullets[bullets.length-1];
			bullets[bullets.length-1]=b;
			bullets=Arrays.copyOf(bullets, bullets.length-1);

		}
	}
	int bossEnteredIndex=0;
	//BOSS�ǳ�
		public void BossenterAction(){
		
			if(score>=100&&score<200&&bossEnteredIndex==0) {
				Boss boss = new Boss();
				bosses = Arrays.copyOf(bosses,bosses.length + 1);
				bosses[bosses.length - 1] = boss;}
			if(score>=300&&bossEnteredIndex==0) {
				Boss boss = new Boss(2);
				bosses = Arrays.copyOf(bosses,bosses.length + 1);
				bosses[bosses.length - 1] = boss;
			}
		}
	
	
	
	
	
	
	public void checkGameOverAction(){
		if(isGameOver()){    //������Ϸ
			state = GAME_OVER;
			}
		for(int j = 0;j < bosses.length;j++) {
			if(bosses[j].isdead()&&score<300)
				//score+=100;
				state =NEXT;
			if(bosses[j].isdead()&&score>=300)
				state =GAME_OVER;
			    
		}
	}
	public boolean isGameOver(){
		for(int i = 0;i < flyings.length;i++){   //ײ���ˣ�
			if(hero.hit(flyings[i])){
				hero.subtractLife();          //������1
				hero.setDoubleFire(0);        //����ֵ����
				hero.setTripleFire(0);

				//��ײ֮�󣬽�������
				FlyingObject t = flyings[i];
				flyings[i] = flyings[flyings.length - 1];
				flyings[flyings.length  - 1] = t;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
		for(int i = 0;i <bosses.length;i++){   //ײ����boss��
			if(hero.hit(bosses[i])){
				hero.setLife();          
			}
		}
		for(int i = 0;i < bulletenemys.length;i++){   //ײ���ˣ�
			if(hero.hit(bulletenemys[i])){
				hero.subtractLife();          //������1
				hero.setDoubleFire(0);        //����ֵ����
				hero.setTripleFire(0);        
				
				BulletEnemy t = bulletenemys[i];
				bulletenemys[i] = bulletenemys[bulletenemys.length - 1];
				bulletenemys[bulletenemys.length  - 1] = t;
				bulletenemys = Arrays.copyOf(bulletenemys, bulletenemys.length - 1);
			}
			}
		//for(int i = 0;i < bullets.length;i++){   //ײ���ˣ�
			for(int j = 0;j < bosses.length;j++) {
				for(int i = 0;i < bullets.length;i++){
			    if(bosses[0].bosshit(bullets[i])){
				bosses[0].hurt();          //������1
				Bullet t = bullets[i];
				bullets[i] = bullets[bullets.length - 1];
				bullets[bullets.length  - 1] = t;
				bullets = Arrays.copyOf(bullets, bullets.length - 1);     }
			/*Bullet t = bullets[i];
			bullets[i] = bullets[bullets.length - 1];
			bullets[bullets.length  - 1] = t;
			bullets = Arrays.copyOf(bullets, bullets.length - 1);*/
			//return bosses[0].getLife() <= 0;
			}
			}
		return hero.getLife() <= 0;  //Ӣ�ۻ�����<=0,��Ϸ����
	}
	
	

	//����ִ�д���
	public void action(){
		MouseAdapter l = new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				if(state == RUNNING&&flag==0){  //����״̬��ִ��
				int x = e.getX();      //���Y����
				int y = e.getY();      //���X����
				if(x>0&&x<600&&y>0&&y<900)
				hero.moveTo(x, y);     //Ӣ�ۻ���������ƶ����ƶ�
				}
			}
			//���ĵ���¼�
			public void mouseClicked(MouseEvent e){
				flag=0;
				switch(state){
				case START:
					state = RUNNING;
					bossEnteredIndex=0;
					break;
				case GAME_OVER:
					hero = new Hero();//�����ֳ�
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					bosses = new Boss[0];
					bulletenemys = new BulletEnemy[0];
					score = 0;
					state = START;
					break;
				case NEXT:
					//hero = new Hero();//�����ֳ�
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					bosses = new Boss[0];
					bulletenemys = new BulletEnemy[0];
					score+=100;
					state = RUNNING;
					
					//bossEnteredIndex=0;
					break;
				
				}
			}
			public void mouseEntered(MouseEvent e){
				//if(state == PAUSE){
					//state = RUNNING;
				//}
			}
			public void mouseExited(MouseEvent e){
				//if(state == RUNNING){
					//state = PAUSE;
				//}
			}
		};
		
		this.addMouseListener(l);     //�����������¼�
		this.addMouseMotionListener(l); //��������ƶ��¼�
		
//		this.addKeyListener(new KeyAdapter(){
//			public void KeyPressed(KeyEvent e) {
//				int keycode = e.getKeyCode();
//				if((state == RUNNING))
//					switch (keycode) {
//					case KeyEvent.VK_UP: System.out.println("UP"); break;
//					case KeyEvent.VK_DOWN: hero.sety(hero.gety()+10); break;
//					case KeyEvent.VK_LEFT: hero.setx(hero.getx()+10); break;
//					case KeyEvent.VK_RIGHT: hero.setx(hero.getx()-10);break;
//					  
//					}
//			}
//		});
		
	   
		
		
		timer = new Timer();   //������ʱ������
		timer.schedule(new TimerTask(){
			public void run(){  //��ʱ�ɵ��Ǹ���--10������һ��
				if(state == RUNNING&&score<100){        //����״̬��ִ��
				allowable=0;	
				enterAction();
				stepAction();//��������һ��
				shootAction();//�ӵ��볡
				outOfBoundsAction();//ɾ��Խ�������
				bangAction();  //�ӵ������ײ
				checkGameOverAction();
				
				repaint(); 
				}   //�ػ�������paint()
				if(state == RUNNING&&score>=100&&score<200) {
					BossenterAction();
					bossEnteredIndex=1;
					flyings = new FlyingObject[0];
					shootAction();
					BossbulletstepAction();
					allowable=1;
					outOfBoundsAction();
					bangAction();
					checkGameOverAction();
					repaint();
				}
				if(state == RUNNING&&score>=200&&score<300) {
					bossEnteredIndex=0;
					allowable=0;
					enterAction();
					stepAction();//��������һ��
					shootAction();//�ӵ��볡
					outOfBoundsAction();//ɾ��Խ�������
					bangAction();  //�ӵ������ײ
					checkGameOverAction();
					repaint();
				}
				if(state == RUNNING&&score>=300) {
					BossenterAction();
					bossEnteredIndex=1;
					flyings = new FlyingObject[0];
					shootAction();
					BossbulletstepAction();
					allowable=1;
					outOfBoundsAction();
					bangAction();
					checkGameOverAction();
					repaint();
				}
				//repaint();
				
			}
		},intervel,intervel);
	}
		
	//��дpaint()����  g����ʾ����	
	public void paint(Graphics g){
		g.drawImage(background,0,0,null); //������ͼ
		paintHero(g);
		paintFlyingObjects(g);
		paintBullets(g);
		paintScore(g);      //���֣�����
		paintState(g);
		paintBulletEnemys(g);
		paintBoss(g);
		paintState(g);
	}
	//��״̬
	public void paintState(Graphics g){
		switch(state){
		case START:           //����״̬������ͼ
			g.drawImage(start,0,0,null);
			break;
		case PAUSE:           //��ͣ״̬����ͣͼ
			g.drawImage(pause,0,0,null);
			break;
		case GAME_OVER:       //����״̬������ͼ
			g.drawImage(gameover,0,0,null);
			break;
		case NEXT:           //��һ��״̬��ͼ
			g.drawImage(next,0,0,null);
			break;	
			
		}
	}
	public void paintHero(Graphics g){
		g.drawImage(hero.image,hero.x,hero.y,null);  //��Ӣ�ۻ�����
	}
	public void paintBoss(Graphics g){
		for(int i=0;i<bosses.length;i++) {
			Boss c= bosses[i];
		g.drawImage(c.image,c.x,c.y,null);
		}                                            //��boss������
	}
	
	public void paintFlyingObjects(Graphics g){
		for(int i =0;i < flyings.length;i++){
			FlyingObject f = flyings[i];
			g.drawImage(f.image,f.x,f.y,null);
		}
	}
	public void paintBullets(Graphics g){
		for(int i = 0;i < bullets.length;i++){
			Bullet b = bullets[i];
			g.drawImage(b.image,b.x,b.y,null);
		}
		
	}
	public void paintBulletEnemys(Graphics g){
		for(int i = 0;i < bulletenemys.length;i++){
			BulletEnemy a = bulletenemys[i];
			g.drawImage(a.image,a.x,a.y,null);
		}
		
	}
		
	public void paintScore(Graphics g){    //���֣�����
		g.setColor(new Color(0xFF0000));
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		g.drawString("SCORE: " + score,20,25);
		g.drawString("LIFE: " + hero.getLife(),20, 45);				                    
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("STAR WARS");  //���ڶ���
		ShootGame game = new ShootGame(); //���
		frame.add(game);   //�������ӵ�������W
		frame.addKeyListener(game);
		frame.setSize(WIDTH, HEIGHT);      //���ô��ڵĿ�͸�
		frame.setAlwaysOnTop(true);        //����һֱ��������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //����Ĭ�ϹرյĲ��������ڹر�ʱ�˳�����
		frame.setLocationRelativeTo(null);  //���ô��ڳ�ʼλ�ã�����)
		frame.setVisible(true);             //���ô���ɼ�
		
		game.action();   //����ִ��
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		if(state == RUNNING)
			switch (keycode) {
			case KeyEvent.VK_UP: if(hero.y>0)
				{hero.y-=10;flag=1;} break;
			
			
			case KeyEvent.VK_DOWN: if(hero.y<(ShootGame.HEIGHT-hero.height))
				{hero.y+=10; flag=1;}break;
			
			
			case KeyEvent.VK_LEFT: if(hero.x>0)
				{hero.x-=10; flag=1;}break;
			
			
			case KeyEvent.VK_RIGHT: if(hero.x<(ShootGame.WIDTH-hero.width))
				{hero.x+=10;flag=1;}break;
			
			case KeyEvent.VK_P: 
			    state=PAUSE;break;
			    
			//case KeyEvent.VK_J: 
				/*{
				if(shootIndex % 30 == 0){
				 Bullet[] bs = hero.shoot();//��ȡ�ӵ�����
				 bullets = Arrays.copyOf(bullets,bullets.length + bs.length);
				  System.arraycopy(bs, 0, bullets, bullets.length - bs.length,bs.length);
				  } 
				}   break;   */
			  
			}
		if(state== PAUSE)
			switch (keycode) {
			case KeyEvent.VK_O: 
			    state=RUNNING;
			}
			
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//int keycode = e.getKeyCode();
		//if(state == RUNNING)
			//switch (keycode) {
			//case KeyEvent.VK_P: 
			    //state=PAUSE;flag=1;repaint(); break;
			//case KeyEvent.VK_O: 
				//state=RUNNING; break;
			}

		
		
	}

	

	