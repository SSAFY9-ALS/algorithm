import java.util.Scanner;

public class BJ_2609_최대공약수와최소공배수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		if (a<b) {
			int temp = a;
			a = b;
			b = temp;
		}
		int max = 1;
		int min=a;
		for(int i = b; i > 0; i--) {
			if((a%i==0)&&(b%i==0)) {
				max = i;
				break;
			}
		}
		a = a / max;
		b = b / max;
		
		min = max * a * b;
		
		System.out.println(max);
		System.out.println(min);
		
	}
}
