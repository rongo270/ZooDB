package sortAnimals;

import java.util.ArrayList;
import java.util.Comparator;

import characteristicsOfAnimals.Animal;

public class SortAnimals {

	// In this class I implement sorting with algorithms for sorting a complete
	// array list in O(nlog n) and if the entire array list is already sorted then
	// the sorting happens in O(log n)

	public <T extends Animal> ArrayList<T> sortAnmimalsByHeightOrAgeInMergeSort(ArrayList<T> arrayOfPenguin,
			SortAnimal whichSort) {
		if (arrayOfPenguin.size() <= 1)
			return arrayOfPenguin;

		int left = arrayOfPenguin.size() / 2;
		ArrayList<T> leftList = new ArrayList<>();
		ArrayList<T> rightList = new ArrayList<>();

		for (int i = 0; i < arrayOfPenguin.size(); i++) {
			if (i < left)
				leftList.add(arrayOfPenguin.get(i));
			else
				rightList.add(arrayOfPenguin.get(i));
		}

		leftList = sortAnmimalsByHeightOrAgeInMergeSort(leftList, whichSort);
		rightList = sortAnmimalsByHeightOrAgeInMergeSort(rightList, whichSort);

		if (whichSort.equals(whichSort.HEIGHT))
			arrayOfPenguin = merge(leftList, rightList, Comparator.comparing(Animal::getHeight));
		else
			arrayOfPenguin = merge(leftList, rightList, Comparator.comparing(Animal::getAge));

		return arrayOfPenguin;
	}

	private <T extends Animal> ArrayList<T> merge(ArrayList<T> leftList, ArrayList<T> rightList,
			Comparator<T> comparator) {
		ArrayList<T> mergedList = new ArrayList<>();

		int leftSize = leftList.size();
		int rightSize = rightList.size();
		int i = 0, j = 0;

		while (i < leftSize && j < rightSize) {
			// if (leftList.get(i).getHeight() > rightList.get(j).getHeight())
			if (comparator.compare(leftList.get(i), rightList.get(j)) > 0)
				mergedList.add(leftList.get(i++));
			else
				mergedList.add(rightList.get(j++));
		}

		while (i < leftSize)
			mergedList.add(leftList.get(i++));

		while (j < rightSize)
			mergedList.add(rightList.get(j++));

		return mergedList;
	}

	public <T extends Animal> void addOneNewAnimalWhenAllOtherAnimalSortByHeightOrAge(ArrayList<T> sortedList,
			T newAnimal, Comparator comparator) {
		int low = 0;
		int high = sortedList.size() - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			T middleAnimal = sortedList.get(mid);

			if (comparator.compare(middleAnimal, newAnimal) > 0)
				low = mid + 1;
			else if (comparator.compare(middleAnimal, newAnimal) < 0)
				high = mid - 1;
			else {
				// sortedList.add(mid, newAnimal);
				low = mid + 1;

			}
		}
		sortedList.add(low, newAnimal);

	}

	public <T extends Animal> void sortAnimalsByABC(ArrayList<T> arrayListOfAnimals) {
		quickSort(arrayListOfAnimals, 0, arrayListOfAnimals.size() - 1);
	}

	private <T extends Animal> void quickSort(ArrayList<T> arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);

			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

	private <T extends Animal> int partition(ArrayList<T> arr, int low, int high) {
		String pivot = arr.get(high).getName();
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (arr.get(j).getName().compareTo(pivot) < 0) {
				i++;

				T temp = arr.get(i);
				arr.set(i, arr.get(j));
				arr.set(j, temp);
			}
		}

		T temp = arr.get(i + 1);
		arr.set(i + 1, arr.get(high));
		arr.set(high, temp);

		return i + 1;
	}

}
