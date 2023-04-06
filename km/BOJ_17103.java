package algorithm_with_java.math;

import java.util.Scanner;

public class BOJ_17103 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 백만까지의 소수 미리 구함
		int[] arr = new int[1000001];
		for(int i=2;i<=1000000;i++) {
			for(int j=i+i;j<=1000000;j+=i) {
				arr[j] = 1; // 0이면 소수 1이면 소수 아님
			}
		}
		int tc = input.nextInt();
		for(int i=0;i<tc;i++) {
			int num = input.nextInt();
			int answer = 0;
			for(int j=2;j<=num/2;j++) { // 해당 입력 받은 수의 절반 까지 검사하며
				// 골드바흐 파티션 이루는 거 있는지 확인
				if(arr[j]==0 && arr[num-j]==0) {
					answer += 1;
				}
			}
			System.out.println(answer);
		}
	}
}
