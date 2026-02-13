//테스트 프로그램
public class Test {

	public static void main(String[] args) throws CloneNotSupportedException {
		Company hyundai = new Company("현대", "대한민국");
		Company bmw = new Company("BMW", "독일");
		Engine e2497 = new Engine(EngineType.Gasoline, 2497);
		Engine e1998 = new Engine(EngineType.Gasoline, 1998);
		Vehicle c1 = new Car("그랜저", e2497, CarType.LARGE, hyundai, 2022);
		Vehicle c2 = new Car("BMW5", e1998, CarType.LARGE, bmw, 2022);
		Vehicle c3 = c1.clone();
		System.out.printf("%s: hash = %d%n",c1, c1.hashCode()); //부를 수 있는건 타입에 의해, 실제 불러지는 참조하는 객체의 의해(car의 toString, hashCode 불러짐)
		System.out.printf("%s: hash = %d%n",c2, c2.hashCode());
		System.out.printf("%s: hash = %d%n",c3, c3.hashCode());
		System.out.println(c1 != c3);
		System.out.println(c1.hashCode() == c3.hashCode());
		System.out.println(c1.equals(c3));
		c3.setName("쏘나타");
		c3.setEngine(new Engine(EngineType.Gasoline, 1999));
		((Car)c3).setCarType(CarType.MEDIUM);
		System.out.printf("%s: hash = %d%n", c3, c3.hashCode());
		System.out.println(c1.hashCode() != c3.hashCode());
		System.out.println(c1.equals(c3));
		System.out.println(c1.compareTo(c3)); //이름 사전 순서 비교
		System.out.println(c1);
	}

}
