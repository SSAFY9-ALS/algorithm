/**
 * A와 B 2 / 골드 5 / 60분
 * https://www.acmicpc.net/problem/12919
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_12919 {
	
	static String S;
	static String T;
	static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		T = br.readLine();
		
		dfs(S,T);
		System.out.println(answer);

	}
	private static void dfs(String s, String t) {
		if(s.length()==t.length()) {
			if(s.equals(t)) {
				answer=1;
			}
			return;
		}
		if(t.charAt(0)=='B') { // 첫번째 문자 B인경우 빼고 뒤집음
			String sub = t.substring(1); // 첫번째 문자 제거
			StringBuilder sb = new StringBuilder(sub);
			String changed = sb.reverse().toString();
			dfs(s,changed);
		}
		if(t.charAt(t.length()-1)=='A') { // 마지막 문자 A인경우 A뺌
			dfs(s,t.substring(0,t.length()-1));
		}
	}
	
	
	
	// S를 T로 만드는것 시간 초과 => 반대로 생각해보기 ㅠ
//	private static void check(String tmp,int anum, int bnum) {
//		if(anum>tAnum || bnum>tBnum) {
//			return;
//		}
//		else if(tmp.length()==T.length()) {
//			if(tmp.equals(T)) {
//				answer = 1;
//			}
//			return;
//		}
//		else {
//			if(bnum==tBnum) {
//				//A추가하는 경우
//				check(tmp+'A',anum+1,bnum);
//			}
//			else if(anum==tAnum) {
//				//B추가하는 경우
//				char[] add = new char[tmp.length()+1];
//				add[0]='B';
//				for(int i=1;i<tmp.length()+1;i++) {
//					add[i]=tmp.charAt(tmp.length()-i);
//				}
//				String btmp = String.copyValueOf(add);
//				check(btmp,anum,bnum+1);
//			}
//			else {
//				//A추가하는 경우
//				check(tmp+'A',anum+1,bnum);
//				//B추가하는 경우
//				char[] add = new char[tmp.length()+1];
//				add[0]='B';
//				for(int i=1;i<tmp.length()+1;i++) {
//					add[i]=tmp.charAt(tmp.length()-i);
//				}
//				String btmp = String.copyValueOf(add);
//				check(btmp,anum,bnum+1);
//			}
//		}
//		
//	}
}
