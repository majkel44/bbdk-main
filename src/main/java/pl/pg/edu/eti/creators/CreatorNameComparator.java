package pl.pg.edu.eti.creators;

import pl.pg.edu.eti.creators.institutions.Institution;
import pl.pg.edu.eti.creators.people.Person;

import java.util.Comparator;

/**
 * Allows for sorting creators alphabetically
 *
 * @author Michał Kruczkowski
 * @version 1.0
 */
public class CreatorNameComparator implements Comparator<Creator> {
    /**
     * Compares two creators
     *
     * @param c1 the first creator to be compared.
     * @param c2 the second creator to be compared.
     */
    @Override
    public int compare(Creator c1, Creator c2){
        if (c1 instanceof Person){
            if (c2 instanceof Person)
                return ((Person) c1).getFullName().compareTo(((Person) c2).getFullName());
            else if (c2 instanceof Institution)
                return ((Person) c1).getFullName().compareTo(((Institution) c2).getName());
        } else if (c1 instanceof Institution) {
            if (c2 instanceof Person)
                return ((Institution) c1).getName().compareTo(((Person) c2).getFullName());
            else if (c2 instanceof Institution)
                return ((Institution) c1).getName().compareTo(((Institution) c2).getName());
        }
        return 0;
    }
}
