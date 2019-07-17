package br.ufms.facom.progweb12.easybook.controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractController;
import br.ufms.facom.progweb12.easybook.dao.UsuarioDao;
import br.ufms.facom.progweb12.easybook.model.Usuario;

@Named
@ViewScoped
public class UsuarioFormController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2339286693028993090L;

	@Inject
	private UsuarioDao userDao;

	private Integer id;
	private String nomeCompleto;
	private String cpf;
	private String endereco;
	private String telefone;
	private String celular;
	private String login;
	private String senha;
	private Boolean ehAdmin;

	private Usuario usuario;

	@PostConstruct
	@Override
	public void init() {
		if (!verificaAutenticacaoUsuarioSimplesLogado())
			return;

		usuario = new Usuario();
	}

	public void onload() {
		if (usuario != null) {
			id = usuario.getId();
			nomeCompleto = usuario.getNomeCompleto();
			cpf = usuario.getCpf();
			endereco = usuario.getEndereco();
			telefone = usuario.getTelefone();
			celular = usuario.getCelular();
			login = usuario.getLogin();
			senha = usuario.getSenha();
			ehAdmin = usuario.isEhAdmin();
		}
	}

	public void actionCadastrar() {
		Usuario usuario = new Usuario();
		usuario.setNomeCompleto(nomeCompleto);
		usuario.setCpf(cpf);
		usuario.setEndereco(endereco);
		usuario.setTelefone(telefone);
		usuario.setCelular(celular);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setEhAdmin(ehAdmin);

		userDao.persist(usuario);
		addInfo("Cadastrado com sucesso!");
		navigation(redirect("usuario-list.xhtml"));
	}

	public void actionAtualizar() {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNomeCompleto(nomeCompleto);
		usuario.setCpf(cpf);
		usuario.setEndereco(endereco);
		usuario.setTelefone(telefone);
		usuario.setCelular(celular);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setEhAdmin(ehAdmin);

		userDao.update(usuario);
		addInfo("Atualizado com sucesso!");
		navigation(redirect("meus-ebooks-list.xhtml"));
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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

	public Boolean getEhAdmin() {
		return ehAdmin;
	}

	public void setEhAdmin(Boolean ehAdmin) {
		this.ehAdmin = ehAdmin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
