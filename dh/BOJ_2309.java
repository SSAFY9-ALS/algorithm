import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 일곱난쟁이 / 브론즈1 / 20분
 * https://www.acmicpc.net/problem/2309
 *
 */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] shorts = new int[9];
		// 처음 입력받은 9명의 난쟁이를 저장할 배열
		List<Integer> realShorts = new ArrayList<>();
		// 진짜 난쟁이 7명을 저장할 리스트
		int sum = 0;
		for(int i = 0; i < 9;i++) {
			shorts[i] = Integer.parseInt(br.readLine());
			sum += shorts[i];
			// 입력과 동시에 키의 합을 구하기
		}
		int find = sum - 100;
		// 구한값에서 100을 빼서 가짜 난쟁이 2명 키의 합을 구하기
		int not_Short1 = -1;
		int not_Short2 = -1;
		// 가짜 난쟁이의 인덱스 저장 변수
		for(int i = 0; i < 8; i++) {
			for(int j = i + 1; j < 9; j++) {
				if(shorts[i] + shorts[j] == find) {
					// 두 명 키의 합이 find면 인덱스 저장 후 break
					not_Short1 = i;
					not_Short2 = j;
					break;
				}
			}
			if(not_Short1 != -1)
				// 가짜 난쟁이를 발견했으면 break
				break;
		}
		for(int i = 0; i < 9; i++) {
			if(i != not_Short1 && i != not_Short2)
				// 가짜 난쟁이를 제외한 난쟁이들을 리스트에 저장
			realShorts.add(shorts[i]);
		}
		Collections.sort(realShorts);
		// 오름차순으로 정렬
		
		for(int i : realShorts) {
			System.out.println(i);
			// 진짜 난쟁이들 출력
		}
		
	}
}