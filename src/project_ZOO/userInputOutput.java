package project_ZOO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import animals.AquariumFish;
import animals.Carnivore;
import animals.Cervus;
import animals.ClownFish;
import animals.Fish;
import animals.GoldFish;
import animals.Lemur;
import animals.Lion;
import animals.Penguin;
import animals.Tiger;
import characteristicsOfAnimals.Animal;
import characteristicsOfAnimals.CharacteristicOfAFish;
import characteristicsOfAnimals.FishColor;
import characteristicsOfAnimals.Gender;
import characteristicsOfAnimals.FishColor.ClownFishColor;
import characteristicsOfAnimals.FishColor.GoldFishColor;
import exception.HandlingOfExceptions;
import exception.MyException;
import exception.MyExceptionAge;
import exception.MyExceptionForHeight;
import sqlDataBase.SqlCreateQuenstion;

public class userInputOutput {

	static JOptionPane IO = new JOptionPane();

	Scanner scan = new Scanner(System.in);

	private FishColor arrayOfFishColor[] = FishColor.values();
	private GoldFishColor arrayOfGoldFishColor[] = GoldFishColor.values();
	private ClownFishColor arrayOfClownFishColor[] = ClownFishColor.values();

	private CharacteristicOfAFish arrayOfCharacter[] = CharacteristicOfAFish.values();
	private ZooManger manger;
	private HandlingOfExceptions exp = new HandlingOfExceptions();
	private Random rand = new Random();
	private boolean addThisAnimal;
	static StringBuffer inputOutputText = new StringBuffer();

	public userInputOutput(ZooManger manger) {
		this.manger = manger;
	}

	// .....................................general..................................//

	public void addSupplayForZoo() {
		inputOutputText.setLength(0);
		boolean Succeeded;
		int supplayForFish;
		int supplayForPenguins, supplayForCarnivores, supplayForLemurs, supplayForCervus;
		IO.showMessageDialog(null, manger.getHowManyFoodYouCanBuy());
		supplayForFish = exp.tryCatchFuncNumbers(0, "How much food supply do you want to add to the fish (int number)");
		supplayForPenguins = exp.tryCatchFuncNumbers(0,
				"How much food do you want to add to the penguins? (int number)");
		supplayForCarnivores = exp.tryCatchFuncNumbers(0,
				"How much food do you want to add to the predators (int number)");
		supplayForLemurs = exp.tryCatchFuncNumbers(0, "How much food do you want to add to the Lemurs (int number)");
		supplayForCervus = exp.tryCatchFuncNumbers(0, "How much food do you want to add to the Cervus (int number)");

		Succeeded = manger.IsThereMoneyToBuyFoodForAnimals(supplayForFish, supplayForPenguins, supplayForCarnivores,
				supplayForLemurs, supplayForCervus);
		if (Succeeded) {
			inputOutputText.append("\nYou managed to get the supplies to all the animals\n");
			inputOutputText.append(manger.getSupplayInZoo());
			inputOutputText.append("\n" + manger.getMoneyWithMassage());
			IO.showMessageDialog(null, inputOutputText);
			return;
		} 
		IO.showMessageDialog(null, " not enoth money ");

	}

	// ....................................Penguin................................................//

