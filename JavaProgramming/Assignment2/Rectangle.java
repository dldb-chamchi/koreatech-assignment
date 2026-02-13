//자바프로그래밍 2주차 과제(Rectangle 클래스 만들기)

/*
사각형의 좌상단 좌표를 point 객체로 유지할 때
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void moveTo(int x, int y) {
    if(x > 0 && y > 0){
        this.x = x;
        this.y = y;
        }
    }

    public void moveBy(int deltaX, int deltaY) {
        if(x + deltaX >= 0 && y + deltaY >= 0){
            this.x = x + deltaX;
            this.y = y + deltaY;
        }
    }
}

public class Rectangle {
private Point topLeft; //왼쪽 위 좌표를 나타내는 Point 객체
private int width, height;

    //moveTo를 point 객체의 메소드를 활용하여 작성
    public void moveTo(int x, int y) {
        if (x < 0 || y < 0) return;
        topLeft.moveTo(x, y);
    }

    //moveBy를 point 객체의 메소드를 활용하여 작성
    public void moveBy(int deltaX, int deltaY) {
        int newX = topLeft.getX() + deltaX;
        int newY = topLeft.getY() + deltaY;
        if (newX >= 0 && newY >= 0) {
            topLeft.moveBy(deltaX, deltaY);
        }
    }
}
*/


//과제 코드
public class Rectangle {
    private int x, y;
    private int width, height;
    private String color; //추가 상태

    public Rectangle(int x, int y, int width, int height){
        //음수 값일 때 5로 설정, 컬러 기본값 null
        this.x = (x >= 0) ? x : 5; 
        this.y = (y >= 0) ? y : 5; 
        this.width = (width > 0) ? width : 5;
        this.height = (height > 0) ? height : 5;
        this.color = null;
    }

    //컬러 받는 생성자
    public Rectangle(int x, int y, int width, int height, String color){
        this.x = (x >= 0) ? x : 5; 
        this.y = (y >= 0) ? y : 5; 
        this.width = (width > 0) ? width : 5;
        this.height = (height > 0) ? height : 5;
        this.color = color;
    }

    public int getWidth(){ return width; }
    public int getHeight(){ return height; }
    public int area(){ return width * height; }
    public boolean isSquare(){ return width == height; }

    public void moveTo(int x, int y){
        if(x < 0 || y < 0) return;
        this.x = x;
        this.y = y;
    }

    public void moveBy(int deltaX, int deltaY){
        if(x + deltaX >= 0 && y + deltaY >= 0){
            this.x = x + deltaX;
            this.y = y + deltaY;
        }
    }

    public boolean isInside(int x, int y) {
        return x > this.x && x < this.x + width && y > this.y && y < this.y + height; //좌상단 유지, 밑으로 갈수록 좌표값 커짐
    }
    
    //이하 추가 메소드
    public int getX(){ return x; }
    public int getY(){ return y; }

    public void changeWidth(int width){
        if(width < 0) return;
        this.width = width; 
    }

    public void changeheight(int height){ 
        if(height < 0) return;
        this.height = height; 
    }

    public String getColor(){ return color; }
    public void setColor(String color){ 
        if(color != null)
        this.color = color; 
    }

    //테스트 함수
    public static void testRectangle1(){
        System.out.println("----Test1----");
        Rectangle rect = new Rectangle(10, 10, 100, 100);
        System.out.println(rect.isSquare()); //true
        System.out.println(rect.isInside(30, 10)); //false
        System.out.println(rect.isInside(20, 15)); //true
        rect.moveBy(-10, 10); //0, 20
        System.out.println(rect.getX() + " " + rect.getY());
        rect.moveBy(10, 10); //10, 30
        System.out.println(rect.getX() + " " + rect.getY());
        rect.moveBy(-5, -5); //5, 25
        System.out.println(rect.getX() + " " + rect.getY());
    }
    
    public static void testRectangle2(){
        System.out.println("----Test2----");
        Rectangle rect = new Rectangle(7, 7, 10, 20);
        System.out.println(rect.area()); //200
        System.out.println(rect.isSquare()); //false
        System.out.println(rect.isInside(5, 5));//false
        rect.moveTo(-5, -5); //둘다 마이너스 실행x
        System.out.println(rect.getX() + " " + rect.getY());
        rect.moveTo(5, 10); //5, 10
        System.out.println(rect.getX() + " " + rect.getY());
        
    }

    public static void testRectangle3(){
        System.out.println("----Test3----");
        Rectangle rect = new Rectangle(10, 10, 5, 1000);
        rect.changeWidth(-10); //마이너스 실행x
        System.out.println(rect.getWidth()); //5
        rect.changeWidth(10); //10
        System.out.println(rect.getWidth());
        rect.changeheight(-1000); //실행x
        System.out.println(rect.getHeight()); //1000
        rect.changeheight(500); //500
        System.out.println(rect.getHeight());
        System.out.println(rect.area()); //10*500 = 5000
    }

    public static void testRectangle4(){
        System.out.println("----Test4----");
        Rectangle rect = new Rectangle(-10, 10, 5, 5);
        Rectangle rect2 = new Rectangle(-10, 10, 5, 5, "white");
        System.out.println(rect.getX() + " " + rect.getY()); //초기값 목록 실행됨(5, 10)
        System.out.println(rect.getColor()); //null
        rect.setColor("black"); //black
        rect2.setColor(null); //null 실행 안됨
        System.out.println(rect.getColor());
        System.out.println(rect2.getColor()); //white
    }
    
    public static void main(String[] args){
        testRectangle1();
        testRectangle2();
        testRectangle3();
        testRectangle4();
    }
}
