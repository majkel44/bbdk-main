package pl.pg.edu.eti;

import pl.pg.edu.eti.creators.Creator;
import pl.pg.edu.eti.initialize.InitializeData;
import pl.pg.edu.eti.pieces.Book;
import pl.pg.edu.eti.pieces.Film;
import pl.pg.edu.eti.pieces.Game;
import pl.pg.edu.eti.pieces.Piece;
import pl.pg.edu.eti.reviews.BookReview;
import pl.pg.edu.eti.reviews.FilmReview;
import pl.pg.edu.eti.reviews.GameReview;
import pl.pg.edu.eti.reviews.Review;

import java.util.*;

/**
 * Project entry point - managing reviews
 *
 * @author Marek Drwal
 * @author Piotr Bereznowski
 * @version 2.7
 */
public class Reviewer {

    /**
     * Input scanner
     * Available in the whole package
     */
    static final Scanner inputScanner = new Scanner(System.in);

    /**
     * Main driver method enabling the user to manage reviews
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

        System.out.println("Reviews");
        String reviewer = args.length > 0 ? args[0] : readString("Enter your name: "); // 1st cmd argument

        System.out.println("1. Browse reviews");
        System.out.println("2. Browse reviews by piece");
        String opt = Reviewer.inputScanner.nextLine();

        try{
            switch (opt.charAt(0)) {
                case '1':
                    System.out.println("1. Browse reviews");
                    Piece p = Recommender.choosePiece(pieces);
                    List<Review> pieceReviews = p.getReviews();
                    System.out.println("Order of reviews");
                    System.out.println("1. Firstly by reviewed piece, secondly by reviewer, and thirdly by date44");
                    System.out.println("2. Firstly by reviewer and secondly by date");
                    String opt1 = inputScanner.nextLine();
                    switch (opt1.charAt(0)) {
                        case '1':
                            Collections.sort(pieceReviews);
                            break;
                        case '2':
                            sortReviewsByReviewerDate(pieceReviews);
                            break;
                    }
                    Recommender.printElements(pieceReviews);
                    break;

                case'2':
                    System.out.println("2. Browse reviews by piece");
                    boolean operateOnPieces = true;
                    while (operateOnPieces) {
                        Piece piece = Recommender.choosePiece(pieces);
                        if (piece != null) {
                            System.out.println(piece + " was chosen");
                            boolean operateOnChosenPiece = true;
                            while (operateOnChosenPiece) {
                                String option = printMenuChooseOption();
                                try {
                                    int id;
                                    switch (option.charAt(0)) {
                                        case '1', '2':
                                            if (getUserReviews(reviewer, piece).isEmpty()) {
                                                System.out.println("1. Add review");
                                                createReview(new Date(), reviewer, piece);
                                            } else {
                                                System.out.println("2. Edit review");
                                                editReview(new Date(), reviewer, piece);
                                            }
                                            break;
                                        case '3':
                                            System.out.println("3. Remove my review");
                                            reviews = getUserReviews(reviewer, piece);
                                            System.out.println("The reviews");
                                            if (reviews.isEmpty()) {
                                                System.out.println("The piece has no reviews yet");
                                            } else {
                                                Recommender.printElements(reviews);
                                                piece.getReviews().remove(reviews.get(readInt("Choose review to remove: ") - 1));
                                                piece.updateRating();
                                            }
                                            break;
                                        case '4':
                                            System.out.println("4. View my review:");
                                            reviews = getUserReviews(reviewer, piece);
                                            if (reviews.isEmpty()) {
                                                System.out.println("You have written no reviews for this piece");
                                            } else {
                                                Recommender.printElements(reviews);
                                            }
                                            break;
                                        case '5':
                                            System.out.println("5. View all reviews:");
                                            if (piece.getReviews().isEmpty()) {
                                                System.out.println("The piece has no reviews yet");
                                            } else {
                                                Recommender.printElements(piece.getReviews());
                                            }
                                            break;
                                        case '6':
                                            System.out.println("6. Summarise reviews");
                                            summariseReviews(piece);
                                            break;
                                        default:
                                            System.out.println("Exiting " + piece);
                                            operateOnChosenPiece = false;
                                            operateOnPieces = readString("Leave the app (y/n)?").equalsIgnoreCase("n");
                                    }
                                } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException exception) {
                                    System.out.println("Erroneous input");
                                }
                            }
                        } else {
                            operateOnPieces = false;
                            System.out.println("No pieces available. Exiting.");
                        }
                    }
                default:
                    System.out.println("Invalid option");
            }
        }catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println("Erroneous input");
        }


    }

    /**
     * Retrieves a list of reviews for the <i>Piece</i> written by the user
     *
     * @param reviewer username
     * @param piece    the piece reviewed
     * @return a subset of reviews
     */
    private static ArrayList<Review> getUserReviews(String reviewer, Piece piece) {
        ArrayList<Review> reviews = new ArrayList<>();
        for (Review r : piece.getReviews()) {
            if (Objects.equals(r.getReviewer(), reviewer)) {
                reviews.add(r);
            }
        }
        return reviews;
    }

