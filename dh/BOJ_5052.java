import java.util.*;
import java.io.*;
/**
 * 
 * 전화번호 목록 / 골드 4 / 60분
 * https://www.acmicpc.net/problem/5052
 * 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int testcase = 0; testcase < t; testcase++) {
			int n = Integer.parseInt(br.readLine());
			String[] phone = new String[n];
			HashMap<String, String> map = new HashMap<>();
			for (int i = 0; i < n; i++) {
				phone[i] = br.readLine();
				map.put(phone[i], "dump");
				// 해당 번호를 map의 key값에 저장
			}
			boolean answer = true;
			out: for (String s : phone) {
				for (int i = 0; i < s.length(); i++) {
					if (map.containsKey(s.substring(0, i))) {
						// 각 키 값을 앞에서부터 확인하면서 같은 키가 있는지 확인
						// 시간 초과 해결
						answer = false;
						break out;
					}
				}
			}
			if (answer) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		}

	}
}
