package sixchapter;
import java.util.Scanner;
import java.util.ArrayList;
public class credit_card {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		long number;
		boolean c;
		System.out.println("ÇëÊäÈëÒøĞĞ¿¨ºÅÂë£º");
		number=input.nextLong();
		c=isValid(number);
		System.out.println(number+"    is   "+printx(c));		
	}

	private static String printx(boolean c) {
		if(c)
			return "valid";
		else
			return "invalid";
	}

	private static boolean isValid(long number) {
		int[] num=new int[16];
		int count=0;
		int sum=0;
		int acount;
		while(number!=0) {
			num[count]=(int)(number%10);
			number=number/10;
			count++;
		}
		acount=count-1;
		if(count>16||count<13)
			return false;
		if(!isvalidofprefix(num,count)) {
			return false;
		}
		acount=count-1;
		for(;acount>=0;acount=acount-2) {
			sum+=getDit(num,acount);}
		acount=count-2;
		for(;acount>=0;) {
			sum+=num[acount];
			acount=acount-2;
		}
		if(sum%10!=0)
			return false;
		return true;
	}

	private static int getDit(int[] num, int count) {
		int xp=2*num[count];
		int bp;
		if(xp>=10) {
			bp=xp%10;
			xp=xp/10;
			xp=xp+bp;
		}			
		return xp;
	}

	private static boolean isvalidofprefix(int[] num, int count) {
		if(num[count-1]==4||num[count-1]==5||num[count-1]==6)
			return true;
		else if(num[count-1]==3) {
			if(num[count-2]==7)
				return true;
		}	
		return false;
	}


	
}
