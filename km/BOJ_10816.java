/**
 * 숫자 카드 2 / 실버 4 / 60분
 * https://www.acmicpc.net/problem/10816
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_10816 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int num = parseInt(br.readLine());
		
		int[] arr = new int[num];
		String[] tmp = br.readLine().split(" ");
		for(int i=0;i<num;i++) { // 숫자 배열 입력 받기
			arr[i] = parseInt(tmp[i]); 
		}
		
		Arrays.sort(arr); // 이분 탐색을 위한 정렬
		
		int m = parseInt(br.readLine());
		tmp = br.readLine().split(" ");
		
		for(int i=0;i<m;i++) {
			sb.append(upperBound(arr,parseInt(tmp[i])) - lowerBound(arr, parseInt(tmp[i]))).append(" ");
		}
		System.out.println(sb);
	}
	
	private static int lowerBound(int[] arr, int key) {
		int start = 0;
		int end = arr.length; // 기본 시작점, 끝점 설정
		
		// start와 end가 같아질때 까지 반복
		while(start<end) {
			int mid = (start + end) / 2; // 중간위치
			
			/**
			 * key 값이 중간점 보다 작을 경우 
			 * 중복 원소에 대해 왼쪽으로 탐색하도록 끝점을 내린다.
			 */
			if(key<=arr[mid]) {
				end = mid;
			}
			else {
				start = mid + 1;
			}
		}
		return start;
	}
	
	private static int upperBound(int[] arr, int key) {
		int start = 0;
		int end = arr.length;
		
		while(start<end) {
			int mid = (start + end)/2;
			
			if(key < arr[mid]) {
				end = mid;
			}
			else {
				start = mid + 1;
			}
		}
		return start;
	}
}
