package pl.pg.edu.eti;

/**
 * The enum Piece type.
 */
public enum PieceType {

    /**
     * Book piece type.
     */
    BOOK(0),
    /**
     * Film piece type.
     */
    FILM(1),
    /**
     * Game piece type.
     */
    GAME(2);

    /**
     * The Value.
     */
    int value;

    private PieceType(int value) {
        this.value = value;
    }
}
