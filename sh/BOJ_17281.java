import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 야구 / 골드4 / 2시간
 * https://www.acmicpc.net/problem/17281
 */
public class BJ_17281_야구 {
	static List<Integer> sequence;
	static Integer[] tempArr;
	static int N, maxScore;
	static int[][] inning;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 이닝 수 : N
		N = Integer.parseInt(br.readLine());
		
		// 이닝 마다 얻는 결과
		inning = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 임시로 타순 담아놓는 배열
		tempArr = new Integer[8];
		
		// 방문
		visited = new boolean[9];
		// 이닝 시작
		sortSequence(0);
		
		System.out.println(maxScore);
	}
	// 타순 정하는 메소드
	static void sortSequence(int cnt) {
		if(cnt == 8) {
			int score = getScore();
			maxScore = Math.max(score, maxScore);
			return;
		}
		
		for (int i = 1; i < 9; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			tempArr[cnt] = i;
			sortSequence(cnt+1);
			visited[i] = false;
		}
	}
	
	static int getScore() {
		// 임시 배열을 리스트로 전환
		sequence = new ArrayList<>(Arrays.asList(tempArr));
		sequence.add(3, 0);
		// 결과 값
		int result = 0;
		// 타순
		int j = 0;
		// 타순대로 이닝 진행
		for (int i = 0; i < N; i++) {
			// out
			int outCount =0;
			// 주자ㅏㅏㅏ
			int[] home = new int[4];
			while(outCount < 3) {
				int index = sequence.get(j);
				int res = inning[i][index];
				
				// 안타, 2루타, 3루타
				if(res == 1 || res == 2 || res == 3 || res == 4) {
					home = go(home, res);
					result += home[3];
					home[3] = 0;
				}
				// 아웃
				else {
					outCount++;
				}
				// 다음 타순
				j = (j+1)%9;
			}
		}
		return result;
	}
	
	// 주자 진루하는 메소드
	static int[] go(int[] home, int safety) {
		for (int i = 0; i < safety; i++) {
			home[3] += home[2];
			home[2] = home[1];
			home[1] = home[0];
			home[0] = 0;
		}
		home[safety-1]++;
		return home;
	}
}
