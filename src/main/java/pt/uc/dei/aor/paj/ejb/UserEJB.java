package pt.uc.dei.aor.paj.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.paj.data.Playlist;
import pt.uc.dei.aor.paj.data.Song;
import pt.uc.dei.aor.paj.data.User;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
public class UserEJB implements UserEJBRemote {

	@PersistenceContext(name = "Entities")
	private EntityManager em;

	private static final Logger log = LoggerFactory.getLogger(UserEJB.class);

	public UserEJB() {
	}

	public static Date getDate(int day, int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, day);

		Date d = cal.getTime();
		return d;
	}

	public void populate() {

		User[] listOfUsers = {
				new User("Renata Silva", "passR", "renatadiasilva@gmail.com"),
				new User("Catarina Lapo", "passC", "ciclapo@hotmail.com") };

		Playlist[] listOfPlaylists = {
				new Playlist("rsPL1", getDate(20, 5, 2015), listOfUsers[0]),
				new Playlist("rsPL2", getDate(21, 5, 2015), listOfUsers[0]),
				new Playlist("clPL1", getDate(19, 5, 2015), listOfUsers[1]),
				new Playlist("clPL2", getDate(21, 5, 2015), listOfUsers[1]), };

		Song[] listOfSongs = {
				new Song("Song1", "Artist1", "Album1", 2010, "C:/Desktop",
						listOfUsers[0]),
				new Song("Song2", "Artist1", "Album1", 2010, "C:/Desktop",
						listOfUsers[0]),
				new Song("Song3", "Artist2", "Album2", 2000, "C:/Desktop",
						listOfUsers[0]),
				new Song("Song4", "Artist2", "Album3", 2011, "C:/Desktop",
						listOfUsers[0]),
				new Song("Song5", "Artist3", "Album4", 2007, "C:/Desktop",
						listOfUsers[0]),
				new Song("Song6", "Artist3", "Album5", 2010, "C:/Desktop",
						listOfUsers[1]),
				new Song("Song7", "Artist3", "Album5", 1999, "C:/Desktop",
						listOfUsers[1]),
				new Song("Song8", "Artist4", "Album6", 2014, "C:/Desktop",
						listOfUsers[1]),
				new Song("Song9", "Artist4", "Album6", 2014, "C:/Desktop",
						listOfUsers[1]),
				new Song("Song10", "Artist5", "Album7", 2005, "C:/Desktop",
						listOfUsers[1]), };

		listOfPlaylists[0].addSong(listOfSongs[0]);
		listOfPlaylists[0].addSong(listOfSongs[1]);
		listOfPlaylists[0].addSong(listOfSongs[2]);
		listOfPlaylists[0].addSong(listOfSongs[7]);

		listOfPlaylists[1].addSong(listOfSongs[0]);
		listOfPlaylists[1].addSong(listOfSongs[3]);
		listOfPlaylists[1].addSong(listOfSongs[4]);
		listOfPlaylists[1].addSong(listOfSongs[9]);

		listOfPlaylists[2].addSong(listOfSongs[1]);
		listOfPlaylists[2].addSong(listOfSongs[4]);
		listOfPlaylists[2].addSong(listOfSongs[5]);
		listOfPlaylists[2].addSong(listOfSongs[6]);
		listOfPlaylists[2].addSong(listOfSongs[8]);
		listOfPlaylists[2].addSong(listOfSongs[9]);

		listOfPlaylists[3].addSong(listOfSongs[2]);
		listOfPlaylists[3].addSong(listOfSongs[7]);
		listOfPlaylists[3].addSong(listOfSongs[8]);
		listOfPlaylists[3].addSong(listOfSongs[9]);

		for (User u : listOfUsers)
			em.persist(u);

		for (Playlist p : listOfPlaylists)
			em.persist(p);

		for (Song s : listOfSongs)
			em.persist(s);

		int i = 0;
		for (User u : listOfUsers) {
			i++;
			log.debug("User " + i + ": " + u.toString());
		}

		log.trace("This is a trace message");
		log.debug("This is a debug message");
		log.info("This is a info message");
		log.warn("This is a warn message");
		log.error("This is a error message");

	}

	@Override
	public List<User> getUsers() {
		log.info("Creating Query for all users");
		Query q = em.createQuery("from User u");
		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();

		// for (User u : users) {
		// usernames.add(u.toString());
		// }

		try {
			int i = 1 / 0;
		} catch (ArithmeticException ex) {
			log.error("Catching an error: " + ex + ex.getMessage());
		}

		return users;
	}

	public List<User> usersWithNameStartingBy(String exp) {
		log.info("Creating Query for all users with name starting by "
				+ exp.substring(exp.length() - 2, exp.length() - 1));
		Query q = em.createQuery("from User u where u.name like :exp");
		q.setParameter("exp", exp);
		@SuppressWarnings("unchecked")
		List<User> result = q.getResultList();
		return result;
	}

}
