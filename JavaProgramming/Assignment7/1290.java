//https://judge.koreatech.ac.kr/problem.php?id=1290

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int[] calcPFreq(char[] pToArray, int[] pFreq){
        for(var c : pToArray) ++pFreq[c-'a'];
        return pFreq;
    }

    public static void solve(char[] sToArray, char[] pToArray, int[] pFreq){
        List<Integer> result = new ArrayList<>();
        int[] sFreq = new int[26];
        for(int i=0; i<pToArray.length; ++i){
            ++sFreq[sToArray[i]-'a'];
        }
        if(Arrays.equals(sFreq, pFreq)) result.add(0);

        for(int i= pToArray.length; i<sToArray.length; ++i){
            --sFreq[sToArray[i-pToArray.length]-'a'];
            ++sFreq[sToArray[i]-'a'];
            if(Arrays.equals(sFreq, pFreq)) result.add(i-pToArray.length+1);
        }

        for(var n : result){
            System.out.print(n);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        while(T-- > 0) {
            String s = in.nextLine();
            String p = in.nextLine();
            char[] sToArray = s.toCharArray();
            char[] pToArray = p.toCharArray();

            int[] pFreq = new int[26];
            calcPFreq(pToArray, pFreq);
            solve(sToArray, pToArray, pFreq);
        }
    }
}