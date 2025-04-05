package foodFoAnimals;

import java.util.Map;

public interface SupplyManager {


    void setNewPriceForFood(int foodId, int newPrice);

    boolean cheakIfCanBuyFood(int id, float balance);

    void addAnimalFoodById(int foodId, int quantity);

    double IsThereMoneyToBuyFoodForAnimals(double supplayFoodForFish, int supplayFoodForPenguin, int supplayFoodForCarnivores,
            int supplayForLemurs, int supplayForCervus);

    void addFoodSupplayForZoo(int supplayFoodForFish, int supplayFoodForPenguin, int supplayFoodForCarnivores,
            int supplayForLemurs, int supplayForCervus);

    boolean cheakIfCanFeedAllAnimalsInZoo(double sumOfFishFood, int sumOfCarnivores, int foodForPenguin,
            int foodForCervus, int foodForLemurs);

    void feedAllAnimalsInZoo(double sumOfFishFood, int sumOfCarnivores, int foodForPenguin, int foodForCervus,
            int foodForLemurs);

    Map<Integer, FoodType> getTheProblemInFood(double sumOfFishFood, int sumOfCarnivores, int foodForPenguin,
            int foodForCervus, int foodForLemurs);

    String getSupplayInZoo();
    
    void printSupplayInZoo();



}
