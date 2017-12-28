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

/*bool sushu(int n) {
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
}*/
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
/*#include <iostream>
using namespace std;
int main()
{
	char c;
	int n;
	int flag = 0;
	while (cin >> c >> n)
	{
		if (c == '@') break;
		if (flag)
			cout << endl;
		flag = 1;
		for (int i = 1; i<n; i++)
		{
			for (int j = 1; j <= n - i; j++)
				cout << " ";
			cout << c;
			for (int t = 1; t <= 2 * (i - 1) - 1; t++)
				cout << " ";
			cout << c;
			for (int q = 1; q <= n - i; q++)
				cout << " ";
			cout << endl;
		}
		for (int p = 1; p <= 2 * n - 1; p++)
			cout << c;
		cout << endl;
	}
	return 0;
}*/
/*#include <iostream>
#include <algorithm>
#define maxn 100
using namespace std;
int main()
{
	long long  a[maxn];
	int T, N;
	int i;
	while (cin >> T)
	{
		for (i = 0; i<T; i++)
		{
			cin >> N;
			for (i = 0; i<N; i++)
				cin >> a[i];
			int m1 = 0; int m2 = 1;
			if (a[m1]>a[m2])
				swap(m1, m2);
			for (i = 2; i<N; i++)
			{
				if (a[i] <= a[m1])
				{
					m2 = m1;
					m1 = i;
				}
				else if (a[i]<a[m2])
					m2 = i;
			}
			cout << a[m1] + a[m2] << endl;
		}
	}
	return 0;
}*/


/*#include <iostream>
using namespace std;
int sushu(int n);
void main()
{
	int n, a;
	cin >> n;
	if (n>1)
	{
		a = sushu(n);
		if (a == 1)
			cout << "这个数不是素数";
		else
			cout << "这个数是素数";
	}
	system("pause");
}
int sushu(int n)
{
	int i;
	for (i = 2; i<n; i++)
	{
		if (n%i == 0)
			return 1;
	}
	return 0;
}*/
/*#include <iostream>
using namespace std;
int he(int a, int b);
int m;
void main()
{
	int i, n, p = 0;
	cin >> n;
	for (i = 1; i <= n; i++)
	{
		p += he(i, n);
	}
	cout << p;
}
int he(int a, int b)
{
	int c, d = 1, j = 1, e = 1, m;
	for (c = 1; c <= a; c++)
		d *= c;
	for (; j <= a; j++)
		e += j;
	m = d*e;
	return m;
}*/
void joint(char *first, char *second,char *third) {
	int count_first = 0;
	int count_second = 0;
	int n=0;
	for (int i=0;*(first+i) != '\0';i++)
		count_first++;
	for (int i = 0; *(second + i) != '\0'; i++)
		count_second++;
	for (int i = 0; i < count_first; i++) 
		*(third + i) = *(first + i);
	for (int i = count_first; i < count_first + count_second; i++){
		*(third + i) = *(second + n);
		n++;
		if (i == count_first + count_second - 1) {
			*(third + i + 1) = '\0';
		}
	}
	for (int i = 0; *(third + i) != '\0'; i++)
		cout << *(third + i)<<endl;
}
void main() {
	char a[20] ;
	char b[20] ;
	char c[20];
	cout << "请输入两个字符串：";
	cin >> a >> b;
	joint(a, b,c);
	for (int i = 0; c [ i] != '\0'; i++)
		cout << c[i];
	system("pause");
}