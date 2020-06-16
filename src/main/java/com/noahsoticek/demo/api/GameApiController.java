package com.noahsoticek.demo.api;

import com.noahsoticek.demo.controller.AuthenticationController;
import com.noahsoticek.demo.controller.GameController;
import com.noahsoticek.demo.models.Field;
import com.noahsoticek.demo.models.Game;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class GameApiController {

    GameController gameController = new GameController();

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public ArrayList<Game> getGames(@RequestHeader("Authorization") String jwt) {
        // Return all games of the current user
        int playerId = AuthenticationController.getPlayerId(jwt);
        return gameController.getGames(playerId);
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    public Game getGame(@RequestHeader("Authorization") String jwt, @PathVariable int id) {
        // Return game based on ID
        int playerId = AuthenticationController.getPlayerId(jwt);
        return gameController.getGame(id);
    }

    @RequestMapping(value = "/play", method = RequestMethod.PUT)
    public String playGame(@RequestHeader("Authorization") String jwt, @RequestBody Field field) {
        int playerId = AuthenticationController.getPlayerId(jwt);
        return gameController.gameExecutor(playerId, field);
    }

    @RequestMapping(value = "/gameField", method = RequestMethod.GET)
    public StringBuilder printGameField(@RequestHeader("Authorization") String jwt) {
        int playerId = AuthenticationController.getPlayerId(jwt);
        return gameController.createTable(playerId);
    }
}
