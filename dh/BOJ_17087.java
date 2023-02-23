import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		public static int gcd(int p, int q) {
			// 최대공약수 구하기 (유클리드 호제법)
			if (q == 0)
				return p;
			return gcd(q, p % q);
		 }
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int S = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i = 0; i<N;i++) {
			int temp = sc.nextInt();
			arr[i] = Math.abs(temp - S);
			// 수빈이와 동생의 거리 저장
		}
		
		int answer = arr[0];
		for(int i = 1; i < N; i++) {
			answer = gcd(answer, arr[i]);
			// 거리들의 최대 공약수 구하기
		}
		
		System.out.println(answer);
	}

}

