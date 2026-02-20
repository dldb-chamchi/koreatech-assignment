package Model;

public class Member {
    // 7. 클래스 변수
    private static Long nextId = 1L;
    // 6. 인스턴스 변수
    private Long id;
    private String name;

    public Member(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void printInfo() {
        System.out.printf("%-8d번 | %-20s", id, name);
    }
}
