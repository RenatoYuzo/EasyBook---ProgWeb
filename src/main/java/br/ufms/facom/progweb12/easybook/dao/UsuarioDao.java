package br.ufms.facom.progweb12.easybook.dao;

import java.util.List;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractDao;
import br.ufms.facom.progweb12.easybook.model.Usuario;

public class UsuarioDao extends AbstractDao<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3536913969544642286L;

	public UsuarioDao() {
		super(Usuario.class);
	}

	public List<Usuario> achaTodosUsuarios() {
		try {
			String sql = "select * from usuario order by id";
			return executeQueryListResult(sql, Usuario.class);
		} catch (Exception e) {
			System.err.println("Erro UsuarioDao[01]: " + e.getMessage());
			return null;
		}
	}

}
