import java.util.LinkedList;
import java.util.Deque;

//Event 클래스

public class Event {
	private Deque<Passenger> passengers = new LinkedList<>();
	public final int eventTime;
	//
	public Event(int currTime, int startFloor, int destFloor) {
		eventTime = currTime;		
		int numPassengers = 3; // ThreadLocalRandom.current().nextInt(3) + 1;
		for(int i = 0; i < numPassengers; ++i)
			passengers.add(new Passenger(startFloor, destFloor));
		System.out.println("사건 생성: "+ this);
	}
	
	public Deque<Passenger> getPassengers() {
		return passengers;
	}
	public int getStartFloor() {
		if(passengers.isEmpty()) throw new IllegalStateException();
		return passengers.getFirst().startFloor; //-1하면 정시에 태움
	}
	public int getDestFloor() {
		if(passengers.isEmpty()) throw new IllegalStateException();
		return passengers.getFirst().destFloor;
	}
	public Direction getDirection() {
		if(passengers.isEmpty()) throw new IllegalStateException();
		return passengers.getFirst().getDirection();
	}

	public int getEventTime() {
		return eventTime;
	}

	@Override public String toString() {
		return "event";
	}
}
