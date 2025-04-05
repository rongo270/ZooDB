package characteristicsOfAnimals;

import java.util.Random;

import project_ZOO.ZooManger;
import project_ZOO.userInputOutput;

@SuppressWarnings("rawtypes")
public abstract class Animal implements Actions {

	public static final int MIN_AGE_ANIMAL = 0;

	protected int age;
	protected int happiness;
	protected float height;
	protected String name;
	protected int animalId;
	Random rand = new Random();

	public Animal() {
		this.happiness = 100;

	}

	public Animal(int animalId, String name, int age, float height) {

		this.age = age;
		this.happiness = 100;
		this.height = height;
		this.name = name;
		this.animalId = animalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public float getHeight() {
		return height;
	}

	public void setAge(int age) {

		this.age = age;
	}

	public void setLenthOrHeight(float height) {
		this.height = height;
	}

	public int getHappiness() {
		return happiness;
	}

	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}

	// only the userInput class can set id
	public <T> boolean setAnimalId(int animalId, Class<T> classWanted) {
		if (classWanted.equals(ZooManger.class)) {
			this.animalId = animalId;
			return true;
		}
		return false;
	}

	public int getAnimalId() {
		return this.animalId;
	}

	public void ageOneYear() {
		this.age++;
		happiness = happiness - (rand.nextInt(40) + 1);
		if (happiness < 0)
			happiness = 0;

	}

}
