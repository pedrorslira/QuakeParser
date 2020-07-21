package com.quakeparser.model;

public class Player {
	String nickname;
	int totalKills;

	public Player(String nickname) {
		totalKills = 0;
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getTotalKills() {
		return totalKills;
	}

	public void setTotalKills(int totalKills) {
		this.totalKills = totalKills;
	}

}
