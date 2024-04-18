package pl.pg.edu.eti;

import pl.pg.edu.eti.creators.Creator;
import pl.pg.edu.eti.creators.CreatorNameComparator;
import pl.pg.edu.eti.creators.institutions.BookPublisher;
import pl.pg.edu.eti.creators.institutions.FilmStudio;
import pl.pg.edu.eti.creators.institutions.GameStudio;
import pl.pg.edu.eti.creators.people.*;
import pl.pg.edu.eti.initialize.InitializeData;
import pl.pg.edu.eti.pieces.Piece;
import pl.pg.edu.eti.reviews.Review;

import java.util.*;

/**
 * Project entry point - browsing pieces, obtaining recommendation
 *
 * @author Marek Drwal
 * @author Michał Kruczkowski
 * @author Natalia Bielacka
 * @author Piotr Bereznowski
 * @version 3.6
 */
public class Recommender {

    /**
     * Value for calculating differences between rating
     * Allows to compare ratings with accuracy to 0.001
     */
    protected static final int RATING_COMPARISON_ACCURACY = 1000;

    /**
     * Value indicating that two elements are equal
     */
    protected static final int BOTH_NULL = 0;
    /**
     * Value indicating that the first element is greater than the second
     * Used when one of the elements is null
     */
    protected static final int FIRST_NULL = 1;
    /**
     * Value indicating that the first element is less than the second
     * Used when one of the elements is null
     */
    protected static final int SECOND_NULL = -1;


