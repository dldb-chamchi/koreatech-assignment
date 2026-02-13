import java.util.Objects;

//Engine 클래스
public final class Engine implements Cloneable{
	private EngineType engineType;	// 연료 종류
	private int capacity;			// 배기량
	public Engine(EngineType engineType, int capacity) {
		setEngineType(engineType);
		setCapacity(capacity);
	}
	
	public void setEngineType(EngineType engineType) {
		this.engineType = Objects.requireNonNull(engineType);
	}
	public void setCapacity(int capacity) {
		if(capacity <= 0) throw new IllegalArgumentException();
		this.capacity = capacity;
	}
	public EngineType getEngineType() {
		return engineType;
	}
	public int getCapacity() {
		return capacity;
	}
	
	@Override public String toString() {
		return String.format("(%s, %,dcc)", engineType, capacity);
	}
	
	@Override public int hashCode() {
		return Objects.hash(engineType, capacity);
	}
	
	@Override public boolean equals(Object other) {
		if(other == null || getClass() != other.getClass()) return false;
		if(this == other) return true;
		Engine e = (Engine)other;
		return engineType == e.engineType && capacity == e.capacity;
	}
	
	@Override public Engine clone() {
		Engine cloned = null;
		try {
			cloned = (Engine)super.clone();
		}
		catch(CloneNotSupportedException e) {}
		return cloned;
	}
}
