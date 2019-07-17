package br.ufms.facom.progweb12.easybook.abstracts;

import java.io.Serializable;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.ufms.facom.progweb12.easybook.controller.SessionContext;

public abstract class AbstractController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5647963290058618404L;
	public static final String ACESSDENIED = "acessDenied.xhtml";
	public static final String LOGIN = "login.xhtml";

	public abstract void init();

	public static void addInfo(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	public static void addWarn(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
	}

	public static void addError(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	}

	public static void navigation(String outcome) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
		nav.performNavigation(outcome);
	}

	public String redirect(String url) {
		if (url == null)
			return "";

		if (url.contains("?"))
			return (url + "&faces-redirect=true");
		else
			return (url + "?faces-redirect=true");
	}

	public boolean verificaAutenticacaoUsuarioLogado() {
		if (SessionContext.usuarioLogado == null) {
			navigation(redirect(ACESSDENIED + "?erro=acesso"));
			return false;
		} else if (SessionContext.usuarioLogado.isEhAdmin() == false) {
			navigation(redirect(ACESSDENIED + "?erro=permissao"));
			return false;
		}
		return true;
	}

	public boolean verificaAutenticacaoUsuarioSimplesLogado() {
		if (SessionContext.usuarioLogado == null) {
			navigation(redirect(ACESSDENIED + "?erro=acesso"));
			return false;
		}
		return true;
	}

}
