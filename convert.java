package test_two;
import java.util.*;
public class convert {
		public static void main(String[] args) {
			@SuppressWarnings("unused")
			Scanner input =new Scanner(System.in);
			System.out.println("���������Ǯ��:");
			String s=input.nextLine();
			String arr[]=s.split("\\.");
			int[] decimalnumber = null;
			int[] number=new int[arr.length];
			int[] integernumber =new int[arr[0].length()];
			if(arr.length==2)
				decimalnumber =new int[arr[1].length()];
			for(int i=0;i<arr.length;i++) {
				number[i]=Integer.parseInt(arr[i]);						
			}			
			if(arr.length==2){
				int i=0;
				int n=0;
				while(number[0]!=0) {
					integernumber[i]=number[0]%10;
					number[0]=number[0]/10;
					i++;
				}
				while(number[1]!=0) {
					decimalnumber[n]=number[1]%10;
					number[1]=number[1]/10;
					n++;
				}
				System.out.print(getcn1(integernumber,arr[0].length()));
				System.out.println(getcn(decimalnumber,arr[1].length()));
				
			}
			else {
				int i=0;
				while(number[0]!=0) {
					integernumber[i]=number[0]%10;
					number[0]=number[0]/10;
					i++;
				}
				System.out.print(getcn1(integernumber,arr[0].length()));						
			}
		}


		private static String getcn(int[] decimalnumber, int length) {
			int n=length-1;
			String s ="";
			for(int i=length-1;i>=0;i--) {
				if(decimalnumber[n]==0) {
					n--;
					continue;
				}
				s+=getwen(decimalnumber[n])+getchar1(i);
				n--;
				}
			return s;
		}



		private static String getchar1(int i) {
			if(i==0)
				return "��";
			else 
			return "��";
		}


		public static String getcn1(int[] integernumber,int count) {
			int n=count-1;
			String s ="";
			for(int i=count-1;i>=0;i--) {
				if(integernumber[n]==0&&i==0) {
					n--;
					continue;
				}
				else if(integernumber[n]==0&&i!=0) {
					n--;
					s=s+"��";
					continue;
				}
				s+=getwen(integernumber[n--])+getchar(i);
				}
			return s;
			}

		private static String getwen(int i) {

			if(i==1)
				return "Ҽ";
			else if(i==2)
				return "��";
			else if(i==3)
				return "��";
			else if(i==4)
				return "��";
			else if(i==5)
				return "��";
			else if(i==6)
				return "½";
			else if(i==7)
				return "��";
			else if(i==8)
				return "��";
			else
				return "��";
		}


		private static String getchar(int i) {
			if(i==0)
				return "Ԫ";
			else if(i==1||i==5)
				return "ʮ";
			else if(i==2||i==6)
				return "��";
			else if(i==3||i==7)
				return "ǧ";
			else if(i==4)
				return "��";
			else
				return "";
				
		}
				 
		}

