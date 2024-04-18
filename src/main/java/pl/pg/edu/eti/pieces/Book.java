package pl.pg.edu.eti.pieces;

import pl.pg.edu.eti.creators.institutions.BookPublisher;
import pl.pg.edu.eti.creators.people.Writer;
import pl.pg.edu.eti.reviews.BookReview;
import pl.pg.edu.eti.reviews.Review;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Provides basic information about a book.
 *
 * @author Michał Kruczkowski
 * @author Piotr Bereznowski
 * @version 2.6
 */
public class Book extends Piece {


    /**
     * An author of a book
     */
    private Writer writer;
    /**
     * Publisher of a book
     */
    private BookPublisher bookPublisher;
    /**
     * Year of release
     */
    private Integer year;
    /**
     * Month of release
     */
    private Integer month;
    /**
     * Day of release
     */

    private Integer day;

    /**
     * Number of pages in the book
     */
    private Integer numberOfPages;

    /**
     * Book class constructor without additional fields provided.
     *
     * @param reviews the reviews
     * @param title   the title
     * @param id      the id
     */
    public Book(List<Review> reviews, String title, String id) {
        super(reviews, title, id);
    }

    /**
     * Book class constructor with additional fields provided.
     *
     * @param reviews       the reviews
     * @param title         the title
     * @param id            the id
     * @param bookPublisher the book publisher
     * @param writer        the writer
     * @param year          the year
     * @param month         the month
     * @param day           the day
     * @param numberOfPages the number of pages
     */
    public Book(List<Review> reviews, String title, String id, BookPublisher bookPublisher, Writer writer, Integer year, Integer month, Integer day, Integer numberOfPages) {
        super(reviews, title, id);
        this.bookPublisher = bookPublisher;
        this.writer = writer;
        this.year = year;
        this.month = month;
        this.day = day;
        this.releaseDate = dateParse();
        this.numberOfPages = numberOfPages;
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
                value += ((BookReview) review).getRating();
                quantity += SINGLE_REVIEW;
            }
            return value / quantity;
        } catch (NullPointerException e) {
            return NO_REVIEWS;
        }
    }

    /**
     * Combines the 3 fields: "year", "month" and "day" into a single field "releaseDate"
     *
     * @return releaseDate
     */
    private Date dateParse() {
        if (year != null && month != null && day != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day); // due to the Calendar format one must be subtracted from the month
            Date date = calendar.getTime();
            return date;
        } else
            return null;
    }

    /**
     * Retrieves the writer of a book.
     *
     * @return writer
     */
    public Writer getWriter() {
        return writer;
    }

    /**
     * Changes the writer
     *
     * @param writer the writer
     */
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    /**
     * Retrieves the publisher of a book.
     *
     * @return bookPublisher
     */
    public BookPublisher getBookPublisher() {
        return bookPublisher;
    }

    /**
     * Changes the publisher
     *
     * @param bookPublisher the book publisher
     */
    public void setBookPublisher(BookPublisher bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    /**
     * Retrieves the year of release of a book.
     *
     * @return year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Changes the year
     *
     * @param year the year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Retrieves the month of release of a book.
     *
     * @return month
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * Changes the month
     *
     * @param month the month
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * Retrieves the day of release of a book.
     *
     * @return day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * Changes the day
     *
     * @param day the day
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * Retrieves the number of pages of a book.
     *
     * @return numberOfPages
     */
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Changes the numberOfPages
     *
     * @param numberOfPages the number of pages
     */
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * Visualization of a Book class object.
     */
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", release date=" + releaseDate +
                ", writer=" + writer +
                ", publisher=" + bookPublisher +
                ", release year=" + year +
                ", id='" + id + '\'' +
                '}';
    }
}
