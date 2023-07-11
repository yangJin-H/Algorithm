package baekjoon.src.gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ_2206_벽부수고이동하기 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[N][M];
        for(int r = 0; r < N; r++) {
            char[] arr = br.readLine().toCharArray();
            for(int c = 0; c < M; c++) {
                map[r][c] = arr[c] == '1';
            }
        }

        boolean[][][] visited = new boolean[N][M][2];
        visited[0][0][1] = true;
        visited[0][0][0] = true;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, 0, 1, 1});
        int ans = -1;
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];
            int c = p[2];
            int t = p[3];

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx == N - 1 && ny == M - 1) {
                    queue.clear();
                    ans = t+1;
                    break;
                }
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny][c]) continue;
                if(!map[nx][ny]) {
                    queue.add(new int[] {nx, ny, c, t+1});
                    visited[nx][ny][c] = true;
                } else if(c == 1) {
                    queue.add(new int[] {nx, ny, 0, t+1});
                    visited[nx][ny][1] = true;
                    visited[nx][ny][0] = true;
                    visited[x][y][0] = true;
                }
            }
        }
        if(N == 1 && M == 1) ans = 1;
        System.out.println(ans);
    }
}
