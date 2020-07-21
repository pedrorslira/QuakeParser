package com.quakeparser.model;

import java.util.ArrayList;

public class Game {
	private int id;
	private int totalKills;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Integer> kills = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalKills() {
		return totalKills;
	}

	public void setTotalKills(int totalKills) {
		this.totalKills = totalKills;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<Integer> getKills() {
		return kills;
	}

	public void setKills(ArrayList<Integer> kills) {
		this.kills = kills;

	}
}