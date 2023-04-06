import java.io.*;
import java.util.*;

/**
 * 
 * 거짓말 / 골드4 / 70분
 * https://www.acmicpc.net/problem/1043
 *
 */

public class Main {
	static int n;
	// 사람의 수
	static int m;
	// 파티의 수
	static int truePeopleSize;
	// 진실을 아는 사람의 수
	static int[][] party;
	// 파티에 오는 사람 모음

	static boolean[] peopleCheck;
	// 진실을 아는 사람을 체크하는 boolean 배열

	static boolean[] partyCheck;
	// 진실을 말할 수 있는지 확인하는 boolean 배열

	/*
	 * 1. 진실을 알고 있는 사람이 있는 파티에는 무조건 진실을 말할 수 밖에 없다. -> 진실 체크 2. 진실을 알고 있는 사람과 파티에 같이
	 * 있는 사람 또한 진실을 아는 사람이 된다. -> 진실 체크 3. 그 외 파티에는 과장된 이야기를 할 수 있다. 에러!
	 * 
	 * 추가 2.5. 같이 있는 사람이 진실을 알게 되면 다시 파티를 처음부터 돌며 확인해야 한다.
	 */

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		party = new int[m][];
		peopleCheck = new boolean[n + 1];
		partyCheck = new boolean[m];

		st = new StringTokenizer(br.readLine());
		truePeopleSize = Integer.parseInt(st.nextToken());
		for (int i = 0; i < truePeopleSize; i++) {
			peopleCheck[Integer.parseInt(st.nextToken())] = true;
			// 진실을 아는 사람을 체크 -> peopleCheck를 true로 변경
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int partyMemberSize = Integer.parseInt(st.nextToken());
			// 파티에 참석한 인원의 수 입력
			party[i] = new int[partyMemberSize];
			for (int j = 0; j < partyMemberSize; j++) {
				party[i][j] = Integer.parseInt(st.nextToken());
				// 파티에 참석한 멤버들의 숫자 입력
			}
		}

		out:while (true) {
			// 파티에 변화가 있으면 처음부터 파티를 탐색하며 확인
			boolean check = false;
			for (int i = 0; i < m; i++) {
				// 진실을 아는 사람의 파티를 진실만 말할 수 있도록 변경 -> 해당 partyCheck를 true로 변경
				for (int j = 0; j < party[i].length; j++) {
					int num = party[i][j];
					// 해당 파티의 파티원 번호 저장
					if (!partyCheck[i] && peopleCheck[num] == true) {
						partyCheck[i] = true;
						check = true;
					}
					if (check == true) {
						// 진실을 아는 사람이 파티에 있으면 그 파티의 멤버 모드 진실을 아는 사람으로 체크
						// -> 파티원의 peopleCheck를 true로 변경
						for (int k = 0; k < party[i].length; k++) {
							int e = party[i][k];
							peopleCheck[e] = true;
						}
						continue out;
						// 다음 파티 탐색
					}
				}
			}
			if (check == false) {
				// 파티체크에 변화가 없으면 break
				break;
			}
		}

		int answer = 0;
		for (int i = 0; i < m; i++) {
			if (partyCheck[i] == false) {
				// 과장된 이야기를 할 수 있는 파티 갯수 세기
				answer++;
			}
		}
		System.out.println(answer);
		// 과장된 이야기를 할 수 있는 파티 갯수 출력
	}
}
