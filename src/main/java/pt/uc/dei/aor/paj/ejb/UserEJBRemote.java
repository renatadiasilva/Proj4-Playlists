package pt.uc.dei.aor.paj.ejb;

import java.util.List;

import pt.uc.dei.aor.paj.data.User;

public interface UserEJBRemote {
	public void populate();
	 
	public List<User> getUsers();
	public List<User> usersWithNameStartingBy(String exp);
}