/**
 * 벌꿀채취 / / 200분
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
 */

package com.ssafy;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class SWEA_2115 {
	static int n;
	static int m;
	static int c;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int tc = parseInt(br.readLine()); // 테스트 케이스 개수
       
        for(int t=1;t<=tc;t++) {
        	String[] tmp = br.readLine().split(" ");
        	
        	n = parseInt(tmp[0]); // 킈기
        	m = parseInt(tmp[1]); // 선택가능한 벌통의 개수
        	c = parseInt(tmp[2]); // 채취가능한 최대 꿀
        	
        	int[][] arr = new int[n][n]; // 벌꿀 기본 배열 
        	for(int i=0;i<n;i++) {
        		tmp = br.readLine().split(" ");
        		for(int j=0;j<n;j++) {
        			arr[i][j] = parseInt(tmp[j]);
        		}
        	}
        	// 각 행별로 최대 양 구하기
        	int[] maxHoney = new int[n];  // 행 별 최대값
        	
        	for(int i=0;i<n;i++) {
        		int max = Integer.MIN_VALUE;
        		ArrayList<Integer> maxrow = new ArrayList<>();
        		for(int j=0;j<n-m+1;j++) {
        			int checksum =0;
        			for(int k=0;k<m;k++){
        				checksum += arr[i][j+k];
        			}
        			int price = 0; 
        			if(checksum<=c) { // 채취 가능한 꿀의 범위를 넘지 않는 경우 바로 구함
        				for(int k=0;k<m;k++) {
        					price += (arr[i][j+k] * arr[i][j+k]);
        				}
        				maxrow.add(price);
        			}
        			else {
        				int[] b = Arrays.copyOfRange(arr[i], j, j+m);
        				int[] visit = new int[m];
        				make(maxrow,b,visit,0);
        			}
        		}
        		maxHoney[i] = Collections.max(maxrow);
        	}
        	Arrays.sort(maxHoney);
        	System.out.println("#" + t + " "+ (maxHoney[n-1]+maxHoney[n-2]));
        }
    }
    private static void make(ArrayList<Integer> row, int[] arr, int[] visited, int count) {
    	if(count==m) {
    		int p =0;
    		int sum = 0;
    		for(int i=0;i<m;i++) {
    			if(visited[i]==1) {
    				sum+=arr[i];
    				p += (arr[i]*arr[i]);
    			}
    		}
    		if(sum<=c) {
    			row.add(p);
    		}
    		return;
    	}
    	
    	// 해당 자리 포함한 조합
    	visited[count] = 1;
    	make(row,arr,visited,count+1);
    	
    	// 해당 자리 포함하지 않은 조합
    	visited[count] = 0;
    	make(row,arr,visited,count+1);
    }
}
