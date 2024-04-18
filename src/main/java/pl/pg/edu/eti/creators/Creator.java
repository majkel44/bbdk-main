package pl.pg.edu.eti.creators;

import pl.pg.edu.eti.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides basic information about a creator.
 *
 * @author Piotr Bereznowski
 * @author Natalia Bielacka
 * @version 3.0
 */
public abstract class Creator {
    /**
     * List of all pieces created by the creator
     */
    protected List<Piece> pieces;

    /**
     * Default constructor of the creator class
     */
    public Creator(){
        this.pieces = new ArrayList<>();
    }

    /**
     * Constructs a new Creator object with the specified list of pieces associated with the creator.
     *
     * @param pieces The list of pieces associated with the creator.
     */
    public Creator(List<Piece> pieces) {
        this.pieces = pieces;
    }

    /**
     * Retrieves pieces authored by the creator
     *
     * @return pieces
     */
    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * Overwrites pieces authored by the creator
     *
     * @param pieces the pieces
     */
    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    /**
     * Adds a single piece to the collection of pieces authored by the creator
     *
     * @param piece a piece to be added
     */
    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    /**
     * Removes a single piece from the collection of pieces authored by the creator
     *
     * @param piece a piece to be removed
     */
    public void removePiece(Piece piece) {
        this.pieces.remove(piece);
    }
}
