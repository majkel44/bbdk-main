package pl.pg.edu.eti.creators.institutions;

import pl.pg.edu.eti.creators.people.GameCreator;

import java.util.List;
import java.util.TreeMap;

/**
 * Provides basic information about a game studio.
 * Game studio is an institution.
 *
 * @author Piotr Bereznowski
 * @version 2.0
 */
public class GameStudio extends Institution {

    /**
     * A collection that contains:
     * {@link GameCreator} as a key representing game creator who worked on a game published by the game studio
     * {@link Integer} as value representing the number of games created by the game creator with the game studio
     */
    private TreeMap<GameCreator, Integer> gameCreatorToNumberOfGames;

    /**
     * Instantiates a new Game studio.
     *
     * @param name          name of the game studio
     * @param country       country in which the game studio is registered
     * @param establishedIn the year of establishing the game studio
     */
    public GameStudio(String name, String country, Integer establishedIn) {
        super(name, country, establishedIn);
        this.gameCreatorToNumberOfGames = new TreeMap<>();
    }

    /**
     * Retrieves the collection of all game creators who worked on a game published by the game studio
     * and their game count
     *
     * @return gameCreatorToNumberOfGames
     */
    public TreeMap<GameCreator, Integer> getGameCreatorToNumberOfGames() {
        return gameCreatorToNumberOfGames;
    }

    /**
     * Sets collection of game creators who worked on a game published by the game studio and their game count
     *
     * @param gameCreatorToNumberOfGames map of game creators to number of games
     */
    public void setGameCreatorToNumberOfGames(TreeMap<GameCreator, Integer> gameCreatorToNumberOfGames) {
        this.gameCreatorToNumberOfGames = gameCreatorToNumberOfGames;
    }

    /**
     * Increases the number of games published by a game creator with the game studio
     *
     * @param gameCreator a game creator whose game count needs to be increased
     */
    public void increaseGameCreatorGameCount(GameCreator gameCreator) {
        if (!gameCreatorToNumberOfGames.containsKey(gameCreator)) {
            gameCreatorToNumberOfGames.put(gameCreator, 1);
        } else {
            gameCreatorToNumberOfGames.put(gameCreator, gameCreatorToNumberOfGames.get(gameCreator) + 1);
        }
    }

    /**
     * Increases the number of games published with the game studio by a list of game creators
     *
     * @param gameCreators a list of game creators whose game counts need to be increased
     */
    public void increaseGameCreatorsGameCount(List<GameCreator> gameCreators) {
        for (GameCreator gc : gameCreators) {
            increaseGameCreatorGameCount(gc);
        }
    }

    /**
     * Decrease the number of games published by a game creator with the game studio
     *
     * @param gameCreator a gameCreator whose game count needs to be decreased
     */
    public void decreaseGameCreatorGameCount(GameCreator gameCreator) {
        try {
            if (gameCreatorToNumberOfGames.get(gameCreator).equals(0)) {
                System.out.println("You have already removed all of the games " +
                        "published by this game creator with this game studio");
            } else {
                gameCreatorToNumberOfGames.put(gameCreator, gameCreatorToNumberOfGames.get(gameCreator) - 1);
            }
        } catch (Exception e) {
            System.out.println("This game creator did not publish with this game studio!");
        }
    }

    /**
     * Decrease the number of games published with the game studio by a list of game creators
     *
     * @param gameCreators a list of game creators whose game counts need to be decreased
     */
    public void decreaseGameCreatorsGameCount(List<GameCreator> gameCreators) {
        for (GameCreator gc : gameCreators) {
            decreaseGameCreatorGameCount(gc);
        }
    }
}