	public void addOfPenguin() {
		inputOutputText.setLength(0);
		if (manger.getMoney() < manger.price_buy_penguin) {
			IO.showMessageDialog(null, "not enoth money to add pengiun");
			IO.showMessageDialog(null, "you have " + manger.getMoney() + "$");
			IO.showMessageDialog(null, "the price for new pengiun is " + manger.price_buy_penguin + "$");
			return;
		}

		Penguin penguin = new Penguin();
		String newNamePenguin;
		int age;

		do {
			newNamePenguin = exp.tryCatchFuncString("Enter name of new penguin: ");
			penguin.setName(newNamePenguin);
		} while (newNamePenguin.length() < 1);

		inputOutputText.append("please enter the age of penguin 0 and " + Penguin.LifeSpan + "years\n");
		boolean temp = false;

		do {
			try {
				String input = IO.showInputDialog(inputOutputText.toString());
				age = Integer.valueOf(input);
				penguin.setAgePenguin(age);
				temp = false;
			} catch (MyExceptionAge e) {
				IO.showMessageDialog(null, e.getMessage());
				temp = true;
			} catch (Exception e) { // A exception that we didn't think about.
				IO.showMessageDialog(null, "EROR, Please enter number again [0-" + Penguin.LifeSpan + "] years");
				temp = true;
			}
		} while (temp);

		float height;
		inputOutputText.setLength(0);
		inputOutputText.append("please enter the heigh of the penguin betwen ");

		if (manger.getLeaderHeadPenguin() != 0)
			inputOutputText.append(Penguin.MIN_HEIGHT_PENGUIN + " to " + manger.getLeaderHeadPenguin() + "cm\n");
		else // When there is no penguin in the zoo the new height will be checked in
				// relation to the maximum possible height
			inputOutputText.append(Penguin.MIN_HEIGHT_PENGUIN + " to " + Penguin.MAX_HEIGHT_PENGUIN + "cm\n");

		temp = true;
		while (temp) {
			try {
				String input = IO.showInputDialog(inputOutputText.toString());
				height = Float.valueOf(input);
				penguin.setHeightPenguin(height);
				temp = false;

			} catch (MyException e) {
				IO.showMessageDialog(null, e.getMessage());
				temp = true;
			} catch (MyExceptionForHeight e) {
				IO.showMessageDialog(null, e.getMessage());
				temp = true;
			} catch (Exception e) { // A exception that we didn't think about.
				IO.showMessageDialog(null, "EROR, Please enter number above 0 cm ");
				temp = true;

			}
		}

		manger.CheckingIfThereIsMoneyToBuyAnimals(penguin);
		IO.showMessageDialog(null, "Successfully add this penguin to the zoo");
		IO.showMessageDialog(null, "current money = " + manger.getMoney() + '$');

	}

	// ..........................................Carnivore...................................................//

	void SelectingTheCarnivoreTheUserWants() {
		inputOutputText.setLength(0);

		int choose;
		Carnivore newCarnivore;
		choose = exp.tryCatchFuncNumbers(1, 2, "Press 1 to add lion or 2 to add tiger");

		if (choose == 1) {
			newCarnivore = new Lion();
			if (manger.getMoney() < manger.price_buy_lion) {
				inputOutputText.append("not enoth money to add lion\n");
				inputOutputText.append("you have " + manger.getMoney() + "$\n");
				inputOutputText.append(" the price for new lion is " + manger.price_buy_lion + "$\n");
				IO.showMessageDialog(null, inputOutputText);
				return;
			}

		} else {
			newCarnivore = new Tiger();
			inputOutputText.setLength(0);
			if (manger.getMoney() < manger.price_buy_tiger) {
				inputOutputText.append("not enoth money to add tiger");
				inputOutputText.append("you have " + manger.getMoney() + "$");
				inputOutputText.append(" the price for new tiger is " + manger.price_buy_tiger + "$");
				IO.showMessageDialog(null, inputOutputText);
				return;
			}

		}
		addOfCarnivore(newCarnivore);
		IO.showMessageDialog(null, "current money = " + manger.getMoney() + '$');
	}

