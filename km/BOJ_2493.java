import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int[] arr = new int[n];
		// StringBuilder answer = new StringBuilder();
		Stack<int[]> stack = new Stack<>();
		
		for(int i=0;i<n;i++) {
			int height = Integer.parseInt(st.nextToken());
			while(!stack.isEmpty()) {
				if(stack.peek()[1] >= height) {
					System.out.print(stack.peek()[0] + " ");
					break;
				}
				stack.pop();
			}
			if(stack.isEmpty()) {
				System.out.print("0 ");
			}
			stack.push(new int[] {i+1,height});
		}
		
		
		// 처음 생각한 풀이
//		for(int i =0;i<n;i++) {
//			arr[i] = Integer.parseInt(st.nextToken());
//		}
//		
//		int flag = 0;  // 찾으면 1, 못찾으면 0
//		for(int i=1;i<n;i++) {
//			flag = 0;
//			for(int j=i-1;j>=0;j--) {
//				if(arr[j]>=arr[i]) {
//					flag = 1;
//					answer.append(j+1);
//					break;
//				}
//			}
//			if(flag==0) {
//				answer.append(0);
//			}
//			
//		}
//		for(int i=0;i<n;i++) {
//			System.out.printf("%s ",answer.charAt(i));
//			
//		}
	}
}
