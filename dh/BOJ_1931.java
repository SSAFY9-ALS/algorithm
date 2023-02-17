import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* 
 * 회의실 배정 / 실버1 / 25분
 * https://www.acmicpc.net/problem/1931
 */

class Meeting {
	int x;
	int y;

	public Meeting(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Meeting [x=" + x + ", y=" + y + "]";
	}
}

public class Main {
	static int n;
	static List<Meeting> list;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		// 0 -> 앞 숫자, 1 -> 뒤 숫자

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		list.sort(new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				if (o1.y == o2.y)
					return o1.x - o2.x;
				return o1.y - o2.y;
				// y 값이 같으면 x기준으로 정렬
			}
		});
		int count = 0;
		int num = 0;
		for (int i = 0; i < n; i++) {
			if(num <= list.get(i).x) {
				// 기준 값보다 i번째 x값이 크거나 같으면 기준 값을 해당 y값으로 대체
				num = list.get(i).y;
				count++;
				// 횟수 증가
			}
		}
		System.out.println(count);

	}
}
