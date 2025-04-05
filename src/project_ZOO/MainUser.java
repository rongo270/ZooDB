package project_ZOO;

import java.awt.HeadlessException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import animals.AnimalType;
import animals.AnimalType.AnimalTypeForPrice;
import animals.AquariumFish;
import animals.Carnivore;
import animals.Cervus;
import animals.ClownFish;
import animals.GoldFish;
import animals.Lemur;
import animals.Lion;
import animals.Penguin;
import animals.Tiger;
import characteristicsOfAnimals.CharacteristicOfAFish;
import characteristicsOfAnimals.FishColor;
import characteristicsOfAnimals.FishColor.GoldFishColor;
import characteristicsOfAnimals.Gender;
import exception.HandlingOfExceptions;
import exception.MyException;
import exception.MyExceptionAge;
import exception.MyExceptionForHeight;
import foodFoAnimals.CarnivoresFoodSupply;
import foodFoAnimals.CervusFoodSupply;
import foodFoAnimals.FishFoodSupply;
import foodFoAnimals.FoodSupply;
import foodFoAnimals.LemurFoodSupply;
import foodFoAnimals.PenguinFoodSupply;
import foodFoAnimals.PrinterSupplayInZoo;
import foodFoAnimals.SupplyManager;
import foodFoAnimals.SupplyManagerImpl;
import sqlDataBase.DataBase;
import sqlDataBase.DelateAnimalsFromTable;
import sqlDataBase.MoneyDataBase;
import sqlDataBase.SqlCreateQuenstion;
import sqlDataBase.SqlDataBaseManger;
import sqlDataBase.StartIdOfTheAnimals;
import sqlDataBase.UpdateAgeOfAnimals;
import sqlDataBase.UpdateHappinessOfAnimals;
import sqlDataBase.MoneyDataBase;
import sqlDataBase.AnimalPrice;

public class MainUser {
	static JOptionPane IO = new JOptionPane();

