package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_5373_큐빙 {
	
	static char[][][] cube;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			init();
			int n = Integer.parseInt(br.readLine());
			char[] str = br.readLine().toCharArray();
			for(int i = 0; i < n; i++) {
				int side = -1;
				int dir = -1;
				switch(str[i*3]) {
				case 'U':
					side = 0;
					break;
				case 'D':
					side = 1;
					break;
				case 'F':
					side = 2;
					break;
				case 'B':
					side = 3;
					break;
				case 'L':
					side = 4;
					break;
				case 'R':
					side = 5;
					break;
				}
				
				switch(str[i*3+1]) {
				case '+': dir = 0; break;
				case '-': dir = 1; break;
				}
				
				rotate(side, dir);
			}
			sb.append(cube[0][0][0]).append(cube[0][0][1]).append(cube[0][0][2]).append("\n");
			sb.append(cube[0][1][0]).append(cube[0][1][1]).append(cube[0][1][2]).append("\n");
			sb.append(cube[0][2][0]).append(cube[0][2][1]).append(cube[0][2][2]).append("\n");
		}
		System.out.println(sb);
	}

	private static void init() {
		cube = new char[6][3][3];
		cube[0] = new char[][] {{'w','w','w'}, {'w','w','w'}, {'w','w','w'}};
		cube[1] = new char[][] {{'y','y','y'}, {'y','y','y'}, {'y','y','y'}};
		cube[2] = new char[][] {{'r','r','r'}, {'r','r','r'}, {'r','r','r'}};
		cube[3] = new char[][] {{'o','o','o'}, {'o','o','o'}, {'o','o','o'}};
		cube[4] = new char[][] {{'g','g','g'}, {'g','g','g'}, {'g','g','g'}};
		cube[5] = new char[][] {{'b','b','b'}, {'b','b','b'}, {'b','b','b'}};
	}

	private static void rotate(int side, int dir) {
		char[][] mside = cube[side];
		if(dir == 0) {
			char tmp1 = mside[0][0];
			char tmp2 = mside[0][1];
			mside[0][0] = mside[2][0];
			mside[0][1] = mside[1][0];
			mside[2][0] = mside[2][2];
			mside[1][0] = mside[2][1];
			mside[2][2] = mside[0][2];
			mside[2][1] = mside[1][2];
			mside[0][2] = tmp1;
			mside[1][2] = tmp2;
		} else if(dir == 1) {
			char tmp1 = mside[0][0];
			char tmp2 = mside[0][1];
			mside[0][0] = mside[0][2];
			mside[0][1] = mside[1][2];
			mside[0][2] = mside[2][2];
			mside[1][2] = mside[2][1];
			mside[2][2] = mside[2][0];
			mside[2][1] = mside[1][0];
			mside[2][0] = tmp1;
			mside[1][0] = tmp2;
		}
		
		if(side == 0) {
			char[] tmp = cube[3][0];
			if(dir == 0) {
				cube[3][0] = cube[4][0];
				cube[4][0] = cube[2][0];
				cube[2][0] = cube[5][0];
				cube[5][0] = tmp;
			} else if(dir == 1) {
				cube[3][0] = cube[5][0];
				cube[5][0] = cube[2][0];
				cube[2][0] = cube[4][0];
				cube[4][0] = tmp;
			}
		} else if(side == 1) {
			char[] tmp = cube[3][2];
			if(dir == 0) {
				cube[3][2] = cube[5][2];
				cube[5][2] = cube[2][2];
				cube[2][2] = cube[4][2];
				cube[4][2] = tmp;
			} else if(dir == 1) {
				cube[3][2] = cube[4][2];
				cube[4][2] = cube[2][2];
				cube[2][2] = cube[5][2];
				cube[5][2] = tmp;
			}
		} else if(side == 2) {
			char a, b, c;
			a = cube[0][2][0];
			b = cube[0][2][1];
			c = cube[0][2][2];
			if(dir == 0) {
				cube[0][2][0] = cube[4][2][2];
				cube[0][2][1] = cube[4][1][2];
				cube[0][2][2] = cube[4][0][2];
				cube[4][2][2] = cube[1][2][0];
				cube[4][1][2] = cube[1][2][1];
				cube[4][0][2] = cube[1][2][2];
				cube[1][2][0] = cube[5][0][0];
				cube[1][2][1] = cube[5][1][0];
				cube[1][2][2] = cube[5][2][0];
				cube[5][0][0] = a;
				cube[5][1][0] = b;
				cube[5][2][0] = c;
			} else if(dir == 1) {
				cube[0][2][0] = cube[5][0][0];
				cube[0][2][1] = cube[5][1][0];
				cube[0][2][2] = cube[5][2][0];
				cube[5][0][0] = cube[1][2][0];
				cube[5][1][0] = cube[1][2][1];
				cube[5][2][0] = cube[1][2][2];
				cube[1][2][2] = cube[4][0][2];
				cube[1][2][1] = cube[4][1][2];
				cube[1][2][0] = cube[4][2][2];
				cube[4][2][2] = a;
				cube[4][1][2] = b;
				cube[4][0][2] = c;
			}
		} else if(side == 3) {
			char a, b, c;
			a = cube[0][0][0];
			b = cube[0][0][1];
			c = cube[0][0][2];
			if(dir == 0) {
				cube[0][0][0] = cube[5][0][2];
				cube[0][0][1] = cube[5][1][2];
				cube[0][0][2] = cube[5][2][2];
				cube[5][0][2] = cube[1][0][0];
				cube[5][1][2] = cube[1][0][1];
				cube[5][2][2] = cube[1][0][2];
				cube[1][0][2] = cube[4][0][0];
				cube[1][0][1] = cube[4][1][0];
				cube[1][0][0] = cube[4][2][0];
				cube[4][2][0] = a;
				cube[4][1][0] = b;
				cube[4][0][0] = c;
			} else if(dir == 1) {
				cube[0][0][0] = cube[4][2][0];
				cube[0][0][1] = cube[4][1][0];
				cube[0][0][2] = cube[4][0][0];
				cube[4][2][0] = cube[1][0][0];
				cube[4][1][0] = cube[1][0][1];
				cube[4][0][0] = cube[1][0][2];
				cube[1][0][0] = cube[5][0][2];
				cube[1][0][1] = cube[5][1][2];
				cube[1][0][2] = cube[5][2][2];
				cube[5][0][2] = a;
				cube[5][1][2] = b;
				cube[5][2][2] = c;
			}
		} else if(side == 4) {
			char a, b, c;
			a = cube[0][0][0];
			b = cube[0][1][0];
			c = cube[0][2][0];
			if(dir == 0) {
				cube[0][0][0] = cube[3][2][2];
				cube[0][1][0] = cube[3][1][2];
				cube[0][2][0] = cube[3][0][2];
				cube[3][0][2] = cube[1][0][2];
				cube[3][1][2] = cube[1][1][2];
				cube[3][2][2] = cube[1][2][2];
				cube[1][2][2] = cube[2][0][0];
				cube[1][1][2] = cube[2][1][0];
				cube[1][0][2] = cube[2][2][0];
				cube[2][0][0] = a;
				cube[2][1][0] = b;
				cube[2][2][0] = c;
			} else if(dir == 1) {
				cube[0][0][0] = cube[2][0][0];
				cube[0][1][0] = cube[2][1][0];
				cube[0][2][0] = cube[2][2][0];
				cube[2][2][0] = cube[1][0][2];
				cube[2][1][0] = cube[1][1][2];
				cube[2][0][0] = cube[1][2][2];
				cube[1][0][2] = cube[3][0][2];
				cube[1][1][2] = cube[3][1][2];
				cube[1][2][2] = cube[3][2][2];
				cube[3][2][2] = a;
				cube[3][1][2] = b;
				cube[3][0][2] = c;
			}
		} else if(side == 5) {
			char a, b, c;
			a = cube[0][0][2];
			b = cube[0][1][2];
			c = cube[0][2][2];
			if(dir == 0) {
				cube[0][0][2] = cube[2][0][2];
				cube[0][1][2] = cube[2][1][2];
				cube[0][2][2] = cube[2][2][2];
				cube[2][2][2] = cube[1][0][0];
				cube[2][1][2] = cube[1][1][0];
				cube[2][0][2] = cube[1][2][0];
				cube[1][0][0] = cube[3][0][0];
				cube[1][1][0] = cube[3][1][0];
				cube[1][2][0] = cube[3][2][0];
				cube[3][2][0] = a;
				cube[3][1][0] = b;
				cube[3][0][0] = c;
			} else if(dir == 1) {
				cube[0][2][2] = cube[3][0][0];
				cube[0][1][2] = cube[3][1][0];
				cube[0][0][2] = cube[3][2][0];
				cube[3][0][0] = cube[1][0][0];
				cube[3][1][0] = cube[1][1][0];
				cube[3][2][0] = cube[1][2][0];
				cube[1][2][0] = cube[2][0][2];
				cube[1][1][0] = cube[2][1][2];
				cube[1][0][0] = cube[2][2][2];
				cube[2][0][2] = a;
				cube[2][1][2] = b;
				cube[2][2][2] = c;
			}
		}
	}
}