	public void addOfCarnivore(Carnivore newCarnivore) {
		inputOutputText.setLength(0);
		String newNameLion;
		int gender;
		float weight;

		inputOutputText.append("Enter name of new " + newCarnivore.TheTypeOfCarnivore() + ": ");
		newNameLion = exp.tryCatchFuncString(inputOutputText.toString());
		newCarnivore.setName(newNameLion);
		inputOutputText.append("please entwe a age of ");
		inputOutputText.append(newCarnivore.TheTypeOfCarnivore());
		inputOutputText.append(" between " + Animal.MIN_AGE_ANIMAL);
		inputOutputText.append(" and " + Lion.LifeSpan);
		int age = exp.tryCatchFuncNumbers(Animal.MIN_AGE_ANIMAL, Lion.LifeSpan, "age",
				newCarnivore.TheTypeOfCarnivore(), "years", inputOutputText.toString());
		newCarnivore.setAge(age);

		if (newCarnivore instanceof Lion) {
			inputOutputText.setLength(0);
			inputOutputText.append("please enter a weight of ");
			inputOutputText.append(newCarnivore.TheTypeOfCarnivore());
			inputOutputText.append(" between " + Carnivore.MIN_WEIGHT_LION_OR_TIGER);
			inputOutputText.append(" and " + Lion.MAX_WEIGHT_LION + " kg");
			weight = exp.tryCatchFuncNumbers(Carnivore.MIN_WEIGHT_LION_OR_TIGER, Lion.MAX_WEIGHT_LION, "weight",
					newCarnivore.TheTypeOfCarnivore(), "kg", inputOutputText.toString());
		} else {
			inputOutputText.setLength(0);
			inputOutputText.append("please enter a weight of ");
			inputOutputText.append(newCarnivore.TheTypeOfCarnivore());
			inputOutputText.append(" between " + Carnivore.MIN_WEIGHT_LION_OR_TIGER);
			inputOutputText.append(" and " + Tiger.MAX_WEIGHT_TIGER);
			weight = exp.tryCatchFuncNumbers(Carnivore.MIN_WEIGHT_LION_OR_TIGER, Tiger.MAX_WEIGHT_TIGER, "weight",
					newCarnivore.TheTypeOfCarnivore(), "kg", inputOutputText.toString());
		}
		newCarnivore.setWeightCarnivore(weight);
		inputOutputText.setLength(0);
		inputOutputText
				.append("Enter gender of new " + newCarnivore.TheTypeOfCarnivore() + " 1 for MALE or 2 for FEMALE");
		gender = exp.tryCatchFuncNumbers(1, 2, inputOutputText.toString());

		if (gender == 1)
			newCarnivore.setGenderCarnivore(Gender.MALE);
		else
			newCarnivore.setGenderCarnivore(Gender.FEMALE);

		addThisAnimal = manger.CheckingIfThereIsMoneyToBuyAnimals(newCarnivore);
		if (addThisAnimal)
			IO.showMessageDialog(null, "Successfully add this " + newCarnivore.TheTypeOfCarnivore() + " to the zoo");

		else {
			inputOutputText.setLength(0);
			inputOutputText.append("sorry you dont have enoth money to add this \n");
			if (newCarnivore instanceof Lion)
				inputOutputText.append("lion");
			else
				inputOutputText.append("tiger");

			IO.showMessageDialog(null, inputOutputText);
		}

	}

	// .....................................Fish...................................................//
	public void SelectingTheFishTheUserWants() {
		inputOutputText.setLength(0);
		if (manger.getMoney() < manger.price_buy_fish) {
			inputOutputText.append("not enoth money to add fish");
			inputOutputText.append("you have " + manger.getMoney() + "$");
			inputOutputText.append(" the price for new fish is " + manger.price_buy_fish + "$");
			IO.showMessageDialog(null, inputOutputText);
			return;
		}
		inputOutputText.setLength(0);
		inputOutputText.append("for add noraml Aquarium Fiah press 1\n");
		inputOutputText.append("for add Gold Fish press 2\n");
		inputOutputText.append("for add Clown  Fiah press 3\n");
		int choose = exp.tryCatchFuncNumbers(1, 3, inputOutputText.toString());

		if (choose == 1) {
			AquariumFish newAquariumFiah = new AquariumFish();
			addFishGenericMetodByhuman(newAquariumFiah, AquariumFish.LifeSpan, AquariumFish.MIN_LENGTH_FISH,
					AquariumFish.MAX_LENGTH_FISH);

			IO.showMessageDialog(null, newAquariumFiah.toString());
			IO.showMessageDialog(null, "Successfully add this Clown Fish");
		} else if (choose == 2) {
			Fish newGoldFish = new GoldFish();
			addFishGenericMetodByhuman(newGoldFish, GoldFish.LifeSpan, GoldFish.MIN_LENGTH_GOLD,
					GoldFish.MAX_LENGTH_GOLD);
			IO.showMessageDialog(null, newGoldFish.toString());
			IO.showMessageDialog(null, "Successfully add this Clown Fish");

		} else if (choose == 3) {
			Fish newClownFish = new ClownFish();
			addFishGenericMetodByhuman(newClownFish, ClownFish.LifeSpan, ClownFish.MIN_LENGTH_CLOWN,
					ClownFish.MAX_LENGTH_CLOWN);
			IO.showMessageDialog(null, newClownFish.toString());
			IO.showMessageDialog(null, "Successfully add this Clown Fish");

		}
		IO.showMessageDialog(null, "current money = " + manger.getMoney() + '$');

	}

