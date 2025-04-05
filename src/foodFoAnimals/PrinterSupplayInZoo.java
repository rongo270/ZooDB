package foodFoAnimals;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import animals.AnimalType;

public class PrinterSupplayInZoo implements PrintSupplayInZoo {
	static JOptionPane IO = new JOptionPane();

	@Override
	public void printSupplayInZoo(List<FoodSupply> listOfFoodSupply, List<FoodType> listOfFoodType,
			FoodSupplyFish foodForFish) {
		StringBuffer str = new StringBuffer();
		Consumer<FoodSupplyFish> printFish = (x) -> {
			str.append("supplay left for fish " + x.getCountOfFood());
		};

		listOfFoodSupply.forEach(food -> {
			int id = listOfFoodSupply.indexOf(food);
			FoodType foodType = listOfFoodType.get(id);
			str.append("supplay left for " + foodType + ", is : " + food.getCountOfFood() + "\n");

		});
		printFish.accept(foodForFish);
		IO.showMessageDialog(IO, str);
	}
 
	@Override
	public void printFoodSupplyProblem(Map<Integer, FoodType> mapOfProblem, Map<Integer, Map<Integer, Number>> FoodMap,
			FoodSupplyFish foodForFish) {

		StringBuffer str = new StringBuffer();
		str.append("Sorry you don't have enough food to feed the animals : \n");
		mapOfProblem.forEach((foodId, type) -> {
			Map<Integer, Number> askedAndSupplyFood = FoodMap.get(foodId);
			if (askedAndSupplyFood != null) {
				askedAndSupplyFood.forEach((currentFood, wantedFood) -> {

					switch (type) {
					case CARNIVORES:
						str.append("you need " + wantedFood);
						str.append(" but you have only : " + currentFood);
						str.append(" food for carnivores");
						str.append(" please add " + (wantedFood.intValue() - currentFood) + " kg of meat ");
						str.append("\n");
						break;
					case CERVUS:
						str.append("you need " + wantedFood);
						str.append(" but you have only : " + currentFood);
						str.append(" food for carnivores");
						str.append(" please add " + (wantedFood.intValue() - currentFood) + " mix fruits ");
						str.append("\n");
						break;
					case FISH:
						str.append("you need " + wantedFood);
						str.append(" but you have only : " + currentFood);
						str.append(" food for fish");
						str.append(" please add " + (wantedFood.doubleValue() - currentFood) + " food for fish ");
						str.append("\n");
						break;
					case LEMUR:
						str.append("you need " + wantedFood);
						str.append(" but you have only : " + currentFood);
						str.append(" food for lumur");
						str.append(" please add " + (wantedFood.intValue() - currentFood) + " mangos");
						str.append("\n");
						break;
					case PENGUIN:
						str.append("you need " + wantedFood);
						str.append(" but you have only : " + currentFood);
						str.append(" food for penguibn");
						str.append(" please add " + (wantedFood.intValue() - currentFood) + " food for penguin");
						str.append("\n");
						break;

					}

				});

			}

		});

		IO.showMessageDialog(null, str);
	}

}
