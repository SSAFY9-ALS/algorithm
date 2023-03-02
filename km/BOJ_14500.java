/**
 * 테트로미노 / 골드 4 / 70분
 * https://www.acmicpc.net/problem/14500
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_14500 {
	 	
		static int n;
	    static int m;
	    static int[][] arr;
	    static int[][] check;
	    static int max = Integer.MIN_VALUE;
	    
	    static void dfs(int x, int y, int num, int count) {
	        if(num==4) { // 길이가 4이면 테트로미노 완성!
	            max = Math.max(max, count); // 최대값 비교
	            return;
	        }
	        
	        int[] dx = {-1,0,1,0};
	        int[] dy = {0,1,0,-1};
	        
	        for(int i=0;i<4;i++) {
	            int nx = x + dx[i];
	            int ny = y + dy[i];
	            if(nx>=0 && ny>=0 && nx<n && ny<m) {
	                if(check[nx][ny]==0) {
	                	check[nx][ny]=1;
	                    dfs(nx,ny,num+1,count+arr[nx][ny]);
	                    check[nx][ny]=0;
	                }
	            }
	        }
	    }
	    static void special(int x, int y) { // ㅗ ㅓ ㅏ ㅜ 인경우 dfs로 만들 수 없음 => 따로 생각해줘야함
	        int sum = 0;
	        // ㅜ 만들 수 있는 범위
	        if(x>=0 && x<n-1 && y>=0 && y<m-2) {
	            sum = arr[x][y] + arr[x][y+1] + arr[x][y+2] + arr[x+1][y+1];
	            max = Math.max(max, sum);
	        }
	        // ㅗ 만들 수 있는 범위
	        if(x-1>=0 && x<n && y>=0 && y<m-2) {
	            sum = arr[x][y] + arr[x][y+1] + arr[x][y+2] + arr[x-1][y+1];
	            max = Math.max(max, sum);
	        }
	        // ㅏ 만들 수 있는 범위
	        if(x>=0 && x+2<n && y>=0 && y+1<m) {            
	            sum = arr[x][y] + arr[x+1][y] + arr[x+2][y] + arr[x+1][y+1];
	            max = Math.max(max, sum);
	        }
	        // ㅓ 만들 수 있는 범위
	        if(x-1>=0 && x+1<n && y>=0 && y+1<m) {
	            sum = arr[x][y] + arr[x][y+1] + arr[x+1][y+1] + arr[x-1][y+1];
	            max = Math.max(max, sum);
	        }
	    }
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        String[] tmp = br.readLine().split(" ");
	        n = parseInt(tmp[0]);
	        m = parseInt(tmp[1]);
	        
	        arr = new int[n][m];
	        check = new int[n][m];
	        
	        for(int i=0;i<n;i++) {
	            tmp = br.readLine().split(" ");
	            for(int j=0;j<m;j++) {
	                arr[i][j] = parseInt(tmp[j]);
	            }
	        }
	        
	        for(int i=0;i<n;i++) {
	            for(int j=0;j<m;j++) {
	                check[i][j]=1;
	                dfs(i,j,1,arr[i][j]);
	                check[i][j]=0;
	                special(i, j);
	            }
	        }
	        
	        System.out.println(max);
	    }
	
}
