/**
 * 랜선 자르기 / 실버 2 / 60분
 * https://www.acmicpc.net/problem/1654
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1654 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		int k = parseInt(tmp[0]);
		int n = parseInt(tmp[1]);
		
		int[] arr = new int[k];
		
		long max = Integer.MIN_VALUE;
		
		for(int i=0;i<k;i++) { // K개의 랜선의 길이 입력 받고 배열에 넣음, 최대값 찾으면서
			arr[i] = parseInt(br.readLine());
			if(arr[i]>=max) {
				max = arr[i];
			}
		}
		
		long min = 1; // 최소길이가 1
		long mid = 0;
		while(min<=max) {
			mid = (max+min) / 2; // 중간값 설정
			
			long count = 0; // 개수
			for(int i=0;i<k;i++) {
				count += (arr[i]/mid);
			}
			
			if(count < n) { // 개수보다 적을 경우, 자르는 길이를 줄여야함
				max = mid-1;
			}
			else { // 개수보다 같거나 많을경우 , 자르는 길이 늘려야 함
				min = mid+1;
			}
		}
		
	}

}
