package pt.uc.dei.aor.paj.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name = "Users")
// @NamedQueries
public class User implements Serializable {

	private static final long serialVersionUID = -3895128862200329846L;

	// @NotNull
	// @Size
	// @Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String password;
	private String email;

	@OneToMany(mappedBy = "owner")
	@OrderBy("name")
	private List<Playlist> playlists;

	@OneToMany(mappedBy = "owner")
	private List<Song> songs;

	private static final Logger log = LoggerFactory.getLogger(User.class);

	public User() {
	}

	public User(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public String getName() {
		log.info("Getting user's name.");
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public String getPassword() {
	// return password;
	// }

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	// listar Users??? só nome?
	public String toString() {
		return name + " -> " + email;
	}

	// criar conta (novo user)
	// editar conta - dados
	// apagar conta (desaparece tudo - mesmo musicas??)
	// fazer login e logout
	// criar nova playlist
	// mudar nome a uma playlist
	// listar as playlists de forma ascendente/descendente por nome, data ou
	// tamanho
	// listar musicas de cada playlist, selecionando a playlist a partir da
	// lista
	// apagar playlist selecionada (não apaga as músicas)
	// adicionar e remover músicas a uma playlist
	// adicionar e remover músicas à aplicação
	// editar os dados das músicas
	// listar todas as músicas da aplicação
	// listar todas as músicas da aplicação que satisfaçam critério: pesquisar
	// por título e/ou artista
	// adicionar músicas ás playlists a partir dos dois tipos de listagens
	// anteriores

}
