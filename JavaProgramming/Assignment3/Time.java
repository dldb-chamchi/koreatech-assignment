//자바프로그래밍 Time 클래스 구현
//질문에 대한 답변은 코드 맨마지막 줄에

//Time 클래스
public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int hour){
        this(hour, 0, 0);
    }

    public Time(int hour, int minute){
        this(hour, minute, 0);
    }

    public Time(int hour, int minute, int second){
        if(hour >= 0 && minute >= 0 && second >= 0){
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }
    }

    public boolean isPM(){
        return hour >= 12;
    }

    public Time next(int h){
        int tmph = h + hour;
        if(tmph < 0) tmph += 24; //날짜 개념이 없으니 hour이 마이너스이면 전날이라는 가정하에 +24
        else tmph = (tmph) % 24;
        return new Time(tmph, minute, second);
    }
    
    public Time next(int h, int m){
        int tmpm = (m + minute) % 60;
        if(tmpm < 0){
            tmpm += 60; //음수 분 처리
            --hour; //hour가 음수여도 전날이라는 가정하에 --hour (ex. 9월30 오전 1시 00분에서 -2시간 -1분이라면 9월29일 22시 59분)
        }
        int carry = (m + minute) / 60;
        minute = tmpm;
        return next(h + carry); // next(int h)를 재사용하여 중복 제거
    }
    

    @Override public String toString(){
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    static void testTime(){
        Time lunchTime = new Time(12, 30); //12:30:00
        System.out.println(lunchTime);
        System.out.println(lunchTime.isPM()); //true
        lunchTime = lunchTime.next(-12, -31); //23:59:00
        System.out.println(lunchTime);
        Time testTime = new Time(1, 0);
        Time testTime2 = new Time(9, 30, 59); //09:30:59
        System.out.println(testTime.next(-2, -1)); //22:59:00
        System.out.println(testTime2);
        Time testTime3 = new Time(-5, -5, -5); //00:00:00 잘못된 값은 0으로 초기화
        System.out.println(testTime3);
        
        }

    public static void main(String[] args) throws Exception {
        testTime();
    }
}


//질문에 대한 답변

/*
 * 1. 질문
 * 불변 객체를 만들 때 멤버 변수가 객체 이면서 불변 객체가 아닐 경우 외부에서 객체의 상태를 변경할 수 있다.
 * 깊은복사를 사용하거나, 해당 멤버변수 객체도 불변객체로 만든다.
 * 
 * 2. 질문
 *  자주 사용되는 객체가 제한적일 때, 정해진 범위 내의 객체만 자주 사용될 때(0~23시를 유지하는 객체가 많이 사용되면 이 객체들을 객체풀에 유지)
 * 객체 생성 비용이 클 때, 객체의 수명이 짧고 자주 생성될 때(객체 풀을 활용해 매번 생성되고 반납되는 비용을 절약)
 */
