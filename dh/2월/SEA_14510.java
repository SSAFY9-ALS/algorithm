import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * 나무 높이 / D2 / 120분
 * https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AYFofW8qpXYDFAR4
 *
 */

public class Main {
	static int n;
	static int[] trees;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++){
			n = Integer.parseInt(br.readLine());
			trees = new int[n];
			int answer = 0;
			int one = 0;
			int two = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int max = 0;
			for(int i = 0; i < n; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				if(max < trees[i])
					max = trees[i];
			}
			int odd = 0; // 키를 1만큼 증가할 수 있는 횟수
			int even = 0; // 키를 2만큼 증가 할 수 있는 횟수
			for(int i = 0; i < n; i++) {
				trees[i] = max - trees[i]; // 자라야 할 키 저장
				even +=  trees[i] / 2; // 키를 2만큼 증가 할 수 있는 횟수 저장
				odd += trees[i] % 2; // 2만큼 증가하고 남은 길이가 1일 때 횟수 저장
			}
			while(even - odd >= 2) {
				// 1,0 / 1,1 / 2,1 / 2,2 / 3,2 ... 만큼 0 또는 1의 차이의 횟수를 각각 가지므로
				// 2자라는 것을 1을 두번 자라게 할 수 있으므로 변환
				even -= 1;
				odd += 2;
			}
			if(odd > even)
				// 1을 한번 더 주어 완성시켰다면(차이가 1이면) 실행
				answer = odd*2 - 1;
			else
				// 차이가 0으로 같으면 실행
				answer = even * 2;
			System.out.println("#" + test_case + " " + answer);
		}
	}
}