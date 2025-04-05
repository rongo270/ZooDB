package foodFoAnimals;

import java.util.List;
import java.util.Map;

import animals.AnimalType;

public interface PrintSupplayInZoo {

	void printSupplayInZoo(List<FoodSupply> listOfFoodSupply ,List<FoodType> listOfFoodType , FoodSupplyFish foodForFish );
	
	void printFoodSupplyProblem(Map<Integer, FoodType> mapOfProblem , Map<Integer, Map<Integer , Number  > > FoodMap ,  FoodSupplyFish foodForFish );
	


}
