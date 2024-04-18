package pl.pg.edu.eti.creators.institutions;

import pl.pg.edu.eti.creators.Creator;

/**
 * Provides basic information about an institution.
 * Institution inherits from creator class.
 *
 * @author Piotr Bereznowski
 * @version 1.0
 */
public abstract class Institution extends Creator {

    /**
     * The official name of the institution
     */
    protected String name;

    /**
     * The country in which the institution is registered
     */
    protected String country;

    /**
     * The year of establishing the institution
     */
    protected Integer establishedIn;

    /**
     * Instantiates a new Institution.
     *
     * @param name          name of the institution
     * @param country       country in which the institution is registered
     * @param establishedIn the year of establishing the institution
     */
    Institution(String name, String country, Integer establishedIn) {
        this.name = name;
        this.country = country;
        this.establishedIn = establishedIn;
    }

    /**
     * Retrieves the name of the institution
     *
     * @return name name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the institution
     *
     * @param name institution's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the country in which the institution is registered
     *
     * @return country country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country in which the institution is registered
     *
     * @param country the country in which the institution is registered
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Retrieves the year of establishing the institution
     *
     * @return establishedIn established in
     */
    public Integer getEstablishedIn() {
        return establishedIn;
    }

    /**
     * Sets the year of establishing the institution
     *
     * @param establishedIn the year of establishing the institution
     */
    public void setEstablishedIn(Integer establishedIn) {
        this.establishedIn = establishedIn;
    }

    /**
     * Provides textual representation of the institution
     * Overrides <i>toString()</i> from <i>Object</i>
     *
     * @return String representing the main contents of the institution
     */
    @Override
    public String toString() {
        return "Institution [name: " + name + ", established in " + establishedIn + " in " + country + "]";
    }
}
