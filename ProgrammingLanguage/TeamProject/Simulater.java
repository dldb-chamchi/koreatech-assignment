import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Course;
import Model.Member;
import Model.Professor;
import Model.Student;

public class Simulater {
    // 4. 필드
    private List<Student> students = new ArrayList<>();
    private List<Professor> professors = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private Member nowMember;
    private Scanner sc = new Scanner(System.in);

    public Simulater() {
        // 3.인스턴스
        students.add(new Student("홍길동", "컴퓨터공학부"));
        students.add(new Student("김철수", "전자공학부"));
        students.add(new Student("이영희", "기계공학부"));
        students.add(new Student("박지민", "컴퓨터공학부"));
        students.add(new Student("최준호", "전자공학부"));
        students.add(new Student("정수빈", "기계공학부"));
        students.add(new Student("이서연", "컴퓨터공학부"));

        professors.add(new Professor("박교수", "컴퓨터공학과"));
        professors.add(new Professor("김교수", "전자공학과"));

        courses.add(new Course(1L, "자료구조", professors.get(0), 30, "자료구조를배워요~"));
        courses.add(new Course(2L, "알고리즘", professors.get(0), 30, "알고리즘을배워요~"));
        courses.add(new Course(3L, "회로이론", professors.get(1), 30, "회로이론을배워요~"));
        courses.add(new Course(4L, "기계설계", professors.get(1), 30, "기계설계를배워요~"));
        courses.add(new Course(5L, "운영체제", professors.get(0), 30, "운영체제를배워요~"));
        courses.add(new Course(6L, "졸업설계", professors.get(0), 30, "졸업설계를배워요~"));

        courses.get(0).getEnrolledStudents().add(students.get(0));
        courses.get(0).getEnrolledStudents().add(students.get(1));
        courses.get(1).getEnrolledStudents().add(students.get(2));
        courses.get(1).getEnrolledStudents().add(students.get(3));
        courses.get(2).getEnrolledStudents().add(students.get(4));
        courses.get(2).getEnrolledStudents().add(students.get(5));
        courses.get(3).getEnrolledStudents().add(students.get(6));
    }

    // 5. 메소드
    public void setInitMember() {
        nowMember = null;
        while (nowMember == null) {
            System.out.println("1. 학생\n2. 교수 \n3. 종료");
            System.out.print("번호를 입력하세요: ");
            Long inputNumber = sc.nextLong();
            if (inputNumber == 1) {
                nowMember = students.get(0);
            } else if (inputNumber == 2) {
                nowMember = professors.get(0);
            } else if (inputNumber == 3) {
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            } else {
                System.out.println("잘못된 번호입니다.");
            }
        }
        System.out.println("" + nowMember.getName() + "님, 환영합니다!");
        // 14. 다형성
        nowMember.printInfo();
    }

    

