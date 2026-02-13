//https://judge.koreatech.ac.kr/problem.php?id=1358

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try(
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ){
            int T = Integer.parseInt(in.readLine());
            for(int t = 0; t < T; ++t) {
                in.readLine();
                Set<Integer> nums = new HashSet<>();
                nums = Arrays.stream(in.readLine().split(" ")).map(Integer::parseInt)
                        .collect(HashSet::new, HashSet::add, HashSet::addAll); //set 자료구조

                Integer[] sorted = nums.toArray(new Integer[nums.size()]);
                Arrays.sort(sorted, Collections.reverseOrder()); //내림차순

                if(sorted.length < 3) System.out.println(sorted[0]);
                else System.out.println(sorted[2]);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}