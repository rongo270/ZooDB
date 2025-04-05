package animals;

import characteristicsOfAnimals.Animal;
import characteristicsOfAnimals.Gender;

/**
 * 
 * @author orian azuri 
 * orian.azuri@s.afeka.ac.il
 *
 */
public class Cervus extends Animal {

	public static final int LifeSpan = 10;
	public static final float maxLengthOfTheRays = 50;
	public static final float minLengthOfTheRays = 0;
	public static final int PROMOTES_FOOD = 4;
	protected Gender genderCervus;
	protected double lengthOfTheRays;

	public Cervus() {
		super();
	}

	public Cervus( int CervusId , String name, Gender genderCervus, int age, double lengthOfTheRays) {
		super( CervusId, name, age, -1);
		setLengthOfTheRays(lengthOfTheRays);
		this.genderCervus = genderCervus;
	}

	public double getLengthOfTheRays() {
		return lengthOfTheRays;
	}

	public void setLengthOfTheRays(double lengthOfTheRays) {

		this.lengthOfTheRays = lengthOfTheRays;
	}

	public Gender getGenderCervus() {
		return genderCervus;
	}

	public void setGenderCervus(Gender genderCervus) {
		this.genderCervus = genderCervus;
	}

	@Override
	public String toString() {
		return "Cervus id is "+this.getAnimalId()+" Cervus: name is " + name + " age is " + age + " years, length of the rays is " + lengthOfTheRays
				+" gender is "+this.genderCervus+ " cm and happiness is " + super.happiness + "\n";

	}

	@Override
	public String makeNoise() {
		return "OhOhOh";
	}

	@Override
	public Number feed() {
		return this.age * Cervus.PROMOTES_FOOD;
	}

}
