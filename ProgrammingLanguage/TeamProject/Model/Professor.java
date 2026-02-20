package Model;

public class Professor extends Member {
    private String department;

    public Professor(String name, String department) {
        super(name);
        this.department = department;
    }

    @Override
    public void printInfo() {
        System.out.printf("%d번 | %s | %s", getId(), getName(), department);
    }
}
