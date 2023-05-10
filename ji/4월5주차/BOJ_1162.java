package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 도로포장 / 골드 1 / 180분
 * @author 민정인
 * https://www.acmicpc.net/problem/1162
 */

public class BOJ_1162 {
	static int n, m, k;
	static class Pair implements Comparable<Pair>{
		int to;
		long val;
		int count;
		public Pair(int to, long val, int count) {
			this.to = to;
			this.val = val;
			this.count = count;
		}
		@Override
		public int compareTo(Pair o) {
			return (int) (this.val - o.val);
		}
	}
	static ArrayList<Pair>[] adj;
	static long[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n+1];
		dp = new long[n+1][k+1];
		for(int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
			Arrays.fill(dp[i], Long.MAX_VALUE);
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			adj[from].add(new Pair(to, val, 0));
			adj[to].add(new Pair(from, val, 0));
		}
		dijkstra();
		long result = Long.MAX_VALUE;
		for(int i = 0; i <= k; i++) {
			result = Math.min(result, dp[n][i]);
		}
		System.out.println(result);
	}
	static void dijkstra() {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(1, 0, 0));
		dp[1][0] = 0;
		while(!pq.isEmpty()) {
			Pair pr = pq.poll();
			if(pr.val > dp[pr.to][pr.count]) continue;
			for(Pair p : adj[pr.to]) {
				long nVal = p.val + pr.val;
				if(nVal < dp[p.to][pr.count]) {
					dp[p.to][pr.count] = nVal;
					pq.add(new Pair(p.to, nVal, pr.count));
				}
				if(pr.count < k && pr.val < dp[p.to][pr.count + 1]) {
					dp[p.to][pr.count+1] = pr.val;
					pq.add(new Pair(p.to, pr.val, pr.count + 1));
				}
			}
		}
	}
}
