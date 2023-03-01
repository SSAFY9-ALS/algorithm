import java.io.*;
import java.util.*;
/**
 * 
 * N-Queen / 골드4 / 80분
 * https://www.acmicpc.net/problem/9663
 * 
 */
public class Main {
	static int n;
	static int[] col;
	static int count;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		col = new int[n + 1];
		queen(1);
		System.out.println(count);
	}
	
	static void queen(int rowNo) { // 놓을려고 하는 퀸의 행
		
		if(!isAvailable(rowNo - 1))
			// rowNo에 놓기전에 이전의 행까지 유효하지 않으면 실행
			// 통과되면 rowNo에 열의 값을 넣어 진행한다.
			return;
		if(rowNo > n) {
			// 행이 가득 차면 count 1 증가
			count++;
			return;
		}
		for(int i = 1; i <= n; i++) {
			// rowNo에 1부터 n까지 열을 넣어 다음 행
			col[rowNo] = i;
			queen(rowNo + 1);
			// queen에 rowNo + 1인자를 주고 유효성 검사를 통해 리턴받으면
			// rowNo에 열의 값을 1 증가 한 후 다시 rowNo + 1인자를 주고 메서드를 호출한다
		}
		
	}
	
	static boolean isAvailable(int rowNo) {
		for(int i = 1; i < rowNo; i++) { // i : 비교대상 queen의 행
			if(col[rowNo] == col[i] || rowNo - i == Math.abs(col[rowNo]-col[i]))
				// 주어진 행의 열과 1부터 rowNo-1행의 열을 비교했을 때 규칙에 하나라도 어긋나면 false 리턴
				return false;
		}
		return true;
		// 규칙에 모두 맞으면 유효하므로 true 리턴
	}

}