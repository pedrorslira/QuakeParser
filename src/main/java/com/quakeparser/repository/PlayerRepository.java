package com.quakeparser.repository;

import java.util.ArrayList;

import com.quakeparser.model.Player;

public class PlayerRepository {

	private static PlayerRepository instance = null;
	private ArrayList<Player> players = new ArrayList<>();

	private PlayerRepository() {

	}

	public static PlayerRepository getInstance() {
		if (instance == null) {
			instance = new PlayerRepository();
		}
		return instance;
	}

	public ArrayList<Player> getPlayer() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public static void setInstance(PlayerRepository instance) {
		PlayerRepository.instance = instance;
	}

}
