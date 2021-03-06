package com.anmertrix.module1.distance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinDistance {

	private IO io;
	
	public MinDistance(IO io) {
		this.io = io;
	}
	
	public void run() {
		String[] pieces = io.readConsole().split(" ");
		List<Integer> numbers = null;
		
		numbers = parseUserNumbersLine(pieces);
		
		List<Integer> twoMinNumbers = getTwoMinNumbers(numbers);
		List<Integer> distances = getDistances(numbers, twoMinNumbers);
		
		printDistanseList(distances);
	}
	
	protected List<Integer> parseUserNumbersLine(String[] pieces) {
		List<Integer> numbersLine = new ArrayList<Integer>();
		for (int i = 0; i < pieces.length; i++) {

			if (pieces[i].isEmpty()) {
				continue;
			}

			try {
				numbersLine.add(Integer.parseInt(pieces[i]));
			} catch (NumberFormatException e) {
				io.print("You can enter only numbers. \"" + pieces[i]
						+ "\" is not a number.\n ");
			}
		}

		return numbersLine;
	}

	protected List<Integer> getTwoMinNumbers(List<Integer> numbers) {

		List<Integer> sortList = new ArrayList<Integer>(numbers);
		List<Integer> result = new ArrayList<Integer>();

		Collections.sort(sortList);

		try {
			result.add(sortList.get(0));
		} catch (IndexOutOfBoundsException e) {
			io.print("You have not entered a number. ");
		}
		try {
			result.add(sortList.get(1));
		} catch (IndexOutOfBoundsException e) {
			io.print("You must entered 2 numbers or more.\n");
		}

		return result;
	}

	protected List<Integer> getDistances(List<Integer> numbers,
			List<Integer> twoMinNumbers) {

		int minNumber1 = 0;
		int minNumber2 = 0;
		try {
			minNumber1 = twoMinNumbers.get(0);
			minNumber2 = twoMinNumbers.get(1);
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
		}

		List<Integer> distances = new ArrayList<Integer>();

		for (int i = 0; i < numbers.size(); i++) {
			for (int j = i + 1; j < numbers.size(); j++) {
				if (numbers.get(i) == minNumber1
						&& numbers.get(j) == minNumber2) {
					distances.add(j - i);
				} else if (numbers.get(i) == minNumber2
						&& numbers.get(j) == minNumber1) {
					distances.add(j - i);
				}
			}
		}

		return distances;
	}

	protected void printDistanseList(List<Integer> distanceList) {
		StringBuilder line = new StringBuilder();
		
		for (int numbers : distanceList) {
			line.append(numbers).append(" ");
		}
		
		io.print(line.toString().trim());
	}

}
