package pl.pg.edu.eti.reviews;

import pl.pg.edu.eti.pieces.Piece;

import pl.pg.edu.eti.pieces.Game;

import java.util.Date;

/**
 * Game Review with recommendation, rates of fun and helpfulness and additional details
 *
 * @author Marek Drwal
 * @version 2.3
 */
public class GameReview extends Review {

    /**
     * If the piece is recommended.
     */
    private boolean recommended;

    /**
     * How many users found the recommendation helpful
     */
    private int helpfulness;

    /**
     * How many users found the recommendation funny
     */
    private int funny;

    /**
     * How may hours played by user
     */
    private double hrsPlayed;

    /**
     * Instantiates a new Game review.
     *
     * @param reviewer    reviewer
     * @param date        date
     * @param recommended if recommended
     * @param helpfulness helpfulness rate
     * @param funny       fun rate
     * @param hrs         hours played
     */
    public GameReview(String reviewer, Date date, boolean recommended, int helpfulness, int funny, double hrs, Piece game) {
        super(reviewer, date, game);
        this.recommended = recommended;
        this.helpfulness = helpfulness;
        this.funny = funny;
        this.hrsPlayed = hrs;
        game.updateRating();
    }

    /**
     * Tells if the piece is recommended or not
     *
     * @return <i>True</i> or <i>False</i>
     */
    public boolean isRecommended() {
        return recommended;
    }

    /**
     * Sets if the piece is recommended
     *
     * @param recommended <i>True</i> or <i>False</i>
     */
    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    /**
     * How many users found the recommendation helpful
     *
     * @return helpfulness rate
     */
    public int getHelpfulness() {
        return helpfulness;
    }

    /**
     * Sets helpfulness of the review
     *
     * @param helpfulness helpfulness rate
     */
    public void setHelpfulness(int helpfulness) {
        this.helpfulness = helpfulness;
    }

    /**
     * How many users found the recommendation funny
     *
     * @return fun rate
     */
    public int getFunny() {
        return funny;
    }

    /**
     * Sets rate of fun
     *
     * @param funny fun rate
     */
    public void setFunny(int funny) {
        this.funny = funny;
    }

    /**
     * How may hours played by user
     *
     * @return hours played
     */
    public double getHrsPlayed() {
        return hrsPlayed;
    }

    /**
     * Sets hours played by user
     *
     * @param hrsPlayed hours played
     */
    public void setHrsPlayed(double hrsPlayed) {
        this.hrsPlayed = hrsPlayed;
    }

    /**
     * Provides textual representation of the review
     * Overrides <i>toString()</i> from <i>Review</i>
     *
     * @return String representing the main contents of the review
     */
    @Override
    public String toString() {
        return "Review [by: " + reviewer + ", is recommended: " + recommended + ", helpfulness: " + helpfulness
                + ", funny: " + funny + ", hrsPlayed: " + hrsPlayed + "]";
    }

}
