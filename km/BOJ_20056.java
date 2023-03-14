/**
 * 마법사 상어와 파이어볼 / 골드 4 / 120분
 * https://www.acmicpc.net/problem/20056
 */
package baekjoon;

import java.io.*;
import java.util.*;

import javax.management.Query;

import static java.lang.Integer.parseInt;

public class BOJ_20056 {
	
	private static class Shark{
		int r; // x좌표
		int c; // y좌표
		int m; // 질량
		int s; // 속력
		int d; // 방향
		
		public Shark(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	static void move() {
		for(Shark s : sharkList) {
			s.r = (N + s.r + dx[s.d] * (s.s % N)) % N;
			s.c = (N + s.c + dy[s.d] * (s.s % N)) % N;
			// 배열 벗어나지 않음
			// 리스트 자체의 상어 정보 업데이트
			
			arr[s.r][s.c].add(s);
		}
	}
	
	static void sum() {
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j].size()>=2) {
					int mSum = 0; // 질량 합
					int sSum = 0; // 속도 합
					int cnt = arr[i][j].size(); // 합쳐진 상어 개수
					boolean odd = true;
					boolean even = true; // 방향 같은지 검사
					
					while(!arr[i][j].isEmpty()) {
						Shark baby = arr[i][j].poll();
						mSum += baby.m;
						sSum += baby.s;
						
						if(baby.d%2==0) {
							odd = false;
						}
						else {
							even = false;
						}
						sharkList.remove(baby);
					}
					
					int nM = mSum/5;
					int nS = sSum / cnt;
					
					if(nM!=0) {
						if(odd || even) { // 홀수, 짝수 하나로만 이루어짐
							for(int k=0;k<=6;k+=2) {
								sharkList.add(new Shark(i,j,nM,nS,k));
							}
						}
						else {
							for(int k=1;k<=7;k+=2) {								
								sharkList.add(new Shark(i,j,nM,nS,k));
							}
						}
					}
				}
				else {
					arr[i][j].clear();
					// 이동한 상어 리스트 배열에 저장되어있음 
				}
			}
		}
	}
	
	static int N,M,K;
	static int answer;
	static List<Shark> sharkList;
	static Queue<Shark> [][] arr;
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		
		N = parseInt(tmp[0]);
		M = parseInt(tmp[1]);
		K = parseInt(tmp[2]);
		
		sharkList = new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			tmp = br.readLine().split(" ");
			sharkList.add(new Shark(parseInt(tmp[0])-1,parseInt(tmp[1])-1,parseInt(tmp[2]),parseInt(tmp[3]),parseInt(tmp[4])));
		}
		
		arr = new Queue[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				arr[i][j] = new ArrayDeque<>();
			}
		}
		
		for(int i=0;i<K;i++) {
			move();
			sum();
		}
		
		for(Shark s : sharkList) {
			answer += s.m;
		}
		
		System.out.println(answer);
	}
	

}
