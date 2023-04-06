/**
 * 숫자 카드 / 실버 5 / 15분
 * https://www.acmicpc.net/problem/10815
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_10815 {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = parseInt(br.readLine()); // 상근이가 가진 숫자의 개수
		String[] tmp = br.readLine().split(" "); // 숫자 카드 배열
		int[] numcard = new int[num];
		for(int i=0;i<num;i++) {
			numcard[i] = parseInt(tmp[i]);
		}
		Arrays.sort(numcard); // 이진 탐색을 위한 정렬
		
		int m = parseInt(br.readLine()); // 확인할 숫자 개수
		String[] checks = br.readLine().split(" ");
		
		for(int i=0;i<m;i++) {
			binarySearch(0, num-1, numcard, parseInt(checks[i])); // 각 원소마다 탐색
		}
		System.out.println(sb);
	}
	private static void binarySearch(int start, int end, int[] arr, int check) {
		if (start>end) { // start와 end가 바뀔동안 나오지 않았다면 존재 하지 않음
			sb.append(0).append(' ');
			return;
		}
		int mid = (start+end)/2; // 중간값 설정
		
		if(arr[mid]==check) { // 해당 중간값과비교 
			sb.append(1).append(' ');
			return;
		}
		else if(arr[mid]>check) { // 찾아야할 숫자가 시작점과 기준점 사이이면 끝점을 mid-1로 재설정 후 탐색
			binarySearch(start, mid-1, arr, check);
		}
		else { // 찾아야할 숫자가 기준점과 끝점 사이라면 시작점을 mid+1로 재설정 후 탐색
			binarySearch(mid+1, end, arr, check);
		}
	}
}
