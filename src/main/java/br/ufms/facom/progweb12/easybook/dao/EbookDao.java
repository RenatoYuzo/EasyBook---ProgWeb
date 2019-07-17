package br.ufms.facom.progweb12.easybook.dao;

import java.sql.PreparedStatement;
import java.util.List;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractDao;
import br.ufms.facom.progweb12.easybook.controller.SessionContext;
import br.ufms.facom.progweb12.easybook.model.Ebook;

public class EbookDao extends AbstractDao<Ebook> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3168732869481414851L;

	public EbookDao() {
		super(Ebook.class);
	}

	public List<Ebook> achaTodosEbooks() {
		try {
			String sql = "select * from ebook order by id";
			return executeQueryListResult(sql, Ebook.class);
		} catch (Exception e) {
			System.err.println("Erro EbookDao[01]: " + e.getMessage());
			return null;
		}
	}

	public List<Ebook> achaTodosEbooksDoUsuario() {
		try {
			String sql = "select * from ebook order by id";
			return executeQueryListResult(sql, Ebook.class);
		} catch (Exception e) {
			System.err.println("Erro EbookDao[01]: " + e.getMessage());
			return null;
		}
	}

	@Override
	public void persist(Ebook entity) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO public.ebook( ");
		sql.append(" titulo, editora, resumo, ano, autor, genero, preco, pdfbyte, imgByte) \n");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ");

		try (PreparedStatement ps = SessionContext.conn.prepareStatement(sql.toString())) {
			ps.setString(1, entity.getTitulo());
			ps.setString(2, entity.getEditora());
			ps.setString(3, entity.getResumo());
			ps.setInt(4, entity.getAno());
			ps.setString(5, entity.getAutor());
			ps.setString(6, entity.getGenero());
			ps.setDouble(7, entity.getPreco());
			ps.setBinaryStream(8, entity.getIsPdf(), entity.getSizePdf());
			ps.setBinaryStream(9, entity.getIsImg(), entity.getSizeImg());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erro EbookFormController[01]: " + e.getMessage());
		}
	}

	@Override
	public void update(Ebook entity) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update public.ebook ");
		sql.append(" set titulo=?,");
		sql.append(" editora=?,");
		sql.append(" resumo=?,");
		sql.append(" ano=?,");
		sql.append(" autor=?,");
		sql.append(" genero=?,");
		sql.append(" preco=?,");
		sql.append(" pdfbyte=?,");
		sql.append(" imgbyte=?");
		sql.append(" where id=?");

		try (PreparedStatement ps = SessionContext.conn.prepareStatement(sql.toString())) {
			ps.setString(1, entity.getTitulo());
			ps.setString(2, entity.getEditora());
			ps.setString(3, entity.getResumo());
			ps.setInt(4, entity.getAno());
			ps.setString(5, entity.getAutor());
			ps.setString(6, entity.getGenero());
			ps.setDouble(7, entity.getPreco());
			ps.setBinaryStream(8, entity.getIsPdf(), entity.getSizePdf());
			ps.setBinaryStream(9, entity.getIsImg(), entity.getSizeImg());
			ps.setInt(10, entity.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erro EbookFormController[02]: " + e.getMessage());
		}
	}

	public List<Ebook> findMaisVendidos() {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(id_ebook) \n");
			sql.append(" as numeroVendas, \n");
			sql.append(" e.* \n");
			sql.append(" from ebook e \n");
			sql.append(" left join venda v on v.id_ebook=e.id \n");
			sql.append(" group by e.id, e.editora,e.resumo,e.ano,e.autor,e.genero,e.preco,e.pdfbyte,e.imgbyte \n");
			sql.append(" order by count(id_ebook) desc");
			return executeQueryListResult(sql.toString(), Ebook.class);
		} catch (Exception e) {
			System.err.println("Erro EbookFormController[03]: " + e.getMessage());
			return null;
		}
	}

	public List<Ebook> achaMeusEbooks() {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from ebook \n");
			sql.append(" where id in (select id_ebook \n");
			sql.append("  			from venda \n");
			sql.append(" 			where id_usuario=" + SessionContext.usuarioLogado.getId());
			sql.append("  			and id_ebook is not null) \n");
			return executeQueryListResult(sql.toString(), Ebook.class);
		} catch (Exception e) {
			System.err.println("Erro EbookFormController[04]: " + e.getMessage());
			return null;
		}
	}

}
