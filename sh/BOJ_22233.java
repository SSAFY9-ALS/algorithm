import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 가희와키워드 / 실버2 / 30분
 * @author 소수현
 * https://www.acmicpc.net/problem/22233
 */

public class BJ_22233_가희와키워드 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 가희가 메모장에 적은 키워드 개수 N
		int N = Integer.parseInt(st.nextToken());
		// 가희가 블로그에 쓴 글의 개수 M
		int M = Integer.parseInt(st.nextToken());
		
		// 메모장에 적은 키워드
		HashMap<String, Boolean> memos = new HashMap<>();
		
		
		for (int n = 0; n < N; n++) {
			memos.put(br.readLine(), false);
		}
		
		// 가희가 쓴 글과 관련된 키워드
		int count = 0;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), ",");
			while(st.hasMoreElements()) {
				String temp = st.nextToken();
				
				if(memos.containsKey(temp) && !memos.get(temp)) {
					memos.put(temp, true);
					count++;
				}
			}
			sb.append(N-count).append("\n");
		}
		System.out.println(sb);
	}
}
