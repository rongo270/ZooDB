package project_ZOO;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import animals.AnimalType;
import animals.AnimalType.AnimalTypeForPrice;
import animals.AquariumFish;
import animals.Carnivore;
import animals.Cervus;
import animals.ClownFish;
import animals.Fish;
import animals.GoldFish;
import animals.Lemur;
import animals.Lion;
import animals.Penguin;
import animals.Tiger;
import characteristicsOfAnimals.Animal;
import characteristicsOfAnimals.FishColor;
import characteristicsOfAnimals.FishColor.GoldFishColor;
import foodFoAnimals.FoodSupply;
import foodFoAnimals.FoodType;
import sortAnimals.SortAnimal;
import sortAnimals.SortAnimals;
import sqlDataBase.DataBase;
import sqlDataBase.MoneyDataBase;
import sqlDataBase.SqlCreateQuenstion;
import sqlDataBase.SqlDataBaseManger;
import sqlDataBase.AnimalPrice;

public class ZooManger<K> {
	private SortAnimals sort = new SortAnimals();

	private String zooName;
	private String zooAddress;

	private int money;
	private static String url;
	private static String user;
	private static String password;
	
	static final int PRICE_PER_KILO_OF_MEAT = 10;
	static final int PRICE_PER_1_FISH_FOR_PENGUIN = 2;
	static final int PRICE_PER_FISH_FOOD = 3;
	static final int PRICE_PER_1_MANGO = 15;
	static final int PRICE_PER_1_MIX_FRUITS = 15;

	
	public int price_buy_fish;
	public int price_buy_penguin;
	public int price_buy_tiger;
	public int price_buy_lion;
	public int price_buy_lemur;
	public int price_buy_cervus;
	
	

	private int supplayFoodForPenguin;
	private double supplayFoodForFish;
	private int supplayFoodForCarnivores;

	private SortAnimal alreadySorted = SortAnimal.HEIGHT;

	private ArrayList<Penguin> arrayListOfPengium = new ArrayList<Penguin>();
	private SortAnimal whatSortDidTheUserSelect = SortAnimal.HEIGHT;
	private int foodForPenguin = 0;

	private ArrayList<Lion> arrayListOfLion = new ArrayList<Lion>();
	private int foodForLions = 0;

	private ArrayList<Tiger> arrayListOfTigers = new ArrayList<Tiger>();
	private int foodForTigers = 0;

	private ArrayList<AquariumFish> arrayListOfAquariumFish = new ArrayList<AquariumFish>();
	private int counterHowManyColorsInZoo = 0;
	private double foodForFish = 0;
	private FishColor arrayOfFishColor[] = FishColor.values();
	private boolean allCoolorsFishInMyZoo[] = new boolean[arrayOfFishColor.length];
	private int[] TheColorsThatAppearMostOftenInFish = new int[arrayOfFishColor.length];
	private StringBuffer allColorsOfFish = new StringBuffer();

	private ArrayList<GoldFish> arrayListOfGoldFish = new ArrayList<GoldFish>();
	private double foodForGoldFish = 0;

	private ArrayList<ClownFish> arrayListOfClownFish = new ArrayList<ClownFish>();
	private double foodForClownFish = 0;

	private ArrayList<Lemur> arrayListOfLemurs = new ArrayList<Lemur>();;
	private int suplayForLemurs;
	private int foodForLemurs = 0;

	private ArrayList<Cervus> arrayListOfCervus = new ArrayList<Cervus>();;
	private int foodForCervus = 0;
	private int supplayForCervus;
	FoodForAnimalsManager myFoodManger;
	private HashMap<Integer, AnimalType> animalsTypeEnumById;

	private HashMap<Integer, Animal> allAnimalAsMap;

	private SqlDataBaseManger sqlDataBaseManger;

	// ...............................ZOO............................................//
	public ZooManger(String zooName, String zooAddress, SqlDataBaseManger sqlDataBaseManger,
			FoodForAnimalsManager foodManger, String url, String user, String password) {
		
		this.url = url;
		this.user = user;
		this.password = password;
		
		
		this.animalsTypeEnumById = new HashMap<Integer, AnimalType>();
		allAnimalAsMap = new HashMap<Integer, Animal>();
		this.zooName = zooName;
		this.zooAddress = zooAddress;
		this.sqlDataBaseManger = sqlDataBaseManger;
		this.myFoodManger = foodManger;
		this.money = sqlDataBaseManger.GetMoney();
		allColorsOfFish.append("\nAll the colors of the fish in your zoo are :\n\n");

		supplayFoodForFish = 50;
		supplayFoodForPenguin = 50;
		supplayFoodForCarnivores = 50;
		suplayForLemurs = 50;
		supplayForCervus = 50;
		
		
		price_buy_fish = getAnimalPrice("FISH");
		price_buy_penguin = getAnimalPrice("PENGUIN");
		price_buy_tiger = getAnimalPrice("TIGER");
		price_buy_lion = getAnimalPrice("LION");
		price_buy_lemur = getAnimalPrice("LEMUR");
		price_buy_cervus = getAnimalPrice("CERVUS");
		

	}

	public String getZooName() {
		return zooName;

	}

	public String getZooAddress() {
		return zooAddress;
	}
	
	public void SetNewAnimalValueInProgram(AnimalTypeForPrice typeOfAnimal, int newPrice) {
        switch (typeOfAnimal) {
        case FISH:
        	this.price_buy_fish = newPrice;
        	break;
        case PENGUIN:
        	this.price_buy_penguin = newPrice;
        	break;
        case TIGER:
        	this.price_buy_tiger = newPrice;
            break;
        case LION:
        	this.price_buy_lion = newPrice;
            break;
        case LEMUR:
        	this.price_buy_lemur = newPrice;
            break;
        case CERVUS:
        	this.price_buy_cervus = newPrice;
            break;
        }
    }

	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("The name of the zoo is " + getZooName());
		str.append(" address is " + getZooAddress());
		str.append("\nThe number of animals in the whole zoo is: " + numberOfAllAnimals());
		str.append("\nThe number of penguins: " + arrayListOfPengium.size());
		str.append("\nThe number of lions: " + arrayListOfLion.size());
		str.append("\nThe number of tigers: " + arrayListOfTigers.size());
		str.append("\nThe number of fishs: " + arrayListOfAquariumFish.size());
		str.append("\nThe number of gold fish : " + arrayListOfGoldFish.size());
		str.append("\nThe number of clown fish : " + arrayListOfClownFish.size());
		str.append("\nThe number of lemurs :" + arrayListOfLemurs.size());
		str.append("\nThe number of cervus :" + arrayListOfCervus.size());

