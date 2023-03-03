package algorithm_mar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 거짓말 / 골드4 / 50분
 * https://www.acmicpc.net/problem/1043
 */

public class BOJ_1043 {
	static boolean[] party; // 파티에서 진실을 말하는지, 거짓을 말하는지 저장하는 배열
	static HashSet<Integer> truth; // 진실을 아는 사람
	static ArrayDeque<Integer> check; // 진실을 아는 사람을 체크하는 큐
	static HashMap<Integer, ArrayList<Integer>> map; // key: 사람, value: 참여하는 파티로 이루어진 map
	
	// 어떤 진실을 말해야 하는 파티가 있을 때, 참여하는 사람을 체크하는 메서드
	static void checkPeople(int partyNo) {
		for(Integer key: map.keySet()) { // map의 key를 전부 탐색
			if(!truth.contains(key) && map.get(key).contains(partyNo)) { // 진실을 아는 사람 목록에 없었고, 그 파티에 참여한다면
				check.offer(key); // 큐에 넣어 다시 체크
			}
		}
	}
	
	// 진실을 말해야 하는 파티를 찾는 메서드
	static void findParty(int num) {
		ArrayList<Integer> list = map.get(num); // 진실을 아는 사람이 참여하는 파티 목록
		if(list != null) { // 참여 파티가 비어 있지 않을 때
			for(int i = 0; i < list.size(); i++) {
				if(!party[list.get(i)-1]) { // 이미 진실로 처리된 파티가 아니라면
					party[list.get(i)-1] = true; // 진실로 만들고
					checkPeople(list.get(i)); // 그 파티에 참여하는 사람들을 다시 확인
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		Integer.parseInt(st.nextToken()); // n 입력 -> 사용하지 않기 때문에 변수 저장 x
		int m = Integer.parseInt(st.nextToken()); // m 입력
		party = new boolean[m];
		st = new StringTokenizer(in.readLine());
		int c = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수 입력
		truth = new HashSet<>();
		check = new ArrayDeque<>();
		int no;
		for(int i = 0; i < c; i++) {
			no = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 번호 입력
			// 체킹을 위해 set과 queue에 add
			truth.add(no);
			check.add(no);
		}
		map = new HashMap<>();
		int k;
		for(int i = 0; i < m; i++) { // 파티 개수만큼 반복
			st = new StringTokenizer(in.readLine());
			k = Integer.parseInt(st.nextToken()); // 각 파티에 참여하는 사람의 수 입력
			for(int j = 0; j < k; j++) {
				no = Integer.parseInt(st.nextToken()); // 파티에 참여하는 사람 번호 입력
				if(map.containsKey(no)) // 번호가 map에 key로 존재한다면
					map.get(no).add(i+1); // 연결된 리스트에 파티 번호 add
				else { // 번호가 key로 존재하지 않다면
					map.put(no, new ArrayList<>()); // 연결된 리스트를 만들고
					map.get(no).add(i+1); // 그 리스트에 파티 번호 add
				}
			}
		}
		while(!check.isEmpty()) { // 체크해야 할 사람이 존재한다면
			no = check.poll(); // 하나를 빼와서
			findParty(no); // 진슬 말해야 하는 파티를 구하는 함수 호출
		}
		int result = 0; // 결과 변수
		for(boolean p: party) {
			if(!p) // 거짓을 말하는 파티라면
				result++; // 개수 1 증가
		}
		System.out.println(result); // 결과 출력
	}
}