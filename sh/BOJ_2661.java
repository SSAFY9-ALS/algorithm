package BackTracking;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 좋은수열 / 골드 4 / 2시간
 * https://www.acmicpc.net/problem/2661
 */
public class BJ_2661_좋은수열 {
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 길이 : N
		N = sc.nextInt();

		// 길이가 1이라면 1이 제일 작은 좋은 수열이므로 1을 출력하고 종료
		if(N == 1) {
			System.out.println(1);
			return;
		}

		// 주어진 숫자로 수열 만들기
		makeInput("");

		
		sc.close();
	}

	/** 수열 만드는 메소드*/
	static void makeInput(String result) {
		if(result.length() == N) {
			System.out.println(result);
			System.exit(0);
		} else {
			for (int i = 1; i <= 3; i++) {
				if(twoPointer(result + i)) {
					makeInput(result + i);
				}
			}
		}
	}

	/**포인터로 좋은수열인지 판별하는 메소드*/
	static boolean twoPointer(String str) {
		int length = str.length()/2;

		for (int i = 1; i <= length; i++) {
			if(str.substring(str.length() - i).equals(str.substring(str.length() - i*2, str.length() - i))) {
				return false;
			}
		}
		return true;
	}
}
