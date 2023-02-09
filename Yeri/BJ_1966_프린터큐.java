package yeri.algorithm0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 프린터큐 / 실버3 / 30분
 */
public class BJ_1966_프린터큐 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		ArrayList<Integer> que = new ArrayList<>();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=  0; tc< T; tc++	) {
			que.clear();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int n = 0; n<N; n++) {
				que.add(Integer.parseInt(st.nextToken()));				
			}
			int result = 0;
			while(true) {
				if(search(que)) {
					result++;
					que.remove(0);
					if(index == 0) {
						break;
					}
					else {
						index--;
					}
				}else {
					if(index==0) {
						index = que.size()-1;
					}
					else {
						index--;
					}
					que.add(que.get(0));
					que.remove(0);
				}
			}
			System.out.println(result);
		}
	}
	
	static boolean search(ArrayList<Integer> que) {
		for(int i = 1; i < que.size(); i++) {
			if(que.get(0)<que.get(i))
				return false;
		}
		return true;
	}
	

}
