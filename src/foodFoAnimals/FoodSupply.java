package foodFoAnimals;

public interface FoodSupply {

    void addFood(int quantity);
    
    void depriveFood(int foodEaten);

    void changePrice(float newPrice);

    int getCountOfFood();
    
    int getFoodAnimalId();

    float getPrice();
}
