package pt.uc.dei.aor.paj.web;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import pt.uc.dei.aor.paj.data.User;
import pt.uc.dei.aor.paj.ejb.UserEJBRemote;

@Named
@ApplicationScoped
public class LoginManager {

	@EJB
	private UserEJBRemote userEJB;

	public LoginManager() {
	}

	public synchronized void addUser(User user) {
		userEJB.addUser(user);
	}

	public boolean userLogin(String email, String password) {
		return userEJB.userLogin(email, password);
	}

	public boolean checkUserEmail(String email) {
		return userEJB.checkUserEmail(email);
	}
		
	public List<User> getUsers() {
		return userEJB.getUsers();
	}
	
}
