import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/** 상어초등학교 / 골드 5 / 1시간 30분
 *
 */
public class BJ_21608_상어초등학교 {
	static final int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};
	static int[][] map;
	static int N, maxSatisfy, good;
	static HashMap<Integer, int[]> students;
	static int[] pos;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 격자의 크기
		N = Integer.parseInt(br.readLine());
		
		// 격자
		map = new int[N][N];
		
		// 순서
		int[] sequence = new int[N*N];
		
		// 학생과 학생이 좋아하는 학생 4명의 번호
		students = new HashMap<>();
		for (int i = 1; i <= N*N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int key = Integer.parseInt(st.nextToken());
			int[] likeStudents = new int[4];
			for (int j = 0; j < 4; j++) {
				likeStudents[j] = Integer.parseInt(st.nextToken());
			}
			
			students.put(key, likeStudents);
			sequence[i-1] = key;
		}

		// 제일 처음 자리는 무조건 1,1 자리
		map[1][1] = sequence[0];

		// 앉을 자리
		pos = new int[2];

		// 순서대로 돌면서 최선의 자리 찾기
		for (int i = 1; i < N*N; i++) {
			pickBestPlace(sequence[i]);
		}

		// 만족도 계산하기
		calSatisfy();
		// 만족도 출력
		System.out.println(good);
	}

	/** 격자 돌면서 최선의 자리 찾는 메소드 */
	static void pickBestPlace(int student) {
		int[] tempPos = new int[2];
		tempPos[0] = -1;
		tempPos[1] = -1;
		// 최대 만족도
		maxSatisfy = 0;
		// 격자를 돌면서 주변에 좋아하는 학생이 있는 것과 많은 칸이 있는지를 고려해 제일 좋은 자리로 정함
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 이미 있는 자리라면 패ㅐㅐㅐ스~~
				if(map[i][j] != 0) continue;

				if(tempPos[0] < 0) {
					tempPos[0] = i;
					tempPos[1] = j;
				}
				// 빈칸 개수 세는 변수
				int vacancy = 0;
				// 자리가 맘에 드는지에 대한 만족도 점수
				int satisfy = 0;
				// 좋아하는 애가 있는지에 대한 변수
				int like = 0;
				// 주변에 좋아하는 학생이 있는지 & 빈칸 개수 세기
				for (int k = 0; k < 4; k++) {
					int dx = i + directions[k][0];
					int dy = j + directions[k][1];

					// 만약 범위 벗어나면 그 다음 위치 탐색
					if(dx < 0 || dx >= N || dy < 0 || dy>= N) continue;

					// 만약 빈방이라면 빈방 체크
					if(map[dx][dy] == 0) vacancy++;
					// 빈방이 아닐 때 내가 좋아하는 애들 중에 있는지 확인
					for (int l = 0; l < 4; l++) {
						// 좋아하는 애가 아니라면 그 다음 좋아하는 애가 맞는지 확인
						if(students.get(student)[l] != map[dx][dy]) continue;
						like += 10;
					}
				}
				// 만족도 = 빈방 개수 + 좋아하는 애
				satisfy = vacancy + like;
				// 만약 빈방과 좋아하는 애를 포함해서 만족도가 제일 높다면 해당 자리로 갱신
				if(maxSatisfy < satisfy) {
					maxSatisfy = satisfy;
					pos[0] = i;
					pos[1] = j;
				}
			}
		}
		if(maxSatisfy == 0) map[tempPos[0]][tempPos[1]] = student;
		// 다 돌고 제일 최선의 자리에 앉는다
		else map[pos[0]][pos[1]] = student;


		// -- debug
//		System.out.println("==============");
//		printMap();
	}

	/** 만족도 구하기 */
	static void calSatisfy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 0이면은 넘어가가ㅣ~~~
				if(map[i][j] == 0) continue;
				// 좋아하는 학생 수 변수
				int likeStudent = 0;
				int student = map[i][j];
				// 그 학생의 주변을 돌아보면서 좋아하는 학생이 몇명인지 세기
				for (int k = 0; k < 4; k++) {
					int dx = i + directions[k][0];
					int dy = j + directions[k][1];

					// 범위 벗어나면 다음 사방탐색
					if(dx < 0 || dx >= N || dy < 0 || dy >= N) continue;

					// -- debug
//					System.out.print("student : " + student);
					// 좋아하는 학생이라면 카운트 업
					for (int l = 0; l < 4; l++) {
						// -- debug
//						System.out.print(students.get(student)[l] + " ");
						if(map[dx][dy]
								== students.get(student)[l]) likeStudent++;
					}
					// -- debug
//					System.out.println();
				}
				// 좋아하는 학생 수 만큼 만족도 업!!
				good += Math.pow(10, likeStudent-1);
			}
		}
	}

	// -- debug
	static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
