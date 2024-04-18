package pl.pg.edu.eti.reviews;

import pl.pg.edu.eti.pieces.Piece;

import java.util.Date;


/**
 * Review of a piece a few details
 *
 * @author Marek Drwal
 * @author Piotr Bereznowski
 * @version 2.3
 */
public abstract class Review implements Cloneable,Comparable {

    /**
     * Author of the review
     */
    protected String reviewer;

    /**
     * The date of providing the review
     */
    protected Date date;

    /**
     * The Piece reviewed
     */
    protected Piece piece;

    /**
     * Instantiates a new Review.
     *
     * @param reviewer author of the review
     * @param date     date
     * @param piece piece reviewed
     */
    protected Review(String reviewer, Date date, Piece piece) {
        this.reviewer = reviewer;
        this.date = date;
        this.piece = piece;
        piece.addReview(this);
    }

    /**
     * Provides textual representation of the object
     * Overrides <i>toString()</i> from <i>Object</i>
     *
     * @return String representing the main contents of the review
     */
    @Override
    public abstract String toString();

    /**
     * Retrieves author of the review
     *
     * @return reviewer
     */
    public String getReviewer() {
        return reviewer;
    }

    /**
     * Sets reviewer
     *
     * @param reviewer author of the review
     */
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    /**
     * Retrieves date of the review
     *
     * @return date of review
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date
     *
     * @param date review date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets piece.
     *
     * @return the piece reviewed
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets piece.
     *
     * @param piece the piece reviewed
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Implementation of <i>clone()</i> method
     *
     * @return cloned Review with own {@link Piece}
     */
    @Override
    public Review clone() {
        try {
            Review clone = (Review) super.clone();
            clone.setPiece(this.getPiece().clone());
            clone.getPiece().getReviews().remove(this);
            clone.getPiece().getReviews().add(clone);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    /**
     * Compares two reviews based on reviewed piece, reviewer name, and date of the review
     *
     * @param o the review to be compared.
     * @return a negative integer, zero, or a positive integer as this review is smaller than, equal to, or greater than the specified review.
     */
    @Override
    public int compareTo(Object o) {
        int pieceDiff = this.piece.getTitle().compareTo(((Review)o).getPiece().getTitle());  // to be changed to compareTo() during merge
        if(pieceDiff != 0) {
            return pieceDiff;
        }
        else {
            int reviewerDiff = this.reviewer.compareTo(((Review)o).getReviewer());
            if(reviewerDiff != 0) {
                return reviewerDiff;
            }
            else {
                return -this.date.compareTo(((Review)o).getDate());
            }
        }
    }
}
