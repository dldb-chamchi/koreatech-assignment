import java.util.Objects;

//Vehicle 클래스
public abstract class Vehicle implements Comparable<Vehicle>{
	private String name;			// 차량 모델명
	private Engine engine;			// 엔진 정보
	private Company maker;			// 제조사
	private int year;				// 출시년도

	public Vehicle(String name, Engine engine, Company maker, int year){
		setName(name);
		setEngine(engine);
		setCompany(maker);
		setYear(year);
	}
	
	public void setName(String name) {
		this.name = Objects.requireNonNull(name);
	}
	public void setEngine(Engine engine) {
		this.engine = Objects.requireNonNull(engine);
	}
	public void setCompany(Company maker) {
		this.maker = Objects.requireNonNull(maker);
	}
	public void setYear(int year) {
		if(year < 1950) throw new IllegalArgumentException("");
		this.year = year;
	}
	
	public String getName() {
		return name;
	}
	public Engine engine() {
		return engine;
	}
	public Company getCompany(){
		return maker;
	}
	public int getYear() {
		return year;
	}
	
	@Override public String toString() {
		return String.format("(%s, %s, %s, %d)", name, engine, maker, year);
	}
	
	@Override public boolean equals(Object other) {
		if(other == null || getClass() != other.getClass()) return false;
		if(this == other) return true;
		Vehicle vehicle = (Vehicle) other;
		return Objects.equals(name, vehicle.name) && engine.equals(vehicle.engine) && Objects.equals(maker, vehicle.maker) && year == vehicle.year;
	}
	
	@Override public int hashCode() {
		return Objects.hash(name, engine, maker, year);
	}
	
	@Override public Vehicle clone() throws CloneNotSupportedException {
		Vehicle cloned = (Vehicle) super.clone();
		cloned.engine = engine.clone();
		return cloned;
	}

	@Override
	public int compareTo(Vehicle other) {
		int nameComparison = name.compareTo(other.name);
		if(nameComparison != 0) return nameComparison;
		return Integer.compare(year, other.year);
	}
}
