package foodFoAnimals;

public class FishFoodSupply implements FoodSupplyFish{
	
	
	private int id ;
	private float price;
	private int countOfFood;
	
	public FishFoodSupply (int id , float price ,  int countOfFood) {
		this.id = id;
		this.price = price;
		this.countOfFood = countOfFood;
	}
	
	 @Override
		public int getFoodAnimalId() {
			return this.id;
		}
	    
		@Override
		public void addFood(double quantity) { 
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
		public void depriveFood(double foodEaten) {
			this.countOfFood-=foodEaten;
			
		}

}
