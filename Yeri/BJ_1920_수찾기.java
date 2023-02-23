package yeri_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 수찾기 / 실버4 / 30분
 * https://www.acmicpc.net/problem/1920
 */
public class BJ_1920_수찾기 {
	
	static List<Integer> arr = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine()," ");
		
		for(int i = 0; i<N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(arr);
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < M; i++) {
			sb.append(binarySearch(Integer.parseInt(st.nextToken()),0,N)).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static int binarySearch(int target,int start, int end) {
		int mid = (start+end)/2;
		if(arr.get(mid) == target) {
			return 1;
		}
		if(end - start == 1)
			return 0;
		else if(target > arr.get(mid)){
			return binarySearch(target, mid, end);
		}else {
			return binarySearch(target, start, mid);
		}
//		return 0;
	}
}
