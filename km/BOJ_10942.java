/**
 * 팰린드롬? / 골드 4 / 20분
 * https://www.acmicpc.net/problem/10942
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_10942 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = parseInt(br.readLine());
		int[] arr = new int[n];
		
		String[] tmp = br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i] = parseInt(tmp[i]);
		}
		
		int m = parseInt(br.readLine());
		
		for(int k=0;k<m;k++) {
			tmp = br.readLine().split(" ");
			int start = parseInt(tmp[0])-1;
			int end = parseInt(tmp[1])-1;
			int cnt = end - start + 1;
			boolean check = true;
			for(int i=0;i<cnt/2;i++) { // 팰린드롬은 앞으로 읽나 뒤로 읽나 같은 수를 말함
				// 읽어야하는 부분에서 맨 앞과 맨 뒤에서 동시에 출발해 다른 부분이 있으면 팰린드롬 아님
				// 홀수개의 경우 가운데 부분은 상관없음
				if(arr[start+i] != arr[end-i]) {
					check = false;
					break;
				}
			}
			//System.out.println();
			if(check) {
				sb.append(1).append("\n");
			}
			else {
				sb.append(0).append("\n");
			}
		}
		
		System.out.println(sb);

	}

}
