package pt.uc.dei.aor.paj.web;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import pt.uc.dei.aor.paj.data.User;
import pt.uc.dei.aor.paj.ejb.EntitiesEJBRemote;

@Named
@ApplicationScoped  //Session? só usar LoginMB???
public class PlaylistsManagerMB {

	@EJB
	private EntitiesEJBRemote entitiesEJB;

	public PlaylistsManagerMB() {
	}

//	public void populate(){
//		entitiesEJB.populate();
//	}
	
	public void addUser(User user) {
		entitiesEJB.addUser(user);
	}

	public User userLogin(String email, String password) {
		return entitiesEJB.userLogin(email, password);
	}

	public boolean checkUserEmail(String email) {
		return entitiesEJB.checkUserEmail(email);
	}
		
	public List<User> getUsers() {
		return entitiesEJB.getUsers();
	}
	
	public List<User> getUsersStartingBy(String name) {
		return entitiesEJB.usersWithNameStartingBy(name);
	}
	
}