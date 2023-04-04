import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * 중복빼고정렬하기 / 실버5 / 10분
 * https://www.acmicpc.net/problem/10867
 *
 */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<Integer> set = new HashSet<>();
		int n = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			set.add(Integer.parseInt(s[i]));
			// 중복 제거를 위해 set에 값 저장
		}
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		// 정렬을 위해 set을 list로 변환
		
		for(int i : list)
			System.out.print(i + " ");
		
	}
}