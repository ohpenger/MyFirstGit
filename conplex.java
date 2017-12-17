package test_four;

public class conplex {
	public double real_number;
	public double imaginary_number;
	public static void main(String[] args) {
		conplex a =new conplex(1.0,3.0);
		conplex b =new conplex(2.1,4.5);
		a.plus(b);
		a.printl();
		b.minus(1.0);
		b.printl();
	}
	private conplex(double real_number,double imaginary_number) {
		this.real_number=real_number;
		this.imaginary_number=imaginary_number;
	}
	public void plus(conplex a) {
		this.real_number=a.real_number+this.real_number;
		this.imaginary_number=a.imaginary_number+this.imaginary_number;
	}
	public void minus(conplex a) {
		this.real_number=a.real_number-this.real_number;
		this.imaginary_number=a.imaginary_number-this.imaginary_number;
	}
	public void plus(double a) {
		this.real_number=a+this.real_number;
	}
	public void minus(double a) {
		this.real_number=this.real_number-a;
	}
	public String toString() {
		return real_number+"+i"+imaginary_number;
	}
	public void printl() {
		System.out.println(this.toString());
	}
}