    /**
     * Returns the latest review added by the user to the given piece
     *
     * @param reviewer author of the review, user
     * @param piece    the piece reviewed
     * @return chronologically the newest review
     */
    private static Review getUsersRecentReview(String reviewer, Piece piece) {
        return getUserReviews(reviewer, piece).stream().toList().get(0);
    }

    /**
     * Auxiliary method to create a review for book. When created, it is added to the piece
     *
     * @param date     review date
     * @param reviewer the user
     * @param piece    the piece reviewed
     * @return the review
     */
    private static BookReview createBookReview(Date date, String reviewer, Piece piece) {
        double rating = readDouble("Enter rating 0-5: ");
        String summary = readString("Enter summary: ");
        String comment = readString("Enter comment: ");
        return new BookReview(reviewer, date, rating, summary, comment, 0, piece);
    }

    /**
     * Auxiliary method to create a review for film. When created, it is added to the piece
     *
     * @param date     review date
     * @param reviewer the user
     * @param piece    the piece reviewed
     * @return the review
     */
    private static FilmReview createFilmReview(Date date, String reviewer, Piece piece) {
        double rating = readDouble("Enter rating 0-5: ");
        return new FilmReview(date, reviewer, rating, piece);
    }

    /**
     * Auxiliary method to create a review for game. When created, it is added to the piece
     *
     * @param date     review date
     * @param reviewer the user
     * @param piece    the piece reviewed
     * @return the review
     */
    private static GameReview createGameReview(Date date, String reviewer, Piece piece) {
        boolean recommended = false;
        int fun = 0;
        double hrs = 0;
        try {
            fun = readInt("Enter fun rate: ");
        } catch (NumberFormatException ignored) {
        }
        try {
            hrs = readDouble("Enter hours played: ");
        } catch (NullPointerException | NumberFormatException ignored) {
        }
        return new GameReview(reviewer, date, recommended, 0, fun, hrs, piece);
    }

    /**
     * Subprogram responsible for adding a review to the piece with necessary prompts
     *
     * @param date     review date
     * @param reviewer the user
     * @param piece    the piece reviewed
     */
    private static void createReview(Date date, String reviewer, Piece piece) {
        System.out.println("Creating a review");
        Review review = null;
        if (piece instanceof Book) {
            review = createBookReview(date, reviewer, piece);
        } else if (piece instanceof Film) {
            review = createFilmReview(date, reviewer, piece);
        } else if (piece instanceof Game) {
            review = createGameReview(date, reviewer, piece);
        }
        System.out.println("The review has been added successfully!");
        System.out.println(review);
    }


    /**
     * Displays menu for Reviewer when the Piece was chosen
     */
    private static String printMenuChooseOption() {
        System.out.println("\nMenu");
        System.out.println("0. Exit");
        System.out.println("1. Add review");
        System.out.println("2. Edit review");
        System.out.println("3. Remove review");
        System.out.println("4. View review");
        System.out.println("5. View all reviews:");
        System.out.println("6. Summarise reviews");
        return readString("Enter option 0-6: ");
    }

