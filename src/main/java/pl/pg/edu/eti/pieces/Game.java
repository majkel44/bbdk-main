package pl.pg.edu.eti.pieces;

import pl.pg.edu.eti.creators.institutions.GameStudio;
import pl.pg.edu.eti.creators.people.GameCreator;
import pl.pg.edu.eti.reviews.GameReview;
import pl.pg.edu.eti.reviews.Review;

import java.util.Date;
import java.util.List;

/**
 * Provides basic information about a game.
 *
 * @author Michał Kruczkowski
 * @author Piotr Bereznowski
 * @version 2.5
 */
public class Game extends Piece {

    /**
     * Value for calculating rating - game recommended
     */
    private static final double GAME_RECOMMENDED = 5.0;
    /**
     * Value for calculating rating - game not recommended
     */
    private static final double GAME_NOT_RECOMMENDED = 0.0;
    /**
     * Value indicating if the game is expensive
     */
    private static final Double EXPENSIVE_GAME = 100.0;
    /**
     * Value indicating if the game is free
     */
    private static final Double FREE_GAME = 0.0;
    /**
     * Game studio responsible the game
     */
    private GameStudio gameStudio;
    /**
     * Creator of the game
     */
    private GameCreator creator;
    /**
     * Price of the game
     */
    private Double price;
    /**
     * Indicates if the game is free, cheap or expensive
     */
    private String priceCategory;


    /**
     * Game class constructor without additional fields provided.
     *
     * @param reviews the reviews
     * @param title   the title
     * @param id      the id
     */
    public Game(List<Review> reviews, String title, String id) {
        super(reviews, title, id);
    }

    /**
     * Game class constructor with additional fields provided.
     *
     * @param reviews     the reviews
     * @param title       the title
     * @param id          the id
     * @param gameStudio  the game studio
     * @param creator     the creator
     * @param releaseDate the release date
     * @param price       the price
     */
    public Game(List<Review> reviews, String title, String id, GameStudio gameStudio, GameCreator creator, Date releaseDate, double price) {
        super(reviews, title, id);
        this.creator = creator;
        this.gameStudio = gameStudio;
        this.releaseDate = releaseDate;
        this.price = price;
        this.priceCategory = priceCategorization();
    }

    /**
     * Calculates rating of the piece based on its reviews
     *
     * @return rating
     */
    @Override
    protected double calculateRating() {
        double value = 0; // field for calculating the rating
        double quantity = 0; // quantity of reviews

        try {
            for (Review review : reviews) {
                if (((GameReview) review).isRecommended())
                    value += GAME_RECOMMENDED; // +5
                else
                    value += GAME_NOT_RECOMMENDED; // +1
                quantity += SINGLE_REVIEW;
            }
            return value / quantity;
        } catch (NullPointerException e) {
            return NO_REVIEWS;
        }
    }

    /**
     * A method for determining if the game is cheap, expensive or free
     *
     * @return priceCategory
     */
    private String priceCategorization() {
        if (price > EXPENSIVE_GAME) return "Expensive";
        else if (price == FREE_GAME) return "Free";
        else if (price < EXPENSIVE_GAME) return "Cheap";
        return "Unknown";
    }

    /**
     * Retrieves the game studio responsible for the game.
     *
     * @return gameStudio
     */
    public GameStudio getGameStudio() {
        return gameStudio;
    }

    /**
     * Changes the game studio
     *
     * @param gameStudio the game studio
     */
    public void setGameStudio(GameStudio gameStudio) {
        this.gameStudio = gameStudio;
    }

    /**
     * Retrieves the creator of a game.
     *
     * @return creator
     */
    public GameCreator getCreator() {
        return creator;
    }

    /**
     * Changes the game creator
     *
     * @param creator the creator
     */
    public void setCreator(GameCreator creator) {
        this.creator = creator;
    }

    /**
     * Retrieves the price of a game.
     *
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Changes the price
     *
     * @param price the price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Retrieves the price categorization of a game.
     *
     * @return priceCategory
     */
    public String getPriceCategory() {
        return priceCategory;
    }

    /**
     * Changes the priceCategory
     *
     * @param priceCategory the price category
     */
    public void setPriceCategory(String priceCategory) {
        this.priceCategory = priceCategory;
    }

    /**
     * Visualization of a Game class object.
     */
    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                ", game studio=" + gameStudio +
                ", game creator=" + creator +
                ", price=" + price +
                ", price category=" + priceCategory + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
