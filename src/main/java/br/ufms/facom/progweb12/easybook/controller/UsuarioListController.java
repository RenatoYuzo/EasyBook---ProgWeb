package br.ufms.facom.progweb12.easybook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractController;
import br.ufms.facom.progweb12.easybook.dao.UsuarioDao;
import br.ufms.facom.progweb12.easybook.model.Usuario;

@Named
@ViewScoped
public class UsuarioListController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3549436958769847869L;

	@Inject
	private UsuarioDao userService;

	private List<Usuario> listaUsuarios;

	@PostConstruct
	@Override
	public void init() {
		if (!verificaAutenticacaoUsuarioLogado())
			return;

		listaUsuarios = userService.achaTodosUsuarios();
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

}
