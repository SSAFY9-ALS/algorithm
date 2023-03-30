import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 전화번호 목록 / 골드 4 / 2시간 + a
 * https://www.acmicpc.net/problem/5052
 */
public class BJ_5052_전화번호목록 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 개수 : T
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			// 전화번호의 수 : n
			int n = Integer.parseInt(br.readLine());

			// 전화번호
			HashMap<String, String> tels = new HashMap<>();
			LinkedList<String> list = new LinkedList<>();

			// 일관성 여부
			String result = "YES";
			
			// 전화번호 담기
			for (int i = 0; i < n; i++) {
				String tel = br.readLine();
				
				tels.put(tel, "hello");
				list.add(tel);
			}
			
			// 일관성 체크
			boolean isCheck = false;
			
			for (String key : list) {
				for (int i = 0; i < key.length(); i++) {
					if(tels.containsKey(key.substring(0, i))) {
						result = "NO";
						isCheck = true;
						break;
					}
				}
				if(isCheck) break;
			}

			sb.append(result);
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
