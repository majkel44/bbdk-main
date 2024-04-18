package pl.pg.edu.eti;

import pl.pg.edu.eti.pieces.Book;
import pl.pg.edu.eti.pieces.Film;
import pl.pg.edu.eti.pieces.Game;
import pl.pg.edu.eti.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides information on the genre, kind, type, etc. a piece may belong to
 *
 * @author Marek Drwal
 * @author Natalia Bielacka
 * @version 2.0
 */
public class Category {

    /**
     * Constant representing a type of pieces
     */
    public static final int BOOK = 0;

    /**
     * Constant representing a type of pieces
     */
    public static final int FILM = 1;

    /**
     * Constant representing a type of pieces
     */
    public static final int GAME = 2;

    /**
     * Constant representing number of types of pieces
     */
    public static final int NO_PIECE_TYPES = 3;

    /**
     * Name of the category
     */
    private String name;

    /**
     * Pieces in the category
     */
    private List<Piece> pieces;

    /**
     * Category constructor
     *
     * @param name text for the category to be named with
     */
    public Category(String name) {
        this.name = name;
        pieces = new ArrayList<Piece>();
    }

    /**
     * Retrieves the name of the category
     *
     * @return name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category
     *
     * @param name text for the category to be named with
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves all pieces in a category
     *
     * @return pieces belonging to the category
     */
    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * Assigns given list as pieces in the category
     *
     * @param pieces new list of pieces
     */
    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    /**
     * Adds a single piece to the set of pieces in the category
     *
     * @param piece the piece to be added
     */
    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    /**
     * Removes a single piece from the set of pieces in the category
     *
     * @param piece the piece to be removed
     */
    public void removePiece(Piece piece) {
        this.pieces.remove(piece);
    }

    /**
     * A helper function subsetting pieces in the category by the given type <br/>
     * Utilises <i>switch</i> and polymorphism
     *
     * @param type type of pieces: <i>BOOK</i>, <i>FILM</i> or <i>GAME</i> (int value)
     * @return a list of pieces in the category of the given type; if no pieces found, an empty list
     */
    private List<Piece> getPiecesOfType(int type) {
        return switch (type) {
            case BOOK -> pieces.stream().filter(piece -> piece instanceof Book).toList();
            case FILM -> pieces.stream().filter(piece -> piece instanceof Film).toList();
            case GAME -> pieces.stream().filter(piece -> piece instanceof Game).toList();
            default -> new ArrayList<>();
        };
    }

    /**
     * Retrieves books in the category. <br/>
     * Entry point for polymorphism usage
     *
     * @return list of books in the category
     */
    public List<Piece> getBooks() {
        return getPiecesOfType(PieceType.BOOK.value);
    }

    /**
     * Retrieves films in the category. <br/>
     * Entry point for polymorphism usage
     *
     * @return list of films in the category
     */
    public List<Piece> getFilms() {
        return getPiecesOfType(FILM);
    }

    /**
     * Retrieves games in the category. <br/>
     * Entry point for polymorphism usage
     *
     * @return list of games in the category
     */
    public List<Piece> getGames() {
        return getPiecesOfType(GAME);
    }

    /**
     * Prints number of books, films and games in the category
     */
    public void summariseByPieces() {
        int[] cnt = new int[NO_PIECE_TYPES];
        for (int i = 0; i < NO_PIECE_TYPES; i++) {
            cnt[i] = getPiecesOfType(i).size();
        }
        System.out.println("Counting pieces in the category by type");
        System.out.println("Books: " + cnt[PieceType.BOOK.value]);
        System.out.println("Films: " + cnt[PieceType.FILM.value]);
        System.out.println("Games: " + cnt[PieceType.GAME.value]);
    }

    /**
     * Returns a string representation of the Category object.
     *
     * @return A string containing the name of the Category object.
     */
    @Override
    public String toString() {
        return "Category{" +
                "name='" + name +
                '}';
    }
}
