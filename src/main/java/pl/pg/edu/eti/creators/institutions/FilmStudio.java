package pl.pg.edu.eti.creators.institutions;

import pl.pg.edu.eti.creators.people.Actor;
import pl.pg.edu.eti.creators.people.Director;
import pl.pg.edu.eti.creators.people.Person;
import pl.pg.edu.eti.creators.people.Screenwriter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Provides basic information about a film studio.
 * Film studio is an institution.
 *
 * @author Piotr Bereznowski
 * @version 1.0
 */
public class FilmStudio extends Institution {

    /**
     * A collection that contains:
     * {@link Actor} as a key representing actor who acted in a film created by the film studio
     * {@link Integer} as value representing the number of films in which actor acted
     */
    private TreeMap<Actor, Integer> actorToNumberOfFilms;

    /**
     * A collection that contains:
     * {@link Director} as a key representing director who directed a film created by the film studio
     * {@link Integer} as value representing the number of films in which director directed
     */
    private TreeMap<Director, Integer> directorToNumberOfFilms;

    /**
     * A collection that contains:
     * {@link Screenwriter} as a key representing screenwriter who wrote a film created by the film studio
     * {@link Integer} as value representing the number of films in which screenwriter wrote
     */
    private TreeMap<Screenwriter, Integer> screenwriterToNumberOfFilms;

    /**
     * Instantiates a new Film studio.
     *
     * @param name          name of the film studio
     * @param country       country in which the film studio is registered
     * @param establishedIn the year of establishing the film studio
     */
    public FilmStudio(String name, String country, Integer establishedIn) {
        super(name, country, establishedIn);
        this.actorToNumberOfFilms = new TreeMap<>();
        this.directorToNumberOfFilms = new TreeMap<>();
        this.screenwriterToNumberOfFilms = new TreeMap<>();
    }

    /**
     * Retrieves the collection of all actors who acted in a film created by the film studio and their film count
     *
     * @return actorToNumberOfFilms
     */
    public TreeMap<Actor, Integer> getActorToNumberOfFilms() {
        return actorToNumberOfFilms;
    }

    /**
     * Sets collection of actors who acted in a film created by the film studio and their film count
     *
     * @param actorToNumberOfFilms map of actors to number of films
     */
    public void setActorToNumberOfFilms(TreeMap<Actor, Integer> actorToNumberOfFilms) {
        this.actorToNumberOfFilms = actorToNumberOfFilms;
    }

    /**
     * Increases the number of films which an actor created with the film studio
     *
     * @param actor an actor whose film count needs to be increased
     */
    public void increaseActorFilmCount(Actor actor) {
        if (!actorToNumberOfFilms.containsKey(actor)) {
            actorToNumberOfFilms.put(actor, 1);
        } else {
            actorToNumberOfFilms.put(actor, actorToNumberOfFilms.get(actor) + 1);
        }
    }

    /**
     * Increases the number of films which a list of actors created with the film studio
     *
     * @param actors a list of actors whose film counts need to be increased
     */
    public void increaseActorsFilmCount(List<Actor> actors) {
        for (Actor a : actors) {
            increaseActorFilmCount(a);
        }
    }

    /**
     * Decrease the number of films which an actor created with the film studio
     *
     * @param actor an actor whose film count needs to be increased
     */
    public void decreaseActorFilmCount(Actor actor) {
        try {
            if (actorToNumberOfFilms.get(actor).equals(0)) {
                System.out.println("You have already removed all of the films " +
                        "published by this actor with this film studio");
            } else {
                actorToNumberOfFilms.put(actor, actorToNumberOfFilms.get(actor) - 1);
            }
        } catch (Exception e) {
            System.out.println("This actor did not created with this film studio!");
        }
    }

    /**
     * Decrease the number of films which a list of actors created with the film studio
     *
     * @param actors a list of actors whose film counts need to be decreased
     */
    public void decreaseActorsFilmCount(List<Actor> actors) {
        for (Actor a : actors) {
            decreaseActorFilmCount(a);
        }
    }

    /**
     * Retrieves the collection of all directors who directed a film created by the film studio and their film count
     *
     * @return directorToNumberOfFilms
     */
    public TreeMap<Director, Integer> getDirectorToNumberOfFilms() {
        return directorToNumberOfFilms;
    }

    /**
     * Sets collection of directors who directed a film created by the film studio and their film count
     *
     * @param directorToNumberOfFilms map of directors to number of films
     */
    public void setDirectorToNumberOfFilms(TreeMap<Director, Integer> directorToNumberOfFilms) {
        this.directorToNumberOfFilms = directorToNumberOfFilms;
    }

    /**
     * Increases the number of films which a director created with the film studio
     *
     * @param director a director whose film count needs to be increased
     */
    public void increaseDirectorFilmCount(Director director) {
        if (!directorToNumberOfFilms.containsKey(director)) {
            directorToNumberOfFilms.put(director, 1);
        } else {
            directorToNumberOfFilms.put(director, directorToNumberOfFilms.get(director) + 1);
        }
    }