	public static void main(String[] args)
			throws MyException, MyExceptionForHeight, MyExceptionAge, HeadlessException, SQLException {

		DataBase dataBase = new DataBase();
		SqlCreateQuenstion SqlCreateQuenstion = new SqlCreateQuenstion();
		DelateAnimalsFromTable delateAnimalsFromTable = new DelateAnimalsFromTable();
		UpdateAgeOfAnimals updateAgeOfAnimals = new UpdateAgeOfAnimals();
		StartIdOfTheAnimals startIdOfTheAnimals = new StartIdOfTheAnimals();
		UpdateHappinessOfAnimals updateHappinessOfAnimals = new UpdateHappinessOfAnimals();
		MoneyDataBase mangerMoney = new MoneyDataBase();
		AnimalPrice prices = new AnimalPrice();
		

		String URL = "jdbc:postgresql://localhost:5432/postgres";
		String USER = "postgres";
		String PASSWORD = "0950";

		SqlDataBaseManger sqlDataBaseManger = SqlDataBaseManger.getTheManger(dataBase, SqlCreateQuenstion,
				delateAnimalsFromTable, updateAgeOfAnimals, startIdOfTheAnimals, updateHappinessOfAnimals,mangerMoney,prices,  URL, USER,
				PASSWORD);

		PrinterSupplayInZoo printerSupplayInZoo = new PrinterSupplayInZoo();
		StringBuffer inputOutputText = new StringBuffer();
		
		

		HashMap<Integer, FoodSupply> foodForAnimals = new HashMap<Integer, FoodSupply>();
		FishFoodSupply fishFood = new FishFoodSupply(1, 3, 50);
		PenguinFoodSupply penguinFood = new PenguinFoodSupply(2, 2, 50);
		CarnivoresFoodSupply carnivoresFood = new CarnivoresFoodSupply(3, 10, 50);
		LemurFoodSupply lemurFood = new LemurFoodSupply(4, 15, 50);
		CervusFoodSupply cervusFood = new CervusFoodSupply(5, 15, 50);

		foodForAnimals.put(penguinFood.getFoodAnimalId(), penguinFood);
		foodForAnimals.put(carnivoresFood.getFoodAnimalId(), carnivoresFood);
		foodForAnimals.put(lemurFood.getFoodAnimalId(), lemurFood);
		foodForAnimals.put(cervusFood.getFoodAnimalId(), cervusFood);

		SupplyManager supplyManger  = new SupplyManagerImpl(foodForAnimals, fishFood, printerSupplayInZoo);
		FoodForAnimalsManager foodManger = FoodForAnimalsManager.getTheManger(supplyManger);
		
		
		prices.firstSetAnimalPrice();
		

		ZooManger manger = new ZooManger("Ramat Gan Zoo", "Sderot Zvi 1 Ramat Gan", sqlDataBaseManger, foodManger,URL,USER,PASSWORD);
		userInputOutput userInpt = new userInputOutput(manger);
		HandlingOfExceptions exp = new HandlingOfExceptions();

		sqlDataBaseManger.readFromSqlFile(manger);

		Scanner scan = new Scanner(System.in);

		final long TIME_TO_WAIT_FOR_ADD_MONEY = 5 * 60 * 1000; // <- in real its 1 month in our project its 5 minutes
		long lastActionTime = System.currentTimeMillis();

		boolean selectSortForPenguinsAtFirst = false;

		/*
		 * SqlCreateQuenstion sqlCreateQuenstion = new SqlCreateQuenstion();
		 * 
		 * Tiger test = new Tiger(-1, "my tiger ",Gender.MALE, 10 , (float) 40.1);
		 * 
		 * try { sqlCreateQuenstion.createTiger(test.getName() ,
		 * test.getGenderCarnivore() , test.getAge() , test.getWeightCarnivore());
		 * }catch (SQLException e) { e.printStackTrace(); }
		 * 
		 */

		// DataBaseNew.main(args, manger);

		int choose = 0;
		while (choose != -1) {

			// .......A function that automatically adds money to the zoo..............//

			long currentTime = System.currentTimeMillis();
			if (currentTime - lastActionTime >= TIME_TO_WAIT_FOR_ADD_MONEY) {
				inputOutputText.setLength(0);

				manger.AddingMoneyToTheZooEveryMonth();
				inputOutputText.append("Payday has arrived: now you have " + manger.getMoney() + "$\n");
				IO.showMessageDialog(null, inputOutputText);
				lastActionTime = currentTime;
			}
			// .......A function that automatically adds money to the zoo..............//

			choose = chooseMenu(scan);
			inputOutputText.setLength(0);
			switch (choose) {
			case 1:
				IO.showMessageDialog(null, manger.toString());
				break;
			case 2:
				userInpt.addOfPenguin();
				break;
			case 3:
				inputOutputText.setLength(0);
				inputOutputText.append("prees 1 to sort by HEIGHT \n");
				inputOutputText.append("prees 2 to sort by ABC\n");
				inputOutputText.append("prees 3 to sort by AGE");
				int chooseSort = exp.tryCatchFuncNumbers(1, 3, inputOutputText.toString());
				IO.showMessageDialog(null, manger.chooseSortAnimalsArrayList(chooseSort));
				selectSortForPenguinsAtFirst = true;
				break;
			case 4:
				if (!selectSortForPenguinsAtFirst)
					manger.chooseSortAnimalsArrayList(1);
				IO.showMessageDialog(null, manger.showAllPenguinInZoo());
				break;
			case 5:
				IO.showMessageDialog(null, manger.showAllCarnivoreZoo());

				break;

			case 6:
				userInpt.SelectingTheCarnivoreTheUserWants();
				break;
			case 7:
				IO.showMessageDialog(null, manger.showAllFishInZoo());
				break;

			case 8:
				inputOutputText.append("Press 1 to add fish randomly\n");
				inputOutputText.append("Press 2 to add fish manually\n");
				choose = exp.tryCatchFuncNumbers(1, 2, inputOutputText.toString());

				if (choose == 1) {
					inputOutputText.setLength(0);
					inputOutputText.append("How many fish do you want to add to the zoo?");
					int numberOfFish = exp.tryCatchFuncNumbers(0, inputOutputText.toString());
					userInpt.addRandomFishToZoo(numberOfFish, true);
				} else
					userInpt.SelectingTheFishTheUserWants();
				break;

			case 9:
				IO.showMessageDialog(null, manger.showingAllLemursInZoo());
				break;

			case 10:
				userInpt.addLenurToTheZoo();
				break;

			case 11:
				IO.showMessageDialog(null, manger.showingAllCervusInZoo());
				break;

			case 12:
				userInpt.addCervusToTheZoo();
				break;

			case 13:
				inputOutputText.append("press 1 to feed all animals in the zoo\n");
				inputOutputText.append("press 2 to see your supplay in your zoo\n");
				inputOutputText.append("press 3 to add supplay for the zoo\n");
				inputOutputText.append("press 4 to see price menue\n");
				choose = exp.tryCatchFuncNumbers(1, 4, inputOutputText.toString());
				inputOutputText.setLength(0);
				if (choose == 1) {
					manger.feedAllAnimalsInZoo();
					IO.showMessageDialog(null, inputOutputText);
				} else if (choose == 2) {
					manger.printSupplayInZoo();
				} else if (choose == 3)
					userInpt.addSupplayForZoo();
				else if (choose == 4) {
					inputOutputText.append(manger.getPriceMenuForEverythingYouCanBuy());
					IO.showMessageDialog(null, inputOutputText);

				}

				break;
			case 14:
				inputOutputText.append(manger.allMakeNoise());
				IO.showMessageDialog(null, inputOutputText);
				break;

			case 15:
				inputOutputText.append(manger.getthe2ColorsThatAppearThMmostInFish());
				IO.showMessageDialog(null, inputOutputText);
				break;

			case 16:
				IO.showMessageDialog(null, manger.ageOneYear());

				break;
			case 17:
				inputOutputText.append(manger.getMoneyWithMassage());
				IO.showMessageDialog(null, inputOutputText);

				break;
			case 18:
				inputOutputText.append(manger.getPriceMenuForEverythingYouCanBuy());
				IO.showMessageDialog(null, inputOutputText);
				break;

			case 19:
				inputOutputText.setLength(0);
				inputOutputText.append("enter animal id  betwen [ 1000 , " + manger.getMaxIdInZoo() + " ]");
				int animalId = exp.tryCatchFuncNumbers(1000, manger.getMaxIdInZoo(), inputOutputText.toString());
				boolean exsist = manger.doesAnimalWithUserIdExist(animalId);
				if (exsist) {
					String animalRevomedToString = manger.removeAnimalById(animalId);
					IO.showMessageDialog(null, "Removing animal: " + animalRevomedToString);
				} else {
					StringBuffer str = new StringBuffer();
					str.append(" this id dosnt exsist in the system closer id in the system is :"
							+ manger.getNextIdOfExsistingAnimal(animalId));
					IO.showMessageDialog(null, str);
				}

				break;

			case 20:
				IO.showMessageDialog(null, "All the animals in the zoo have been deleted");
				sqlDataBaseManger.deleteAllAnimals();
				manger.deleteAllAnimals();
				break;

			case 21:
				IO.showMessageDialog(null, manger.getAllStringFromAnimals());
				break;
			case 22:
				AnimalTypeForPrice[] animals = AnimalTypeForPrice.values();
				for(int i = 0; i<animals.length;i++) {
				    inputOutputText.append("press " + (i+1) + " to update " + animals[i] +" price\n");

				}
			    choose = exp.tryCatchFuncNumbers(1, animals.length, inputOutputText.toString());

			        // Open a new window asking the user to enter the new price
			        String priceInput = JOptionPane.showInputDialog(null, "Enter the new price for " + animals[choose - 1]);

			        // Convert the input price to an integer
			        try {
			            int newPrice = Integer.parseInt(priceInput);
			            
			            // Check if the price is within the valid range (1-500)
			            if (newPrice >= 1 && newPrice <= 500) {
			                sqlDataBaseManger.SetAnimalPrice(animals[choose - 1].toString(), newPrice);
			                manger.SetNewAnimalValueInProgram(animals[choose-1], newPrice);
			            } else {
			                JOptionPane.showMessageDialog(null, "Price must be between 1 and 500.");
			            }
			        } catch (NumberFormatException e) {
			            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
			        }

			    break;

			case -1:
				break;

			default:
				IO.showMessageDialog(null, "Error, select one of the menu options again");

			}

		}
		//IO.showMessageDialog(null, "\nBye Bye");

	}

