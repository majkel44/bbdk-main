package pl.pg.edu.eti.pieces;

import pl.pg.edu.eti.Category;
import pl.pg.edu.eti.creators.people.Actor;
import pl.pg.edu.eti.reviews.Review;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Provides basic information about a media piece.
 *
 * @author Michał Kruczkowski
 * @author Natalia Bielacka
 * @author Marek Drwal
 * @author Piotr Bereznowski
 * @version 3.3
 */
public abstract class Piece implements Cloneable,Comparable<Piece>{

    /**
     * Value for calculating rating - single review
     */
    protected static final double SINGLE_REVIEW = 1.0;

    /**
     * Value for calculating rating - lack of reviews
     */
    protected static final double NO_REVIEWS = -1.0;
    /**
     * Value for calculating similarities by creators representing weight of having same director
     */
    protected static final int DIRECTOR_WEIGHT = 50;
    /**
     * Value for calculating similarities by creators, representing weight of having same screenwriter
     */
    protected static final int SCREENWRITER_WEIGHT = 15;
    /**
     * Value for calculating similarities by creators, representing weight of having same actors
     */
    protected static final int ACTORS_WEIGHT = 25;
    /**
     * Value for calculating similarities by creators, representing weight of having same film studio
     */
    protected static final int FILM_STUDIO_WEIGHT = 10;
    /**
     * Value for calculating similarities by creators, representing weight of having same writer
     */
    protected static final int WRITER_WEIGHT = 90;
    /**
     * Value for calculating similarities by creators, representing weight of having same book publisher
     */
    protected static final int BOOK_PUBLISHER_WEIGHT = 10;
    /**
     * Value for calculating similarities by creators, representing weight of having same game creator
     */
    protected static final int GAME_CREATOR_WEIGHT = 70;
    /**
     * Value for calculating similarities by creators, representing weight of having same game studio
     */
    protected static final int GAME_STUDIO_WEIGHT = 30;
    /**
     * Value for calculating similarities by creators, representing weight multiplier if compared value is null
     */
    protected static final double IF_NULL_WEIGHT = 0.05;
    /**
     * Value for comparing pieces representing weight of categories similarities
     */
    protected static final double CATEGORIES_WEIGHT = 0.65;
    /**
     * Value for comparing pieces representing weight of creators similarities
     */
    protected static final double CREATORS_WEIGHT = 0.35;
    /**
     * Title of the piece.
     */
    protected String title;

    /**
     * Genres which describe the piece.
     */
    protected List<Category> categories;

    /**
     * Piece rating.
     */
    protected double rating;

    /**
     * Date at which the piece was released
     */
    protected Date releaseDate;
    /**
     * List of reviews of this piece.
     */
    protected List<Review> reviews;

    /**
     * ID of the piece.
     */
    protected String id;


    /**
     * Piece class constructor.
     *
     * @param reviews the reviews
     * @param title   the title
     * @param id      the id
     */
    public Piece(List<Review> reviews, String title, String id) {
        this.reviews = reviews;
        this.title = title;
        this.id = id;
        rating = calculateRating();
    }

    /**
     * Retrieves rating of the piece
     *
     * @return rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Retrieves the date of release of a piece.
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
     * Changes the rating
     *
     * @param rating the rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Retrieves title of the piece
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Changes title of the piece
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves ID of the piece
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Changes the ID
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves genres of the piece
     *
     * @return categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Changes the categories
     *
     * @param categories the categories
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Changes the reviews
     *
     * @param reviews the reviews
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Calculates rating of the piece based on its reviews
     *
     * @return rating
     */
    protected abstract double calculateRating();

    /**
     * Updates rating of the piece
     */
    public void updateRating() {
        rating = calculateRating();
    }

    /**
     * Adds another review to the reviews of the piece. Recalculates its rating
     *
     * @param review the review
     */
    public void addReview(Review review) {
        reviews.add(review);
        updateRating();
    }

    /**
     * Visualization of an object derived from a piece class, which could not run its own toString method.
     */
    public String toString() {
        return "Piece{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", id='" + id + '\'' +
                '}';
    }

    /**
     * Retrieves the reviews
     *
     * @return reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Compares this piece to another piece based on category similarity and creators similarity.
     *
     * @param otherPiece The other piece to compare to.
     * @return A negative integer, zero, or a positive integer as this piece is less than, equal to, or greater than the other piece.
     */
    @Override
    public int compareTo(Piece otherPiece) {
        if(this.categories==null || otherPiece.categories==null){
            if (this.categories==null && otherPiece.categories!=null){
                return -(int) (CREATORS_WEIGHT*creatorsSimilarity(otherPiece));
            } else if (this.categories!=null && otherPiece.categories==null) {
                return (int) (CREATORS_WEIGHT*creatorsSimilarity(otherPiece));
            }else if(this.rating>otherPiece.rating) {
                return (int) (CREATORS_WEIGHT*creatorsSimilarity(otherPiece));
            }else return -(int) (CREATORS_WEIGHT*creatorsSimilarity(otherPiece));

        }

        if(this.categories.size()>otherPiece.categories.size()){
            return (int) (CATEGORIES_WEIGHT*categoriesSimilarity(otherPiece) + CREATORS_WEIGHT*creatorsSimilarity(otherPiece));
        } else if (this.categories.size()<otherPiece.categories.size()) {
            return -(int) (CATEGORIES_WEIGHT*categoriesSimilarity(otherPiece) + CREATORS_WEIGHT*creatorsSimilarity(otherPiece));
        }else{
            if (categoriesSimilarity(otherPiece)==0 && creatorsSimilarity(otherPiece)==0) {
                return 0;
            }
            else if(this.rating>otherPiece.rating){ //conditions if pieces have same number of categories
                return (int) (CATEGORIES_WEIGHT*categoriesSimilarity(otherPiece) + CREATORS_WEIGHT*creatorsSimilarity(otherPiece));
            }
            else return -(int) (CATEGORIES_WEIGHT*categoriesSimilarity(otherPiece) + CREATORS_WEIGHT*creatorsSimilarity(otherPiece));
        }
    }

