import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static StringBuilder sb = new StringBuilder();

    static int N, M, numHouse, maxK, cost;
    static List<int[]> HouseIdx;


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(input.readLine());
        for (int t = 1; t <= T; t++) {
            token = new StringTokenizer(input.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());

            numHouse = 0;
            HouseIdx = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                token = new StringTokenizer(input.readLine());
                for (int j = 0; j < N; j++) {
                    int temp = Integer.parseInt(token.nextToken());
                    if (temp == 1) {
                        HouseIdx.add(new int[]{j, i});
                        numHouse++;
                    }
                }
            }

            // 미리 최대 k 계산
            maxK = 1;
            while (maxK * maxK + (maxK - 1) * (maxK - 1) <= numHouse * M) {
                maxK++;
            }

            int res = 0;
            for (int k=1; k <= maxK; k++) {
                cost = k*k+(k-1)*(k-1);
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        res = Math.max(res, cntHouse(i, j, k));

                    }
                }
            }
            sb.append("#").append(t).append(" ").append(res).append("\n");
        }
        System.out.println(sb);
    }
    public static int cntHouse(int x, int y, int k){
        int cnt = 0;
        for (int[] idx : HouseIdx) {
            int hx = idx[0];
            int hy = idx[1];
            if (Math.abs(hx - x)+Math.abs(hy - y)<=k-1) {
                cnt++;
            }
        }
        if (cnt*M >= cost){
            return cnt;
        }
        return 0;
    }
}