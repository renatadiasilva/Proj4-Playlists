package pt.uc.dei.aor.paj.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "playlists")
public class Playlist implements Serializable {

	private static final long serialVersionUID = -341288742583267978L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_creation", nullable = false)
	private Date dateOfCriation;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;
	
	@ManyToMany
	@JoinTable(name = "playlist_contains_songs",
		joinColumns = @JoinColumn(name = "playlist_id"),
		inverseJoinColumns = @JoinColumn(name = "song_id"))
	private List<Song> songs;
	
	public Playlist() {
	}
	
	public Playlist(String name, Date dateOfCriation, User owner) {
		this.name = name;
		this.dateOfCriation = dateOfCriation;
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfCriation() {
		return dateOfCriation;
	}

	public void setDateOfCriation(Date dateOfCriation) {
		this.dateOfCriation = dateOfCriation;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getOwner() {
		return owner;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}
	
	public void addSong(Song s) {

		if (this.songs == null) this.songs = new ArrayList<Song>();
		this.songs.add(s);
		// não deixar adicionar se já lá está???
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Playlist other = (Playlist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
