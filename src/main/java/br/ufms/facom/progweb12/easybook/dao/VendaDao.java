package br.ufms.facom.progweb12.easybook.dao;

import java.util.List;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractDao;
import br.ufms.facom.progweb12.easybook.model.Venda;

public class VendaDao extends AbstractDao<Venda> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7954205822450049501L;

	public VendaDao() {
		super(Venda.class);
	}

	@Override
	public List<Venda> findAll() {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select v.*, \n");
			sql.append(" u.nomecompleto as nomeComprador, \n");
			sql.append(" e.titulo as ebookTitulo, \n");
			sql.append(" TO_CHAR(v.dataVenda, 'DD/MM/YYYY HH:MI') as dataVendaFormatada \n");
			sql.append(" from venda v \n");
			sql.append(" join usuario u on u.id=v.id_usuario \n");
			sql.append(" join ebook e on e.id=v.id_ebook \n");
			sql.append(" order by dataVenda asc ");
			return executeQueryListResult(sql.toString(), Venda.class);
		} catch (Exception e) {
			System.err.println("Erro VendaDao[01]: " + e.getMessage());
		}

		return null;
	}
	
	public List<Venda> findDezUltimasVendas() {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select v.*, \n");
			sql.append(" u.nomecompleto as nomeComprador, \n");
			sql.append(" e.titulo as ebookTitulo, \n");
			sql.append(" TO_CHAR(v.dataVenda, 'DD/MM/YYYY HH:MI') as dataVendaFormatada \n");
			sql.append(" from venda v \n");
			sql.append(" join usuario u on u.id=v.id_usuario \n");
			sql.append(" join ebook e on e.id=v.id_ebook \n");
			sql.append(" order by dataVenda desc ");
			sql.append(" limit 10 ");
			return executeQueryListResult(sql.toString(), Venda.class);
		} catch (Exception e) {
			System.err.println("Erro VendaDao[01]: " + e.getMessage());
		}

		return null;
	}

}
