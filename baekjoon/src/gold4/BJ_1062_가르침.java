package baekjoon.src.gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1062_가르침 {
	
	static int N, K, c, ans;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		String str = "antic";
		c = 0;
		for(int i = 0; i < 5; i++) {
			c |= 1<<(str.charAt(i) - 'a');
		}

		list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			s = s.substring(4, s.length() - 4);
			int t = 0;
			for(int j = 0; j < s.length(); j++) {
				t |= 1<<(s.charAt(j) - 'a');
			}
			list.add(t);
		}
		
		ans = 0;
		if(K >= 5) {
			comb(5, c);
		}
		System.out.println(ans);
	}

	public static void comb(int num, int selected) {
		if(num == K) {
			int count = 0;
			for(int word : list) {
				int readable = word & selected;
				if(word == readable) count++;
			}
			ans = Math.max(ans, count);
			return;
		}

		for(int i = 0; i < 26; i++) {
			if((selected & 1<<i) != 0) continue;
			comb(num+1, (selected | 1<<i));
		}
	}
}
