/**
 * 드래곤 커브 / 골드 4 / 
 * https://www.acmicpc.net/problem/15685
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_15685 {
	
	static int[][] arr = new int[102][102];
    static int answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = parseInt(br.readLine()); // 커브 개수
        for(int k=0;k<n;k++) {
            String[] tmp = br.readLine().split(" ");
            int starty = parseInt(tmp[0]); // 시작점 y좌표 배열상으로
            int startx = parseInt(tmp[1]); // 시작점 x좌표 배열상으로
            int startdir = parseInt(tmp[2]); // 시작 방향
            int generation = parseInt(tmp[3]); // 커브 정보
            
            curve(startx,starty,generation,startdir);
        }
        int check=0;
        // 네 꼭지점이 모두 드래곤 커브인 정사각형 체크
        for(int i=0;i<=100;i++) {
            for(int j=0;j<=100;j++) {
                check=0;
                for(int k=0;k<2;k++) {
                    for(int m=0;m<2;m++) {
                        if(arr[i+k][j+m]==1)
                            check+=1;
                    }
                }
                if(check==4) {
                    answer+=1;
                }
            }
        }
        
        System.out.println(answer);
    }
    private static void curve(int x, int y, int g, int d) {
        int[] dx = {0,-1,0,1};
        int[] dy = {1,0,-1,0}; //문제 에서 제시한 0,1,2,3 순 방향
        
        int[] dir = new int[(int)Math.pow(2, g)]; // 방향 배열의 크기
        // 0 세대 1, 세대가 늘어날때 마다 전 세데의 2배 만큼 커브 생성
        
        arr[x][y]=1;
        dir[0]= d;// 방향 배열
        for(int k=0;k<g;k++) {
            for(int i=(int)Math.pow(2, k);i<(int)Math.pow(2, k)*2;i++) {
                dir[i]=(dir[(int)Math.pow(2, k+1)-1-i]+1)%4;
                //1234  4321순의 대칭으로 이루어짐
            }
        }
        
        int nx=x;
        int ny=y;
        for(int i=0;i<dir.length;i++) {// 베ㅐ열 돌면서 방향에 맞춰 커브길 생성
            nx+=dx[dir[i]];
            ny+=dy[dir[i]];
            arr[nx][ny]=1;
        }
        
        
    }
}
