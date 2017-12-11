package sixchapter;

public class Dice {
	public static void main(String[] args) {
		int[] count=new int[13];
		int num1=0;
		int num2=0;
		for(int i=1;i<13;i++)
			count[i]=1;
		count[7]=count[11]=3;
		count[2]=count[3]=count[12]=4;
		num1=(int)(1+Math.random()*6);
		num2=(int)(1+Math.random()*6);
		if(count[num1+num2]==3) {
			System.out.println("You rolled"+num1+"+"+num2+"="+(num1+num2));
			System.out.print("You Win");
			}
		else {
			count[7]=4;
			while(count[num1+num2]!=3) {
				if(count[num1+num2]==3||count[num1+num2]==2) {
					System.out.println("You rolled"+num1+"+"+num2+"="+(num1+num2));
					System.out.print("You Win");
					break;
				}
				else if(count[num1+num2]>3) {
					System.out.println("You rolled"+num1+"+"+num2+"="+(num1+num2));
					System.out.println("You Wrong");
					break;
				}
				else if(count[num1+num2]==1||count[num1+num2]==2) {
					count[num1+num2]=count[num1+num2]+1;
					System.out.println("You rolled"+num1+"+"+num2+"="+(num1+num2));
					System.out.println("point is"+" "+(num1+num2));
					
					}
				num1=(int)(1+Math.random()*6);
				num2=(int)(1+Math.random()*6);
			}
			}
			
	}
}
