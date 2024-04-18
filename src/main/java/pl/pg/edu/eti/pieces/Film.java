package pl.pg.edu.eti.pieces;


import pl.pg.edu.eti.creators.institutions.FilmStudio;
import pl.pg.edu.eti.creators.people.Actor;
import pl.pg.edu.eti.creators.people.Director;
import pl.pg.edu.eti.creators.people.Screenwriter;
import pl.pg.edu.eti.reviews.FilmReview;
import pl.pg.edu.eti.reviews.Review;

import java.util.Date;
import java.util.List;

/**
 * Provides basic information about a film.
 *
 * @author Michał Kruczkowski
 * @author Piotr Bereznowski
 * @author Natalia Bielacka
 * @version 3.1
 */
public class Film extends Piece {

    /**
     * A list of actors who played in the movie.
     * array - because the number of actors is fixed
     */
    private Actor[] actors;
    /**
     * A director of a movie
     */
    private Director director;
    /**
     * A screenwriter of a movie
     */
    private Screenwriter screenwriter;

    /**
     * A film studio of a movie
     */
    private FilmStudio filmStudio;
    /**
     * Release date of the film
     */
    private Date releaseDate;
    /**
     * Countries in which the film was made
     * array - because the number of countries of production is fixed
     */
    private String[] productionCountries;

    /**
     * Film class constructor without additional fields provided.
     *
     * @param reviews the reviews
     * @param title   the title
     * @param id      the id
     */
    public Film(List<Review> reviews, String title, String id) {
        super(reviews, title, id);
    }

    /**
     * Film class constructor with additional fields provided.
     *
     * @param reviews             the reviews
     * @param title               the title
     * @param id                  the id
     * @param actors              the actors
     * @param director            the director
     * @param screenwriter        the screenwriter
     * @param releaseDate         the release date
     * @param productionCountries the production countries
     */
    public Film(List<Review> reviews, String title, String id, Actor[] actors, Director director, Screenwriter screenwriter, Date releaseDate, String[] productionCountries) {
        super(reviews, title, id);
        this.actors = actors;
        this.director = director;
        this.screenwriter = screenwriter;
        this.releaseDate = releaseDate;
        this.productionCountries = productionCountries;
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
                value += ((FilmReview) review).getRating();
                quantity += SINGLE_REVIEW;
            }
            return value / quantity;
        } catch (NullPointerException e) {
            return NO_REVIEWS;
        }
    }

    /**
     * Retrieves the release date of a film.
     *
     * @return releaseDate
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Changes the releaseDate
     *
     * @param releaseDate the release date
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Retrieves the countries of production of a film.
     *
     * @return productionCountries
     */
    public String[] getProductionCountries() {
        return productionCountries;
    }

    /**
     * Changes the productionCountries
     *
     * @param productionCountries the production countries
     */
    public void setProductionCountries(String[] productionCountries) {
        this.productionCountries = productionCountries;
    }

    /**
     * Retrieves the actors of a film.
     *
     * @return actors
     */
    public Actor[] getActors() {
        return actors;
    }

    /**
     * Changes the actors
     *
     * @param actors the actors
     */
    public void setActors(Actor[] actors) {
        this.actors = actors;
    }

    /**
     * Retrieves the director of a film.
     *
     * @return director
     */
    public Director getDirector() {
        return director;
    }

    /**
     * Changes the director
     *
     * @param director the director
     */
    public void setDirector(Director director) {
        this.director = director;
    }

    /**
     * Retrieves the screenwriter of a film.
     *
     * @return screenwriter
     */
    public Screenwriter getScreenwriter() {
        return screenwriter;
    }

    /**
     * Changes the screenwriter
     *
     * @param screenwriter the screenwriter
     */
    public void setScreenwriter(Screenwriter screenwriter) {
        this.screenwriter = screenwriter;
    }
    /**
     * Retrieves the film studio of a film.
     *
     * @return filmStudio
     */
    public FilmStudio getFilmStudio() {
        return filmStudio;
    }
    /**
     * Changes the film studio
     *
     * @param filmStudio the film studio
     */
    public void setFilmStudio(FilmStudio filmStudio) {
        this.filmStudio = filmStudio;
    }

    /**
     * Visualization of a Film class object.
     */
    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                ", director=" + director +
                ", screenwriter=" + screenwriter +
                ", id='" + id + '\'' +
                '}';
    }
}
