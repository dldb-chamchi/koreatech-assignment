//https://judge.koreatech.ac.kr/problem.php?id=1359

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void debugPrint(int[] nums){
        for(var n : nums){
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void solve(int[] nums){
        int[] countNums = new int[101];
        for(var n : nums) ++countNums[n];
        int even = 0;
        for(var n : countNums){
            if(n > 1) even += n/2;
        }
        System.out.printf("%d %d", even, nums.length - 2*even);
    }

    public static void main(String[] args) {
        try(
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ){
            int T = Integer.parseInt(in.readLine());
            for(int t = 0; t < T; ++t) {
                in.readLine();
                int[] nums = Arrays.stream(in.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                //debugPrint(nums);
                solve(nums);
                System.out.print('\n');
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}