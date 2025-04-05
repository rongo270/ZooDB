package animals;

import characteristicsOfAnimals.Gender;

public class Lion extends Carnivore {

	public static final float MAX_WEIGHT_LION = 260;

	public Lion() {

	}

	public Lion(int CarnivoreId , String name, Gender gender, int age, float weight) { 
		super(CarnivoreId , name, gender, age, weight);

	}

	@Override
	public String makeNoise() {
		return "ROAR";
	}

	@Override
	public String TheTypeOfCarnivore() {
		return "lion";
	}

	@Override
	public Number feed() {
		if (this.genderCarnivore == Gender.MALE)
			food = (int) (this.weightCarnivore * this.age * this.PROMOTES_FOOD_MALE);
		else
			food = (int) (this.weightCarnivore * this.age * this.PROMOTES_FOOD_FEMALE);

		if (food > 25)
			food = 25;

		return food;
	}

	@Override
	public String toString() {
		return "Lion: " + super.toString() + "\n";
	}
}
