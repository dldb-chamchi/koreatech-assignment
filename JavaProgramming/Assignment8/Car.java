import java.util.Objects;

//Car 클래스
public final class Car extends Vehicle implements Cloneable {
	private CarType carType;	// 차종
	public Car(String name, Engine engine, CarType carType, Company maker, int year){
		super(name, engine, maker, year);
		setCarType(carType);
	}
	
	public void setCarType(CarType carType) {
		this.carType = Objects.requireNonNull(carType);
	}
	public CarType carType() {
		return carType;
	}
	
	@Override public String toString() {
		return String.format("(%S, %S)", super.toString(), carType);
	}
	
	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), carType);
	}
	
	@Override public boolean equals(Object other) {
		if(!super.equals(other)) return false;
		Car car = (Car) other;
		return carType == car.carType;
	}

	@Override
	public Car clone() {
		Car cloned = null;
		try {
			cloned = (Car) super.clone();
		}
		catch (CloneNotSupportedException e) {}
		return cloned;
	}

	@Override
	public int compareTo(Vehicle other) {
		Car car = (Car) other;

		int makerComparison = getCompany().name().compareTo(car.getCompany().name());
		if (makerComparison != 0) return makerComparison;

		return carType.compareTo(car.carType());
	}
}

