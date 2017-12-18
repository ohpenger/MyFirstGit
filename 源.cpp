#include<iostream>
using namespace std;
#include <regex>
#include<string>
#include <sstream>
#include <algorithm>
/*int main()
{
	int num;
	int sum = 0;
	int acount = 1;
	char a;
	cin >> num;
	while (num) {
		sum += (num % 8) * acount;
		num = num / 8;
		acount = acount * 10;
	}
	cout << sum;
	system("pause");
}*/

/*int main()
{
	char sum[20];
	char n;
	int num;
	int i = 0;
	cout << "请输入一个十进制数字:";
	cin >> num;
	while (num) {
		switch (num % 16) {
		case 1:n = '1'; break;
		case 2:n = '2'; break;
		case 3:n = '3'; break;
		case 4:n = '4'; break;
		case 5:n = '5'; break;
		case 6:n = '6'; break;
		case 7:n = '7'; break;
		case 8:n = '8'; break;
		case 9:n = '9'; break;
		case 10:n = 'a'; break;
		case 11:n = 'b'; break;
		case 12:n = 'c'; break;
		case 13:n = 'd'; break;
		case 14:n = 'e'; break;
		case 15:n = 'f'; break;
		}
		sum[i]= n;
		num = num/16;
		i++;
	}
	for (int n = i-1; n >= 0; n--)
		cout << sum[n];
	system("pause");
}*/
/*int main()
{
	int number;
	int multiply;
	int sum = 0;
	cin >> number;
	for (int n = 1; n <= number; n++) {
		multiply = 1;
		for (int i = 1; i <= n; i++) {
			multiply = multiply*i;
		}
		if (n % 2 == 0) {
			multiply = -multiply;
		}
		sum += multiply;
	}
	cout << sum;
	system("pause");
}*/
/*void main() {
	int sum = 1;
	for (int i = 0; i < 9; i++) {
		sum = (sum + 1) * 2;
	}
	cout << sum;
	system("pause");
}*/
/*void main() {
	int num[] = { 8,6,4,3,5,25,7,9 };
	int max, mix, ave,sum;
	sum = 0;
	max = mix = num[0];
	for (int i = 0; i < 8; i++) {
		if (num[i] > max)
			max = num[i];
		if (num[i] < mix)
			mix = num[i];
		sum += num[i];
	}
	ave = sum / 8;
	cout << "最大数：" <<max<< "最小数:" << mix << "平均数:" << ave << endl;
	system("pause");
}*/
/*void main() {
	smatch r;
	string x;
	cout << "请输入你需要模糊查找的内容:";
	cin >> x;;
	regex rx(x);
	string name[] = { "谢君宜","罗浩然","彭志鹏","王湘生","蒙栋禄","胡伟涛","谢俣姝","俞琳荟" };
	for (int i = 0; i < 8; i++) {
		if (regex_search(name[i], rx))
			cout << name[i]<<endl;
	}
	system("pause");
}*/
/*int main() {
	int yanghui[20][20];
	int n;
	cout << "请输入你要打印多少行杨辉三角:";
	cin >> n;
	yanghui[0][0] = 1;
	for (int i = 1; i < n; i++) {
		for (int m=0; m < i + 1; m++){		
			if (m == 0)
				yanghui[i][m] = 1;
			else if (m == i )
				yanghui[i][m] = 1;
			else
				yanghui[i][m]=yanghui[i - 1][m - 1] + yanghui[i - 1][m];
		}
	}
	for (int i = 0; i < n; i++) {
		for (int m = 0; m < i+1; m++) {
			cout << yanghui[i][m];
		}
		cout << "\n";
	}
	system("pause");
}*/

bool sushu(int n) {
	if (n == 2)
		return true;
	for (int i = 2; i < n; i++) {
		if (n%i == 0)
			return false;
	}
	return true;		
}

void main() {
	int n;
	int b;
	bool a;
	cout << "请输入你需要判断是否为素数的数:"<<endl;
	cin >> n;
	a=sushu(n);
	if (a)
		cout << "这是一个素数";
	else
		cout << "这不是一个素数"<<endl;
	if (n > 1 && n % 2 == 0) {
		for (int i = 1; i < n; i++) {
			if (sushu(i)) {
				if (n%i == 0) {
					b = n - i;
					if(sushu(b))
					cout << "这个偶数可以拆分为" << i << "和" << b<<"\n";
				}
			}
		}
	}
	system("pause");
}
/*void fun(int n) {
	if (n == 0)
		return;
	else
		fun(n / 2);
	cout << (n % 2);
}
void main() {
	int n;
	cout << "请输入一个十进制数";
	cin >> n;
	fun(n);
	system("pause");
}*/
/*int suml(int i) {
	static int sumnumber = 0;
	sumnumber += i;
	return sumnumber;
}
int multiply(int i){
	static int multiplynumber = 1;
	multiplynumber = multiplynumber*i;
	return multiplynumber;
}

void main() {
	int n;
	cout << "请输入N的值：";
	cin >> n;
	int sum = 0;
	for (int i = 1; i <= n; i++) {
		sum += multiply(i)*suml(i);
	}
	cout << "和为:" << sum << endl;
	system("pause");
}*/