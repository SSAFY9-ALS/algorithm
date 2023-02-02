package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2630_색종이만들기 {
	static int [][] paper;
	static int[] colors = new int[2];
	final int white = 0;
	final int blue = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		paper = new int[N][N];
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < N; x++) {
				paper[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		split(0,0,N,N);
		StringBuilder sb = new StringBuilder();
		sb.append(colors[0]).append("\n");
		sb.append(colors[1]);
		System.out.println(sb);
		
	}
	
	public static void split(int starty,int startx, int endy, int endx) {
		int color = paper[starty][startx];
		if(starty==endy) {
			colors[color]++;
			return;
		}
		for(int y = starty; y < endy; y++) {
			for(int x = startx; x < endx; x++) {
				if(paper[y][x]!=color) {
					int newy = (starty+endy)/2;
					int newx = (startx+endx)/2;
					split(starty,startx,newy,newx);
					split(starty,newx,newy,endx);
					split(newy,startx,endy,newx);
					split(newy,newx,endy,endx);
					return;
				}
			}
		}
		colors[color]++;
	}
}

//https://www.acmicpc.net/problem/2630
