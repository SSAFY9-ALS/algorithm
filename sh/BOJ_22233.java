import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


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
		String[] memos = new String[N];
		boolean[] isHere = new boolean[N];
		for (int n = 0; n < N; n++) {
			memos[n] = br.readLine();
		}
		
		// 가희가 쓴 글과 관련된 키워드
		for (int m = 0; m < M; m++) {
			System.out.println("test");
			int count = 0;
			st = new StringTokenizer(br.readLine(), ",");
			while(st.hasMoreElements()) {
				String temp = st.nextToken();
				for (int n = 0; n < memos.length; n++) {
					if(memos[n].equals(temp) || isHere[n]) {
						isHere[n] = true;
						count++;
					}
				}
			}
//			count = N - count;
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}
}

