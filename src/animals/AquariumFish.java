package animals;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import characteristicsOfAnimals.CharacteristicOfAFish;
import characteristicsOfAnimals.FishColor;

import java.text.NumberFormat;

public class AquariumFish extends Fish {

	public static final double MIN_LENGTH_FISH = 0.1;
	public static final double MAX_LENGTH_FISH = 15;
	public static final int LifeSpan = 25;

	//private ArrayList<Object> hisColor = new ArrayList<>();
	protected int counterColorsInFish = 0; // Index to several colors of the fish

	public AquariumFish() { 

	}
	
	
	
                                                                            // gold                        // no one
	public AquariumFish(int fishId, int ageOfFish, double fishLength, ArrayList<Object> hisColor, CharacteristicOfAFish hisTrait) { 	
		setAgeOfFish(ageOfFish);
		this.animalId = fishId;
		this.fishLength = fishLength;
		this.hisTrait = hisTrait;
		for (int i = 0; i < hisColor.size(); i++) 
			setFishColor((FishColor) hisColor.get(i));
	}

	public int getAgeOfFish() {
		return super.getAge();
	}

	public void setAgeOfFish(int ageOfFish) {
		super.setAge(ageOfFish);
	}

	public ArrayList<Object> getFishColor() {

		return super.getFishColor();
	}

	public void setFishColor(FishColor fishColor) {
		super.setFishColor(fishColor);
	}

	public int getFishColorLength() {
		return super.getFishColorLength();
	}

	@Override
	public CharacteristicOfAFish getFishTrait() {
		return super.getFishTrait();
	}

	@Override
	public void setFishTrait(CharacteristicOfAFish hisTrait) {
		super.setFishTrait(hisTrait);
	}

	public void setFishLength(double fishLength) {

		super.setFishLength(fishLength);
	}

	public double getFishLength() {
		return super.getFishLength();
	}

	@Override
	public String toString() {
		return "< Aquarium Fish > : " + super.toString() + "\n";
	}

	@Override
	public Number feed() {
		if (this.age < 3)
			return (double) 3;
		double foodEvryDay = this.fishLength + 3;
		return foodEvryDay;
	}

}
