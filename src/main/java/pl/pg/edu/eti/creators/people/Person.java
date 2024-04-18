package pl.pg.edu.eti.creators.people;


import pl.pg.edu.eti.creators.Creator;
import pl.pg.edu.eti.pieces.Piece;

import java.util.List;


/**
 * Provides basic information about a person.
 * Person inherits from Creator class
 *
 * @author Marek Drwal
 * @author Natalia Bielacka
 * @author Michał Kruczkowski
 * @version 3.0
 */
public abstract class Person extends Creator {

    /**
     * First name of the person
     */
    protected String firstName;

    /**
     * Second name of the person
     */
    protected String secondName;

    /**
     * Last name of the person
     */
    protected String lastName;

    public Person() {
        super();
    }

    /**
     * Constructs a new Person object with the specified first name, second name, and last name.
     *
     * @param firstName  The first name of the person.
     * @param secondName The second name of the person.
     * @param lastName   The last name of the person.
     */
    public Person( String firstName, String secondName, String lastName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }
    /**
     * Retrieves first name of the person
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves second name of the person
     *
     * @return second name
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Retrieves last name of the person
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves full name of the person
     *
     * @return full name
     */
    public String getFullName() {
        return firstName + " " + secondName + " " + lastName;
    }

    /**
     * Returns a string representation of the Person object.
     *
     * @return A string containing the firstName, secondName, and lastName of the Person object.
     */
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
