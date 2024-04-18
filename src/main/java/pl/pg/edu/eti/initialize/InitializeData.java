package pl.pg.edu.eti.initialize;

import pl.pg.edu.eti.Category;
import pl.pg.edu.eti.creators.Creator;
import pl.pg.edu.eti.creators.institutions.BookPublisher;
import pl.pg.edu.eti.creators.institutions.FilmStudio;
import pl.pg.edu.eti.creators.institutions.GameStudio;
import pl.pg.edu.eti.creators.people.*;
import pl.pg.edu.eti.pieces.*;
import pl.pg.edu.eti.reviews.BookReview;
import pl.pg.edu.eti.reviews.FilmReview;
import pl.pg.edu.eti.reviews.GameReview;
import pl.pg.edu.eti.reviews.Review;

import javax.sound.sampled.ReverbType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Data initialization
 *
 * @author Piotr Bereznowski
 * @author Michał Kruczkowski
 * @version 1.2
 */
public class InitializeData {

    /**
     * The list of categories
     */
    private ArrayList<Category> categories;

    /**
     * The list of creators
     */
    private ArrayList<Creator> creators;

    /**
     * The list of pieces
     */
    private ArrayList<Piece> pieces;

    /**
     * The list of reviews
     */
    private ArrayList<Review> reviews;

    /**
     * Instantiates a new Initialize data.
     *
     * @param categories the list of categories
     * @param creators   the list of creators
     * @param pieces     the list of pieces
     * @param reviews    the list reviews
     */
    public InitializeData(ArrayList<Category> categories, ArrayList<Creator> creators,
                          ArrayList<Piece> pieces, ArrayList<Review> reviews) {
        this.categories = categories;
        this.creators = creators;
        this.pieces = pieces;
        this.reviews = reviews;
    }

    /**
     * The method which populates the application with data
     */
    public void run() {
        // populate categories
        Category cat1 = new Category("Category1");
        Category cat2 = new Category("Category2");
        Category cat3 = new Category("Category3");

        Collections.addAll(this.categories, cat1, cat2, cat3);

        // populate creators
        Writer wri1 = new Writer("Adam", "John", "Smith");
        Writer wri2 = new Writer("John", "Charles", "Novicki");
        Writer wri3 = new Writer("Test", "Tescik", "Testowy");

        List<Review> brl1 = new ArrayList<Review>();
        List<Review> brl2 = new ArrayList<Review>();
        List<Review> brl3 = new ArrayList<Review>();
        List<Review> brl4 = new ArrayList<Review>();

        // populate pieces
        Book book4 = new Book(brl1, "A Book #3", "ID04");
        Book book3 = new Book(brl2, "A Book #3", "ID03");
        Book book2 = new Book(brl3, "Book #1", "ID02");
        Book book1 = new Book(brl4, "Book #1", "ID01");

        Collections.addAll(this.pieces, book1, book2, book3, book4);


        book1.setWriter(wri1);
        book2.setWriter(wri3);
        book3.setWriter(wri2);
        book4.setWriter(wri2);

        ArrayList<Category> cl12 = new ArrayList<>();
        ArrayList<Category> cl13 = new ArrayList<>();

        cl12.add(cat1);
        cl12.add(cat2);
        cl13.add(cat1);
        cl13.add(cat3);

        book1.setCategories(cl12);
        book2.setCategories(cl12);
        book3.setCategories(cl13);
        book4.setCategories(cl13);

        book1.setRating(1.25);
        book2.setRating(3.86);
        book3.setRating(4.52);
        book4.setRating(4.52);

        book1.setReleaseDate(new Date(500000000));
        book2.setReleaseDate(new Date(500000000));
        book3.setReleaseDate(new Date(500000000));
        book4.setReleaseDate(new Date(250000000));

        cat1.addPiece(book1);
        cat1.addPiece(book2);
        cat1.addPiece(book3);
        cat1.addPiece(book4);
        cat2.addPiece(book1);
        cat2.addPiece(book2);
        cat3.addPiece(book3);
        cat3.addPiece(book4);

        Category category1 = new Category("Action");
        Category category2 = new Category("Adventure");
        Category category3 = new Category("Drama");
        Category category4 = new Category("Sci-Fi");
        Category category5 = new Category("Fantasy");
        Category category6 = new Category("Animation");
        Category category7 = new Category("Family");
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);
        categories.add(category6);
        categories.add(category7);
        Actor actor1=new Actor("Timothee",null,"Chalamet");
        Actor actor2 = new Actor("Zendaya",null,null);
        Director director1 = new Director("Denis",null,"Villeneuve");
        Director director2 = new Director("Joaquim","Dos","Santos");
        Director director3 = new Director("Hayao",null,"Miyazaki");
        Director director4 = new Director("Chrostopher",null,"Nolan");
        Screenwriter screenwriter1 = new Screenwriter("Hayao",null,"Miyazaki");
        creators.add(actor1);
        creators.add(actor2);
        creators.add(director1);
        creators.add(director2);
        creators.add(director3);
        creators.add(director4);
        creators.add(screenwriter1);
        Film film1 = new Film(new ArrayList<>(),"Dune: Part One","movie1", new Actor[]{actor1, actor2},director1,null,
                null, null);
        film1.setCategories(Arrays.asList(category1, category2, category3, category4));
        Film film2 = new Film(new ArrayList<>(),"Spider-Man: Across the Spider-Verse","movie2", null,director2,null,
                null, null);
        film2.setCategories(Arrays.asList(category1, category2, category4,category5,category6));
        Film film3 = new Film(new ArrayList<>(),"The Boy and the Heron","movie3", null,director3,screenwriter1,
                null, null);
        film3.setCategories(Arrays.asList(category3, category2, category7,category5,category6));
        Film film4 = new Film(new ArrayList<>(),"Dune: Part Two","movie4", new Actor[]{actor1, actor2},director1,null,
                null, null);
        film4.setCategories(Arrays.asList(category1, category2, category3, category4));
        Film film5 = new Film(new ArrayList<>(),"Interstellar","movie5", null,director4,null,
                null,null);
        film5.setCategories(Arrays.asList(category2, category3, category4));

