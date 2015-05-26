package pt.uc.dei.aor.paj.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Playlists")
public class Playlist implements Serializable {

	private static final long serialVersionUID = -341288742583267978L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date dateOfCriation;
	
	@ManyToOne
	private User owner;
	
	@ManyToMany
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
		Playlist other = (Playlist) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