    /**
     * Calculates the similarity between this piece and another piece based on their categories.
     *
     * @param otherPiece The other piece to compare to.
     * @return The similarity score between 0 and 100. Where 0 is most alike.
     */
    public int categoriesSimilarity(Piece otherPiece) {

        if(this.categories == null || otherPiece.categories==null){
            return 100;
        }else {
            Set<Category> thisCategories = new HashSet<>(this.categories);
            Set<Category> otherCategories = new HashSet<>(otherPiece.categories);

            Set<Category> intersection = new HashSet<>(thisCategories);
            intersection.retainAll(otherCategories);

            Set<Category> union = new HashSet<>(thisCategories);
            union.addAll(otherCategories);

            double similarity = (double) intersection.size() / union.size();

            return 100 - (int) Math.round(similarity * 100);
        }
    }



    /**
     * Calculates the similarity between this piece and another piece based on their creators.
     *
     * @param otherPiece The other piece to compare to.
     * @return The similarity score between 0 and 100. Where 0 is most alike.
     */
    public int creatorsSimilarity(Piece otherPiece){
        int similarity=100;
        if(this instanceof Film && otherPiece instanceof Film){
            if (((Film) this).getDirector() == ((Film) otherPiece).getDirector()){
                if (((Film) this).getDirector() == null || ((Film) otherPiece).getDirector() == null) {
                    similarity-= (int) (IF_NULL_WEIGHT*DIRECTOR_WEIGHT);
                }else similarity-=DIRECTOR_WEIGHT;
            }
            if (((Film) this).getScreenwriter() == ((Film) otherPiece).getScreenwriter()) {
                if (((Film) this).getScreenwriter() == null || ((Film) otherPiece).getScreenwriter() == null) {
                    similarity-= (int) (IF_NULL_WEIGHT*SCREENWRITER_WEIGHT);
                }else similarity-=SCREENWRITER_WEIGHT;
            }
            if (((Film) this).getFilmStudio() == ((Film) otherPiece).getFilmStudio()) {
                if (((Film) this).getFilmStudio() == null || ((Film) otherPiece).getFilmStudio() == null) {
                    similarity-= (int) (IF_NULL_WEIGHT*FILM_STUDIO_WEIGHT);
                }else similarity-=FILM_STUDIO_WEIGHT;
            }
            if (((Film) this).getActors() == null || ((Film) otherPiece).getActors() == null) {
                similarity-= (int) (IF_NULL_WEIGHT*ACTORS_WEIGHT);
            }else {
                int actorNumber = 0;
                for (Actor a : ((Film) this).getActors()) {
                    for (Actor b : ((Film) otherPiece).getActors()) {
                        if (b.equals(a)) {
                            actorNumber++;
                            break;
                        }
                    }
                }
                switch (actorNumber) {
                    case 0:
                        break;
                    case 1:
                        similarity -= (int) (ACTORS_WEIGHT * 0.25);
                    case 2:
                        similarity -= (int) (ACTORS_WEIGHT * 0.5); // exponential decrease
                        break;
                    default: // 3 or more
                        similarity -= ACTORS_WEIGHT;
                        break;
                }
            }


        } else if (this instanceof Book && otherPiece instanceof Book) {
            if (((Book) this).getWriter() == ((Book) otherPiece).getWriter()){
                if (((Book) this).getWriter() == null || ((Book) otherPiece).getWriter() == null) {
                    similarity-= (int) (IF_NULL_WEIGHT*WRITER_WEIGHT);
                }else similarity-=WRITER_WEIGHT;
            }
            if (((Book) this).getBookPublisher() == ((Book) otherPiece).getBookPublisher()){
                if (((Book) this).getBookPublisher() == null || ((Book) otherPiece).getBookPublisher() == null) {
                    similarity-= (int) (IF_NULL_WEIGHT*BOOK_PUBLISHER_WEIGHT);
                }else similarity-=BOOK_PUBLISHER_WEIGHT;
            }
        }else if(this instanceof Game && otherPiece instanceof Game){
            if (((Game) this).getGameStudio() == ((Game) otherPiece).getGameStudio()) {
                if (((Game) this).getGameStudio() == null || ((Game) otherPiece).getGameStudio() == null) {
                    similarity-= (int) (IF_NULL_WEIGHT*GAME_STUDIO_WEIGHT);
                }else similarity-=GAME_STUDIO_WEIGHT;
            }
            if (((Game) this).getCreator() == ((Game) otherPiece).getCreator()){
                if (((Game) this).getCreator() == null || ((Game) otherPiece).getCreator() == null) {
                    similarity-= (int) (IF_NULL_WEIGHT*GAME_CREATOR_WEIGHT);
                }else similarity-=GAME_CREATOR_WEIGHT;
            }
        }
        return similarity;
    }

    /**
     * Implementation of <i>clone()</i> method
     *
     * @return a cloned {@link Piece}
     */
    @Override
    public Piece clone() {
        try {
            Piece clone = (Piece) super.clone();
            // let the cloned piece have own list of reviews, prefilled with currently available reviews; leave other fields
            clone.setReviews(new ArrayList<>(reviews));
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
