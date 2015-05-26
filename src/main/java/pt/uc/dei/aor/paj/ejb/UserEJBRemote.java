package pt.uc.dei.aor.paj.ejb;

import java.util.List;

import pt.uc.dei.aor.paj.data.User;

public interface UserEJBRemote {
	public void populate();
	 
	public List<User> getUsers();
	public List<User> usersWithNameStartingBy(String exp);
	public void addUser(User u);
	public boolean userLogin(String email, String password);
	public boolean checkUserEmail(String email);

}