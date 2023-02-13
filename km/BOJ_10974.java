/**
 * 모든 순열 / 실버 3 / 20분
 * https://www.acmicpc.net/problem/10974
 */
package baekjoon;

import java.util.*;

public class BOJ_10974 {
	public static int[] arr;
	public static int[] selected;
 	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int num = input.nextInt();
		arr = new int[num];
		selected = new int[num+1]; // 순열에 들어가 있는지 체크할 배열 1이면 들어가 있음
		
		perm(num,0);
	}
	
	public static void perm(int num, int count) {
		if(count==num) { // 순열을 담는 배열이 다 채워지면 출력
			for(int i=0;i<arr.length;i++) {
				System.out.print(arr[i]+" ");
			}
			System.out.println();
		}
		else {
			for(int i=1;i<=num;i++) {
				if(selected[i]==1) // 이미 순열에 포함되어 있으면 다음 조건으로 넘어감
					continue;
				selected[i] = 1; // 들어와있지않으면 들어가고 갔음을 표시
				arr[count] = i; // 들어와 있지 않은 수를 순열 배열에 넣어줌
				perm(num,count+1); // 다시 다음번 순열에 올 수를 찾기 위해 재귀 호출
				selected[i] = 0; // 해당 순서에 들어간 순열은 완성되었으므로 다시 사용 해제
			}
		}
	}
}

