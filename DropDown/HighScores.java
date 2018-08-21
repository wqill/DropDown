package DropDown;

import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;


/**
 * Highest scores.
 */
public class HighScores {
	private Map<Integer, String> scoreMap;
	private String fileName;

	public HighScores(String fileName) {
		this.fileName = fileName;
		scoreMap = new TreeMap<>();
	}

	public static void writeHS(Map<Integer, String> scoreMap) {
		try {
			FileWriter writeFile = new FileWriter("HighScores.txt");
			List<Integer> keys = new ArrayList<>(scoreMap.keySet());
			Collections.sort(keys);
			Collections.reverse(keys);

			for (int x : keys) {
				String name = scoreMap.get(x);
				String score = Integer.toString(x);
				String next = name + " ; " + score + "\n";
				writeFile.write(next);
			}
			writeFile.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	public Map<Integer, String> getHS() {
		try {
			BufferedReader readFile = new BufferedReader (new FileReader (fileName));
			String textLine, name;

			while ((textLine = readFile.readLine())!= null) {
				String[] arr = textLine.split(" ; ");
				if(arr[0].length() >= 15){
					name = arr[0].substring(0, 14);
				} else {
					name = arr[0];
				}
				int score = Integer.parseInt(arr[1]);
				scoreMap.put(score, name);
			}
			readFile.close();
			return scoreMap;
		} catch (Exception e) {
			e.printStackTrace();
			return new TreeMap<Integer, String>();
		}
	}

}
