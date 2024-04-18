package pl.pg.edu.eti.creators.people;

import pl.pg.edu.eti.creators.institutions.BookPublisher;
import pl.pg.edu.eti.pieces.Book;
import pl.pg.edu.eti.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides basic information about a writer.
 * Writer is an author.
 *
 * @author Natalia Bielacka
 * @author Michał Kruczkowski
 * @author Piotr Bereznowski
 * @version 3.1
 */
public class Writer extends Author {

    /**
     * Constructs a new Writer object with the specified first name, second name, and last name.
     *
     * @param firstName  The first name of the writer.
     * @param secondName The second name of the writer.
     * @param lastName   The last name of the writer.
     */
    public Writer( String firstName, String secondName, String lastName) {
        super(firstName, secondName, lastName);
    }

    /**
     * Retrieves a list of publishers associated with the writers pieces.
     * <p>
     * This method iterates over the list of pieces and retrieves the publisher for each writer's piece.
     * It then returns a list containing all the unique publishers found among the pieces.
     *
     * @return A list of publishers associated with the writers pieces.
     * Returns an empty list if no publishers are found or if the pieces list is empty.
     */
    public List<BookPublisher> getBookPublishers() {
        List<BookPublisher> bookPublishers;
        bookPublishers = new ArrayList<>();

        for (Piece i : pieces) {
            if (i instanceof Book) {
                bookPublishers.add(((Book) i).getBookPublisher());
            }
        }
        return bookPublishers;
    }

    /**
     * Retrieves the publisher of the most recently published book among the pieces.
     *
     * @return The publisher of the most recently published book among the pieces.
     * Returns null if no books are found or if the pieces list is empty.
     */
    public BookPublisher recentPublisher() {

        int publishedYear = 0;
        BookPublisher recentPublisher = null;

        for (Piece i : pieces) {
            if (i instanceof Book) {
                if (((Book) i).getYear() > publishedYear) {
                    recentPublisher = ((Book) i).getBookPublisher();
                    publishedYear = ((Book) i).getYear();
                }
            }
        }

        return recentPublisher;
    }

    /**
     * Returns a string representation of the Writer object, including both the superclass's
     * string representation (Person) and the subclass-specific information.
     *
     * @return A string containing the firstName, secondName, lastName, and occupation of the
     * Writer object.
     */
    @Override
    public String toString() {
        return super.toString() +
                ", occupation = Writer" + '\'' +
                ", recent publisher='" + this.recentPublisher() + '\'' +
                '}';
    }


}
