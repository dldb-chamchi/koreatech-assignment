package Model;

import java.util.ArrayList;
import java.util.List;

// 1. 클래스
public class Course {
    // 9.캡슐화, 13 접근한정자, 11. 정보은닉
    private Long id;
    private String name;
    private String description;
    private List<Student> enrolledStudents = new ArrayList<>();
    private Professor professor;
    private Integer maxStudents;

    // 10. 생성자
    public Course(Long id, String name, Professor professor, Integer maxStudents, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        if (maxStudents != null && maxStudents > 0) {
            this.maxStudents = maxStudents;
        } else {
            this.maxStudents = 30;
        }
        this.professor = professor;
    }

    // 8. 오버로딩. 16. 정적바인딩
    public Course(Long id, String name, Professor professor, Integer maxStudents) {
        this(id, name, professor, maxStudents, name + "입니다");
    }

    public void printCourseInfo() {
        System.out.printf("%-8s | %-20s | %-30s | %-10s | %-15d | %-20d",
                id,
                name,
                description.length() > 28 ? description.substring(0, 27) + "…" : description,
                (professor != null ? professor.getName() : "없음"),
                maxStudents,
                enrolledStudents.size());
    }

    // 12. 접근자
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // 13. 설정자
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void addStudent(Student student) {
        if (enrolledStudents.size() < maxStudents && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            System.out.println(name + " 수강 신청이 완료되었습니다.");
        } else {
            System.out.println("최대 수강 인원을 초과하거나 이미 등록된 학생입니다.");
        }
    }

    public void removeStudent(Student student) {
        if (enrolledStudents.contains(student)) {
            enrolledStudents.remove(student);
            System.out.println(getName() + " 수강 취소가 완료되었습니다.");
            return;
        } else {
            System.out.println("등록된 학생이 아닙니다.");
        }
    }
}