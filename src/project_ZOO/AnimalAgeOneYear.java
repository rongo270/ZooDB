package project_ZOO;

import java.sql.SQLException;
import java.util.ArrayList;

import animals.AquariumFish;
import animals.Carnivore;
import animals.Cervus;
import animals.ClownFish;
import animals.Fish;
import animals.GoldFish;
import animals.Lemur;
import animals.Penguin;
import characteristicsOfAnimals.Animal;
import sqlDataBase.SqlDataBaseManger;

public class AnimalAgeOneYear<T> {

	// Array lists - increase per year
	public <T extends Animal> String animalAgeOneYear(ArrayList<T> animals, ZooManger manger,
			SqlDataBaseManger sqlDataBaseManger) throws SQLException {
		int maxAge = 0;
		StringBuffer str = new StringBuffer();

		// Decreased amount of food food per animal type due to removing an animal from
		// the zoo

		int i = 0;
		if (animals.get(i) instanceof Penguin)
			maxAge = Penguin.LifeSpan;
		else if (animals.get(i) instanceof Carnivore)
			maxAge = Carnivore.LifeSpan;
		else if (animals.get(i) instanceof AquariumFish)
			maxAge = AquariumFish.LifeSpan;
		else if (animals.get(i) instanceof ClownFish)
			maxAge = ClownFish.LifeSpan;
		else if (animals.get(i) instanceof GoldFish)
			maxAge = GoldFish.LifeSpan;
		else if (animals.get(i) instanceof Lemur)
			maxAge = Lemur.LifeSpan;
		else
			maxAge = Cervus.LifeSpan;
		for (i = 0; i < animals.size(); i++) {
			animals.get(i).ageOneYear();
			int animalId = animals.get(i).getAnimalId();
			int newAge = animals.get(i).getAge();
			int happiness = animals.get(i).getHappiness();
			sqlDataBaseManger.updateAnimalAge(animalId, newAge);
			sqlDataBaseManger.updateAnimalHappiness(animalId, happiness);

			// Deletion of the animal that has passed its lifespan
			if (animals.get(i).getAge() > maxAge || animals.get(i).getHappiness() == 0) {

				str.append("Remove -  " + animals.get(i).toString()); // The animal print
																		// removed at the zoo
				if (animals.get(i) instanceof Fish) {
					manger.removeAnimalById(animalId);
					//animals.remove(i--); // -> if the animal is fish i need first delete him after that cheak all colors
											// again in the left of fish
					manger.cheakAgainAllColorsInZooAfterDealeteFish();
					return str.toString();

				}
				else
					manger.removeAnimalById(animalId);

				// animals.remove(i--);
			
			}
		}

		return str.toString();

	}
}
