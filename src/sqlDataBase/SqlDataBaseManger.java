package sqlDataBase;

import java.security.PublicKey;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import animals.Cervus;
import animals.Lemur;
import animals.Lion;
import animals.Penguin;
import animals.Tiger;
import characteristicsOfAnimals.Animal;
import characteristicsOfAnimals.CharacteristicOfAFish;
import characteristicsOfAnimals.Gender;
import characteristicsOfAnimals.FishColor.GoldFishColor;
import project_ZOO.ZooManger;

public class SqlDataBaseManger {

	private static SqlDataBaseManger myManger;
	private DataBase myDataBase;
	private SqlCreateQuenstion myCrator;
	private DelateAnimalsFromTable myDealtor;
	private UpdateAgeOfAnimals myUpdateAgeAnimals;
	private StartIdOfTheAnimals myStarterOfId;
	private UpdateHappinessOfAnimals myUpdateHappiness ;
	private boolean isReadFromSqlFile;
	private MoneyDataBase mangerMoney;
	private AnimalPrice animalprice;
	
	private static String url;
	private static String user;
	private static String password;

	private SqlDataBaseManger(DataBase myDataBase, SqlCreateQuenstion myCrator,
			DelateAnimalsFromTable delateAnimalsFromTable, UpdateAgeOfAnimals updateAgeOfAnimals,
			StartIdOfTheAnimals startIdOfTheAnimals, UpdateHappinessOfAnimals updateHappinessOfAnimals,MoneyDataBase mangerMoney,AnimalPrice animalprice  , String url, String user, String password) {
		this.myDataBase = myDataBase;
		this.myCrator = myCrator;
		this.myDealtor = delateAnimalsFromTable;
		this.myStarterOfId = startIdOfTheAnimals;
		this.myUpdateHappiness = updateHappinessOfAnimals;
		this.isReadFromSqlFile = false;
		this.myUpdateAgeAnimals = updateAgeOfAnimals;
		this.mangerMoney = mangerMoney; 
		this.animalprice = animalprice;
		this.url = url;
		this.user = user;
		this.password = password;

		this.myCrator.setTheFileLocation(url, user, password);
		this.myDataBase.setTheFileLocation(url, user, password);
		this.myDealtor.setTheFileLocation(url, user, password);
		this.myUpdateAgeAnimals.setTheFileLocation(url, user, password);
		this.myStarterOfId.setTheFileLocation(url, user, password);
		this.myUpdateHappiness.setTheFileLocation(url, user, password);
		this.mangerMoney.setTheFileLocation(url, user, password);
		this.animalprice.setTheFileLocation(url, user, password);
		
		
	}

	public static synchronized SqlDataBaseManger getTheManger(DataBase myDataBase, SqlCreateQuenstion myCrator,
			DelateAnimalsFromTable delateAnimalsFromTable, UpdateAgeOfAnimals updateAgeOfAnimals,
			StartIdOfTheAnimals startIdOfTheAnimals, UpdateHappinessOfAnimals updateHappinessOfAnimals  ,  MoneyDataBase mangerMoney,AnimalPrice animalprice, String url, String user, String password) {
		if (myManger == null)
			myManger = new SqlDataBaseManger(myDataBase, myCrator, delateAnimalsFromTable, updateAgeOfAnimals,
					startIdOfTheAnimals, updateHappinessOfAnimals,mangerMoney,animalprice ,  url, user, password);
		return myManger;
	}

	// .................. read from file ............//
	public void readFromSqlFile(ZooManger zooMamger) {
		if (!isReadFromSqlFile) {
			if (!myDataBase.readFromSqlFile(zooMamger))
				myStarterOfId.startIdOfAnimals(1000);
			}
			isReadFromSqlFile = true;
		}
	// .................. read from file ............//

	// .............................. crate new animals in table ............ //

	public int createAquariumFish(int age, double fishLength, ArrayList<Object> hisColor,
			CharacteristicOfAFish hisTrait) throws SQLException {
		return myCrator.createAquariumFish(age, fishLength, hisColor, hisTrait);

	}

	public int createGoldFish(int age, double fishLength, GoldFishColor fishColor) throws SQLException {

		return myCrator.createGoldFish(age, fishLength, fishColor);

	}

	public int createClownFish(int age, double fishLength) throws SQLException {
		return myCrator.createClownFish(age, fishLength);
	}

	public int createCervus(String name, Gender genderCervus, int age, double lengthOfTheRays) throws SQLException {

		return myCrator.createCervus(name, genderCervus, age, lengthOfTheRays);

	}

	public int createLion(String name, Gender gender, int age, float weight) throws SQLException {
		return myCrator.createLion(name, gender, age, weight);

	}

	public int createTiger(String name, Gender gender, int age, float weight) throws SQLException {
		return myCrator.createTiger(name, gender, age, weight);

	}

	public int createLemur(String name, int age, float height) throws SQLException {
		return myCrator.createLemur(name, age, height);
	}

	public int createPenguin(String name, int age, float height) throws SQLException {
		return myCrator.createPenguin(name, age, height);
	}

	// .............................. crate new animals in table ............ //

	
	//.....................update happiness......................//
	public void updateAnimalHappiness(int animalid, int newHappiness) throws SQLException {
		myUpdateHappiness.updateAnimalHappiness(animalid, newHappiness);
	}
	//.....................update happiness......................//

	
	// .........................delate animals.................//
	public void deleteAnimalById(int animalid) throws SQLException {
		myDealtor.deleteAnimalById(animalid);
	}
	
	public void deleteAllAnimals() throws SQLException {
		myDealtor.deleteAllAnimal();
		myStarterOfId.startIdOfAnimals(1000);
	}

	// .........................delate animals.................//

	// ......................set new age for animals..........//
	public void updateAnimalAge(int animalid, int newAge) throws SQLException {
		myUpdateAgeAnimals.updateAnimalAge(animalid, newAge);
	}
	// ......................set new age for animals..........//
	
	
	// ......................set new money..........//
	public int GetMoney() {
		return mangerMoney.GetMoney();
	}
	public void FirstMakeMoney() {
		mangerMoney.FirstMakeMoney();
	}
	public void UpdateMoney(int priceChange) {
		mangerMoney.UpdateMoney(priceChange);
	}
	public void ResetMoney() {
		mangerMoney.ResetMoney();
	}
	
	// ......................set new money..........//
	
	// ......................set new animal price..........//
	public void firstSetAnimalPrice() {
		animalprice.firstSetAnimalPrice();
	}
	
	 public int getPrice(String name) {
		 return animalprice.getPrice(name);
	 }
	 public void SetAnimalPrice(String name, int price) {
		 animalprice.SetAnimalPrice(name, price);
	 }
	
	
	// ......................set new animal price..........//

	
}
