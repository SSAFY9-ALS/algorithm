package yeri.algorithm0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;


/**
 * 요세푸스문제0 / 실버5 / 15분
 */
public class BJ_11866_요세푸스문제0 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int size = Integer.parseInt(st.nextToken());
		int jump = Integer.parseInt(st.nextToken())-1;
		
		LinkedList<Integer> que = new LinkedList<>();
		for(int i = 0; i < size; i++) {
			que.add(i+1);
		}
		
		StringBuilder sb = new StringBuilder();
		int index = jump;
		sb.append("<");
		while(size!=0) {
			while(index>=size) {
				index-=size;
			}
			sb.append(que.get(index)).append(", ");
			que.remove(index);
			index+=jump;
			size--;
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb);
	}

}
