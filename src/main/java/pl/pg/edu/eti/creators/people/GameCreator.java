package pl.pg.edu.eti.creators.people;

import pl.pg.edu.eti.pieces.Piece;

import java.util.List;

/**
 * Provides basic information about a game creator.
 * GameCreator is an author.
 *
 * @author Natalia Bielacka
 * @author Michał Kruczkowski
 * @version 3.0
 */
public class GameCreator extends Author {

    /**
     * Constructs a new GameCreator object with the specified first name, second name, and last name.
     *
     * @param firstName  The first name of the game creator.
     * @param secondName The second name of the game creator.
     * @param lastName   The last name of the game creator.
     */
    public GameCreator(String firstName, String secondName, String lastName) {
        super(firstName, secondName, lastName);
    }

    /**
     * Returns a string representation of the GameCreator object, including both the superclass's
     * string representation (Person) and the subclass-specific information.
     *
     * @return A string containing the firstName, secondName, lastName, and occupation of the
     * GameCreator object.
     */
    @Override
    public String toString() {
        return super.toString() +
                ", occupation = GameCreator" + '\'' +
                '}';
    }
}
