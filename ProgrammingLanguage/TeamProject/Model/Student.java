package Model;

// 15. 상속
public class Student extends Member {
    private String major;

    public Student(String name, String major) {
        super(name);
        this.major = major;
    }

    // 8. 오버라이드, 16. 동적바인딩
    @Override
    public void printInfo() {
        System.out.printf("%d번 | %s | %s", getId(), getName(), major);
    }

}
