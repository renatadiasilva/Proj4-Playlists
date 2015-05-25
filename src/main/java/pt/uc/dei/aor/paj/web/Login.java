package pt.uc.dei.aor.paj.web;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.paj.data.User;
import pt.uc.dei.aor.paj.ejb.UserEJBRemote;

@Named
@RequestScoped
public class Login {

	//tirar daqui
	static Logger log = LoggerFactory.getLogger(Login.class); 
	
	@EJB
	private UserEJBRemote userEJB;
	private String username;
	private String password;
	
	@Inject
	private ActiveUser aUser;

	public Login() {
		super();
		
	}
		
//	public Login(String username, String password) {
//		super();
//		this.username = username;
//		this.password = password;
//	}
	
	public String fazLogin() {
		boolean success = true; // ver se tá incorreto username ou pass
		if(success){
			/* o que fazer se login for bem sucedido */
			aUser.setName(username);
			aUser.startSession();
			return "index?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login Failed. Username or Password incorrect."));
		}
		return "failed";
	}
	
	public String logout(){
		aUser.endSession();
		return "/login?faces-redirect=true";
	}
	
	public void populate(){
		log.info("Populating");
		userEJB.populate();
	}

	public List<User> getUsers() {
		log.info("Listing all users.");
		return userEJB.getUsers();
	}
	
	public List<User> getUsersStartingBy(String name) {
		log.info("Listing all users starting by "+name.substring(name.length()-2, name.length()-1));
		return userEJB.usersWithNameStartingBy(name);
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
