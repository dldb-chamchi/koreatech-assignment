//https://judge.koreatech.ac.kr/problem.php?id=1179

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static StringBuilder solve(String s){
        StringBuilder result = new StringBuilder();
        int i = s.length()-1;
        while(i >= 0){
            if(s.charAt(i) == '#'){
                int num = Integer.parseInt(s.substring(i-2, i));
                result.append((char) ('a'+num-1));
                i-=3;
            }
            else{
                int num = s.charAt(i) -'0';
                result.append((char) ('a'+num-1));
                --i;
            }
        }
        //result = result.reverse();
        return result.reverse();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        while (T-- > 0) {
            //StringBuilder results = new StringBuilder();
            String s = in.nextLine();
            solve(s);
            System.out.println(solve(s));
        }
    }
}