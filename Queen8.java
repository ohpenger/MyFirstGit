package sevenchapter;

public class Queen8 {
private static final int MaxOfQueen8=8;
private static int[] cols=new int[8];
private static int number =0;
	public static void main(String[] args) {
		GetQueen(0);
	}
	private static void GetQueen(int i) {
		boolean[] rows=new boolean[MaxOfQueen8];
		for(int n=0;n<i;n++) {
			rows[cols[n]] =true;
			int subtaction=i-n;
			if(cols[n]-subtaction>=0)
				rows[cols[n]-subtaction]=true;
			if(cols[n]+subtaction<=7)
				rows[cols[n]+subtaction]=true;
		}
		for(int n=0;n<8;n++) {
			if(rows[n])
				continue;
			cols[i]=n;
			if(i<7)
				GetQueen(i+1);
			else {
				number++;
				printQueen();
			}
		}
		
	}
	private static void printQueen() {
		System.out.println("第"+number+"种解法");
		for(int i=0;i<8;i++) {
			for(int n=0;n<8;n++) {
				if(cols[n]==i)
					System.out.print("  1");
				else
					System.out.print("  *");
			}
		System.out.println("");
		}
		
	}

}
