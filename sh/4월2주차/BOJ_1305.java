import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 광고 / 플레 4 / 2시간
 */
public class BJ_1305_광고 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        // KMP 알고리즘
        // 접두사와 접미사 동일한 문자열 최대 길이 구하기
        int lastPi = getLastPi(str);
        // 총 문자열 길이에서 접두사와 접미사가 일치하는 길이를 뺀 길이가 가능한 광고의 길이 중 가장 짧은 길이
        System.out.println(n - lastPi);
    }

    static int getLastPi(String str) {
        int len = str.length();
        int j = 0;// 접두사 탐색 시작 인덱스
        int[] pi = new int[len];

        // i : 1에서부터 이동하면서 접두사와 일치하는 부분이 있는지 찾을 때 사용하는 인덱스
        for(int i = 1; i < len; i++) {
            // 만약에 이전에 접두사랑 비교하는 뒤의 문자가 비슷해서 계속 찾고 있는 상태였다가
            // 접두사와 다른 문자를 만났다면 접두사는 다시 이전에 일치했던 부분으로 이동한다.
            while(j > 0 && str.charAt(j) != str.charAt(i)) {
                j = pi[j - 1];
            }

            // 접두사와 일치하는 문자라면 pi 배열에 j를 +1한 값을 넣어준다.
            if(str.charAt(j) == str.charAt(i)) {
                pi[i] = ++j;
            }
        }

        // 마지막에 있는 자리에 있는 숫자가 접두사와 접미사가 동일한 문자열 최대 길이
        return pi[len - 1];
    }
}
