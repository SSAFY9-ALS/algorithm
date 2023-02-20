
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A와 B2 / 골드5 / 1시간
 * https://www.acmicpc.net/problem/12919
 */
public class BJ_12919_A와B2 {
	
	static String S, T;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		T = br.readLine();
		
		StringBuilder sb = new StringBuilder();
		sb.append(T);

		AB(sb, T.length(), T);
		
		System.out.println(0);
		
	}
	
	public static void AB(StringBuilder next, int len, String before) {
		// 종료조건 : 문자열의 길이가 시작 문자열의 길이와 같을 때 시작 문자열과 같은 문자열인지 확인
		if(len == S.length()) {
			if(next.toString().equals(S)) {
				System.out.println(1);
				System.exit(0);
			}
			return;
		}

		/*
		시작 문자열에서 마지막 문자열까지 구하려면 시간초과가 걸림 -> 마지막 문자열에서 문자열을 빼면서 시작 문자열을 찾는게 더 빠름
		Step1. A로 시작하는 문자열은 마지막 문자열이 A일 때는 마지막 문자열을 그냥 뗴도 됨 하지만 마지막 문자열이 B일 때 시작 문자열이 아니라면 B일 때는 문자열이 반대로 뒤집어지는 경우 이므로 될 수가 없음
		Step2. B로 시작하는 문자열은 마지막 문자열이 A일 때는 뒤집어서 떼거나 그냥 떼거나 둘 다 가능!
		Step3. B로 시작하는 문자열의 마지막 문자열이 B일 때는 뒤집어서 떼기만 가능
		 */
		char last = next.charAt(next.length()-1);
		before = next.toString();
		if(next.toString().charAt(0) == 'A') {
			if(last == 'A') {
				AB(next.delete(next.length()-1, next.length()), len - 1, before);
			}
			// 마지막이 B일 때 만들어질 수 있는 문자열이 아니므로 return함
			else {
				return;
			}
		} else {
			if(last == 'A') {
				AB(next.delete(next.length()-1, next.length()), len - 1, before);
				// 이미 next에서 마지막 문자열을 지운 상태이므로 다시 이전의 상태로 돌림
				next.replace(0, before.length(), before);
				before = next.toString();
				// 문자열 마지막을 뗄 때 뒤집고 뺌
				AB(next.reverse().delete(next.length()-1, next.length()), len - 1, before);
			} else {
				AB(next.reverse().delete(next.length()-1, next.length()), len - 1, before);
			}
		}
	}
}
