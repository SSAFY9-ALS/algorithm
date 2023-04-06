import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 모든 순열 / 실버3 / 10분
 * https://www.acmicpc.net/problem/10974
 *
 */

public class Main {
	static int n;
	static int[] numbers;
	// 순열 저장 배열
	static boolean[] isSelected;
	// 인덱스에 해당하는 숫자가 사용 중인지 저장하는 배열

	public static void perm(int count) {
		if (count == n + 1) {
			// count가 다 차면 순열 출력
			for (int i = 1; i <= n; i++)
				System.out.print(numbers[i] + " ");
			System.out.println();
		} else {
			for (int i = 1; i <= n; i++) {
				if (isSelected[i] == true)
					// 사용중이면 continue;
					continue;
				numbers[count] = i;
				// 순열 배열에서 count위치에 i값 저장
				isSelected[i] = true;
				// i값은 사용중으로 저장
				perm(count + 1);
				// count+1 위치를 인자로 갖고 perm 호출
				isSelected[i] = false;
				// 이후의 위치의 값을 다 찾았으면 i번째는 사용하지 않고있음을 저장
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		// n의 값 입력
		numbers = new int[n + 1];
		// 순열 저장 배열 초기화
		isSelected = new boolean[n + 1];
		// 인덱스에 해당하는 숫자가 사용 중인지 저장하는 배열 초기화
		perm(1);
		// 인자 1을 갖고 perm메서드 호출
	}
}
