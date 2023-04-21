package gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1562_계단수 {
	
	static int N, count;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		count = 0;
		for(int i = 1; i <= 9; i++) {
			dfs(i, 1<<i, N-1, 1);
		}
		
		System.out.println(count);
	}

	private static void dfs(int last, int selected, int left, int selcount) {
		if(left < 10 - selcount) return;
		if(left == 0) {
			if(selected == (1<<10)-1) count++;
			return;
		}
		
		if(last != 0) {
			int nselc = selcount;
			if((selected & 1<<(last-1)) != 1) nselc++;
			dfs(last-1, selected | 1<<(last-1), left-1, nselc);
		}
		if(last != 9) {
			int nselc = selcount;
			if((selected & 1<<(last+1)) != 1) nselc++;
			dfs(last+1, selected | 1<<(last+1), left-1, nselc);
		}
	}
}
