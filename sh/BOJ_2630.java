import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 색종이 만들기 / 실버2 / 1시간
 * https://www.acmicpc.net/problem/2630
 */
public class BJ_2630_색종이만들기 {

    static int white = 0;
    static int blue = 0;
    static int[][] box;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        box = new int[N][N];

        StringTokenizer st;

        for(int i =0 ;i  < N ;i++) {
            st = new StringTokenizer(br.readLine());

            for(int j =0; j< N; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        divide(0, 0, N);

        System.out.println(white);
        System.out.println(blue);
    }

    public static void divide(int row, int col, int size) {
        if (isColor(row, col, size)) {
            if(box[row][col] == 0){
                white++;
            } else {
                blue++;
            }
            return;
        }
        // 크기 절반으로 줄임
        int nSize = size / 2;

        // 2사분면
        divide(row, col, nSize);
        // 1사분면
        divide(row, col + nSize, nSize);
        // 3사분면
        divide(row + nSize, col, nSize);
        // 4사분면
        divide(row + nSize, col + nSize, nSize);

    }

    public static boolean isColor(int row, int col, int size) {
        int color = box[row][col];

        for(int i = row; i < row + size; i++) {
            for(int j = col; j < col + size; j++) {
                // 색상이 다르면 false
                if (box[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
