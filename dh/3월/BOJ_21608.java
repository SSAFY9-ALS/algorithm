import java.io.*;
import java.util.*;
/**
 * 
 * 상어 초등학교 / 골드 5 / 100분
 * https://www.acmicpc.net/problem/21608
 * 
 */
class Info implements Comparable<Info>{
	// 해당 자리에 앉았을때의 정보
	int like;
	int empty;
	int x;
	int y;
	public Info(int like, int empty, int x, int y) {
		super();
		this.like = like;
		this.empty = empty;
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Info o) {
		if(this.like == o.like) {
			if(this.empty == o.empty) {
				if(this.x == o.x) {
					return Integer.compare(this.y, o.y);
					// 작은것 우선
				}
				return Integer.compare(this.x, o.x);
				// 작은것 우선
			}
			return Integer.compare(o.empty, this.empty);
			// 큰것 우선
		}
		return Integer.compare(o.like, this.like);
		// 큰것 우선
	}
	
}
public class Main {
	static int n;
	static int student_size;
	static int[][] student;
	static int[][] school;
	static int[][] like_list;
	static int answer;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		student_size = n*n;
		student = new int[student_size][5]; // 순서를 기준으로 학생의 번호 및 좋아하는 학생의 번호를 저장하는 배열
		school = new int[n+2][n+2];
		like_list = new int[student_size + 1][4]; // 번호 마다의 좋아하는 학생의 번호를 저장하는 배열
		StringTokenizer st = null;
		for(int i = 0; i < student_size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				student[i][j] = Integer.parseInt(st.nextToken());
				// 순서를 기준으로 입력받기
			}
		}
		for(int i = 0; i < student_size; i++) {
			int num = student[i][0];
			for(int j = 0; j < 4; j++) {
				like_list[num][j] = student[i][j+1];
			}
		} // 학생의 번호를 기준으로 저장한 배열 초기화
		
		
		for(int i = 0; i < n+2; i++) {
			Arrays.fill(school[i], -1);
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				school[i][j] = 0;
			}
		} // 자리가 아닌 위치를 -1, 빈 자리를 0으로 초기화
		
		for(int i = 0; i < student_size; i++) {
			seat(student[i][0], i); // 해당 학생의 번호와 순서 인자로 주고 출력
		}
		
		calc();
		// 만족도 계산
		System.out.println(answer);
		
	}
	static void seat(int num, int count) {
		PriorityQueue<Info> pq = new PriorityQueue<>();
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(school[i][j] > 0) // 자리배치되었으면 continue;
					continue;
				int like = 0;
				int empty = 0;
				for(int k = 0; k < 4; k++) {
					int lx = i + dx[k];
					int ly = j + dy[k];
					int s = school[lx][ly];
					if(s == -1) {
						continue;
						// 자리가 아니면 continue;
					}
					if(s == 0) {
						empty++;
						// 빈자리면 empty 증가
					}
					for(int m = 1; m < 5; m++) {
						if(s == student[count][m]) {
							like++;
							break;
							// 좋아하는 학생의 번호이면 like증가 후 break;
						}
					}
				}
				pq.add(new Info(like, empty, i, j));
			}
		}
		Info temp = pq.poll();
		// 규칙에 의해 앉을 자리 구하기
		school[temp.x][temp.y] = num;
		// 해당 위치에 앉을 순서에 해당하는 학생의 번호 앉히기
	}
	static void calc() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				int count = 0;
				for(int k = 0; k < 4; k++) {
					int lx = i + dx[k];
					int ly = j + dy[k];
					int s = school[lx][ly];
					for(int m = 0; m < 4; m++) {
						if(s == like_list[school[i][j]][m])
							// 해당 위치에 앉은 학생이 좋아하는 학생의 번호이면 실행
							count++;
					}
				}
				if(count != 0) {
					answer += (int) Math.pow(10, count-1);
					// 만족도  계산에 맞게 답 구하기
				}
			}
		}
	}
}
