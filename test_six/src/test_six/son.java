package test_six;

public class son extends father{

	public son(double x, int n) {
		super(x, n);
	
	}
	public static void main(String[] args) {
		son a=new son(3,4);
		System.out.println(a.get());
	}

}
