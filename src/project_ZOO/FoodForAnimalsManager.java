package project_ZOO;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import animals.AnimalType;
import animals.Cervus;
import foodFoAnimals.CarnivoresFoodSupply;
import foodFoAnimals.CervusFoodSupply;
import foodFoAnimals.FishFoodSupply;
import foodFoAnimals.FoodSupply;
import foodFoAnimals.FoodSupplyFish;
import foodFoAnimals.FoodType;
import foodFoAnimals.LemurFoodSupply;
import foodFoAnimals.PenguinFoodSupply;
import foodFoAnimals.PrinterSupplayInZoo;
import foodFoAnimals.SupplyManager;

public class FoodForAnimalsManager {

	private static FoodForAnimalsManager myManger;

	private SupplyManager mySupplyManager;

	private FoodForAnimalsManager(SupplyManager mySupplyManager) {
		this.mySupplyManager = mySupplyManager;

	}

	public static synchronized FoodForAnimalsManager getTheManger(SupplyManager mySupplyManager) {
		if (myManger == null)
			myManger = new FoodForAnimalsManager(mySupplyManager);
		return myManger;
	}

	/*
	 * public void setNewPriceForFood(int foodId, int newPrice) {
	 * myFoodForAnimals.get(foodId).changePrice(newPrice); }
	 */

	public boolean cheakIfCanBuyFood(int id, float balance) {
		return mySupplyManager.cheakIfCanBuyFood(id, balance);
		// return (myFoodForAnimals.get(id).getPrice() - balance) >= 0;
	}

	public void addAnimalFoodById(int foodId, int quantity) {
		mySupplyManager.addAnimalFoodById(foodId, quantity);
	}

	public double IsThereMoneyToBuyFoodForAnimals(double supplayFoodForFish, int supplayFoodForPenguin,
			int supplayFoodForCarnivores, int supplayForLemurs, int supplayForCervus) {
		return mySupplyManager.IsThereMoneyToBuyFoodForAnimals(supplayFoodForFish, supplayFoodForPenguin,
				supplayFoodForCarnivores, supplayForLemurs, supplayForCervus);

	}

	public void addFoodSupplayForZoo(int supplayFoodForFish, int supplayFoodForPenguin, int supplayFoodForCarnivores,
			int supplayForLemurs, int supplayForCervus) {

		mySupplyManager.addFoodSupplayForZoo(supplayFoodForFish, supplayFoodForPenguin, supplayFoodForCarnivores,
				supplayForLemurs, supplayForCervus);

	}

	public void printSupplayInZoo() {
		mySupplyManager.printSupplayInZoo();

	}

	public String getSupplayInZoo() {
		return mySupplyManager.getSupplayInZoo();

	}

	public boolean cheakIfCanFeedAllAnimalsInZoo(double sumOfFishFood, int sumOfCarnivores, int foodForPenguin,
			int foodForCervus, int foodForLemurs) {

		return mySupplyManager.cheakIfCanFeedAllAnimalsInZoo(sumOfFishFood, sumOfCarnivores, foodForPenguin,
				foodForCervus, foodForLemurs);

	}

	public void feedAllAnimalsInZoo(double sumOfFishFood, int sumOfCarnivores, int foodForPenguin, int foodForCervus,
			int foodForLemurs) {
		mySupplyManager.feedAllAnimalsInZoo(sumOfFishFood, sumOfCarnivores, foodForPenguin, foodForCervus,
				foodForLemurs);

	}

	public Map<Integer, FoodType> getTheProblemInFood(double sumOfFishFood, int sumOfCarnivores, int foodForPenguin,
			int foodForCervus, int foodForLemurs) {
		return mySupplyManager.getTheProblemInFood(sumOfFishFood, sumOfCarnivores, foodForPenguin, foodForCervus,
				foodForLemurs);

	}

}
