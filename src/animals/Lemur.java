package animals;

import characteristicsOfAnimals.Animal;

/**
 * 
 * @author lior Malul 
 * Lior.Malul@s.afeka.ac.il
 * 
 */
public class Lemur extends Animal {

	public static final float MIN_HEIGHT_LEMUR = 30;
	public static final float MAX_HEIGHT_LEMUR = 50;
	public static final int LifeSpan = 18;

	public Lemur() {
		super();
	}

	public Lemur(int lemurId  , String name, int age, float height) {
		super(lemurId  , name, age, height); 

	}

	public float getHeight() {
		return super.getHeight();
	}

	@Override
	public String makeNoise() {

		return "I like to move it";
	}

	@Override
	public String toString() {
		return "Lemur id is "+this.animalId+" Lemur: name is " + name + " age is " + age + " height is " + super.getHeight() + " and happiness is "
				+ super.happiness + "\n";

	}

	@Override
	public Number feed() {

		return (int) 4;
	}

}
