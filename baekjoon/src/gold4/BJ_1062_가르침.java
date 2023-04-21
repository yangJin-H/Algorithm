package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1062_가르침 {
	
	static int N, K, c, p, ans;
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
		
		p = c;
		list = new ArrayList<Integer>();
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			s = s.substring(4, s.length() - 4);
			int t = 0;
			for(int j = 0; j < s.length(); j++) {
				t |= 1<<(s.charAt(j) - 'a');
			}
			p |= t;
			list.add(t);
		}
		
		ans = 0;
		if(K >= 5) {
			dfs(0);
		}
		System.out.println(ans);
	}

	private static void dfs(int cnt) {
		if(cnt == K) {
			
			return;
		}
		
		for(int i = 0; i < 26; i++) {
			if((p & 1<<i) != 1) continue;
			
			dfs(cnt+1);
			
		}
	}
}