	@SuppressWarnings("hiding")
	public <T extends Fish> void addFishGenericMetodByhuman(T newFish, int maxAge, double minLength, double maxLength) {
		newFish = (T) newFish;
		inputOutputText.setLength(0);
		int choose = 0, age = 0;
		inputOutputText.append("please enter age of fish");
		age = exp.tryCatchFuncNumbers(0, maxAge, "age", "fish", "years", inputOutputText.toString());
		newFish.setAge(age);
		inputOutputText.setLength(0);
		inputOutputText.append("please enter length of fish in cm");
		double fishLength = exp.tryCatchFuncNumbers(minLength, maxLength, "length", "fish", "cm",
				inputOutputText.toString());
		newFish.setFishLength(fishLength);

		if (newFish instanceof ClownFish) {
			for (int i = 0; i < arrayOfClownFishColor.length; i++)
				newFish.setFishColor(arrayOfClownFishColor[i]);
			;

			manger.CheckingIfThereIsMoneyToBuyAnimals(newFish);
			return;

		}

		if (newFish instanceof GoldFish) {
			inputOutputText.setLength(0);
			for (int i = 0; i < arrayOfGoldFishColor.length; i++)
				inputOutputText.append((i + 1) + ") for ->" + arrayOfGoldFishColor[i] + "\n");

			choose = exp.tryCatchFuncNumbers(1, arrayOfGoldFishColor.length, inputOutputText.toString()); // <-
			((GoldFish) newFish).setFishColor(arrayOfGoldFishColor[choose - 1]);
			manger.CheckingIfThereIsMoneyToBuyAnimals(newFish);
			return;

		}

		addingAquariumFishPart2((AquariumFish) newFish);
	}

	public void addingAquariumFishPart2(AquariumFish newFish) {
		inputOutputText.setLength(0);
		int choose = 0;
		inputOutputText.append("How many colors does the fish have from the given options? \n");

		inputOutputText.append(
				"Number of possible colors between 1 and " + arrayOfFishColor.length + "): \nThe options are:\n");

		for (int i = 0; i < arrayOfFishColor.length; i++)
			inputOutputText.append((i + 1) + ") " + arrayOfFishColor[i] + "\n");

		inputOutputText.append("A special choice. To add all existing colors to the fish, press 11\n");

		int amountOfColors = exp.tryCatchFuncNumbers(1, arrayOfFishColor.length + 1, inputOutputText.toString()); // <-
		;
		inputOutputText.setLength(0);
		if (amountOfColors == 11)
			addAllOfColorsToFish((AquariumFish) newFish);
		else {

			for (int i = 1; i <= amountOfColors; i++) {
				inputOutputText.setLength(0);
				inputOutputText.append("what is color #" + i + " of your fish? Options: \n");

				do { // Color selection menu
					for (int j = 0; j < arrayOfFishColor.length; j++)
						inputOutputText.append((j + 1) + ") for ->" + arrayOfFishColor[j] + "\n");

					choose = exp.tryCatchFuncNumbers(1, 10, inputOutputText.toString()); // <-
					if (CheckForDuplicateColors(newFish.getFishColor(), arrayOfFishColor[choose - 1])) {
						IO.showMessageDialog(null, "Error, a fish cannot be the same color twice. Select again");
						inputOutputText.setLength(0);
					}

				} while (choose < 0 || choose > arrayOfFishColor.length
						|| CheckForDuplicateColors(newFish.getFishColor(), arrayOfFishColor[choose - 1]));
				newFish.setFishColor(arrayOfFishColor[choose - 1]);
			}
		}
		choose = 0;
		inputOutputText.setLength(0);
		for (int i = 0; i < arrayOfCharacter.length; i++)
			inputOutputText.append((i + 1) + "for ->" + arrayOfCharacter[i] + "\n");
		choose = exp.tryCatchFuncNumbers(1, arrayOfCharacter.length, inputOutputText.toString()); // <-

		newFish.setFishTrait(arrayOfCharacter[choose - 1]);
		manger.CheckingIfThereIsMoneyToBuyAnimals(newFish);
	}

