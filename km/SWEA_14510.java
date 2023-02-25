/**
 * 나무 높이 / D2 / 70분
 * https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AYFofW8qpXYDFAR4
 */
package swea;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class SWEA_14510 {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = parseInt(br.readLine());
		
		for(int t=1;t<tc+1;t++) {
			int n = parseInt(br.readLine());
			int[] tree = new int[n];
			
			String[] tmp = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				tree[j] = parseInt(tmp[j]);
			}
			
			Arrays.sort(tree);
			int maxtree = tree[n-1];
			int even = 0; // 짝수 개수
			int odd = 0; // 홀수 개수
			for(int i=0;i<n;i++) {
				int mid = maxtree-tree[i];
				even += mid/2;
				odd += mid%2; 
			}
			
			while(even-odd>1) {
				even-=1;
				odd+=2;
			}
			if(odd>even) {
				sb.append("#").append(t).append(" ").append(odd*2-1).append("\n");
			}
			else {
				sb.append("#").append(t).append(" ").append(even*2).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	

}
