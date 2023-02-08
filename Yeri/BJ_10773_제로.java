package yeri_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 제로 / 실버4 / 10분
 * https://www.acmicpc.net/problem/10773
 */
public class BJ_10773_제로 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		int index = 0;
		int[] arr = new int [K];
		for(int k = 0; k < K; k++) {
			int t = Integer.parseInt(br.readLine());
			if(t==0) {
				arr[--index]=0;
			}else {
				arr[index++]=t;
			}
		}
		int sum = 0;
		for(int i = 0; i < index; i++) {
			sum+=arr[i];
		}
		System.out.println(sum);
	}
}
