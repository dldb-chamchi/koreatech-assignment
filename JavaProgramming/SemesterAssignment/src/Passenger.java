import java.util.concurrent.ThreadLocalRandom;

//Passenger 클래스

public class Passenger {
	public final int weight;
	public final int startFloor;
	public final int destFloor;
	
	public Passenger() {
		this(ThreadLocalRandom.current().nextInt(Utility.MAX_FLOOR));
	}
	public Passenger(int startFloor) {
		this(startFloor, Utility.getDestFloor(startFloor));
	}
	
	public Passenger(int startFloor, int destFloor) {
		weight = ThreadLocalRandom.current().nextInt(70) + 30;
		this.startFloor = startFloor;
		this.destFloor = destFloor;
	}

	public int getWeight(){
		return weight;
	}
	
	public Direction getDirection() {
		return (startFloor < destFloor)? Direction.UP: Direction.DOWN;
	}
	
	@Override public String toString() {
		return String.format("몸무게: %d, 출발층: %d, 목표층: %d, 방향: %s\n", 
			weight, startFloor, destFloor, getDirection());
	}
}
