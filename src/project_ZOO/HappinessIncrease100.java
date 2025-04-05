package project_ZOO;

import java.util.ArrayList;

import characteristicsOfAnimals.Animal;

public class HappinessIncrease100<T> {

	// Array lists - increase per year
	public <T extends Animal> void animalAgeOneYear(ArrayList<T> animals) {

		for (int i = 0; i < animals.size(); i++)
			animals.get(i).setHappiness(100);
	}

}
