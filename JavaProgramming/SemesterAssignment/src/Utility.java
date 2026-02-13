import java.util.concurrent.ThreadLocalRandom;

public interface Utility {
	static int MAX_FLOOR = 10;
	static int getDestFloor(int startFloor) {
		return (startFloor + 
			ThreadLocalRandom.current().nextInt(MAX_FLOOR - 1) + 1) % MAX_FLOOR;
	}
}
