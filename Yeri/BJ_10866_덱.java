package yeri.algorithm0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 덱 / 실버5 / 10분
 */
public class BJ_10866_덱 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> que = new ArrayList<>(); 
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			switch (st.nextToken()) {
			case "push_front":
				que.add(0, Integer.parseInt(st.nextToken()));
				break;
			case "push_back":
				que.add(Integer.parseInt(st.nextToken()));
				break;
			case "pop_front":
				if(que.size()!=0) {
					System.out.println(que.get(0));
					que.remove(0);
				}else {
					System.out.println(-1);
				}
				break;
			case "pop_back":
				if(que.size()!=0) {
					System.out.println(que.get(que.size()-1));
					que.remove(que.size()-1);
				}else {
					System.out.println(-1);
				}
				break;
			case "size":
				System.out.println(que.size());
				break;
			case "empty":
				if(que.size()!=0) {
					System.out.println(0);
				}else {
					System.out.println(1);
				}
				break;
			case "front":
				if(que.size()!=0) {
					System.out.println(que.get(0));
				}else {
					System.out.println(-1);
				}
				break;
			case "back":
				if(que.size()!=0) {
					System.out.println(que.get(que.size()-1));
				}else {
					System.out.println(-1);
				}
				break;
			}
		}
	}
}
