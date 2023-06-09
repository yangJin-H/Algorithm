package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20061_모노미노도미노2 {
	
	static int idx, repeat, score = 0;
	static int[] b, g, btop, gtop;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		b = new int[] {0, 0, 0, 0};
		g = new int[] {0, 0, 0, 0};
		btop = new int[] {0, 0, 0, 0};
		gtop = new int[] {0, 0, 0, 0};
		
		for(int i = 0; i < N; i++) {
			idx = 0; repeat = 0;
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			blue(t, x, y);
			green(t, x, y);
		}
		
		int count = 0;
		for(int i = 0; i < 4; i++) {
			count += Integer.bitCount(b[i]);
			count += Integer.bitCount(g[i]);
		}

		System.out.printf("%d\n%d", score, count);
	}

	private static void green(int t, int x, int y) {
		idx = -1; repeat = 1;
		switch(t) {
		case 1:
			g[y] |= (1<<gtop[y]);
			check(g, gtop[y]);
			gtop[y]++;
			break;
		case 2:
			int h = gtop[y] > gtop[y+1] ? y : y+1;
			int hcol = gtop[h];
			g[y] |= (1<<hcol);
			g[y+1] |= (1<<hcol);
			check(g, hcol);
			gtop[y] = hcol+1;
			gtop[y+1] = hcol+1;
			break;
		case 3:
			g[y] |= (3<<gtop[y]);
			check(g, gtop[y]);
			check(g, gtop[y]+1);
			gtop[y] += 2;
			break;
		}
		
		if(idx != -1) remove(g, gtop);
		int top = 0;
		for(int i = 0; i < 4; i++) {
			if(gtop[i] >= 5) {
				top = top > gtop[i] ? top : gtop[i];
			}
		}
		if(top != 0) {
			for(int i = 0; i < 4; i++) {
				int move = top - 4;
				g[i] >>= move;
				gtop[i] = 0 > gtop[i] - move ? 0 : gtop[i] - move;
			}
		}
	}

	private static void blue(int t, int x, int y) {
		idx = -1; repeat = 1;
		switch(t) {
		case 1:
			b[x] |= (1<<btop[x]);
			check(b, btop[x]);
			btop[x]++;
			break;
		case 2:
			b[x] |= (3<<btop[x]);
			check(b, btop[x]);
			check(b, btop[x]+1);
			btop[x] += 2;
			break;
		case 3:
			int h = btop[x] > btop[x+1] ? x : x+1;
			int hcol = btop[h];
			b[x] |= (1<<hcol);
			b[x+1] |= (1<<hcol);
			check(b, hcol);
			btop[x] = hcol+1;
			btop[x+1] = hcol+1;
			break;
		}
		
		if(idx != -1) remove(b, btop);
		int top = 0;
		for(int i = 0; i < 4; i++) {
			if(btop[i] >= 5) {
				top = top > btop[i] ? top : btop[i];
			}
		}
		System.out.println("top: "+top);
		if(top != 0) {
			for(int i = 0; i < 4; i++) {
				int move = top - 4;
				b[i] >>= move;
				btop[i] = 0 > btop[i] - move ? 0 : btop[i] - move;
			}
		}
	}

	private static void check(int[] board, int col) {
		for(int i = 0; i < 4; i++) {
			if((board[i] & (1<<col)) == 0) return;
		}
		
		if(idx == -1) idx = col;
		else repeat = 2;
		
		score++;
	}

	private static void remove(int[] board, int[] top) {
		for(int i = 0; i < 4; i++) {
			board[i] = (board[i] % (1<<idx)) + ((board[i]>>(idx+repeat))<<(idx));
			for(int j = top[i]; j >= -1; j--) {
				if((board[i] & (1<<j)) != 0 || j == -1) {
					top[i] = j+1;
					break;
				}
			}
		}
	}
}