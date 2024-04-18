package pl.pg.edu.eti.creators.people;

import java.util.Comparator;

/**
 * Allows for sorting people alphabetically
 *
 * @author Michał Kruczkowski
 * @version 1.0
 */
public class PersonNameComparator implements Comparator<Person> {
    /**
     * Compares two people
     *
     * @param p1 the first person to be compared.
     * @param p2 the second person to be compared.
     */
    @Override
    public int compare(Person p1, Person p2){
        return p1.getFullName().compareTo(p2.getFullName());
    }
}
