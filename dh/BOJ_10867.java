import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<Integer> set = new HashSet<>();
		int n = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			set.add(Integer.parseInt(s[i]));
		}
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		
		for(int i : list)
			System.out.print(i + " ");
		
	}
}