        pieces.add(film1);
        pieces.add(film2);
        pieces.add(film3);
        pieces.add(film4);
        pieces.add(film5);

        actor1.addPiece(film1);
        actor2.addPiece(film1);
        director1.addPiece(film1);
        director2.addPiece(film2);
        director3.addPiece(film3);
        screenwriter1.addPiece(film3);
        actor1.addPiece(film4);
        actor2.addPiece(film4);
        director1.addPiece(film4);
        director4.addPiece(film5);

        category1.addPiece(film1);
        category2.addPiece(film1);
        category3.addPiece(film1);
        category4.addPiece(film1);
        category4.addPiece(film2);
        category5.addPiece(film2);
        category6.addPiece(film2);
        category1.addPiece(film2);
        category2.addPiece(film2);
        category2.addPiece(film3);
        category3.addPiece(film3);
        category5.addPiece(film3);
        category6.addPiece(film3);
        category7.addPiece(film3);
        category1.addPiece(film4);
        category2.addPiece(film4);
        category3.addPiece(film4);
        category4.addPiece(film4);
        category2.addPiece(film5);
        category3.addPiece(film5);
        category4.addPiece(film5);

        BookReview br1 = new BookReview("Reviewer 1", new Date(1000000),3.2, "Summary 1", "Full comment 1", 2.5, book1);
        BookReview br2 = new BookReview("Reviewer 1", new Date(1000000), 3.2, "Summary 2", "Full comment 2", 3.5, book1);
        BookReview br3 = new BookReview("Reviewer 2", new Date(4000000),4.2, "Summary 2", "Full comment 2", 1, book1);
        BookReview br4 = new BookReview("Reviewer 1", new Date(4000000),4.2, "Summary 2", "Full comment 2", 5, book1);

        book1.addReview(br1);
        book1.addReview(br2);
        book1.addReview(br3);
        book1.addReview(br4);

        Collections.addAll(reviews, br1, br2, br3, br4);

        Film f1 = new Film(new ArrayList<Review>(), "f", "1", null, null, null, null, null);
        Book b1 = new Book(new ArrayList<Review>(), "b", "2", null, null, 2020, 2, 2, 2);
        Game g1 = new Game(new ArrayList<Review>(), "g", "3");
        FilmReview fr1 = new FilmReview(new Date(), "aaa", 5, f1);
        FilmReview fr2 = new FilmReview(new Date(), "bbb", 4, f1);
        BookReview br5 = new BookReview("aaa", new Date(), 5, "nice", "It was quite satisfying to read", 3, b1);
        BookReview br6 = new BookReview("bbb", new Date(), 4, "OK", "", 0, b1);
        BookReview br7 = new BookReview("ccc", new Date(), 3, "", "", 0, b1);
        GameReview gr1 = new GameReview("aaa", new Date(), true, 0, 4, 6, g1);
        Collections.addAll(pieces, f1, b1, g1);
        Collections.addAll(reviews, br5, br6, br7, gr1,fr1,fr2);


        Actor actorA = new Actor("Andrzej", "Adam", "Antkowski");
        Director directorA = new Director("Błażej", "Bartłomiej", "Baranowski");
        GameCreator gameCreatorA = new GameCreator("Cedryk", "Czesław", "Czapiewski");
        Screenwriter screenwriterA = new Screenwriter("Dominik", "Daniel", "Dmowski");
        Writer writerA = new Writer("Eugeniusz", "Eryk", "Ekwadorski");
        BookPublisher bookPublisherA = new BookPublisher("Favorite Fiction", "France", 1999);
        FilmStudio filmStudioA = new FilmStudio("Genius Gag", "Germany", 2000);
        GameStudio gameStudioA = new GameStudio("Handy Hobbies", "Hungary", 2001);

        creators.add(bookPublisherA);
        creators.add(filmStudioA);
        creators.add(gameStudioA);
        creators.add(actorA);
        creators.add(directorA);
        creators.add(gameCreatorA);
        creators.add(screenwriterA);
        creators.add(writerA);
    }
}
