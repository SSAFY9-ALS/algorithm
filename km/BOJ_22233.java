/**
 * 가희와 키워드 / 실버 2 / 30분
 * https://www.acmicpc.net/problem/22233
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_22233 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] tmp = br.readLine().split(" ");
		int n = parseInt(tmp[0]);
		int m = parseInt(tmp[1]);
		HashSet<String> hs = new HashSet<>(); // 키워드 받을 집합
		
		for(int i=0;i<n;i++) {
			hs.add(br.readLine());
		}
		
		for(int i=0;i<m;i++) {
			tmp = br.readLine().split(","); // 글 입력 받음
			for(int j=0;j<tmp.length;j++) {
				if(hs.contains(tmp[j])) { // 키워드 가지고 있으면 제거
					hs.remove(tmp[j]);
				}
			}
			sb.append(hs.size()).append("\n"); // 제거후 사이즈 추가
		}
		
		System.out.println(sb);
	}

}
