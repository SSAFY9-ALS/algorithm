import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 실버5
 */

public class BJ_10867_중복빼고정렬하기_소수현 {
    public static void main(String[] args) throws IOException {
         /*
         수의개수(N) 입력 받기
         수 저장하는 배열 선언하기 : numbers[]
         for(0~N) { numbers[i] = 입력받은 값; 앞에 있는 값이 중복이면 건너뛰기; }
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // 수의 개수 입력받기
        int N = Integer.parseInt(br.readLine());

        int[] checkNum1 = new int[1001];
        int[] checkNum2 = new int[1001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            // 입력받은 숫자가 음의 정수이면 입력받은 숫자의 절댓값을 checkNum 인덱스로 하고 음이라는 표시로 2번쨰 인덱스에 -1을 넣어준다.
            if (num < 0) {
                checkNum2[Math.abs(num)] = -1;
            }
            // 입력받은 숫자가 양의 정수이면 입력받은 숫자의 절댓값을 checkNum 인덱스로 하고 양이라는 표시로 1번째 인덱스에 +1을 넣어준다.
            else {
                checkNum1[Math.abs(num)] = 1;
            }
        }

        for (int i = 1000; i > 0; i--) {
            if(checkNum2[i] < 0) {
                int result = -i;
                sb.append(result).append(" ");
            }
        }

        for(int i = 0; i <= 1000; i++) {
            if(checkNum1[i] > 0) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb.toString().trim());
    }
}