		return str.toString();

	}

	public void feedAllAnimalsInZoo() {
		updatingTheAmountOfFoodForAnimal(); // Updating the total amount of food for each type of animal
		double sumOfFishFood = foodForFish + foodForGoldFish + foodForClownFish;
		int sumOfCarnivores = (foodForLions + foodForTigers);
		boolean canBuy = myFoodManger.cheakIfCanFeedAllAnimalsInZoo(sumOfFishFood, sumOfCarnivores, foodForPenguin,
				foodForCervus, foodForLemurs);
		if (canBuy) {
			myFoodManger.feedAllAnimalsInZoo(sumOfFishFood, sumOfCarnivores, foodForPenguin, foodForCervus,
					foodForLemurs);
			increaseHappinessTo100();
		} else
			myFoodManger.getTheProblemInFood(sumOfFishFood, sumOfCarnivores, foodForPenguin, foodForCervus,
					foodForLemurs);

	}

	public void updatingTheAmountOfFoodForAnimal() {

		foodForPenguin = 0;
		foodForLions = 0;
		foodForTigers = 0;
		foodForFish = 0;
		foodForGoldFish = 0;
		foodForClownFish = 0;
		foodForLemurs = 0;
		foodForCervus = 0;

		if (!arrayListOfPengium.isEmpty())
			for (int i = 0; i < arrayListOfPengium.size(); i++)
				foodForPenguin += (int) arrayListOfPengium.get(i).feed();

		if (!arrayListOfLion.isEmpty())
			for (int i = 0; i < arrayListOfLion.size(); i++)
				foodForLions += (int) arrayListOfLion.get(i).feed();

		if (!arrayListOfTigers.isEmpty())
			for (int i = 0; i < arrayListOfTigers.size(); i++)
				foodForTigers += (int) arrayListOfTigers.get(i).feed();

		if (!arrayListOfAquariumFish.isEmpty())
			for (int i = 0; i < arrayListOfAquariumFish.size(); i++)
				foodForFish += Double
						.parseDouble(new DecimalFormat("##.##").format(arrayListOfAquariumFish.get(i).feed()));

		if (!arrayListOfGoldFish.isEmpty())
			for (int i = 0; i < arrayListOfGoldFish.size(); i++)
				foodForGoldFish += Double
						.parseDouble(new DecimalFormat("##.##").format(arrayListOfGoldFish.get(i).feed()));

		if (!arrayListOfClownFish.isEmpty())
			for (int i = 0; i < arrayListOfClownFish.size(); i++)
				foodForClownFish += Double
						.parseDouble(new DecimalFormat("##.##").format(arrayListOfClownFish.get(i).feed()));

		if (!arrayListOfLemurs.isEmpty())
			for (int i = 0; i < arrayListOfLemurs.size(); i++)
				foodForLemurs += (int) arrayListOfLemurs.get(i).feed();

		if (!arrayListOfCervus.isEmpty())
			for (int i = 0; i < arrayListOfCervus.size(); i++)
				foodForCervus += (int) arrayListOfCervus.get(i).feed();

	}

	private void increaseHappinessTo100() {
		HappinessIncrease100<Object> happiness100 = new HappinessIncrease100<Object>();
		happiness100.animalAgeOneYear(arrayListOfPengium);
		happiness100.animalAgeOneYear(arrayListOfLion);
		happiness100.animalAgeOneYear(arrayListOfTigers);

		happiness100.animalAgeOneYear(arrayListOfAquariumFish);
		happiness100.animalAgeOneYear(arrayListOfGoldFish);
		happiness100.animalAgeOneYear(arrayListOfClownFish);

		happiness100.animalAgeOneYear(arrayListOfLemurs);
		happiness100.animalAgeOneYear(arrayListOfCervus);

	}

	public String allMakeNoise() {
		StringBuffer allMakeNoiseAllAnimal = new StringBuffer();
		allMakeNoiseAllAnimal.append(allMakeNoiseTypeAnimal(arrayListOfPengium));
		allMakeNoiseAllAnimal.append(allMakeNoiseTypeAnimal(arrayListOfLion));
		allMakeNoiseAllAnimal.append(allMakeNoiseTypeAnimal(arrayListOfTigers));

		allMakeNoiseAllAnimal.append(allMakeNoiseTypeAnimal(arrayListOfAquariumFish));
		allMakeNoiseAllAnimal.append(allMakeNoiseTypeAnimal(arrayListOfGoldFish));
		allMakeNoiseAllAnimal.append(allMakeNoiseTypeAnimal(arrayListOfClownFish));

		allMakeNoiseAllAnimal.append(allMakeNoiseTypeAnimal(arrayListOfLemurs));
		allMakeNoiseAllAnimal.append(allMakeNoiseTypeAnimal(arrayListOfCervus));

		if (allMakeNoiseAllAnimal.isEmpty())
			allMakeNoiseAllAnimal.append("EROR: There are no animals in the zoo\n");

		return allMakeNoiseAllAnimal.toString();

	}

	public <T extends Animal> String allMakeNoiseTypeAnimal(ArrayList<T> animals) {
		StringBuffer allMakeNoiseTypeAnimal = new StringBuffer();

		for (int i = 0; i < animals.size(); i++) {
			allMakeNoiseTypeAnimal.append(animals.get(i).makeNoise() + " ");
		}
		return allMakeNoiseTypeAnimal.toString();
	}

	public int numberOfAllAnimals() {

		return arrayListOfPengium.size() + arrayListOfLion.size() + arrayListOfTigers.size()
				+ arrayListOfAquariumFish.size() + arrayListOfGoldFish.size() + arrayListOfClownFish.size()
				+ arrayListOfLemurs.size() + arrayListOfCervus.size();
	}

	

	public String getHowManyFoodYouCanBuy() {
		StringBuffer str = new StringBuffer();
		str.append("with your monet [" + getMoney() + "$]");
		str.append("\nyou can buy in max " + (getMoney() / PRICE_PER_1_FISH_FOR_PENGUIN));
		str.append(" unit of food for pengiun");
		str.append("\nor you can buy in max " + (getMoney() / PRICE_PER_FISH_FOOD));
		str.append(" unit food for fish");
		str.append("\nor you can buy in max  " + (getMoney() / PRICE_PER_KILO_OF_MEAT));
		str.append(" kg of meat for lions/tigers");
		str.append("\nor you can buy in max  " + (getMoney() / PRICE_PER_1_MANGO));
		str.append(" kg of mango for lemur");
		str.append("\nor you can buy in max  " + (getMoney() / PRICE_PER_1_MIX_FRUITS));
		str.append(" kg of mango for cervus\n");

		str.append("the optimal is :");
		int sum = PRICE_PER_1_FISH_FOR_PENGUIN + PRICE_PER_FISH_FOOD + PRICE_PER_KILO_OF_MEAT + PRICE_PER_1_MANGO
				+ PRICE_PER_1_MIX_FRUITS;
		str.append(getMoney() / sum + " unit from each\n\n");
		return str.toString();
	}

	public String getSupplayInZoo() {
		return myFoodManger.getSupplayInZoo();
	}

	public void printSupplayInZoo() {
		myFoodManger.printSupplayInZoo();
	}

	public <T extends Animal> String ageOneYear() throws SQLException {
		StringBuffer str = new StringBuffer();
		int countRemovAnimals = 0;
		AnimalAgeOneYear<?> temp = new AnimalAgeOneYear();
		ZooManger manger = this;

		if (numberOfAllAnimals() == 0) {
			str.append("There are no animals in the zoo");
			return str.toString();
		}

		if (arrayListOfPengium.size() != 0)
			str.append(temp.animalAgeOneYear(arrayListOfPengium, manger, sqlDataBaseManger));
		if (arrayListOfLion.size() != 0)
			str.append(temp.animalAgeOneYear(arrayListOfLion, manger, sqlDataBaseManger));
		if (arrayListOfTigers.size() != 0)
			str.append(temp.animalAgeOneYear(arrayListOfTigers, manger, sqlDataBaseManger));

		if (arrayListOfAquariumFish.size() != 0)
			str.append(temp.animalAgeOneYear(arrayListOfAquariumFish, manger, sqlDataBaseManger));
		if (arrayListOfGoldFish.size() != 0)
			str.append(temp.animalAgeOneYear(arrayListOfGoldFish, manger, sqlDataBaseManger));
		if (arrayListOfClownFish.size() != 0)
			str.append(temp.animalAgeOneYear(arrayListOfClownFish, manger, sqlDataBaseManger));

		if (arrayListOfLemurs.size() != 0)
			str.append(temp.animalAgeOneYear(arrayListOfLemurs, manger, sqlDataBaseManger));
		if (arrayListOfCervus.size() != 0)
			str.append(temp.animalAgeOneYear(arrayListOfCervus, manger, sqlDataBaseManger));

		if (str.isEmpty())
			str.append("The operation was performed\n");

		for (int i = 0; i < 3; i++) // -->> in our project "passed" 3 month
			AddingMoneyToTheZooEveryMonth();

		return str.toString();

	}

	public <A extends Animal> String showingAllTypeAnimalInZoo(ArrayList<A> arrayList, String stringTypeAnimal) {
		StringBuffer str = new StringBuffer();

		str.append("\nThe number of " + stringTypeAnimal + "s in the zoo is: " + arrayList.size() + "\n");

		for (int i = 0; i < arrayList.size(); i++)
			str.append("#" + (i + 1) + " " + arrayList.get(i));

		return str.toString();
	}

	// ..................................sorting..........................................//

	public String chooseSortAnimalsArrayList(int choose) {
		StringBuffer str = new StringBuffer();

		if (choose == 1) {
			whatSortDidTheUserSelect = SortAnimal.HEIGHT;
			arrayListOfPengium = sort.sortAnmimalsByHeightOrAgeInMergeSort(arrayListOfPengium, SortAnimal.HEIGHT);
			arrayListOfLemurs = sort.sortAnmimalsByHeightOrAgeInMergeSort(arrayListOfLemurs, SortAnimal.HEIGHT);
			arrayListOfCervus = sort.sortAnmimalsByHeightOrAgeInMergeSort(arrayListOfCervus, SortAnimal.HEIGHT);
			str.append("now the animals sort by height");

		} else if (choose == 2) {
			whatSortDidTheUserSelect = SortAnimal.ABC;
			sort.sortAnimalsByABC(arrayListOfPengium);
			sort.sortAnimalsByABC(arrayListOfLemurs);
			sort.sortAnimalsByABC(arrayListOfCervus);
			str.append("now the animals sort by abc");

		}

		else if (choose == 3) {
			whatSortDidTheUserSelect = SortAnimal.AGE;
			arrayListOfPengium = sort.sortAnmimalsByHeightOrAgeInMergeSort(arrayListOfPengium, SortAnimal.AGE);
			arrayListOfLemurs = sort.sortAnmimalsByHeightOrAgeInMergeSort(arrayListOfLemurs, SortAnimal.AGE);
			arrayListOfCervus = sort.sortAnmimalsByHeightOrAgeInMergeSort(arrayListOfCervus, SortAnimal.AGE);
			str.append("now the animals sort by age");

		}
		return str.toString();

	}

	private <T extends Animal> void addAnimalToSystem(ArrayList<T> arrayListOfAnimals, T newAnimal) {
		// 2 of 3 methods work at O(log n)
		if (alreadySorted.equals(SortAnimal.HEIGHT)) {
			sort.addOneNewAnimalWhenAllOtherAnimalSortByHeightOrAge(arrayListOfAnimals, newAnimal,
					Comparator.comparing(Animal::getHeight));
			alreadySorted = SortAnimal.HEIGHT;

		} else if (alreadySorted.equals(SortAnimal.AGE)) {
			sort.addOneNewAnimalWhenAllOtherAnimalSortByHeightOrAge(arrayListOfAnimals, newAnimal,
					Comparator.comparing(Animal::getAge));
			alreadySorted = SortAnimal.AGE;
		} else {
			arrayListOfAnimals.add(newAnimal); // -> this only method work at O(n*log n)
			alreadySorted = SortAnimal.ABC;
			sort.sortAnimalsByABC(arrayListOfAnimals);
		}

	}

	// ......................................money...................................................//
	public void changeMoneyInDataBase(int price) {
		//sqlDataBaseManger.
		MoneyDataBase changeMoney = new MoneyDataBase();
		changeMoney.setTheFileLocation(url,user,password);
		changeMoney.UpdateMoney(price);
	}
	
	public int getAnimalPrice(String name) {
		AnimalPrice priceTable = new AnimalPrice();
		priceTable.setTheFileLocation(url, user, password);
		return priceTable.getPrice(name);
	}
	
	public boolean IsThereMoneyToBuyFoodForAnimals(int supplayFoodForFish, int supplayFoodForPenguin,
			int supplayFoodForCarnivores, int supplayForLemurs, int supplayForCervus) {
		double totalCost;
		totalCost = myFoodManger.IsThereMoneyToBuyFoodForAnimals(supplayFoodForFish, supplayFoodForPenguin,
				supplayFoodForCarnivores, supplayForLemurs, supplayForCervus);

		if (this.money >= totalCost) {
			this.money -= totalCost;
			changeMoneyInDataBase((int)-totalCost);
			myFoodManger.addFoodSupplayForZoo(supplayFoodForFish, supplayFoodForPenguin, supplayFoodForCarnivores,
					supplayForLemurs, supplayForCervus);
			
			return true;

		}
		return false;

	}

	public <T extends Animal, k> boolean addArrayListOfAnimalsFromDataBaseForFree(ArrayList<T> arrayListOfAnimals,
			Class<K> dataBaseClass) {

		if (!dataBaseClass.equals(DataBase.class))
			return false;
		for (int i = 0; i < arrayListOfAnimals.size(); i++) {
			T newAnimal = arrayListOfAnimals.get(i);
			allAnimalAsMap.put(newAnimal.getAnimalId(), newAnimal);

			setAllAnimalsToMapOfTypes(newAnimal.getAnimalId(), newAnimal);
			if (newAnimal instanceof Fish)

				addFishToSystem((Fish) newAnimal);

			else if (newAnimal instanceof Penguin)

				addPenguinToSystem((Penguin) newAnimal);

			else if (newAnimal instanceof Lion)

				addCarnivoreToSystem((Carnivore) newAnimal);

			else if (newAnimal instanceof Tiger)

				addCarnivoreToSystem((Carnivore) newAnimal);

			else if (newAnimal instanceof Lemur)

				addNewLemurToSystem((Lemur) newAnimal);

			else if (newAnimal instanceof Cervus)

				addNewCervusToSystem((Cervus) newAnimal);
		}

		return true;
	}

	public <T extends Animal> boolean CheckingIfThereIsMoneyToBuyAnimals(T newAnimal) {
		if (newAnimal instanceof Fish) {
			if (this.money >= price_buy_fish) {
				this.money -= price_buy_fish;
				changeMoneyInDataBase(-price_buy_fish);
				if (newAnimal.getAnimalId() <= 0)
					addIdForAnimals(newAnimal);
				allAnimalAsMap.put(newAnimal.getAnimalId(), newAnimal);
				setAllAnimalsToMapOfTypes(newAnimal.getAnimalId(), newAnimal);
				addFishToSystem((Fish) newAnimal);
				return true;
			}
		} else if (newAnimal instanceof Penguin) {
			if (this.money >= price_buy_penguin) {
				this.money -= price_buy_penguin;
				changeMoneyInDataBase(-price_buy_penguin);
				if (newAnimal.getAnimalId() <= 0)
					addIdForAnimals(newAnimal);
				allAnimalAsMap.put(newAnimal.getAnimalId(), newAnimal);

				setAllAnimalsToMapOfTypes(newAnimal.getAnimalId(), newAnimal);
				addPenguinToSystem((Penguin) newAnimal);
				return true;
			}
		} else if (newAnimal instanceof Lion) {
			if (this.money >= price_buy_lion) {
				this.money -= price_buy_lion;
				changeMoneyInDataBase(-price_buy_lion);
				if (newAnimal.getAnimalId() <= 0)
					addIdForAnimals(newAnimal);

				allAnimalAsMap.put(newAnimal.getAnimalId(), newAnimal);
				setAllAnimalsToMapOfTypes(newAnimal.getAnimalId(), newAnimal);
				addCarnivoreToSystem((Carnivore) newAnimal);
				return true;
			}
		} else if (newAnimal instanceof Tiger) {
			if (this.money >= price_buy_tiger) {
				this.money -= price_buy_tiger;
				changeMoneyInDataBase(-price_buy_tiger);
				if (newAnimal.getAnimalId() <= 0)
					addIdForAnimals(newAnimal);

				allAnimalAsMap.put(newAnimal.getAnimalId(), newAnimal);
				setAllAnimalsToMapOfTypes(newAnimal.getAnimalId(), newAnimal);
				addCarnivoreToSystem((Carnivore) newAnimal);
				return true;
			}
		} else if (newAnimal instanceof Lemur) {
			if (this.money >= price_buy_lemur) {
				this.money -= price_buy_lemur;
				changeMoneyInDataBase(-price_buy_lemur);
				if (newAnimal.getAnimalId() <= 0)
					addIdForAnimals(newAnimal);

				allAnimalAsMap.put(newAnimal.getAnimalId(), newAnimal);
				setAllAnimalsToMapOfTypes(newAnimal.getAnimalId(), newAnimal);
				addNewLemurToSystem((Lemur) newAnimal);
				return true;
			}
		} else if (newAnimal instanceof Cervus) {
			if (this.money >= price_buy_cervus) {
				this.money -= price_buy_cervus;
				changeMoneyInDataBase(-price_buy_cervus);
				if (newAnimal.getAnimalId() <= 0)
					addIdForAnimals(newAnimal);

				allAnimalAsMap.put(newAnimal.getAnimalId(), newAnimal);
				setAllAnimalsToMapOfTypes(newAnimal.getAnimalId(), newAnimal);
				addNewCervusToSystem((Cervus) newAnimal);
				return true;
			}
		}
		return false;
	}

	public int getMoney() {
		return this.money;
	}

	public String getMoneyWithMassage() {
		StringBuffer str = new StringBuffer();
		str.append("The money left in the zoo is : " + this.money + "$");
		return str.toString();
	}

	public void AddingMoneyToTheZooEveryMonth() {
		int counterFishTotal = arrayListOfAquariumFish.size() + arrayListOfClownFish.size()
				+ arrayListOfGoldFish.size();
		int incomeFromFish = (int) (0.4 * price_buy_fish * counterFishTotal);
		int incomeFromPenguins = (int) (0.6 * price_buy_penguin * arrayListOfPengium.size());
		int incomeFromTigers = (int) (0.7 * price_buy_tiger * arrayListOfTigers.size());
		int incomeFromLions = (int) (0.8 * price_buy_lion * arrayListOfLion.size());
		int incomeFromLemurs = (int) (3 * price_buy_lemur * arrayListOfLemurs.size()); // --> evry one love King Julian
		int incomeFromCervus = (int) (price_buy_cervus * arrayListOfCervus.size());

		this.money += incomeFromFish + incomeFromPenguins + incomeFromTigers + incomeFromLions + incomeFromLemurs
				+ incomeFromCervus;
		
		changeMoneyInDataBase(incomeFromFish + incomeFromPenguins + incomeFromTigers + incomeFromLions + incomeFromLemurs
				+ incomeFromCervus);

	}

	public String getPriceMenuForAnimalsOnly() {
		StringBuffer str = new StringBuffer();
		str.append("1.new fish = " + price_buy_fish + "$\n");
		str.append("2.new lion = " + price_buy_lion + "$\n");
		str.append("3.new tiger = " + price_buy_tiger + "$\n");
		str.append("4.new penguin = " + price_buy_penguin + "$\n");
		str.append("5.new lemur = " + price_buy_lemur + "$\n");
		str.append("6.new cervus = " + price_buy_cervus + "$\n");
		return str.toString();
	}
	
	public String getPriceMenuForEverythingYouCanBuy() {
		StringBuffer str = new StringBuffer();
		str.append("~~~~~~~~~~~~price menue~~~~~~~~~~~~~~~~~~\n\n");
		str.append("~~~~~~~~~~~~~~Food~~~~~~~~~~~~ :\n");
		str.append("1-kg of meat " + PRICE_PER_KILO_OF_MEAT + "$\n");
		str.append("1 food for fish " + PRICE_PER_FISH_FOOD + "$\n");
		str.append("1 food for penguins " + PRICE_PER_1_FISH_FOR_PENGUIN + "$\n");
		str.append("1 Mango for lemurs " + PRICE_PER_1_MANGO + "$\n");
		str.append("1 mix fruits for lemurs " + PRICE_PER_1_MIX_FRUITS + "$\n\n");

		str.append("~~~~~~~~~~~~~~anumals~~~~~~~~~~~~ :\n");
		str.append("new fish = " + price_buy_fish + "$\n");
		str.append("new lion = " + price_buy_lion + "$\n");
		str.append("new tiger = " + price_buy_tiger + "$\n");
		str.append("new penguin = " + price_buy_penguin + "$\n");
		str.append("new lemur = " + price_buy_lemur + "$\n");
		str.append("new cervus = " + price_buy_cervus + "$\n");

		return str.toString();
	}

	// ................................Penguin........................................//

	public void addPenguinToSystem(Penguin newPenguin) {
		addAnimalToSystem(arrayListOfPengium, newPenguin);
	}

	public float getLeaderHeadPenguin() {
		if (arrayListOfPengium.isEmpty()) {
			Penguin.heightLeaderPenguin = 0;
			return 0;
		}

		if (whatSortDidTheUserSelect.equals(SortAnimal.HEIGHT)) {
			Penguin.heightLeaderPenguin = arrayListOfPengium.get(0).getHeight();
			return Penguin.heightLeaderPenguin;

		}
		ArrayList<Penguin> leaderOfPEnguins = sort.sortAnmimalsByHeightOrAgeInMergeSort(arrayListOfPengium,
				SortAnimal.HEIGHT);
		Penguin.heightLeaderPenguin = leaderOfPEnguins.get(0).getHeight();

		return Penguin.heightLeaderPenguin;
	}

	public String showAllPenguinInZoo() {
		StringBuffer str = new StringBuffer();
		int index = 1;

		str.append("The number of penguins in the zoo is: " + arrayListOfPengium.size() + "\n");
		str.append("the sort is by :" + whatSortDidTheUserSelect + "\n");
		for (int i = 0; i < arrayListOfPengium.size(); i++) {
			str.append("Penguin #" + index + ": " + arrayListOfPengium.get(i).toString());
			index++;
		}

		return str.toString();
	}

	// ...............................Carnivore.....................................//

	public void addCarnivoreToSystem(Carnivore NewCarnivore) {

		SortAnimal temp = alreadySorted;

		if (NewCarnivore instanceof Lion) {
			// arrayListOfLion.add((Lion) NewCarnivore);
			alreadySorted = SortAnimal.AGE;
			addAnimalToSystem(arrayListOfLion, (Lion) NewCarnivore);
			alreadySorted = temp;
			// System.out.println(alreadySorted);

		} else {
			alreadySorted = SortAnimal.AGE;
			addAnimalToSystem(arrayListOfTigers, (Tiger) NewCarnivore);
			alreadySorted = temp;

		}
		// arrayListOfTigers.add((Tiger) NewCarnivore);
	}

	public String showAllCarnivoreZoo() {
		StringBuffer str = new StringBuffer();
		str.append("sort is by AGE");

		return str + showingAllTypeAnimalInZoo(arrayListOfLion, "Lion")
				+ showingAllTypeAnimalInZoo(arrayListOfTigers, "Tiger");
	}

	// .........................Fish......................................................//

	public void cheakAgainAllColorsInZooAfterDealeteFish() {
		for (int i = 0; i < allCoolorsFishInMyZoo.length; i++) {
			allCoolorsFishInMyZoo[i] = false; // -> in this func i "restart" all colors to 0 and then cheak the colors
												// after deleting the fish
			TheColorsThatAppearMostOftenInFish[i] = 0; // -> It's an array of int that in the future I count the 2
														// dominant colors in the fish
		}
		allColorsOfFish.setLength(0);
		counterHowManyColorsInZoo = 0;

		for (int i = 0; i < arrayListOfAquariumFish.size(); i++) {
			ChecksAllTheColorsOfTheFish(arrayListOfAquariumFish.get(i).getFishColor(),
					arrayListOfAquariumFish.get(i).getFishColorLength());

			the2ColorsThatAppearThMmostInFish(arrayListOfAquariumFish.get(i).getFishColor(),
					arrayListOfAquariumFish.get(i).getFishColorLength());
		}

		for (int i = 0; i < arrayListOfGoldFish.size(); i++) {
			ChecksAllTheColorsOfTheFish(arrayListOfGoldFish.get(i).getFishColor(),
					arrayListOfGoldFish.get(i).getFishColorLength());

			the2ColorsThatAppearThMmostInFish(arrayListOfGoldFish.get(i).getFishColor(),
					arrayListOfGoldFish.get(i).getFishColorLength());

		}

		for (int i = 0; i < arrayListOfClownFish.size(); i++) {
			ChecksAllTheColorsOfTheFish(arrayListOfClownFish.get(i).getClownFishColor(),
					arrayListOfClownFish.get(i).getFishColorLength());

			the2ColorsThatAppearThMmostInFish(arrayListOfClownFish.get(i).getClownFishColor(),
					arrayListOfClownFish.get(i).getFishColorLength());

		}

	}

	public <T extends Fish> void addFishToSystem(T newFish) {

		if (newFish instanceof GoldFish) {
			addNewGoldFishToSystem((GoldFish) newFish);
		} else if (newFish instanceof ClownFish) {
			addNewClownFishToSystem((ClownFish) newFish);
		} else if (newFish instanceof AquariumFish) {
			addNewAquariumFishToSystem((AquariumFish) newFish);
		}
	}

	private void addNewAquariumFishToSystem(AquariumFish newAquariumFish) {

		ChecksAllTheColorsOfTheFish(newAquariumFish.getFishColor(), newAquariumFish.getFishColorLength());
		the2ColorsThatAppearThMmostInFish(newAquariumFish.getFishColor(), newAquariumFish.getFishColorLength());
		arrayListOfAquariumFish.add(newAquariumFish);
	}

	private void addNewGoldFishToSystem(GoldFish newGoldFish) {

		ChecksAllTheColorsOfTheFish(newGoldFish.getFishColor(), newGoldFish.getFishColorLength());
		the2ColorsThatAppearThMmostInFish(newGoldFish.getFishColor(), newGoldFish.getFishColorLength());
		arrayListOfGoldFish.add(newGoldFish);

	}

	private void addNewClownFishToSystem(ClownFish newClownFish) {

		ChecksAllTheColorsOfTheFish(newClownFish.getClownFishColor(), newClownFish.getFishColorLength());
		the2ColorsThatAppearThMmostInFish(newClownFish.getClownFishColor(), newClownFish.getFishColorLength());
		arrayListOfClownFish.add(newClownFish);

	}

	private <T> void ChecksAllTheColorsOfTheFish(ArrayList<T> curretColor, int size) {
		if (counterHowManyColorsInZoo == allCoolorsFishInMyZoo.length)
			return;

		if (size == 0)
			return;

		for (int i = 0; i < allCoolorsFishInMyZoo.length; i++) {
			if (arrayOfFishColor[i].name() == curretColor.get(size - 1).toString() && !allCoolorsFishInMyZoo[i]) {
				counterHowManyColorsInZoo++;
				allCoolorsFishInMyZoo[i] = true;
				allColorsOfFish.append(curretColor.get(size - 1) + ", ");
			}
		}

		ChecksAllTheColorsOfTheFish(curretColor, size - 1);

	}

	private <T> void the2ColorsThatAppearThMmostInFish(ArrayList<T> curretColor, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < allCoolorsFishInMyZoo.length; j++) {

				if (arrayOfFishColor[j].name().equals(curretColor.get(i).toString()))
					TheColorsThatAppearMostOftenInFish[j] += 1;

			}
		}

	}

	private int select(int[] arrOfColorsOfFish, int index, int maxiumum) {
		for (int i = 0; i < arrOfColorsOfFish.length; i++) {
			if (arrOfColorsOfFish[i] > index) {
				if (arrOfColorsOfFish[i] < maxiumum)
					index = arrOfColorsOfFish[i];
			}

		}
		return index;
	}

	// Here I check a number of things
	// 1- What is the maximum number of shows in which the fish appeared
	// 2- The limit of the number of colors that I am allowed to check
	// 3- If the fish before it is null or if it is the same color as the fish
	// before it
	private String getTheColorOfTheTishThatAppearTheMostTimes(int index, int maximum, String previousColor) {
		FishColor color = null;
		for (int i = 0; i < TheColorsThatAppearMostOftenInFish.length; i++) {
			if (TheColorsThatAppearMostOftenInFish[i] > index) {
				if (TheColorsThatAppearMostOftenInFish[i] < maximum) {
					if (previousColor == null) {
						index = TheColorsThatAppearMostOftenInFish[i];
						color = arrayOfFishColor[i];
					} else if (!(previousColor.equals(arrayOfFishColor[i].toString().trim()))) {
						index = TheColorsThatAppearMostOftenInFish[i];
						color = arrayOfFishColor[i];
					}
				}
			}
		}
		if (color == null)
			return "";

		return color.toString();
	}

	public String getthe2ColorsThatAppearThMmostInFish() {
		StringBuffer str = new StringBuffer();
		int max1, max2;
		int maxIndex = TheColorsThatAppearMostOftenInFish.length - 1;
		max1 = select(TheColorsThatAppearMostOftenInFish, 0, maxIndex);
		if (max1 == 1)
			max2 = 1;
		else
			max2 = select(TheColorsThatAppearMostOftenInFish, 0, max1);
		String colorA = getTheColorOfTheTishThatAppearTheMostTimes(0, max1 + 1, null);
		String colorB = getTheColorOfTheTishThatAppearTheMostTimes(0, max2 + 1, colorA);
		if (colorB.isEmpty()) {
			str.append("The colors that appear most often in fish ");
			str.append(max1 + " " + colorA);
			return str.toString();
		}

		str.append("The colors that appear most often in fish ");
		str.append(max1 + " " + colorA + "  " + max2 + " " + colorB);
		return str.toString();
	}

	private String getAllColorsInMyZoo() {
		StringBuffer str = new StringBuffer();
		str.append("\n\nin total you have : " + this.counterHowManyColorsInZoo);
		str.append(" different colors of fish");
		return this.allColorsOfFish.toString() + str.toString();

	}

	public String showAllFishInZoo() {
		StringBuffer str = new StringBuffer();

		str.append(showingAllTypeAnimalInZoo(arrayListOfAquariumFish, "AquariumFish"));
		str.append(showingAllTypeAnimalInZoo(arrayListOfGoldFish, "GoldFish"));
		str.append(showingAllTypeAnimalInZoo(arrayListOfClownFish, "ClownFish"));

		return str.toString() + getAllColorsInMyZoo();

	}