    /**
     * Increases the number of films which a list of directors created with the film studio
     *
     * @param directors a list of directors whose film counts need to be increased
     */
    public void increaseDirectorsFilmCount(List<Director> directors) {
        for (Director a : directors) {
            increaseDirectorFilmCount(a);
        }
    }

    /**
     * Decrease the number of films which a director created with the film studio
     *
     * @param director a director whose film count needs to be increased
     */
    public void decreaseDirectorFilmCount(Director director) {
        try {
            if (directorToNumberOfFilms.get(director).equals(0)) {
                System.out.println("You have already removed all of the films " +
                        "published by this director with this film studio");
            } else {
                directorToNumberOfFilms.put(director, directorToNumberOfFilms.get(director) - 1);
            }
        } catch (Exception e) {
            System.out.println("This director did not created with this film studio!");
        }
    }

    /**
     * Decrease the number of films which a list of directors created with the film studio
     *
     * @param directors a list of directors whose film counts need to be decreased
     */
    public void decreaseDirectorsFilmCount(List<Director> directors) {
        for (Director a : directors) {
            decreaseDirectorFilmCount(a);
        }
    }

    /**
     * Retrieves the collection of all screenwriters who wrote a film created by the film studio and their film count
     *
     * @return screenwriterToNumberOfFilms
     */
    public TreeMap<Screenwriter, Integer> getScreenwriterToNumberOfFilms() {
        return screenwriterToNumberOfFilms;
    }

    /**
     * Sets collection of screenwriters who wrote a film created by the film studio and their film count
     *
     * @param screenwriterToNumberOfFilms map of screenwriters to number of films
     */
    public void setScreenwriterToNumberOfFilms(TreeMap<Screenwriter, Integer> screenwriterToNumberOfFilms) {
        this.screenwriterToNumberOfFilms = screenwriterToNumberOfFilms;
    }

    /**
     * Increases the number of films which a screenwriter created with the film studio
     *
     * @param screenwriter a screenwriter whose film count needs to be increased
     */
    public void increaseScreenwriterFilmCount(Screenwriter screenwriter) {
        if (!screenwriterToNumberOfFilms.containsKey(screenwriter)) {
            screenwriterToNumberOfFilms.put(screenwriter, 1);
        } else {
            screenwriterToNumberOfFilms.put(screenwriter, screenwriterToNumberOfFilms.get(screenwriter) + 1);
        }
    }

    /**
     * Increases the number of films which a list of screenwriters created with the film studio
     *
     * @param screenwriters a list of screenwriters whose film counts need to be increased
     */
    public void increaseScreenwritersFilmCount(List<Screenwriter> screenwriters) {
        for (Screenwriter a : screenwriters) {
            increaseScreenwriterFilmCount(a);
        }
    }

    /**
     * Decrease the number of films which a screenwriter created with the film studio
     *
     * @param screenwriter a screenwriter whose film count needs to be increased
     */
    public void decreaseScreenwriterFilmCount(Screenwriter screenwriter) {
        try {
            if (screenwriterToNumberOfFilms.get(screenwriter).equals(0)) {
                System.out.println("You have already removed all of the films " +
                        "published by this screenwriter with this film studio");
            } else {
                screenwriterToNumberOfFilms.put(screenwriter, screenwriterToNumberOfFilms.get(screenwriter) - 1);
            }
        } catch (Exception e) {
            System.out.println("This screenwriter did not created with this film studio!");
        }
    }

    /**
     * Decrease the number of films which a list of screenwriters created with the film studio
     *
     * @param screenwriters a list of screenwriters whose film counts need to be decreased
     */
    public void decreaseScreenwritersFilmCount(List<Screenwriter> screenwriters) {
        for (Screenwriter a : screenwriters) {
            decreaseScreenwriterFilmCount(a);
        }
    }

    /**
     * Gets the single most popular actor, director and screenwriter of the film studio
     *
     * @return topFilmStudioCreators - a list containing three elements which represent
     * the most prolific actor, director, and screenwriter of the film studio, respectively.
     */
    public List<Person> getTheMostProlificActorDirectorAndScreenwriter() {
        Actor act = new Actor();
        Director dir = new Director();
        Screenwriter scrwrt = new Screenwriter();
        Integer aCount = 0;
        Integer dCount = 0;
        Integer swCount = 0;

        for (Actor a : actorToNumberOfFilms.keySet()) {
            if (actorToNumberOfFilms.get(a) > aCount) {
                act = a;
                aCount = actorToNumberOfFilms.get(a);
            }
        }

        for (Director d : directorToNumberOfFilms.keySet()) {
            if (directorToNumberOfFilms.get(d) > dCount) {
                dir = d;
                dCount = actorToNumberOfFilms.get(d);
            }
        }

        for (Screenwriter sw : screenwriterToNumberOfFilms.keySet()) {
            if (screenwriterToNumberOfFilms.get(sw) > swCount) {
                scrwrt = sw;
                swCount = actorToNumberOfFilms.get(sw);
            }
        }

        List<Person> topFilmStudioCreators = new ArrayList<Person>();
        topFilmStudioCreators.add(act);
        topFilmStudioCreators.add(dir);
        topFilmStudioCreators.add(scrwrt);

        return topFilmStudioCreators;
    }


}
