//https://judge.koreatech.ac.kr/problem.php?id=1357

import java.util.Scanner;

public class Main {

    public static int[] solve(int n, int m, int[] nums){
        int i=0;
        while(i<=n){
            if(i%m != 0) nums[0] += i;
            else nums[1] += i;
            ++i;
        }
        return nums;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int t=0; t<T; ++t) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] nums = new int[2];
            solve(n, m, nums);
            System.out.println(nums[0] - nums[1]);
        }
    }
}