    //시뮬레이터 실행
    public void simulate() {
        while (true) {
            if (nowMember == null) {
                setInitMember();
                continue;
            }

            if (nowMember instanceof Student) {
                System.out.println("\n====== [학생 메뉴] ======");
                System.out.println("1. 수강 신청");
                System.out.println("2. 수강 취소");
                System.out.println("3. 수강 과목 조회");
                System.out.println("4. 로그아웃");
                System.out.print("메뉴 번호를 선택하세요: ");

                Long inputNumber = sc.nextLong();
                switch (inputNumber.intValue()) {
                    case 1:
                        printCourses(courses);
                        System.out.print("수강 신청할 과목 ID를 입력하세요: ");
                        Long courseId = sc.nextLong();
                        enrollCourse(courseId);
                        break;
                    case 2:
                        printEnrolledCourses();
                        System.out.print("수강 취소할 과목 ID를 입력하세요: ");
                        Long dropCourseId = sc.nextLong();
                        dropCourse(dropCourseId);
                        break;
                    case 3:
                        printEnrolledCourses();
                        break;
                    case 4:
                        nowMember = null;
                        System.out.println("로그아웃 되었습니다.");
                        break;
                    default:
                        System.out.println(" 잘못된 선택입니다. 다시 시도하세요.");
                }

            } else if (nowMember instanceof Professor) {
                System.out.println("\n====== [교수자 메뉴] ======");
                System.out.println("1. 과목 생성");
                System.out.println("2. 과목 삭제");
                System.out.println("3. 과목 조회");
                System.out.println("4. 로그아웃");
                System.out.print("메뉴 번호를 선택하세요: ");

                Long inputNumber = sc.nextLong();
                switch (inputNumber.intValue()) {
                    case 1:
                        System.out.println("\n[과목 생성]");
                        System.out.print("과목 ID를 입력하세요: ");
                        Long courseId = sc.nextLong();
                        if (courses.stream().anyMatch(course -> course.getId().equals(courseId))) {
                            System.out.println(" 이미 존재하는 과목 ID입니다.");
                            break;
                        }

                        sc.nextLine();

                        System.out.print("과목명을 입력하세요: ");
                        String courseName = sc.nextLine();
                        if (courseName.isEmpty()) {
                            System.out.println(" 과목명은 필수 입력입니다.");
                            break;
                        }

                        System.out.print("과목 설명을 입력하세요 (빈칸 시 기본 설명): ");
                        String courseDescription = sc.nextLine();

                        System.out.print("최대 수강 인원을 입력하세요: ");
                        Integer maxStudents = sc.nextInt();
                        if (maxStudents <= 0) {
                            System.out.println(" 최대 수강 인원은 1명 이상이어야 합니다.");
                            break;
                        }

                        createCourse(courseId, courseName, (Professor) nowMember, maxStudents, courseDescription);
                        System.out.println(" 과목이 생성되었습니다.");
                        break;

                    case 2:
                        System.out.println("\n[과목 삭제]");
                        printProfessorCourses((Professor) nowMember);
                        System.out.print("삭제할 과목 ID를 입력하세요: ");
                        Long deleteCourseId = sc.nextLong();
                        deleteCourse(deleteCourseId);
                        break;

                    case 3:
                        printProfessorCourses((Professor) nowMember);
                        break;

                    case 4:
                        nowMember = null;
                        System.out.println("로그아웃 되었습니다.");
                        break;

                    default:
                        System.out.println(" 잘못된 선택입니다. 다시 시도하세요.");
                }
            }
        }
    }

    
    //과목출력용메서드
    public void printCourses(List<Course> courses) {
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-20s | %-30s | %-10s | %-15s | %-20s |\n",
                "과목 ID", "과목명", "설명", "담당 교수", "최대 수강 인원", "현재 수강 학생 수");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Course course : courses) {
            System.out.print("| ");
            course.printCourseInfo();
            System.out.println("|");
        }
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //수강신청
    public void enrollCourse(Long courseId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                if (nowMember instanceof Student) {
                    course.addStudent((Student) nowMember);
                    return;
                } else {
                    System.out.println("교수는 수강 신청을 할 수 없습니다.");
                    return;
                }
            }
        }
        System.out.println("해당 ID의 과목을 찾을 수 없습니다.");
    }

    //수강취소
    public void dropCourse(Long courseId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                if (nowMember instanceof Student) {
                    course.removeStudent((Student) nowMember);
                    return;
                } else {
                    System.out.println("교수는 수강 취소를 할 수 없습니다.");
                    return;
                }
            }
        }
        System.out.println("해당 ID의 과목을 찾을 수 없습니다.");
    }

    //학생의 수강 과목 조회
    public void printEnrolledCourses() {
        if (nowMember instanceof Student) {
            Student student = (Student) nowMember;
            System.out.println(student.getName() + "의 수강 과목:");
            List<Course> enrolledCourses = courses.stream()
                    .filter(course -> course.getEnrolledStudents().contains(student))
                    .toList();
            printCourses(enrolledCourses);
        } else {
            System.out.println("교수는 수강 과목을 조회할 수 없습니다.");
        }
    }

    //교수의 과목 조회
    public void printProfessorCourses(Professor professor) {
        System.out.println(professor.getName() + "의 과목:");
        List<Course> professorCourses = courses.stream()
                .filter(course -> course.getProfessor().equals(professor))
                .toList();
        printCourses(professorCourses);
    }

    //교수의 과목 삭제
    public void deleteCourse(Long courseId) {
        Course courseToDelete = courses.stream()
                .filter(course -> course.getId().equals(courseId))
                .findFirst()
                .orElse(null);
        if (courseToDelete == null) {
            System.out.println("해당 ID의 과목을 찾을 수 없습니다.");
            return;
        }
        if (courseToDelete.getProfessor() != nowMember) {
            System.out.println("해당 과목은 현재 교수님이 담당하지 않는 과목입니다.");
            return;
        }
        courses.removeIf(course -> course.getId().equals(courseId));
    }

    //교수의 과목 생성
    public void createCourse(Long id, String name, Professor professor, int maxStudents, String description) {
        Course newCourse;
        if (description == null || description.isEmpty()) {
            newCourse = new Course(id, name, professor, maxStudents);
        } else {
            newCourse = new Course(id, name, professor, maxStudents, description);
        }
        courses.add(newCourse);
        System.out.println("과목이 생성되었습니다: " + newCourse.getName());
    }
}
