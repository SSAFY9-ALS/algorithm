import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 쿼드트리 / 실버1 / 17분
 * @author 소수현
 * https://www.acmicpc.net/problem/1992
 */
public class BJ_1992_쿼드트리 {
	
	static int N;
	static int[][] map;
	static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
			}
		}
		
		movie(0,0, N);
		
		System.out.println(sb);
	}
	
	/**영상을 4분할로 쪼개는 메소드*/
	public static void movie(int dx, int dy, int size) {
		// 종료조건 : 안에 있는 원소들이 다 같은 원소들이라면
		// 제일 첫번째에 있는 원소로 StringBuilder에 넣어준다.
		if(isEqual(dx, dy, size)) {
			sb.append(map[dx][dy]);
			return;
		}
		
		sb.append("(");
		movie(dx, dy, size/2);
		movie(dx, dy + size/2, size/2);
		movie(dx + size/2, dy, size/2);
		movie(dx + size/2, dy + size/2, size/2);
		sb.append(")");
	}
	
	/**안에 들어있는 원소들이 다 같은 애들인지 확인하는 메소드*/
	public static boolean isEqual(int dx, int dy, int size) {
		int check = map[dx][dy];
		for(int i = dx; i < dx + size; i++) {
			for(int j = dy; j < dy + size; j++) {
				if(check != map[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
