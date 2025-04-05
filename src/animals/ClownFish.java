package animals;

import java.util.ArrayList;

import characteristicsOfAnimals.CharacteristicOfAFish;
import characteristicsOfAnimals.FishColor;
import characteristicsOfAnimals.FishColor.ClownFishColor;

public class ClownFish extends Fish {

	public static final double MIN_LENGTH_CLOWN = 0.2;
	public static final double MAX_LENGTH_CLOWN = 10;
	public static final int LifeSpan = 8;

	

	public ClownFish(int fishId , int ageOfFiah , double fishLength) {
		this.animalId = fishId;
		setAge(ageOfFiah);
		this.fishLength = fishLength;
		this.hisTrait = CharacteristicOfAFish.Stripes;
		for (FishColor.ClownFishColor color : FishColor.ClownFishColor.values()) 
		 setFishColor(color);
		

		//setFishColor();
	}

	public ClownFish() { 
		this.hisTrait = CharacteristicOfAFish.Stripes;
	}

	@Override
	public int getAge() {
		// TODO Auto-generated method stub
		return super.getAge();
	}

	@Override
	public void setAge(int ageOfFish) {
		// TODO Auto-generated method stub
		super.setAge(ageOfFish);
	}

	public ArrayList<Object> getClownFishColor() {
		return super.getFishColor();
	}

	public void setFishColor(ClownFishColor hisColor) {
		super.setFishColor(hisColor);

	}

	@Override
	public int getFishColorLength() {
		// TODO Auto-generated method stub
		return super.getFishColorLength();
	}

	@Override
	public CharacteristicOfAFish getFishTrait() {
		// TODO Auto-generated method stub
		return super.getFishTrait();
	}

	@Override
	public void setFishLength(double fishLength) {

		super.setFishLength(fishLength);
	}

	@Override
	public double getFishLength() {
		// TODO Auto-generated method stub
		return super.getFishLength();
	}

	@Override
	public String toString() {
		return "< Clown fish > : " + super.toString() + "\n";
	}

	@Override
	public Number feed() {

		return (double) 2;
	}

}
