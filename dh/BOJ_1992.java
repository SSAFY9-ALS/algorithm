import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * 쿼드트리 / 실버1 / 30분
 * https://www.acmicpc.net/problem/1992
 *
 */
public class Main {
	static int n;
	static int[][] matrix;

	public static void quad(int x, int y, int num) {
		
		int tmp = matrix[x][y];
		// 확인할 임시 값 저장
		int check = 0;
		// 확인 여부 값
		for (int i = x; i < x + num; i++) {
			for (int j = y; j < y + num; j++) {
				if (matrix[i][j] != tmp) {
					// 만약 임시 값과 다르면 한개의 숫자로 이루어져 있지 않으므로 재귀 호출
					check = 1;
					break;
				}
			}
			if (check == 1)
				break;
		}
		if (check == 1) {
			System.out.print("(");
			// 분할 하면 괄호 출력
			quad(x, y, num / 2);
			// 왼쪽 위 탐색
			quad(x, y + num / 2, num / 2);
			// 오른쪽 위 탐색
			quad(x + num / 2, y, num / 2);
			// 왼쪽 아래 탐색
			quad(x + num / 2, y + num / 2, num / 2);
			// 오른쪽 아래 탐색
			System.out.print(")");
			// 앞의 재귀가 종료되면 괄호 닫기
		}
		else
			System.out.print(tmp);
		// 값이 같으면 임시값 출력
		
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			String st = br.readLine();
			for (int j = 0; j < n; j++) {
				matrix[i][j] = st.charAt(j) - '0';
				// int 값으로 입력값 저장
			}
		}
		quad(0, 0, n);
		// 분할 정복 재귀 호출

	}
}