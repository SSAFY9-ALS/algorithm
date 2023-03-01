package TwoPointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 좋다 / 골드 4 / 1시간
 * https://www.acmicpc.net/problem/1253
 */
public class BJ_1253_좋다 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 수의 개수 : N
        int N = Integer.parseInt(br.readLine());

        // 수 입력받기
        int[] numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            numbers[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        // 좋은 수의 개수
        int good = 0;
        // 수의 합
        int sum;


        for (int i  = 0; i < N; i++) {
            int firstPoint = 0;
            int secondPoint = N-1;

            while (true) {
                // 가리키는 포인터가 나와 같을 때
                // 1. firstPoint가 나랑 같다면 오른쪽으로 이동시켜줌
                if(firstPoint == i) firstPoint++;
                // 2. secondPoint가 나랑 같다면 왼쪽으로 이동시켜줌
                else if (secondPoint == i) secondPoint--;

                // firstPoint가 secondPoint보다 오른쪽이거나 같은 위치면 반복문 벗어남
                if(firstPoint >= secondPoint) break;

                sum = numbers[firstPoint] + numbers[secondPoint];
                if (numbers[i] == sum) {
                    good++;
                    break;
                }
                // 1. 해당 자리의 숫자가 합보다 크면 왼쪽 포인터를 오른쪽으로 이동시켜줌
                else if (numbers[i] > sum) {
                    firstPoint++;
                }
                // 2. 해당 자리의 숫자가 합보다 작으면 오른쪽 포인터를 왼쪽으로 이동시켜줌
                else if (numbers[i] < sum) {
                    secondPoint--;
                }
            }
        }

        System.out.println(good);
    }
}
