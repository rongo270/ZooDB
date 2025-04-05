package animals;

import java.util.ArrayList;

import characteristicsOfAnimals.CharacteristicOfAFish;
import characteristicsOfAnimals.FishColor;
import characteristicsOfAnimals.FishColor.GoldFishColor;

public class GoldFish extends Fish {

	public static final double MIN_LENGTH_GOLD = 0.1;
	public static final double MAX_LENGTH_GOLD = 6;
	public static final int LifeSpan = 12;

	private GoldFishColor fishColor ; 
	
	
	public GoldFish(int fishId , int ageOfFish ,double fishLenth, GoldFishColor fishColor ) {
		this.hisTrait = CharacteristicOfAFish.Smooth;
		setFishLength(fishLenth);
		this.animalId = fishId;
		this.age = ageOfFish;
		setFishColor(fishColor);
		this.fishColor = fishColor;
 
	}
	
	public GoldFish() {
		this.hisTrait = CharacteristicOfAFish.Smooth;
	}
	
	

	@Override
	public void setFishLength(double fishLength) {

		super.setFishLength(fishLength);
	}

	@Override
	public void setAge(int ageOfFish) {
		super.setAge(ageOfFish);
	}

	public void setFishColor(GoldFishColor fishColor) {
		this.fishColor = fishColor;
		super.setFishColor(fishColor);
	}
	
	public GoldFishColor getGoldFishColor() {
		return this.fishColor;
	}


	public ArrayList<Object> getFishColor() {
		return super.getFishColor();
	}

	public int getFishColorLength() {
		return super.getFishColorLength();
	}

	@Override
	public int getAge() {
		return super.getAge();
	}

	@Override
	public CharacteristicOfAFish getFishTrait() {
		// TODO Auto-generated method stub
		return super.getFishTrait();
	}

	@Override
	public String toString() {
		return "< Gold fish > : " + super.toString() + "\n";
	}

	@Override
	public Number feed() {

		return (double) 1;
	}

}
