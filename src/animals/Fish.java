package animals;

import java.text.DecimalFormat;
import java.util.ArrayList;

import characteristicsOfAnimals.Animal;
import characteristicsOfAnimals.CharacteristicOfAFish;
import characteristicsOfAnimals.FishColor.ClownFishColor;
import characteristicsOfAnimals.FishColor.GoldFishColor;

public abstract class Fish extends Animal {
	private ArrayList<Object> hisColor = new ArrayList<>();
	protected double fishLength;
	protected CharacteristicOfAFish hisTrait;
	private double foodEvryDay;

	public Fish() {
		super();
	}

	public ArrayList<Object> getFishColor() {

		return hisColor;
	}

	public <E extends Enum<E>> void setFishColor(E hisColor) {
		this.hisColor.add(hisColor);
	}

	public int getFishColorLength() {
		return this.hisColor.size();
	}

	public void setFishTrait(CharacteristicOfAFish hisTrait) {
		this.hisTrait = hisTrait;
	}

	public CharacteristicOfAFish getFishTrait() {
		return this.hisTrait;
	}

	public double getFoodEvryDay() {
		return foodEvryDay;
	}

	public void setFishLength(double fishLength) {
		this.fishLength = Double.parseDouble(new DecimalFormat("##.####").format(fishLength));
	}

	public double getFishLength() {
		return fishLength;
	}

	public double feedFish(Fish newFish) {
		if (newFish.age < 3)
			return 3;
		foodEvryDay = newFish.fishLength + 3;
		return foodEvryDay;
	}

	@Override
	public String toString() { 
		StringBuffer str = new StringBuffer();
		str.append("fish id is : :"+this.animalId );
		str.append("|age of fish is " + age);
		str.append("| length is : " + fishLength);
		str.append("| the trait is : " + hisTrait);
		str.append(toStringColorFish());
		str.append("| the happiness is : " + super.happiness);

		return str.toString();
	}

	public String toStringColorFish() {
		StringBuffer str = new StringBuffer();
		if (this.hisColor.size() == 1) {
			str.append("|  color of  fish : ");
			str.append(hisColor.get(0));
			return str.toString();
		}

		str.append("| colors of fish : ");
		for (int i = 0; i < hisColor.size(); i++) {
			str.append(hisColor.get(i));
			if (i != hisColor.size() - 1)
				str.append(" ,");
		}

		return str.toString();
	}

	public String makeNoise() {
		return "blob";
	}

}
