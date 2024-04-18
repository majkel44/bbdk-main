package pl.pg.edu.eti.creators.institutions;

import pl.pg.edu.eti.creators.people.Writer;

import java.util.List;
import java.util.TreeMap;

/**
 * Provides basic information about a book publisher.
 * Book publisher is an institution.
 *
 * @author Piotr Bereznowski
 * @version 3.0
 */
public class BookPublisher extends Institution {

    /**
     * A collection that contains:
     * {@link Writer} as a key representing writer who published with the book publisher
     * {@link Integer} as value representing the number of books published by the writer with the publisher
     */
    private TreeMap<Writer, Integer> writerToNumberOfBooks;

    /**
     * Instantiates a new Book publisher.
     *
     * @param name          name of the publisher
     * @param country       country in which the publisher is registered
     * @param establishedIn the year of establishing the book publisher
     */
    public BookPublisher(String name, String country, Integer establishedIn) {
        super(name, country, establishedIn);
        this.writerToNumberOfBooks = new TreeMap<>();
    }

    /**
     * Retrieves the collection of all writers who published with the book publisher and their book count
     *
     * @return writerToNumberOfBooks
     */
    public TreeMap<Writer, Integer> getWriterToNumberOfBooks() {
        return writerToNumberOfBooks;
    }

    /**
     * Sets collection of writers who published with the book publisher and their book count
     *
     * @param writerToNumberOfBooks map of writers to number of books
     */
    public void setWriterToNumberOfBooks(TreeMap<Writer, Integer> writerToNumberOfBooks) {
        this.writerToNumberOfBooks = writerToNumberOfBooks;
    }

    /**
     * Increases the number of books published by a writer with the book publisher
     *
     * @param writer a writer whose book count needs to be increased
     */
    public void increaseWriterBookCount(Writer writer) {
        if (!writerToNumberOfBooks.containsKey(writer)) {
            writerToNumberOfBooks.put(writer, 1);
        } else {
            writerToNumberOfBooks.put(writer, writerToNumberOfBooks.get(writer) + 1);
        }
    }

    /**
     * Increases the number of books published with the book publisher by a list of writers
     *
     * @param writers a list of writers whose book counts need to be increased
     */
    public void increaseWritersBookCount(List<Writer> writers) {
        for (Writer w : writers) {
            increaseWriterBookCount(w);
        }
    }

    /**
     * Decrease the number of books published by a writer with the book publisher
     *
     * @param writer a writer whose book count needs to be decreased
     */
    public void decreaseWriterBookCount(Writer writer) {
        try {
            if (writerToNumberOfBooks.get(writer).equals(0)) {
                System.out.println("You have already removed all of the books " +
                        "published by this writer with this book publisher");
            } else {
                writerToNumberOfBooks.put(writer, writerToNumberOfBooks.get(writer) - 1);
            }
        } catch (Exception e) {
            System.out.println("This writer has not publish with this book publisher!");
        }
    }

    /**
     * Decrease the number of books published with the book publisher by a list of writers
     *
     * @param writers a list of writers whose book counts need to be decreased
     */
    public void decreaseWritersBookCount(List<Writer> writers) {
        for (Writer w : writers) {
            decreaseWriterBookCount(w);
        }
    }

    /**
     * Gets the most prolific authors of the book publisher
     *
     * @param N number of top entries
     * @return topWriters - a collection of top most prolific authors
     */
    public TreeMap<Integer, Writer> getTopProlificAuthors(Integer N) {
        TreeMap<Integer, Writer> topWriters = new TreeMap<>();

        for (Writer w : writerToNumberOfBooks.keySet()) {
            topWriters.put(writerToNumberOfBooks.get(w), w);

            if (topWriters.size() > N) {
                topWriters.remove(topWriters.firstKey());
            }
        }
        return topWriters;
    }
}
