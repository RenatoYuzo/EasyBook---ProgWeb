package br.ufms.facom.progweb12.easybook.controller;

import java.sql.Connection;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractController;
import br.ufms.facom.progweb12.easybook.dao.FabricaConexao;
import br.ufms.facom.progweb12.easybook.model.Ebook;
import br.ufms.facom.progweb12.easybook.model.Usuario;

@Named
@SessionScoped
public class SessionContext extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7886044898357730202L;

	public static Usuario usuarioLogado;
	public static Connection conn;

	@PostConstruct
	@Override
	public void init() {
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		SessionContext.usuarioLogado = usuarioLogado;
	}

	public void verificaLogin() {
		if (SessionContext.usuarioLogado == null)
			navigation(redirect("view/login.xhtml"));
		else
			navigation(redirect("view/usuario-form.xhtml?id=" + SessionContext.usuarioLogado.getId()));
	}

	public void killSession() {
		System.out.println("Session Killed!");
		TelaPagamentoController.listaCarrinho = new ArrayList<Ebook>();
		usuarioLogado = null;
	}

	public boolean verificaConexaoBD() {
		if (SessionContext.conn == null)
			conn = FabricaConexao.getConnection();
		return true;
	}

}
