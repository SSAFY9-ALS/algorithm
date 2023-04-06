import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 톱니바퀴 / 골드5 / 120분
 * https://www.acmicpc.net/problem/14891
 * 
 */
public class Main {
	static int[][] gear;
	static int k;
	static int[] rot;
	static boolean[] check;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new int[5][8];
		for(int i = 1; i <= 4; i++) {
			String s = br.readLine();
			for(int j = 0; j < 8; j++) {
				gear[i][j] = s.charAt(j) - '0';
			}
		}
		k = Integer.parseInt(br.readLine());
		for(int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			rot = new int[5];
			// 어느 톱니바퀴가 어느방향으로 돌리는지 저장하는 배열
			check = new boolean[5];
			// 확인 여부 저장 배열
			rotation(num, dir);
			// rot배열에 값을 저장하기 위해 rotation 메서드 실행
			init();
			// 돌리기
		}
		score();
		System.out.println(result);
	}

	static void rotation(int num, int dir) {
		rot[num] = dir;
		check[num] = true;
		// num번째 톱니바귀를 dir방향으로 돌리는 정보를 저장 후 방문 체크
		if(num == 1) {
			if(!check[num+1] && gear[num][2] != gear[num+1][6]) {
				rotation(num+1, -dir);
				// 2번째 톱니 확인
			}
		}
		else if(num == 2 || num == 3) {
			if(!check[num+1] && gear[num][2] != gear[num+1][6]) {
				rotation(num+1, -dir);
				// 오른쪽 톱니 확인
			}
			if(!check[num-1] && gear[num-1][2] != gear[num][6]) {
				rotation(num-1, -dir);
				// 왼쪽 톱니 확인
			}
		}
		else if(num == 4) {
			if(!check[num-1] && gear[num-1][2] != gear[num][6]) {
				rotation(num-1, -dir);
				// 왼쪽 톱니 확인
			}
		}
	}
	static void init() {
		for(int i = 1; i <= 4; i++) {
			if(rot[i] == 1) {
				int temp = gear[i][7];
				for(int j = 7; j >= 1; j--) {
					gear[i][j] = gear[i][j - 1];
				}
				gear[i][0] = temp;
				// 시계방향으로 돌리기
			}
			if(rot[i] == -1) {
				int temp = gear[i][0];
				for(int j = 0; j < 7; j++) {
					gear[i][j] = gear[i][j+1];
				}
				gear[i][7] = temp;
				// 반시계방향으로 돌리기
			}
		}
	}
	static void score() {
		for(int i = 1; i <= 4; i++) {
			if(gear[i][0] == 1) {
				result += Math.pow(2, i-1);
				// 점수 계산
			}
				
		}
	}

}