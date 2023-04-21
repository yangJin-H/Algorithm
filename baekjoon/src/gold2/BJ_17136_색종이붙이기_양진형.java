package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_17136_색종이붙이기_양진형 {
	
	static boolean[][] paper;
	static int[] left;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		paper = new boolean[10][10];
		for(int i = 0 ; i < 10; i++) {
			String str = br.readLine();
			for(int j = 0; j < 10; j++) {
				paper[i][j] = str.charAt(j*2) == '1';
			}
		}
		left = new int[] {0, 5, 5, 5, 5, 5};
		ArrayList<int[]>[] list = new ArrayList[6];
		for(int i = 1; i <= 5; i++) {
			list[i] = new ArrayList<int[]>();
		}
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(!paper[i][j]) continue;
				int len = -1;
				for(int k = 1; k < 5; k++) {
					int nx = i+k;
					int ny = j+k;
					if(nx >= 10 || ny >= 10) break;
					for(int r = i; r <= nx; r++) {
						if(!paper[r][ny]) {
							len = k;
							break;
						}
					}
					if(len == -1) {
						for(int c = j; c <= ny; c++) {
							if(!paper[nx][c]) {
								len = k;
								break;
							}
						}
					}
					
					if(len != -1) break;
					if(len == -1 && k == 4) len = 5;
				}
				list[len].add(new int[] {i, j});
			}
		}
		
		
		
//		for(boolean[] row: paper) {
//			for(boolean e : row) {
//				System.out.print(e==false?"F ":"T ");
//			}
//			System.out.println();
//		}
//		System.out.println();
	}
}
