package com.quakeparser.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.quakeparser.model.Game;

@Repository
public class GameRepository {

	private ArrayList<Game> games = new ArrayList<>();

	public ArrayList<Game> getGames() {
		return games;
	}

	public Game getGameById(int id) {
		for (Game g : this.games) {
			if (g.getId() == id) {
				return g;
			}
		}
		return null;
	}

}
