package pt.uc.dei.aor.paj.web;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.paj.data.User;
import pt.uc.dei.aor.paj.ejb.UserEJBRemote;

@Named
@RequestScoped
public class Login {
	
	@EJB
	private UserEJBRemote userEJB;
	private String email;
	private String name;
	private String password;
	
	@Inject
	private LoginManager manager;
	
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
	
	public String doLogin() {
		boolean ok = manager.userLogin(email, password);
		
		if (ok) {
			aUser.setEmail(email);
			aUser.startSession();
			return "/pages/index?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login failure: Wrong email or password."));
			return "login";
		}
	}
	
	public String doLogout(){
		aUser.endSession();
		return "/login?faces-redirect=true";
	}
	
	public void populate(){
		userEJB.populate();
	}

	public List<User> getUsersStartingBy(String name) {
		return userEJB.usersWithNameStartingBy(name);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
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

}