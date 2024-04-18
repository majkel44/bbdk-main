package pl.pg.edu.eti.reviews;

import pl.pg.edu.eti.pieces.Piece;

import pl.pg.edu.eti.pieces.Film;

import java.util.Date;

/**
 * Film Review with rating and additional details
 *
 * @author Marek Drwal
 * @version 2.0
 */
public class FilmReview extends Review {

    /**
     * A constant determining minimum possible rating to be given
     */
    public static final int MIN_RATING = 0;

    /**
     * A constant determining maximum possible rating to be given
     */
    public static final int MAX_RATING = 5;

    /**
     * The rating given. Valued 0-5
     */
    private double rating;

    /**
     * Constructor for FilmReview
     *
     * @param date     date
     * @param reviewer author of the review
     * @param rating   rating (0-5)
     * @param film film piece
     */
    public FilmReview(Date date, String reviewer, double rating, Piece film) {
        super(reviewer, date, film);
        this.rating = rating;
        film.updateRating();
    }

    /**
     * Provides textual representation of the review
     * Overrides <i>toString()</i> from <i>Review</i>
     *
     * @return String representing the main contents of the review
     */
    @Override
    public String toString() {
        return "Review [by: " + reviewer + ", rating: " + rating + "]";
    }

    /**
     * Retrieves rating
     *
     * @return rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets rating
     *
     * @param rating rating given to the piece
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

}
