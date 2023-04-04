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
 * 색종이 만들기 / 실버2 / 40분
 * https://www.acmicpc.net/problem/2630
 *
 */

public class Main {
	static int n;
	static int[][] matrix;
	static int[] count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		matrix = new int[n][n];
		count = new int[2];
		// 0 -> 흰색, 1 -> 파란색
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		colorPaper(0, 0, n);
		System.out.println(count[0]);
		// 흰색 출력
		System.out.println(count[1]);
		// 파란색 출력

	}

	public static void colorPaper(int x, int y, int num) {
		// x, y 는 탐색 시작 위치, num은 탐색할 길이
		int tmp = matrix[x][y];
		// x ~ x+num, y ~ y+num 사이의 값들이 동일한지 알아보기 위해 x,y값의 위치를 얻어와 다른 값들과 비교한다. 

		for (int i = x; i < x + num; i++) {
			// x ~ x+num
			for (int j = y; j < y + num; j++) {
				// y ~ y+num
				if (matrix[i][j] != tmp) {
					// i,j의 위치의 값이 tmp와 다르면 분할하여 탐색
					colorPaper(x, y, num / 2);
					// 시작위치 x,y 로 하고 num/2의 길이의 색종이 찾기
					colorPaper(x + num / 2, y, num / 2);
					// 시작위치 x + 길이,y 로 하고 num/2의 길이의 색종이 찾기
					colorPaper(x, y + num / 2, num / 2);
					// 시작위치 x,y + 길이 로 하고 num/2의 길이의 색종이 찾기
					colorPaper(x + num / 2, y + num / 2, num / 2);
					// 시작위치 x + 길이,y + 길이 로 하고 num/2의 길이의 색종이 찾기
					return;
				}

			}
		}
		count[tmp]++;
		// 범위의 값이 tmp값과 모두 같으면 tmp와 같은 count의 index값을 1증가
	}
}