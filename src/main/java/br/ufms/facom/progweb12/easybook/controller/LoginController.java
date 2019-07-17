package br.ufms.facom.progweb12.easybook.controller;

import java.sql.Connection;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractController;
import br.ufms.facom.progweb12.easybook.dao.FabricaConexao;
import br.ufms.facom.progweb12.easybook.dao.UsuarioDao;
import br.ufms.facom.progweb12.easybook.model.Usuario;

@Named
@ViewScoped
public class LoginController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6778102610595261057L;

	@Inject
	private UsuarioDao userService;

	private String login;
	private String senha;

	@Override
	public void init() {
	}

	public void actionAutenticar() {
		Usuario userLogando = null;
		try (Connection conn = FabricaConexao.getConnection()) {
			userLogando = userService.findByLogin(login, conn);
		} catch (Exception e) {
			System.out.println("Erro LoginController[01]: " + e.getMessage());
		}

		if (userLogando != null) {
			if (userLogando.getSenha().equals(senha)) {
				SessionContext.usuarioLogado = userLogando;

				if (SessionContext.conn == null)
					SessionContext.conn = FabricaConexao.getConnection();
				
				navigation(redirect("meus-ebooks-list.xhtml"));
			} else
				addError("Usuário Inválido!");
		} else
			addError("Usuário não encontrado!");

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
