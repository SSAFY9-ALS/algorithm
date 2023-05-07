import java.util.*;
import java.io.*;

/**
 * 
 * 고층 건물 / 골드 4 / -분
 * https://www.acmicpc.net/problem/1027
 * 
 */
public class Main {
	static int n;
	static int[] arr;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; i++) {
			int num = 0;
			int ax = i;
			int ay = arr[i];
			double max = Double.MIN_VALUE;
			for (int j = i - 1; j >= 1; j--) {
				int bx = j;
				int by = arr[j];
				double a = (ay - by) / (double) (ax - bx);
				if (a > max) {
					num++;
					max = a;
				}
			}
			double min = Double.MIN_VALUE;
			for (int j = i + 1; j <= n; j++) {
				int bx = j;
				int by = arr[j];
				double a = (ay - by) / (double) (bx - ax);
				if (a > min) {
					num++;
					min = a;
				}
			}
			answer = Math.max(answer, num);
		}
		System.out.println(answer);

	}
}
