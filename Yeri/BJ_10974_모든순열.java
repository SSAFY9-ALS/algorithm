import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 모든 순열 / 실버3 / ?분
 * https://www.acmicpc.net/problem/10974
 */

public class Main {

//	static int M;
	static int N;
	static int[] numbers;
	static boolean[] isSelected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		for(int n = 1; n <= N; n++) {
			numbers[n-1]=n;
		}
		isSelected = new boolean[N];
		
//		for(int i = 0; i < N; i++) {
			perm(0);
//		}

	}

	static void perm(int cnt) {
		if(cnt == N) {
			for(int i = 0; i < N; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
			return;
		}else {
			for(int i = 1; i <= N; i++) {
				if(isSelected[i-1] == true) {
					continue;
				}
				numbers[cnt] = i;
				isSelected[i-1] = true;
				perm(cnt+1);
				isSelected[i-1] = false;
			}
		}
	}
}
