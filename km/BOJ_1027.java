/**
 *  고층 건물 / 골드 4 / 60분
 *  https://www.acmicpc.net/problem/1027
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1027 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = parseInt(br.readLine());
		int[] arr = new int[n]; // 건물 높이 정보를 담을 배열 
		int[] count = new int[n]; // 보이는 건물 개수를 담을 배열
		
		String[] tmp = br.readLine().split(" ");
		for(int i=0;i<n;i++) { // 초기화
			arr[i] = parseInt(tmp[i]);
		}
		
		for(int i=0;i<n-1;i++) {
			count[i]+=1;
			count[i+1]+=1;
			
			double slope = (arr[i+1]-arr[i]); // 시작 기준 기울기
			for(int j=i+2;j<n;j++) {
				double compare = (double)(arr[j]-arr[i])/(j-i); // 비교할 기울기
				if(compare > slope) { // 통과되면
					slope = compare; // 다음 기울기를 위해 바꿔주기
					count[i]+=1;
					count[j]+=1;
				}
			}
		}
		
		
		Arrays.sort(count);
		System.out.println(count[n-1]);
		

	}

}
