import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * 잃어버린 괄호 / 실버2 / 25분
 * https://www.acmicpc.net/problem/1541
 * 
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("-");
		// - 기준으로 분리
		String[] first = s[0].split("\\+");
		// - 가 나오기 전 + 이루어진 숫자는 더하기 위해 분리
		// 메타문자로 들어가는 일부 특수문자를 split으로 나누기 위해 \\추가하여 사용
		int answer = 0;
		for(int i = 0 ; i < first.length; i++) {
			answer += Integer.parseInt(first[i]);
		}
		for(int i = 1 ; i < s.length; i++) {
			String[] s_tmp = s[i].split("\\+");
			int tmp = 0;
			for(int j = 0; j < s_tmp.length; j++) {
				tmp += Integer.parseInt(s_tmp[j]);
			}
			answer -= tmp;
			// - 나온 이후에는 값들을 먼저 더해준 후 빼주어 최소값을 만든다
		}
		System.out.println(answer);
	}

}