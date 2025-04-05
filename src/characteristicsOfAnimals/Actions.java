package characteristicsOfAnimals;

public interface Actions<N> {

	public String makeNoise();

	@SuppressWarnings("hiding")
	public <N extends Number> N feed();

	public String toString();

	public void ageOneYear();

}
