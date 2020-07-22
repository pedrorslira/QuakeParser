package com.quakeparser.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quakeparser.model.Game;
import com.quakeparser.model.Player;
import com.quakeparser.repository.GameRepository;

@Service
public class GameService {

	private BufferedReader readFile;

	@Autowired
	GameRepository gameRepository;;

	public void readFile() {
		try {
			FileReader file = new FileReader("./src/main/resources/games.log");
			readFile = new BufferedReader(file);
			String line = readFile.readLine();
			while (line != null) {
				handleInitGame(line);
				line = readFile.readLine();
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleInitGame(String line) {
		try {
			if (line.substring(7, 16).equals("InitGame:")) {
				Game game = new Game();
				line = readFile.readLine();
				while (!line.substring(10, 16).equals("------")) { // em alguns casos, um novo jogo inicia sem o antigo
																	// ser encerrado (sem o "ShutdownGame:")
																	// por isso essa comparação
					handlePlayer(line, game);
					handleKill(line, game);
					line = readFile.readLine();
				}
				gameRepository.getGames().add(game);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handlePlayer(String line, Game game) {
		if (line.length() > 29 && line.substring(7, 29).equals("ClientUserinfoChanged:")) {
			int indexNickname = line.substring(34).indexOf("\\t");
			String nickname = line.substring(34).substring(0, indexNickname);
			if (game.searchPlayer(nickname) == null) {
				game.getPlayers().add(new Player(nickname));
			}
		}
	}

	public void handleKill(String line, Game game) {
		if (line.substring(7, 12).equals("Kill:")) {
			game.setTotalKills(game.getTotalKills() + 1);
			if (line.contains("<world>")) {
				int indexKilled = line.lastIndexOf("killed");
				int indexBy = line.lastIndexOf("by");
				Player player = game.searchPlayer(line.substring(indexKilled + 7, indexBy - 1));
				if (player != null) {
					game.getPlayers().remove(player);
					player.setTotalKills(player.getTotalKills() - 1);
					game.getPlayers().add(player);
				}
			} else {
				int indexNickname = line.lastIndexOf(":") + 2;
				int indexKilled = line.lastIndexOf("killed");
				Player player = game.searchPlayer(line.substring(indexNickname, indexKilled - 1));
				if (player != null) {
					game.getPlayers().remove(player);
					player.setTotalKills(player.getTotalKills() + 1);
					game.getPlayers().add(player);
				}
			}
		}
	}
}
