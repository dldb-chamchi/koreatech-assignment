import java.util.*;

//Elevator 클래스

public class Elevator {
	@SuppressWarnings("unused")
	private final int MAX_WEIGHT = 1000;
	public final String id;
	@SuppressWarnings("unused")
	private int numPassengers = 0;
	private int currentFloor = 0;
	private int totalWeight = 0;
	private boolean roundDone = false;
	private int nextFloor = -1;
	private Direction direction = Direction.NONE;
	private HashMap<Integer, List<Integer>> PassengersInfo = new HashMap<>(10);

	@Override
	public String toString() {
		return String.format("Elevator %s | Current Floor: %d | Passengers: %d | Total Weight: %d",
				id, currentFloor, numPassengers, totalWeight);
	}

	public Elevator(String id) {
		this.id = Objects.requireNonNull(id);
	}

	public void init(int currentFloor) {
		this.currentFloor = currentFloor;
		numPassengers = totalWeight = 0;
	}

	public void newRound() {
		roundDone = false;
	}

	// 탑승객 하차 처리
	public void dischargePassengers() {
		if(roundDone) return;

		// 현재 층에서 하차할 승객들을 처리 (Passenger 클래스에서 목적 층 비교를 가정)
		int dischargedWeight = 0;
		int dischargedPassengers = 0;

		if(PassengersInfo.containsKey(currentFloor)){
			List<Integer> passengers = PassengersInfo.get(currentFloor);
			if(!passengers.isEmpty()){
				dischargedPassengers = passengers.get(0);
				dischargedWeight = passengers.get(1);
				roundDone = true;
			}
		}

		PassengersInfo.remove(currentFloor);

		numPassengers -= dischargedPassengers;
		totalWeight -= dischargedWeight;

	}

	// 대기 고객 승차 처리
	public void pickPassengers(Deque<Passenger> newPassengers) {
		if(roundDone) return;

		int numP = 0; int totalW = 0;
		while(!newPassengers.isEmpty() && totalWeight < MAX_WEIGHT){
			Passenger passenger = newPassengers.peek();
			if(totalWeight+totalW+passenger.getWeight() <= MAX_WEIGHT){
				newPassengers.poll(); // 승객 제거
				++numP;
				totalW += passenger.getWeight();
				PassengersInfo.put(passenger.destFloor, new ArrayList<>(Arrays.asList(numP, totalW)));
				roundDone = true;
			}
			else break;
		}

		numPassengers += numP;
		totalWeight += totalW;


	}

	public void setNextFloor(int floor) {
		this.nextFloor = floor; // 1부터 시작하는 층을 0부터로 변환
	}

	// 움직이고 있고, 이번 시간에 움직일 수 있는 경우 한 층 위로 또는 아래로 이동
	// 엘리베이터 클래스의 일부 (추가/수정된 부분)
	public void move() {
		if(roundDone) return;
		if(isMoving()){
			if(currentFloor == nextFloor) nextFloor = -1;
			if(currentFloor > nextFloor && nextFloor != -1){
				direction = Direction.DOWN;
				--currentFloor;
			}
			else{
				++currentFloor;
				direction = Direction.UP;
			}

		}

	}

	// 엘리베이터가 이동 중인지 확인
	public boolean isMoving() {
		// 엘리베이터가 승객을 태우고 있거나, 새로운 층으로 이동 중이면 true
		return numPassengers > 0 || (nextFloor != -1 && currentFloor != nextFloor) || nextFloor != -1;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public int getTotalWeight() {
		return totalWeight;
	}

	public Direction getElevatorDirection(){
		return direction;
	}

	public int getNextFloor(){
		return nextFloor;
	}

}
