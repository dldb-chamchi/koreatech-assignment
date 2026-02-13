import java.util.Scanner;

public class JavaHomework2024 {

    //전역 스캐너
    static Scanner in = new Scanner(System.in);

    //잡지 문자열로 랜섬 노트 가능 확인
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] freq = new int[26];  

        
        for (char c : magazine.toCharArray()) {
            freq[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            if (--freq[c - 'a'] < 0) //필요한 문자 부족
                return false;              
        }

        return true;  // 모든 문자를 사용할 수 있으면 true 반환
    }

    //계속 or 중단 체크
    public static boolean checkDone() {
        System.out.print("계속(y/n)? ");
        String input = in.nextLine();
        return !input.equalsIgnoreCase("y");
    }

    public static void main(String[] args) {
        boolean done = false;

        while (!done) {
            System.out.print("랜섬 노트 입력: ");
            String ransomNote = in.nextLine();
            System.out.print("잡지 문자열 입력: ");
            String magazine = in.nextLine();

            if (canConstruct(ransomNote, magazine)) {
                System.out.println("랜섬 노트 작성 가능");
            } else {
                System.out.println("랜섬 노트 작성 불가");
            }

            //계속 여부 확인
            done = checkDone();
        }

        in.close();
    }
}
