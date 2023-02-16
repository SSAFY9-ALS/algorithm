import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * 
 * 종이의 개수 / 실버2 / 30분
 * https://www.acmicpc.net/problem/1780
 *
 */
public class Main {
	static int n;
	static int[][] matrix;
	static int[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		matrix = new int[n][n];
		count = new int[3];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		paper(0, 0, n);
		// 0,0 길이는 n으로 함수 호출
		System.out.println(count[0]);
		System.out.println(count[1]);
		System.out.println(count[2]);
		// 출력 형식에 맞게 출력
	}

	static void paper(int x, int y, int num) {
		int tmp = matrix[x][y];
		boolean check = false;
		loop: for (int i = x; i < x + num; i++) {
			for (int j = y; j < y + num; j++) {
				if (tmp != matrix[i][j]) {
					// 처음값과 다른 값이 나오면 break;
					check = true;
					break loop;
				}
			}
		}
		if (check) {
			paper(x, y, num / 3);
			paper(x + (num / 3), y, num / 3);
			paper(x + (num / 3) * 2, y, num / 3);
			paper(x, y + (num / 3), num / 3);
			paper(x + (num / 3), y + (num / 3), num / 3);
			paper(x + (num / 3) * 2, y + (num / 3), num / 3);
			paper(x, y + (num / 3) * 2, num / 3);
			paper(x + (num / 3), y + (num / 3) * 2, num / 3);
			paper(x + (num / 3) * 2, y + (num / 3) * 2, num / 3);
			//9구역으로 나누어 재귀 함수 호출
			return;
		}
		count[tmp + 1]++;
		// tmp에 맞는 값 1 증가
	}

}