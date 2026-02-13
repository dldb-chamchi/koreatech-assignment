//https://judge.koreatech.ac.kr/problem.php?id=1180

import java.util.Scanner;

public class Main {
    public static int solve(int n) {
        int nowN = n;
        int nowIdx = 1;
        int firstSixIdx = 0; //맨 첫자리 6 때문에 도입

        while (nowN > 0) {
            int digit = nowN % 10;
            if (digit == 6) {
                firstSixIdx = nowIdx;  //가장 왼족 6
            }
            nowN /= 10;
            nowIdx *= 10;
        }

        return firstSixIdx == 0 ? n : n + 3 * firstSixIdx;  //가장 왼쪽 6 -> 9
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; ++t) {
            int N = in.nextInt();
            System.out.println(solve(N));
        }
        in.close();
    }
}