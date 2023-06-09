package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_19235_모노미노도미노 {
	
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
			System.out.println("=========b=========");
			System.out.println("idx: "+idx+", repeat: "+repeat);
			System.out.println(Arrays.toString(btop));
			for(int be : b) {
				System.out.println(Integer.toBinaryString(be));
			}
			green(t, x, y);
			System.out.println("=========g=========");
			System.out.println("idx: "+idx+", repeat: "+repeat);
			System.out.println(Arrays.toString(gtop));
			for(int be : g) {
				System.out.println(Integer.toBinaryString(be));
			}
			System.out.println();
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
		int gt = 0;
		switch(t) {
		case 1:
			gt = gtop[y] + 1;
			g[y] |= (1<<gtop[y]);
			check(g, gt);
			gtop[y] = gt;
			break;
		case 2:
			int h = gtop[y] > gtop[y+1] ? y : y+1;
			gt = gtop[h] + 1;
			g[y] |= (1<<gtop[h]);
			g[y+1] |= (1<<gtop[h]);
			check(g, gt);
			gtop[y] = gt;
			gtop[y+1] = gt;
			break;
		case 3:
			gt = gtop[y];
			g[y] |= (3<<gtop[y]);
			check(g, gt);
			check(g, gt+1);
			gtop[y] = gt + 2;
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
		int gt = 0;
		switch(t) {
		case 1:
			gt = btop[x] + 1;
			b[x] |= (1<<btop[x]);
			check(b, gt);
			btop[x] = gt;
			break;
		case 2:
			gt = btop[x];
			b[x] |= (3<<btop[x]);
			check(b, gt);
			check(b, gt+1);
			btop[x] = gt + 2;
			break;
		case 3:
			int h = btop[x] > btop[x+1] ? x : x+1;
			gt = btop[h] + 1;
			b[x] |= (1<<btop[h]);
			b[x+1] |= (1<<btop[h]);
			check(b, gt);
			btop[x] = gt;
			btop[x+1] = gt;
			break;
		}
		
		if(idx != -1) remove(b, btop);
		int top = 0;
		for(int i = 0; i < 4; i++) {
			if(btop[i] >= 5) {
				top = top > btop[i] ? top : btop[i];
			}
		}
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