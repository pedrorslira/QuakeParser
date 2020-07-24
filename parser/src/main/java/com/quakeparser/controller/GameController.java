package com.quakeparser.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quakeparser.model.Game;
import com.quakeparser.repository.GameRepository;
import com.quakeparser.service.GameService;

@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {

	@Autowired
	GameService gameService;

	@Autowired
	GameRepository gameRepository;

	@GetMapping("/parser")
	public ArrayList<Game> listGames() {
		if(gameRepository.getGames().isEmpty()) {
			gameService.readFile();
		}
		return gameRepository.getGames();
	}

	@GetMapping("/id/{gameId}")
	public Game getGameById(@PathVariable int gameId) {
		return gameRepository.getGameById(gameId);
	}
}
