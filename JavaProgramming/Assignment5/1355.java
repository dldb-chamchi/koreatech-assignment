//https://judge.koreatech.ac.kr/problem.php?id=1355

import java.util.Scanner;

public class Main {
    public static void debugPrint(int[] nums){
        System.out.println("입력한 숫자 배열: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void solve(int[] nums){
        //i = j보다 항상 1 앞서는 반복자
        int i=1, j=0;
        while(i<nums.length){
            if(nums[i] < nums[j]) {
                if ((nums[i] % 2 == 0 && nums[j] % 2 == 0) || (nums[i] % 2 != 0 && nums[j] % 2 != 0)){
                    swap(nums, i, j);
                    break;
                }
            }
            ++i; ++j;
        }
        print(nums);
    }

    public static void print(int[] nums){
        for(int i=0; i<nums.length; ++i){ //foreach문은 nums배열의 길이가 테스트케이스마다 달라질 수 있어서 사용x
            System.out.print(nums[i]);
        }
        System.out.print('\n');
    }


    public static void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine(); //버퍼
        for(int t=0; t<T; ++t){
            String input = in.nextLine();
            int[] nums = new int[input.length()];
            for(int i=0; i<nums.length; ++i) nums[i] = Character.getNumericValue(input.charAt(i));
            solve(nums);
        }
    }
}