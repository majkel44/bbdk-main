package pl.pg.edu.eti.creators.people;

import pl.pg.edu.eti.Category;
import pl.pg.edu.eti.pieces.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides basic information about an author.
 * Author is a person
 *
 * @author Marek Drwal
 * @author Natalia Bielacka
 * @author Michał Kruczkowski
 * @version 3.0
 */
public abstract class Author extends Person {

    /**
     * Constructs a new Author object with the specified first name, second name, and last name.
     *
     * @param firstName  The first name of the author.
     * @param secondName The second name of the author.
     * @param lastName   The last name of the author.
     */
    public Author( String firstName, String secondName, String lastName) {
        super(firstName, secondName, lastName);
    }

    public Author() {

    }

    /**
     * Finds and returns the best rated Piece among authors list of pieces.
     *
     * @return The Piece object with the highest rating. Returns null if the list is empty.
     */
    public Piece bestPiece() {

        double rating = 0;
        Piece bestPiece = null;

        for (Piece i : pieces) {
            if (i.getRating() > rating) {
                bestPiece = i;
                rating = i.getRating();
            }
        }

        return bestPiece;
    }

    /**
     * Finds and returns the most common genre among the authors pieces.
     * <p>
     * This method iterates over the list of pieces and counts occurrences of each genre.
     * It then returns the genre with the highest occurrence count. If multiple genres have
     * the same highest occurrence count, it returns the first one encountered in the iteration.
     *
     * @return The most common genre among the authors pieces. Returns null if the list is empty.
     */
    Category mostCommonGenre() {

        Map<Category, Integer> occurrenceMap = new HashMap<>();
        for (int i = 0; i < pieces.size(); i++) {
            for (Category j : pieces.get(i).getCategories()) {
                if (occurrenceMap.containsKey(j)) {
                    occurrenceMap.put(j, occurrenceMap.get(j) + 1);
                } else {
                    occurrenceMap.put(j, 1);
                }
            }
        }

        int maxOccurrences = 0;
        Category maxCategory = null;

        for (Map.Entry<Category, Integer> entry : occurrenceMap.entrySet()) {
            if (entry.getValue() > maxOccurrences) {
                maxCategory = entry.getKey();
                maxOccurrences = entry.getValue();
            }
        }

        return maxCategory;

    }
}
