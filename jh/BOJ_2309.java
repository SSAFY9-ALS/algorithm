package ssafy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 일곱 난쟁이 / 브론즈1 / 10분
 * https://www.acmicpc.net/problem/2309
 */

public class BOJ_2309 {
	static int[] nums;
	static int[] combi;
	static boolean flag = false;

	static void findCombination(int n, int r, int idx, int count, int sum) {
		if (r == 0) { // 7개 만큼 뽑았으면
			if (sum == 100 && !flag) { // 키의 합이 100이고, 처음 출력하는 것이라면
				for (int num : combi)
					System.out.println(num); // 값 출력
				flag = true; // 다음부터는 출력이 되지 않도록 flag 값 변경
			}
		} else { // 아직 덜 뽑았으면
			for (int i = idx; i < n; i++) { // 중복을 허용하지 않기 때문에 현재 인덱스 ~ n-1까지 봄
				combi[count] = nums[i]; // 조합 배열에 값 저장
				findCombination(n, r - 1, idx + 1, count + 1, sum + combi[count]); // 필요한 값들을 갱신해 다시 재귀 함수 호출
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		nums = new int[9]; // 9명 난쟁이 키 저장할 배열
		combi = new int[7]; // 조합 저장할 배열
		for (int i = 0; i < 9; i++)
			nums[i] = sc.nextInt(); // 난쟁이 키 입력
		Arrays.sort(nums); // 오름차순 정렬 -> 출력할 때 정렬되어 있어야 하기 때문에 미리 함
		findCombination(9, 7, 0, 0, 0); // 재귀 함수 호출
	}
}