	public void addRandomFishToZoo(int numbersOfFish, boolean notFishIncomeFromTheSystem) {
		FishColor newColor;
		int counterAquariumFiah = 0;
		int counterGoldFish = 0;
		int counterClownFish = 0;
		for (int i = 0; i < numbersOfFish; i++) {
			int randomFish = rand.nextInt(3);
			AquariumFish newAquariumFiah = new AquariumFish();

			if (randomFish == 0) {
				GoldFish newGoldFish = new GoldFish();
				newGoldFish.setAge(rand.nextInt(GoldFish.LifeSpan) + 1);
				int randColor = arrayOfGoldFishColor.length;
				newGoldFish.setFishColor(arrayOfGoldFishColor[rand.nextInt(randColor)]);
				newGoldFish.setFishLength(rand.nextDouble(GoldFish.MAX_LENGTH_GOLD - GoldFish.MIN_LENGTH_GOLD)
						+ GoldFish.MIN_LENGTH_GOLD);
				addThisAnimal = manger.CheckingIfThereIsMoneyToBuyAnimals(newGoldFish);
				if (addThisAnimal)
					counterGoldFish++;
			}

			else if (randomFish == 1) {
				ClownFish newClownFish = new ClownFish();
				newClownFish.setAge(rand.nextInt(ClownFish.LifeSpan) + 1);
				for (int j = 0; j < arrayOfClownFishColor.length; j++)
					newClownFish.setFishColor(arrayOfClownFishColor[j]);

				newClownFish.setFishLength(rand.nextDouble(ClownFish.MAX_LENGTH_CLOWN - ClownFish.MIN_LENGTH_CLOWN)
						+ ClownFish.MIN_LENGTH_CLOWN);
				addThisAnimal = manger.CheckingIfThereIsMoneyToBuyAnimals(newClownFish);
				if (addThisAnimal)
					counterClownFish++;
			}

			else {
				int numberRandColor = rand.nextInt(4) + 1; // It is assumed that a random fish has up to 4 colors
				for (int j = 0; j < numberRandColor; j++) {
					do {
						newColor = arrayOfFishColor[rand.nextInt(arrayOfFishColor.length)];
					} while (CheckForDuplicateColors(newAquariumFiah.getFishColor(), newColor));
					newAquariumFiah.setFishColor(newColor);
				}
				newAquariumFiah.setFishTrait(arrayOfCharacter[rand.nextInt(arrayOfCharacter.length)]);
				newAquariumFiah.setAgeOfFish(rand.nextInt(AquariumFish.LifeSpan) + 1);
				newAquariumFiah
						.setFishLength(rand.nextDouble(AquariumFish.MAX_LENGTH_FISH - AquariumFish.MIN_LENGTH_FISH)
								+ AquariumFish.MIN_LENGTH_FISH);
				addThisAnimal = manger.CheckingIfThereIsMoneyToBuyAnimals(newAquariumFiah);
				if (addThisAnimal)
					counterAquariumFiah++;

			}
		}
		if (notFishIncomeFromTheSystem) {
			inputOutputText.setLength(0);
			int sumOfFishAff = counterAquariumFiah + counterGoldFish + counterClownFish;
			inputOutputText.append("Successfully add " + sumOfFishAff + " fish to the zoo\n");
			inputOutputText.append("Aquarium Fiah added :" + counterAquariumFiah + "\n");
			inputOutputText.append("Gold Fish added :" + counterGoldFish + "\n");
			inputOutputText.append("Clown Fish added :" + counterClownFish + "\n");
			IO.showMessageDialog(null, inputOutputText);
			if (numbersOfFish != sumOfFishAff) {
				inputOutputText.setLength(0);
				inputOutputText.append("you dont have money to add " + (numbersOfFish - sumOfFishAff) + " left");
				IO.showMessageDialog(null, inputOutputText);
			}
			IO.showMessageDialog(null, "current money = " + manger.getMoney() + '$');

		}
	}

	// Checking for duplicates in the color of the fish
	public boolean CheckForDuplicateColors(ArrayList<Object> arrayList, FishColor newColor) {
		for (int i = 0; arrayList.size() != i; i++)
			if (arrayList.get(i) == newColor) {
				return true; // duplicates in color

			}
		return false; // No duplicates in color

	}

