package pt.uc.dei.aor.paj.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.paj.data.User;

@Named
@RequestScoped
public class Registo {

	private String username;
	private String password;
	private String passconfirm;
	@Inject Login login;
	@Inject ActiveUser auser;

	public Registo() {

	}

	public void registar(){
		this.username=login.getUsername();
		this.password=login.getPassword();
		if(password.equals(passconfirm) && (!password.isEmpty()) ){
			/* utilizador registado com sucesso */
		User u = new User(username, password,"email");
		//add user
		auser.changeModetoLogin();
		} else if (!password.equals(passconfirm)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passwords don't match."));
		}
	}
	
	/********* Getters e Setters ************/
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

	public String getPassconfirm() {
		return passconfirm;
	}

	public void setPassconfirm(String passconfirm) {
		this.passconfirm = passconfirm;
	}

}
