package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14391_종이조각 {
	
	static int N, M, max;
	static int[][] paper;
	static boolean[][] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		paper = new int[N][M];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				paper[i][j] = str.charAt(j) - '0';
			}
		}
		
		max = Integer.MIN_VALUE;
		for(int i = 0; i < (1<<(N*M)); i++) {
			cut(i);
		}
		
		System.out.println(max);
	}

	private static void cut(int selected) {
		int sum = 0;
		boolean[][] visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j]) continue;
				int num = paper[i][j];
				visited[i][j] = true;
				if((selected & (1<<(i*M+j))) == 0) {
					for(int k = i+1; k < N; k++) {
						if((selected & (1<<(k*M+j))) == 0) {
							num *= 10;
							num += paper[k][j];
							visited[k][j] = true;
						} else break;
					}
				} else {
					for(int k = j+1; k < M; k++) {
						if((selected & (1<<(i*M+k))) != 0) {
							num *= 10;
							num += paper[i][k];
							visited[i][k] = true;
						} else break;
					}
				}
				sum += num;
			}
		}
		max = max > sum ? max : sum;
	}
}
