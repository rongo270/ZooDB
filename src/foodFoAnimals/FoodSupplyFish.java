package foodFoAnimals;

public interface FoodSupplyFish {

	void addFood(double quantity);

	void depriveFood(double foodEaten);

	void changePrice(float newPrice);

	int getCountOfFood();

	int getFoodAnimalId();

	float getPrice();
}
