import java.util.*;

//ElevatorSimulator 클래스

public class ElevatorSimulator {
	private Elevator elevatorA = new Elevator("A");
	private Elevator elevatorB = new Elevator("B");
	private int currTime = 0;
	private List<Deque<Event>> events = new ArrayList<>(Utility.MAX_FLOOR+1);

	public ElevatorSimulator() {}

	public void init(int elevatorALoc, int elevatorBLoc) {
		elevatorA.init(elevatorALoc);
		elevatorB.init(elevatorBLoc);
		currTime = 0;
		for (int i = 0; i < Utility.MAX_FLOOR; ++i) {
			events.add(new LinkedList<>());  // 각 층별로 이벤트 큐 초기화
		}
	}

	// 한 라운드에서 하차, 승차, 이동 중 하나만 가능. 순서는 하차, 승차, 이동 순
	public void nextRound() {
		elevatorA.newRound();
		elevatorB.newRound();
		processEventQueue(currTime);
		dischargePassengers();
		pickPassengers(currTime);
		elevatorA.move();
		elevatorB.move();
		++currTime;
	}

	// 탑승객 하차 처리
	public void dischargePassengers() {
		if(elevatorA.isMoving()) elevatorA.dischargePassengers();
		if(elevatorB.isMoving()) elevatorB.dischargePassengers();
	}

	// 대기중 승객 승차 처리
	public void pickPassengers(int currTime) {
		pickPassengers(currTime, elevatorA);
		pickPassengers(currTime, elevatorB);
	}

	// 엘리베이터 움직이고 있는 중이면 엘리베이터 현재 층에 대기 중인 고객이 탑승할 수 있는지 검사 (사건 시간 고려)
	private void pickPassengers(int currTime, Elevator elevator) {
		if (elevator.isMoving()) {
			// 예시: 이벤트 큐에서 승객 탑승할 수 있는 사건을 처리
			for (Deque<Event> eventDeque : events) {  // events는 List<Deque<Event>> 타입
				for (Event event : eventDeque) {  // Deque 안의 Event 객체들 순회
					if (event.getEventTime() <= currTime){
						if(event.getStartFloor() == elevator.getCurrentFloor()) {
							elevator.pickPassengers(event.getPassengers());
							eventDeque.remove(event);  // 사건 처리 후 큐에서 제거
							return;
						}
					}
				}
			}
		}
	}

	// 발생한 사건 검사
	// 현재 시간과 같거나 이전 시간에 발생한 사건만 검사
	// 이동 중인 엘리베이터가 처리할 수 있는지 검사
	// 이동 중인 엘리베이터가 처리할 수 없는 사건이 멈추어 있는 엘리베이터가 있으면 멈추어 있는 엘리베이터에 사건 할당
	// 이때 둘 다 멈추어 있으면 둘 중 더 적합한 엘리베이터에 사건을 할당해야 함
	private void processEventQueue(int currTime) {
		for (Deque<Event> eventDeque : events) {
			for (Event event : eventDeque) {
				if (event.getEventTime() <= currTime) {
					// 이벤트가 발생할 수 있는 시간이라면
					if (elevatorA.isMoving() || elevatorB.isMoving()) {
						if(elevatorA.isMoving()) {
							if (elevatorA.getCurrentFloor() <= event.getStartFloor() && elevatorA.getNextFloor() <= event.getStartFloor() && (elevatorA.getElevatorDirection() == Direction.UP || elevatorA.getElevatorDirection() == Direction.NONE)){
								elevatorA.setNextFloor(event.getStartFloor());
								//eventDeque.remove(event);
							}

							else if (elevatorA.getCurrentFloor() >= event.getStartFloor() && elevatorA.getNextFloor() >= event.getStartFloor() && (elevatorA.getElevatorDirection() == Direction.DOWN || elevatorA.getElevatorDirection() == Direction.NONE)){
								elevatorA.setNextFloor(event.getStartFloor());
								//eventDeque.remove(event);
							}

							//else elevatorB.setNextFloor(event.getStartFloor());
						}
						else if (elevatorB.isMoving()) {
							if(elevatorB.getCurrentFloor() <= event.getStartFloor() && elevatorB.getNextFloor() <= event.getStartFloor() && (elevatorB.getElevatorDirection() == Direction.UP || elevatorB.getElevatorDirection() == Direction.NONE))
								elevatorB.setNextFloor(event.getStartFloor());

							else if(elevatorB.getCurrentFloor() >= event.getStartFloor() && elevatorB.getNextFloor() >= event.getStartFloor() && (elevatorB.getElevatorDirection() == Direction.DOWN || elevatorB.getElevatorDirection() == Direction.NONE))
								elevatorB.setNextFloor(event.getStartFloor());

							else elevatorA.setNextFloor(event.getStartFloor());
						}
					}

					// 엘리베이터가 모두 멈춰 있다면 적절한 엘리베이터를 선택
					else if (!elevatorA.isMoving() && !elevatorB.isMoving()) {
						// 더 가까운 엘리베이터에 사건 할당
						Elevator selectedElevator = (Math.abs(elevatorA.getCurrentFloor() - event.getStartFloor()) < Math.abs(elevatorB.getCurrentFloor() - event.getStartFloor())) ? elevatorA : elevatorB;

						if (Math.abs(elevatorA.getCurrentFloor() - event.getStartFloor()) == Math.abs(elevatorB.getCurrentFloor() - event.getStartFloor()))
							selectedElevator = elevatorA.getCurrentFloor() - event.getStartFloor() > 0 ? elevatorB : elevatorA;

						selectedElevator.setNextFloor(event.getStartFloor());
					}

				}
			}
		}
	}



	public void addEvents(List<Event> newEvents) {
		for (Event event : newEvents) {
			int startFloor = event.getStartFloor();

			// events 리스트의 크기를 startFloor보다 클 수 있도록 동적 확장
			while (events.size() <= startFloor) {
				events.add(new LinkedList<>());  // 크기가 부족할 경우 새로운 큐를 추가
			}

			events.get(startFloor).add(event);  // 해당 층에 이벤트 추가
		}
	}

	// 사건을 모두 처리하였고, 모든 엘리베이터가 이동을 중지한 경우
	public boolean hasSimulationEnded() {
		// 모든 엘리베이터가 멈추고 더 이상 처리할 사건이 없으면 종료
		return !elevatorA.isMoving() && !elevatorB.isMoving() && events.stream().allMatch(Deque::isEmpty);
	}

	public void debugPrint() {
		System.out.printf("현재 시간: %d%n", currTime);
		System.out.println(elevatorA);
		System.out.println(elevatorB);
	}

}
