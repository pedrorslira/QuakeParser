package com.quakeparser.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameService { // LEMBRAR DE TIRAR O STATIC DE TUDO AQUI

	public static void readFile() {
		try {
			FileReader file = new FileReader("./src/main/resources/games.log");
			BufferedReader readFile = new BufferedReader(file);
			String line = readFile.readLine();
			while (line != null) {

				//handleKill(line);
				//handleInitGame(line);
				//handlePlayer(line);

				line = readFile.readLine();
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void handleInitGame(String line) {
		if (line.substring(7, 16).equals("InitGame:")) {
			System.out.println(line.substring(7));
		}
	}

	public static void handlePlayer(String line) {
		if (line.length() > 29) {
			if (line.substring(7, 29).equals("ClientUserinfoChanged:")) {
				int lastIndexNickname = line.substring(34).indexOf("\\t");
				System.out.println(line.substring(34).substring(0, lastIndexNickname));
			}
		}

	}

	public static void handleKill(String line) {
		
		if (line.substring(7, 12).equals("Kill:")) {
			if(line.contains("<world>")){
				int indexKilled = line.lastIndexOf("killed");
				int indexBy = line.lastIndexOf("by");
				if(indexKilled != -1) {				
					System.out.println(line.substring(indexKilled + 7,indexBy)); //Aqui eu vou tirar 1 kill desse cara
				}	
			}
			else {
				int indexNickname = line.lastIndexOf(":") + 2;				
				int indexKilled = line.lastIndexOf("killed");			
				System.out.println(line.substring(indexNickname,indexKilled)); //aqui eu vou vou dar 1 kill pra esse cara
			}	
		}
	}

} 
