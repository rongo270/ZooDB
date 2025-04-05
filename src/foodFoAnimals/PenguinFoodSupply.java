package foodFoAnimals;

public class PenguinFoodSupply implements FoodSupply {

	  private int id;
	    private float price;
	    private int countOfFood;

	    public PenguinFoodSupply(int id, float price, int countOfFood) {
	        this.id = id;
	        this.price = price;
	        this.countOfFood = countOfFood;
	    }
	    
	    @Override
		public int getFoodAnimalId() {
			return this.id;
		}
	    
		@Override
		public void addFood(int quantity) {
			this.countOfFood+=quantity;
		}

		@Override
		public void changePrice(float newPrice) {
			this.price = newPrice;
		}

		@Override
		public int getCountOfFood() {
			return this.countOfFood;
		}

		@Override
		public float getPrice() {
			return this.price;
		}
		
		@Override
		public void depriveFood(int foodEaten) {
			this.countOfFood-=foodEaten;
			
		}
}