    /**
     * Main driver method making recommendation
     *
     * @param args extra cmd arguments
     */
    public static void main(String[] args) {
        ArrayList<Category> categories = new ArrayList<Category>();
        ArrayList<Creator> creators = new ArrayList<Creator>();
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        ArrayList<Review> reviews = new ArrayList<Review>();

        InitializeData initializeData = new InitializeData(categories, creators, pieces, reviews);
        initializeData.run();

        // TODO load data

        CreatorNameComparator creatorNameComparator = new CreatorNameComparator();
        Collections.sort(creators, creatorNameComparator);

        System.out.println("Recommender");
        System.out.println("1. Browse categories");
        System.out.println("2. Browse creators");
        String option = Reviewer.inputScanner.nextLine();
        try {
            int id;
            switch (option.charAt(0)) {
                case '1':
                    System.out.println("1. Browse categories");
                    printElements(categories);
                    id = Reviewer.readInt("Choose category no.: ") - 1;
                    Category category = categories.get(id);
                    System.out.println("Category "+category.getName());
                    choosePieceRecommend(category.getPieces(),pieces);
                    break;
                case '2':
                    System.out.println("1. Browse all creators");
                    System.out.println("2. Browse actors");
                    System.out.println("3. Browse directors");
                    System.out.println("4. Browse game creators");
                    System.out.println("5. Browse screenwriters");
                    System.out.println("6. Browse writers");
                    System.out.println("7. Browse book publishers");
                    System.out.println("8. Browse film studios");
                    System.out.println("9. Browse game studios");
                    String creatorType = Reviewer.inputScanner.nextLine();
                    try {
                        switch (creatorType.charAt(0)) {
                            case '1':
                                printElements(creators);
                                printCreatorPieces(creators, pieces);
                                break;
                            case '2':
                                ArrayList<Creator> actors = new ArrayList<Creator>();
                                for (Creator creator : creators){
                                    if (creator instanceof Actor)
                                        actors.add(creator);
                                }
                                printElements(actors);
                                printCreatorPieces(actors, pieces);
                                break;
                            case '3':
                                ArrayList<Creator> directors = new ArrayList<Creator>();
                                for (Creator creator : creators){
                                    if (creator instanceof Director)
                                        directors.add(creator);
                                }
                                printElements(directors);
                                printCreatorPieces(directors, pieces);
                                break;
                            case '4':
                                ArrayList<Creator> gameCreators = new ArrayList<Creator>();
                                for (Creator creator : creators){
                                    if (creator instanceof GameCreator)
                                        gameCreators.add(creator);
                                }
                                printElements(gameCreators);
                                printCreatorPieces(gameCreators, pieces);
                                break;
                            case '5':
                                ArrayList<Creator> screenwriters = new ArrayList<Creator>();
                                for (Creator creator : creators){
                                    if (creator instanceof Screenwriter)
                                        screenwriters.add(creator);
                                }
                                printElements(screenwriters);
                                printCreatorPieces(screenwriters, pieces);
                                break;
                            case '6':
                                ArrayList<Creator> writers = new ArrayList<Creator>();
                                for (Creator creator : creators){
                                    if (creator instanceof Writer)
                                        writers.add(creator);
                                }
                                printElements(writers);
                                printCreatorPieces(writers, pieces);
                                break;
                            case '7':
                                ArrayList<Creator> bookPublishers = new ArrayList<Creator>();
                                for (Creator creator : creators){
                                    if (creator instanceof BookPublisher)
                                        bookPublishers.add(creator);
                                }
                                printElements(bookPublishers);
                                printCreatorPieces(bookPublishers, pieces);
                                break;
                            case '8':
                                ArrayList<Creator> filmStudios = new ArrayList<Creator>();
                                for (Creator creator : creators){
                                    if (creator instanceof FilmStudio)
                                        filmStudios.add(creator);
                                }
                                printElements(filmStudios);
                                printCreatorPieces(filmStudios, pieces);
                                break;
                            case '9':
                                ArrayList<Creator> gameStudios = new ArrayList<Creator>();
                                for (Creator creator : creators){
                                    if (creator instanceof GameStudio)
                                        gameStudios.add(creator);
                                }
                                printElements(gameStudios);
                                printCreatorPieces(gameStudios, pieces);
                                break;
                            default:
                                System.out.println("Invalid option");
                        }
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException exception) {
                        System.out.println("Erroneous input");
                    }
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println("Erroneous input");
        }
        // TODO category's rating (aggregated)
        // TODO creator's rating (aggregated)
    }

    public static void printCreatorPieces(ArrayList<Creator> creators, ArrayList<Piece> pieces){
        int id;
        id = Reviewer.readInt("Choose creator no.: ") - 1;
        Creator creator = creators.get(id);
        choosePieceRecommend(creator.getPieces(), pieces);
    }

    /**
     * Displays elements from a collection as a numbered list
     *
     * @param collection collection of elements to be printed to screen
     */
    public static void printElements(Collection collection) {
        if (collection != null) {
            int i = 1;
            Iterator iterator = collection.iterator();
            while (iterator.hasNext()) {
                System.out.println(i++ + ". " + iterator.next());
            }
        }
    }

    /**
     * Auxiliary function to display pieces and choose one
     *
     * @param pieces list of pieces
     * @return chosen piece or null
     */
    public static Piece choosePiece(List<Piece> pieces) {
        sortPiecesByTitleRatingReleaseDate(pieces);
        printElements(pieces);
        try {
            return pieces.get(Reviewer.readInt("Choose piece no.: ") - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println("Erroneous input");
            return null;
        }
    }

    /**
     * Display pieces, choose one, give recommendation
     *
     * @param pieces subset of pieces
     */
    private static void choosePieceRecommend(List<Piece> pieces,ArrayList<Piece> allPieces) {
        recommend(choosePiece(pieces),allPieces);
    }


    /**
     * Provides recommendations for a given piece based on similarity to other pieces.
     *
     * @param piece piece to give recommendation to
     * @param pieces all pieces
     *
     */
    private static void recommend(Piece piece,ArrayList<Piece> pieces) {
        System.out.println("Here are top recommendations for the piece "+piece.getTitle()+": ");
        ArrayList<Piece> otherpieces = pieces;
        otherpieces.remove(piece);
        int top1 = 100;
        Piece top1Piece = null;
        int top2 = 100;
        Piece top2Piece = null;
        int top3 = 100;
        Piece top3Piece = null;

        for (Piece p: otherpieces){
            int similarity = Math.abs(p.compareTo(piece));
            if(similarity<top1){
                top3=top2; //decrease position of previous top pieces
                top3Piece=top2Piece;
                top2=top1;
                top2Piece=top1Piece;
                top1=similarity;
                top1Piece=p;

            } else if (similarity<top2) {
                top3=top2;
                top3Piece=top2Piece;
                top2=similarity;
                top2Piece=p;
            } else if (similarity<top3) {
                top3=similarity;
                top3Piece=p;
            }
        }
        assert top1Piece != null;
        System.out.println(top1Piece.getTitle()+" "+top1);
        assert top2Piece != null;
        System.out.println(top2Piece.getTitle()+" "+top2);
        assert top3Piece != null;
        System.out.println(top3Piece.getTitle()+" "+top3);
    }

    /**
     * Sorts pieces by title, rating, and release date.
     * The titles should be sorted in such a way that:
     * a) a list is in alphabetical order,
     * b) pieces with the same title by different rating are in descending order,
     * c) newer pieces are presented before older pieces     *
     *
     * @param pieces a list of pieces to sort
     */
    private static void sortPiecesByTitleRatingReleaseDate(List<Piece> pieces) {
        Comparator<Piece> byTitle = (p1, p2) -> {
            if (p1.getTitle() == null && p2.getTitle() == null) {
                return BOTH_NULL;
            } else if (p1.getTitle() == null && p2.getTitle() != null) {
                return FIRST_NULL;
            } else if (p1.getTitle() != null && p2.getTitle() == null) {
                return SECOND_NULL;
            } else {
                return p1.getTitle().compareTo(p2.getTitle());
            }
        };
        Comparator<Piece> byRating = (p1, p2) -> (int)((p2.getRating() - p1.getRating()) * RATING_COMPARISON_ACCURACY);
        Comparator<Piece> byReleaseDate = (p1, p2) -> {
            if (p1.getReleaseDate() == null && p2.getReleaseDate() == null) {
                return BOTH_NULL;
            } else if (p1.getReleaseDate() == null && p2.getReleaseDate() != null) {
                return FIRST_NULL;
            } else if (p1.getReleaseDate() != null && p2.getReleaseDate() == null) {
                return SECOND_NULL;
            } else {
                return p2.getReleaseDate().compareTo(p1.getReleaseDate());
            }
        };

        if(pieces == null || pieces.isEmpty()) {
            return;
        }

        System.out.println("Choose sorting method for pieces");
        System.out.println("1. First by title then by rating");
        System.out.println("2. First rating then by title");
        System.out.println("3. First by release date then by rating and title");
        System.out.println("4. First by title then by rating and release date");

        String option = Reviewer.inputScanner.nextLine();
        try {
            switch (option.charAt(0)) {
                case '1':
                    Collections.sort(pieces, byTitle
                            .thenComparing(byRating));
                    break;
                case '2':
                    Collections.sort(pieces, byRating
                            .thenComparing(byTitle));
                    break;
                case '3':
                    Collections.sort(pieces, byReleaseDate
                            .thenComparing(byRating)
                            .thenComparing(byTitle));
                    break;
                case '4':
                    Collections.sort(pieces, byTitle
                            .thenComparing(byRating)
                            .thenComparing(byReleaseDate));
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println("Erroneous input");
        }
    }

}


