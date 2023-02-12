/**
 * 일곱 난쟁이 / 브론즈 1 / 40분
 * https://www.acmicpc.net/problem/2309
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_2309 {
	
	static int[] arr; // 완성된 일곱 난쟁이를 담을 배열
	static int[] nums; // 9명의 난쟁이를 받을 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new int[7];
		nums = new int[9];
		int count = 0; // 완성된 난쟁이를 채우기 위해 배열에 접근하기 위한 인덱스
		int tmp1=-1; // 진짜 난쟁이가 아닌 난쟁이의 키
		int tmp2=-1; // 진짜 난쟁이가 아닌 난쟁이의 키
		int sum = 0; // 9명의 난쟁이 키의 합
		
		for(int i=0;i<9;i++) { // 9명의 난쟁이의 키를 입력 받음
			nums[i] = parseInt(br.readLine());
			sum += nums[i];
		}
		
		
		for(int i=0;i<nums.length-1;i++) {
			for(int j=i+1;j<nums.length;j++) {
				if(sum-nums[i]-nums[j]==100) {
					// 두명의 난쟁이의 키를 뺴서 100이되면
					// 그 두명의 난쟁이가 가짜 난쟁이
					tmp1 = nums[i];
					tmp2 = nums[j];
				}
			}
		}
		
		for(int i=0;i<nums.length;i++) {
			if(nums[i]!=tmp1 && nums[i]!=tmp2) {
				// 가짜 난쟁이가 아닌 난쟁이만 배열에 넣음
				arr[count++] = nums[i];
			}
		}
		
		Arrays.sort(arr); // 오름 차순 정렬
		for(int i=0;i<arr.length;i++) {
			// 출력!
			System.out.println(arr[i]);
		}
	}

}
