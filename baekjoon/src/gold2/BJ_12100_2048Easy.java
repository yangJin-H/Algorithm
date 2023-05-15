package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_12100_2048Easy {
	
	static class Block {
		int n, p;

		public Block(int n, int p) {
			this.n = n;
			this.p = p;
		}
	}
	
	static ArrayList<Block>[] rlist, llist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		rlist = new ArrayList[N];
		llist = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			rlist[i] = new ArrayList<Block>();
			llist[i] = new ArrayList<Block>();
		}
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				rlist[i].add(new Block(n, j));
				llist[j].add(new Block(n, i));
			}
		}
		
		
	}
}
