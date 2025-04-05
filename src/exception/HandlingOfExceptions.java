package exception;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class HandlingOfExceptions {
	JOptionPane IO = new JOptionPane();

	// Checking a number within minimum and maximum limits
	public <T extends Number> T tryCatchFuncNumbers(T min, T max, String attribute, String typeAnimal,
			String unitsOfMeasurement, String inputText) {

		T number = null;
		boolean finished = false;

		while (!finished) {
			try {
				if (min instanceof Integer) {
					String input = IO.showInputDialog(inputText);
					number = (T) Integer.valueOf(input);
				}

				else if (min instanceof Double) {
					String input = IO.showInputDialog(inputText);
					number = (T) Double.valueOf(input);
				}

				else if (min instanceof Float) {
					String input = IO.showInputDialog(inputText);
					number = (T) Float.valueOf(input);
				} else
					IO.showMessageDialog(null, "I don't know type of number\n");

				if (number.doubleValue() >= min.doubleValue() && number.doubleValue() <= max.doubleValue())
					finished = true;
				else if (typeAnimal == "penguin" && attribute == "height" && number.doubleValue() > max.doubleValue())
					// Height higher than the leader of the penguins
					throw new MyException("EROR, This " + attribute + " is is greater than the leader " + max + " "
							+ unitsOfMeasurement);
				else {
					if (unitsOfMeasurement == null)// Checking a number regardless of a particular feature
						throw new RuntimeException("EROR, Please enter a value between " + min + " and " + max);

					else
						throw new RuntimeException("EROR, Please enter a" + attribute + " of " + typeAnimal
								+ " between " + min + " and " + max + unitsOfMeasurement);

				}
			} catch (InputMismatchException e) {
				IO.showMessageDialog(null, "Please enter a valid number");
			} catch (MyException e) {
				IO.showMessageDialog(null, e.getMessage());
			} catch (RuntimeException e) {
				IO.showMessageDialog(null, e.getMessage());

			} catch (Exception e) { // A exception that we didn't think about.
				IO.showMessageDialog(null, "EROR, Please enter number again");

			}
		}
		return number;
	}

	// Checking a number regardless of a particular feature
	public <T extends Number> T tryCatchFuncNumbers(T min, T max, String inputText) {
		return tryCatchFuncNumbers(min, max, null, null, null, inputText);

	}

	// -> tryCatchFuncNumbers

	// Range check when there is no upper limit
	public <T extends Number> T tryCatchFuncNumbers(T min, String inputText) {
		T number = null;
		boolean finished = false;
		while (!finished) {

			try {

				if (min instanceof Integer) {
					String input = IO.showInputDialog(inputText);
					number = (T) Integer.valueOf(input);
				}

				else if (min instanceof Double) {
					String input = IO.showInputDialog(inputText);
					number = (T) Double.valueOf(input);
				}

				else if (min instanceof Float) {
					String input = IO.showInputDialog(inputText);
					number = (T) Float.valueOf(input);
				} else
					IO.showMessageDialog(null, "I don't know type of number\n");

				if (number.doubleValue() >= min.doubleValue())
					finished = true;
				else
					System.out.println("EROR, Please enter a number whose value is greater or equal than " + min);

			} catch (InputMismatchException e) {
				IO.showMessageDialog(null, "Please enter a valid number");
			} catch (Exception e) { // A exception that we didn't think about.
				IO.showMessageDialog(null, "EROR, Please enter number again");

			}
		}
		return number;
	}

	// Checking the integrity of a number that is indeed a number and not a string
	public int tryCatchFuncIntNumber(String inputText) {
		int number = 0;
		boolean finished = false;
		while (!finished) {

			try {
				String input = IO.showInputDialog(inputText);
				number = Integer.parseInt(input);
				finished = true;
			} catch (InputMismatchException e) {
				IO.showMessageDialog(null, "Please enter a valid number");
			} catch (Exception e) { // A exception that we didn't think about.
				IO.showMessageDialog(null, "EROR, Please enter number again");

			}
		}
		return number;
	}

	// String testing, the assumption that an animal can be defined with a "number"
	// name (for example in the case that an animal was just born)
	public String tryCatchFuncString(String textForInput) {
		String string = null;
		boolean finished = false;
		while (!finished) {

			try {
				string = IO.showInputDialog(textForInput).toLowerCase();
				if (string == null || string.trim().isEmpty())
					throw new RuntimeException("Name can not be empty");
				char[] arrName = string.toCharArray();
				if (arrName.length == 1)
					throw new RuntimeException("name of animal must be at least 2 char between [a/A -> z/Z]");

				if (arrName[0] < 'a' || arrName[0] > 'z')
					throw new RuntimeException("name of animal must be start with latter from [a/A -> z/Z]");

				for (int i = 0; i < arrName.length; i++) {

					if (arrName[i] < 'a' && arrName[i] != ' ' || arrName[i] > 'z' && arrName[i] != ' ')
						throw new RuntimeException("name of animal must be only laters from [a/A -> z/Z] ");
				}
				finished = true;

			} catch (RuntimeException e) {
				IO.showMessageDialog(null, e.getMessage());
			} catch (Exception e) { // A exception that we didn't think about.
				IO.showMessageDialog(null, "EROR, Please enter String again");

			}
		}

		string = Character.toUpperCase(string.charAt(0)) + string.substring(1); // -> this change the name of
		return string;
	}

}