    /**
     * Ancillary method to ask for input. Requires manual exception handling
     *
     * @param prompt a prompt to be printed
     * @return inserted value in right data type
     */
    public static double readDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(inputScanner.nextLine());
    }

    /**
     * Ancillary method to ask for input. Requires manual exception handling
     *
     * @param prompt a prompt to be printed
     * @return inserted value in right data type
     */
    public static int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(inputScanner.nextLine());
    }

    /**
     * Ancillary method to ask for input
     *
     * @param prompt a prompt to be printed
     * @return inserted value in right data type
     */
    public static String readString(String prompt) {
        System.out.print(prompt);
        return inputScanner.nextLine();
    }

    /**
     * Counts number of reviews by rating range, minimum and maximum of book ratings
     *
     * @param piece the book whose reviews are to be summarised
     */
    private static void summariseReviews(Book piece) {
        int[] cnt = new int[BookReview.MAX_RATING - BookReview.MIN_RATING + 1];
        double min = BookReview.MAX_RATING;
        double max = BookReview.MIN_RATING;
        for (Review review : piece.getReviews()) {
            double rating = ((BookReview) review).getRating();
            int x = (int) (rating);
            cnt[x - BookReview.MIN_RATING]++;
            min = Math.min(rating, min);
            max = Math.max(rating, max);
        }
        for (int i = BookReview.MIN_RATING; i < BookReview.MAX_RATING; i++) {
            System.out.println(i + "-" + (int) (i + 1) + ": " + cnt[i]);
        }
        System.out.println(BookReview.MAX_RATING + ": " + cnt[BookReview.MAX_RATING]);
        System.out.println("Min rating: " + min + "\tMax rating: " + max);
        System.out.println("Total no. of reviews: " + piece.getReviews().size());
    }

    /**
     * Counts number of reviews by rating range, minimum and maximum of film ratings
     *
     * @param piece the film whose reviews are to be summarised
     */
    private static void summariseReviews(Film piece) {
        int[] cnt = new int[FilmReview.MAX_RATING - FilmReview.MIN_RATING + 1];
        double min = FilmReview.MAX_RATING;
        double max = FilmReview.MIN_RATING;
        for (Review review : piece.getReviews()) {
            double rating = ((FilmReview) review).getRating();
            int x = (int) (rating);
            cnt[x - FilmReview.MIN_RATING]++;
            min = Math.min(rating, min);
            max = Math.max(rating, max);
        }
        for (int i = FilmReview.MIN_RATING; i < FilmReview.MAX_RATING; i++) {
            System.out.println(i + "-" + (int) (i + 1) + ": " + cnt[i]);
        }
        System.out.println(FilmReview.MAX_RATING + ": " + cnt[FilmReview.MAX_RATING]);
        System.out.println("Min rating: " + min + "\tMax rating: " + max);
        System.out.println("Total no. of reviews: " + piece.getReviews().size());
    }

    /**
     * Counts how many times the game was recommended or not and displays the information
     *
     * @param piece the game whose reviews are to be summarised
     */
    private static void summariseReviews(Game piece) {
        int yea = 0;
        int nay = 0;
        for (Review review : piece.getReviews()) {
            GameReview r = (GameReview) review;
            yea = r.isRecommended() ? yea + 1 : yea;
        }
        nay = piece.getReviews().size() - yea;
        System.out.println("Recommends: " + yea + " (" + yea * 100.0 / (yea + nay) + "%)");
        System.out.println("Not recommends: " + nay + " (" + nay * 100.0 / (yea + nay) + "%)");
    }

    /**
     * Provides a basic summary of reviews
     *
     * @param piece the piece whose reviews are to be summarised
     */
    public static void summariseReviews(Piece piece) {
        if (!piece.getReviews().isEmpty()) {
            System.out.println("Review summary");
            if (piece instanceof Book) {
                summariseReviews((Book) piece);
            } else if (piece instanceof Film) {
                summariseReviews((Film) piece);
            } else if (piece instanceof Game) {
                summariseReviews((Game) piece);
            }
        } else {
            System.out.println("The piece has no reviews yet");
        }
    }

    /**
     * Subprogram responsible for editing the latest review to the piece with necessary prompts
     *
     * @param date     review date
     * @param reviewer the user
     * @param piece    the piece reviewed
     */
    private static void editReview(Date date, String reviewer, Piece piece) {
        Review latestReview = getUsersRecentReview(reviewer, piece);
        Review r2 = latestReview.clone(); // a snapshot of the latest review with the piece
        latestReview.setDate(date); // latest review updated - acts like new one
        if (latestReview instanceof FilmReview) {
            ((FilmReview) latestReview).setRating(readDouble("Enter new rating: "));
        } else if (latestReview instanceof BookReview) {
            ((BookReview) latestReview).setRating(readDouble("Enter new rating: "));
            if (readString("Would you like to write new review summary (y/n)? ").equalsIgnoreCase("y")) {
                ((BookReview) latestReview).setSummary(readString("Enter new summary: "));
            }
            if (readString("Would you like to write new review text (y/n)? ").equalsIgnoreCase("y")) {
                ((BookReview) latestReview).setFullComment(readString("Enter new comment: "));
            }
        } else if (latestReview instanceof GameReview) {
            ((GameReview) latestReview).setRecommended(readString("Is recommended (y/n)? ").equalsIgnoreCase("y"));
            ((GameReview) latestReview).setHrsPlayed(readDouble("Enter hours played: "));
        }
        piece.addReview(r2); // insert previously carbon-copied snapshot "back" to the piece
        piece.updateRating();
        System.out.println("The previous version of review was: " + r2);
        System.out.println("The new version of review is: " + latestReview);
        System.out.println("Note the difference in piece's rating and details");
    }

    /**
     * Sorts reviews by date.
     *
     * @param reviews a list of reviews to sort
     */
    private static void sortReviewsByReviewerDate(List<Review> reviews) {
        Comparator<Review> byReviewer = (r1, r2) -> r1.getReviewer().compareTo(r2.getReviewer());
        Comparator<Review> byDate = (r1, r2) -> r2.getDate().compareTo(r1.getDate());

        Collections.sort(reviews, byReviewer
                .thenComparing(byDate));
    }
}
