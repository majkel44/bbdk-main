package pl.pg.edu.eti.creators.institutions;

import pl.pg.edu.eti.creators.people.Person;

import java.util.Comparator;

/**
 * Allows for sorting institutions alphabetically
 *
 * @author Michał Kruczkowski
 * @version 1.0
 */
public class InstitutionNameComparator implements Comparator<Institution> {
    /**
     * Compares two institutions
     *
     * @param i1 the first institution to be compared.
     * @param i2 the second institution to be compared.
     */
    @Override
    public int compare(Institution i1, Institution i2){
        return i1.getName().compareTo(i2.getName());
    }
}
