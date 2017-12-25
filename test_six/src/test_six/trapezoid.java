package test_six;

public class trapezoid implements jisuan{
	public double border1;
	public double border2;
	public double border3;
	public double border4;
	public trapezoid(double border1,double border2,double border3,double border4) {
		this.border1=border1;
		this.border2=border2;
		this.border3=border3;
		this.border4=border4;
	}
	public static void main(String[] args) {
		trapezoid a=new trapezoid(5,3,9,5);
		System.out.println("周长为："+a.perimeter());
		System.out.println("面积为:"+a.area());			
	}
	@Override
	public double perimeter() {
		double sum=this.border1+this.border2+this.border3+this.border4;
		return sum;
	}
	@Override
	public double area() {
		double a=this.border3-this.border2;
		double b=(a+this.border1+this.border4)/2;
		double c=Math.sqrt(b*(b-this.border1)*(b-this.border4)*(b-a));
		double h=2*c/a;
		return (this.border2+this.border3)*h/2;
	}
}
