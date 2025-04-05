package animals;

import characteristicsOfAnimals.Gender;

public class Tiger extends Carnivore {

	public static final float MAX_WEIGHT_TIGER = 90;

	public Tiger() {

	}

	public Tiger(int CarnivoreId,String name, Gender gender, int age, float weight) {

		super(CarnivoreId , name, gender, age, weight);

	}

	@Override
	public String makeNoise() {

		return "roar";
	}

	@Override
	public String TheTypeOfCarnivore() {
		// TODO Auto-generated method stub
		return "tiger";
	}

	@Override
	public Number feed() {
		if (this.genderCarnivore == Gender.MALE)
			food = (int) (this.weightCarnivore * this.age * PROMOTES_FOOD_MALE);
		else
			food = (int) (this.weightCarnivore * this.age * PROMOTES_FOOD_FEMALE);

		return food;
	}

	@Override
	public String toString() {
		return "Tiger: " + super.toString()+"\n";
	}

}
