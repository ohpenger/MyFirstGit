package test_six;

public class triangle implements jisuan{
	public double border1;
	public double border2;
	public double border3;
	public triangle(double border1,double border2,double border3) {
		this.border1=border1;
		this.border2=border2;
		this.border3=border3;
	}
	public static void main(String[] args) {
		triangle a=new triangle(5,4,5);
		System.out.println("周长为："+a.perimeter());
		System.out.println("面积为:"+a.area());		
	}
	@Override
	public double perimeter() {
		double sum=0;
		sum=this.border1+this.border2+this.border3;
		return sum;
	}

	@Override
	public double area() {
		double s=this.perimeter()/2;
		double x=s*(s-this.border1)*(s-this.border2)*(s-this.border3);
		return Math.sqrt(x);
	}
	
}
