/**
 * 중복 뺴고 정렬하기 / 실버 5 / 30분
 * https://www.acmicpc.net/problem/10867
 */
package baekjoon;
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_10867 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int num = parseInt(br.readLine()); // 수의 개수 입력받음
		String[] tmp = br.readLine().split(" "); 
		
		HashSet<Integer> hset = new HashSet<Integer>(); // 중복 없이 받기 위해  HashSet선언
		
		for(int i=0;i<num;i++) {
			hset.add(parseInt(tmp[i]));
		}
		
		ArrayList<Integer> arr = new ArrayList<Integer>(hset);
		// 따로 for문을 돌지 않고 hset을 배열로 받기 위해 ArrayList 사용
		
		Collections.sort(arr); // 정렬
		
		for(int i=0;i<arr.size();i++) {
			sb.append(arr.get(i)).append(" ");
		}
		System.out.println(sb);
		
	}

}
