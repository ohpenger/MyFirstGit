package test_six;

public class father {
	public double x;
	public int n;
	static int count =1;
	public father(double x,int n) {
		this.x=x;
		this.n=n;
	}
	public double get() {
		double sum=0;
		int a=1;
		if(n%2!=0) {
			for(;count<=n;count++) {
				sum+=x/count;
			}
		}
		else {
			for(;a<=n;a++) {
				count=count*a;
				sum+=x/count;				
			}
		}
		return sum;
	}
}
