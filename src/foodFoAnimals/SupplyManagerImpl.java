package foodFoAnimals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class SupplyManagerImpl implements SupplyManager {

	
	 private HashMap<Integer, FoodSupply> myFoodForAnimals;
	    private FoodSupplyFish foodForFish;
	    private HashMap<Integer, FoodType> foodTypesById;
	    private List<FoodSupply> listOfFoodSupply;
	    private List<FoodType> listOfFoodType;
	    private PrinterSupplayInZoo myPrinterSupply;

	    public SupplyManagerImpl(HashMap<Integer, FoodSupply> foodForAnimal, FoodSupplyFish foodForFish ,    PrinterSupplayInZoo printerSupplayInZoo) {
	        this.myFoodForAnimals = foodForAnimal;
	        this.foodForFish = foodForFish;
	        this.myPrinterSupply = printerSupplayInZoo;

	        this.foodTypesById = new HashMap<>();
	        this.listOfFoodSupply = new LinkedList<>();
	        this.listOfFoodType = new LinkedList<>();

	        foodTypesById.put(foodForFish.getFoodAnimalId(), FoodType.FISH);
	        for (Entry<Integer, FoodSupply> entry : myFoodForAnimals.entrySet()) {
	            FoodSupply foodSupply = entry.getValue();
	            int id = entry.getKey();
	            listOfFoodSupply.add(foodSupply);

	            if (foodSupply instanceof foodFoAnimals.PenguinFoodSupply)
	                foodTypesById.put(id, FoodType.PENGUIN);
	            else if (foodSupply instanceof foodFoAnimals.CarnivoresFoodSupply)
	                foodTypesById.put(id, FoodType.CARNIVORES);
	            else if (foodSupply instanceof foodFoAnimals.LemurFoodSupply)
	                foodTypesById.put(id, FoodType.LEMUR);
	            else if (foodSupply instanceof foodFoAnimals.CervusFoodSupply)
	                foodTypesById.put(id, FoodType.CERVUS);
	            listOfFoodType.add(foodTypesById.get(id));
	        }
	    }

	    @Override
	    public void setNewPriceForFood(int foodId, int newPrice) {
	        myFoodForAnimals.get(foodId).changePrice(newPrice);
	    }

	    @Override
	    public boolean cheakIfCanBuyFood(int id, float balance) {
	        return (myFoodForAnimals.get(id).getPrice() - balance) >= 0;
	    }

	    @Override
	    public void addAnimalFoodById(int foodId, int quantity) {
	        myFoodForAnimals.get(foodId).addFood(quantity);
	    }

	    @Override
	    public double IsThereMoneyToBuyFoodForAnimals(double supplayFoodForFish, int supplayFoodForPenguin,
	            int supplayFoodForCarnivores, int supplayForLemurs, int supplayForCervus) {
	        double totalCost = 0;
	        for (Map.Entry<Integer, FoodSupply> entry : myFoodForAnimals.entrySet()) {
	            int id = entry.getKey();
	            FoodSupply foodSupply = entry.getValue();
	            FoodType foodType = foodTypesById.get(id);

	            switch (foodType) {
	            case PENGUIN:
	                totalCost += foodSupply.getPrice() * supplayFoodForPenguin;
	                break;
	            case CARNIVORES:
	                totalCost += foodSupply.getPrice() * supplayFoodForCarnivores;
	                break;
	            case LEMUR:
	                totalCost += foodSupply.getPrice() * supplayForLemurs;
	                break;
	            case CERVUS:
	                totalCost += foodSupply.getPrice() * supplayForCervus;
	                break;
	            }
	        }
	        totalCost += this.foodForFish.getPrice() * supplayFoodForFish;
	        return totalCost;
	    }

	    @Override
	    public void addFoodSupplayForZoo(int supplayFoodForFish, int supplayFoodForPenguin, int supplayFoodForCarnivores,
	            int supplayForLemurs, int supplayForCervus) {
	        foodForFish.addFood(supplayFoodForFish);
	        for (Map.Entry<Integer, FoodSupply> entry : myFoodForAnimals.entrySet()) {
	            int id = entry.getKey();
	            FoodSupply foodSupply = entry.getValue();
	            FoodType foodType = foodTypesById.get(id);

	            switch (foodType) {
	            case PENGUIN:
	                foodSupply.addFood(supplayFoodForPenguin);
	                break;
	            case CARNIVORES:
	                foodSupply.addFood(supplayFoodForCarnivores);
	                break;
	            case LEMUR:
	                foodSupply.addFood(supplayForLemurs);
	                break;
	            case CERVUS:
	                foodSupply.addFood(supplayForCervus);
	                break;
	            }
	        }
	    }

	    @Override
	    public boolean cheakIfCanFeedAllAnimalsInZoo(double sumOfFishFood, int sumOfCarnivores, int foodForPenguin,
	            int foodForCervus, int foodForLemurs) {
	        if ((foodForFish.getCountOfFood() - sumOfFishFood) < 0)
	            return false;

	        for (Map.Entry<Integer, FoodSupply> entry : myFoodForAnimals.entrySet()) {
	            int id = entry.getKey();
	            FoodSupply foodSupply = entry.getValue();
	            FoodType foodType = foodTypesById.get(id);

	            switch (foodType) {
	            case PENGUIN:
	                if (foodSupply.getCountOfFood() - foodForPenguin < 0)
	                    return false;
	                break;
	            case CARNIVORES:
	                if (foodSupply.getCountOfFood() - sumOfCarnivores < 0)
	                    return false;
	                break;
	            case LEMUR:
	                if (foodSupply.getCountOfFood() - foodForLemurs < 0)
	                    return false;
	                break;
	            case CERVUS:
	                if (foodSupply.getCountOfFood() - foodForCervus < 0)
	                    return false;
	                break;
	            }
	        }
	        return true;
	    }

	    @Override
	    public void feedAllAnimalsInZoo(double sumOfFishFood, int sumOfCarnivores, int foodForPenguin, int foodForCervus,
	            int foodForLemurs) {
	        this.foodForFish.depriveFood(sumOfFishFood);
	        for (Map.Entry<Integer, FoodSupply> entry : myFoodForAnimals.entrySet()) {
	            int id = entry.getKey();
	            FoodSupply foodSupply = entry.getValue();
	            FoodType foodType = foodTypesById.get(id);

	            switch (foodType) {
	            case PENGUIN:
	                foodSupply.depriveFood(foodForPenguin);
	                break;
	            case CARNIVORES:
	                foodSupply.depriveFood(sumOfCarnivores);
	                break;
	            case LEMUR:
	                foodSupply.depriveFood(foodForLemurs);
	                break;
	            case CERVUS:
	                foodSupply.depriveFood(foodForCervus);
	                break;
	            }
	        }
			myPrinterSupply.printSupplayInZoo(listOfFoodSupply, listOfFoodType, foodForFish);

	    }

	    @Override
	    public Map<Integer, FoodType> getTheProblemInFood(double sumOfFishFood, int sumOfCarnivores, int foodForPenguin,
	            int foodForCervus, int foodForLemurs) {
	        Map<Integer, FoodType> notEnothFoodMap = new HashMap<>();
	        Map<Integer, Map<Integer, Number>> FoodMap = new HashMap<>();

	        Consumer<FoodSupplyFish> foodForFishProblem = (foofForFish) -> {
	            if ((foofForFish.getCountOfFood() - sumOfFishFood) < 0) {
	                notEnothFoodMap.put(foofForFish.getFoodAnimalId(), FoodType.FISH);
	                Map<Integer, Number> foodOfFishMap = new HashMap<>();
	                foodOfFishMap.put(foofForFish.getFoodAnimalId(), Double.valueOf(sumOfFishFood));
	                FoodMap.put(foofForFish.getFoodAnimalId(), foodOfFishMap);
	            }
	        };

	        listOfFoodSupply.forEach(landAnimalsFood -> {
	            FoodType foodType = foodTypesById.get(landAnimalsFood.getFoodAnimalId());
	            Map<Integer, Number> foodOfAnimalsMap = new HashMap<>();

	            switch (foodType) {
	            case CERVUS:
	                if (landAnimalsFood.getCountOfFood() - foodForCervus < 0) {
	                    notEnothFoodMap.put(landAnimalsFood.getFoodAnimalId(), FoodType.CERVUS);
	                    foodOfAnimalsMap.put(landAnimalsFood.getFoodAnimalId(), Integer.valueOf(foodForCervus));
	                }
	                break;
	            case CARNIVORES:
	                if (landAnimalsFood.getCountOfFood() - sumOfCarnivores < 0) {
	                    notEnothFoodMap.put(landAnimalsFood.getFoodAnimalId(), FoodType.CARNIVORES);
	                    foodOfAnimalsMap.put(landAnimalsFood.getFoodAnimalId(), Integer.valueOf(sumOfCarnivores));
	                }
	                break;
	            case LEMUR:
	                if (landAnimalsFood.getCountOfFood() - foodForLemurs < 0) {
	                    notEnothFoodMap.put(landAnimalsFood.getFoodAnimalId(), FoodType.LEMUR);
	                    foodOfAnimalsMap.put(landAnimalsFood.getFoodAnimalId(), Integer.valueOf(foodForLemurs));
	                }
	                break;
	            case PENGUIN:
	                if (landAnimalsFood.getCountOfFood() - foodForPenguin < 0) {
	                    notEnothFoodMap.put(landAnimalsFood.getFoodAnimalId(), FoodType.PENGUIN);
	                    foodOfAnimalsMap.put(landAnimalsFood.getFoodAnimalId(), Integer.valueOf(foodForPenguin));
	                }
	                break;
	            }
	            if (!foodOfAnimalsMap.isEmpty()) {
	                FoodMap.put(landAnimalsFood.getFoodAnimalId(), foodOfAnimalsMap);
	            }
	        });
	        foodForFishProblem.accept(foodForFish);
	        return notEnothFoodMap;
	    }

	    @Override
	    public String getSupplayInZoo() {
	        StringBuffer str = new StringBuffer();
	        listOfFoodSupply.forEach(food -> {
	            int id = listOfFoodSupply.indexOf(food);
	            FoodType foodType = listOfFoodType.get(id);
	            str.append("supplay left for " + foodType + ", is : " + food.getCountOfFood() + "\n");
	        });

	        Consumer<FoodSupplyFish> printFish = (x) -> {
	            str.append("supplay left for fish " + x.getCountOfFood());
	        };

	        printFish.accept(foodForFish);
	        return str.toString();
	    }
	    
	    @Override
	    public void printSupplayInZoo() {
	        myPrinterSupply.printSupplayInZoo(this.listOfFoodSupply, this.listOfFoodType, this.foodForFish);
	    }

	  
}
