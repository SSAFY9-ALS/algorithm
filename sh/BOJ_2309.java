import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_2309_일곱난쟁이 {

    static int[] dwarf;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 아홉명의 난쟁이 중 일곱명의 난쟁이를 골라 키의 합이 100이 되는 경우의 수를 찾아야함
        // 조합
        dwarf = new int[9];
        visited = new boolean[9];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 9 ; i++) {
            dwarf[i] = Integer.parseInt(br.readLine());
        }

        findDwarfs(0, 0);

    }

    public static void findDwarfs(int cnt, int start) {
        if(cnt == 7) {
            heights();
            return;
        }

        for(int i = start; i < 9; i++) {
            if(!visited[i]) {
                visited[i] = true;
                findDwarfs(cnt + 1, start+1);
                visited[i] = false;
            }
        }
    }

    public static void heights() {
        int sum = 0;

        // 방문했는지 체크를 해서 방문했으면 키의 합에 넣기
        for(int i = 0; i < 9; i++){
            if(visited[i]) {
                sum += dwarf[i];
            }
        }
        System.out.println();

        // 난쟁이들의 키의 합이 100이 되었을 때 그 조합들을 정렬해서 출력
        if(sum == 100) {
            int[] height = new int[7];
            int idx = 0;
            for(int i = 0; i < 9; i++){
                if(visited[i]) {
                    height[idx++] = dwarf[i];
                }
            }
            Arrays.sort(height);
            for(int i = 0 ; i < 7; i++){
                System.out.println(height[i]);
            }
            System.exit(0);
        }
    }
}
