import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
0만들기
*/
public class BJ_7490_0만들기 {
	
	static int N;
	static int[] pick;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			pick = new int[N-1];
		}
	}
	
	public static void expression(int cnt) {
		if(cnt == N-1) {
			resultEx();
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			pick[cnt] = i;
			expression(cnt + 1);
		}
	}
	
	public static void resultEx() {
		int total = 1;
		// 더하기 큐
		Deque<Integer> plus = new LinkedList<>();
		// 빼기 큐
		Deque<Integer> minus = new LinkedList<>();
		
		for(int i = 0; i < N-1; i++) {
			// 0 : 더하기
			if(pick[i] == 0) {
				if(minus.size() == 0 && plus.size() == 0) {
					plus.add(i+2);
				}
				else if(minus.size() == 0) {
					StringBuilder tempSb = new StringBuilder();
					// 더하기 큐 안에 있는 모든 값들을 다 빼내서 정수로 만든다.
					while(plus.size()>0) {
						tempSb.append(String.valueOf(plus.pollFirst()));
					}
					// 합에 더함
					total += Integer.parseInt(tempSb.toString());
				}
				else if(plus.size() == 0) {
					StringBuilder tempSb = new StringBuilder();
					// 빼기 큐 안에 있는 모든 값들을 다 빼내서 정수로 만든다.
					while(minus.size()>0) {
						tempSb.append(String.valueOf(minus.pollFirst()));
					}
					// 합에서 뺌
					total -= Integer.parseInt(tempSb.toString());
				}
			}
			// 1 : 빼기
			else if(pick[i] == 1) {
				if(plus.size() == 0 && minus.size() == 0) {
					minus.add(i+2);
				}
				else if(plus.size() == 0) {
					StringBuilder tempSb = new StringBuilder();
					// 빼기 큐 안에 있는 모든 값들을 다 빼내서 정수로 만든다.
					while(minus.size()>0) {
						tempSb.append(String.valueOf(minus.pollFirst()));
					}
					// 합에서 뺌
					total -= Integer.parseInt(tempSb.toString());
				}
				else if(minus.size() == 0) {
					StringBuilder tempSb = new StringBuilder();
					// 더하기 큐 안에 있는 모든 값들을 다 빼내서 정수로 만든다.
					while(plus.size()>0) {
						tempSb.append(String.valueOf(plus.pollFirst()));
					}
					// 합에 더함
					total += Integer.parseInt(tempSb.toString());
				}
				
			}
			// 2 : 이어붙이기
			else {
				if(minus.size() == 0 && plus.size() == 0) {
					plus.add(i+2);			
				}
				else if(plus.size() == 0) {
					minus.add(i+2);
				}
			}
		}
		if(total == 0) {
			
		}
	}
}
