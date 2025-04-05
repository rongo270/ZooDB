package sqlDataBase;

import java.security.PublicKey;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import animals.AquariumFish;
import animals.Carnivore;
import animals.Cervus;
import animals.ClownFish;
import animals.GoldFish;
import animals.Lemur;
import animals.Lion;
import animals.Penguin;
import animals.Tiger;
import characteristicsOfAnimals.Animal;
import characteristicsOfAnimals.CharacteristicOfAFish;
import characteristicsOfAnimals.FishColor;
import characteristicsOfAnimals.Gender;
import characteristicsOfAnimals.FishColor.GoldFishColor;
import project_ZOO.ZooManger;
import sqlDataBase.MoneyDataBase;

public class DataBase {

	private static String url;
	private static String user;
	private static String password;

	private static int aquariumFishCount;
	private static int goldFishCount;
	private static int clownFishCount;
	private static int CervusCont;
	private static int lionCount;
	private static int tigerCount;
	private static int lemurCount;
	private static int penguinCount;

	public DataBase() {

		aquariumFishCount = 0;
		goldFishCount = 0;
		clownFishCount = 0;
		CervusCont = 0;
		lionCount = 0;
		tigerCount = 0;
		lemurCount = 0;
		penguinCount = 0;

	}

	public void setTheFileLocation(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public boolean readFromSqlFile(ZooManger zooManger) {
		boolean fileIsEmpty = true;

		JOptionPane IO = new JOptionPane();
		ArrayList<Animal> arrayListOfAnimals = new ArrayList<Animal>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			IO.showMessageDialog(null , "Connected to the PostgreSQL server successfully.");

			Statement stmt = conn.createStatement();

			String query = "SELECT a.animalid, a.age, a.happiness,  af.fishLength, af.hisColor, af.hisTrait\r\n"
					+ "FROM animals a\r\n" + "JOIN AquariumFish af ON a.animalid = af.animalid;\r\n" + "";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("animalid");
				int age = rs.getInt("age");
				int happiness = rs.getInt("happiness");
				double fishLength = rs.getDouble("fishLength");

				Array colorsArray = rs.getArray("hisColor");
				String[] colors = (String[]) colorsArray.getArray();
				ArrayList<Object> test = new ArrayList<>();
				for (String color : colors) {
					test.add(FishColor.valueOf(color));
				}
				String hisTraitString = rs.getString("hisTrait");
				CharacteristicOfAFish hisTrait = CharacteristicOfAFish.valueOf(hisTraitString);
				AquariumFish aquariumFish = new AquariumFish(id, age, fishLength, test, hisTrait);
				aquariumFish.setHappiness(happiness);
				arrayListOfAnimals.add(aquariumFish);
				aquariumFishCount++;

			}

			query = "SELECT a.animalid, a.age, a.happiness, g.fishlength, g.hiscolor\r\n" + "FROM animals a\r\n"
					+ "JOIN goldfish g ON a.animalid = g.animalid;\r\n";

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("animalid");
				int age = rs.getInt("age");
				int happiness = rs.getInt("happiness");
				double fishLength = rs.getDouble("fishLength");
				GoldFishColor hisColor = GoldFishColor.valueOf(rs.getString("hiscolor"));
				GoldFish goldFish = new GoldFish(id, age, fishLength, hisColor);
				goldFish.setHappiness(happiness);
				arrayListOfAnimals.add(goldFish);
				goldFishCount++;

			}

