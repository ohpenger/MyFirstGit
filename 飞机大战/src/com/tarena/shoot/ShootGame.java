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
//主程序测试类
public class ShootGame extends JPanel implements KeyListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600;  //窗口的宽
	public static final int HEIGHT = 900; //窗口的高
	//静态资源
	public static BufferedImage background; //背景图
	public static BufferedImage start;     //开始图
	public static BufferedImage pause;      //暂停图
	public static BufferedImage gameover;   //游戏结束图
	public static BufferedImage airplane;   //敌机图
	public static BufferedImage bee;        //蜜蜂图
	public static BufferedImage bullet;     //子弹图
	public static BufferedImage hero0;      //英雄机0图
	public static BufferedImage hero1;      //英雄机1图
	public static BufferedImage airspecious; //特殊敌机图
	public static BufferedImage bulletenemy; //敌人子弹图
	public static BufferedImage boss; //Boss图
	public static BufferedImage next; //下一关图
	public static BufferedImage buffer0; //BUFF种类
	public static BufferedImage buffer1;
	public static BufferedImage buffer2;
	public static BufferedImage boss2;  //第二关Boss
	public static BufferedImage boss3;  //第三关boss

	
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
	private int state = 0;   //当前状态
	
	private Hero hero = new Hero();
	//private Boss boss1 = new Boss();//英雄机
	private Bullet[] bullets = {};          //子弹数组
	private FlyingObject[] flyings = {};     //敌人数组
	private BulletEnemy[] bulletenemys = {};          //敌人子弹数组
	private Boss[] bosses = {};
	
	
	private Timer timer;
	private int intervel = 10;              //间隔时间：单位--毫秒
	
	
	//静态块
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
		int type = rand.nextInt(20);  //生成0到19的随机数
		if(type == 0){                //随机数为0，返回bee;否则返回敌机
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
	
	//敌人登场
	public void enterAction(){//10毫秒走一次
		flyEnteredIndex++;  //每10毫秒增1
		if(flyEnteredIndex % 40 == 0){
			FlyingObject obj = nextOne();
			flyings = Arrays.copyOf(flyings,flyings.length + 1);
			flyings[flyings.length - 1] = obj;//将敌人赋值给flyings数组的最后一个元素
		}
	}
	
	public void stepAction(){        //10毫秒走一次
		hero.step();                 //英雄机走一步
		for(int i = 0;i < flyings.length;i++){
			flyings[i].step();       //敌人走一步
		}
		for(int i = 0;i < bullets.length;i++){
			bullets[i].step();       //子弹走一步
		}
		for(int i = 0;i < bulletenemys.length;i++){
			bulletenemys[i].step();       //敌人子弹走一步
		}
	}
	int allowable =0;
	public void BossbulletstepAction() {
		if(allowable==0) {
			bulletenemys = new BulletEnemy[0];
		}
		hero.step(); 
		for(int i = 0;i < bullets.length;i++){
			bullets[i].step();       //子弹走一步
		}
		for(int i = 0;i < flyings.length;i++){
			flyings[i].step();       //敌人走一步
		}
		for(int i = 0;i < bosses.length;i++){
			bosses[i].step();       //boss走一步
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
	public void shootAction(){         //10毫秒走一次
		shootIndex++;                 //每10毫秒增1
		if(shootIndex % 30 == 0){     //300毫秒发射一次子弹			
			Bullet[] bs = hero.shoot();//获取子弹对象
			bullets = Arrays.copyOf(bullets,bullets.length + bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length,bs.length);
			
		}
		
		for(int i = 0;i < flyings.length;i++)  //特殊敌机发射子弹
		{if(flyings[i] instanceof Attack&&shootIndex % 100 == 0) {  //每1秒发射一颗子弹
			BulletEnemy[] ba = ((Airspecious) flyings[i]).shoot();
			bulletenemys = Arrays.copyOf(bulletenemys,bulletenemys.length + ba.length);
			System.arraycopy(ba, 0, bulletenemys, bulletenemys.length - ba.length,ba.length);
		}
			
			
		}
		for(int i = 0;i < bosses.length;i++)            //Boss发射子弹
		{
			if(bosses[i] instanceof Attack&&shootIndex % 200 == 0&&score<200) {        //每2秒发射一次子弹
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
	
	//删除越界飞行物
	public void outOfBoundsAction(){
		int index = 0;
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for(int i = 0;i < flyings.length;i++){
			FlyingObject f = flyings[i];
			if(!f.outOfBounds()){
				flyingLives[index] = f;//不越界，将其装入flyingLives[]数组中
				index++;
			}			
		}		
		flyings = Arrays.copyOf(flyingLives,index);
		
		index = 0;
		Bullet[] bulletsLives = new Bullet[bullets.length];
		for(int i = 0;i < bullets.length;i++){
			Bullet bs = bullets[i];
			if(!bs.outOfBounds()){
				bulletsLives[index] = bs;//不越界，将其装入flyingLives[]数组中
				index++;
			}			
		}
		bullets = Arrays.copyOf(bulletsLives,index);
		
		index = 0;
		BulletEnemy[] bulletenemysLives = new BulletEnemy[bulletenemys.length];
		for(int i = 0;i < bulletenemys.length;i++){
			BulletEnemy ba = bulletenemys[i];
			if(!ba.outOfBounds()){
				bulletenemysLives[index] = ba;//不越界，将其装入flyingLives[]数组中
				index++;
			}			
		}
		bulletenemys = Arrays.copyOf(bulletenemysLives,index);
	
	}
	
	int score = 0;        //得分
	//所有子弹与所有敌人撞
	public void bangAction(){
		for(int i = 0;i < bullets.length;i++){
			bang(bullets[i]);
		}
	}
	
	//一个子弹与所有敌人撞
	public void bang(Bullet b){
		int index = -1;//被撞敌人的索引
		for(int i = 0;i < flyings.length;i++){//遍历所有的敌人
			if(flyings[i].shootBy(b)){//判断是否撞上
				index = i;                    //记录被撞敌人的索引
				break;
			}
		}
		if(index != -1){//撞上了
			FlyingObject one = flyings[index]; 
			if(one instanceof Enemy){
				Enemy e = (Enemy)one;
				score += e.getScore();
			}
			if(one instanceof Award){
				Award a = (Award)one;
				int type = a.getType();
				switch(type){
				case Award.DOUBLE_FIRE:      //奖励活力值
					hero.addDoubleFire();    //英雄机增加火力
					break;
				case Award.LIFE:             //奖励命
					hero.addLife();          //英雄机增命
					break;	
				case Award.TRIPLEFIRE:
					hero.addTripleFire();
					break;
				}
			}
			//被撞敌人与flyings数组中的最后一个元素交换
			FlyingObject t = flyings[index];
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = t;
			//缩容，删除随后一个元素---即被撞的对象
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
	//BOSS登场
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
		if(isGameOver()){    //结束游戏
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
		for(int i = 0;i < flyings.length;i++){   //撞上了，
			if(hero.hit(flyings[i])){
				hero.subtractLife();          //生命减1
				hero.setDoubleFire(0);        //火力值清零
				hero.setTripleFire(0);

				//相撞之后，交换缩容
				FlyingObject t = flyings[i];
				flyings[i] = flyings[flyings.length - 1];
				flyings[flyings.length  - 1] = t;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
		for(int i = 0;i <bosses.length;i++){   //撞上了boss，
			if(hero.hit(bosses[i])){
				hero.setLife();          
			}
		}
		for(int i = 0;i < bulletenemys.length;i++){   //撞上了，
			if(hero.hit(bulletenemys[i])){
				hero.subtractLife();          //生命减1
				hero.setDoubleFire(0);        //火力值清零
				hero.setTripleFire(0);        
				
				BulletEnemy t = bulletenemys[i];
				bulletenemys[i] = bulletenemys[bulletenemys.length - 1];
				bulletenemys[bulletenemys.length  - 1] = t;
				bulletenemys = Arrays.copyOf(bulletenemys, bulletenemys.length - 1);
			}
			}
		//for(int i = 0;i < bullets.length;i++){   //撞上了，
			for(int j = 0;j < bosses.length;j++) {
				for(int i = 0;i < bullets.length;i++){
			    if(bosses[0].bosshit(bullets[i])){
				bosses[0].hurt();          //生命减1
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
		return hero.getLife() <= 0;  //英雄机的命<=0,游戏结束
	}
	
	

	//启动执行代码
	public void action(){
		MouseAdapter l = new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				if(state == RUNNING&&flag==0){  //运行状态下执行
				int x = e.getX();      //鼠标Y坐标
				int y = e.getY();      //鼠标X坐标
				if(x>0&&x<600&&y>0&&y<900)
				hero.moveTo(x, y);     //英雄机随着鼠标移动而移动
				}
			}
			//鼠标的点击事件
			public void mouseClicked(MouseEvent e){
				flag=0;
				switch(state){
				case START:
					state = RUNNING;
					bossEnteredIndex=0;
					break;
				case GAME_OVER:
					hero = new Hero();//清理现场
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					bosses = new Boss[0];
					bulletenemys = new BulletEnemy[0];
					score = 0;
					state = START;
					break;
				case NEXT:
					//hero = new Hero();//清理现场
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
		
		this.addMouseListener(l);     //处理鼠标操作事件
		this.addMouseMotionListener(l); //处理鼠标移动事件
		
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
		
	   
		
		
		timer = new Timer();   //创建定时器对象
		timer.schedule(new TimerTask(){
			public void run(){  //定时干的那个事--10毫秒走一次
				if(state == RUNNING&&score<100){        //运行状态下执行
				allowable=0;	
				enterAction();
				stepAction();//飞行物走一步
				shootAction();//子弹入场
				outOfBoundsAction();//删除越界飞行物
				bangAction();  //子弹与敌人撞
				checkGameOverAction();
				
				repaint(); 
				}   //重画，调用paint()
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
					stepAction();//飞行物走一步
					shootAction();//子弹入场
					outOfBoundsAction();//删除越界飞行物
					bangAction();  //子弹与敌人撞
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
		
	//重写paint()方法  g：表示画笔	
	public void paint(Graphics g){
		g.drawImage(background,0,0,null); //画背景图
		paintHero(g);
		paintFlyingObjects(g);
		paintBullets(g);
		paintScore(g);      //画分，画名
		paintState(g);
		paintBulletEnemys(g);
		paintBoss(g);
		paintState(g);
	}
	//画状态
	public void paintState(Graphics g){
		switch(state){
		case START:           //启动状态画启动图
			g.drawImage(start,0,0,null);
			break;
		case PAUSE:           //暂停状态画暂停图
			g.drawImage(pause,0,0,null);
			break;
		case GAME_OVER:       //结束状态画结束图
			g.drawImage(gameover,0,0,null);
			break;
		case NEXT:           //下一关状态画图
			g.drawImage(next,0,0,null);
			break;	
			
		}
	}
	public void paintHero(Graphics g){
		g.drawImage(hero.image,hero.x,hero.y,null);  //画英雄机对象
	}
	public void paintBoss(Graphics g){
		for(int i=0;i<bosses.length;i++) {
			Boss c= bosses[i];
		g.drawImage(c.image,c.x,c.y,null);
		}                                            //画boss机对象
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
		
	public void paintScore(Graphics g){    //画分，画命
		g.setColor(new Color(0xFF0000));
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		g.drawString("SCORE: " + score,20,25);
		g.drawString("LIFE: " + hero.getLife(),20, 45);				                    
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("STAR WARS");  //窗口对象
		ShootGame game = new ShootGame(); //面板
		frame.add(game);   //将面板添加到窗口中W
		frame.addKeyListener(game);
		frame.setSize(WIDTH, HEIGHT);      //设置窗口的宽和高
		frame.setAlwaysOnTop(true);        //设置一直在最上面
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置默认关闭的操作：窗口关闭时退出程序
		frame.setLocationRelativeTo(null);  //设置窗口初始位置（居中)
		frame.setVisible(true);             //设置窗体可见
		
		game.action();   //启动执行
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
				 Bullet[] bs = hero.shoot();//获取子弹对象
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

	

	