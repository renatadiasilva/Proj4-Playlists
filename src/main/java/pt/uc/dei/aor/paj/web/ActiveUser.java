package pt.uc.dei.aor.paj.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class ActiveUser implements Serializable {

	private static final long serialVersionUID = 1429959255702576110L;
	private String name;
	private boolean signup;
	private HttpSession session;

	public ActiveUser() {
		name = null;
		signup = false;
	}
	

	public void changeModetoLogin() {
		this.signup = false;
	}
	public void changeMode() {
		this.signup = true;
	}
	/********* Getters e Setters ************/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSignup() {
		return signup;
	}
	public void setSignup(boolean signup) {
		this.signup = signup;
	}
	
	public void startSession(){
		session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("username", name);
	}
	public void endSession(){
		if(session!=null)
			session.invalidate();
		name=null;
	}
}
