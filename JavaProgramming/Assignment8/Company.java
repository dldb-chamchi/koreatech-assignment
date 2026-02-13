import java.util.Objects;

//차량 제조사
public record Company(String name, String country) {
	public Company{
		Objects.requireNonNull(name);
		Objects.requireNonNull(country);
	}
}