	public void addAllOfColorsToFish(AquariumFish newFish) {
		for (int i = 0; i < arrayOfFishColor.length; i++)
			newFish.setFishColor(arrayOfFishColor[i]);
	}

//..................................................Lemurs........................//

	public void addLenurToTheZoo() {
		inputOutputText.setLength(0);
		if (manger.getMoney() < manger.price_buy_lemur) {
			IO.showMessageDialog(null, "not enoth money to add Lemur");
			IO.showMessageDialog(null, "you have " + manger.getMoney() + "$");
			IO.showMessageDialog(null, "the price for new Lemur is " + manger.price_buy_lemur + "$");
			return;
		}
		inputOutputText.setLength(0);
		inputOutputText.append("Enter name of new Lemur");
		String nameOfLemur = exp.tryCatchFuncString(inputOutputText.toString());
		inputOutputText.setLength(0);
		inputOutputText.append("enter age to new Lemur betwen [0,18]");
		int ageOfLemur = exp.tryCatchFuncNumbers(Animal.MIN_AGE_ANIMAL, Lemur.LifeSpan, inputOutputText.toString());
		inputOutputText.setLength(0);
		inputOutputText.append("enter height to new Lemur betwen [30,50]");
		float heightOfLemur = exp.tryCatchFuncNumbers(Lemur.MIN_HEIGHT_LEMUR, Lemur.MAX_HEIGHT_LEMUR,
				inputOutputText.toString());

		Lemur newLemur = new Lemur(-1, nameOfLemur, ageOfLemur, heightOfLemur);
		manger.CheckingIfThereIsMoneyToBuyAnimals(newLemur);
		IO.showMessageDialog(null, "Successfully add this Lemur to the zoo");
		IO.showMessageDialog(null, newLemur.toString());
		IO.showMessageDialog(null, "current money = " + manger.getMoney() + '$');

	}

	// ..................................................Cervus........................//

	public void addCervusToTheZoo() {

		int gender;
		Cervus newCervus = new Cervus();
		inputOutputText.setLength(0);
		if (manger.getMoney() < manger.price_buy_cervus) {
			IO.showMessageDialog(null, "not enoth money to add Cervus");
			IO.showMessageDialog(null, "you have " + manger.getMoney() + "$");
			IO.showMessageDialog(null, "the price for new Cervus is " + manger.price_buy_cervus + "$");
			return;
		}
		inputOutputText.setLength(0);
		inputOutputText.append("Enter name of new cervus");
		String nameOfCervus = exp.tryCatchFuncString(inputOutputText.toString());
		newCervus.setName(nameOfCervus);
		inputOutputText.setLength(0);
		;
		inputOutputText.append("Enter gender of new cervus 1 for MALE or 2 for FEMALE");
		gender = exp.tryCatchFuncNumbers(1, 2, inputOutputText.toString());

		if (gender == 1)
			newCervus.setGenderCervus(Gender.MALE);
		else
			newCervus.setGenderCervus(Gender.FEMALE);
		inputOutputText.setLength(0);
		inputOutputText.append("enter age to new Cervus betwen [0," + Cervus.LifeSpan + "]");
		int ageOfCervus = exp.tryCatchFuncNumbers(Animal.MIN_AGE_ANIMAL, Cervus.LifeSpan, inputOutputText.toString());
		newCervus.setAge(ageOfCervus);
		inputOutputText.setLength(0);
		inputOutputText.append("enter length of the rays to new cervus betwen [" + Cervus.minLengthOfTheRays + ","
				+ Cervus.maxLengthOfTheRays + "]");
		float lengthOfTheRays = exp.tryCatchFuncNumbers(Cervus.minLengthOfTheRays, Cervus.maxLengthOfTheRays,
				inputOutputText.toString());
		newCervus.setLengthOfTheRays(lengthOfTheRays);

		manger.CheckingIfThereIsMoneyToBuyAnimals(newCervus);
		IO.showMessageDialog(null, "Successfully add this Cervus to the zoo");
		IO.showMessageDialog(null, newCervus.toString());
		IO.showMessageDialog(null, "current money = " + manger.getMoney() + '$');
	}

}
