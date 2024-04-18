package pl.pg.edu.eti.creators.people;

import pl.pg.edu.eti.pieces.Film;
import pl.pg.edu.eti.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides basic information about a screenwriter.
 * Screenwriter is an author.
 *
 * @author Piotr Bereznowski
 * @author Natalia Bielacka
 * @author Michał Kruczkowski
 * @version 3.0
 */
public class Screenwriter extends Author {

    /**
     * Constructs a new Screenwriter object with the specified first name, second name, and last name.
     *
     * @param firstName  The first name of the screenwriter.
     * @param secondName The second name of the screenwriter.
     * @param lastName   The last name of the screenwriter.
     */
    public Screenwriter( String firstName, String secondName, String lastName) {
        super(firstName, secondName, lastName);
    }

    public Screenwriter() {
        super();
    }

    /**
     * Retrieves a list of directors associated with the screenwriters pieces.
     * <p>
     * This method iterates over the list of pieces and retrieves the director for each screenwriter's piece.
     * It then returns a list containing all the unique directors found among the pieces.
     *
     * @return A list of directors associated with the screenwriters pieces.
     * Returns an empty list if no directors are found or if the pieces list is empty.
     */
    public List<Director> getDirectors() {
        List<Director> directors;
        directors = new ArrayList<>();

        for (Piece i : pieces) {
            if (i instanceof Film) {
                directors.add(((Film) i).getDirector());
            }

        }
        return directors;
    }

    /**
     * Returns a string representation of the Screenwriter object, including both the superclass's
     * string representation (Person) and the subclass-specific information.
     *
     * @return A string containing the firstName, secondName, lastName, and occupation of the
     * Screenwriter object.
     */
    @Override
    public String toString() {
        return super.toString() +
                ", occupation = Screenwriter" + '\'' +
                '}';
    }

}
