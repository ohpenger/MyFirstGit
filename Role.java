package test_four;

public class Role {
	public int life;
	public int experience;
	public int fight_number=0;
	public Role() {
		life=1000;
		experience=0;
	}
	public static void main(String[] args) {
		Role a=new Role();
		Role b=new Role();
		a.fight(b);
	}
	public  void fight(Role role) {
		
		int count1=0;
		int count2=0;
		while(this.life>=0&&role.life>=0) {
			fight_number++;
			this.life--;
			this.experience+=2;
			role.life-=2;
			role.experience+=3;
			if(this.experience/50>count1) {
				this.life++;
				count1++;
			}
			if(role.experience/50>count2) {
				role.life++;
				count2++;
			}
		}
		if(this.life>0) {
			System.out.println("��ʤ��Ϊ��ɫһ");
			this.printl();
		}
		else{
			System.out.println("��ʤ��Ϊ��ɫ��");
			role.printl();
		}
	}
	public String toString() {
		return "����ֵΪ:"+this.life+"   ����Ϊ:"+this.experience+"��ս����Ϊ��"+fight_number;
	}
	public void printl() {
	System.out.println(this.toString());
	}
}
