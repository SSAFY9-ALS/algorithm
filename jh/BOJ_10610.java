package algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 30 / 실버4 / 30분
 * https://www.acmicpc.net/problem/10610
 */

public class BOJ_10610 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.next(); // n 입력
		Integer[] nums = new Integer[n.length()]; // 입력값을 받을 배열
		StringBuilder sb= new StringBuilder(); // 결과를 출력할 StringBuilder
		int num, sum = 0;
		boolean flag = false;
		// 30의 배수 -> 무조건 1의 자리는 0이어야 하고, 1의 자리를 제외한 숫자가 3의 배수여야 함
		for(int i = 0; i < n.length(); i++) {
			num = Character.getNumericValue(n.charAt(i)); // 각 자리 숫자 입력
			nums[i] = num;
			sum += num; // 3의 배수 판별법을 적용하기 위함 -> 각 자리 숫자의 합이 3의 배수일 때
			if(num == 0) // 만약 0이 있다면 10의 배수는 만족하는 것
				flag = true;
		}
		Arrays.sort(nums, Collections.reverseOrder()); // 가장 큰 수를 구하는 것이기 때문에 내림차순 정렬
		if(flag && sum % 3 == 0) { // 0이 존재하고, 각 자리 숫자의 합이 3의 배수면
			for(int i = 0; i < nums.length; i++)
				sb.append(nums[i]); // 결과 출력을 위해 append
		}
		else // 아니면
			sb.append(-1); // -1 출력
		System.out.println(sb);
	}
}
