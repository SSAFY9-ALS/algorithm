import java.io.*;
import java.util.*;
/**
 * 
 * 야구 / 골드 4 / 70분
 * https://www.acmicpc.net/problem/17281
 * 
 */
public class Main {
	static int n;
	static int[][] innings;
	static int[] player;
	static boolean[] isSelected;
	static int score;
	static int answer;
	static int[] base;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		innings = new int[n][9];
		player = new int[10];
		isSelected = new boolean[10];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		perm(1);
		// 1칸부터 배정
		System.out.println(answer);
	}
	static void perm(int count) {
		if(count == 10) {
			// 다 채워지면 경기 시작
			playGame();
			answer = Math.max(answer, score);
			score = 0;
			return;
		}
		if(count == 4) {
			// 4번째는 1번 선수로 배정
			player[4] = 1;
			perm(count + 1);
			// 다음 타자 선택
			return;
		}
		for(int i = 2; i <= 9; i++) {
			// i번 선수 배정
			if(isSelected[i]) {
				continue;
			}
			player[count] = i;
			isSelected[i] = true;
			perm(count + 1);
			// 다음 타자 선택
			isSelected[i] = false;
		}
	}
	
	static void playGame() {
		int count = 1;
		for(int i = 0; i < n; i++) {
			// 이닝
			int out_count = 0;
			base = new int[3];
			while(out_count != 3) {
				int num = innings[i][player[count] - 1];
				if(num == 1) {
					score += calc(1);
				}
				else if(num == 2) {
					score += calc(1);
					score += calc(0);
				}
				else if(num == 3) {
					score += calc(1);
					score += calc(0);
					score += calc(0);
				}
				else if(num == 4) {
					score += calc(1);
					score += calc(0);
					score += calc(0);
					score += calc(0);
				}
				// 사람을 진출하고 빈 공간 또한 넣어 점수 계산
				else if(num == 0) {
					out_count++;
				}
				
				count = count % 9 + 1;
			}
		}
	}
	
	static int calc(int go) {
		int point = 0;
		if(base[2] == 1) {
			point = 1;
			// 3루에 사람이 있으면 1점
		}
		base[2] = base[1];
		base[1] = base[0];
		base[0] = go;
		// 한칸 미뤄주고 받은 인자 1루에 넣어주기
		return point;
	}
}
