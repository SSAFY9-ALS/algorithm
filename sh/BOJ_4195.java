package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 친구 네트워크 / 골드 2 / 1시간
 * https://www.acmicpc.net/problem/4195
 */
public class BJ_4195_친구네트워크 {
	static int[] parents;
	static int N;
	static HashMap<Integer, Integer> setCnt;
	
	static void makeSet() {
		for (int i = 1; i < N+1; i++) {
			parents[i] = i;
		}
	}
	static int findSet(int a) {
		if (parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		set(aRoot, bRoot);
		return true;
	}
	
	// 친구관계 합쳐주는 메소드
	static void set(int aRoot, int bRoot) {
		// 이미 친구관계에 있는 애들이라면 둘의 친구관계를 합침
		if(setCnt.containsKey(aRoot) && setCnt.containsKey(bRoot)) {
			int aVal = setCnt.get(aRoot);
			int bVal = setCnt.get(bRoot);
			setCnt.put(aRoot, aVal + bVal);
			setCnt.remove(bRoot);
		}
		// 한 친구만 이미 친구관계가 형성 되어있다면 그 친구의 친구들까지 합쳐서 관계 합침
		else if(setCnt.containsKey(aRoot)) {
			int aVal = setCnt.get(aRoot);
			setCnt.put(aRoot, aVal + 1);
		} else if(setCnt.containsKey(bRoot)){
			int bVal = setCnt.get(bRoot);
			setCnt.put(aRoot, bVal + 1);
			setCnt.remove(bRoot);
		}
		// 둘 다 없다면 그냥 새로 생성해서 친구관계 2 추가
		else {
			setCnt.put(aRoot, 2);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int result = 0;
			
			// 친구 관계 수 : F
			int F = Integer.parseInt(br.readLine());
			
			N = F*2;
			parents = new int[N+1];
			makeSet();
			
			// 친구 번호
			int friendNum = 0;
			
			// 친구
			HashMap<String, Integer> friends = new HashMap<>();
			
			setCnt = new HashMap<>();
			
			for (int f = 0; f < F; f++) {
				st = new StringTokenizer(br.readLine());
				
				String a = st.nextToken();
				String b = st.nextToken();
				
				// 이미 친구관계에 포함이 되어있다면
				if(!friends.containsKey(a)) {
					friendNum++;
					friends.put(a, friendNum);
				}
				// 이미 친구관계에 포함이 되어있지 않다면 값 부여
				if(!friends.containsKey(b)) {
					friendNum++;
					friends.put(b, friendNum);
				}
				
				union(friends.get(a), friends.get(b));
				
				result = setCnt.get(findSet(friends.get(a)));
				sb.append(result).append("\n");
			}
			
		}
		System.out.println(sb);
	}
}
