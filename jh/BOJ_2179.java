package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashMap;

/**
 * 비슷한 단어 / 골드4 / 30분
 * https://www.acmicpc.net/problem/2179
 */

public class BOJ_2179 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); // n 입력
		LinkedHashMap<String, Object[]> map = new LinkedHashMap<String, Object[]>(); // 접두사 정보를 저장할 map
		HashSet<String> dup = new HashSet<String>(); // 중복 단어를 체크할 set
		StringBuilder sb; // substring을 위한 StringBuilder
		String sen, sub, result = "";
		int max = Integer.MIN_VALUE; // 최대 접두사 길이 확인용 변수
		Object[] temp;
		for(int i = 0; i < n; i++) {
			sen = in.readLine(); // 단어 입력
			if(dup.contains(sen)) // 이미 입력된 단어면
				continue; // 넘어감
			dup.add(sen); // 아니면 단어 목록에 add
			sb = new StringBuilder(sen); // substring을 위한 sb
			for(int j = sb.length(); j > 0; j--) { // 접두사를 구하기 위해 반복 -> 최대 길이 접두사를 구하는 것이 목표이기 때문에 뒤에서부터 탐색
				sub = sb.substring(0, j);
				if(map.containsKey(sub)) { // 이미 map에 key로 존재하는 접두사면
					temp = map.get(sub); // 정보를 불러옴
					if((int)temp[0] == 1) { // 한 번만 등장한 접두사면 -> 가장 먼저 등장한 S와 T를 구하기 때문에 map 값에 현재 단어를 추가해야 함
						map.put(sub, new Object[] {2, temp[1], sb.toString()}); // key값에 연결된 value에 현재 단어 추가
						if(j > max) { // 현재 접두사가 지금까지의 최대 길이보다 길다면
							result = sub; // 현재 접두사 result로 갱신
							max = sub.length(); // 최대 길이 현재 접두사로 갱신
						}
					}
					break; // 최대 길이만 탐색하기 때문에 이 접두사가 map에 등장하면 이후의 것 볼 필요가 없음
				}
				else { // 아직 등장하지 않은 단어면
					map.put(sub, new Object[] {1, sb.toString()}); // map에 추가 -> 두 번째 등장한 단어는 아직 모르기 때문에 현재 단어만 넣어줌
				}
			}
		}
		System.out.println(map.get(result)[1]); // 해당 접두사가 처음 등장한 단어 출력
		System.out.println(map.get(result)[2]); // 해당 접두사가 두 번째로 등장한 단어 출력
	}
}
