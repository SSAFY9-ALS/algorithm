package kr.co.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author 민정인
 *
 * list 사용
 * 랜덤이라는 것은 결국 당첨자 수 / 구매한 사람 수만큼의 확률
 * 전체 맵에 put으로 업데이트(다만 확률은 현재 인원수 + 나까지 해서 진행)
 * 
 * 100/100
 */

class Pair implements Comparable<Pair>{		// 복권의 번호, 확률, 당첨금 저장 클래
	public int idx;
	public Double d;
	public int i;
	public Pair() {}
	public Pair(int idx, Double d, int i) {
		this.idx = idx;
		this.d = d;
		if(this.d > 1) {
			this.d = (double) 1;			// 100%는 다 같은 100%이므로 1을 초과할 경우 1로 바꿔줌
		}
		this.i = i;
	}
	
	@Override
	public int compareTo(Pair p) {			// 비교함수 재정
		if(this.d < p.d) {
			return 1;
		} else if(this.d - p.d == 0) {
			return p.i - this.i;
		} else {
			return -1;
		}
	}
}
public class Test_1 {
	static List<Pair> list = new ArrayList<>();
	static class Solution {
	    public int solution(int[][] lotteries) {
	        int answer = 0;
	        for(int i = 0; i < lotteries.length; i++) {
	        	Pair tmp = new Pair(i + 1, (double) lotteries[i][0] / (lotteries[i][1] + 1), lotteries[i][2]);	// 각각의 번호(i+1), 확률, 당첨금 입력
	        	list.add(tmp);
	        }
	        Collections.sort(list);		// 리스트 정렬
	        for(int i = 0; i < list.size(); i++) {
	        	System.out.println(list.get(i).d + " " + list.get(i).i);	// 가장 확률이 높고 그 중에서 당첨금이 가장 높은 복권의 번호와 당첨금 출력
	        }
	        answer = list.get(0).idx;
	        return answer;
	    }
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		Solution s = new Solution();
		int[][] lotteries = new int[t][3];
		for(int i = 0; i < t; i++) {
			lotteries[i][0] = sc.nextInt();
			lotteries[i][1] = sc.nextInt();
			lotteries[i][2] = sc.nextInt();
		}
		s.solution(lotteries);
	}
}
