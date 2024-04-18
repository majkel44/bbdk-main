package pl.pg.edu.eti.creators.people;

import pl.pg.edu.eti.pieces.Film;
import pl.pg.edu.eti.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Provides basic information about an actor.
 * Actor is a person.
 *
 * @author Piotr Bereznowski
 * @author Natalia Bielacka
 * @author Michał Kruczkowski
 * @version 3.0
 */
public class Actor extends Person {

    /**
     * Constructs a new Actor object with the specified first name, second name, and last name.
     *
     * @param firstName  The first name of the actor.
     * @param secondName The second name of the actor.
     * @param lastName   The last name of the actor.
     */
    public Actor(String firstName, String secondName, String lastName) {
        super(firstName, secondName, lastName);
    }

    public Actor() {
        super();
    }


    /**
     * Retrieves a list of actors associated with the actors pieces.
     * <p>
     * This method iterates over the list of pieces and retrieves the actors for each actor's piece.
     * It then returns a list containing all the actors found among the pieces.
     *
     * @return A list of actors associated with the actors pieces.
     * Returns an empty list if no directors are found or if the pieces list is empty.
     */
    public List<Actor> getCoActors() {
        List<Actor> actors;
        actors = new ArrayList<>();

        for (Piece i : pieces) {

            if (i instanceof Film) {
                for (Actor j : ((Film) i).getActors()) {
                    if (!Objects.equals(j.getFullName(), this.getFullName())) {
                        actors.add(j);
                    }
                }
            }
        }

        return actors;
    }

    /**
     * Retrieves a list of directors associated with the actors pieces.
     * <p>
     * This method iterates over the list of pieces and retrieves the director for each actor's piece.
     * It then returns a list containing all the unique directors found among the pieces.
     *
     * @return A list of directors associated with the actors pieces.
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
     * Returns a string representation of the Actor object, including both the superclass's
     * string representation (Person) and the subclass-specific information.
     *
     * @return A string containing the firstName, secondName, lastName, and occupation of the
     * Actor object.
     */
    @Override
    public String toString() {
        return super.toString() +
                ", occupation = Actor" + '\'' +
                '}';
    }

}