			query = "SELECT a.animalid, a.age , a.happiness, c.fishlength\r\n" + "FROM animals a\r\n"
					+ "JOIN clownfish c ON a.animalid = c.animalid;\r\n";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("animalid");
				int age = rs.getInt("age");
				int happiness = rs.getInt("happiness");
				double fishLength = rs.getDouble("fishLength");
				ClownFish clownFish = new ClownFish(id, age, fishLength);
				clownFish.setHappiness(happiness);
				arrayListOfAnimals.add(clownFish);
				clownFishCount++;

			}

			query = "SELECT a.animalid, a.age , a.happiness, p.height, n.nameOfAnimal\r\n" + "FROM animals a\r\n"
					+ "JOIN penguin p ON a.animalid = p.animalid\r\n"
					+ "JOIN animalsnames n ON a.animalid = n.animalid;\r\n";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("animalid");
				int age = rs.getInt("age");
				int happiness = rs.getInt("happiness");
				float height = rs.getFloat("height");
				String nameOfAnimal = rs.getString("nameOfAnimal");
				Penguin penguin = new Penguin(id, nameOfAnimal, age, height);
				penguin.setHappiness(happiness);
				arrayListOfAnimals.add(penguin);
				penguinCount++;

			}

			query = "SELECT a.animalid, a.age,  a.happiness , l.height, n.nameOfAnimal\r\n" + "FROM animals a\r\n"
					+ "JOIN Lemur l ON a.animalid = l.animalid\r\n" + "JOIN animalsnames n ON a.animalid = n.animalid;";

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("animalid");
				int age = rs.getInt("age");
				int happiness = rs.getInt("happiness");
				float height = rs.getFloat("height");
				String nameOfAnimal = rs.getString("nameOfAnimal");
				Lemur lemur = new Lemur(id, nameOfAnimal, age, height);
				lemur.setHappiness(happiness);
				arrayListOfAnimals.add(lemur);
				lemurCount++;
			}

			query = "SELECT a.animalid, a.age, a.happiness , l.weight, n.nameOfAnimal, g.gender\r\n" + "FROM animals a\r\n"
					+ "JOIN tiger l ON a.animalid = l.animalid\r\n"
					+ "JOIN animalsnames n ON a.animalid = n.animalid\r\n"
					+ "JOIN gender g ON a.animalid = g.animalid;";

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("animalid");
				int age = rs.getInt("age");
				int happiness = rs.getInt("happiness");
				float height = rs.getFloat("weight");
				String nameOfAnimal = rs.getString("nameOfAnimal");
				Gender gender = Gender.valueOf(rs.getString("gender"));
				Tiger tiger = new Tiger(id, nameOfAnimal, gender, age, height);
				tiger.setHappiness(happiness);
				arrayListOfAnimals.add(tiger);
				tigerCount++;

			}

			query = "SELECT a.animalid, a.age,  a.happiness , l.weight, n.nameOfAnimal, g.gender\r\n" + "FROM animals a\r\n"
					+ "JOIN lion l ON a.animalid = l.animalid\r\n"
					+ "JOIN animalsnames n ON a.animalid = n.animalid\r\n"
					+ "JOIN gender g ON a.animalid = g.animalid;";

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("animalid");
				int age = rs.getInt("age");
				int happiness = rs.getInt("happiness");
				float height = rs.getFloat("weight");
				String nameOfAnimal = rs.getString("nameOfAnimal");
				Gender gender = Gender.valueOf(rs.getString("gender"));
				Lion lion = new Lion(id, nameOfAnimal, gender, age, height);
				lion.setHappiness(happiness);
				arrayListOfAnimals.add(lion);
				lionCount++;

			}

			query = "SELECT a.animalid, a.age , a.happiness , l.lengthoftherays, n.nameOfAnimal, g.gender\r\n" + "FROM animals a\r\n"
					+ "JOIN cervus l ON a.animalid = l.animalid\r\n"
					+ "JOIN animalsnames n ON a.animalid = n.animalid\r\n"
					+ "JOIN gender g ON a.animalid = g.animalid;";

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("animalid");
				int age = rs.getInt("age");
				int happiness = rs.getInt("happiness");
				double lengthoftherays = rs.getDouble("lengthoftherays");
				String nameOfAnimal = rs.getString("nameOfAnimal");
				Gender gender = Gender.valueOf(rs.getString("gender"));
				Cervus cervus = new Cervus(id, nameOfAnimal, gender, age, lengthoftherays);
				cervus.setHappiness(happiness);
				arrayListOfAnimals.add(cervus);
				CervusCont++;

			}
			
			arrayListOfAnimals.sort((o1, o2) -> Integer.compare(o1.getAnimalId(), o2.getAnimalId()));

			boolean managedToGetIntoTheZoo = zooManger.addArrayListOfAnimalsFromDataBaseForFree(arrayListOfAnimals,
					DataBase.class);
			if (managedToGetIntoTheZoo) {
				StringBuffer inputFromSqlFile = new StringBuffer();
				inputFromSqlFile.append("The animals successfully entered the zoo : in total added : ");
				inputFromSqlFile.append(arrayListOfAnimals.size() +"\n"+ " new animals");
				inputFromSqlFile.append("aquarium Fish: "+aquariumFishCount +"\n");
				inputFromSqlFile.append("gold Fish : "+goldFishCount + "\n");
				inputFromSqlFile.append("clown Fish :"+clownFishCount + "\n");
				inputFromSqlFile.append("Cervus: "+CervusCont +"\n");
				inputFromSqlFile.append("lion :"+lionCount +"\n");
				inputFromSqlFile.append("tiger :"+tigerCount +"\n");
				inputFromSqlFile.append("lemur : "+lemurCount +"\n");
				inputFromSqlFile.append("penguin :"+penguinCount +"\n");
				IO.showMessageDialog(null, inputFromSqlFile);
				
				StringBuffer str = new StringBuffer();
				for (int i = 0; i < 60; i++)
					str.append(" ");
				str.append(
						"-------------------------------- animal from sql file --------------------------------------- \n\n");
				for (int i = 0; i < arrayListOfAnimals.size(); i++)
					str.append(arrayListOfAnimals.get(i) + "\n");
				IO.showMessageDialog(null, str);
			} else
				IO.showMessageDialog(null, "Animals did not successfully enter a zoo");

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileIsEmpty;
	}

}
