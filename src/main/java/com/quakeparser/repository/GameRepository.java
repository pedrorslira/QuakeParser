package com.quakeparser.repository;

import java.util.ArrayList;

import com.quakeparser.model.Game;

public class GameRepository {

	private static GameRepository instance = null;
	private ArrayList<Game> games = new ArrayList<>();

	private GameRepository() {

	}

	public static GameRepository getInstance() {
		if (instance == null) {
			instance = new GameRepository();
		}
		return instance;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	public static void setInstance(GameRepository instance) {
		GameRepository.instance = instance;
	}

}
