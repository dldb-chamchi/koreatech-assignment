import java.util.ArrayList;
import java.util.List;

//테스트 파일

public class Test {

	public static void simulateTest01(ElevatorSimulator simulator) {
		List<Event> events = new ArrayList<>();
		Event event = new Event(0, 1, 6);
		events.add(event);
		simulator.addEvents(events);
		simulator.init(1, 1);
	} //good
	
	public static void simulateTest02(ElevatorSimulator simulator) {
		List<Event> events = new ArrayList<>();
		events.add(new Event(0, 5, 8));
		events.add(new Event(0, 6, 9));
		simulator.addEvents(events);
		simulator.init(1, 1);
	} //good
	
	public static void simulateTest03(ElevatorSimulator simulator) {
		List<Event> events = new ArrayList<>();
		events.add(new Event(0, 5, 8));
		simulator.addEvents(events);
		simulator.init(3, 1);
	} //good
	
	public static void simulateTest04(ElevatorSimulator simulator) {
		List<Event> events = new ArrayList<>();
		events.add(new Event(0, 5, 8));
		events.add(new Event(0, 5, 1));
		simulator.addEvents(events);
		simulator.init(1, 1);
	}//무한루프
	
	public static void simulateTest05(ElevatorSimulator simulator) {
		List<Event> events = new ArrayList<>();
		events.add(new Event(0, 5, 8));
		simulator.addEvents(events);
		simulator.init(9, 1);
	} //good
	
	public static void simulateTest06(ElevatorSimulator simulator) {
		List<Event> events = new ArrayList<>();
		events.add(new Event(0, 2, 5));
		simulator.addEvents(events);
		simulator.init(1, 1);
	} //정격하중 초과 검사
	
	public static void simulateTest07(ElevatorSimulator simulator) {
		List<Event> events = new ArrayList<>();
		events.add(new Event(0, 1, 5));
		events.add(new Event(0, 1, 4));
		simulator.addEvents(events);
		simulator.init(1, 1);
	} //good
	
	public static void simulateTest08(ElevatorSimulator simulator) {
		List<Event> events = new ArrayList<>();
		events.add(new Event(0, 2, 5));
		events.add(new Event(0, 2, 5));
		events.add(new Event(0, 2, 5));
		events.add(new Event(0, 2, 5));
		events.add(new Event(0, 2, 5));
		events.add(new Event(0, 2, 5));
		simulator.addEvents(events);
		simulator.init(1, 1);
	} //무한루프 B가 안움직여 ㅠㅠ
	
	public static void simulateTest09(ElevatorSimulator simulator) {
		List<Event> events = new ArrayList<>();
		events.add(new Event(0, 2, 5));
		events.add(new Event(2, 1, 7));
		events.add(new Event(3, 5, 3));
		simulator.addEvents(events);
		simulator.init(1, 1);
	} //작동이 좀 이상함

	public static void simulatorTest() {
		ElevatorSimulator simulator = new ElevatorSimulator();
		simulateTest07(simulator);
		simulator.debugPrint();
		while(!simulator.hasSimulationEnded()) {
			simulator.nextRound();
			simulator.debugPrint();
		}

	}
	
	public static void main(String[] args) {
		simulatorTest();
	}

}
