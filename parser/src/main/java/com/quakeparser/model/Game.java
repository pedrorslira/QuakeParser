package com.quakeparser.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
	private static AtomicInteger increment = new AtomicInteger(0);
	private int id;
	private int totalKills;
	private ArrayList<Player> players = new ArrayList<>();

	public Game() {
		totalKills = 0;
		id = increment.incrementAndGet();
	}

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

	public Player searchPlayer(String nickname) {
		for (Player p : this.players) {
			if (p.getNickname().equals(nickname)) {
				return p;
			}
		}
		return null;
	}

}