	static int chooseMenu(Scanner scan) {
		boolean finished = false;
		int choose = 0;
		StringBuffer str = new StringBuffer();
		str.append("Enter one of the menu options: \n");
		str.append("1) Displaying zoo details\n");
		str.append("2) Adding a new penguin\n"); // <-
		str.append("3) to choose how to  sort animals**  (see patch notes)\n");
		str.append("4) See all the penguins in the zoo according to your sorting selection (defult by height)\n");
		str.append("5) Showing the details of the carnivore\n");
		str.append("6) Adding a new carnivore\n");
		str.append(
				"7) Displaying the details of the fish in the aquarium and a list of their colors without duplication\n");
		str.append("8) Adding new fish\n");
		str.append("9) show all lemurs in zoo\n");
		str.append("10) add new lemur\n");
		str.append("11) show all cervus in zoo\n");
		str.append("12) add new cervus\n");
		str.append("13) To feed all the animals \n");
		str.append("14) Listening to all animals \n");
		str.append("15) To see the 2 dominant colors in the fish \n");
		str.append("16) A year has passed \n");
		str.append("17) see your money\n");
		str.append("18) price menu\n ");
		str.append("\n --------- data base project -------\n ");
		str.append("19) delate one animal from data base : { our project + sql file }\n ");
		str.append("20) delate all animals from data base : { our project + sql file }\n ");
		str.append("21) print all animals in data base sorted by id \n");
		str.append("22) change animal price\n");

		str.append("-1) EXIT\n");

		while (!finished) {
			try {
				String input = IO.showInputDialog(str);
				if (input == null) {
					choose = -1;
					break;
				}
				choose = Integer.parseInt(input);
				finished = true;
			} catch (InputMismatchException e) {
				IO.showMessageDialog(null, "Please enter a valid number");
			} catch (Exception e) { // A exception that we didn't think about.
				IO.showMessageDialog(null, "EROR, Please enter number again");

			}

		}
		return choose;

	}

}
