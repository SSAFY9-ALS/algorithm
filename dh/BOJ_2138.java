import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 전구와 스위치 / 골드5 / 150분
 * https://www.acmicpc.net/problem/2138
 * 
 */
public class Main {
	static int n;
	static int count;
	static boolean check;
	static boolean[] start;
	static boolean[] end;
	static boolean[] first_on;
	static boolean[] first_off;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		start = new boolean[n];
		end = new boolean[n];

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1')
				start[i] = true;
		}
		s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1')
				end[i] = true;
		}
		// boolean 값으로 입력받기

		first_on = new boolean[n];
		first_off = new boolean[n];
		for(int i = 0; i < n;i++) {
			first_on[i] = start[i];
			first_off[i] = start[i];
		}
		calc(1, first_off);
		// 처음 스위치를 키지 않았을 때 count 계산
		int answer = Integer.MAX_VALUE;
		if (check) {
			answer = count;
			// 상태 변경이 가능하면 count 값 저장
		}
		count = 0;
		check = false;
		

		firstOnOff();
		// 처음 스위치 켜기
		calc(1, first_on);
		// 처음 스위치를 켰을 때 count 계산
		if (check) {
			answer = Math.min(answer, count);
		}
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		System.out.println(answer);
	}

	static void calc(int loc, boolean[] first) {
		if (loc == n - 1) {
			// 마지막 위치일 때 상태변경
			if (first[loc - 1] != end[loc - 1]) {
				first[loc - 1] = !first[loc - 1];
				first[loc] = !first[loc];
				count++;
			}
			if (first[loc] == end[loc]) {
				// 상태가 변경되었으면 check 변경
				check = true;
				return;
			}
			return;
		}

		if (first[loc - 1] != end[loc - 1]) {
			// 현재위치이전의 값이 다르면 변경
			first[loc - 1] = !first[loc - 1];
			first[loc] = !first[loc];
			first[loc + 1] = !first[loc + 1];
			count++;
		}
		calc(loc + 1, first);
		// 다음 위치 탐색
	}

	static void firstOnOff() {
		count++;
		first_on[0] = !first_on[0];
		first_on[1] = !first_on[1];
		// 처음 스위치를 누를 때 사용
	}

}