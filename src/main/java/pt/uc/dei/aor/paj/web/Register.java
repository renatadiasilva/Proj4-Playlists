package pt.uc.dei.aor.paj.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.paj.data.User;

@Named
@RequestScoped
public class Register {

	private String email;
	private String password;
	private String repeatedPassword;
	private String name;
	
	@Inject
	private LoginManager manager;
	
	@Inject
	Login login;
	
	@Inject
	ActiveUser aUser;

	public Register() {

	}

	public void singUp() {

		this.email = login.getEmail();
		this.password = login.getPassword();
		
		if (password.equals(repeatedPassword)) {
			/* utilizador registado com sucesso */
			User u = new User(name, password, email);
			if (manager.checkUserEmail(email)) {
				manager.addUser(u); 
				aUser.changeToLogin();
			} else {  // user já existe
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("This email already exists!"));
				//limpar campos?
			}
				
		} else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passwords don't match."));

	}
	
	/********* Getters e Setters ************/
	public String getName() {
		return name;
	}

	public void setUserName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
