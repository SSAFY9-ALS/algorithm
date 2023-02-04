import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9020 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		
		// 소수 미리 구하기
		int[] prime = new int[10001];
		for(int i=2;i<=10000;i++) {
			for(int j=i+i;j<=10000;j+=i) {
				prime[j] = 1; // 0이면 소수 1이면 소수 아님
			}
		}
		for(int tc=0;tc<testcase;tc++) {
			int num = Integer.parseInt(br.readLine());
			
			int g1 = 0;
			int g2 = 0;
			int sub = 10000;
			for(int i=1;i<=num/2;i++) {
				if(prime[i]==0 && prime[num-i]==0) {
					g2 = num -i;
					g1 = i;
				}
			}
			System.out.println(g1 + " " + g2);
		}
		
		
	}

}
