package pl.pg.edu.eti.creators.people;

import pl.pg.edu.eti.pieces.Film;
import pl.pg.edu.eti.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides basic information about a director.
 * Director is an author.
 *
 * @author Piotr Bereznowski
 * @author Natalia Bielacka
 * @author Michał Kruczkowski
 * @version 3.0
 */
public class Director extends Author {


    /**
     * Constructs a new Director object with the specified first name, second name, and last name.
     *
     * @param firstName  The first name of the director.
     * @param secondName The second name of the director.
     * @param lastName   The last name of the director.
     */
    public Director( String firstName, String secondName, String lastName) {
        super(firstName, secondName, lastName);
    }

    public Director() {
        super();
    }

    /**
     * Retrieves a list of screenwriters associated with the directors pieces.
     * <p>
     * This method iterates over the list of pieces and retrieves the screenwriter for each director's piece.
     * It then returns a list containing all the unique screenwriters found among the pieces.
     *
     * @return A list of screenwriters associated with the directors pieces.
     * Returns an empty list if no screenwriters are found or if the pieces list is empty.
     */
    public List<Screenwriter> getScreenwiters() {

        List<Screenwriter> screenwriters;
        screenwriters = new ArrayList<>();

        for (Piece i : pieces) {
            if (i instanceof Film) {
                screenwriters.add(((Film) i).getScreenwriter());
            }
        }
        return screenwriters;
    }

    /**
     * Returns a string representation of the Director object, including both the superclass's
     * string representation (Person) and the subclass-specific information.
     *
     * @return A string containing the firstName, secondName, lastName, and occupation of the
     * Director object.
     */
    @Override
    public String toString() {
        return super.toString() +
                ", occupation = Director" + '\'' +
                '}';
    }
}
