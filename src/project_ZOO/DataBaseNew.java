package project_ZOO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

public class DataBaseNew {

	public static void main(String[] args, ZooManger manger) {
		
		
	

		String url = "jdbc:mysql://localhost:3306/newanimaldatabase";
		String user = "root";
		String password = "root";

		ArrayList<Animal> animalArray = new ArrayList<Animal>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to MySQL database successfully!");

			String selectQuery = "SELECT * FROM aquariumfish";
			PreparedStatement pstmt = connection.prepareStatement(selectQuery);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id"); 
				int ageOfFish = rs.getInt("ageOfFish");
				double fishLength = rs.getDouble("fishLength");

				String colorsStr = rs.getString("hisColor");
				String[] colorsArray = colorsStr.split(",");
				ArrayList<Object> hisColor = new ArrayList<>();
				for (String color : colorsArray)
					hisColor.add(FishColor.valueOf(color.trim()));

				String characteristicsStr = rs.getString("hisTrait");
				String[] characteristicsArray = characteristicsStr.split(",");
				ArrayList<Object> hisTraits = new ArrayList<>();
				for (String trait : characteristicsArray) {
					hisTraits.add(CharacteristicOfAFish.valueOf(trait.trim()));
				}

				AquariumFish fish = new AquariumFish(id, ageOfFish, fishLength, hisColor,
						(CharacteristicOfAFish) hisTraits.get(0));
				animalArray.add(fish);
			}

			selectQuery = "SELECT * FROM goldfish";
			pstmt = connection.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id"); 
				int ageOfFish = rs.getInt("ageOfFish");
				double fishLength = rs.getDouble("fishLength");
				String colorsStr = rs.getString("fishColor");
				GoldFishColor color = GoldFishColor.valueOf(colorsStr);

				GoldFish goldFish = new GoldFish(id, ageOfFish, fishLength, color);
				animalArray.add(goldFish);
			}

			// 3. שליפה מטבלת ClownFish
			selectQuery = "SELECT * FROM clownfish";
			pstmt = connection.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id"); // מותאם לשדה id
				int ageOfFish = rs.getInt("ageOfFish");
				double fishLength = rs.getDouble("fishLength");
				ClownFish clownFish = new ClownFish(id, ageOfFish, fishLength);
				animalArray.add(clownFish);
			}

			
			selectQuery = "SELECT a.id, a.age, an.name, g.gender, c.weight , c.isTiger\r\n"
					+ "FROM Animals a\r\n"
					+ "JOIN AnimalNames an ON a.id = an.id\r\n"
					+ "JOIN Gender g ON a.id = g.id\r\n"
					+ "JOIN carnivore c ON a.id = c.id;" ;
				pstmt = connection.prepareStatement(selectQuery);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					int id = rs.getInt("id");
					int age = rs.getInt("age");
					boolean isTiger = rs.getBoolean("isTiger");
					float weight = rs.getFloat("weight");
					String genderStr = rs.getString("gender");
					String name =  rs.getString("name");
					Gender gender = Gender.valueOf(genderStr);

					Carnivore carnivore = null;
					if (isTiger) {
						carnivore = new Tiger(id, name, gender, age, weight);

					} else  {
						carnivore = new Lion(id, name, gender, age, weight);
					}

					animalArray.add(carnivore);
				}
			
				
			

				
				
			selectQuery = "\r\n"
					+ "SELECT a.id, a.age, an.name, g.gender, c.lengthOfTheRays\r\n"
					+ "FROM Animals a\r\n"
					+ "JOIN AnimalNames an ON a.id = an.id\r\n"
					+ "JOIN Gender g ON a.id = g.id\r\n"
					+ "JOIN Cervus c ON a.id = c.id";
			pstmt = connection.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int cervusId = rs.getInt("id"); // מותאם לשדה id
				int age = rs.getInt("age");
				double lengthOfTheRays = rs.getDouble("lengthOfTheRays");
				String name = rs.getString("name");
				String stringGender = rs.getString("gender");
				Gender gender = Gender.valueOf(stringGender);
				Cervus cervus = new Cervus(cervusId,name , gender,age, lengthOfTheRays);
				animalArray.add(cervus);
			}
		

			selectQuery = "SELECT a.id, a.age, an.name,  c.height\r\n"
					+ "FROM Animals a\r\n"
					+ "JOIN AnimalNames an ON a.id = an.id\r\n"
					+ "JOIN Lemur c ON a.id = c.id";
			pstmt = connection.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int lemurId = rs.getInt("id"); 
				int age = rs.getInt("age");
				float height = rs.getFloat("height");
				String name = rs.getString("name");
				Lemur lemur = new Lemur(lemurId, name ,  age, height);
				animalArray.add(lemur);
			}
			
			
			
			

			selectQuery = "SELECT a.id, a.age, an.name,  c.height\r\n"
					+ "FROM Animals a\r\n"
					+ "JOIN AnimalNames an ON a.id = an.id\r\n"
					+ "JOIN Penguin c ON a.id = c.id";
			pstmt = connection.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int penguinId = rs.getInt("id"); 
				int age = rs.getInt("age");
				float height = rs.getFloat("height");
				String name = rs.getString("name");
				Penguin penguin = new Penguin(penguinId,name  ,age, height);
				animalArray.add(penguin);
			}

		
			//   sort by id //
			animalArray.sort((o1, o2) -> Integer.compare(o1.getAnimalId(), o2.getAnimalId()));
			//   sort by id //

			
			for (Animal animal : animalArray) {
				System.out.println(animal);
				manger.CheckingIfThereIsMoneyToBuyAnimals(animal);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
