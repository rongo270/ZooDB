package animals;

import characteristicsOfAnimals.Animal;
import exception.MyException;
import exception.MyExceptionAge;
import exception.MyExceptionForHeight;

public class Penguin extends Animal {

	public static float heightLeaderPenguin = 0; // if heightLeaderPenguin = 0 -> There is no penguin leader (meaning there
											// is no penguin in the zoo)
	protected float height;
	public static final float MIN_HEIGHT_PENGUIN = 50;
	public static final float MAX_HEIGHT_PENGUIN = 400;

	public static final int LifeSpan = 6;

	public Penguin() {
		super();

	}

	public Penguin(int penguinId , String name, int age, float height) throws MyException, MyExceptionForHeight, MyExceptionAge { 
		this.name = name;
		this.animalId = penguinId;
		setAgePenguin(age);
		setHeightPenguin(height);
		super.setHappiness(100);

	}

	public void setAgePenguin(int age) throws MyExceptionAge {

		if (age < 0 || age > LifeSpan)
			throw new MyExceptionAge("EROR, Please enter a value between 0 and " + LifeSpan + " year\n");
		this.age = age;
	}

	public float getHeight() {
		return height;
	}

	public void setHeightPenguin(float height) throws MyException, MyExceptionForHeight {

		if (height < MIN_HEIGHT_PENGUIN)
			throw new MyException("EROR, The penguin must be above" + MIN_HEIGHT_PENGUIN + " cm\n");

		if (heightLeaderPenguin != 0 && height > heightLeaderPenguin)
			throw new MyExceptionForHeight(
					"EROR, This height is greater than the leader " + heightLeaderPenguin + " cm\n");

		this.height = height;
	}

	public String makeNoise() {

		return "squack";
	}

	public String toString() {
		return "Penguin id is "+this.animalId+" ,Penguin: name is " + name + ", age is " + age + " years, height is " + height + " cm and happiness is "
				+ super.happiness + "\n";
	}

	@Override
	public Number feed() {

		return (int) 1;
	}

}
