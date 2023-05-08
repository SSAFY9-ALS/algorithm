import java.util.*;
import java.io.*;

/**
 * 
 * 이모티콘 / 골드 4 / 100분
 * https://www.acmicpc.net/problem/1027
 * 
 */
class Info {
	int screen_num;
	int clipboard_num;

	public Info(int screen_num, int clipboard_num) {
		super();
		this.screen_num = screen_num;
		this.clipboard_num = clipboard_num;
	}
}

public class Main {
	static int s;
	static int answer;
	static int[][] info;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = Integer.parseInt(br.readLine());
		info = new int[10001][10001];
		visited = new boolean[10001][10001];
		solution();
		System.out.println(answer);

	}

	public static void solution() {
		Queue<Info> q = new ArrayDeque<>();
		info[1][0] = 0;
		q.add(new Info(1, 0));
		visited[1][0] = true;
		while (!q.isEmpty()) {
			Info temp = q.poll();
			int screen_num = temp.screen_num;
			int clipboard_num = temp.clipboard_num;
			int time = info[screen_num][clipboard_num] + 1;
			if (!visited[screen_num][screen_num]) {
				q.add(new Info(screen_num, screen_num));
				// 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
				info[screen_num][screen_num] = time;
				visited[screen_num][screen_num] = true;
			}
			if (clipboard_num != 0 && !visited[screen_num + clipboard_num][clipboard_num]) {
				// 클립보드에 아무것도 없으면 붙여넣기 불가능
				q.add(new Info(screen_num + clipboard_num, clipboard_num));
				// 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
				info[screen_num + clipboard_num][clipboard_num] = time;
				visited[screen_num + clipboard_num][clipboard_num] = true;
			}
			if (screen_num - 1 > 0 && !visited[screen_num - 1][clipboard_num]) {
				q.add(new Info(screen_num - 1, clipboard_num));
				// 화면에 있는 이모티콘 중 하나를 삭제한다.
				info[screen_num - 1][clipboard_num] = time;
				visited[screen_num - 1][clipboard_num] = true;

			}
			if (screen_num + clipboard_num == s || screen_num - 1 == s) {
				// 화면의 수와 클립보드의 수 또는 스크린 수에서 1 뺸값이 s와 같은면 걸린 시간을 저장 후 리턴
				answer = time;
				return;
			}
		}
	}
}
