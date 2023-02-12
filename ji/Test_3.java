package kr.co.programmers;

import java.util.Scanner;
/**
 *
 * 최적의 움직임?
 * 1, 4, 3 -> 1, 4, 2 -> 1, 4, 1(win)
 * 0, 2, 0, 1 -> 0, 2, 0, 0 -> 0, 1, 0, 0 -> 0, 0, 0, 0
 * @author 민정인
 * 
 * 최적의 움직임이란 자신이 승리한다는 조건 하에 움직여야 한다.
 * + 우리는 배열에서 0이 아닌 모든 곳을 뺄 수 있다
 * -> 이로 인해 이미 팰린드롬이 만들어진 장소도, 팰린드롬과는 상관없는 정중앙(홀수 배열의 경우)에서도 값을 뺼 수 있다.
 * 33.3 / 100
 */
public class Test_3 {
//	static int cnt = 0;
//	static void turn(int[] query) {
//		int idx = -1;
//		int evenIdx = -1;
//		int min = Integer.MAX_VALUE;
//		int even = Integer.MAX_VALUE;
//		for(int i = 0; i <= query.length / 2; i++) {
//			if(query[i] != query[query.length - (i + 1)]) {
//				int sub = Math.abs(query[i] - query[query.length - (i + 1)]);
//				if(sub % 2 != 0 && even > sub) {
//					even = sub;
//					evenIdx = i;
//				}
//				if(sub < min) {
//					min = sub;
//					idx = i;
//				}
//			}
//		}
//		if(idx == -1 || evenIdx == -1) {
//			return;
//		}
//		if(query[idx] > query[query.length - (idx + 1)]) {
//			query[idx]--;
//		} else {
//			query[query.length - (idx + 1)]--;
//		}
//		cnt++;
//		turn(query);
//	}
	static public int[] solution(int[][] queries) {
        int[] answer = {};
        answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
        	int cnt = 0;
        	for(int j = 0; j < queries[i].length; j++) {
        		cnt += queries[i][j];
        	}
        	answer[i] = cnt % 2;
        }
//        for(int i = 0; i < queries.length; i++) {
//        	turn(queries[i]);
//        	answer[i] = cnt % 2;
//        	System.out.println(cnt);
//        	cnt = 0;
//        }
        return answer;
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int n = sc.nextInt();
		int[][] arr = new int[t][n];
		for(int i = 0; i < t; i++) {
			for(int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		int[] ans =solution(arr);
		for(int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}
}
