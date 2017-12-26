package test_three;
import java.util.*;
public class identification_card {	
	public static StringBuilder idnumber =new StringBuilder();
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("请输入身份证信息：");
		Scanner input=new Scanner(System.in);
		String s=input.nextLine();
		idnumber.append(s);
		printl();
		int i =input.nextInt();
		if(i==1)
		testid(idnumber);
		else if(i==2)
		get_information(idnumber);
		else if(i==3) {
		convert();	
		}
		else {
			System.out.println("退出");
		}
			
		}
		
	private static void convert() {		
		idnumber.insert(6, 19);
		int i=getchar(idnumber);
		idnumber.append(i);
		System.out.println(idnumber);
	}

	private static int getchar(StringBuilder idnumber2) {
		int[] number=new int[17];
		int sum=0;
		int wi;
		for(int i=0;i<17;i++) {
			String s=String.valueOf(idnumber2.charAt(i));
			number[i]=Integer.parseInt(s);
			wi=2*(i-1)%11;
			sum+=wi*i;
		}
		sum=sum%11;
		return sum;
	}

	private static boolean isnew(StringBuilder idnumber2) {
		if(idnumber2.length()==18)
			return true;
		else
		return false;
	}
	private static void get_information(StringBuilder idnumber2) {
		if(isnew(idnumber2)) {
			int dict=Integer.parseInt(idnumber2.substring(0,6));
			int year=Integer.parseInt(idnumber2.substring(6,10));
			int month=Integer.parseInt(idnumber2.substring(10, 12));
			int day=Integer.parseInt(idnumber2.substring(12, 14));
			int sexnumber=Integer.parseInt(idnumber2.substring(14, 17));
			String sex;
			if(sexnumber%2==0)
				sex="女";
			else
				sex="男";
			System.out.println("地区编号为："+dict);
			System.out.println("出身年月日为："+year+"."+month+"."+day);
			System.out.println("性别："+sex);
		}
		else {
			int dict=Integer.parseInt(idnumber2.substring(0,6));
			int year=Integer.parseInt(idnumber2.substring(6,8));
			int month=Integer.parseInt(idnumber2.substring(8, 10));
			int day=Integer.parseInt(idnumber2.substring(10, 12));
			int sexnumber=Integer.parseInt(idnumber2.substring(12, 15));
			String sex;
			if(sexnumber%2==0)
				sex="女";
			else
				sex="男";
			System.out.println("地区编号为："+dict);
			System.out.println("出身年月日为："+year+"."+month+"."+day);
			System.out.println("性别："+sex);
		}
		
	}
	private static void testid(StringBuilder idnumber2) {
		if(isnew(idnumber2)) {
			int year=Integer.parseInt(idnumber2.substring(6,10));
			int month=Integer.parseInt(idnumber2.substring(10, 12));
			int day=Integer.parseInt(idnumber2.substring(12, 14));
			if(year<1900||year>2017) 
				System.out.println("年份出现错误");
			if(month<0||month>12)
				System.out.println("月份出现错误");
			if(day<0||day>31)
				System.out.println("日期出现错误");
			else if((year>1900&&year<=2017)&&(month>0&&month<=12)&&(day>0&&day<=31))
				System.out.println("未发现错误");
		}
		else {
			int year=Integer.parseInt(idnumber2.substring(6,8));
			int month=Integer.parseInt(idnumber2.substring(8, 10));
			int day=Integer.parseInt(idnumber2.substring(10, 12));
			if(year<0) 
				System.out.println("年份出现错误");
			if(month<0||month>12)
				System.out.println("月份出现错误");
			if(day<0||day>31)
				System.out.println("日期出现错误");
			else if((year>1900&&year<=2017)&&(month>0&&month<=12)&&(day>0&&day<=31))
				System.out.println("未发现错误");
		}
		
	}

	public static void printl() {
		System.out.println(" _______________________________________");
        System.out.println("|    0.退出                 			|");
        System.out.println("|    1.检验身份证信息的真伪    	        |");
        System.out.println("|    2.获取真实身份证的信息   	        |");
        System.out.println("|    3.将15位旧身份证转换成18位的新身份证   |");
        System.out.println("|_______________________________________|");
	}
	
	
}
