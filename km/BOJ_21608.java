/**
 * 상어 초등학교 / 골드 5 / 80분
 * https://www.acmicpc.net/problem/21608
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_21608 {
	
	static int N;
	static int[][] arr; // 학생들의 자리
	static int[][] friendInfo; // 번호와 그 학생 번호의 친한 친구 정보
	static int[] order; // 자리에 앉힐 순서
	static int answer;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static void makeSeat() {
		for(int k=1;k<N*N;k++) { // 순서 배열에서 한명씩 꺼내어 앉힘
			int now = order[k]; // 자리에 앉을 학생
			int[] max = new int[4]; // 해당 자리 주변에 있는 친한 친구 수 , 해당자리 주변 비어있는 칸 수,   그 좌표
									// 친구 수가 4가되면 그자리에 앉히고 바로 다음 순서로 넘어감
			max[0]=-1;
			max[1]=-1;
			max[2]=-2;
			max[3]=-2;
			boolean check = false;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) { // 배열을 돌며 해당 자리 주변에 친구가 몇명 앉아있는지 확인
					if(arr[i][j]==0) { // 앉을 수 있는 자리 이므로 확인
						int cnt = 0; // 해당 자리 주변에 있는 친한 친구 수
						int empty = 0; // 해당 자리 주변의 빈칸 개수
						for(int ii=0;ii<4;ii++) {
							int nx = i + dx[ii];
							int ny = j + dy[ii];
							if(nx>=0 && nx<N & ny>=0 && ny<N) {
								if(arr[nx][ny]==0) {
									empty++;
								}
								for(int jj=0;jj<4;jj++) {
									if(friendInfo[now][jj] == arr[nx][ny]) {
										cnt++;
									}
								}
							}
						}
						if(cnt>max[0]) { // 친구의 값이 현재 값보다 크다면 갱신
							max[0] = cnt;
							max[1] = empty;
							max[2] = i;
							max[3] = j;
						}
						else if(cnt==max[0]) { // 친구의 값이 현재값과 같을 때는
							if(empty>max[1]) { // 주변의 빈칸의 개수를 확인해야함
								max[0] = cnt;
								max[1] = empty;
								max[2] = i;
								max[3] = j;
							}
						}
						if(max[0]==4) { // 4인 경우가 나올시 그 자리에 바로 앉힘
							arr[i][j] = now;
							check = true;
							break;
						}
					}
				}
				if(check) break;
				
			}
			if(max[0]!=-1) { // 해당 자리에 자리에 앉힘
				arr[max[2]][max[3]]=now;
			}
		}
	}
	
	static void sum() { // 만족도 계산
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int friend = 0; // 해당 자리 주변의 친구 수
				for(int k=0;k<4;k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(nx>=0 && nx<N & ny>=0 && ny<N) {
						for(int m=0;m<4;m++) {
							if(arr[nx][ny]==friendInfo[arr[i][j]][m]) {
								// 친구의 수 세줌
								friend++;
							}
						}
					}
				}
				// 그 수만큼 만족도 계산
				if(friend==1) {
					answer+=1;
				}
				else if(friend==2) {
					answer+=10;
				}
				else if(friend==3) {
					answer+=100;
				}
				else if(friend==4) {
					answer+=1000;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = parseInt(br.readLine());
		
		arr = new int[N][N];
		friendInfo = new int[N*N+1][4];
		order = new int[N*N];
		
		for(int i=0;i<N*N;i++) {
			String[] tmp = br.readLine().split(" ");
			order[i] = parseInt(tmp[0]);
			for(int j=0;j<4;j++) {
				friendInfo[order[i]][j] = parseInt(tmp[j+1]);
			}
		}
		
		arr[1][1] = order[0]; // 첫번쨰 앉는 사람은 무조건 1,1 앉음
		
		makeSeat();
		sum();
		
		System.out.println(answer);
	}

}