//..........................................Lemurs........................................................//

	public void addNewLemurToSystem(Lemur newLemur) {

		addAnimalToSystem(arrayListOfLemurs, newLemur);
	}

	public String showingAllLemursInZoo() {
		StringBuffer str = new StringBuffer();
		str.append("the sort is by :" + whatSortDidTheUserSelect);
		return str.toString() + showingAllTypeAnimalInZoo(arrayListOfLemurs, "Lemur");
	}

	// ..................................................Cervus........................//
	public void addNewCervusToSystem(Cervus newCervus) {

		addAnimalToSystem(arrayListOfCervus, newCervus);
	}

	public String showingAllCervusInZoo() {
		StringBuffer str = new StringBuffer();
		str.append("the sort is by :" + whatSortDidTheUserSelect);
		return str.toString() + showingAllTypeAnimalInZoo(arrayListOfCervus, "Cervus");
	}

	// .................deleate all animals.....................................//

	public void deleteAllAnimals() {
		allAnimalAsMap.clear();
		arrayListOfAquariumFish.clear();
		arrayListOfCervus.clear();
		arrayListOfClownFish.clear();
		arrayListOfGoldFish.clear();
		arrayListOfLemurs.clear();
		arrayListOfLion.clear();
		arrayListOfPengium.clear();
		arrayListOfTigers.clear();

		deleteAllColorsOfFishInZoo();

	}

	private void deleteAllColorsOfFishInZoo() {
		counterHowManyColorsInZoo = 0;
		allColorsOfFish.setLength(0);
		;
	}

	// .................deleate all animals.....................................//

	// .................deleate one animal.....................................//

	public boolean doesAnimalWithUserIdExist(int animalId) {
		return allAnimalAsMap.containsKey(animalId);
	}

	public String removeAnimalById(int animalId) throws SQLException {

		AnimalType animalType = animalsTypeEnumById.get(animalId);
		StringBuffer animalToString = new StringBuffer();

		switch (animalType) {
		case LION:
			animalToString.append(removeAnimal(arrayListOfLion, animalId));
			break;
		case TIGER:
			animalToString.append(removeAnimal(arrayListOfTigers, animalId));
			break;
		case CERVUS:
			animalToString.append(removeAnimal(arrayListOfCervus, animalId));
			break;
		case LEMUR:
			animalToString.append(removeAnimal(arrayListOfLemurs, animalId));
			break;
		case PENGUIN:
			animalToString.append(removeAnimal(arrayListOfPengium, animalId));
			break;
		case AQUARIUMFISH:
			animalToString.append(removeAnimal(arrayListOfAquariumFish, animalId));
			cheakAgainAllColorsInZooAfterDealeteFish();
			break;
		case GOLDFISH:
			animalToString.append(removeAnimal(arrayListOfGoldFish, animalId));
			cheakAgainAllColorsInZooAfterDealeteFish();
			break;
		case CLOWNFISH:
			animalToString.append(removeAnimal(arrayListOfClownFish, animalId));
			cheakAgainAllColorsInZooAfterDealeteFish();
			break;
		}

		animalsTypeEnumById.remove(animalId);
		allAnimalAsMap.remove(animalId);
		sqlDataBaseManger.deleteAnimalById(animalId);
		return animalToString.toString();
	}

	private <T extends Animal> String removeAnimal(ArrayList<T> list, int animalId) {
		StringBuffer animalToString = new StringBuffer();
		for (T animal : list) {
			if (animal.getAnimalId() == animalId) {
				animalToString.append(animal.toString());
				list.remove(animal);
				break;
			}
		}
		return animalToString.toString();
	}
	// .................deleate one animal.....................................//

	// ...........string all animal in the zoo.........................//
	public String getAllStringFromAnimals() {
		ArrayList<Animal> allAnimalsAsArrayList = new ArrayList<Animal>();
		for (Map.Entry<Integer, Animal> entry : allAnimalAsMap.entrySet()) {
			Animal animal = entry.getValue();
			allAnimalsAsArrayList.add(animal);

		}
		allAnimalsAsArrayList.sort((o1, o2) -> Integer.compare(o1.getAnimalId(), o2.getAnimalId()));
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < allAnimalsAsArrayList.size(); i++) {
			str.append(allAnimalsAsArrayList.get(i) + "\n");
		}
		return str.toString();
	}

	// ...........string all animal in the zoo.........................//

	// ..................... get max id in system................................//
	public int getMaxIdInZoo() {
		int maxId = -1;
		for (Map.Entry<Integer, AnimalType> entry : animalsTypeEnumById.entrySet()) {
			Integer id = entry.getKey();
			if (id > maxId)
				maxId = id;

		}
		return maxId;
	}
	// ..................... get max id in system................................//

	// ....................... get next id to animal id .......................//

	public int getNextIdOfExsistingAnimal(int id) {
		int closerId = id;
		int minDifference = Integer.MAX_VALUE;
		for (Map.Entry<Integer, Animal> entry : allAnimalAsMap.entrySet()) {
			Integer animalId = entry.getKey();
			int difference = Math.abs(id - animalId);
			if (difference < minDifference) {
				minDifference = difference;
				closerId = animalId;
			}
		}
		return closerId;
	}
	// ....................... get next id to animal id .......................//

	// ............add all animals to hashMap.....................................//
	private void setAllAnimalsToMapOfTypes(int animalId, Animal newAnimal) {
		if (newAnimal instanceof Lion)
			animalsTypeEnumById.put(animalId, AnimalType.LION);

		else if (newAnimal instanceof Tiger)
			animalsTypeEnumById.put(animalId, AnimalType.TIGER);

		else if (newAnimal instanceof Cervus)
			animalsTypeEnumById.put(animalId, AnimalType.CERVUS);

		else if (newAnimal instanceof Lemur)
			animalsTypeEnumById.put(animalId, AnimalType.LEMUR);

		else if (newAnimal instanceof Penguin)
			animalsTypeEnumById.put(animalId, AnimalType.PENGUIN);

		else if (newAnimal instanceof AquariumFish)
			animalsTypeEnumById.put(animalId, AnimalType.AQUARIUMFISH);

		else if (newAnimal instanceof GoldFish)
			animalsTypeEnumById.put(animalId, AnimalType.GOLDFISH);

		else if (newAnimal instanceof ClownFish)
			animalsTypeEnumById.put(animalId, AnimalType.CLOWNFISH);
	}

	// ............add all animals to hashMap.....................................//

	// ...............................................SQL.....................................................//

	private void addIdForAnimals(Animal newAnimal) {
		int animalId = -1;

		try {
			if (newAnimal instanceof Lion) {
				Lion tempLion = (Lion) newAnimal;
				animalId = sqlDataBaseManger.createLion(tempLion.getName(), tempLion.getGenderCarnivore(),
						tempLion.getAge(), tempLion.getWeightCarnivore());
				newAnimal.setAnimalId(animalId, this.getClass());

			} else if (newAnimal instanceof Tiger) {
				Tiger tempTiger = (Tiger) newAnimal;
				animalId = sqlDataBaseManger.createTiger(tempTiger.getName(), tempTiger.getGenderCarnivore(),
						tempTiger.getAge(), tempTiger.getWeightCarnivore());
				newAnimal.setAnimalId(animalId, this.getClass());

			} else if (newAnimal instanceof Cervus) {
				Cervus tempCervus = (Cervus) newAnimal;
				animalId = sqlDataBaseManger.createCervus(tempCervus.getName(), tempCervus.getGenderCervus(),
						tempCervus.getAge(), tempCervus.getLengthOfTheRays());
				newAnimal.setAnimalId(animalId, this.getClass());

			} else if (newAnimal instanceof Lemur) {
				Lemur tempLemur = (Lemur) newAnimal;
				animalId = sqlDataBaseManger.createLemur(tempLemur.getName(), tempLemur.getAge(),
						tempLemur.getHeight());
				newAnimal.setAnimalId(animalId, this.getClass());

			} else if (newAnimal instanceof Penguin) {
				Penguin tempPenguin = (Penguin) newAnimal;
				animalId = sqlDataBaseManger.createPenguin(tempPenguin.getName(), tempPenguin.getAge(),
						tempPenguin.getHeight());
				newAnimal.setAnimalId(animalId, this.getClass());
			} else if (newAnimal instanceof AquariumFish) {
				AquariumFish tempAquariumFish = (AquariumFish) newAnimal;
				animalId = sqlDataBaseManger.createAquariumFish(tempAquariumFish.getAge(),
						tempAquariumFish.getFishLength(), tempAquariumFish.getFishColor(),
						tempAquariumFish.getFishTrait());
				newAnimal.setAnimalId(animalId, this.getClass());
			} else if (newAnimal instanceof GoldFish) {
				GoldFish tempGoldFish = (GoldFish) newAnimal;
				animalId = sqlDataBaseManger.createGoldFish(tempGoldFish.getAge(), tempGoldFish.getFishLength(),
						tempGoldFish.getGoldFishColor());
				newAnimal.setAnimalId(animalId, this.getClass());
			} else if (newAnimal instanceof ClownFish) {
				ClownFish tempClownFish = (ClownFish) newAnimal;
				animalId = sqlDataBaseManger.createClownFish(tempClownFish.getAge(), tempClownFish.getFishLength());
				newAnimal.setAnimalId(animalId, this.getClass());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
