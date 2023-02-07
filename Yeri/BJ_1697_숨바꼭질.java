package yeri_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 숨바꼭질 / 실버1 / 30분
 * https://www.acmicpc.net/problem/1697
 */
public class BJ_1697_숨바꼭질 {

	static int[] visited = new int[100001];
	static List<Integer> que = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		que.add(N);
		System.out.println(bfs(N, K));
	}

	static int bfs(int n, int k) {
		visited[n] = 1;
		while (!que.isEmpty()) {

			que.remove(0);
			if (n == k) {
				return visited[n] - 1;
			} else {
				if (n - 1 >= 0 && 0 == visited[n - 1]) {
					que.add(n - 1);
					visited[n - 1] = visited[n] + 1;
				}
				if (n != 0 && 2 * n <= 100000 && 0 == visited[2 * n]) {
					que.add(n * 2);
					visited[n * 2] = visited[n] + 1;
				}
				if (n + 1 <= 100000 && 0 == visited[n + 1]) {
					que.add(n + 1);
					visited[n + 1] = visited[n] + 1;
				}
				n=que.get(0);
			}
		}
		return -1;
	}
}
