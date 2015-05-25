package pt.uc.dei.aor.paj.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity implementation class for Entity: Song
 *
 */
@Entity
@Table(name="Songs")
public class Song implements Serializable {
	
	private static final long serialVersionUID = -846738109409670761L;

	static Logger log = LoggerFactory.getLogger(Song.class); 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String title;
	private String artist;
	private String album;
	private int releaseYear;
	private String pathUpload;
	
	@ManyToOne
	private User owner;
	
	@ManyToMany (mappedBy="songs")
	private List<Playlist> playlists;

	public Song() {
		super();
	}

	public Song(String title, String artist, String album, int releaseYear,
			String pathUpload, User owner) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.releaseYear = releaseYear;
		this.pathUpload = pathUpload;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		log.info("Getting song's title.");
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getPathUpload() {
		return pathUpload;
	}

	public void setPathUpload(String pathUpload) {
		this.pathUpload = pathUpload;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}
