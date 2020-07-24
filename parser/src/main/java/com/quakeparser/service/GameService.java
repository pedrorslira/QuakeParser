package com.quakeparser.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quakeparser.model.Game;
import com.quakeparser.model.Player;
import com.quakeparser.repository.GameRepository;

@Service
public class GameService {

	private BufferedReader readFile;
	private String line;

	@Autowired
	GameRepository gameRepository;;

	public void readFile() {
		try {
			FileReader file = new FileReader("./src/main/resources/games.log");
			readFile = new BufferedReader(file);
			line = readFile.readLine();
			while (line != null) {
				handleInitGame();
				line = readFile.readLine();
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleInitGame() {
		try {
			if (line.substring(7, 16).equals("InitGame:")) {
				Game game = new Game();
				line = readFile.readLine();
				while (!line.substring(10, 16).equals("------")) { // em alguns casos, um novo jogo inicia sem o antigo
																	// ser encerrado (sem o "ShutdownGame:")
																	// por isso essa comparação
					handlePlayer(game);
					handleKill(game);
					line = readFile.readLine();
				}
				Collections.sort(game.getPlayers(),
						(player1, player2) -> player2.getTotalKills() - player1.getTotalKills());
				gameRepository.getGames().add(game);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handlePlayer(Game game) {
		if (line.length() > 29 && line.substring(7, 29).equals("ClientUserinfoChanged:")) {
			int indexNickname = line.substring(34).indexOf("\\t");
			String nickname = line.substring(34).substring(0, indexNickname);
			if (game.searchPlayer(nickname) == null) {
				game.getPlayers().add(new Player(nickname));
			}
		}
	}

	public void handleKill(Game game) {

		if (line.substring(7, 12).equals("Kill:")) {
			int indexNickname = line.lastIndexOf(":") + 2;
			int indexKilled = line.lastIndexOf("killed");
			int indexBy = line.lastIndexOf("by");

			game.setTotalKills(game.getTotalKills() + 1);

			if (line.contains("<world>")) {
				killScore(game, indexKilled + 7, indexBy - 1, -1);
			} else {
				if (line.substring(indexNickname, indexKilled - 1)
						.equals(line.substring(indexKilled + 7, indexBy - 1))) {
					killScore(game, indexNickname, indexKilled - 1, -1);
				} else {
					killScore(game, indexNickname, indexKilled - 1, 1);
				}
			}
		}
	}

	public void killScore(Game game, int index, int finalIndex, int score) {
		Player player = game.searchPlayer(line.substring(index, finalIndex));
		if (player != null) {
			game.getPlayers().remove(player);
			player.setTotalKills(player.getTotalKills() + score);
			game.getPlayers().add(player);
		}
	}

}
