import java.util.*;
import java.awt.Point;
import java.io.*;

/**
 * 
 * 도로포장 / 골드 1 / 100분
 * https://www.acmicpc.net/problem/1162
 * 
 */

class Road implements Comparable<Road> {
	int end;
	long cost;
	int pavement;

	public Road(int end, long cost, int pavement) {
		super();
		this.end = end;
		this.cost = cost;
		this.pavement = pavement;
	}

	@Override
	public int compareTo(Road o) {
		return (int) (cost - o.cost);
	}

}

public class Main {
	static int n, m, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		ArrayList<Road>[] map = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			map[start].add(new Road(end, cost, 0));
			map[end].add(new Road(start, cost, 0));
		}

		long[][] dp = new long[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= k; j++) {
				dp[i][j] = Long.MAX_VALUE;
			}
		}
		PriorityQueue<Road> pq = new PriorityQueue<>();
		pq.offer(new Road(1, 0, k));
		dp[1][0] = 0;

		while (!pq.isEmpty()) {
			Road road = pq.poll();
			if (dp[road.end][k - road.pavement] < road.cost) {
				continue;
			}
			for (Road r : map[road.end]) {
				// 포장 x
				if (dp[r.end][k - road.pavement] > road.cost + r.cost) {
					dp[r.end][k - road.pavement] = road.cost + r.cost;
					pq.offer(new Road(r.end, dp[r.end][k - road.pavement], road.pavement));
				}
				
				// 포장 o
				if(road.pavement > 0 && dp[r.end][k-road.pavement + 1] > road.cost) {
					dp[r.end][k - road.pavement + 1] = road.cost;
					pq.offer(new Road(r.end, dp[r.end][k - road.pavement + 1], road.pavement - 1));
				}
			}
		}
		
		long answer = dp[n][0];
		for (int i = 1; i <= k; i++) {
			answer = Math.min(dp[n][i], answer);
			// 최소값 찾기
		}
		System.out.println(answer);
	}
}