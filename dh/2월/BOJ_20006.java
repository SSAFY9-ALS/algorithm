import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * 랭킹전 대기열 / 실버2 / 40분
 * https://www.acmicpc.net/problem/20006
 *
 */
class Player implements Comparable<Player>{
	int I;
	String n;
	public Player(int i, String n) {
		super();
		I = i;
		this.n = n;
	}
	@Override
	public int compareTo(Player o) {
		return n.compareTo(o.n);
		// 닉네임 순 정렬
	}
	
	
}
public class Main {
	static int p, m;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		List<List<Player>> list = new ArrayList<>();
		out: for(int index = 0; index < p; index++) {
			st = new StringTokenizer(br.readLine());
			int I = Integer.parseInt(st.nextToken());
			String n = st.nextToken();
			
			for(int i = 0; i < list.size(); i++) {
				int size = list.get(i).size();
				int tmp = list.get(i).get(0).I;
				if(size < m && I+10 >= tmp && I-10 <= tmp) {
					// 리스트가 가득 있지 않고 레벨이 10보다 크거나 작은 값 안에 있으면 실행
					list.get(i).add(new Player(I, n));
					continue out;
				}
			}
			// 리스트를 탐색 후 들어갈 방이 없으면 방 새로 생성
			list.add(new ArrayList<Player>());
			list.get(list.size() - 1).add(new Player(I, n));
			// 새로 생긴 방에 플레이어 추가
		}
		for(int i = 0; i < list.size(); i++) {
			Collections.sort(list.get(i));
			// 닉네임 순 정렬
		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).size() == m) {
				System.out.println("Started!");
				for(int j = 0; j < list.get(i).size(); j++) {
					System.out.println(list.get(i).get(j).I + " " + list.get(i).get(j).n);
				}
			}
			else {
				System.out.println("Waiting!");
				for(int j = 0; j < list.get(i).size(); j++) {
					System.out.println(list.get(i).get(j).I + " " + list.get(i).get(j).n);
				}
			}
			// 형식에 맞게 출력
		}
	}
}