package pl.pg.edu.eti.reviews;

import pl.pg.edu.eti.pieces.Piece;

import pl.pg.edu.eti.pieces.Book;

import java.util.Date;


/**
 * Book Review with rating, comment and additional details
 *
 * @author Marek Drwal
 * @author Piotr Bereznowski
 * @version 2.4
 */
public class BookReview extends Review {

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
     * Text of the review
     */
    private String fullComment;

    /**
     * Textual summary of the review
     */
    private String summary;

    /**
     * The extent to which the review was helpful to other users. Valued 0-5
     */
    private double helpfulness;

    /**
     * Instantiates a new Book review.
     *
     * @param reviewer    author of the review
     * @param date        date
     * @param rating      rating, 0-5
     * @param summary     summary text
     * @param fullComment text of the review
     * @param helpfulness review helpfulness
     */
    public BookReview(String reviewer, Date date, double rating, String summary, String fullComment, double helpfulness, Piece book) {
        super(reviewer, date, book);
        this.reviewer = reviewer;
        this.rating = rating;
        this.summary = summary;
        this.fullComment = fullComment;
        this.helpfulness = helpfulness;
        book.updateRating();
    }

    /**
     * Provides textual representation of the review
     * Overrides <i>toString()</i> from <i>Object</i>
     *
     * @return String representing the main contents of the review
     */
    @Override
    public String toString() {
        return "BookReview [author=" + reviewer + ", date=" + date + ", rating=" + rating + ", summary=" + summary + "]\n" + "for: " + piece;
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

    /**
     * Retrieves full comment
     *
     * @return text of the review
     */
    public String getFullComment() {
        return fullComment;
    }

    /**
     * Sets comment
     *
     * @param fullComment text of the comment
     */
    public void setFullComment(String fullComment) {
        this.fullComment = fullComment;
    }

    /**
     * Retrieves summary
     *
     * @return summary of the review
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets review summary
     *
     * @param summary summary text
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * How helpful the review is
     *
     * @return helpfulness rate (0-5)
     */
    public double getHelpfulness() {
        return helpfulness;
    }

    /**
     * Sets helpfulness of the review
     *
     * @param helpfulness helpfulness rate
     */
    public void setHelpfulness(double helpfulness) {
        this.helpfulness = helpfulness;
    }

}
