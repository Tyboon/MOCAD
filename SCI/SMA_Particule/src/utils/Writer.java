package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

	@SuppressWarnings("resource")
	public static void write(String path, ArrayList<Integer> list) {
		try {
			BufferedWriter file= new BufferedWriter(new FileWriter(path));
		
			for (Integer i : list) {
				file.write(""+i + "\n");
			}
			file.close();
		} catch (IOException e) {
			System.out.println("Impossible de créer le fichier");
			e.printStackTrace();
		}
		return;
	}
}
