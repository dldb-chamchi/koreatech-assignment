//https://judge.koreatech.ac.kr/problem.php?id=1354


import java.util.Scanner;

public class Main {
    public static int solve(int n) {
        int result = 0;
        int bitPosition = 0;

        while (n > 0) {
            //현재 비트가 0이면 1로, 1이면 0으로 설정
            if ((n & 1) == 0) {
                result |= (1 << bitPosition);
            }
            //다음 비트로 이동
            n >>= 1;
            ++bitPosition;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int t=0; t<T; ++t) {
            int N = in.nextInt();
            System.out.println(solve(N));
        }
    }
}