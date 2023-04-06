/**
 * 원판 돌리기 / 골드 2 / 80분
 * https://www.acmicpc.net/problem/17822
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_17822 {
	
	static int N,M,T;
	static LinkedList<LinkedList<Integer>> arr = new LinkedList<>();
	static boolean check;
	static int answer;
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static void rotate(int x, int d, int k) { // x번쨰 원을 d의 방향으로 k칸 돌리기
		
		int multi = N/x;
		
		if(d==0) { // 시계 방향 ->
			for(int m=1;m<=multi;m++) {
				for(int i=0;i<k;i++) {
					int back = arr.get(m*x-1).remove(M-1);
					arr.get(m*x-1).addFirst(back);
				}
			}
		}
		else if(d==1) { // 반시계 방향 <-
			for(int m=1;m<=multi;m++) {
				for(int i=0;i<k;i++) {
					int front = arr.get(m*x-1).remove(0);
					arr.get(m*x-1).add(front);
				}
			}
		}
	}
	
	static void remove() {
		// 제거시 0으로
		check = false;
		int sum = 0;
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				sum += arr.get(i).get(j);
				if(arr.get(i).get(j)!=0) {
					cnt++;
					bfs(i,j);
				}
			}
		}
		if(!check) {
			// 인접하면서 같은 수 없음
			float avg = (float)sum / (float)cnt;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					int in = arr.get(i).get(j);
					if(in!=0) {
						if(in > avg) arr.get(i).set(j, in-1);
						else if(in < avg) arr.get(i).set(j, in+1);
					}
				}
			}
		}
	}
	
	static void bfs(int x, int y) {
		int num = arr.get(x).get(y);
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		
		queue.add(new int[] {x,y});
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			
			for(int i=0;i<4;i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(nx>=0 && nx<N && ny>=0 && ny<M) {// 범위 안쪽인 경우
					if(arr.get(nx).get(ny)==num) {
						arr.get(x).set(y, 0);
						check = true;
						arr.get(nx).set(ny, 0);
						queue.offer(new int[] {nx,ny});
					}
				}
				else if(ny<0 || ny>=M){
					if(ny<0) ny=M-1;
					else if(ny>=M) ny=0;
					
					if(nx>=0 && nx<N && ny>=0 && ny<M) {
						if(arr.get(nx).get(ny)==num) {
							arr.get(x).set(y, 0);
							check = true;
							arr.get(nx).set(ny, 0);
							queue.offer(new int[] {nx,ny});
						}
					}
				}
			}
			//System.out.println("x=" + x + " y="+y);
			//print();
		}
	}
	
	static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(arr.get(i).get(j)+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = parseInt(tmp[0]);
		M = parseInt(tmp[1]);
		T = parseInt(tmp[2]);
		
		for(int i=0;i<N;i++) {
			arr.offer(new LinkedList<>());
		}
		
		for(int i=0;i<N;i++) {
			tmp = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				arr.get(i).add(parseInt(tmp[j]));
			}
		}
		
		print();
		
		for(int t=0;t<T;t++) { // T번 회전
			tmp = br.readLine().split(" ");
			int x = parseInt(tmp[0]);
			int d = parseInt(tmp[1]);
			int k = parseInt(tmp[2]);
			// 회전
			rotate(x,d,k);
			//System.out.println(t+"번 회전");
			//print();
			
			// 인접한곳 없애기
			remove();
			//print();
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				answer += arr.get(i).get(j);
			}
		}
		
		System.out.println(answer);
	}

}
